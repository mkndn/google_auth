package com.google.android.libraries.phenotype.client.stable;

import android.content.Context;
import androidx.collection.SimpleArrayMap;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.lockdown.FlagExemptionsReader;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStablePhenotypeFlag implements Supplier {
    private static volatile FlagExemptionsReader exemptionsReader = new FlagExemptionsReader(ProcessStablePhenotypeFlag$$ExternalSyntheticLambda0.INSTANCE);
    private volatile Object cachedValueForLoggedOutUser;
    private SimpleArrayMap cachedValuesByAccountName;
    private final boolean codegenFlag;
    private final String configurationPackageName;
    private final Object defaultValue;
    private final String flagName;
    private Object overrideValue;
    private volatile PackageVersionCache packageVersionCache;
    private SimpleArrayMap packageVersionCachesByAccountName;
    private final FlagSource source;
    private SimpleArrayMap versionByAccountName;
    private volatile int versionForLoggedOutUser = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProcessStablePhenotypeFlag(String str, String str2, Object obj, FlagSource flagSource, boolean z) {
        Preconditions.checkNotNull(obj);
        this.configurationPackageName = str;
        this.flagName = str2;
        this.defaultValue = obj;
        this.source = flagSource;
        this.codegenFlag = z;
    }

    private Object getNonCached(PhenotypeContext phenotypeContext, String str) {
        validateFlagAccess();
        if (PhenotypeContext.isTestMode()) {
            return this.defaultValue;
        }
        Object obj = this.source.get(phenotypeContext, this.configurationPackageName, str, this.flagName);
        return obj != null ? obj : this.defaultValue;
    }

    private Object getWithPhenotypeContext(PhenotypeContext phenotypeContext, String str) {
        Object obj = this.overrideValue;
        if (obj != null) {
            return obj;
        }
        if ("".equals(str)) {
            int i = this.versionForLoggedOutUser;
            Object obj2 = this.cachedValueForLoggedOutUser;
            if (this.packageVersionCache == null || i < this.packageVersionCache.get() || obj2 == null) {
                synchronized (this) {
                    if (this.packageVersionCache == null) {
                        this.packageVersionCache = this.source.getVersionCache(phenotypeContext, this.configurationPackageName, str);
                    }
                    if (this.versionForLoggedOutUser < this.packageVersionCache.get()) {
                        this.versionForLoggedOutUser = this.packageVersionCache.get();
                        this.cachedValueForLoggedOutUser = getNonCached(phenotypeContext, str);
                    }
                    obj2 = this.cachedValueForLoggedOutUser;
                }
            }
            return obj2;
        }
        synchronized (this) {
            boolean z = true;
            if (this.packageVersionCachesByAccountName == null) {
                Preconditions.checkState(this.cachedValuesByAccountName == null);
                Preconditions.checkState(this.versionByAccountName == null);
                this.packageVersionCachesByAccountName = new SimpleArrayMap();
                this.cachedValuesByAccountName = new SimpleArrayMap();
                this.versionByAccountName = new SimpleArrayMap();
            }
            PackageVersionCache packageVersionCache = (PackageVersionCache) this.packageVersionCachesByAccountName.get(str);
            if (packageVersionCache != null && packageVersionCache.get() <= ((Integer) this.versionByAccountName.getOrDefault(str, -1)).intValue()) {
                Object obj3 = this.cachedValuesByAccountName.get(str);
                Preconditions.checkNotNull(obj3, "Cached flag value should not be null if its version is up to date.");
                return obj3;
            }
            PackageVersionCache versionCache = this.source.getVersionCache(phenotypeContext, this.configurationPackageName, str);
            PackageVersionCache packageVersionCache2 = (PackageVersionCache) this.packageVersionCachesByAccountName.put(str, versionCache);
            if (packageVersionCache2 != null && packageVersionCache2 != versionCache) {
                z = false;
            }
            Preconditions.checkState(z, "PackageVersionCache object should not change in the life of the process.");
            this.versionByAccountName.put(str, Integer.valueOf(versionCache.get()));
            Object nonCached = getNonCached(phenotypeContext, str);
            Preconditions.checkNotNull(nonCached, "Expected user-scoped %s to not be null.", this.flagName);
            this.cachedValuesByAccountName.put(str, nonCached);
            return nonCached;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0() {
        return true;
    }

    private void validateFlagAccess() {
        if (!this.codegenFlag) {
            Preconditions.checkState(exemptionsReader.isFlagExempted(this.configurationPackageName, this.flagName), "Attempt to access ProcessStablePhenotypeFlag not via codegen. All new ProcessStablePhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error.");
        }
    }

    @Override // com.google.common.base.Supplier
    public Object get() {
        return getWithPhenotypeContext(PhenotypeContext.get(), "");
    }

    public Object get(Context context) {
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        return getWithPhenotypeContext(PhenotypeContext.getPhenotypeContextFrom(applicationContext), "");
    }
}
