package com.google.android.libraries.phenotype.client;

import android.content.Context;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.common.base.Supplier;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoValue_PhenotypeFlag_FlagsContext extends PhenotypeFlag.FlagsContext {
    private final Context context;
    private final Supplier hermeticFileOverrides;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_PhenotypeFlag_FlagsContext(Context context, Supplier supplier) {
        if (context == null) {
            throw new NullPointerException("Null context");
        }
        this.context = context;
        this.hermeticFileOverrides = supplier;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag.FlagsContext
    public Context context() {
        return this.context;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PhenotypeFlag.FlagsContext) {
            PhenotypeFlag.FlagsContext flagsContext = (PhenotypeFlag.FlagsContext) obj;
            if (this.context.equals(flagsContext.context())) {
                Supplier supplier = this.hermeticFileOverrides;
                if (supplier == null) {
                    if (flagsContext.hermeticFileOverrides() == null) {
                        return true;
                    }
                } else if (supplier.equals(flagsContext.hermeticFileOverrides())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag.FlagsContext
    public Supplier hermeticFileOverrides() {
        return this.hermeticFileOverrides;
    }

    public String toString() {
        String valueOf = String.valueOf(this.context);
        return "FlagsContext{context=" + valueOf + ", hermeticFileOverrides=" + String.valueOf(this.hermeticFileOverrides) + "}";
    }

    public int hashCode() {
        int hashCode = (this.context.hashCode() ^ 1000003) * 1000003;
        Supplier supplier = this.hermeticFileOverrides;
        return hashCode ^ (supplier == null ? 0 : supplier.hashCode());
    }
}
