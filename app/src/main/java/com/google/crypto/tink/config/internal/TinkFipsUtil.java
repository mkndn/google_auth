package com.google.crypto.tink.config.internal;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TinkFipsUtil {
    private static final Logger logger = Logger.getLogger(TinkFipsUtil.class.getName());
    private static final AtomicBoolean isRestrictedToFips = new AtomicBoolean(false);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class AlgorithmFipsCompatibility {
        public static final AlgorithmFipsCompatibility ALGORITHM_NOT_FIPS = new AnonymousClass1("ALGORITHM_NOT_FIPS", 0);
        public static final AlgorithmFipsCompatibility ALGORITHM_REQUIRES_BORINGCRYPTO = new AnonymousClass2("ALGORITHM_REQUIRES_BORINGCRYPTO", 1);
        private static final /* synthetic */ AlgorithmFipsCompatibility[] $VALUES = $values();

        /* compiled from: PG */
        /* renamed from: com.google.crypto.tink.config.internal.TinkFipsUtil$AlgorithmFipsCompatibility$1  reason: invalid class name */
        /* loaded from: classes.dex */
        enum AnonymousClass1 extends AlgorithmFipsCompatibility {
            private AnonymousClass1(String str, int i) {
                super(str, i);
            }

            @Override // com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility
            public boolean isCompatible() {
                return !TinkFipsUtil.useOnlyFips();
            }
        }

        /* compiled from: PG */
        /* renamed from: com.google.crypto.tink.config.internal.TinkFipsUtil$AlgorithmFipsCompatibility$2  reason: invalid class name */
        /* loaded from: classes.dex */
        enum AnonymousClass2 extends AlgorithmFipsCompatibility {
            private AnonymousClass2(String str, int i) {
                super(str, i);
            }

            @Override // com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility
            public boolean isCompatible() {
                return !TinkFipsUtil.useOnlyFips() || TinkFipsUtil.fipsModuleAvailable();
            }
        }

        private static /* synthetic */ AlgorithmFipsCompatibility[] $values() {
            return new AlgorithmFipsCompatibility[]{ALGORITHM_NOT_FIPS, ALGORITHM_REQUIRES_BORINGCRYPTO};
        }

        private AlgorithmFipsCompatibility(String str, int i) {
        }

        public static AlgorithmFipsCompatibility[] values() {
            return (AlgorithmFipsCompatibility[]) $VALUES.clone();
        }

        public abstract boolean isCompatible();
    }

    private TinkFipsUtil() {
    }

    static Boolean checkConscryptIsAvailableAndUsesFipsBoringSsl() {
        try {
            return (Boolean) Class.forName("org.conscrypt.Conscrypt").getMethod("isBoringSslFIPSBuild", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            logger.logp(Level.INFO, "com.google.crypto.tink.config.internal.TinkFipsUtil", "checkConscryptIsAvailableAndUsesFipsBoringSsl", "Conscrypt is not available or does not support checking for FIPS build.");
            return false;
        }
    }

    public static boolean fipsModuleAvailable() {
        return checkConscryptIsAvailableAndUsesFipsBoringSsl().booleanValue();
    }

    public static boolean useOnlyFips() {
        return TinkFipsStatus.useOnlyFips() || isRestrictedToFips.get();
    }
}
