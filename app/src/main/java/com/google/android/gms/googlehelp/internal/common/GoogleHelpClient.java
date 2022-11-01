package com.google.android.gms.googlehelp.internal.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.GoogleHelpApi;
import com.google.android.gms.googlehelp.Help;
import com.google.android.gms.tasks.Task;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleHelpClient extends GoogleApi {
    static GoogleHelpApi googleHelpApi = new GoogleHelpApiImpl();
    private final Activity callingActivity;

    public GoogleHelpClient(Activity activity) {
        super(activity, Help.API, (Api.ApiOptions) null, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.callingActivity = activity;
    }

    public Task saveAsyncFeedbackPsbd(GoogleHelp googleHelp, FeedbackOptions feedbackOptions, Bundle bundle, long j) {
        return PendingResultUtil.toVoidTask(googleHelpApi.saveAsyncFeedbackPsbd(asGoogleApiClient(), googleHelp, feedbackOptions, bundle, j));
    }

    public Task saveAsyncFeedbackPsd(GoogleHelp googleHelp, Bundle bundle, long j) {
        return PendingResultUtil.toVoidTask(googleHelpApi.saveAsyncFeedbackPsd(asGoogleApiClient(), googleHelp, bundle, j));
    }

    public Task saveAsyncHelpPsd(GoogleHelp googleHelp, Bundle bundle, long j) {
        return PendingResultUtil.toVoidTask(googleHelpApi.saveAsyncHelpPsd(asGoogleApiClient(), googleHelp, bundle, j));
    }

    public Task startHelp(Intent intent) {
        Preconditions.checkNotNull(this.callingActivity);
        return PendingResultUtil.toVoidTask(googleHelpApi.startHelp(asGoogleApiClient(), this.callingActivity, intent));
    }

    public GoogleHelpClient(Context context) {
        super(context, Help.API, (Api.ApiOptions) null, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.callingActivity = null;
    }
}
