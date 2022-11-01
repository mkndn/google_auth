package com.google.android.libraries.phenotype.client.api;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.phenotype.Configuration;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.gmstasks.TaskFutures;
import com.google.android.libraries.phenotype.client.api.Configurations;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import javax.inject.Inject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThinPhenotypeClient implements PhenotypeClient {
    private final com.google.android.gms.phenotype.PhenotypeClient client;

    public static /* synthetic */ Configurations $r8$lambda$qEL711ysdvvPE1KfhKLdQmappHA(ThinPhenotypeClient thinPhenotypeClient, Task task) {
        return thinPhenotypeClient.convertConfigurations(task);
    }

    @Inject
    public ThinPhenotypeClient(com.google.android.gms.phenotype.PhenotypeClient phenotypeClient) {
        Preconditions.checkNotNull(phenotypeClient);
        this.client = phenotypeClient;
    }

    public Configurations convertConfigurations(Task task) {
        Configuration[] configurationArr;
        com.google.android.gms.phenotype.Configurations configurations = (com.google.android.gms.phenotype.Configurations) task.getResult();
        Configurations.Builder configurationVersion = Configurations.newBuilder().setSnapshotToken(configurations.snapshotToken).setServerToken(configurations.serverToken).setIsDelta(configurations.isDelta).setConfigurationVersion(configurations.configurationVersion);
        if (configurations.experimentToken != null) {
            configurationVersion.setExperimentToken(ByteString.copyFrom(configurations.experimentToken));
        }
        for (Configuration configuration : configurations.configurations) {
            for (com.google.android.gms.phenotype.Flag flag : configuration.flags) {
                configurationVersion.addFlag(convertFlag(flag));
            }
            if (configuration.deleteFlags != null) {
                for (String str : configuration.deleteFlags) {
                    configurationVersion.addDeleteFlag(str);
                }
            }
        }
        return (Configurations) configurationVersion.build();
    }

    private Flag convertFlag(com.google.android.gms.phenotype.Flag flag) {
        switch (flag.flagValueType) {
            case 1:
                return (Flag) Flag.newBuilder().setName(flag.name).setLongValue(flag.getLong()).build();
            case 2:
                return (Flag) Flag.newBuilder().setName(flag.name).setBoolValue(flag.getBoolean()).build();
            case 3:
                return (Flag) Flag.newBuilder().setName(flag.name).setDoubleValue(flag.getDouble()).build();
            case 4:
                return (Flag) Flag.newBuilder().setName(flag.name).setStringValue(flag.getString()).build();
            case 5:
                return (Flag) Flag.newBuilder().setName(flag.name).setBytesValue(ByteString.copyFrom(flag.getBytesValue())).build();
            default:
                throw new IllegalArgumentException("Unrecognized flag type: " + flag.flagValueType);
        }
    }

    public static /* synthetic */ ListenableFuture lambda$toListenableFuture$0(ApiException apiException) {
        throw new PhenotypeRuntimeException(apiException.getStatusCode(), apiException.getMessage(), apiException);
    }

    @Override // com.google.android.libraries.phenotype.client.api.PhenotypeClient
    public ListenableFuture commitToConfiguration(String str) {
        Preconditions.checkNotNull(str);
        return toListenableFuture(this.client.commitToConfiguration(str));
    }

    @Override // com.google.android.libraries.phenotype.client.api.PhenotypeClient
    public ListenableFuture getConfigurationSnapshot(String str, String str2, String str3) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        return toListenableFuture(this.client.getConfigurationSnapshot(str, str2, str3).continueWith(MoreExecutors.directExecutor(), new Continuation() { // from class: com.google.android.libraries.phenotype.client.api.ThinPhenotypeClient$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return ThinPhenotypeClient.$r8$lambda$qEL711ysdvvPE1KfhKLdQmappHA(ThinPhenotypeClient.this, task);
            }
        }));
    }

    private static ListenableFuture toListenableFuture(Task task) {
        return Futures.catchingAsync(TaskFutures.toListenableFuture(task), ApiException.class, ThinPhenotypeClient$$ExternalSyntheticLambda1.INSTANCE, MoreExecutors.directExecutor());
    }
}
