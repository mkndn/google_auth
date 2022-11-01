package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.clearcut.internal.LogVerifierResultParcelable;
import com.google.android.gms.clearcut.internal.PlayLoggerContext;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.phenotype.ExperimentTokens;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LogEventParcelableCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(LogEventParcelable logEventParcelable, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, logEventParcelable.playLoggerContext, i, false);
        SafeParcelWriter.writeByteArray(parcel, 3, logEventParcelable.logEventBytes, false);
        SafeParcelWriter.writeIntArray(parcel, 4, logEventParcelable.testCodes, false);
        SafeParcelWriter.writeStringArray(parcel, 5, logEventParcelable.mendelPackages, false);
        SafeParcelWriter.writeIntArray(parcel, 6, logEventParcelable.experimentIds, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 7, logEventParcelable.experimentTokens, false);
        SafeParcelWriter.writeBoolean(parcel, 8, logEventParcelable.addPhenotypeExperimentTokens);
        SafeParcelWriter.writeTypedArray(parcel, 9, logEventParcelable.experimentTokensParcelables, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, logEventParcelable.logVerifierResult, i, false);
        SafeParcelWriter.writeStringArray(parcel, 12, logEventParcelable.getMendelPackagesToFilter(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public LogEventParcelable createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        PlayLoggerContext playLoggerContext = null;
        byte[] bArr = null;
        int[] iArr = null;
        String[] strArr = null;
        int[] iArr2 = null;
        byte[][] bArr2 = null;
        ExperimentTokens[] experimentTokensArr = null;
        LogVerifierResultParcelable logVerifierResultParcelable = null;
        String[] strArr2 = null;
        boolean z = true;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    playLoggerContext = (PlayLoggerContext) SafeParcelReader.createParcelable(parcel, readHeader, PlayLoggerContext.CREATOR);
                    break;
                case 3:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 4:
                    iArr = SafeParcelReader.createIntArray(parcel, readHeader);
                    break;
                case 5:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 6:
                    iArr2 = SafeParcelReader.createIntArray(parcel, readHeader);
                    break;
                case 7:
                    bArr2 = SafeParcelReader.createByteArrayArray(parcel, readHeader);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 9:
                    experimentTokensArr = (ExperimentTokens[]) SafeParcelReader.createTypedArray(parcel, readHeader, ExperimentTokens.CREATOR);
                    break;
                case 10:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 11:
                    logVerifierResultParcelable = (LogVerifierResultParcelable) SafeParcelReader.createParcelable(parcel, readHeader, LogVerifierResultParcelable.CREATOR);
                    break;
                case 12:
                    strArr2 = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new LogEventParcelable(playLoggerContext, bArr, iArr, strArr, iArr2, bArr2, z, experimentTokensArr, logVerifierResultParcelable, strArr2);
    }

    @Override // android.os.Parcelable.Creator
    public LogEventParcelable[] newArray(int i) {
        return new LogEventParcelable[i];
    }
}
