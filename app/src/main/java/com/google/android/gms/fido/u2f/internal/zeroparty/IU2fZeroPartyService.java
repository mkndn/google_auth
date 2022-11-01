package com.google.android.gms.fido.u2f.internal.zeroparty;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IU2fZeroPartyService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IU2fZeroPartyService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements IU2fZeroPartyService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.fido.u2f.internal.zeroparty.IU2fZeroPartyService");
            }
        }

        public static IU2fZeroPartyService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fido.u2f.internal.zeroparty.IU2fZeroPartyService");
            if (queryLocalInterface instanceof IU2fZeroPartyService) {
                return (IU2fZeroPartyService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }
}
