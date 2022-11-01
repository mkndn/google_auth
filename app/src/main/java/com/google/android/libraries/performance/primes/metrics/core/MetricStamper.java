package com.google.android.libraries.performance.primes.metrics.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.flashmanagement.storagestats.DataPartitionSize;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.version.PrimesVersion;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import javax.inject.Inject;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.SystemHealthProto$AccountableComponent;
import logs.proto.wireless.performance.mobile.SystemHealthProto$ApplicationInfo;
import logs.proto.wireless.performance.mobile.SystemHealthProto$DeviceInfo;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class MetricStamper {
    private final Context application;
    private final String applicationPackage;
    private final Supplier componentNameSupplier;
    private final DataPartitionSize dataPartitionSize;
    private final SystemHealthProto$ApplicationInfo.HardwareVariant hardwareVariant;
    private final String shortProcessName;
    private final Supplier totalDiskSizeKbSupplier;
    private final String versionName;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MetricStamper(Context context, Optional optional, String str) {
        Supplier supplier;
        this.application = context;
        this.applicationPackage = context.getPackageName();
        this.shortProcessName = ProcessStats.getShortProcessName(context);
        if (optional.isPresent()) {
            supplier = ((GlobalConfigurations) optional.get()).getComponentNameSupplier();
        } else {
            supplier = null;
        }
        this.componentNameSupplier = supplier;
        this.versionName = str;
        this.hardwareVariant = getHardwareVariant(context.getPackageManager());
        this.dataPartitionSize = new DataPartitionSize(context);
        this.totalDiskSizeKbSupplier = Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.performance.primes.metrics.core.MetricStamper$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return MetricStamper.this.m309x6f9eb5f3();
            }
        });
    }

    private static SystemHealthProto$ApplicationInfo.HardwareVariant getHardwareVariant(PackageManager packageManager) {
        SystemHealthProto$ApplicationInfo.HardwareVariant hardwareVariant = SystemHealthProto$ApplicationInfo.HardwareVariant.PHONE_OR_TABLET;
        if (Build.VERSION.SDK_INT >= 20) {
            if (packageManager.hasSystemFeature("android.hardware.type.watch")) {
                hardwareVariant = SystemHealthProto$ApplicationInfo.HardwareVariant.WATCH;
            } else if (Build.VERSION.SDK_INT >= 21 && packageManager.hasSystemFeature("android.software.leanback")) {
                hardwareVariant = SystemHealthProto$ApplicationInfo.HardwareVariant.LEANBACK;
            }
            if (Build.VERSION.SDK_INT >= 23 && packageManager.hasSystemFeature("android.hardware.type.automotive")) {
                return SystemHealthProto$ApplicationInfo.HardwareVariant.AUTOMOTIVE;
            }
        }
        return hardwareVariant;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-android-libraries-performance-primes-metrics-core-MetricStamper  reason: not valid java name */
    public /* synthetic */ Long m309x6f9eb5f3() {
        return Long.valueOf(this.dataPartitionSize.getSizeInBytes() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
    }

    public SystemHealthProto$SystemHealthMetric stamp(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        SystemHealthProto$SystemHealthMetric.Builder builder = (SystemHealthProto$SystemHealthMetric.Builder) systemHealthProto$SystemHealthMetric.toBuilder();
        SystemHealthProto$ApplicationInfo.Builder hardwareVariant = SystemHealthProto$ApplicationInfo.newBuilder().setHardwareVariant(this.hardwareVariant);
        String str = this.applicationPackage;
        if (str != null) {
            hardwareVariant.setApplicationPackage(str);
        }
        hardwareVariant.setPrimesVersion(PrimesVersion.getBaselineChangelist());
        String str2 = this.versionName;
        if (str2 != null) {
            hardwareVariant.setApplicationVersionName(str2);
        }
        String str3 = this.shortProcessName;
        if (str3 != null) {
            hardwareVariant.setShortProcessName(str3);
        }
        builder.setApplicationInfo(hardwareVariant);
        if (DirectBootUtils.isUserUnlocked(this.application)) {
            builder.setDeviceInfo(SystemHealthProto$DeviceInfo.newBuilder().setAvailableDiskSizeKb(this.dataPartitionSize.getFreeSpaceSizeInBytes() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID).setTotalDiskSizeKb(((Long) this.totalDiskSizeKbSupplier.get()).longValue()));
        }
        Supplier supplier = this.componentNameSupplier;
        String noPiiString = supplier == null ? null : ((NoPiiString) supplier.get()).toString();
        if (!TextUtils.isEmpty(noPiiString)) {
            SystemHealthProto$AccountableComponent.Builder builder2 = (SystemHealthProto$AccountableComponent.Builder) systemHealthProto$SystemHealthMetric.getAccountableComponent().toBuilder();
            if (builder2.getCustomName().isEmpty()) {
                builder2.setCustomName(noPiiString);
            } else {
                builder2.setCustomName(noPiiString + "::" + builder2.getCustomName());
            }
            builder.setAccountableComponent(builder2);
        }
        return (SystemHealthProto$SystemHealthMetric) builder.build();
    }
}
