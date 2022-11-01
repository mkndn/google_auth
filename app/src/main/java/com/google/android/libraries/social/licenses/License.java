package com.google.android.libraries.social.licenses;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class License implements Comparable, Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.google.android.libraries.social.licenses.License.1
        @Override // android.os.Parcelable.Creator
        public License createFromParcel(Parcel parcel) {
            return new License(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public License[] newArray(int i) {
            return new License[i];
        }
    };
    private final String libraryName;
    private final int licenseLength;
    private final long licenseOffset;
    private final String path;

    private License(Parcel parcel) {
        this.libraryName = parcel.readString();
        this.licenseOffset = parcel.readLong();
        this.licenseLength = parcel.readInt();
        this.path = parcel.readString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static License create(String str, long j, int i, String str2) {
        return new License(str, j, i, str2);
    }

    @Override // java.lang.Comparable
    public int compareTo(License license) {
        return this.libraryName.compareTo(license.getLibraryName());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof License) {
            return getLibraryName().equals(((License) obj).getLibraryName());
        }
        return false;
    }

    public String getLibraryName() {
        return this.libraryName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLicenseLength() {
        return this.licenseLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getLicenseOffset() {
        return this.licenseOffset;
    }

    public String getPath() {
        return this.path;
    }

    public int hashCode() {
        return this.libraryName.hashCode();
    }

    public String toString() {
        return getLibraryName();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.libraryName);
        parcel.writeLong(this.licenseOffset);
        parcel.writeInt(this.licenseLength);
        parcel.writeString(this.path);
    }

    private License(String str, long j, int i, String str2) {
        this.libraryName = str;
        this.licenseOffset = j;
        this.licenseLength = i;
        this.path = str2;
    }
}
