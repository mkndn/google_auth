package googledata.experiments.mobile.primes_android.features;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MetricTransmitterFeatureFlags {
    boolean disableClearcutLoggingInTests(Context context);

    boolean enableDelphiCollectionBasisLogVerifier(Context context);

    boolean usePackedHistogramEncodingInClearcut(Context context);
}
