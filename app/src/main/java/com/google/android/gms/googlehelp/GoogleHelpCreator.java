package com.google.android.gms.googlehelp;

import android.accounts.Account;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.feedback.ThemeSettings;
import com.google.android.gms.googlehelp.internal.common.OverflowMenuItem;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleHelpCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(GoogleHelp googleHelp, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, googleHelp.versionCode);
        SafeParcelWriter.writeString(parcel, 2, googleHelp.appContext, false);
        SafeParcelWriter.writeParcelable(parcel, 3, googleHelp.googleAccount, i, false);
        SafeParcelWriter.writeBundle(parcel, 4, googleHelp.psdBundle, false);
        SafeParcelWriter.writeBoolean(parcel, 5, googleHelp.searchEnabled);
        SafeParcelWriter.writeBoolean(parcel, 6, googleHelp.metricsReportingEnabled);
        SafeParcelWriter.writeStringList(parcel, 7, googleHelp.supportPhoneNumbers, false);
        SafeParcelWriter.writeBundle(parcel, 10, googleHelp.feedbackPsdBundle, false);
        SafeParcelWriter.writeParcelable(parcel, 11, googleHelp.backupScreenshot, i, false);
        SafeParcelWriter.writeString(parcel, 14, googleHelp.apiDebugOption, false);
        SafeParcelWriter.writeParcelable(parcel, 15, googleHelp.fallbackSupportUri, i, false);
        SafeParcelWriter.writeTypedList(parcel, 16, googleHelp.overflowMenuItems, false);
        SafeParcelWriter.writeInt(parcel, 17, googleHelp.helpActivityTheme);
        SafeParcelWriter.writeTypedList(parcel, 18, googleHelp.offlineSuggestions, false);
        SafeParcelWriter.writeByteArray(parcel, 19, googleHelp.screenshotBytes, false);
        SafeParcelWriter.writeInt(parcel, 20, googleHelp.screenshotWidth);
        SafeParcelWriter.writeInt(parcel, 21, googleHelp.screenshotHeight);
        SafeParcelWriter.writeBoolean(parcel, 22, googleHelp.showContactCardFirst);
        SafeParcelWriter.writeParcelable(parcel, 23, googleHelp.feedbackErrorReport, i, false);
        SafeParcelWriter.writeParcelable(parcel, 25, googleHelp.themeSettings, i, false);
        SafeParcelWriter.writeString(parcel, 28, googleHelp.callingPackageName, false);
        SafeParcelWriter.writeParcelable(parcel, 31, googleHelp.togglingData, i, false);
        SafeParcelWriter.writeInt(parcel, 32, googleHelp.pipInitPos);
        SafeParcelWriter.writeParcelable(parcel, 33, googleHelp.customFeedbackPendingIntent, i, false);
        SafeParcelWriter.writeString(parcel, 34, googleHelp.customCallingAppLabel, false);
        SafeParcelWriter.writeParcelable(parcel, 35, googleHelp.customCallingAppIcon, i, false);
        SafeParcelWriter.writeInt(parcel, 36, googleHelp.clientVersion);
        SafeParcelWriter.writeBoolean(parcel, 37, googleHelp.hasHelpPsd);
        SafeParcelWriter.writeBoolean(parcel, 38, googleHelp.hasFeedbackPsd);
        SafeParcelWriter.writeInt(parcel, 39, googleHelp.syncHelpPsdTimeoutMs);
        SafeParcelWriter.writeString(parcel, 40, googleHelp.sessionId, false);
        SafeParcelWriter.writeBoolean(parcel, 41, googleHelp.accountPickerEnabled);
        SafeParcelWriter.writeString(parcel, 42, googleHelp.appPackageNameOverride, false);
        SafeParcelWriter.writeBoolean(parcel, 43, googleHelp.sendFeedbackDisabled);
        SafeParcelWriter.writeParcelable(parcel, 44, googleHelp.nd4cSettings, i, false);
        SafeParcelWriter.writeBoolean(parcel, 45, googleHelp.dynamicColorEnabled);
        SafeParcelWriter.writeTypedList(parcel, 46, googleHelp.frdPsds, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public GoogleHelp createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        Account account = null;
        Bundle bundle = null;
        String str2 = null;
        String str3 = null;
        Bitmap bitmap = null;
        ArrayList arrayList = null;
        Bundle bundle2 = null;
        Bitmap bitmap2 = null;
        byte[] bArr = null;
        String str4 = null;
        Uri uri = null;
        ArrayList arrayList2 = null;
        ThemeSettings themeSettings = null;
        ArrayList arrayList3 = null;
        ErrorReport errorReport = null;
        TogglingData togglingData = null;
        PendingIntent pendingIntent = null;
        String str5 = null;
        String str6 = null;
        ND4CSettings nD4CSettings = null;
        ArrayList arrayList4 = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z3 = false;
        int i5 = 0;
        int i6 = 0;
        boolean z4 = false;
        boolean z5 = false;
        int i7 = 0;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    account = (Account) SafeParcelReader.createParcelable(parcel, readHeader, Account.CREATOR);
                    break;
                case 4:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 5:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 6:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 7:
                    arrayList = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 8:
                case 9:
                case 12:
                case 13:
                case 24:
                case 26:
                case 27:
                case 29:
                case 30:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 10:
                    bundle2 = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 11:
                    bitmap2 = (Bitmap) SafeParcelReader.createParcelable(parcel, readHeader, Bitmap.CREATOR);
                    break;
                case 14:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 15:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 16:
                    arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, OverflowMenuItem.CREATOR);
                    break;
                case 17:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 18:
                    arrayList3 = SafeParcelReader.createTypedList(parcel, readHeader, OfflineSuggestion.CREATOR);
                    break;
                case 19:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 20:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 21:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 22:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 23:
                    errorReport = (ErrorReport) SafeParcelReader.createParcelable(parcel, readHeader, ErrorReport.CREATOR);
                    break;
                case 25:
                    themeSettings = (ThemeSettings) SafeParcelReader.createParcelable(parcel, readHeader, ThemeSettings.CREATOR);
                    break;
                case 28:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 31:
                    togglingData = (TogglingData) SafeParcelReader.createParcelable(parcel, readHeader, TogglingData.CREATOR);
                    break;
                case 32:
                    i5 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 33:
                    pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, readHeader, PendingIntent.CREATOR);
                    break;
                case 34:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 35:
                    bitmap = (Bitmap) SafeParcelReader.createParcelable(parcel, readHeader, Bitmap.CREATOR);
                    break;
                case 36:
                    i6 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 37:
                    z4 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 38:
                    z5 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 39:
                    i7 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 40:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 41:
                    z6 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 42:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 43:
                    z7 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 44:
                    nD4CSettings = (ND4CSettings) SafeParcelReader.createParcelable(parcel, readHeader, ND4CSettings.CREATOR);
                    break;
                case 45:
                    z8 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 46:
                    arrayList4 = SafeParcelReader.createTypedList(parcel, readHeader, FRDProductSpecificDataEntry.CREATOR);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GoogleHelp(i, str, account, bundle, str2, str3, bitmap, z, z2, arrayList, bundle2, bitmap2, bArr, i2, i3, str4, uri, arrayList2, i4, themeSettings, arrayList3, z3, errorReport, togglingData, i5, pendingIntent, i6, z4, z5, i7, str5, z6, str6, z7, nD4CSettings, z8, arrayList4);
    }

    @Override // android.os.Parcelable.Creator
    public GoogleHelp[] newArray(int i) {
        return new GoogleHelp[i];
    }
}
