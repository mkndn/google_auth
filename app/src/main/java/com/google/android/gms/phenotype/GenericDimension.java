package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GenericDimension extends AbstractSafeParcelable implements Comparable {
    public static final Parcelable.Creator CREATOR = new GenericDimensionCreator();
    public final int instance;
    public final int namespace;

    public GenericDimension(int i, int i2) {
        this.namespace = i;
        this.instance = i2;
    }

    @Override // java.lang.Comparable
    public int compareTo(GenericDimension genericDimension) {
        int i = this.namespace;
        int i2 = genericDimension.namespace;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = this.instance;
        int i4 = genericDimension.instance;
        if (i3 < i4) {
            return -1;
        }
        return i3 > i4 ? 1 : 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof GenericDimension) && compareTo((GenericDimension) obj) == 0;
    }

    public int hashCode() {
        return (this.namespace * 31) + this.instance;
    }

    public String toString() {
        return "GenericDimension(" + this.namespace + ", " + this.instance + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        GenericDimensionCreator.writeToParcel(this, parcel, i);
    }
}
