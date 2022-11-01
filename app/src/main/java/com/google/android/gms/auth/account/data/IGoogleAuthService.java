package com.google.android.gms.auth.account.data;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGoogleAuthService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IGoogleAuthService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements IGoogleAuthService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.auth.account.data.IGoogleAuthService");
            }

            @Override // com.google.android.gms.auth.account.data.IGoogleAuthService
            public void getTokenWithDetails(IGetTokenWithDetailsCallback iGetTokenWithDetailsCallback, Account account, String str, Bundle bundle) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iGetTokenWithDetailsCallback);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, account);
                obtainAndWriteInterfaceToken.writeString(str);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, bundle);
                transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
            }
        }

        public static IGoogleAuthService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.account.data.IGoogleAuthService");
            if (queryLocalInterface instanceof IGoogleAuthService) {
                return (IGoogleAuthService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    void getTokenWithDetails(IGetTokenWithDetailsCallback iGetTokenWithDetailsCallback, Account account, String str, Bundle bundle);
}
