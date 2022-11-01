package com.google.android.libraries.phenotype.client;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.google.android.gsf.Gservices;
import com.google.android.libraries.phenotype.client.HermeticFileOverridesReader;
import com.google.android.libraries.phenotype.client.lockdown.FlagExemptionsReader;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PhenotypeFlag {
    private volatile Object cachedValue;
    private volatile int cachedVersion;
    private final boolean codegenFlag;
    private final Object defaultValue;
    final Factory factory;
    final String name;
    private static final Object setContextLock = new Object();
    private static volatile FlagsContext flagsContext = null;
    private static volatile boolean testMode = false;
    private static final AtomicReference overriddenFlags = new AtomicReference();
    private static FlagExemptionsReader exemptionsReader = new FlagExemptionsReader(PhenotypeFlag$$ExternalSyntheticLambda3.INSTANCE);
    private static final AtomicInteger globalVersion = new AtomicInteger();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BytesConverter {
        Object fromBytes(byte[] bArr);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Factory {
        final Function allowGservicesFn;
        final boolean autoSubpackage;
        final Uri contentProviderUri;
        final boolean disableBypassPhenotypeForDebug;
        final String gservicesPrefix;
        final String phenotypePrefix;
        final boolean preferGservices;
        final String sharedPrefsName;
        final boolean skipGservices;

        public Factory(Uri uri) {
            this(null, uri, "", "", false, false, false, false, null);
        }

        @Deprecated
        public PhenotypeFlag createFlag(String str, Object obj, BytesConverter bytesConverter) {
            return PhenotypeFlag.value(this, str, obj, bytesConverter, false);
        }

        public PhenotypeFlag createFlagRestricted(String str, double d) {
            return PhenotypeFlag.value(this, str, Double.valueOf(d), true);
        }

        public Factory disableBypassPhenotypeForDebug() {
            return new Factory(this.sharedPrefsName, this.contentProviderUri, this.gservicesPrefix, this.phenotypePrefix, this.skipGservices, this.preferGservices, true, this.autoSubpackage, this.allowGservicesFn);
        }

        public Factory skipGservices() {
            if (!this.gservicesPrefix.isEmpty()) {
                throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
            }
            Function function = this.allowGservicesFn;
            if (function != null) {
                throw new IllegalStateException("Cannot skip gservices both always and conditionally");
            }
            return new Factory(this.sharedPrefsName, this.contentProviderUri, this.gservicesPrefix, this.phenotypePrefix, true, this.preferGservices, this.disableBypassPhenotypeForDebug, this.autoSubpackage, function);
        }

        public Factory withGservicePrefix(String str) {
            boolean z = this.skipGservices;
            if (z) {
                throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
            }
            return new Factory(this.sharedPrefsName, this.contentProviderUri, str, this.phenotypePrefix, z, this.preferGservices, this.disableBypassPhenotypeForDebug, this.autoSubpackage, this.allowGservicesFn);
        }

        public Factory withPhenotypePrefix(String str) {
            return new Factory(this.sharedPrefsName, this.contentProviderUri, this.gservicesPrefix, str, this.skipGservices, this.preferGservices, this.disableBypassPhenotypeForDebug, this.autoSubpackage, this.allowGservicesFn);
        }

        public PhenotypeFlag createFlagRestricted(String str, long j) {
            return PhenotypeFlag.value(this, str, Long.valueOf(j), true);
        }

        private Factory(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, Function function) {
            this.sharedPrefsName = str;
            this.contentProviderUri = uri;
            this.gservicesPrefix = str2;
            this.phenotypePrefix = str3;
            this.skipGservices = z;
            this.preferGservices = z2;
            this.disableBypassPhenotypeForDebug = z3;
            this.autoSubpackage = z4;
            this.allowGservicesFn = function;
        }

        public PhenotypeFlag createFlagRestricted(String str, Object obj, BytesConverter bytesConverter) {
            return PhenotypeFlag.value(this, str, obj, bytesConverter, true);
        }

        public PhenotypeFlag createFlagRestricted(String str, boolean z) {
            return PhenotypeFlag.value(this, str, Boolean.valueOf(z), true);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class FlagsContext {
        static FlagsContext create(Context context, Supplier supplier) {
            return new AutoValue_PhenotypeFlag_FlagsContext(context, supplier);
        }

        public abstract Context context();

        public abstract Supplier hermeticFileOverrides();
    }

    private PhenotypeFlag(Factory factory, String str, Object obj, boolean z) {
        this.cachedVersion = -1;
        if (factory.sharedPrefsName == null && factory.contentProviderUri == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (factory.sharedPrefsName != null && factory.contentProviderUri != null) {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        this.factory = factory;
        this.name = str;
        this.defaultValue = obj;
        this.codegenFlag = z;
    }

    private Object getFromBackingSource(FlagsContext flagsContext2) {
        if (this.factory.preferGservices) {
            Object loadValueFromGservices = loadValueFromGservices(flagsContext2);
            if (loadValueFromGservices != null) {
                return loadValueFromGservices;
            }
            Object loadValueFromPhenotype = loadValueFromPhenotype(flagsContext2);
            if (loadValueFromPhenotype != null) {
                return loadValueFromPhenotype;
            }
        } else {
            Object loadValueFromPhenotype2 = loadValueFromPhenotype(flagsContext2);
            if (loadValueFromPhenotype2 != null) {
                return loadValueFromPhenotype2;
            }
            Object loadValueFromGservices2 = loadValueFromGservices(flagsContext2);
            if (loadValueFromGservices2 != null) {
                return loadValueFromGservices2;
            }
        }
        return this.defaultValue;
    }

    private Object getInternal() {
        FlagsContext flagsContext2 = flagsContext;
        if (testMode) {
            return this.defaultValue;
        }
        Preconditions.checkState(flagsContext2 != null, "Must call PhenotypeFlag.init() first");
        Object fromBackingSource = getFromBackingSource(flagsContext2);
        Optional optional = (Optional) flagsContext2.hermeticFileOverrides().get();
        if (optional.isPresent()) {
            String str = ((HermeticFileOverrides) optional.get()).get(this.factory.contentProviderUri, this.factory.sharedPrefsName, this.factory.phenotypePrefix, this.name);
            return str == null ? this.defaultValue : convertValue(str);
        }
        return fromBackingSource;
    }

    private String getPrefixedName(String str) {
        if (str == null || !str.isEmpty()) {
            return str + this.name;
        }
        return this.name;
    }

    @Deprecated
    public static void init(final Context context) {
        if (testMode) {
            return;
        }
        synchronized (setContextLock) {
            FlagsContext flagsContext2 = flagsContext;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (flagsContext2 == null || flagsContext2.context() != context) {
                ConfigurationContentLoader.clearLoaderMap();
                SharedPreferencesLoader.clearLoaderMap();
                GservicesLoader.clearLoader();
                flagsContext = FlagsContext.create(context, Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.phenotype.client.PhenotypeFlag$$ExternalSyntheticLambda2
                    @Override // com.google.common.base.Supplier
                    public final Object get() {
                        return PhenotypeFlag.lambda$init$1(context);
                    }
                }));
                invalidateProcessCache();
            }
        }
    }

    public static void invalidateProcessCache() {
        globalVersion.incrementAndGet();
    }

    public static /* synthetic */ Optional lambda$init$1(Context context) {
        return HermeticFileOverridesReader.CachingReader.readFromFileAndCacheIfEligible(context);
    }

    public static /* synthetic */ boolean lambda$static$0() {
        return true;
    }

    private Object loadValueFromGservices(FlagsContext flagsContext2) {
        Object flag;
        if (this.factory.skipGservices) {
            return null;
        }
        if ((this.factory.allowGservicesFn == null || ((Boolean) this.factory.allowGservicesFn.apply(flagsContext2.context())).booleanValue()) && (flag = GservicesLoader.getLoader(flagsContext2.context()).getFlag(getGservicesFlagName())) != null) {
            return convertValue(flag);
        }
        return null;
    }

    private Object loadValueFromPhenotype(FlagsContext flagsContext2) {
        FlagLoader loader;
        Object flag;
        if (!shouldBypassPhenotypeForDebug(flagsContext2)) {
            if (this.factory.contentProviderUri != null) {
                if (PhenotypeClientHelper.validateContentProvider(flagsContext2.context(), this.factory.contentProviderUri)) {
                    if (this.factory.autoSubpackage) {
                        loader = ConfigurationContentLoader.getLoader(flagsContext2.context().getContentResolver(), PhenotypeConstants.getContentProviderUri(PhenotypeConstants.getSubpackagedName(flagsContext2.context(), this.factory.contentProviderUri.getLastPathSegment())), PhenotypeFlag$$ExternalSyntheticLambda1.INSTANCE);
                    } else {
                        loader = ConfigurationContentLoader.getLoader(flagsContext2.context().getContentResolver(), this.factory.contentProviderUri, PhenotypeFlag$$ExternalSyntheticLambda1.INSTANCE);
                    }
                } else {
                    loader = null;
                }
            } else {
                loader = SharedPreferencesLoader.getLoader(flagsContext2.context(), this.factory.sharedPrefsName, PhenotypeFlag$$ExternalSyntheticLambda1.INSTANCE);
            }
            if (loader != null && (flag = loader.getFlag(getMendelFlagName())) != null) {
                return convertValue(flag);
            }
        } else if (Log.isLoggable("PhenotypeFlag", 3)) {
            Log.d("PhenotypeFlag", "Bypass reading Phenotype values for flag: " + getMendelFlagName());
        }
        return null;
    }

    public static void maybeInit(Context context) {
        if (flagsContext == null && !testMode) {
            synchronized (setContextLock) {
                if (flagsContext == null && !testMode) {
                    init(context);
                }
            }
        }
    }

    private boolean shouldBypassPhenotypeForDebug(FlagsContext flagsContext2) {
        return !this.factory.disableBypassPhenotypeForDebug && GservicesLoader.getLoader(flagsContext2.context()).getBooleanFlagOrFalse("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
    }

    private void validateFlagAccess() {
        if (!this.codegenFlag) {
            Preconditions.checkState(exemptionsReader.isFlagExempted(this.name), "Attempt to access PhenotypeFlag not via codegen. All new PhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error.");
        }
    }

    public static PhenotypeFlag value(Factory factory, String str, Boolean bool, boolean z) {
        return new PhenotypeFlag(factory, str, bool, z) { // from class: com.google.android.libraries.phenotype.client.PhenotypeFlag.3
            @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag
            public Boolean convertValue(Object obj) {
                if (obj instanceof Boolean) {
                    return (Boolean) obj;
                }
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (Gservices.TRUE_PATTERN.matcher(str2).matches()) {
                        return true;
                    }
                    if (Gservices.FALSE_PATTERN.matcher(str2).matches()) {
                        return false;
                    }
                }
                String mendelFlagName = super.getMendelFlagName();
                Log.e("PhenotypeFlag", "Invalid boolean value for " + mendelFlagName + ": " + String.valueOf(obj));
                return null;
            }
        };
    }

    abstract Object convertValue(Object obj);

    public Object get() {
        validateFlagAccess();
        int i = globalVersion.get();
        if (this.cachedVersion < i) {
            synchronized (this) {
                if (this.cachedVersion < i) {
                    this.cachedValue = getInternal();
                    this.cachedVersion = i;
                }
            }
        }
        return this.cachedValue;
    }

    public String getGservicesFlagName() {
        if (this.factory.skipGservices) {
            return null;
        }
        return getPrefixedName(this.factory.gservicesPrefix);
    }

    public String getMendelFlagName() {
        return getPrefixedName(this.factory.phenotypePrefix);
    }

    public static PhenotypeFlag value(Factory factory, String str, Double d, boolean z) {
        return new PhenotypeFlag(factory, str, d, z) { // from class: com.google.android.libraries.phenotype.client.PhenotypeFlag.4
            @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag
            public Double convertValue(Object obj) {
                if (obj instanceof Double) {
                    return (Double) obj;
                }
                if (obj instanceof Float) {
                    return Double.valueOf(((Float) obj).doubleValue());
                }
                if (obj instanceof String) {
                    try {
                        return Double.valueOf(Double.parseDouble((String) obj));
                    } catch (NumberFormatException e) {
                    }
                }
                String mendelFlagName = super.getMendelFlagName();
                Log.e("PhenotypeFlag", "Invalid double value for " + mendelFlagName + ": " + String.valueOf(obj));
                return null;
            }
        };
    }

    public static PhenotypeFlag value(Factory factory, String str, Long l, boolean z) {
        return new PhenotypeFlag(factory, str, l, z) { // from class: com.google.android.libraries.phenotype.client.PhenotypeFlag.1
            @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag
            public Long convertValue(Object obj) {
                if (obj instanceof Long) {
                    return (Long) obj;
                }
                if (obj instanceof String) {
                    try {
                        return Long.valueOf(Long.parseLong((String) obj));
                    } catch (NumberFormatException e) {
                    }
                }
                String mendelFlagName = super.getMendelFlagName();
                Log.e("PhenotypeFlag", "Invalid long value for " + mendelFlagName + ": " + String.valueOf(obj));
                return null;
            }
        };
    }

    public static PhenotypeFlag value(Factory factory, String str, Object obj, final BytesConverter bytesConverter, boolean z) {
        return new PhenotypeFlag(factory, str, obj, z) { // from class: com.google.android.libraries.phenotype.client.PhenotypeFlag.8
            @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag
            Object convertValue(Object obj2) {
                if (obj2 instanceof String) {
                    try {
                        return bytesConverter.fromBytes(Base64.decode((String) obj2, 3));
                    } catch (IOException e) {
                    } catch (IllegalArgumentException e2) {
                    }
                }
                String mendelFlagName = super.getMendelFlagName();
                Log.e("PhenotypeFlag", "Invalid byte[] value for " + mendelFlagName + ": " + String.valueOf(obj2));
                return null;
            }
        };
    }
}
