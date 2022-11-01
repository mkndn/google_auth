package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FRDProductSpecificDataEntry extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new FRDProductSpecificDataEntryCreator();
    Boolean boolValue;
    byte[][] bytesValues;
    List dateTimeValues;
    List enumValueIdentifiers;
    int frdIdentifier;
    int frdType;
    List intValues;
    List stringValues;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FRDProductSpecificDataEntry(int i, int i2, List list, List list2, List list3, List list4, byte[][] bArr, boolean z) {
        this.frdIdentifier = i;
        this.frdType = i2;
        this.enumValueIdentifiers = list;
        this.intValues = list2;
        this.stringValues = list3;
        this.dateTimeValues = list4;
        this.bytesValues = bArr;
        this.boolValue = Boolean.valueOf(z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FRDProductSpecificDataEntry) {
            FRDProductSpecificDataEntry fRDProductSpecificDataEntry = (FRDProductSpecificDataEntry) obj;
            return this.frdIdentifier == fRDProductSpecificDataEntry.frdIdentifier && this.frdType == fRDProductSpecificDataEntry.frdType && Objects.equal(this.enumValueIdentifiers, fRDProductSpecificDataEntry.enumValueIdentifiers) && Objects.equal(this.intValues, fRDProductSpecificDataEntry.intValues) && Objects.equal(this.stringValues, fRDProductSpecificDataEntry.stringValues) && Objects.equal(this.dateTimeValues, fRDProductSpecificDataEntry.dateTimeValues) && Arrays.equals(this.bytesValues, fRDProductSpecificDataEntry.bytesValues) && Objects.equal(this.boolValue, fRDProductSpecificDataEntry.boolValue);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.frdIdentifier), Integer.valueOf(this.frdType), this.enumValueIdentifiers, this.intValues, this.stringValues, this.dateTimeValues, Integer.valueOf(Arrays.deepHashCode(this.bytesValues)), this.boolValue);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        FRDProductSpecificDataEntryCreator.writeToParcel(this, parcel, i);
    }
}
