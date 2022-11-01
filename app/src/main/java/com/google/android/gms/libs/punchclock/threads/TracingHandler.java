package com.google.android.gms.libs.punchclock.threads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TracingHandler extends Handler {
    private static Propagator propagator = null;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Propagator {
        public static final int UPTIME_MILLIS_IMMEDIATE = 0;

        void afterDispatch(TracingHandler tracingHandler, Message message, Object obj);

        Object beforeDispatch(TracingHandler tracingHandler, Message message);

        void handleThrowable(TracingHandler tracingHandler, Throwable th, Object obj);

        void prepare(TracingHandler tracingHandler, Message message, long j);
    }

    public TracingHandler() {
    }

    private void prepare(Message message, long j) {
        Propagator propagator2 = propagator;
        if (propagator2 != null) {
            propagator2.prepare(this, message, j);
        }
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        Propagator propagator2 = propagator;
        if (propagator2 == null) {
            dispatchMessageTraced(message);
            return;
        }
        Object beforeDispatch = propagator2.beforeDispatch(this, message);
        try {
            dispatchMessageTraced(message);
        } catch (Throwable th) {
            try {
                propagator2.handleThrowable(this, th, beforeDispatch);
                throw th;
            } finally {
                propagator2.afterDispatch(this, message, beforeDispatch);
            }
        }
    }

    protected void dispatchMessageTraced(Message message) {
        super.dispatchMessage(message);
    }

    @Override // android.os.Handler
    public boolean sendMessageAtTime(Message message, long j) {
        prepare(message, j);
        return super.sendMessageAtTime(message, j);
    }

    public TracingHandler(Looper looper) {
        super(looper);
    }

    public TracingHandler(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
