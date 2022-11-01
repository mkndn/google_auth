package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Configuration extends AbstractSafeParcelable implements Comparable {
    public static final Parcelable.Creator CREATOR = new ConfigurationCreator();
    public final String[] deleteFlags;
    public final Map flagMap = new TreeMap();
    public final int flagType;
    public final Flag[] flags;

    public Configuration(int i, Flag[] flagArr, String[] strArr) {
        this.flagType = i;
        this.flags = flagArr;
        for (Flag flag : flagArr) {
            this.flagMap.put(flag.name, flag);
        }
        this.deleteFlags = strArr;
        if (strArr != null) {
            Arrays.sort(strArr);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Configuration configuration) {
        return this.flagType - configuration.flagType;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Configuration) {
            Configuration configuration = (Configuration) obj;
            return this.flagType == configuration.flagType && PhenotypeCore.equals(this.flagMap, configuration.flagMap) && Arrays.equals(this.deleteFlags, configuration.deleteFlags);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.flagType);
        sb.append(", ");
        sb.append("(");
        for (Flag flag : this.flagMap.values()) {
            sb.append(flag);
            sb.append(", ");
        }
        sb.append(")");
        sb.append(", ");
        sb.append("(");
        String[] strArr = this.deleteFlags;
        if (strArr != null) {
            for (String str : strArr) {
                sb.append(str);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ConfigurationCreator.writeToParcel(this, parcel, i);
    }
}
