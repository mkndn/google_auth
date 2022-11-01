package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ISignInService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements ISignInService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements ISignInService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
            }

            @Override // com.google.android.gms.auth.api.signin.internal.ISignInService
            public void revokeAccessFromGoogle(ISignInCallbacks iSignInCallbacks, GoogleSignInOptions googleSignInOptions) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iSignInCallbacks);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, googleSignInOptions);
                transactAndReadExceptionReturnVoid(103, obtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.auth.api.signin.internal.ISignInService
            public void signOutFromGoogle(ISignInCallbacks iSignInCallbacks, GoogleSignInOptions googleSignInOptions) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iSignInCallbacks);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, googleSignInOptions);
                transactAndReadExceptionReturnVoid(102, obtainAndWriteInterfaceToken);
            }
        }

        public static ISignInService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
            if (queryLocalInterface instanceof ISignInService) {
                return (ISignInService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    void revokeAccessFromGoogle(ISignInCallbacks iSignInCallbacks, GoogleSignInOptions googleSignInOptions);

    void signOutFromGoogle(ISignInCallbacks iSignInCallbacks, GoogleSignInOptions googleSignInOptions);
}
