package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MethodInvocation extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new MethodInvocationCreator();
    public static final int TASK_CANCELED_STATUS = 100;
    public static final int UNKNOWN_EXCEPTION_STATUS = 101;
    private final String callingEntryPoint;
    private final String callingModuleId;
    private final int connectionResultStatusCode;
    private final long endTimeMillis;
    private final int latencyMillis;
    private final int methodKey;
    private final int resultStatusCode;
    private final int serviceId;
    private final long startTimeMillis;

    public MethodInvocation(int i, int i2, int i3, long j, long j2, String str, String str2, int i4, int i5) {
        this.methodKey = i;
        this.resultStatusCode = i2;
        this.connectionResultStatusCode = i3;
        this.startTimeMillis = j;
        this.endTimeMillis = j2;
        this.callingModuleId = str;
        this.callingEntryPoint = str2;
        this.serviceId = i4;
        this.latencyMillis = i5;
    }

    public String getCallingEntryPoint() {
        return this.callingEntryPoint;
    }

    public String getCallingModuleId() {
        return this.callingModuleId;
    }

    public int getConnectionResultStatusCode() {
        return this.connectionResultStatusCode;
    }

    public long getEndTimeMillis() {
        return this.endTimeMillis;
    }

    public int getLatencyMillis() {
        return this.latencyMillis;
    }

    public int getMethodKey() {
        return this.methodKey;
    }

    public int getResultStatusCode() {
        return this.resultStatusCode;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public long getStartTimeMillis() {
        return this.startTimeMillis;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        MethodInvocationCreator.writeToParcel(this, parcel, i);
    }
}
