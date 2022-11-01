package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.io.File;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ErrorReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new ErrorReportCreator();
    public static final int TYPE_USER_INITIATED_BUG_REPORT = 11;
    public String account;
    public String anrStackTraces;
    public ApplicationErrorReport applicationErrorReport;
    public BitmapTeleporter bitmapTeleporter;
    public String board;
    public String brand;
    public String buildFingerprint;
    public String buildId;
    public String buildType;
    public String categoryTag;
    public Bundle classificationSignals;
    public String codename;
    @Deprecated
    public String color;
    public String[] contentCaptureDumpsysLog;
    public String description;
    public String device;
    public boolean enableDynamicColor;
    public String[] eventLog;
    public String exceptionClassName;
    public String exceptionMessage;
    public boolean excludePii;
    public FileTeleporter[] fileTeleporterList;
    public List highlightBounds;
    public String incremental;
    public boolean isCtlAllowed;
    public boolean isSilentSend;
    public String launcher;
    public String localeString;
    public LogOptions logOptions;
    public String[] mainFullLog;
    public String model;
    public int networkMcc;
    public int networkMnc;
    public String networkName;
    public int networkType;
    public int noExperimentTokensReason;
    public int packageVersion;
    public String packageVersionName;
    public List phenotypeExperimentTokens;
    public int phoneType;
    public String product;
    public Bundle psdBundle;
    public String[] psdFilePaths;
    public boolean psdHasNoPii;
    public String release;
    public int reportSource;
    public String[] runningApplications;
    public String screenshot;
    public Bitmap screenshotBitmap;
    public byte[] screenshotBytes;
    public int screenshotHeight;
    public String screenshotPath;
    public int screenshotWidth;
    public int sdk_int;
    public String sessionId;
    public String stackTrace;
    public String submittingPackageName;
    @Deprecated
    public String suggestionSessionId;
    public boolean suggestionShown;
    public String[] systemFullLog;
    public String[] systemLog;
    public ThemeSettings themeSettings;
    public String throwClassName;
    public String throwFileName;
    public int throwLineNumber;
    public String throwMethodName;

    public ErrorReport() {
        this.applicationErrorReport = new ApplicationErrorReport();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ErrorReportCreator.writeToParcel(this, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ErrorReport(ApplicationErrorReport applicationErrorReport, String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String str9, String str10, String str11, String str12, String str13, String[] strArr, String[] strArr2, String[] strArr3, String str14, String str15, byte[] bArr, int i3, int i4, int i5, int i6, String str16, String str17, String str18, Bundle bundle, boolean z, int i7, int i8, boolean z2, String str19, String str20, int i9, String str21, String str22, String str23, String str24, String str25, String str26, String str27, BitmapTeleporter bitmapTeleporter, String str28, FileTeleporter[] fileTeleporterArr, String[] strArr4, boolean z3, String str29, ThemeSettings themeSettings, LogOptions logOptions, String str30, boolean z4, Bundle bundle2, List list, boolean z5, Bitmap bitmap, String str31, List list2, int i10, int i11, String[] strArr5, String[] strArr6, String[] strArr7, boolean z6) {
        this.applicationErrorReport = new ApplicationErrorReport();
        this.applicationErrorReport = applicationErrorReport;
        this.description = str;
        this.packageVersion = i;
        this.packageVersionName = str2;
        this.device = str3;
        this.buildId = str4;
        this.buildType = str5;
        this.model = str6;
        this.product = str7;
        this.buildFingerprint = str8;
        this.sdk_int = i2;
        this.release = str9;
        this.incremental = str10;
        this.codename = str11;
        this.board = str12;
        this.brand = str13;
        this.runningApplications = strArr;
        this.systemLog = strArr2;
        this.eventLog = strArr3;
        this.anrStackTraces = str14;
        this.screenshot = str15;
        this.screenshotBytes = bArr;
        this.screenshotHeight = i3;
        this.screenshotWidth = i4;
        this.phoneType = i5;
        this.networkType = i6;
        this.networkName = str16;
        this.account = str17;
        this.localeString = str18;
        this.psdBundle = bundle;
        this.isSilentSend = z;
        this.networkMcc = i7;
        this.networkMnc = i8;
        this.isCtlAllowed = z2;
        this.exceptionClassName = str19;
        this.throwFileName = str20;
        this.throwLineNumber = i9;
        this.throwClassName = str21;
        this.throwMethodName = str22;
        this.stackTrace = str23;
        this.exceptionMessage = str24;
        this.categoryTag = str25;
        this.color = str26;
        this.submittingPackageName = str27;
        this.bitmapTeleporter = bitmapTeleporter;
        this.screenshotPath = str28;
        this.fileTeleporterList = fileTeleporterArr;
        this.psdFilePaths = strArr4;
        this.excludePii = z3;
        this.launcher = str29;
        this.themeSettings = themeSettings;
        this.logOptions = logOptions;
        this.suggestionSessionId = str30;
        this.suggestionShown = z4;
        this.classificationSignals = bundle2;
        this.highlightBounds = list;
        this.psdHasNoPii = z5;
        this.screenshotBitmap = bitmap;
        this.sessionId = str31;
        this.phenotypeExperimentTokens = list2;
        this.noExperimentTokensReason = i10;
        this.reportSource = i11;
        this.systemFullLog = strArr5;
        this.mainFullLog = strArr6;
        this.contentCaptureDumpsysLog = strArr7;
        this.enableDynamicColor = z6;
    }

    public ErrorReport(FeedbackOptions feedbackOptions, File file) {
        this.applicationErrorReport = new ApplicationErrorReport();
        if (feedbackOptions == null) {
            return;
        }
        if (feedbackOptions.getPsdBundle() != null && !feedbackOptions.getPsdBundle().isEmpty()) {
            this.psdBundle = feedbackOptions.getPsdBundle();
        }
        if (!TextUtils.isEmpty(feedbackOptions.getAccountInUse())) {
            this.account = feedbackOptions.getAccountInUse();
        }
        if (!TextUtils.isEmpty(feedbackOptions.getDescription())) {
            this.description = feedbackOptions.getDescription();
        }
        ApplicationErrorReport.CrashInfo crashInfo = feedbackOptions.getCrashInfo();
        if (crashInfo != null) {
            this.throwMethodName = crashInfo.throwMethodName;
            this.throwLineNumber = crashInfo.throwLineNumber;
            this.throwClassName = crashInfo.throwClassName;
            this.stackTrace = crashInfo.stackTrace;
            this.exceptionClassName = crashInfo.exceptionClassName;
            this.exceptionMessage = crashInfo.exceptionMessage;
            this.throwFileName = crashInfo.throwFileName;
        }
        if (feedbackOptions.getThemeSettings() != null) {
            this.themeSettings = feedbackOptions.getThemeSettings();
        }
        if (!TextUtils.isEmpty(feedbackOptions.getCategoryTag())) {
            this.categoryTag = feedbackOptions.getCategoryTag();
        }
        String packageName = feedbackOptions.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            this.applicationErrorReport.packageName = packageName;
        }
        if (!TextUtils.isEmpty(feedbackOptions.getSessionId())) {
            this.sessionId = feedbackOptions.getSessionId();
        }
        if (feedbackOptions.getScreenshot() != null) {
            this.screenshotBitmap = feedbackOptions.getScreenshot();
        }
        if (file != null) {
            BitmapTeleporter bitmapTeleporter = feedbackOptions.getBitmapTeleporter();
            this.bitmapTeleporter = bitmapTeleporter;
            if (bitmapTeleporter != null) {
                bitmapTeleporter.setTempDir(file);
            }
            List<FileTeleporter> fileTeleporters = feedbackOptions.getFileTeleporters();
            if (fileTeleporters != null && !fileTeleporters.isEmpty()) {
                for (FileTeleporter fileTeleporter : fileTeleporters) {
                    fileTeleporter.setTempDir(file);
                }
                this.fileTeleporterList = (FileTeleporter[]) fileTeleporters.toArray(new FileTeleporter[0]);
            }
        }
        if (feedbackOptions.getLogOptions() != null) {
            this.logOptions = feedbackOptions.getLogOptions();
        }
        this.excludePii = feedbackOptions.getExcludePii();
        this.psdHasNoPii = feedbackOptions.getPsdHasNoPii();
        this.isSilentSend = feedbackOptions.getIsSilentSend();
        this.enableDynamicColor = feedbackOptions.enableDynamicColor;
    }
}
