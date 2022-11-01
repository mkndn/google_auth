package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.libs.punchclock.threads.Propagation;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BasePendingResult extends PendingResult {
    static final ThreadLocal sTransformRunning = new ThreadLocal() { // from class: com.google.android.gms.common.api.internal.BasePendingResult.1
        @Override // java.lang.ThreadLocal
        public Boolean initialValue() {
            return false;
        }
    };
    protected final WeakReference mApiClient;
    private ResultCallback mCallback;
    private ICancelToken mCancelToken;
    private boolean mCanceled;
    private volatile boolean mConsumed;
    private final AtomicReference mConsumedCallback;
    private boolean mForcedFailure;
    protected final CallbackHandler mHandler;
    private boolean mIsInChain;
    private final CountDownLatch mLatch;
    private Result mResult;
    private ReleasableResultGuardian mResultGuardian;
    private Status mStatus;
    private final ArrayList mStatusListeners;
    private final Object mSyncToken;
    private volatile TransformedResultImpl mTransformRoot;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ReleasableResultGuardian {
        private ReleasableResultGuardian() {
            BasePendingResult.this = r1;
        }

        protected void finalize() {
            BasePendingResult.maybeReleaseResult(BasePendingResult.this.mResult);
            super.finalize();
        }
    }

    @Deprecated
    BasePendingResult() {
        this.mSyncToken = new Object();
        this.mLatch = new CountDownLatch(1);
        this.mStatusListeners = new ArrayList();
        this.mConsumedCallback = new AtomicReference();
        this.mIsInChain = false;
        this.mHandler = new CallbackHandler(Looper.getMainLooper());
        this.mApiClient = new WeakReference(null);
    }

    private Result get() {
        Result result;
        synchronized (this.mSyncToken) {
            Preconditions.checkState(!this.mConsumed, "Result has already been consumed.");
            Preconditions.checkState(isReady(), "Result is not ready.");
            result = this.mResult;
            this.mResult = null;
            this.mCallback = null;
            this.mConsumed = true;
        }
        onResultConsumed();
        return (Result) Preconditions.checkNotNull(result);
    }

    public static /* synthetic */ void lambda$addStatusListener$2(PendingResult.StatusListener statusListener, Status status) {
        statusListener.onComplete(status);
    }

    public static /* synthetic */ void lambda$addStatusListener$3(Propagation.TraceResumer traceResumer, final PendingResult.StatusListener statusListener, final Status status) {
        traceResumer.run(new Runnable() { // from class: com.google.android.gms.common.api.internal.BasePendingResult$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BasePendingResult.lambda$addStatusListener$2(PendingResult.StatusListener.this, status);
            }
        });
    }

    public static /* synthetic */ void lambda$traceResultCallback$0(ResultCallback resultCallback, Result result) {
        resultCallback.onResult(result);
    }

    public static /* synthetic */ void lambda$traceResultCallback$1(Propagation.TraceResumer traceResumer, final ResultCallback resultCallback, final Result result) {
        traceResumer.run(new Runnable() { // from class: com.google.android.gms.common.api.internal.BasePendingResult$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                BasePendingResult.lambda$traceResultCallback$0(ResultCallback.this, result);
            }
        });
    }

    public static void maybeReleaseResult(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                Log.w("BasePendingResult", "Unable to release " + String.valueOf(result), e);
            }
        }
    }

    private void onResultConsumed() {
        UnconsumedApiCalls$ResultConsumedCallback unconsumedApiCalls$ResultConsumedCallback = (UnconsumedApiCalls$ResultConsumedCallback) this.mConsumedCallback.getAndSet(null);
        if (unconsumedApiCalls$ResultConsumedCallback != null) {
            unconsumedApiCalls$ResultConsumedCallback.onConsumed(this);
        }
    }

    private void setResultAndNotifyListeners(Result result) {
        this.mResult = result;
        this.mStatus = result.getStatus();
        this.mCancelToken = null;
        this.mLatch.countDown();
        if (this.mCanceled) {
            this.mCallback = null;
        } else {
            ResultCallback resultCallback = this.mCallback;
            if (resultCallback == null) {
                if (this.mResult instanceof Releasable) {
                    this.mResultGuardian = new ReleasableResultGuardian();
                }
            } else {
                this.mHandler.removeTimeoutMessages();
                this.mHandler.sendResultCallback(resultCallback, get());
            }
        }
        Iterator it = this.mStatusListeners.iterator();
        while (it.hasNext()) {
            ((PendingResult.StatusListener) it.next()).onComplete(this.mStatus);
        }
        this.mStatusListeners.clear();
    }

    public static ResultCallback traceResultCallback(final ResultCallback resultCallback) {
        if (BuildConstants.IS_PACKAGE_SIDE && resultCallback != null) {
            final Propagation.TraceResumer strongTrace = Propagation.CC.getInstance().getStrongTrace();
            return new ResultCallback() { // from class: com.google.android.gms.common.api.internal.BasePendingResult$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.common.api.ResultCallback
                public final void onResult(Result result) {
                    BasePendingResult.lambda$traceResultCallback$1(Propagation.TraceResumer.this, resultCallback, result);
                }
            };
        }
        return resultCallback;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(final PendingResult.StatusListener statusListener) {
        Preconditions.checkArgument(statusListener != null, "Callback cannot be null.");
        synchronized (this.mSyncToken) {
            if (isReady()) {
                statusListener.onComplete(this.mStatus);
            } else if (BuildConstants.IS_PACKAGE_SIDE) {
                final Propagation.TraceResumer strongTrace = Propagation.CC.getInstance().getStrongTrace();
                this.mStatusListeners.add(new PendingResult.StatusListener() { // from class: com.google.android.gms.common.api.internal.BasePendingResult$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
                    public final void onComplete(Status status) {
                        BasePendingResult.lambda$addStatusListener$3(Propagation.TraceResumer.this, statusListener, status);
                    }
                });
            } else {
                this.mStatusListeners.add(statusListener);
            }
        }
    }

    boolean allowMultipleSetResult() {
        return false;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final Result await(long j, TimeUnit timeUnit) {
        if (j > 0) {
            Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
        }
        Preconditions.checkState(!this.mConsumed, "Result has already been consumed.");
        Preconditions.checkState(this.mTransformRoot == null, "Cannot await if then() has been called.");
        try {
            if (!this.mLatch.await(j, timeUnit)) {
                forceFailureUnlessReady(Status.RESULT_TIMEOUT);
            }
        } catch (InterruptedException e) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return get();
    }

    public abstract Result createFailedResult(Status status);

    @Deprecated
    public final void forceFailureUnlessReady(Status status) {
        synchronized (this.mSyncToken) {
            if (!isReady()) {
                setResult(createFailedResult(status));
                this.mForcedFailure = true;
            }
        }
    }

    public final boolean isReady() {
        return this.mLatch.getCount() == 0;
    }

    public void maybeMarkChain() {
        this.mIsInChain = this.mIsInChain || ((Boolean) sTransformRunning.get()).booleanValue();
    }

    public final void setResult(Result result) {
        synchronized (this.mSyncToken) {
            if (!this.mForcedFailure && !this.mCanceled && (!isReady() || !allowMultipleSetResult())) {
                boolean z = true;
                Preconditions.checkState(!isReady(), "Results have already been set");
                if (this.mConsumed) {
                    z = false;
                }
                Preconditions.checkState(z, "Result has already been consumed");
                setResultAndNotifyListeners(result);
                return;
            }
            maybeReleaseResult(result);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CallbackHandler extends TracingHandler {
        public static final int CALLBACK_ON_COMPLETE = 1;
        public static final int CALLBACK_ON_TIMEOUT = 2;

        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        protected void deliverResultCallback(ResultCallback resultCallback, Result result) {
            try {
                resultCallback.onResult(result);
            } catch (RuntimeException e) {
                BasePendingResult.maybeReleaseResult(result);
                throw e;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    deliverResultCallback((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((BasePendingResult) message.obj).forceFailureUnlessReady(Status.RESULT_TIMEOUT);
                    return;
                default:
                    Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                    return;
            }
        }

        public void removeTimeoutMessages() {
            removeMessages(2);
        }

        public void sendResultCallback(ResultCallback resultCallback, Result result) {
            sendMessage(obtainMessage(1, new Pair((ResultCallback) Preconditions.checkNotNull(BasePendingResult.traceResultCallback(resultCallback)), result)));
        }

        public CallbackHandler(Looper looper) {
            super(looper);
        }
    }

    public BasePendingResult(GoogleApiClient googleApiClient) {
        this.mSyncToken = new Object();
        this.mLatch = new CountDownLatch(1);
        this.mStatusListeners = new ArrayList();
        this.mConsumedCallback = new AtomicReference();
        this.mIsInChain = false;
        this.mHandler = new CallbackHandler(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.mApiClient = new WeakReference(googleApiClient);
    }
}
