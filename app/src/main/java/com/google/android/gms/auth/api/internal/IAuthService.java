package com.google.android.gms.auth.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IAuthService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IAuthService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements IAuthService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.auth.api.internal.IAuthService");
            }
        }

        public static IAuthService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
            if (queryLocalInterface instanceof IAuthService) {
                return (IAuthService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }
}
