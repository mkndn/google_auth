package com.google.android.gms.phenotype.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.phenotype.Configurations;
import com.google.android.gms.phenotype.DogfoodsToken;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.android.gms.phenotype.Flag;
import com.google.android.gms.phenotype.FlagOverrides;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IPhenotypeCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IPhenotypeCallbacks {
        public Stub() {
            super("com.google.android.gms.phenotype.internal.IPhenotypeCallbacks");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    enforceNoDataAvail(parcel);
                    onRegistered((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 2:
                    enforceNoDataAvail(parcel);
                    onWeakRegistered((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 3:
                    enforceNoDataAvail(parcel);
                    onUnregistered((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 4:
                    enforceNoDataAvail(parcel);
                    onGetConfigurationSnapshot((Status) Codecs.createParcelable(parcel, Status.CREATOR), (Configurations) Codecs.createParcelable(parcel, Configurations.CREATOR));
                    return true;
                case 5:
                    enforceNoDataAvail(parcel);
                    onCommitToConfiguration((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 6:
                    enforceNoDataAvail(parcel);
                    onExperimentTokensRetrieved((Status) Codecs.createParcelable(parcel, Status.CREATOR), (ExperimentTokens) Codecs.createParcelable(parcel, ExperimentTokens.CREATOR));
                    return true;
                case 7:
                    enforceNoDataAvail(parcel);
                    onDogfoodsTokenRetrieved((Status) Codecs.createParcelable(parcel, Status.CREATOR), (DogfoodsToken) Codecs.createParcelable(parcel, DogfoodsToken.CREATOR));
                    return true;
                case 8:
                    enforceNoDataAvail(parcel);
                    onDogfoodsTokenSet((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 9:
                    enforceNoDataAvail(parcel);
                    onGetFlag((Status) Codecs.createParcelable(parcel, Status.CREATOR), (Flag) Codecs.createParcelable(parcel, Flag.CREATOR));
                    return true;
                case 10:
                    enforceNoDataAvail(parcel);
                    onGetCommittedConfiguration((Status) Codecs.createParcelable(parcel, Status.CREATOR), (Configurations) Codecs.createParcelable(parcel, Configurations.CREATOR));
                    return true;
                case 11:
                    long readLong = parcel.readLong();
                    enforceNoDataAvail(parcel);
                    onSyncAfter((Status) Codecs.createParcelable(parcel, Status.CREATOR), readLong);
                    return true;
                case 12:
                    enforceNoDataAvail(parcel);
                    onSetFlagOverride((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 13:
                    enforceNoDataAvail(parcel);
                    onListOrDeleteFlagOverrides((Status) Codecs.createParcelable(parcel, Status.CREATOR), (FlagOverrides) Codecs.createParcelable(parcel, FlagOverrides.CREATOR));
                    return true;
                case 14:
                    enforceNoDataAvail(parcel);
                    onSetAppSpecificProperties((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 15:
                    enforceNoDataAvail(parcel);
                    onSetDimensions((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    return true;
                case 16:
                    long readLong2 = parcel.readLong();
                    enforceNoDataAvail(parcel);
                    onGetServingVersion((Status) Codecs.createParcelable(parcel, Status.CREATOR), readLong2);
                    return true;
                default:
                    return false;
            }
        }
    }

    void onCommitToConfiguration(Status status);

    void onDogfoodsTokenRetrieved(Status status, DogfoodsToken dogfoodsToken);

    void onDogfoodsTokenSet(Status status);

    void onExperimentTokensRetrieved(Status status, ExperimentTokens experimentTokens);

    void onGetCommittedConfiguration(Status status, Configurations configurations);

    void onGetConfigurationSnapshot(Status status, Configurations configurations);

    void onGetFlag(Status status, Flag flag);

    void onGetServingVersion(Status status, long j);

    void onListOrDeleteFlagOverrides(Status status, FlagOverrides flagOverrides);

    void onRegistered(Status status);

    void onSetAppSpecificProperties(Status status);

    void onSetDimensions(Status status);

    void onSetFlagOverride(Status status);

    void onSyncAfter(Status status, long j);

    void onUnregistered(Status status);

    void onWeakRegistered(Status status);
}
