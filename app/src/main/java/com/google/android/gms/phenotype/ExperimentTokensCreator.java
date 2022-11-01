package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ExperimentTokensCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(ExperimentTokens experimentTokens, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, experimentTokens.user, false);
        SafeParcelWriter.writeByteArray(parcel, 3, experimentTokens.directExperimentToken, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 4, experimentTokens.gaiaCrossExperimentTokens, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 5, experimentTokens.pseudonymousCrossExperimentTokens, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 6, experimentTokens.alwaysCrossExperimentTokens, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 7, experimentTokens.otherCrossExperimentTokens, false);
        SafeParcelWriter.writeIntArray(parcel, 8, experimentTokens.weakExperimentIds, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 9, experimentTokens.additionalDirectExperimentTokens, false);
        SafeParcelWriter.writeIntArray(parcel, 10, experimentTokens.genericDimensions, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public ExperimentTokens createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        byte[] bArr = null;
        byte[][] bArr2 = null;
        byte[][] bArr3 = null;
        byte[][] bArr4 = null;
        byte[][] bArr5 = null;
        int[] iArr = null;
        byte[][] bArr6 = null;
        int[] iArr2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 4:
                    bArr2 = SafeParcelReader.createByteArrayArray(parcel, readHeader);
                    break;
                case 5:
                    bArr3 = SafeParcelReader.createByteArrayArray(parcel, readHeader);
                    break;
                case 6:
                    bArr4 = SafeParcelReader.createByteArrayArray(parcel, readHeader);
                    break;
                case 7:
                    bArr5 = SafeParcelReader.createByteArrayArray(parcel, readHeader);
                    break;
                case 8:
                    iArr = SafeParcelReader.createIntArray(parcel, readHeader);
                    break;
                case 9:
                    bArr6 = SafeParcelReader.createByteArrayArray(parcel, readHeader);
                    break;
                case 10:
                    iArr2 = SafeParcelReader.createIntArray(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ExperimentTokens(str, bArr, bArr2, bArr3, bArr4, bArr5, iArr, bArr6, iArr2);
    }

    @Override // android.os.Parcelable.Creator
    public ExperimentTokens[] newArray(int i) {
        return new ExperimentTokens[i];
    }
}
