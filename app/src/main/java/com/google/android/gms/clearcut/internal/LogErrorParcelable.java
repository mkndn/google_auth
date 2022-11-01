package com.google.android.gms.clearcut.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LogErrorParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new LogErrorParcelableCreator();
    public static final int ERROR_OVERFLOW = 1002;
    @Deprecated
    public static final int EVENTS_QUEUED = 1004;
    @Deprecated
    public static final int EVENTS_QUEUED_AND_SENT = 1005;
    public static final int INTERNAL_ERROR = 1000;
    public static final int LOG_EVENT_UNCHECKED_EXCEPTION = 1003;
    public final int clearcutStatusCode;
    public int errorCount;
    public final String logSourceName;

    public LogErrorParcelable(String str, int i, int i2) {
        this.logSourceName = str;
        this.clearcutStatusCode = i;
        this.errorCount = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogErrorParcelable) {
            LogErrorParcelable logErrorParcelable = (LogErrorParcelable) obj;
            return LogErrorParcelable$$ExternalSyntheticBackport0.m(this.logSourceName, logErrorParcelable.logSourceName) && this.clearcutStatusCode == logErrorParcelable.clearcutStatusCode && this.errorCount == logErrorParcelable.errorCount;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.logSourceName, Integer.valueOf(this.clearcutStatusCode), Integer.valueOf(this.errorCount));
    }

    public String toString() {
        return "LogErrorParcelable[LogSourceName: " + this.logSourceName + ", ClearcutStatusCode: " + this.clearcutStatusCode + ", ErrorCount: " + this.errorCount + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        LogErrorParcelableCreator.writeToParcel(this, parcel, i);
    }
}
