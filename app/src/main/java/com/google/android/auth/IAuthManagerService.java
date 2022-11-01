package com.google.android.auth;

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
public interface IAuthManagerService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IAuthManagerService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements IAuthManagerService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.auth.IAuthManagerService");
            }

            @Override // com.google.android.auth.IAuthManagerService
            public Bundle getTokenByAccount(Account account, String str, Bundle bundle) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, account);
                obtainAndWriteInterfaceToken.writeString(str);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, bundle);
                Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken);
                Bundle bundle2 = (Bundle) Codecs.createParcelable(transactAndReadException, Bundle.CREATOR);
                transactAndReadException.recycle();
                return bundle2;
            }
        }

        public static IAuthManagerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
            if (queryLocalInterface instanceof IAuthManagerService) {
                return (IAuthManagerService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    Bundle getTokenByAccount(Account account, String str, Bundle bundle);
}
