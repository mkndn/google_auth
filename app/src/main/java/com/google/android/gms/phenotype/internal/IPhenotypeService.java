package com.google.android.gms.phenotype.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IPhenotypeService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IPhenotypeService {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class Proxy extends BaseProxy implements IPhenotypeService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.phenotype.internal.IPhenotypeService");
            }

            @Override // com.google.android.gms.phenotype.internal.IPhenotypeService
            public void commitToConfiguration(IPhenotypeCallbacks iPhenotypeCallbacks, String str) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iPhenotypeCallbacks);
                obtainAndWriteInterfaceToken.writeString(str);
                transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.phenotype.internal.IPhenotypeService
            public void getConfigurationSnapshotWithToken(IPhenotypeCallbacks iPhenotypeCallbacks, String str, String str2, String str3) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iPhenotypeCallbacks);
                obtainAndWriteInterfaceToken.writeString(str);
                obtainAndWriteInterfaceToken.writeString(str2);
                obtainAndWriteInterfaceToken.writeString(str3);
                transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.phenotype.internal.IPhenotypeService
            public void register(IPhenotypeCallbacks iPhenotypeCallbacks, String str, int i, String[] strArr, byte[] bArr) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iPhenotypeCallbacks);
                obtainAndWriteInterfaceToken.writeString(str);
                obtainAndWriteInterfaceToken.writeInt(i);
                obtainAndWriteInterfaceToken.writeStringArray(strArr);
                obtainAndWriteInterfaceToken.writeByteArray(bArr);
                transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
            }
        }

        public static IPhenotypeService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.phenotype.internal.IPhenotypeService");
            if (queryLocalInterface instanceof IPhenotypeService) {
                return (IPhenotypeService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    void commitToConfiguration(IPhenotypeCallbacks iPhenotypeCallbacks, String str);

    void getConfigurationSnapshotWithToken(IPhenotypeCallbacks iPhenotypeCallbacks, String str, String str2, String str3);

    void register(IPhenotypeCallbacks iPhenotypeCallbacks, String str, int i, String[] strArr, byte[] bArr);
}
