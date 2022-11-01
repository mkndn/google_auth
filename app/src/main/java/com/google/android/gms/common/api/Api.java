package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Api {
    private final AbstractClientBuilder mClientBuilder;
    private final ClientKey mClientKey;
    private final String mName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class AbstractClientBuilder extends BaseClientBuilder {
        public static final int API_PRIORITY_GAMES = 1;
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;
        public static final int API_PRIORITY_PLUS = 2;

        @Deprecated
        public Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return buildClient(context, looper, clientSettings, obj, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
        }

        public Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AnyClient {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AnyClientKey {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ApiOptions {
        public static final NoOptions NO_OPTIONS = new NoOptions();

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface HasAccountOptions extends ApiOptions {
            Account getAccount();
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface HasGoogleSignInAccountOptions extends ApiOptions {
            GoogleSignInAccount getGoogleSignInAccount();
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class NoOptions implements ApiOptions {
            private NoOptions() {
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class BaseClientBuilder {
        public static final int API_PRIORITY_GAMES = 1;
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;
        public static final int API_PRIORITY_PLUS = 2;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Client extends AnyClient {
        void connect(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks);

        void disconnect();

        void disconnect(String str);

        Feature[] getAvailableFeatures();

        String getEndpointPackageName();

        String getLastDisconnectMessage();

        int getMinApkVersion();

        void getRemoteService(IAccountAccessor iAccountAccessor, Set set);

        Set getScopesForConnectionlessNonSignIn();

        boolean isConnected();

        boolean isConnecting();

        void onUserSignOut(BaseGmsClient.SignOutCallbacks signOutCallbacks);

        boolean requiresGooglePlayServices();

        boolean requiresSignIn();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ClientKey extends AnyClientKey {
    }

    public Api(String str, AbstractClientBuilder abstractClientBuilder, ClientKey clientKey) {
        Preconditions.checkNotNull(abstractClientBuilder, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull(clientKey, "Cannot construct an Api with a null ClientKey");
        this.mName = str;
        this.mClientBuilder = abstractClientBuilder;
        this.mClientKey = clientKey;
    }

    public AbstractClientBuilder getClientBuilder() {
        return this.mClientBuilder;
    }

    public AnyClientKey getClientKey() {
        return this.mClientKey;
    }

    public String getName() {
        return this.mName;
    }
}
