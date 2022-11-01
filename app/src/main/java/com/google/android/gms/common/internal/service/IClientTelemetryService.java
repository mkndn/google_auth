package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.internal.TelemetryData;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IClientTelemetryService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IClientTelemetryService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements IClientTelemetryService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.service.IClientTelemetryService");
            }

            @Override // com.google.android.gms.common.internal.service.IClientTelemetryService
            public void recordData(TelemetryData telemetryData) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(obtainAndWriteInterfaceToken, telemetryData);
                transactOneway(1, obtainAndWriteInterfaceToken);
            }
        }

        public static IClientTelemetryService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.service.IClientTelemetryService");
            if (queryLocalInterface instanceof IClientTelemetryService) {
                return (IClientTelemetryService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    void recordData(TelemetryData telemetryData);
}
