package logs.proto.wireless.performance.mobile;

import com.google.android.libraries.consentverifier.BaseProtoCollectionBasis;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthMetricCollectionBasisHelper$SystemHealthMetric extends BaseProtoCollectionBasis {
    private SystemHealthMetricCollectionBasisHelper$SystemHealthMetric(SystemHealthMetricCollectionBasisHelper$Features systemHealthMetricCollectionBasisHelper$Features) {
        super(systemHealthMetricCollectionBasisHelper$Features.getFeatureHash(), 2065731759, R$raw.logs_proto_wireless_performance_mobile_system_health_metric_collection_basis_library);
    }

    public static SystemHealthMetricCollectionBasisHelper$SystemHealthMetric forFeature(SystemHealthMetricCollectionBasisHelper$Features systemHealthMetricCollectionBasisHelper$Features) {
        return new SystemHealthMetricCollectionBasisHelper$SystemHealthMetric(systemHealthMetricCollectionBasisHelper$Features);
    }

    public static SystemHealthMetricCollectionBasisHelper$SystemHealthMetric getInstance() {
        return forFeature(SystemHealthMetricCollectionBasisHelper$Features.DEFAULT);
    }
}
