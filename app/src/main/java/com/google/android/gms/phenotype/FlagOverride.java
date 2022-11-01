package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FlagOverride extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new FlagOverrideCreator();
    public final boolean committed;
    public final String configurationName;
    public final Flag flag;
    public final String userName;

    public FlagOverride(String str, String str2, Flag flag, boolean z) {
        this.configurationName = str;
        this.userName = str2;
        this.flag = flag;
        this.committed = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FlagOverride) {
            FlagOverride flagOverride = (FlagOverride) obj;
            return PhenotypeCore.equals(this.configurationName, flagOverride.configurationName) && PhenotypeCore.equals(this.userName, flagOverride.userName) && PhenotypeCore.equals(this.flag, flagOverride.flag) && this.committed == flagOverride.committed;
        }
        return false;
    }

    public String toString() {
        return toString(new StringBuilder());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        FlagOverrideCreator.writeToParcel(this, parcel, i);
    }

    public String toString(StringBuilder sb) {
        sb.append("FlagOverride(");
        sb.append(this.configurationName);
        sb.append(", ");
        sb.append(this.userName);
        sb.append(", ");
        this.flag.toString(sb);
        sb.append(", ");
        sb.append(this.committed);
        sb.append(")");
        return sb.toString();
    }
}
