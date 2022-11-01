package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ErrorReportCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(ErrorReport errorReport, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, errorReport.applicationErrorReport, i, false);
        SafeParcelWriter.writeString(parcel, 3, errorReport.description, false);
        SafeParcelWriter.writeInt(parcel, 4, errorReport.packageVersion);
        SafeParcelWriter.writeString(parcel, 5, errorReport.packageVersionName, false);
        SafeParcelWriter.writeString(parcel, 6, errorReport.device, false);
        SafeParcelWriter.writeString(parcel, 7, errorReport.buildId, false);
        SafeParcelWriter.writeString(parcel, 8, errorReport.buildType, false);
        SafeParcelWriter.writeString(parcel, 9, errorReport.model, false);
        SafeParcelWriter.writeString(parcel, 10, errorReport.product, false);
        SafeParcelWriter.writeString(parcel, 11, errorReport.buildFingerprint, false);
        SafeParcelWriter.writeInt(parcel, 12, errorReport.sdk_int);
        SafeParcelWriter.writeString(parcel, 13, errorReport.release, false);
        SafeParcelWriter.writeString(parcel, 14, errorReport.incremental, false);
        SafeParcelWriter.writeString(parcel, 15, errorReport.codename, false);
        SafeParcelWriter.writeString(parcel, 16, errorReport.board, false);
        SafeParcelWriter.writeString(parcel, 17, errorReport.brand, false);
        SafeParcelWriter.writeStringArray(parcel, 18, errorReport.runningApplications, false);
        SafeParcelWriter.writeStringArray(parcel, 19, errorReport.systemLog, false);
        SafeParcelWriter.writeStringArray(parcel, 20, errorReport.eventLog, false);
        SafeParcelWriter.writeString(parcel, 21, errorReport.anrStackTraces, false);
        SafeParcelWriter.writeString(parcel, 22, errorReport.screenshot, false);
        SafeParcelWriter.writeByteArray(parcel, 23, errorReport.screenshotBytes, false);
        SafeParcelWriter.writeInt(parcel, 24, errorReport.screenshotHeight);
        SafeParcelWriter.writeInt(parcel, 25, errorReport.screenshotWidth);
        SafeParcelWriter.writeInt(parcel, 26, errorReport.phoneType);
        SafeParcelWriter.writeInt(parcel, 27, errorReport.networkType);
        SafeParcelWriter.writeString(parcel, 28, errorReport.networkName, false);
        SafeParcelWriter.writeString(parcel, 29, errorReport.account, false);
        SafeParcelWriter.writeString(parcel, 30, errorReport.localeString, false);
        SafeParcelWriter.writeBundle(parcel, 31, errorReport.psdBundle, false);
        SafeParcelWriter.writeBoolean(parcel, 32, errorReport.isSilentSend);
        SafeParcelWriter.writeInt(parcel, 33, errorReport.networkMcc);
        SafeParcelWriter.writeInt(parcel, 34, errorReport.networkMnc);
        SafeParcelWriter.writeBoolean(parcel, 35, errorReport.isCtlAllowed);
        SafeParcelWriter.writeString(parcel, 36, errorReport.exceptionClassName, false);
        SafeParcelWriter.writeString(parcel, 37, errorReport.throwFileName, false);
        SafeParcelWriter.writeInt(parcel, 38, errorReport.throwLineNumber);
        SafeParcelWriter.writeString(parcel, 39, errorReport.throwClassName, false);
        SafeParcelWriter.writeString(parcel, 40, errorReport.throwMethodName, false);
        SafeParcelWriter.writeString(parcel, 41, errorReport.stackTrace, false);
        SafeParcelWriter.writeString(parcel, 42, errorReport.exceptionMessage, false);
        SafeParcelWriter.writeString(parcel, 43, errorReport.categoryTag, false);
        SafeParcelWriter.writeString(parcel, 44, errorReport.color, false);
        SafeParcelWriter.writeString(parcel, 45, errorReport.submittingPackageName, false);
        SafeParcelWriter.writeParcelable(parcel, 46, errorReport.bitmapTeleporter, i, false);
        SafeParcelWriter.writeString(parcel, 47, errorReport.screenshotPath, false);
        SafeParcelWriter.writeTypedArray(parcel, 48, errorReport.fileTeleporterList, i, false);
        SafeParcelWriter.writeStringArray(parcel, 49, errorReport.psdFilePaths, false);
        SafeParcelWriter.writeBoolean(parcel, 50, errorReport.excludePii);
        SafeParcelWriter.writeString(parcel, 51, errorReport.launcher, false);
        SafeParcelWriter.writeParcelable(parcel, 52, errorReport.themeSettings, i, false);
        SafeParcelWriter.writeParcelable(parcel, 53, errorReport.logOptions, i, false);
        SafeParcelWriter.writeString(parcel, 54, errorReport.suggestionSessionId, false);
        SafeParcelWriter.writeBoolean(parcel, 55, errorReport.suggestionShown);
        SafeParcelWriter.writeBundle(parcel, 56, errorReport.classificationSignals, false);
        SafeParcelWriter.writeTypedList(parcel, 57, errorReport.highlightBounds, false);
        SafeParcelWriter.writeBoolean(parcel, 58, errorReport.psdHasNoPii);
        SafeParcelWriter.writeParcelable(parcel, 59, errorReport.screenshotBitmap, i, false);
        SafeParcelWriter.writeString(parcel, 60, errorReport.sessionId, false);
        SafeParcelWriter.writeStringList(parcel, 61, errorReport.phenotypeExperimentTokens, false);
        SafeParcelWriter.writeInt(parcel, 62, errorReport.noExperimentTokensReason);
        SafeParcelWriter.writeInt(parcel, 63, errorReport.reportSource);
        SafeParcelWriter.writeStringArray(parcel, 64, errorReport.systemFullLog, false);
        SafeParcelWriter.writeStringArray(parcel, 65, errorReport.mainFullLog, false);
        SafeParcelWriter.writeStringArray(parcel, 66, errorReport.contentCaptureDumpsysLog, false);
        SafeParcelWriter.writeBoolean(parcel, 67, errorReport.enableDynamicColor);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public ErrorReport createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ApplicationErrorReport applicationErrorReport = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str14 = null;
        String str15 = null;
        byte[] bArr = null;
        String str16 = null;
        String str17 = null;
        String str18 = null;
        Bundle bundle = null;
        String str19 = null;
        String str20 = null;
        String str21 = null;
        String str22 = null;
        String str23 = null;
        String str24 = null;
        String str25 = null;
        String str26 = null;
        String str27 = null;
        BitmapTeleporter bitmapTeleporter = null;
        String str28 = null;
        FileTeleporter[] fileTeleporterArr = null;
        String[] strArr4 = null;
        String str29 = null;
        ThemeSettings themeSettings = null;
        LogOptions logOptions = null;
        String str30 = null;
        Bundle bundle2 = null;
        ArrayList arrayList = null;
        Bitmap bitmap = null;
        String str31 = null;
        ArrayList arrayList2 = null;
        String[] strArr5 = null;
        String[] strArr6 = null;
        String[] strArr7 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        int i7 = 0;
        int i8 = 0;
        boolean z2 = false;
        int i9 = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        int i10 = 0;
        int i11 = 0;
        boolean z6 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    applicationErrorReport = (ApplicationErrorReport) SafeParcelReader.createParcelable(parcel, readHeader, ApplicationErrorReport.CREATOR);
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 8:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 10:
                    str7 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 11:
                    str8 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 12:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 13:
                    str9 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 14:
                    str10 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 15:
                    str11 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 16:
                    str12 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 17:
                    str13 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 18:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 19:
                    strArr2 = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 20:
                    strArr3 = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 21:
                    str14 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 22:
                    str15 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 23:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 24:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 25:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 26:
                    i5 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 27:
                    i6 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 28:
                    str16 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 29:
                    str17 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 30:
                    str18 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 31:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 32:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 33:
                    i7 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 34:
                    i8 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 35:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 36:
                    str19 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 37:
                    str20 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 38:
                    i9 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 39:
                    str21 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 40:
                    str22 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 41:
                    str23 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 42:
                    str24 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 43:
                    str25 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 44:
                    str26 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 45:
                    str27 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 46:
                    bitmapTeleporter = (BitmapTeleporter) SafeParcelReader.createParcelable(parcel, readHeader, BitmapTeleporter.CREATOR);
                    break;
                case 47:
                    str28 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 48:
                    fileTeleporterArr = (FileTeleporter[]) SafeParcelReader.createTypedArray(parcel, readHeader, FileTeleporter.CREATOR);
                    break;
                case 49:
                    strArr4 = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 50:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 51:
                    str29 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 52:
                    themeSettings = (ThemeSettings) SafeParcelReader.createParcelable(parcel, readHeader, ThemeSettings.CREATOR);
                    break;
                case 53:
                    logOptions = (LogOptions) SafeParcelReader.createParcelable(parcel, readHeader, LogOptions.CREATOR);
                    break;
                case 54:
                    str30 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 55:
                    z4 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 56:
                    bundle2 = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 57:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, RectF.CREATOR);
                    break;
                case 58:
                    z5 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 59:
                    bitmap = (Bitmap) SafeParcelReader.createParcelable(parcel, readHeader, Bitmap.CREATOR);
                    break;
                case 60:
                    str31 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 61:
                    arrayList2 = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 62:
                    i10 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 63:
                    i11 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 64:
                    strArr5 = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 65:
                    strArr6 = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 66:
                    strArr7 = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 67:
                    z6 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ErrorReport(applicationErrorReport, str, i, str2, str3, str4, str5, str6, str7, str8, i2, str9, str10, str11, str12, str13, strArr, strArr2, strArr3, str14, str15, bArr, i3, i4, i5, i6, str16, str17, str18, bundle, z, i7, i8, z2, str19, str20, i9, str21, str22, str23, str24, str25, str26, str27, bitmapTeleporter, str28, fileTeleporterArr, strArr4, z3, str29, themeSettings, logOptions, str30, z4, bundle2, arrayList, z5, bitmap, str31, arrayList2, i10, i11, strArr5, strArr6, strArr7, z6);
    }

    @Override // android.os.Parcelable.Creator
    public ErrorReport[] newArray(int i) {
        return new ErrorReport[i];
    }
}
