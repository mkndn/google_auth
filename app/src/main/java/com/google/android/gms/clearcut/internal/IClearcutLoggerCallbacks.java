package com.google.android.gms.clearcut.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IClearcutLoggerCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IClearcutLoggerCallbacks {
        public Stub() {
            super("com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    enforceNoDataAvail(parcel);
                    onLogEvent((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 2:
                    enforceNoDataAvail(parcel);
                    onForceUpload((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 3:
                    long readLong = parcel.readLong();
                    enforceNoDataAvail(parcel);
                    onStartCollectForDebug((Status) Codecs.createParcelable(parcel, Status.CREATOR), readLong);
                    return true;
                case 4:
                    enforceNoDataAvail(parcel);
                    onStopCollectForDebug((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 5:
                    long readLong2 = parcel.readLong();
                    enforceNoDataAvail(parcel);
                    onGetCollectForDebugExpiryTime((Status) Codecs.createParcelable(parcel, Status.CREATOR), readLong2);
                    return true;
                case 6:
                    enforceNoDataAvail(parcel);
                    onGetLogEventParcelables((Status) Codecs.createParcelable(parcel, Status.CREATOR), (LogEventParcelable[]) parcel.createTypedArray(LogEventParcelable.CREATOR));
                    return true;
                case 7:
                    enforceNoDataAvail(parcel);
                    onGetLogEventParcelablesDataBuffer((DataHolder) Codecs.createParcelable(parcel, DataHolder.CREATOR));
                    return true;
                case 8:
                    enforceNoDataAvail(parcel);
                    onLogError((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                default:
                    return false;
            }
        }
    }

    void onForceUpload(Status status);

    void onGetCollectForDebugExpiryTime(Status status, long j);

    void onGetLogEventParcelables(Status status, LogEventParcelable[] logEventParcelableArr);

    void onGetLogEventParcelablesDataBuffer(DataHolder dataHolder);

    void onLogError(Status status);

    void onLogEvent(Status status);

    void onStartCollectForDebug(Status status, long j);

    void onStopCollectForDebug(Status status);
}
