package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.feedback.internal.common.FeedbackUtils;
import googledata.experiments.mobile.gmscore.feedback.features.Y2019W24Bugfixes;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FeedbackOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new FeedbackOptionsCreator();
    String accountInUse;
    ApplicationErrorReport applicationErrorReport;
    private BaseFeedbackProductSpecificData asyncFeedbackPsd;
    BitmapTeleporter bitmapTeleporter;
    String categoryTag;
    String description;
    boolean enableDynamicColor;
    boolean excludePii;
    List fileTeleporters;
    boolean isSilentSend;
    LogOptions logOptions;
    String packageName;
    Bundle psdBundle;
    boolean psdHasNoPii;
    Bitmap screenshot;
    String sessionId;
    long startTickNanos;
    ThemeSettings themeSettings;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private String accountInUse;
        private BaseFeedbackProductSpecificData asyncFeedbackPsd;
        private BitmapTeleporter bitmapTeleporter;
        private String categoryTag;
        private String description;
        private boolean enableDynamicColor;
        private boolean excludePii;
        private boolean isSilentSend;
        private LogOptions logOptions;
        private boolean psdHasNoPii;
        private Bitmap screenshot;
        private long startTickNanos;
        private ThemeSettings themeSettings;
        private final Bundle psdBundle = new Bundle();
        private final List fileTeleporters = new ArrayList();
        ApplicationErrorReport applicationErrorReport = new ApplicationErrorReport();
        private String sessionId = FeedbackUtils.createDefaultSessionId();

        public FeedbackOptions build() {
            return new FeedbackOptions(new ApplicationErrorReport()).setScreenshot(this.screenshot).setBitmapTeleporter(this.bitmapTeleporter).setAccountInUse(this.accountInUse).setDescription(this.description).setPsdBundle(this.psdBundle).setCategoryTag(this.categoryTag).setPsbd(this.fileTeleporters).setExcludePii(this.excludePii).setThemeSettings(this.themeSettings).setLogOptions(this.logOptions).setPsdHasNoPii(this.psdHasNoPii).setAsyncPsd(this.asyncFeedbackPsd).setSessionId(this.sessionId).setIsSilentSend(this.isSilentSend).setStartTickNanos(this.startTickNanos).setEnableDynamicColor(this.enableDynamicColor);
        }

        @Deprecated
        public Builder setScreenshot(Bitmap bitmap) {
            if (bitmap != null) {
                setScreenshotBitmap(bitmap);
            }
            return this;
        }

        public Builder setScreenshotBitmap(Bitmap bitmap) {
            if (this.excludePii && Y2019W24Bugfixes.throwOnSetScreenshotButNoPiiAllowed()) {
                throw new IllegalStateException("Can't call setScreenshotBitmap after report is already certified pii free.");
            }
            this.screenshot = bitmap;
            return this;
        }
    }

    private FeedbackOptions(ApplicationErrorReport applicationErrorReport) {
        this(null, null, null, applicationErrorReport, null, null, null, null, true, null, null, false, null, null, false, 0L, false);
    }

    public static FeedbackOptions newInstanceForAsyncPsbd(List list) {
        FeedbackOptions feedbackOptions = new FeedbackOptions(new ApplicationErrorReport());
        if (list != null) {
            feedbackOptions.setPsbd(list);
        }
        return feedbackOptions;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setAccountInUse(String str) {
        this.accountInUse = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setAsyncPsd(BaseFeedbackProductSpecificData baseFeedbackProductSpecificData) {
        this.asyncFeedbackPsd = baseFeedbackProductSpecificData;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setBitmapTeleporter(BitmapTeleporter bitmapTeleporter) {
        this.bitmapTeleporter = bitmapTeleporter;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setCategoryTag(String str) {
        this.categoryTag = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setDescription(String str) {
        this.description = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setEnableDynamicColor(boolean z) {
        this.enableDynamicColor = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setExcludePii(boolean z) {
        this.excludePii = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setIsSilentSend(boolean z) {
        this.isSilentSend = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setLogOptions(LogOptions logOptions) {
        this.logOptions = logOptions;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setPsbd(List list) {
        this.fileTeleporters = list;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setPsdBundle(Bundle bundle) {
        this.psdBundle = bundle;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setPsdHasNoPii(boolean z) {
        this.psdHasNoPii = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setScreenshot(Bitmap bitmap) {
        this.screenshot = bitmap;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setSessionId(String str) {
        this.sessionId = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setStartTickNanos(long j) {
        this.startTickNanos = j;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedbackOptions setThemeSettings(ThemeSettings themeSettings) {
        this.themeSettings = themeSettings;
        return this;
    }

    @Deprecated
    public String getAccountInUse() {
        return this.accountInUse;
    }

    @Deprecated
    public BaseFeedbackProductSpecificData getAsyncPsd() {
        return this.asyncFeedbackPsd;
    }

    @Deprecated
    public BitmapTeleporter getBitmapTeleporter() {
        return this.bitmapTeleporter;
    }

    @Deprecated
    public String getCategoryTag() {
        return this.categoryTag;
    }

    @Deprecated
    public ApplicationErrorReport.CrashInfo getCrashInfo() {
        return this.applicationErrorReport.crashInfo;
    }

    @Deprecated
    public String getDescription() {
        return this.description;
    }

    @Deprecated
    public boolean getExcludePii() {
        return this.excludePii;
    }

    @Deprecated
    public List getFileTeleporters() {
        return this.fileTeleporters;
    }

    @Deprecated
    public boolean getIsSilentSend() {
        return this.isSilentSend;
    }

    @Deprecated
    public LogOptions getLogOptions() {
        return this.logOptions;
    }

    @Deprecated
    public String getPackageName() {
        return this.packageName;
    }

    @Deprecated
    public BaseFeedbackProductSpecificData getPsd() {
        return getAsyncPsd();
    }

    @Deprecated
    public Bundle getPsdBundle() {
        return this.psdBundle;
    }

    @Deprecated
    public boolean getPsdHasNoPii() {
        return this.psdHasNoPii;
    }

    @Deprecated
    public Bitmap getScreenshot() {
        return this.screenshot;
    }

    @Deprecated
    public String getSessionId() {
        return this.sessionId;
    }

    @Deprecated
    public ThemeSettings getThemeSettings() {
        return this.themeSettings;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        FeedbackOptionsCreator.writeToParcel(this, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FeedbackOptions(String str, Bundle bundle, String str2, ApplicationErrorReport applicationErrorReport, String str3, BitmapTeleporter bitmapTeleporter, String str4, List list, boolean z, ThemeSettings themeSettings, LogOptions logOptions, boolean z2, Bitmap bitmap, String str5, boolean z3, long j, boolean z4) {
        this.accountInUse = str;
        this.psdBundle = bundle != null ? bundle : new Bundle();
        this.description = str2;
        this.applicationErrorReport = applicationErrorReport != null ? applicationErrorReport : new ApplicationErrorReport();
        this.categoryTag = str3;
        this.bitmapTeleporter = bitmapTeleporter;
        this.packageName = str4;
        this.fileTeleporters = list != null ? list : new ArrayList();
        this.excludePii = z;
        this.themeSettings = themeSettings;
        this.logOptions = logOptions;
        this.psdHasNoPii = z2;
        this.screenshot = bitmap;
        this.sessionId = str5;
        this.isSilentSend = z3;
        this.startTickNanos = j;
        this.enableDynamicColor = z4;
    }
}
