package com.google.android.gms.phenotype;

import android.content.Context;
import android.util.Pair;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.phenotype.internal.IPhenotypeCallbacks;
import com.google.android.gms.phenotype.internal.IPhenotypeService;
import com.google.android.gms.phenotype.internal.PhenotypeClientImpl;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PhenotypeClient extends GoogleApi {
    private static long lastSyncAfterServingVersion = 0;
    private static Pair lastServingVersionAndTask = Pair.create(ServingVersion.fromServer(0), Tasks.forResult(null));

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class TaskPhenotypeCallbacks extends IPhenotypeCallbacks.Stub {
        private final TaskCompletionSource completionSource;

        private TaskPhenotypeCallbacks(TaskCompletionSource taskCompletionSource) {
            this.completionSource = taskCompletionSource;
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onCommitToConfiguration(Status status) {
            TaskUtil.setResultOrApiException(status, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onDogfoodsTokenRetrieved(Status status, DogfoodsToken dogfoodsToken) {
            TaskUtil.setResultOrApiException(status, dogfoodsToken, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onDogfoodsTokenSet(Status status) {
            TaskUtil.setResultOrApiException(status, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onExperimentTokensRetrieved(Status status, ExperimentTokens experimentTokens) {
            TaskUtil.setResultOrApiException(status, experimentTokens, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onGetCommittedConfiguration(Status status, Configurations configurations) {
            TaskUtil.setResultOrApiException(status, configurations, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onGetConfigurationSnapshot(Status status, Configurations configurations) {
            TaskUtil.setResultOrApiException(status, configurations, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onGetFlag(Status status, Flag flag) {
            TaskUtil.setResultOrApiException(status, flag, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onGetServingVersion(Status status, long j) {
            TaskUtil.setResultOrApiException(status, Long.valueOf(j), this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onListOrDeleteFlagOverrides(Status status, FlagOverrides flagOverrides) {
            TaskUtil.setResultOrApiException(status, flagOverrides, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onRegistered(Status status) {
            TaskUtil.setResultOrApiException(status, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onSetAppSpecificProperties(Status status) {
            TaskUtil.setResultOrApiException(status, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onSetDimensions(Status status) {
            TaskUtil.setResultOrApiException(status, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onSetFlagOverride(Status status) {
            TaskUtil.setResultOrApiException(status, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onSyncAfter(Status status, long j) {
            TaskUtil.setResultOrApiException(status, Long.valueOf(j), this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onUnregistered(Status status) {
            TaskUtil.setResultOrApiException(status, this.completionSource);
        }

        @Override // com.google.android.gms.phenotype.internal.IPhenotypeCallbacks
        public void onWeakRegistered(Status status) {
            TaskUtil.setResultOrApiException(status, this.completionSource);
        }
    }

    public PhenotypeClient(Context context) {
        super(context, Phenotype.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    private static Task apiNotSupportedTask() {
        return Tasks.forException(new ApiException(new Status(16)));
    }

    private boolean hasApkVersion(int i) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(getApplicationContext(), i) == 0;
    }

    public static /* synthetic */ void lambda$commitToConfiguration$10(String str, PhenotypeClientImpl phenotypeClientImpl, TaskCompletionSource taskCompletionSource) {
        ((IPhenotypeService) phenotypeClientImpl.getService()).commitToConfiguration(new TaskPhenotypeCallbacks(taskCompletionSource), str);
    }

    public static /* synthetic */ void lambda$commitToCurrentConfiguration$11(String str, String str2, PhenotypeClientImpl phenotypeClientImpl, TaskCompletionSource taskCompletionSource) {
        ((IPhenotypeService) phenotypeClientImpl.getService()).commitToConfiguration(new TaskPhenotypeCallbacks(taskCompletionSource), PhenotypeCore.getSnapshotTokenForCommitCurrent(str, str2));
    }

    public static /* synthetic */ void lambda$getConfigurationSnapshot$9(String str, String str2, String str3, PhenotypeClientImpl phenotypeClientImpl, TaskCompletionSource taskCompletionSource) {
        ((IPhenotypeService) phenotypeClientImpl.getService()).getConfigurationSnapshotWithToken(new TaskPhenotypeCallbacks(taskCompletionSource), str, str2, str3);
    }

    public static /* synthetic */ void lambda$register$0(String str, int i, String[] strArr, byte[] bArr, PhenotypeClientImpl phenotypeClientImpl, TaskCompletionSource taskCompletionSource) {
        ((IPhenotypeService) phenotypeClientImpl.getService()).register(new TaskPhenotypeCallbacks(taskCompletionSource), str, i, strArr, bArr);
    }

    public Task commitToCurrentConfiguration(final String str, final String str2) {
        if (!hasApkVersion(12451000)) {
            return apiNotSupportedTask();
        }
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.phenotype.PhenotypeClient$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                PhenotypeClient.lambda$commitToCurrentConfiguration$11(str, str2, (PhenotypeClientImpl) obj, (TaskCompletionSource) obj2);
            }
        }).build());
    }

    public Task commitToConfiguration(final String str) {
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.phenotype.PhenotypeClient$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                PhenotypeClient.lambda$commitToConfiguration$10(str, (PhenotypeClientImpl) obj, (TaskCompletionSource) obj2);
            }
        }).build());
    }

    public Task getConfigurationSnapshot(final String str, final String str2, final String str3) {
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.phenotype.PhenotypeClient$$ExternalSyntheticLambda23
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                PhenotypeClient.lambda$getConfigurationSnapshot$9(str, str2, str3, (PhenotypeClientImpl) obj, (TaskCompletionSource) obj2);
            }
        }).build());
    }

    public Task register(final String str, final int i, final String[] strArr, final byte[] bArr) {
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.phenotype.PhenotypeClient$$ExternalSyntheticLambda22
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                PhenotypeClient.lambda$register$0(str, i, strArr, bArr, (PhenotypeClientImpl) obj, (TaskCompletionSource) obj2);
            }
        }).build());
    }
}
