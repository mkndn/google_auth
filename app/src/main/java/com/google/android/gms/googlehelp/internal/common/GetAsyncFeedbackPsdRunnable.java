package com.google.android.gms.googlehelp.internal.common;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.feedback.BaseFeedbackProductSpecificData;
import com.google.android.gms.feedback.internal.common.FeedbackUtils;
import com.google.android.gms.feedback.internal.common.Stopwatch;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.Help;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GetAsyncFeedbackPsdRunnable implements Runnable {
    private final Context context;
    private final GoogleHelp googleHelp;
    private final BaseFeedbackProductSpecificData psd;
    private final long startTickNanos;

    public GetAsyncFeedbackPsdRunnable(Context context, GoogleHelp googleHelp, BaseFeedbackProductSpecificData baseFeedbackProductSpecificData, long j) {
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
        List listOf;
        try {
            Stopwatch createStopwatch = createStopwatch();
            createStopwatch.start();
            listOf = this.psd.getAsyncFeedbackPsd();
            if (listOf == null) {
                listOf = new ArrayList(1);
            }
            try {
                listOf.add(Pair.create("gms:feedback:async_feedback_psd_collection_time_ms", String.valueOf(createStopwatch.elapsedMillis())));
            } catch (UnsupportedOperationException e) {
                ArrayList arrayList = new ArrayList(listOf);
                arrayList.add(Pair.create("gms:feedback:async_feedback_psd_collection_time_ms", String.valueOf(createStopwatch.elapsedMillis())));
                listOf = arrayList;
            }
        } catch (Exception e2) {
            Log.w("gH_GetAsyncFeedbackPsd", "Failed to get async Feedback psd.", e2);
            listOf = CollectionUtils.listOf(Pair.create("gms:feedback:async_feedback_psd_failure", "exception"));
        }
        Help.getClient(this.context).saveAsyncFeedbackPsd(this.googleHelp, FeedbackUtils.createPsdBundle(listOf), this.startTickNanos);
    }
}
