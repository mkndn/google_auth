package com.google.android.gms.clearcut.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.clearcut.LogEventParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IClearcutLoggerService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IClearcutLoggerService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements IClearcutLoggerService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
            }

            @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerService
            public void logError(IClearcutLoggerCallbacks iClearcutLoggerCallbacks, BatchedLogErrorParcelable batchedLogErrorParcelable) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iClearcutLoggerCallbacks);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, batchedLogErrorParcelable);
                transactOneway(8, obtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerService
            public void logEvent(IClearcutLoggerCallbacks iClearcutLoggerCallbacks, LogEventParcelable logEventParcelable) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iClearcutLoggerCallbacks);
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, logEventParcelable);
                transactOneway(1, obtainAndWriteInterfaceToken);
            }
        }

        public static IClearcutLoggerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
            if (queryLocalInterface instanceof IClearcutLoggerService) {
                return (IClearcutLoggerService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    void logError(IClearcutLoggerCallbacks iClearcutLoggerCallbacks, BatchedLogErrorParcelable batchedLogErrorParcelable);

    void logEvent(IClearcutLoggerCallbacks iClearcutLoggerCallbacks, LogEventParcelable logEventParcelable);
}
