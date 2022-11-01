package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new FeatureCreator();
    private final String name;
    @Deprecated
    private final int oldVersion;
    private final long version;

    public Feature(String str, int i, long j) {
        this.name = str;
        this.oldVersion = i;
        this.version = j;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            return ((getName() != null && getName().equals(feature.getName())) || (getName() == null && feature.getName() == null)) && getVersion() == feature.getVersion();
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public int getOldVersion() {
        return this.oldVersion;
    }

    public long getVersion() {
        long j = this.version;
        return j == -1 ? this.oldVersion : j;
    }

    public int hashCode() {
        return Objects.hashCode(getName(), Long.valueOf(getVersion()));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", getName()).add("version", Long.valueOf(getVersion())).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        FeatureCreator.writeToParcel(this, parcel, i);
    }

    public Feature(String str, long j) {
        this.name = str;
        this.version = j;
        this.oldVersion = -1;
    }
}
