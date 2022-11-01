package com.google.android.gms.clearcut.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BatchedLogErrorParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new BatchedLogErrorParcelableCreator();
    private final List logErrors;

    public BatchedLogErrorParcelable(List list) {
        this.logErrors = Collections.unmodifiableList(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BatchedLogErrorParcelable) {
            return this.logErrors.equals(((BatchedLogErrorParcelable) obj).logErrors);
        }
        return false;
    }

    public List getErrors() {
        return this.logErrors;
    }

    public int hashCode() {
        return Objects.hashCode(this.logErrors);
    }

    public String toString() {
        return "BatchedLogErrorParcelable[LogErrors: " + this.logErrors + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        BatchedLogErrorParcelableCreator.writeToParcel(this, parcel, i);
    }
}
