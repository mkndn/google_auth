package com.google.android.apps.authenticator.timesync;

import android.util.Log;
import com.google.android.apps.authenticator.otp.TotpClock;
import com.google.android.apps.authenticator.util.concurrent.RunOnThisLooperThreadExecutor;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SyncNowController {
    private static final String LOG_TAG = "TimeSync";
    private final Executor mBackgroundExecutor;
    private final boolean mBackgroundExecutorServiceOwnedByThisController;
    private final Executor mCallbackFromBackgroundExecutor;
    private final NetworkTimeProvider mNetworkTimeProvider;
    private Presenter mPresenter;
    private Result mResult;
    private State mState;
    private final TotpClock mTotpClock;

    /* compiled from: PG */
    /* renamed from: com.google.android.apps.authenticator.timesync.SyncNowController$3  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$State = iArr;
            try {
                iArr[State.NOT_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$State[State.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$State[State.DONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Presenter {
        void onDone(Result result);

        void onStarted();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Result {
        TIME_CORRECTED,
        TIME_ALREADY_CORRECT,
        CANCELLED_BY_USER,
        ERROR_CONNECTIVITY_ISSUE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum State {
        NOT_STARTED,
        IN_PROGRESS,
        DONE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(Result result) {
        if (this.mState == State.DONE) {
            return;
        }
        if (this.mBackgroundExecutorServiceOwnedByThisController) {
            ((ExecutorService) this.mBackgroundExecutor).shutdownNow();
        }
        this.mState = State.DONE;
        this.mResult = result;
        Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.onDone(result);
        }
    }

    private void onCancelledByUser() {
        finish(Result.CANCELLED_BY_USER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNewTimeCorrectionObtained(int i) {
        if (this.mState != State.IN_PROGRESS) {
            return;
        }
        long timeCorrectionMinutes = this.mTotpClock.getTimeCorrectionMinutes();
        Log.i(LOG_TAG, "Obtained new time correction: " + i + " min, old time correction: " + timeCorrectionMinutes + " min");
        if (i == timeCorrectionMinutes) {
            finish(Result.TIME_ALREADY_CORRECT);
            return;
        }
        this.mTotpClock.setTimeCorrectionMinutes(i);
        finish(Result.TIME_CORRECTED);
    }

    private void start() {
        this.mState = State.IN_PROGRESS;
        Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.onStarted();
        }
        ListenableFutureTask create = ListenableFutureTask.create(new Callable() { // from class: com.google.android.apps.authenticator.timesync.SyncNowController.1
            @Override // java.util.concurrent.Callable
            public Integer call() {
                double networkTime = SyncNowController.this.mNetworkTimeProvider.getNetworkTime() - SyncNowController.this.mTotpClock.getSystemWallClock().nowMillis();
                Double.isNaN(networkTime);
                return Integer.valueOf((int) Math.round(networkTime / 60000.0d));
            }
        });
        Futures.addCallback(create, new FutureCallback() { // from class: com.google.android.apps.authenticator.timesync.SyncNowController.2
            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                if (th instanceof IOException) {
                    Log.w(SyncNowController.LOG_TAG, "Failed to obtain network time due to connectivity issues");
                    SyncNowController.this.finish(Result.ERROR_CONNECTIVITY_ISSUE);
                    return;
                }
                Throwables.throwIfUnchecked(th);
                throw new RuntimeException(th);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(Integer num) {
                SyncNowController.this.onNewTimeCorrectionObtained(num.intValue());
            }
        }, this.mCallbackFromBackgroundExecutor);
        this.mBackgroundExecutor.execute(create);
    }

    public void abort(Presenter presenter) {
        if (this.mPresenter != presenter) {
            return;
        }
        onCancelledByUser();
    }

    public void attach(Presenter presenter) {
        this.mPresenter = presenter;
        switch (AnonymousClass3.$SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$State[this.mState.ordinal()]) {
            case 1:
                start();
                return;
            case 2:
                Presenter presenter2 = this.mPresenter;
                if (presenter2 != null) {
                    presenter2.onStarted();
                    return;
                }
                return;
            case 3:
                Presenter presenter3 = this.mPresenter;
                if (presenter3 != null) {
                    presenter3.onDone(this.mResult);
                    return;
                }
                return;
            default:
                throw new IllegalStateException(String.valueOf(this.mState));
        }
    }

    public void detach(Presenter presenter) {
        if (presenter != this.mPresenter) {
            return;
        }
        switch (AnonymousClass3.$SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$State[this.mState.ordinal()]) {
            case 1:
            case 2:
                onCancelledByUser();
                return;
            case 3:
                return;
            default:
                throw new IllegalStateException(String.valueOf(this.mState));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SyncNowController(TotpClock totpClock, NetworkTimeProvider networkTimeProvider) {
        this(totpClock, networkTimeProvider, Executors.newSingleThreadExecutor(), true, new RunOnThisLooperThreadExecutor());
    }

    public SyncNowController(TotpClock totpClock, NetworkTimeProvider networkTimeProvider, Executor executor, boolean z, Executor executor2) {
        this.mState = State.NOT_STARTED;
        this.mTotpClock = totpClock;
        this.mNetworkTimeProvider = networkTimeProvider;
        this.mBackgroundExecutor = executor;
        this.mBackgroundExecutorServiceOwnedByThisController = z;
        this.mCallbackFromBackgroundExecutor = executor2;
    }
}
