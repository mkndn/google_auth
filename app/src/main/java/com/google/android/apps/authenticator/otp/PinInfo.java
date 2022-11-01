package com.google.android.apps.authenticator.otp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PinInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.google.android.apps.authenticator.otp.PinInfo.1
        @Override // android.os.Parcelable.Creator
        public PinInfo createFromParcel(Parcel parcel) {
            return new PinInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public PinInfo[] newArray(int i) {
            return new PinInfo[i];
        }
    };
    private boolean isPinVisible;
    private boolean mHotpCodeGenerationAllowed;
    private final AccountDb.AccountIndex mIndex;
    private final boolean mIsHotp;
    private String mPin;

    public PinInfo(Parcel parcel) {
        this.mPin = null;
        this.mHotpCodeGenerationAllowed = false;
        this.isPinVisible = false;
        this.mPin = (String) parcel.readValue(PinInfo.class.getClassLoader());
        this.mIndex = (AccountDb.AccountIndex) parcel.readSerializable();
        boolean[] zArr = new boolean[2];
        parcel.readBooleanArray(zArr);
        this.mIsHotp = zArr[0];
        this.mHotpCodeGenerationAllowed = zArr[1];
    }

    public static void swapIndex(PinInfo[] pinInfoArr, int i, int i2) {
        PinInfo pinInfo = pinInfoArr[i];
        pinInfoArr[i] = pinInfoArr[i2];
        pinInfoArr[i2] = pinInfo;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PinInfo pinInfo = (PinInfo) obj;
        return Objects.equal(pinInfo.mIndex, this.mIndex) && pinInfo.mIsHotp == this.mIsHotp && Objects.equal(pinInfo.mPin, this.mPin) && pinInfo.mHotpCodeGenerationAllowed == this.mHotpCodeGenerationAllowed;
    }

    public AccountDb.AccountIndex getIndex() {
        return this.mIndex;
    }

    public String getPin() {
        return this.mPin;
    }

    public int hashCode() {
        return Objects.hashCode(this.mPin, this.mIndex, Boolean.valueOf(this.mIsHotp), Boolean.valueOf(this.mHotpCodeGenerationAllowed));
    }

    public boolean isHotp() {
        return this.mIsHotp;
    }

    public boolean isHotpCodeGenerationAllowed() {
        return this.mHotpCodeGenerationAllowed;
    }

    public boolean isPinVisible() {
        return this.isPinVisible;
    }

    public PinInfo setIsHotpCodeGenerationAllowed(boolean z) {
        this.mHotpCodeGenerationAllowed = z;
        return this;
    }

    public PinInfo setPin(String str) {
        this.mPin = str;
        return this;
    }

    public void setPinVisible(boolean z) {
        this.isPinVisible = z;
    }

    public String toString() {
        return String.format("PinInfo {mPin=%s, mIndex=%s, mIsHotp=%s, mHotpCodeGenerationAllowed=%s}", this.mPin, this.mIndex, Boolean.valueOf(this.mIsHotp), Boolean.valueOf(this.mHotpCodeGenerationAllowed));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.mPin);
        parcel.writeSerializable(this.mIndex);
        parcel.writeBooleanArray(new boolean[]{this.mIsHotp, this.mHotpCodeGenerationAllowed});
    }

    public PinInfo(AccountDb.AccountIndex accountIndex) {
        this(accountIndex, false);
    }

    public PinInfo(AccountDb.AccountIndex accountIndex, boolean z) {
        this.mPin = null;
        this.mHotpCodeGenerationAllowed = false;
        this.isPinVisible = false;
        this.mIndex = (AccountDb.AccountIndex) Preconditions.checkNotNull(accountIndex);
        this.mIsHotp = z;
    }
}
