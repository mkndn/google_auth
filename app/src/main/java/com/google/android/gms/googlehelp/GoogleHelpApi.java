package com.google.android.gms.googlehelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.feedback.FeedbackOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface GoogleHelpApi {
    PendingResult saveAsyncFeedbackPsbd(GoogleApiClient googleApiClient, GoogleHelp googleHelp, FeedbackOptions feedbackOptions, Bundle bundle, long j);

    PendingResult saveAsyncFeedbackPsd(GoogleApiClient googleApiClient, GoogleHelp googleHelp, Bundle bundle, long j);

    PendingResult saveAsyncHelpPsd(GoogleApiClient googleApiClient, GoogleHelp googleHelp, Bundle bundle, long j);

    PendingResult startHelp(GoogleApiClient googleApiClient, Activity activity, Intent intent);
}
