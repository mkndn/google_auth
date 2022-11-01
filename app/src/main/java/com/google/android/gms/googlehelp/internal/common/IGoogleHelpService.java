package com.google.android.gms.googlehelp.internal.common;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.googlehelp.GoogleHelp;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGoogleHelpService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IGoogleHelpService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements IGoogleHelpService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.googlehelp.internal.common.IGoogleHelpService");
            }

            @Override // com.google.android.gms.googlehelp.internal.common.IGoogleHelpService
            public void processGoogleHelpAndPip(GoogleHelp googleHelp, Bitmap bitmap, IGoogleHelpCallbacks iGoogleHelpCallbacks) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, googleHelp);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, bitmap);
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iGoogleHelpCallbacks);
                transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.googlehelp.internal.common.IGoogleHelpService
            public void saveAsyncFeedbackPsbd(FeedbackOptions feedbackOptions, Bundle bundle, long j, GoogleHelp googleHelp, IGoogleHelpCallbacks iGoogleHelpCallbacks) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, feedbackOptions);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, bundle);
                obtainAndWriteInterfaceToken.writeLong(j);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, googleHelp);
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iGoogleHelpCallbacks);
                transactOneway(10, obtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.googlehelp.internal.common.IGoogleHelpService
            public void saveAsyncFeedbackPsd(Bundle bundle, long j, GoogleHelp googleHelp, IGoogleHelpCallbacks iGoogleHelpCallbacks) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, bundle);
                obtainAndWriteInterfaceToken.writeLong(j);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, googleHelp);
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iGoogleHelpCallbacks);
                transactOneway(9, obtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.googlehelp.internal.common.IGoogleHelpService
            public void saveAsyncHelpPsd(Bundle bundle, long j, GoogleHelp googleHelp, IGoogleHelpCallbacks iGoogleHelpCallbacks) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, bundle);
                obtainAndWriteInterfaceToken.writeLong(j);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, googleHelp);
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iGoogleHelpCallbacks);
                transactOneway(8, obtainAndWriteInterfaceToken);
            }
        }

        public static IGoogleHelpService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.googlehelp.internal.common.IGoogleHelpService");
            if (queryLocalInterface instanceof IGoogleHelpService) {
                return (IGoogleHelpService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    void processGoogleHelpAndPip(GoogleHelp googleHelp, Bitmap bitmap, IGoogleHelpCallbacks iGoogleHelpCallbacks);

    void saveAsyncFeedbackPsbd(FeedbackOptions feedbackOptions, Bundle bundle, long j, GoogleHelp googleHelp, IGoogleHelpCallbacks iGoogleHelpCallbacks);

    void saveAsyncFeedbackPsd(Bundle bundle, long j, GoogleHelp googleHelp, IGoogleHelpCallbacks iGoogleHelpCallbacks);

    void saveAsyncHelpPsd(Bundle bundle, long j, GoogleHelp googleHelp, IGoogleHelpCallbacks iGoogleHelpCallbacks);
}
