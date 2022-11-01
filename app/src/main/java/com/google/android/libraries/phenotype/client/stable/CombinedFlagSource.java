package com.google.android.libraries.phenotype.client.stable;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.libraries.phenotype.client.HermeticFileOverrides;
import com.google.android.libraries.phenotype.client.HermeticFileOverridesReader;
import com.google.android.libraries.phenotype.client.PhenotypeConstants;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CombinedFlagSource implements FlagSource {
    private static volatile Optional fileOverrides = null;
    private final boolean accountScoped;
    private final boolean autoSubpackage;
    private final boolean canInvalidate;
    private final boolean directBootAware;
    private final Set logSourceNames;
    private final ProcessStablePhenotypeFlagFactory.Converter objectConverter;
    private final boolean stickyAccountSupport;
    private final ProcessStablePhenotypeFlagFactory.Converter stringConverter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CombinedFlagSource(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, Set set, ProcessStablePhenotypeFlagFactory.Converter converter, ProcessStablePhenotypeFlagFactory.Converter converter2) {
        this.stickyAccountSupport = z;
        this.autoSubpackage = z2;
        this.directBootAware = z3;
        this.canInvalidate = z4;
        this.accountScoped = z5;
        this.logSourceNames = set;
        this.stringConverter = converter;
        this.objectConverter = converter2;
    }

    private static Optional cachedFileOverrides(Context context) {
        Optional optional = fileOverrides;
        if (optional == null) {
            synchronized (CombinedFlagSource.class) {
                if (fileOverrides == null) {
                    fileOverrides = HermeticFileOverridesReader.CachingReader.readFromFileAndCacheIfEligible(context);
                }
                optional = fileOverrides;
            }
        }
        return optional;
    }

    private FlagStore getMobStoreFlagStore(PhenotypeContext phenotypeContext, String str, String str2) {
        return FlagStore.getRegistry().register(phenotypeContext, str, str2, this.stickyAccountSupport, this.directBootAware, this.canInvalidate, this.accountScoped, this.logSourceNames, CombinedFlagSource$$ExternalSyntheticLambda1.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void verifyDeclarativeRegistration(PhenotypeContext phenotypeContext, String str) {
        if (!BuildConstants.IS_PACKAGE_SIDE && Build.VERSION.SDK_INT >= 26 && !PackageInfo.getRegisteredPackages(phenotypeContext.getContext()).containsKey(str)) {
            Log.e("PhenotypeCombinedFlags", "Config package " + str + " cannot use PROCESS_STABLE backing without declarative registration. See go/phenotype-android-integration#phenotype for more information. This will lead to stale flags.");
        }
    }

    @Override // com.google.android.libraries.phenotype.client.stable.FlagSource
    public PackageVersionCache getVersionCache(PhenotypeContext phenotypeContext, String str, String str2) {
        if (PhenotypeContext.isTestMode()) {
            return new PackageVersionCache();
        }
        return getMobStoreFlagStore(phenotypeContext, str, str2).getPackageVersionCache();
    }

    private Object getFromFileOverrides(HermeticFileOverrides hermeticFileOverrides, String str, String str2) {
        String str3 = hermeticFileOverrides.get(PhenotypeConstants.getContentProviderUri(str), null, null, str2);
        if (str3 == null) {
            return null;
        }
        try {
            return this.stringConverter.convert(str3);
        } catch (IOException | IllegalArgumentException e) {
            Log.e("PhenotypeCombinedFlags", "Invalid Phenotype flag value for flag " + str2, e);
            return null;
        }
    }

    private Object getFromMobStore(PhenotypeContext phenotypeContext, String str, String str2, String str3) {
        Object flag = getMobStoreFlagStore(phenotypeContext, str, str2).getFlag(str3);
        if (flag == null) {
            return null;
        }
        try {
            return this.objectConverter.convert(flag);
        } catch (IOException | ClassCastException e) {
            Log.e("PhenotypeCombinedFlags", "Invalid Phenotype flag value for flag " + str3, e);
            return null;
        }
    }

    @Override // com.google.android.libraries.phenotype.client.stable.FlagSource
    public Object get(final PhenotypeContext phenotypeContext, String str, String str2, String str3) {
        final String str4;
        if (this.autoSubpackage) {
            str4 = PhenotypeConstants.getSubpackagedName(phenotypeContext.getContext(), str);
        } else {
            str4 = str;
        }
        if (this.directBootAware) {
            Preconditions.checkState(str2.equals(""), "DirectBoot aware package %s can not access account-scoped flags.", str4);
        }
        PhenotypeExecutor.crashOnFailure(phenotypeContext.getExecutor().submit(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.CombinedFlagSource$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CombinedFlagSource.verifyDeclarativeRegistration(PhenotypeContext.this, str4);
            }
        }));
        Object fromMobStore = getFromMobStore(phenotypeContext, str4, str2, str3);
        Optional cachedFileOverrides = cachedFileOverrides(phenotypeContext.getContext());
        return cachedFileOverrides.isPresent() ? getFromFileOverrides((HermeticFileOverrides) cachedFileOverrides.get(), str, str3) : fromMobStore;
    }
}
