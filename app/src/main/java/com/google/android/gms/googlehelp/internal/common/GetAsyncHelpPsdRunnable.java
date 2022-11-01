package com.google.android.gms.googlehelp.internal.common;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.feedback.internal.common.FeedbackUtils;
import com.google.android.gms.feedback.internal.common.Stopwatch;
import com.google.android.gms.googlehelp.BaseHelpProductSpecificData;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.Help;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GetAsyncHelpPsdRunnable implements Runnable {
    private final Context context;
    private final GoogleHelp googleHelp;
    private final BaseHelpProductSpecificData psd;
    private final long startTickNanos;

    public GetAsyncHelpPsdRunnable(Context context, GoogleHelp googleHelp, BaseHelpProductSpecificData baseHelpProductSpecificData, long j) {
        this.context = context;
        this.googleHelp = googleHelp;
        this.psd = baseHelpProductSpecificData;
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
            listOf = this.psd.getAsyncHelpPsd();
            if (listOf == null) {
                listOf = new ArrayList(1);
            }
            try {
                listOf.add(Pair.create("gms:googlehelp:async_help_psd_collection_time_ms", String.valueOf(createStopwatch.elapsedMillis())));
            } catch (UnsupportedOperationException e) {
                ArrayList arrayList = new ArrayList(listOf);
                arrayList.add(Pair.create("gms:googlehelp:async_help_psd_collection_time_ms", String.valueOf(createStopwatch.elapsedMillis())));
                listOf = arrayList;
            }
        } catch (Exception e2) {
            Log.w("gH_GetAsyncHelpPsd", "Failed to get async help psd.", e2);
            listOf = CollectionUtils.listOf(Pair.create("gms:googlehelp:async_help_psd_failure", "exception"));
        }
        Help.getClient(this.context).saveAsyncHelpPsd(this.googleHelp, FeedbackUtils.createPsdBundle(listOf), this.startTickNanos);
    }
}
