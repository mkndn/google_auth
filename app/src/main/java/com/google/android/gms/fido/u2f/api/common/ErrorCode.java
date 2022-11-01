package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public enum ErrorCode implements Parcelable {
    OK(0),
    OTHER_ERROR(1),
    BAD_REQUEST(2),
    CONFIGURATION_UNSUPPORTED(3),
    DEVICE_INELIGIBLE(4),
    TIMEOUT(5);
    
    private final int code;
    private static final String TAG = ErrorCode.class.getSimpleName();
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.google.android.gms.fido.u2f.api.common.ErrorCode.1
        @Override // android.os.Parcelable.Creator
        public ErrorCode createFromParcel(Parcel parcel) {
            return ErrorCode.toErrorCode(parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public ErrorCode[] newArray(int i) {
            return new ErrorCode[i];
        }
    };

    ErrorCode(int i) {
        this.code = i;
    }

    public static ErrorCode toErrorCode(int i) {
        ErrorCode[] values;
        for (ErrorCode errorCode : values()) {
            if (i == errorCode.code) {
                return errorCode;
            }
        }
        return OTHER_ERROR;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.code;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
    }
}
