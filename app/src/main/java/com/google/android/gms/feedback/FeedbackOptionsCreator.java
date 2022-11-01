package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FeedbackOptionsCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(FeedbackOptions feedbackOptions, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, feedbackOptions.accountInUse, false);
        SafeParcelWriter.writeBundle(parcel, 3, feedbackOptions.psdBundle, false);
        SafeParcelWriter.writeString(parcel, 5, feedbackOptions.description, false);
        SafeParcelWriter.writeParcelable(parcel, 6, feedbackOptions.applicationErrorReport, i, false);
        SafeParcelWriter.writeString(parcel, 7, feedbackOptions.categoryTag, false);
        SafeParcelWriter.writeParcelable(parcel, 8, feedbackOptions.bitmapTeleporter, i, false);
        SafeParcelWriter.writeString(parcel, 9, feedbackOptions.packageName, false);
        SafeParcelWriter.writeTypedList(parcel, 10, feedbackOptions.fileTeleporters, false);
        SafeParcelWriter.writeBoolean(parcel, 11, feedbackOptions.excludePii);
        SafeParcelWriter.writeParcelable(parcel, 12, feedbackOptions.themeSettings, i, false);
        SafeParcelWriter.writeParcelable(parcel, 13, feedbackOptions.logOptions, i, false);
        SafeParcelWriter.writeBoolean(parcel, 14, feedbackOptions.psdHasNoPii);
        SafeParcelWriter.writeParcelable(parcel, 15, feedbackOptions.screenshot, i, false);
        SafeParcelWriter.writeString(parcel, 16, feedbackOptions.sessionId, false);
        SafeParcelWriter.writeBoolean(parcel, 17, feedbackOptions.isSilentSend);
        SafeParcelWriter.writeLong(parcel, 18, feedbackOptions.startTickNanos);
        SafeParcelWriter.writeBoolean(parcel, 19, feedbackOptions.enableDynamicColor);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public FeedbackOptions createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        Bundle bundle = null;
        String str2 = null;
        ApplicationErrorReport applicationErrorReport = null;
        String str3 = null;
        BitmapTeleporter bitmapTeleporter = null;
        String str4 = null;
        ArrayList arrayList = null;
        ThemeSettings themeSettings = null;
        LogOptions logOptions = null;
        Bitmap bitmap = null;
        String str5 = null;
        long j = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 4:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 5:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    applicationErrorReport = (ApplicationErrorReport) SafeParcelReader.createParcelable(parcel, readHeader, ApplicationErrorReport.CREATOR);
                    break;
                case 7:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 8:
                    bitmapTeleporter = (BitmapTeleporter) SafeParcelReader.createParcelable(parcel, readHeader, BitmapTeleporter.CREATOR);
                    break;
                case 9:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 10:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, FileTeleporter.CREATOR);
                    break;
                case 11:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 12:
                    themeSettings = (ThemeSettings) SafeParcelReader.createParcelable(parcel, readHeader, ThemeSettings.CREATOR);
                    break;
                case 13:
                    logOptions = (LogOptions) SafeParcelReader.createParcelable(parcel, readHeader, LogOptions.CREATOR);
                    break;
                case 14:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 15:
                    bitmap = (Bitmap) SafeParcelReader.createParcelable(parcel, readHeader, Bitmap.CREATOR);
                    break;
                case 16:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 17:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 18:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 19:
                    z4 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new FeedbackOptions(str, bundle, str2, applicationErrorReport, str3, bitmapTeleporter, str4, arrayList, z, themeSettings, logOptions, z2, bitmap, str5, z3, j, z4);
    }

    @Override // android.os.Parcelable.Creator
    public FeedbackOptions[] newArray(int i) {
        return new FeedbackOptions[i];
    }
}
