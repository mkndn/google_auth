package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import android.util.Base64;
import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashFeatureFlagsImpl implements CrashFeatureFlags {
    public static final ProcessStablePhenotypeFlag crashSamplingParameters;
    public static final ProcessStablePhenotypeFlag enableExceptionMessageLogging;
    public static final ProcessStablePhenotypeFlag enableLifeboat;
    public static final ProcessStablePhenotypeFlag enableSuppressedExceptionCollection;
    public static final ProcessStablePhenotypeFlag recordingTimeouts;

    static {
        ProcessStablePhenotypeFlagFactory directBootAware = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware();
        try {
            crashSamplingParameters = directBootAware.createFlagRestricted("14", SystemHealthProto$SamplingParameters.parseFrom(Base64.decode("EAAYAg", 3)), CrashFeatureFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
            enableExceptionMessageLogging = directBootAware.createFlagRestricted("37", true);
            enableLifeboat = directBootAware.createFlagRestricted("33", false);
            enableSuppressedExceptionCollection = directBootAware.createFlagRestricted("45362737", true);
            try {
                recordingTimeouts = directBootAware.createFlagRestricted("45371370", CrashRecordingTimeouts.parseFrom(Base64.decode("CAAQAA", 3)), CrashFeatureFlagsImpl$$ExternalSyntheticLambda1.INSTANCE);
            } catch (Exception e) {
                throw new AssertionError(e);
            }
        } catch (Exception e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // googledata.experiments.mobile.primes_android.features.CrashFeatureFlags
    public CrashRecordingTimeouts recordingTimeouts(Context context) {
        return (CrashRecordingTimeouts) recordingTimeouts.get(context);
    }
}
