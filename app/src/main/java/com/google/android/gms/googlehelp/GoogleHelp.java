package com.google.android.gms.googlehelp;

import android.accounts.Account;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.feedback.BaseFeedbackProductSpecificData;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.feedback.FeedbackClient;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.feedback.ThemeSettings;
import com.google.android.gms.feedback.internal.common.FeedbackUtils;
import com.google.android.gms.googlehelp.internal.common.OverflowMenuItem;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleHelp extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new GoogleHelpCreator();
    public static final int OPEN_TO_CONTACT_OPTION_C2C = 2;
    public static final int OPEN_TO_CONTACT_OPTION_CHAT = 3;
    public static final int OPEN_TO_CONTACT_OPTION_EMAIL = 1;
    public static final int OPEN_TO_CONTACT_OPTION_NONE = 0;
    public static final int PIP_POS_BOTTOM_RIGHT = 0;
    public static final int PIP_POS_TOP_RIGHT = 1;
    boolean accountPickerEnabled;
    String apiDebugOption;
    String appContext;
    String appPackageNameOverride;
    @Deprecated
    Bitmap backupScreenshot;
    String callingPackageName;
    int clientVersion;
    Bitmap customCallingAppIcon;
    String customCallingAppLabel;
    PendingIntent customFeedbackPendingIntent;
    boolean dynamicColorEnabled;
    Uri fallbackSupportUri;
    ErrorReport feedbackErrorReport;
    private BaseFeedbackProductSpecificData feedbackPsd;
    @Deprecated
    Bundle feedbackPsdBundle;
    List frdPsds;
    Account googleAccount;
    boolean hasFeedbackPsd;
    boolean hasHelpPsd;
    @Deprecated
    int helpActivityTheme;
    private BaseHelpProductSpecificData helpPsd;
    boolean metricsReportingEnabled;
    ND4CSettings nd4cSettings;
    List offlineSuggestions;
    List overflowMenuItems;
    int pipInitPos;
    Bundle psdBundle;
    @Deprecated
    byte[] screenshotBytes;
    @Deprecated
    int screenshotHeight;
    @Deprecated
    int screenshotWidth;
    boolean searchEnabled;
    boolean sendFeedbackDisabled;
    String sessionId;
    boolean showContactCardFirst;
    List supportPhoneNumbers;
    int syncHelpPsdTimeoutMs;
    ThemeSettings themeSettings;
    TogglingData togglingData;
    final int versionCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleHelp(int i, String str, Account account, Bundle bundle, String str2, String str3, Bitmap bitmap, boolean z, boolean z2, List list, Bundle bundle2, Bitmap bitmap2, byte[] bArr, int i2, int i3, String str4, Uri uri, List list2, int i4, ThemeSettings themeSettings, List list3, boolean z3, ErrorReport errorReport, TogglingData togglingData, int i5, PendingIntent pendingIntent, int i6, boolean z4, boolean z5, int i7, String str5, boolean z6, String str6, boolean z7, ND4CSettings nD4CSettings, boolean z8, List list4) {
        this.feedbackErrorReport = new ErrorReport();
        if (TextUtils.isEmpty(str)) {
            if (!BuildConstants.APK_IS_DEBUG_APK && BuildConstants.IS_PACKAGE_SIDE) {
                Log.e("gH_GoogleHelp", "Help requires a non-empty appContext. Please fix ASAP.");
            } else {
                throw new IllegalStateException("Help requires a non-empty appContext");
            }
        }
        this.versionCode = i;
        this.clientVersion = i6;
        this.hasHelpPsd = z4;
        this.hasFeedbackPsd = z5;
        this.syncHelpPsdTimeoutMs = i7;
        this.sessionId = str5;
        this.appContext = str;
        this.googleAccount = account;
        this.psdBundle = bundle;
        this.callingPackageName = str2;
        this.customCallingAppLabel = str3;
        this.customCallingAppIcon = bitmap;
        this.searchEnabled = z;
        this.metricsReportingEnabled = z2;
        this.accountPickerEnabled = z6;
        this.supportPhoneNumbers = list;
        this.customFeedbackPendingIntent = pendingIntent;
        this.feedbackPsdBundle = bundle2;
        this.backupScreenshot = bitmap2;
        this.screenshotBytes = bArr;
        this.screenshotWidth = i2;
        this.screenshotHeight = i3;
        this.apiDebugOption = str4;
        this.fallbackSupportUri = uri;
        this.overflowMenuItems = list2;
        if (i < 4) {
            this.themeSettings = new ThemeSettings().setTheme(i4);
        } else {
            this.themeSettings = themeSettings == null ? new ThemeSettings() : themeSettings;
        }
        this.offlineSuggestions = list3;
        this.showContactCardFirst = z3;
        this.feedbackErrorReport = errorReport;
        if (errorReport != null) {
            errorReport.launcher = "GoogleHelp";
        }
        this.togglingData = togglingData;
        this.pipInitPos = i5;
        this.appPackageNameOverride = str6;
        this.sendFeedbackDisabled = z7;
        this.nd4cSettings = nD4CSettings;
        this.dynamicColorEnabled = z8;
        this.frdPsds = list4;
    }

    public static Bitmap getScreenshot(Activity activity) {
        return FeedbackClient.getScreenshot(activity);
    }

    public GoogleHelp addAdditionalOverflowMenuItem(int i, String str, Intent intent) {
        this.overflowMenuItems.add(new OverflowMenuItem(i, str, intent));
        return this;
    }

    public Intent buildHelpIntent() {
        return new Intent("com.google.android.gms.googlehelp.HELP").setPackage("com.google.android.gms").putExtra("EXTRA_GOOGLE_HELP", this);
    }

    public Uri getFallbackSupportUri() {
        return this.fallbackSupportUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseFeedbackProductSpecificData getFeedbackPsd() {
        return this.feedbackPsd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseHelpProductSpecificData getHelpPsd() {
        return this.helpPsd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSyncHelpPsdTimeoutMs() {
        return this.syncHelpPsdTimeoutMs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public TogglingData getTogglingData() {
        return this.togglingData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleHelp setClientVersion(int i) {
        this.clientVersion = i;
        return this;
    }

    public GoogleHelp setFallbackSupportUri(Uri uri) {
        this.fallbackSupportUri = uri;
        return this;
    }

    public GoogleHelp setFeedbackOptions(FeedbackOptions feedbackOptions, File file) {
        if (feedbackOptions != null) {
            this.feedbackPsd = feedbackOptions.getPsd();
        }
        ErrorReport errorReport = new ErrorReport(feedbackOptions, file);
        this.feedbackErrorReport = errorReport;
        errorReport.launcher = "GoogleHelp";
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleHelp setHasFeedbackPsd(boolean z) {
        this.hasFeedbackPsd = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleHelp setHasHelpPsd(boolean z) {
        this.hasHelpPsd = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleHelp setSyncPsd(List list) {
        this.psdBundle = FeedbackUtils.createPsdBundle(list);
        return this;
    }

    public GoogleHelp setThemeSettings(ThemeSettings themeSettings) {
        this.themeSettings = themeSettings;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        GoogleHelpCreator.writeToParcel(this, parcel, i);
    }

    @Deprecated
    public GoogleHelp(String str) {
        this(18, str, null, null, null, null, null, true, true, new ArrayList(), null, null, null, 0, 0, null, null, new ArrayList(), 3, null, new ArrayList(), false, new ErrorReport(), null, 0, null, -1, false, false, 200, null, false, null, false, null, false, new ArrayList());
    }
}
