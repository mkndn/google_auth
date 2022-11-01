package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    private final ConnectionResult mConnectionResult;
    private final PendingIntent mPendingIntent;
    private final int mStatusCode;
    private final String mStatusMessage;
    final int mVersionCode;
    public static final Status RESULT_SUCCESS_CACHE = new Status(-1);
    public static final Status RESULT_SUCCESS = new Status(0);
    public static final Status RESULT_INTERRUPTED = new Status(14);
    public static final Status RESULT_INTERNAL_ERROR = new Status(8);
    public static final Status RESULT_TIMEOUT = new Status(15);
    public static final Status RESULT_CANCELED = new Status(16);
    public static final Status RESULT_API_NOT_CONNECTED = new Status(17);
    public static final Status RESULT_DEAD_CLIENT = new Status(18);
    public static final Parcelable.Creator CREATOR = new StatusCreator();

    public Status(int i) {
        this(i, (String) null);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Status) {
            Status status = (Status) obj;
            return this.mVersionCode == status.mVersionCode && this.mStatusCode == status.mStatusCode && Objects.equal(this.mStatusMessage, status.mStatusMessage) && Objects.equal(this.mPendingIntent, status.mPendingIntent) && Objects.equal(this.mConnectionResult, status.mConnectionResult);
        }
        return false;
    }

    public ConnectionResult getConnectionResult() {
        return this.mConnectionResult;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PendingIntent getPendingIntent() {
        return this.mPendingIntent;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String getStatusMessage() {
        return this.mStatusMessage;
    }

    public String getStatusString() {
        String str = this.mStatusMessage;
        if (str != null) {
            return str;
        }
        return CommonStatusCodes.getStatusCodeString(this.mStatusCode);
    }

    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.mStatusCode), this.mStatusMessage, this.mPendingIntent, this.mConnectionResult);
    }

    public boolean isSuccess() {
        return this.mStatusCode <= 0;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("statusCode", getStatusString()).add("resolution", this.mPendingIntent).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        StatusCreator.writeToParcel(this, parcel, i);
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this(i, i2, str, pendingIntent, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Status(int i, int i2, String str, PendingIntent pendingIntent, ConnectionResult connectionResult) {
        this.mVersionCode = i;
        this.mStatusCode = i2;
        this.mStatusMessage = str;
        this.mPendingIntent = pendingIntent;
        this.mConnectionResult = connectionResult;
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    public Status(ConnectionResult connectionResult, String str) {
        this(connectionResult, str, 17);
    }

    @Deprecated
    public Status(ConnectionResult connectionResult, String str, int i) {
        this(1, i, str, connectionResult.getResolution(), connectionResult);
    }
}
