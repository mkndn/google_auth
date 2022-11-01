package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SourceStatsEntity extends AbstractSafeParcelable implements SourceStats {
    public static final Parcelable.Creator CREATOR = new SourceStatsCreator();
    private final Integer mNumContacts;
    private final Integer mNumRestorableContacts;
    private final String mSource;

    public SourceStatsEntity(String str, Integer num, Integer num2) {
        this.mSource = str;
        this.mNumContacts = num;
        this.mNumRestorableContacts = num2;
    }

    public static boolean equals(SourceStats sourceStats, SourceStats sourceStats2) {
        return Objects.equal(sourceStats.getSource(), sourceStats2.getSource()) && Objects.equal(sourceStats.getNumContacts(), sourceStats2.getNumContacts()) && Objects.equal(sourceStats.getNumRestorableContacts(), sourceStats2.getNumRestorableContacts());
    }

    @Override // com.google.android.gms.people.protomodel.SourceStats
    public Integer getNumContacts() {
        return this.mNumContacts;
    }

    @Override // com.google.android.gms.people.protomodel.SourceStats
    public Integer getNumRestorableContacts() {
        return this.mNumRestorableContacts;
    }

    @Override // com.google.android.gms.people.protomodel.SourceStats
    public String getSource() {
        return this.mSource;
    }

    public int hashCode() {
        return hashCode(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        SourceStatsCreator.writeToParcel(this, parcel, i);
    }

    public static int hashCode(SourceStats sourceStats) {
        return Objects.hashCode(sourceStats.getSource(), sourceStats.getNumContacts(), sourceStats.getNumRestorableContacts());
    }

    public boolean equals(Object obj) {
        if (obj instanceof SourceStats) {
            if (this == obj) {
                return true;
            }
            return equals(this, (SourceStats) obj);
        }
        return false;
    }
}
