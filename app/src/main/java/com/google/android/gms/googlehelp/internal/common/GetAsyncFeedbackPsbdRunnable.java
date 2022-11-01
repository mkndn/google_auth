package com.google.android.gms.googlehelp.internal.common;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.feedback.BaseFeedbackProductSpecificData;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.feedback.FileTeleporter;
import com.google.android.gms.feedback.internal.common.Stopwatch;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.Help;
import java.io.File;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GetAsyncFeedbackPsbdRunnable implements Runnable {
    private final Context context;
    private final GoogleHelp googleHelp;
    private final BaseFeedbackProductSpecificData psd;
    private final long startTickNanos;

    public GetAsyncFeedbackPsbdRunnable(Context context, GoogleHelp googleHelp, BaseFeedbackProductSpecificData baseFeedbackProductSpecificData, long j) {
        this.context = context;
        this.googleHelp = googleHelp;
        this.psd = baseFeedbackProductSpecificData;
        this.startTickNanos = j;
    }

    Stopwatch createStopwatch() {
        return new Stopwatch();
    }

    @Override // java.lang.Runnable
    public void run() {
        List<FileTeleporter> list;
        Bundle bundle = new Bundle(1);
        try {
            Stopwatch createStopwatch = createStopwatch();
            createStopwatch.start();
            list = this.psd.getAsyncFeedbackPsbd();
            File cacheDir = this.context.getCacheDir();
            if (list != null && !list.isEmpty() && cacheDir != null) {
                for (FileTeleporter fileTeleporter : list) {
                    fileTeleporter.setTempDir(cacheDir);
                }
            }
            bundle.putString("gms:feedback:async_feedback_psbd_collection_time_ms", String.valueOf(createStopwatch.elapsedMillis()));
        } catch (Exception e) {
            Log.w("gH_GetAsyncFeedbackPsbd", "Failed to get async Feedback psbd.", e);
            bundle.putString("gms:feedback:async_feedback_psbd_failure", "exception");
            list = null;
        }
        Help.getClient(this.context).saveAsyncFeedbackPsbd(this.googleHelp, FeedbackOptions.newInstanceForAsyncPsbd(list), bundle, this.startTickNanos);
    }
}
