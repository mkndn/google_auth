package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AppExitFeatureFlags {
    boolean appExitCollectionEnabled(Context context);

    ApplicationExitReasons appExitReasonsToReport(Context context);
}
