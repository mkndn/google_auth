package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGmsServiceBroker extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends Binder implements IGmsServiceBroker {
        public static final int TRANSACTION_GET_ACCOUNT_DATA_SERVICE = 17;
        public static final int TRANSACTION_GET_ADDRESS_SERVICE = 24;
        public static final int TRANSACTION_GET_ADMOB_SERVICE = 12;
        public static final int TRANSACTION_GET_APP_DATA_SEARCH_SERVICE = 3;
        public static final int TRANSACTION_GET_APP_STATE_SERVICE = 10;
        public static final int TRANSACTION_GET_AUTH_SERVICE_DEPRECATED = 28;
        public static final int TRANSACTION_GET_AUTO_BACKUP_SERVICE = 23;
        public static final int TRANSACTION_GET_CAR_SERVICE = 25;
        public static final int TRANSACTION_GET_CAST_MIRRORING_SERVICE = 15;
        public static final int TRANSACTION_GET_CAST_SERVICE = 19;
        public static final int TRANSACTION_GET_CLEARCUT_LOGGER_SERVICE = 41;
        public static final int TRANSACTION_GET_CLOUD_SAVE_SERVICE = 33;
        public static final int TRANSACTION_GET_COMMON_SERVICE = 40;
        public static final int TRANSACTION_GET_DEVICE_CONNECTION_SERVICE = 44;
        public static final int TRANSACTION_GET_DEVICE_MANAGER_SERVICE = 36;
        public static final int TRANSACTION_GET_DRIVE_SERVICE = 20;
        public static final int TRANSACTION_GET_DROID_GUARD_SERVICE = 13;
        public static final int TRANSACTION_GET_FEEDBACK_SERVICE = 18;
        public static final int TRANSACTION_GET_FITNESS_SERVICE = 30;
        public static final int TRANSACTION_GET_GAMES_SERVICE = 9;
        public static final int TRANSACTION_GET_GLOBAL_SEARCH_ADMIN_SERVICE = 32;
        public static final int TRANSACTION_GET_GOOGLE_LOCATION_MANAGER_SERVICE = 8;
        public static final int TRANSACTION_GET_IDENTITY_SERVICE = 27;
        public static final int TRANSACTION_GET_KIDS_SERVICE = 45;
        public static final int TRANSACTION_GET_LIGHTWEIGHT_APP_DATA_SEARCH_SERVICE = 21;
        public static final int TRANSACTION_GET_LOCATION_SERVICE = 7;
        public static final int TRANSACTION_GET_LOCKBOX_SERVICE = 14;
        public static final int TRANSACTION_GET_NETWORK_QUALITY_SERVICE = 16;
        public static final int TRANSACTION_GET_PANORAMA_SERVICE = 2;
        public static final int TRANSACTION_GET_PEOPLE_SERVICE = 5;
        public static final int TRANSACTION_GET_PLAY_LOG_SERVICE = 11;
        public static final int TRANSACTION_GET_PLUS_SERVICE = 1;
        public static final int TRANSACTION_GET_PSEUDONYMOUS_ID_SERVICE = 37;
        public static final int TRANSACTION_GET_REMINDERS_SERVICE = 38;
        public static final int TRANSACTION_GET_REPORTING_SERVICE = 6;
        public static final int TRANSACTION_GET_SEARCH_ADMINISTRATION_SERVICE = 22;
        public static final int TRANSACTION_GET_SEARCH_CORPORA_SERVICE = 35;
        public static final int TRANSACTION_GET_SEARCH_QUERIES_SERVICE = 31;
        public static final int TRANSACTION_GET_SERVICE = 46;
        public static final int TRANSACTION_GET_UDC_SERVICE = 34;
        public static final int TRANSACTION_GET_USAGE_REPORTING_SERVICE = 43;
        public static final int TRANSACTION_GET_WALLET_SERVICE = 4;
        public static final int TRANSACTION_GET_WALLET_SERVICE_WITH_PACKAGE = 42;
        public static final int TRANSACTION_GET_WEARABLE_SERVICE = 26;
        public static final int TRANSACTION_VALIDATE_ACCOUNT = 47;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        class Proxy implements IGmsServiceBroker {
            private final IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.google.android.gms.common.internal.IGmsServiceBroker
            public void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(iGmsCallbacks != null ? iGmsCallbacks.asBinder() : null);
                    if (getServiceRequest != null) {
                        obtain.writeInt(1);
                        getServiceRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IGmsServiceBroker asInterface(IBinder iBinder) {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGmsServiceBroker)) {
                return (IGmsServiceBroker) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            throw null;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            throw null;
        }
    }

    void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest);
}
