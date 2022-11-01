package com.google.android.gms.googlehelp.internal.common;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.feedback.internal.common.Stopwatch;
import com.google.android.gms.googlehelp.BaseHelpProductSpecificData;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.GoogleHelpAccessor;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GetSyncHelpPsdRunnable implements Runnable {
    private final SyncHelpPsdCallback callback;
    private final GoogleHelp googleHelp;
    private boolean isCallbackLocked;
    private final BaseHelpProductSpecificData psd;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface SyncHelpPsdCallback {
        void onSyncHelpPsdCollected(GoogleHelp googleHelp);
    }

    public GetSyncHelpPsdRunnable(GoogleHelp googleHelp, BaseHelpProductSpecificData baseHelpProductSpecificData, SyncHelpPsdCallback syncHelpPsdCallback) {
        this.googleHelp = googleHelp;
        this.psd = baseHelpProductSpecificData;
        this.callback = syncHelpPsdCallback;
    }

    synchronized boolean acquireCallbackLock() {
        if (this.isCallbackLocked) {
            return false;
        }
        this.isCallbackLocked = true;
        return true;
    }

    Stopwatch createStopwatch() {
        return new Stopwatch();
    }

    Handler createSyncHelpPsdTimeoutHandler() {
        return new TracingHandler(Looper.getMainLooper());
    }

    Runnable createSyncHelpPsdTimeoutRunnable() {
        return new Runnable() { // from class: com.google.android.gms.googlehelp.internal.common.GetSyncHelpPsdRunnable.1
            @Override // java.lang.Runnable
            public void run() {
                if (!GetSyncHelpPsdRunnable.this.acquireCallbackLock()) {
                    return;
                }
                Log.w("gH_GetSyncHelpPsd", "Getting sync help psd timed out.");
                new GoogleHelpAccessor(GetSyncHelpPsdRunnable.this.googleHelp).setSyncPsd(CollectionUtils.mutableListOfWithSize(1, Pair.create("gms:googlehelp:sync_help_psd_failure", "timeout")));
                GetSyncHelpPsdRunnable.this.callback.onSyncHelpPsdCollected(GetSyncHelpPsdRunnable.this.googleHelp);
            }
        };
    }

    @Override // java.lang.Runnable
    public void run() {
        List mutableListOf;
        this.isCallbackLocked = false;
        Handler createSyncHelpPsdTimeoutHandler = createSyncHelpPsdTimeoutHandler();
        Runnable createSyncHelpPsdTimeoutRunnable = createSyncHelpPsdTimeoutRunnable();
        createSyncHelpPsdTimeoutHandler.postDelayed(createSyncHelpPsdTimeoutRunnable, new GoogleHelpAccessor(this.googleHelp).getSyncHelpPsdTimeoutMs());
        try {
            Stopwatch createStopwatch = createStopwatch();
            createStopwatch.start();
            mutableListOf = this.psd.getSyncHelpPsd();
            if (mutableListOf == null) {
                mutableListOf = new ArrayList(1);
            }
            try {
                mutableListOf.add(Pair.create("gms:googlehelp:sync_help_psd_collection_time_ms", String.valueOf(createStopwatch.elapsedMillis())));
            } catch (UnsupportedOperationException e) {
                ArrayList arrayList = new ArrayList(mutableListOf);
                arrayList.add(Pair.create("gms:googlehelp:sync_help_psd_collection_time_ms", String.valueOf(createStopwatch.elapsedMillis())));
                mutableListOf = arrayList;
            }
        } catch (Exception e2) {
            Log.w("gH_GetSyncHelpPsd", "Failed to get sync help psd.", e2);
            mutableListOf = CollectionUtils.mutableListOf(Pair.create("gms:googlehelp:sync_help_psd_failure", "exception"));
        }
        if (acquireCallbackLock()) {
            createSyncHelpPsdTimeoutHandler.removeCallbacks(createSyncHelpPsdTimeoutRunnable);
            new GoogleHelpAccessor(this.googleHelp).setSyncPsd(mutableListOf);
            this.callback.onSyncHelpPsdCollected(this.googleHelp);
        }
    }
}
