package com.google.android.gms.fido.u2f.internal.privileged;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.fido.u2f.api.common.BrowserRegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.BrowserSignRequestParams;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IU2fPrivilegedService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IU2fPrivilegedService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements IU2fPrivilegedService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedService");
            }

            @Override // com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedService
            public void getRegisterIntent(IU2fPrivilegedCallbacks iU2fPrivilegedCallbacks, BrowserRegisterRequestParams browserRegisterRequestParams) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iU2fPrivilegedCallbacks);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, browserRegisterRequestParams);
                transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedService
            public void getSignIntent(IU2fPrivilegedCallbacks iU2fPrivilegedCallbacks, BrowserSignRequestParams browserSignRequestParams) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iU2fPrivilegedCallbacks);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, browserSignRequestParams);
                transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
            }
        }

        public static IU2fPrivilegedService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedService");
            if (queryLocalInterface instanceof IU2fPrivilegedService) {
                return (IU2fPrivilegedService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    void getRegisterIntent(IU2fPrivilegedCallbacks iU2fPrivilegedCallbacks, BrowserRegisterRequestParams browserRegisterRequestParams);

    void getSignIntent(IU2fPrivilegedCallbacks iU2fPrivilegedCallbacks, BrowserSignRequestParams browserSignRequestParams);
}
