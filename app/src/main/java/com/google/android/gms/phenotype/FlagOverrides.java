package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FlagOverrides extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new FlagOverridesCreator();
    public final List overrides;

    public FlagOverrides(List list) {
        this.overrides = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FlagOverrides) {
            return this.overrides.equals(((FlagOverrides) obj).overrides);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FlagOverrides(");
        boolean z = true;
        for (FlagOverride flagOverride : this.overrides) {
            if (!z) {
                sb.append(", ");
            }
            flagOverride.toString(sb);
            z = false;
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        FlagOverridesCreator.writeToParcel(this, parcel, i);
    }
}
