package com.google.android.gms.googlehelp.internal.common;

import android.content.Context;
import com.google.android.gms.feedback.BaseFeedbackProductSpecificData;
import com.google.android.gms.googlehelp.BaseHelpProductSpecificData;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.GoogleHelpAccessor;
import com.google.android.gms.googlehelp.internal.common.GetSyncHelpPsdRunnable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PsdCollector {
    private final GoogleHelp googleHelp;
    private final RunnableFactory runnableFactory;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class DefaultRunnableFactory implements RunnableFactory {
        private DefaultRunnableFactory() {
        }

        @Override // com.google.android.gms.googlehelp.internal.common.PsdCollector.RunnableFactory
        public GetAsyncFeedbackPsbdRunnable createGetAsyncFeedbackPsbdRunnable(Context context, GoogleHelp googleHelp, BaseFeedbackProductSpecificData baseFeedbackProductSpecificData, long j) {
            return new GetAsyncFeedbackPsbdRunnable(context, googleHelp, baseFeedbackProductSpecificData, j);
        }

        @Override // com.google.android.gms.googlehelp.internal.common.PsdCollector.RunnableFactory
        public GetAsyncFeedbackPsdRunnable createGetAsyncFeedbackPsdRunnable(Context context, GoogleHelp googleHelp, BaseFeedbackProductSpecificData baseFeedbackProductSpecificData, long j) {
            return new GetAsyncFeedbackPsdRunnable(context, googleHelp, baseFeedbackProductSpecificData, j);
        }

        @Override // com.google.android.gms.googlehelp.internal.common.PsdCollector.RunnableFactory
        public GetAsyncHelpPsdRunnable createGetAsyncHelpPsdRunnable(Context context, GoogleHelp googleHelp, BaseHelpProductSpecificData baseHelpProductSpecificData, long j) {
            return new GetAsyncHelpPsdRunnable(context, googleHelp, baseHelpProductSpecificData, j);
        }

        @Override // com.google.android.gms.googlehelp.internal.common.PsdCollector.RunnableFactory
        public GetSyncHelpPsdRunnable createGetSyncHelpPsdRunnable(GoogleHelp googleHelp, BaseHelpProductSpecificData baseHelpProductSpecificData, GetSyncHelpPsdRunnable.SyncHelpPsdCallback syncHelpPsdCallback) {
            return new GetSyncHelpPsdRunnable(googleHelp, baseHelpProductSpecificData, syncHelpPsdCallback);
        }

        @Override // com.google.android.gms.googlehelp.internal.common.PsdCollector.RunnableFactory
        public Thread createThread(Runnable runnable) {
            return new Thread(runnable, "PsdCollector");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface RunnableFactory {
        GetAsyncFeedbackPsbdRunnable createGetAsyncFeedbackPsbdRunnable(Context context, GoogleHelp googleHelp, BaseFeedbackProductSpecificData baseFeedbackProductSpecificData, long j);

        GetAsyncFeedbackPsdRunnable createGetAsyncFeedbackPsdRunnable(Context context, GoogleHelp googleHelp, BaseFeedbackProductSpecificData baseFeedbackProductSpecificData, long j);

        GetAsyncHelpPsdRunnable createGetAsyncHelpPsdRunnable(Context context, GoogleHelp googleHelp, BaseHelpProductSpecificData baseHelpProductSpecificData, long j);

        GetSyncHelpPsdRunnable createGetSyncHelpPsdRunnable(GoogleHelp googleHelp, BaseHelpProductSpecificData baseHelpProductSpecificData, GetSyncHelpPsdRunnable.SyncHelpPsdCallback syncHelpPsdCallback);

        Thread createThread(Runnable runnable);
    }

    public PsdCollector(GoogleHelp googleHelp) {
        this(googleHelp, new DefaultRunnableFactory());
    }

    private void startThread(Runnable runnable, int i) {
        Thread createThread = this.runnableFactory.createThread(runnable);
        createThread.setPriority(i);
        createThread.start();
    }

    public void getAsyncPsds(Context context, BaseFeedbackProductSpecificData baseFeedbackProductSpecificData, BaseHelpProductSpecificData baseHelpProductSpecificData, long j) {
        GoogleHelpAccessor googleHelpAccessor = new GoogleHelpAccessor(this.googleHelp);
        if (baseHelpProductSpecificData != null) {
            googleHelpAccessor.setHasHelpPsd(true);
            startThread(this.runnableFactory.createGetAsyncHelpPsdRunnable(context, this.googleHelp, baseHelpProductSpecificData, j), 4);
        }
        if (baseFeedbackProductSpecificData != null) {
            googleHelpAccessor.setHasFeedbackPsd(true);
            startThread(this.runnableFactory.createGetAsyncFeedbackPsbdRunnable(context, this.googleHelp, baseFeedbackProductSpecificData, j), 4);
            startThread(this.runnableFactory.createGetAsyncFeedbackPsdRunnable(context, this.googleHelp, baseFeedbackProductSpecificData, j), 4);
        }
    }

    public void getSyncHelpPsd(BaseHelpProductSpecificData baseHelpProductSpecificData, GetSyncHelpPsdRunnable.SyncHelpPsdCallback syncHelpPsdCallback) {
        if (baseHelpProductSpecificData == null) {
            syncHelpPsdCallback.onSyncHelpPsdCollected(this.googleHelp);
        } else {
            startThread(this.runnableFactory.createGetSyncHelpPsdRunnable(this.googleHelp, baseHelpProductSpecificData, syncHelpPsdCallback), 10);
        }
    }

    public PsdCollector(GoogleHelp googleHelp, RunnableFactory runnableFactory) {
        this.googleHelp = googleHelp;
        this.runnableFactory = runnableFactory;
    }
}
