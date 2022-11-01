package com.google.android.libraries.phenotype.client.stable;

import android.net.Uri;
import android.util.Log;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.api.Configurations;
import com.google.android.libraries.phenotype.client.api.Flag;
import com.google.android.libraries.phenotype.client.stable.SnapshotProto$Snapshot;
import com.google.android.libraries.phenotype.client.stable.SnapshotProto$SnapshotFlag;
import com.google.android.libraries.storage.file.backends.AndroidUri;
import com.google.android.libraries.storage.file.behaviors.SyncingBehavior;
import com.google.android.libraries.storage.file.openers.ReadProtoOpener;
import com.google.android.libraries.storage.file.openers.WriteProtoOpener;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.util.concurrent.Callable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SnapshotHandler {
    private final String account;
    private final String configPackage;
    private final boolean directBootAware;
    private final PhenotypeContext phenotypeContext;
    private final Uri uri = buildUri();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.phenotype.client.stable.SnapshotHandler$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$libraries$phenotype$client$api$Flag$ValueCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$android$libraries$phenotype$client$stable$SnapshotProto$SnapshotFlag$ValueCase;

        static {
            int[] iArr = new int[SnapshotProto$SnapshotFlag.ValueCase.values().length];
            $SwitchMap$com$google$android$libraries$phenotype$client$stable$SnapshotProto$SnapshotFlag$ValueCase = iArr;
            try {
                iArr[SnapshotProto$SnapshotFlag.ValueCase.LONG_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$libraries$phenotype$client$stable$SnapshotProto$SnapshotFlag$ValueCase[SnapshotProto$SnapshotFlag.ValueCase.BOOLEAN_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$libraries$phenotype$client$stable$SnapshotProto$SnapshotFlag$ValueCase[SnapshotProto$SnapshotFlag.ValueCase.DOUBLE_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$libraries$phenotype$client$stable$SnapshotProto$SnapshotFlag$ValueCase[SnapshotProto$SnapshotFlag.ValueCase.STRING_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$libraries$phenotype$client$stable$SnapshotProto$SnapshotFlag$ValueCase[SnapshotProto$SnapshotFlag.ValueCase.BYTES_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            int[] iArr2 = new int[Flag.ValueCase.values().length];
            $SwitchMap$com$google$android$libraries$phenotype$client$api$Flag$ValueCase = iArr2;
            try {
                iArr2[Flag.ValueCase.LONG_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$libraries$phenotype$client$api$Flag$ValueCase[Flag.ValueCase.BOOL_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$android$libraries$phenotype$client$api$Flag$ValueCase[Flag.ValueCase.DOUBLE_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$android$libraries$phenotype$client$api$Flag$ValueCase[Flag.ValueCase.STRING_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$android$libraries$phenotype$client$api$Flag$ValueCase[Flag.ValueCase.BYTES_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public SnapshotHandler(PhenotypeContext phenotypeContext, String str, String str2, boolean z) {
        this.phenotypeContext = phenotypeContext;
        this.configPackage = str;
        this.account = str2;
        this.directBootAware = z;
    }

    private Uri buildUri() {
        AndroidUri.Builder module = AndroidUri.builder(this.phenotypeContext.getContext()).setModule("phenotype");
        String str = this.account;
        AndroidUri.Builder relativePath = module.setRelativePath(str + "/" + this.configPackage + ".pb");
        if (this.directBootAware && DirectBootUtils.supportsDirectBoot()) {
            relativePath.setDirectBootFilesLocation();
        }
        return relativePath.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SnapshotProto$Snapshot configurationToSnapshot(Configurations configurations) {
        SnapshotProto$Snapshot.Builder newBuilder = SnapshotProto$Snapshot.newBuilder();
        if (configurations == null) {
            return (SnapshotProto$Snapshot) newBuilder.build();
        }
        for (Flag flag : configurations.getFlagList()) {
            SnapshotProto$SnapshotFlag.Builder name = SnapshotProto$SnapshotFlag.newBuilder().setName(flag.getName());
            switch (AnonymousClass1.$SwitchMap$com$google$android$libraries$phenotype$client$api$Flag$ValueCase[flag.getValueCase().ordinal()]) {
                case 1:
                    name.setLongValue(flag.getLongValue());
                    break;
                case 2:
                    name.setBooleanValue(flag.getBoolValue());
                    break;
                case 3:
                    name.setDoubleValue(flag.getDoubleValue());
                    break;
                case 4:
                    name.setStringValue(flag.getStringValue());
                    break;
                case 5:
                    name.setBytesValue(flag.getBytesValue());
                    break;
                default:
                    throw new IllegalStateException("No known flag type");
            }
            newBuilder.addFlag((SnapshotProto$SnapshotFlag) name.build());
        }
        newBuilder.setServerToken(configurations.getServerToken()).setSnapshotToken(configurations.getSnapshotToken()).setConfigurationVersion(configurations.getConfigurationVersion());
        if (configurations.hasExperimentToken()) {
            newBuilder.setExperimentToken(configurations.getExperimentToken());
        }
        return (SnapshotProto$Snapshot) newBuilder.setLastUpdateEpochMillis(System.currentTimeMillis()).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenableFuture getLatestSnapshot(String str) {
        return Futures.transform(this.phenotypeContext.getPhenotypeClient().getConfigurationSnapshot(this.configPackage, str, null), SnapshotHandler$$ExternalSyntheticLambda1.INSTANCE, this.phenotypeContext.getExecutor());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SnapshotProto$Snapshot getStoredSnapshot() {
        try {
            return (SnapshotProto$Snapshot) this.phenotypeContext.getStorageBackend().open(this.uri, ReadProtoOpener.create(SnapshotProto$Snapshot.getDefaultInstance()));
        } catch (IOException | RuntimeException e) {
            Log.i("SnapshotHandler", "Unable to retrieve flag snapshot for " + this.configPackage + ", using defaults.");
            return SnapshotProto$Snapshot.getDefaultInstance();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateStoredSnapshot$0$com-google-android-libraries-phenotype-client-stable-SnapshotHandler  reason: not valid java name */
    public /* synthetic */ Void m377x8391e4c2(SnapshotProto$Snapshot snapshotProto$Snapshot) {
        try {
            Void r6 = (Void) this.phenotypeContext.getStorageBackend().open(this.uri, WriteProtoOpener.create(snapshotProto$Snapshot).withBehaviors(new SyncingBehavior()));
            return null;
        } catch (IOException | RuntimeException e) {
            Log.w("SnapshotHandler", "Failed to update snapshot for " + this.configPackage + " flags may be stale.", e);
            return null;
        }
    }

    public ListenableFuture updateStoredSnapshot(final SnapshotProto$Snapshot snapshotProto$Snapshot) {
        return Futures.submit(new Callable() { // from class: com.google.android.libraries.phenotype.client.stable.SnapshotHandler$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SnapshotHandler.this.m377x8391e4c2(snapshotProto$Snapshot);
            }
        }, this.phenotypeContext.getExecutor());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableMap snapshotToMap(SnapshotProto$Snapshot snapshotProto$Snapshot) {
        ImmutableMap.Builder builderWithExpectedSize = ImmutableMap.builderWithExpectedSize(snapshotProto$Snapshot.getFlagCount() + 3);
        for (SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag : snapshotProto$Snapshot.getFlagList()) {
            switch (AnonymousClass1.$SwitchMap$com$google$android$libraries$phenotype$client$stable$SnapshotProto$SnapshotFlag$ValueCase[snapshotProto$SnapshotFlag.getValueCase().ordinal()]) {
                case 1:
                    builderWithExpectedSize.put(snapshotProto$SnapshotFlag.getName(), Long.valueOf(snapshotProto$SnapshotFlag.getLongValue()));
                    break;
                case 2:
                    builderWithExpectedSize.put(snapshotProto$SnapshotFlag.getName(), Boolean.valueOf(snapshotProto$SnapshotFlag.getBooleanValue()));
                    break;
                case 3:
                    builderWithExpectedSize.put(snapshotProto$SnapshotFlag.getName(), Double.valueOf(snapshotProto$SnapshotFlag.getDoubleValue()));
                    break;
                case 4:
                    builderWithExpectedSize.put(snapshotProto$SnapshotFlag.getName(), snapshotProto$SnapshotFlag.getStringValue());
                    break;
                case 5:
                    builderWithExpectedSize.put(snapshotProto$SnapshotFlag.getName(), snapshotProto$SnapshotFlag.getBytesValue().toByteArray());
                    break;
            }
        }
        builderWithExpectedSize.put("__phenotype_server_token", snapshotProto$Snapshot.getServerToken());
        builderWithExpectedSize.put("__phenotype_snapshot_token", snapshotProto$Snapshot.getSnapshotToken());
        builderWithExpectedSize.put("__phenotype_configuration_version", Long.valueOf(snapshotProto$Snapshot.getConfigurationVersion()));
        return builderWithExpectedSize.buildKeepingLast();
    }
}
