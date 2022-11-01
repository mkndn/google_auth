package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.ConnectionlessLifecycleHelper;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.GoogleApiWrapper;
import com.google.android.gms.common.api.internal.NonGmsServiceBrokerClient;
import com.google.android.gms.common.api.internal.SignInCoordinator;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GoogleApi {
    private final Api mApi;
    private final ApiKey mApiKey;
    private final Api.ApiOptions mApiOptions;
    private final String mAttributionTag;
    private final Context mContext;
    private final int mId;
    private final Looper mLooper;
    protected final GoogleApiManager mManager;
    private final StatusExceptionMapper mMapper;
    private final GoogleApiClient mWrapper;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Settings {
        public static final Settings DEFAULT_SETTINGS = new Builder().build();
        public final Looper looper;
        public final StatusExceptionMapper mapper;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Builder {
            private Account mAccount;
            private Looper mLooper;
            private StatusExceptionMapper mMapper;

            public Settings build() {
                if (this.mMapper == null) {
                    this.mMapper = new ApiExceptionMapper();
                }
                if (this.mLooper == null) {
                    this.mLooper = Looper.getMainLooper();
                }
                return new Settings(this.mMapper, this.mAccount, this.mLooper);
            }

            public Builder setLooper(Looper looper) {
                Preconditions.checkNotNull(looper, "Looper must not be null.");
                this.mLooper = looper;
                return this;
            }

            public Builder setMapper(StatusExceptionMapper statusExceptionMapper) {
                Preconditions.checkNotNull(statusExceptionMapper, "StatusExceptionMapper must not be null.");
                this.mMapper = statusExceptionMapper;
                return this;
            }
        }

        private Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper) {
            this.mapper = statusExceptionMapper;
            this.looper = looper;
        }
    }

    public GoogleApi(Activity activity, Api api, Api.ApiOptions apiOptions, Settings settings) {
        this(activity, activity, api, apiOptions, settings);
    }

    private BaseImplementation$ApiMethodImpl doNonListenerCall(int i, BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        baseImplementation$ApiMethodImpl.maybeMarkChain();
        this.mManager.execute(this, i, baseImplementation$ApiMethodImpl);
        return baseImplementation$ApiMethodImpl;
    }

    private Account getAccount() {
        GoogleSignInAccount googleSignInAccount;
        Api.ApiOptions apiOptions = this.mApiOptions;
        if ((apiOptions instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) && (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) apiOptions).getGoogleSignInAccount()) != null) {
            return googleSignInAccount.getAccount();
        }
        Api.ApiOptions apiOptions2 = this.mApiOptions;
        if (apiOptions2 instanceof Api.ApiOptions.HasAccountOptions) {
            return ((Api.ApiOptions.HasAccountOptions) apiOptions2).getAccount();
        }
        return null;
    }

    private static String getAttributionTag(Object obj) {
        if (PlatformVersion.isAtLeastR()) {
            try {
                return (String) Context.class.getMethod("getAttributionTag", new Class[0]).invoke(obj, new Object[0]);
            } catch (IllegalAccessException e) {
                return null;
            } catch (NoSuchMethodException e2) {
                return null;
            } catch (InvocationTargetException e3) {
                return null;
            }
        }
        return null;
    }

    private Set getRequiredScopes() {
        Api.ApiOptions apiOptions = this.mApiOptions;
        if (apiOptions instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) {
            GoogleSignInAccount googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) apiOptions).getGoogleSignInAccount();
            if (googleSignInAccount == null) {
                return Collections.emptySet();
            }
            return googleSignInAccount.getRequestedScopes();
        }
        return Collections.emptySet();
    }

    public GoogleApiClient asGoogleApiClient() {
        return this.mWrapper;
    }

    public Api.Client buildApiClient(Looper looper, GoogleApiManager.ClientConnection clientConnection) {
        Api.Client buildClient = ((Api.AbstractClientBuilder) Preconditions.checkNotNull(this.mApi.getClientBuilder())).buildClient(this.mContext, looper, createClientSettingsBuilder().build(), (Object) this.mApiOptions, (GoogleApiClient.ConnectionCallbacks) clientConnection, (GoogleApiClient.OnConnectionFailedListener) clientConnection);
        String contextAttributionTag = getContextAttributionTag();
        if (contextAttributionTag != null && (buildClient instanceof BaseGmsClient)) {
            ((BaseGmsClient) buildClient).setAttributionTag(contextAttributionTag);
        }
        if (contextAttributionTag != null && (buildClient instanceof NonGmsServiceBrokerClient)) {
            ((NonGmsServiceBrokerClient) buildClient).setAttributionTag(contextAttributionTag);
        }
        return buildClient;
    }

    protected ClientSettings.Builder createClientSettingsBuilder() {
        return new ClientSettings.Builder().setAccount(getAccount()).addAllRequiredScopes(getRequiredScopes()).setRealClientClassName(this.mContext.getClass().getName()).setRealClientPackageName(this.mContext.getPackageName());
    }

    public SignInCoordinator createSignInCoordinator(Context context, Handler handler) {
        return new SignInCoordinator(context, handler, createClientSettingsBuilder().build());
    }

    public BaseImplementation$ApiMethodImpl doBestEffortWrite(BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        return doNonListenerCall(2, baseImplementation$ApiMethodImpl);
    }

    public BaseImplementation$ApiMethodImpl doRead(BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        return doNonListenerCall(0, baseImplementation$ApiMethodImpl);
    }

    public BaseImplementation$ApiMethodImpl doWrite(BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        return doNonListenerCall(1, baseImplementation$ApiMethodImpl);
    }

    public ApiKey getApiKey() {
        return this.mApiKey;
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    protected String getContextAttributionTag() {
        return this.mAttributionTag;
    }

    public int getInstanceId() {
        return this.mId;
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public Task doBestEffortWrite(TaskApiCall taskApiCall) {
        return doNonListenerCall(2, taskApiCall);
    }

    public Task doRead(TaskApiCall taskApiCall) {
        return doNonListenerCall(0, taskApiCall);
    }

    public Task doWrite(TaskApiCall taskApiCall) {
        return doNonListenerCall(1, taskApiCall);
    }

    @Deprecated
    public GoogleApi(Activity activity, Api api, Api.ApiOptions apiOptions, StatusExceptionMapper statusExceptionMapper) {
        this(activity, api, apiOptions, new Settings.Builder().setMapper(statusExceptionMapper).setLooper(activity.getMainLooper()).build());
    }

    private Task doNonListenerCall(int i, TaskApiCall taskApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mManager.execute(this, i, taskApiCall, taskCompletionSource, this.mMapper);
        return taskCompletionSource.getTask();
    }

    private GoogleApi(Context context, Activity activity, Api api, Api.ApiOptions apiOptions, Settings settings) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        String attributionTag = getAttributionTag(context);
        this.mAttributionTag = attributionTag;
        this.mApi = api;
        this.mApiOptions = apiOptions;
        this.mLooper = settings.looper;
        ApiKey sharedApiKey = ApiKey.getSharedApiKey(api, apiOptions, attributionTag);
        this.mApiKey = sharedApiKey;
        this.mWrapper = new GoogleApiWrapper(this);
        GoogleApiManager googleApiManager = GoogleApiManager.getInstance(applicationContext);
        this.mManager = googleApiManager;
        this.mId = googleApiManager.getNextInstanceId();
        this.mMapper = settings.mapper;
        if (activity != null && !(activity instanceof GoogleApiActivity) && Looper.myLooper() == Looper.getMainLooper()) {
            ConnectionlessLifecycleHelper.initialize(activity, googleApiManager, sharedApiKey);
        }
        googleApiManager.register(this);
    }

    public GoogleApi(Context context, Api api, Api.ApiOptions apiOptions, Settings settings) {
        this(context, null, api, apiOptions, settings);
    }

    @Deprecated
    public GoogleApi(Context context, Api api, Api.ApiOptions apiOptions, StatusExceptionMapper statusExceptionMapper) {
        this(context, api, apiOptions, new Settings.Builder().setMapper(statusExceptionMapper).build());
    }
}
