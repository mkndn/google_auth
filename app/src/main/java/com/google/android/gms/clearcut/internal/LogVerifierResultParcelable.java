package com.google.android.gms.clearcut.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogVerifierResultParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new LogVerifierResultParcelableCreator();
    public boolean passed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogVerifierResultParcelable(boolean z) {
        this.passed = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LogVerifierResultParcelable) && this.passed == ((LogVerifierResultParcelable) obj).passed;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.passed));
    }

    public String toString() {
        return "LogVerifierResultParcelable[" + this.passed + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        LogVerifierResultParcelableCreator.writeToParcel(this, parcel, i);
    }
}
