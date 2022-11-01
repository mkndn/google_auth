package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.auth.IAuthManagerService;
import com.google.android.gms.auth.account.data.GoogleAuthServiceClientFactory;
import com.google.android.gms.auth.firstparty.shared.Status;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesIncorrectManifestValueException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.libs.phenotype.GmsSdkFlagInitializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import googledata.experiments.mobile.gmscore.auth_account.features.GetTokenRefactor;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleAuthUtilLight {
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final int DELEGATION_TYPE_CHILD_IMPERSONATION = 1;
    public static final int DELEGATION_TYPE_UNKNOWN = 0;
    public static final int GCORE_REQUEST_GOOGLE_ACCOUNTS_MIN_VERSION = 11400000;
    public static final String[] ACCEPTABLE_ACCOUNT_TYPES = {"com.google", "com.google.work", "cn.google"};
    public static final String KEY_CALLER_UID = "callerUid";
    public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
    private static final ComponentName GET_TOKEN_SERVICE_COMPONENT_NAME = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
    private static final Logger sLogger = AuthLoggers.newLogger("GoogleAuthUtil");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ConnectionExecutor {
        Object exec(IBinder iBinder);
    }

    private static Object connectAndExecute(Context context, ComponentName componentName, ConnectionExecutor connectionExecutor) {
        return connectAndExecute(context, componentName, connectionExecutor, 0L);
    }

    private static void ensurePlayServicesAvailable(Context context, int i) {
        try {
            GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context.getApplicationContext(), i);
        } catch (GooglePlayServicesIncorrectManifestValueException e) {
            e = e;
            throw new GoogleAuthException(e.getMessage(), e);
        } catch (GooglePlayServicesNotAvailableException e2) {
            e = e2;
            throw new GoogleAuthException(e.getMessage(), e);
        } catch (GooglePlayServicesRepairableException e3) {
            throw new GooglePlayServicesAvailabilityException(e3.getConnectionStatusCode(), e3.getMessage(), e3.getIntent());
        }
    }

    private static TokenData extractTokenDataFrom(Bundle bundle) {
        TokenData fromWrappedBundle = TokenData.fromWrappedBundle(bundle, "tokenDetails");
        if (fromWrappedBundle != null) {
            return fromWrappedBundle;
        }
        String string = bundle.getString("Error");
        Preconditions.checkNotNull(string);
        throwGetTokenError(string, (Intent) bundle.getParcelable("userRecoveryIntent"));
        throw new GoogleAuthException();
    }

    public static Account[] getAccounts(Context context, String str) {
        Preconditions.checkNotEmpty(str);
        try {
            GoogleApiAvailabilityLight.getInstance().verifyGooglePlayServicesIsAvailable(context, 8400000);
            if (PlatformVersion.isAtLeastM()) {
                return getAccountsPostM(context, str);
            }
            return AccountManager.get(context).getAccountsByType(str);
        } catch (GooglePlayServicesIncorrectManifestValueException e) {
            throw new GooglePlayServicesNotAvailableException(18);
        }
    }

    private static Account[] getAccountsPostM(Context context, String str) {
        ContentProviderClient acquireContentProviderClient = ((Context) Preconditions.checkNotNull(context)).getContentResolver().acquireContentProviderClient("com.google.android.gms.auth.accounts");
        if (acquireContentProviderClient == null) {
            throw new RemoteException("The com.google.android.gms.auth.accounts provider is not available.");
        }
        try {
            try {
                Bundle call = acquireContentProviderClient.call("get_accounts", str, new Bundle());
                if (call == null) {
                    throw new RemoteException("Null result from AccountChimeraContentProvider");
                }
                Parcelable[] parcelableArray = call.getParcelableArray(AccountDb.TABLE_NAME);
                if (parcelableArray == null) {
                    throw new RemoteException("Key_Accounts is Null");
                }
                Account[] accountArr = new Account[parcelableArray.length];
                for (int i = 0; i < parcelableArray.length; i++) {
                    accountArr[i] = (Account) parcelableArray[i];
                }
                return accountArr;
            } catch (RemoteException e) {
                sLogger.e("RemoteException when fetching accounts", e, new Object[0]);
                throw e;
            } catch (Exception e2) {
                sLogger.e("Exception when getting accounts", e2, new Object[0]);
                throw new RemoteException("Accounts ContentProvider failed: " + e2.getMessage());
            }
        } finally {
            acquireContentProviderClient.release();
        }
    }

    private static Object getResultFromTask(Task task, String str) {
        try {
            return Tasks.await(task);
        } catch (InterruptedException e) {
            String format = String.format("Interrupted while waiting for the task of %s to finish.", str);
            sLogger.w(format, new Object[0]);
            throw new IOException(format, e);
        } catch (CancellationException e2) {
            String format2 = String.format("Canceled while waiting for the task of %s to finish.", str);
            sLogger.w(format2, new Object[0]);
            throw new IOException(format2, e2);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof ApiException) {
                throw ((ApiException) cause);
            }
            String format3 = String.format("Unable to get a result for %s due to ExecutionException.", str);
            sLogger.w(format3, new Object[0]);
            throw new IOException(format3, e3);
        }
    }

    public static String getToken(Context context, Account account, String str, Bundle bundle) {
        validateAccount(account);
        return getTokenWithDetails(context, account, str, bundle).getToken();
    }

    public static TokenData getTokenWithDetails(Context context, final Account account, final String str, Bundle bundle) {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        Preconditions.checkNotEmpty(str, "Scope cannot be empty or null.");
        validateAccount(account);
        ensurePlayServicesAvailable(context, 8400000);
        final Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        populateOptions(context, bundle2);
        GmsSdkFlagInitializer.initPhenotypeForSdk(context);
        if (GetTokenRefactor.gaulTokenApiEvolved() && isClientCompatible(context)) {
            try {
                Bundle bundle3 = (Bundle) getResultFromTask(GoogleAuthServiceClientFactory.newInstance(context).getTokenWithDetails(account, str, bundle2), "token retrieval");
                verifyResultNotNull(bundle3);
                return extractTokenDataFrom(bundle3);
            } catch (ApiException e) {
                logApiException(e, "token retrieval");
            }
        }
        return (TokenData) connectAndExecute(context, GET_TOKEN_SERVICE_COMPONENT_NAME, new ConnectionExecutor() { // from class: com.google.android.gms.auth.GoogleAuthUtilLight$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.auth.GoogleAuthUtilLight.ConnectionExecutor
            public final Object exec(IBinder iBinder) {
                return GoogleAuthUtilLight.lambda$getTokenWithDetails$0(account, str, bundle2, iBinder);
            }
        });
    }

    @Deprecated
    public static void invalidateToken(Context context, String str) {
        AccountManager.get(context).invalidateAuthToken("com.google", str);
    }

    private static boolean isClientAllowedForConnectionless(Context context, List list) {
        String str = context.getApplicationInfo().packageName;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((String) it.next()).equals(str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isClientCompatible(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context, 17895000) != 0) {
            return false;
        }
        return isClientAllowedForConnectionless(context, GetTokenRefactor.blockedPackages().getElementList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TokenData lambda$getTokenWithDetails$0(Account account, String str, Bundle bundle, IBinder iBinder) {
        Bundle tokenByAccount = IAuthManagerService.Stub.asInterface(iBinder).getTokenByAccount(account, str, bundle);
        if (tokenByAccount == null) {
            throw new IOException("Service call returned null");
        }
        return extractTokenDataFrom(tokenByAccount);
    }

    private static void logApiException(ApiException apiException, String str) {
        sLogger.w("%s failed via GoogleAuthServiceClient, falling back to previous approach:\n%s", str, Log.getStackTraceString(apiException));
    }

    private static void populateOptions(Context context, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        String str = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", str);
        String str2 = KEY_ANDROID_PACKAGE_NAME;
        if (TextUtils.isEmpty(bundle.getString(str2))) {
            bundle.putString(str2, str);
        }
        bundle.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
    }

    private static void throwGetTokenError(String str, Intent intent) {
        Status fromWireCode = Status.fromWireCode(str);
        if (Status.isUserRecoverableError(fromWireCode)) {
            sLogger.w("isUserRecoverableError status: " + String.valueOf(fromWireCode), new Object[0]);
            throw new UserRecoverableAuthException(str, intent);
        } else if (Status.isRetryableError(fromWireCode)) {
            throw new IOException(str);
        } else {
            throw new GoogleAuthException(str);
        }
    }

    private static void validateAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (TextUtils.isEmpty(account.name)) {
            throw new IllegalArgumentException("Account name cannot be empty!");
        }
        for (String str : ACCEPTABLE_ACCOUNT_TYPES) {
            if (str.equals(account.type)) {
                return;
            }
        }
        throw new IllegalArgumentException("Account type not supported");
    }

    private static Object verifyResultNotNull(Object obj) {
        if (obj == null) {
            sLogger.w("Service call returned null.", new Object[0]);
            throw new IOException("Service unavailable.");
        }
        return obj;
    }

    private static Object connectAndExecute(Context context, ComponentName componentName, ConnectionExecutor connectionExecutor, long j) {
        BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
        GmsClientSupervisor gmsClientSupervisor = GmsClientSupervisor.getInstance(context);
        try {
            if (gmsClientSupervisor.bindService(componentName, blockingServiceConnection, "GoogleAuthUtil")) {
                try {
                    try {
                        return j > 0 ? connectionExecutor.exec(blockingServiceConnection.getServiceWithTimeout(j, TimeUnit.MILLISECONDS)) : connectionExecutor.exec(blockingServiceConnection.getService());
                    } catch (RemoteException | InterruptedException | TimeoutException e) {
                        Log.i("GoogleAuthUtil", "Error on service connection.", e);
                        throw new IOException("Error on service connection.", e);
                    }
                } finally {
                    gmsClientSupervisor.unbindService(componentName, blockingServiceConnection, "GoogleAuthUtil");
                }
            }
            throw new IOException("Could not bind to service.");
        } catch (SecurityException e2) {
            Log.w("GoogleAuthUtil", String.format("SecurityException while bind to auth service: %s", e2.getMessage()));
            throw new IOException("SecurityException while binding to Auth service.", e2);
        }
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2, Bundle bundle) {
        return getToken(context, new Account(str, "com.google"), str2, bundle);
    }
}
