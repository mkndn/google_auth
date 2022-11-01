package com.google.android.libraries.phenotype.client.stable;

import android.util.Base64;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStablePhenotypeFlagFactory {
    private final boolean accountScoped;
    private final boolean autoSubpackage;
    private final boolean canInvalidate;
    private final String configPackage;
    private final boolean directBootAware;
    private final Set logSourceNames;
    private final boolean stickyAccountSupport;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Converter {
        Object convert(Object obj);
    }

    public ProcessStablePhenotypeFlagFactory(String str) {
        this(str, ImmutableSet.of(), false, false, false, false, false);
    }

    public static /* synthetic */ Object lambda$createFlagRestricted$10(Converter converter, String str) {
        return converter.convert(Base64.decode(str, 3));
    }

    public static /* synthetic */ Object lambda$createFlagRestricted$11(Converter converter, Object obj) {
        return converter.convert((byte[]) obj);
    }

    public static /* synthetic */ String lambda$createFlagRestricted$7(String str) {
        return str;
    }

    public ProcessStablePhenotypeFlagFactory autoSubpackage() {
        return new ProcessStablePhenotypeFlagFactory(this.configPackage, this.logSourceNames, this.stickyAccountSupport, true, this.directBootAware, this.canInvalidate, this.accountScoped);
    }

    public ProcessStablePhenotypeFlag createFlagRestricted(String str, long j) {
        String str2 = this.configPackage;
        Long valueOf = Long.valueOf(j);
        boolean z = this.stickyAccountSupport;
        boolean z2 = this.autoSubpackage;
        boolean z3 = this.directBootAware;
        boolean z4 = this.canInvalidate;
        boolean z5 = this.accountScoped;
        ImmutableSet copyOf = ImmutableSet.copyOf((Collection) this.logSourceNames);
        ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda8 processStablePhenotypeFlagFactory$$ExternalSyntheticLambda8 = ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda8.INSTANCE;
        Long.class.getClass();
        return new ProcessStablePhenotypeFlag(str2, str, valueOf, new CombinedFlagSource(z, z2, z3, z4, z5, copyOf, processStablePhenotypeFlagFactory$$ExternalSyntheticLambda8, new Converter() { // from class: com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda9
            @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
            public final Object convert(Object obj) {
                return (Long) r1.cast(obj);
            }
        }), true);
    }

    public ProcessStablePhenotypeFlagFactory directBootAware() {
        return new ProcessStablePhenotypeFlagFactory(this.configPackage, this.logSourceNames, this.stickyAccountSupport, this.autoSubpackage, true, this.canInvalidate, this.accountScoped);
    }

    public ProcessStablePhenotypeFlagFactory withLogSourceNames(List list) {
        return new ProcessStablePhenotypeFlagFactory(this.configPackage, ImmutableSet.copyOf((Collection) list), this.stickyAccountSupport, this.autoSubpackage, this.directBootAware, this.canInvalidate, this.accountScoped);
    }

    private ProcessStablePhenotypeFlagFactory(String str, Set set, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.configPackage = str;
        this.logSourceNames = set;
        this.stickyAccountSupport = z;
        this.autoSubpackage = z2;
        this.directBootAware = z3;
        this.canInvalidate = z4;
        this.accountScoped = z5;
    }

    public ProcessStablePhenotypeFlag createFlagRestricted(String str, Object obj, final Converter converter) {
        return new ProcessStablePhenotypeFlag(this.configPackage, str, obj, new CombinedFlagSource(this.stickyAccountSupport, this.autoSubpackage, this.directBootAware, this.canInvalidate, this.accountScoped, ImmutableSet.copyOf((Collection) this.logSourceNames), new Converter() { // from class: com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda10
            @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
            public final Object convert(Object obj2) {
                return ProcessStablePhenotypeFlagFactory.lambda$createFlagRestricted$10(ProcessStablePhenotypeFlagFactory.Converter.this, (String) obj2);
            }
        }, new Converter() { // from class: com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda11
            @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
            public final Object convert(Object obj2) {
                return ProcessStablePhenotypeFlagFactory.lambda$createFlagRestricted$11(ProcessStablePhenotypeFlagFactory.Converter.this, obj2);
            }
        }), true);
    }

    public ProcessStablePhenotypeFlag createFlagRestricted(String str, String str2) {
        String str3 = this.configPackage;
        boolean z = this.stickyAccountSupport;
        boolean z2 = this.autoSubpackage;
        boolean z3 = this.directBootAware;
        boolean z4 = this.canInvalidate;
        boolean z5 = this.accountScoped;
        ImmutableSet copyOf = ImmutableSet.copyOf((Collection) this.logSourceNames);
        ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18 processStablePhenotypeFlagFactory$$ExternalSyntheticLambda18 = ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18.INSTANCE;
        String.class.getClass();
        return new ProcessStablePhenotypeFlag(str3, str, str2, new CombinedFlagSource(z, z2, z3, z4, z5, copyOf, processStablePhenotypeFlagFactory$$ExternalSyntheticLambda18, new Converter() { // from class: com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda15
            @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
            public final Object convert(Object obj) {
                return (String) r1.cast(obj);
            }
        }), true);
    }

    public ProcessStablePhenotypeFlag createFlagRestricted(String str, boolean z) {
        String str2 = this.configPackage;
        Boolean valueOf = Boolean.valueOf(z);
        boolean z2 = this.stickyAccountSupport;
        boolean z3 = this.autoSubpackage;
        boolean z4 = this.directBootAware;
        boolean z5 = this.canInvalidate;
        boolean z6 = this.accountScoped;
        ImmutableSet copyOf = ImmutableSet.copyOf((Collection) this.logSourceNames);
        ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda2 processStablePhenotypeFlagFactory$$ExternalSyntheticLambda2 = ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda2.INSTANCE;
        Boolean.class.getClass();
        return new ProcessStablePhenotypeFlag(str2, str, valueOf, new CombinedFlagSource(z2, z3, z4, z5, z6, copyOf, processStablePhenotypeFlagFactory$$ExternalSyntheticLambda2, new Converter() { // from class: com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda3
            @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
            public final Object convert(Object obj) {
                return (Boolean) r1.cast(obj);
            }
        }), true);
    }
}
