package com.google.android.gms.googlehelp.internal.common;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.InProductHelp;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGoogleHelpCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IGoogleHelpCallbacks {
        public Stub() {
            super("com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    enforceNoDataAvail(parcel);
                    onGoogleHelpProcessed((GoogleHelp) Codecs.createParcelable(parcel, GoogleHelp.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    enforceNoDataAvail(parcel);
                    onTogglingPipProcessed((TogglingData) Codecs.createParcelable(parcel, TogglingData.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    onPipShown();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    onPipClick();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    onPipInCallingAppHidden();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    onPipInCallingAppDisabled();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    onAsyncPsdSaved();
                    return true;
                case 8:
                    onAsyncPsbdSaved();
                    return true;
                case 9:
                    int readInt = parcel.readInt();
                    enforceNoDataAvail(parcel);
                    onChatSupportRequestSuccess(readInt);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    onChatSupportRequestFailed();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    onC2cSupportRequestSuccess();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    onC2cSupportRequestFailed();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    byte[] createByteArray = parcel.createByteArray();
                    enforceNoDataAvail(parcel);
                    onSuggestionsReceived(createByteArray);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    onSuggestionsRequestFailed();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    byte[] createByteArray2 = parcel.createByteArray();
                    enforceNoDataAvail(parcel);
                    onEscalationOptionsReceived(createByteArray2);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    onEscalationOptionsRequestFailed();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    enforceNoDataAvail(parcel);
                    onInProductHelpProcessed((InProductHelp) Codecs.createParcelable(parcel, InProductHelp.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    byte[] createByteArray3 = parcel.createByteArray();
                    enforceNoDataAvail(parcel);
                    onRealtimeSupportStatusSuccess(createByteArray3);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    onRealtimeSupportStatusRequestFailed();
                    parcel2.writeNoException();
                    return true;
                default:
                    return false;
            }
        }
    }

    void onAsyncPsbdSaved();

    void onAsyncPsdSaved();

    void onC2cSupportRequestFailed();

    void onC2cSupportRequestSuccess();

    void onChatSupportRequestFailed();

    void onChatSupportRequestSuccess(int i);

    void onEscalationOptionsReceived(byte[] bArr);

    void onEscalationOptionsRequestFailed();

    void onGoogleHelpProcessed(GoogleHelp googleHelp);

    void onInProductHelpProcessed(InProductHelp inProductHelp);

    void onPipClick();

    void onPipInCallingAppDisabled();

    void onPipInCallingAppHidden();

    void onPipShown();

    void onRealtimeSupportStatusRequestFailed();

    void onRealtimeSupportStatusSuccess(byte[] bArr);

    void onSuggestionsReceived(byte[] bArr);

    void onSuggestionsRequestFailed();

    void onTogglingPipProcessed(TogglingData togglingData);
}
