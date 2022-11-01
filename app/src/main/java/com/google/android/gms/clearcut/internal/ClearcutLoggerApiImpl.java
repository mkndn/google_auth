package com.google.android.gms.clearcut.internal;

import android.content.Context;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.ClearcutLoggerApi;
import com.google.android.gms.clearcut.Features;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.clearcut.LogVerifier;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl;
import com.google.android.gms.clearcut.internal.LogEventQueue;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.protobuf.ByteString;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$LogEvent;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ClearcutLoggerApiImpl extends GoogleApi implements ClearcutLoggerApiPrivileged {
    private static final AtomicBoolean logEventRetryPending = new AtomicBoolean(false);
    private final Supplier logErrorQueueEnabledSupplier;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogEventMethodImpl extends BaseImplementation$ApiMethodImpl {
        private final ClearcutLogger.LogEventBuilder logEventBuilder;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class LogEventCallback extends DefaultClearcutLoggerCallbacks {
            private LogEventCallback() {
            }

            @Override // com.google.android.gms.clearcut.internal.DefaultClearcutLoggerCallbacks, com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks
            public void onLogEvent(Status status) {
                LogEventMethodImpl.this.setResult(status);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class RetryCallback extends DefaultClearcutLoggerCallbacks {
            private final LogEventQueue.Entry entry;

            private RetryCallback(LogEventQueue.Entry entry) {
                this.entry = entry;
            }

            @Override // com.google.android.gms.clearcut.internal.DefaultClearcutLoggerCallbacks, com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks
            public void onLogEvent(Status status) {
                try {
                    boolean z = true;
                    if (status.isSuccess()) {
                        LogEventQueue.getInstance().poll();
                        LogEventMethodImpl.this.retryNextQueuedEvent();
                    } else {
                        if (LogEventQueue.getInstance().retry() != null) {
                            LogErrorQueue.getInstance().add(new LogErrorParcelable(this.entry.event.playLoggerContext.logSourceName, this.entry.code, 1));
                        }
                        z = false;
                    }
                    if (z) {
                    }
                } finally {
                    ClearcutLoggerApiImpl.logEventRetryPending.set(false);
                }
            }
        }

        LogEventMethodImpl(ClearcutLogger.LogEventBuilder logEventBuilder, GoogleApiClient googleApiClient) {
            super(ClearcutLogger.API, googleApiClient);
            this.logEventBuilder = logEventBuilder;
        }

        private void handleLogEventException(Exception exc, LogEventParcelable logEventParcelable) {
            Log.w("ClearcutLoggerApiImpl", "logEvent exception", exc);
            if (!(ClearcutClientFlags.eventQueueEnabled && LogEventQueue.getInstance().add(logEventParcelable, LogErrorParcelable.LOG_EVENT_UNCHECKED_EXCEPTION)) && ((Boolean) ClearcutLoggerApiImpl.this.logErrorQueueEnabledSupplier.get()).booleanValue()) {
                LogErrorQueue.getInstance().add(new LogErrorParcelable(logEventParcelable.playLoggerContext.logSourceName, LogErrorParcelable.LOG_EVENT_UNCHECKED_EXCEPTION, 1));
            }
        }

        private void retryFirstQueuedEvent() {
            if (ClearcutLoggerApiImpl.logEventRetryPending.getAndSet(true)) {
                return;
            }
            retryNextQueuedEvent();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void retryNextQueuedEvent() {
            final LogEventQueue.Entry peek = LogEventQueue.getInstance().peek();
            if (peek == null) {
                ClearcutLoggerApiImpl.logEventRetryPending.set(false);
            } else {
                ClearcutLoggerApiImpl.this.doBestEffortWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl$LogEventMethodImpl$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.common.api.internal.RemoteCall
                    public final void accept(Object obj, Object obj2) {
                        ClearcutLoggerApiImpl.LogEventMethodImpl.this.m128x8314cb35(peek, (ClearcutLoggerClientImpl) obj, (TaskCompletionSource) obj2);
                    }
                }).build());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
        public void doExecute(ClearcutLoggerClientImpl clearcutLoggerClientImpl) {
            LogEventCallback logEventCallback = new LogEventCallback();
            try {
                ClearcutLogger.LogEventBuilder applyEventModifiers = this.logEventBuilder.applyEventModifiers();
                if (applyEventModifiers == null) {
                    logEventCallback.onLogEvent(Status.RESULT_SUCCESS);
                } else if (!applyEventModifiers.getLogger().getLogSampler().shouldLog(applyEventModifiers.getLogSourceName(), -1, applyEventModifiers.getEventCode())) {
                    setResult(Status.RESULT_SUCCESS);
                } else {
                    LogEventParcelable buildLogEventParcelable = ClearcutLoggerApiImpl.buildLogEventParcelable(applyEventModifiers);
                    if (buildLogEventParcelable == null) {
                        setFailedResult(new Status(10, "MessageProducer"));
                        return;
                    }
                    try {
                        ((IClearcutLoggerService) clearcutLoggerClientImpl.getService()).logEvent(logEventCallback, buildLogEventParcelable);
                        if (((Boolean) ClearcutLoggerApiImpl.this.logErrorQueueEnabledSupplier.get()).booleanValue()) {
                            ClearcutLoggerApiImpl.this.logError(LogErrorQueue.getInstance().getAndClearErrors());
                        }
                        if (ClearcutClientFlags.eventQueueEnabled) {
                            retryFirstQueuedEvent();
                        }
                    } catch (TransactionTooLargeException e) {
                        Log.e("ClearcutLoggerApiImpl", "Log event caused a TransactionTooLargeException", e);
                        ClearcutLoggerApiImpl.this.logError(new BatchedLogErrorParcelable(Arrays.asList(new LogErrorParcelable(buildLogEventParcelable.playLoggerContext.logSourceName, 31004, 1))));
                    } catch (RemoteException e2) {
                        e = e2;
                        handleLogEventException(e, buildLogEventParcelable);
                        throw e;
                    } catch (RuntimeException e3) {
                        e = e3;
                        handleLogEventException(e, buildLogEventParcelable);
                        throw e;
                    }
                }
            } catch (RuntimeException e4) {
                Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.EventModifier ", e4);
                setFailedResult(new Status(10, "EventModifier"));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$retryNextQueuedEvent$0$com-google-android-gms-clearcut-internal-ClearcutLoggerApiImpl$LogEventMethodImpl  reason: not valid java name */
        public /* synthetic */ void m128x8314cb35(LogEventQueue.Entry entry, ClearcutLoggerClientImpl clearcutLoggerClientImpl, TaskCompletionSource taskCompletionSource) {
            try {
                ((IClearcutLoggerService) clearcutLoggerClientImpl.getService()).logEvent(new RetryCallback(entry), entry.event);
            } catch (Exception e) {
                try {
                    if (LogEventQueue.getInstance().retry() != null) {
                        LogErrorQueue.getInstance().add(new LogErrorParcelable(entry.event.playLoggerContext.logSourceName, entry.code, 1));
                    }
                    throw e;
                } finally {
                    ClearcutLoggerApiImpl.logEventRetryPending.set(false);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public Status createFailedResult(Status status) {
            return status;
        }
    }

    ClearcutLoggerApiImpl(Context context, Supplier supplier) {
        super(context, ClearcutLogger.API, Api.ApiOptions.NO_OPTIONS, new ApiExceptionMapper());
        this.logErrorQueueEnabledSupplier = supplier;
    }

    static LogEventParcelable buildLogEventParcelable(ClearcutLogger.LogEventBuilder logEventBuilder) {
        try {
            LogEventParcelable resolveToBytes = resolveToBytes(logEventBuilder);
            LogVerifier logVerifier = logEventBuilder.getLogVerifier();
            if (logVerifier != null) {
                resolveToBytes.logVerifierResult = new LogVerifierResultParcelable(logVerifier.canLog(logEventBuilder, ((ClientAnalytics$LogEvent) Preconditions.checkNotNull(resolveToBytes.logEvent)).getSourceExtension()));
            }
            return resolveToBytes;
        } catch (RuntimeException e) {
            Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
            return null;
        }
    }

    public static ClearcutLoggerApi getInstance(Context context, Supplier supplier) {
        return new ClearcutLoggerApiImpl(context, supplier);
    }

    public static LogEventParcelable resolveToBytes(ClearcutLogger.LogEventBuilder logEventBuilder) {
        LogEventParcelable logEventParcelable = logEventBuilder.getLogEventParcelable();
        ClientAnalytics$LogEvent clientAnalytics$LogEvent = logEventParcelable.logEvent;
        Preconditions.checkNotNull(clientAnalytics$LogEvent);
        ClientAnalytics$LogEvent.Builder builder = (ClientAnalytics$LogEvent.Builder) clientAnalytics$LogEvent.toBuilder();
        if (logEventParcelable.sourceExtensionByteStringProvider != null && clientAnalytics$LogEvent.getSourceExtension().size() == 0) {
            builder.setSourceExtension((ByteString) logEventParcelable.sourceExtensionByteStringProvider.get());
        }
        if (logEventParcelable.clientVisualElements != null && clientAnalytics$LogEvent.getClientVe().isEmpty()) {
            builder.setClientVe(logEventParcelable.clientVisualElements.toByteString());
        }
        logEventParcelable.logEvent = (ClientAnalytics$LogEvent) builder.build();
        logEventParcelable.logEventBytes = logEventParcelable.logEvent.toByteArray();
        return logEventParcelable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$logError$2$com-google-android-gms-clearcut-internal-ClearcutLoggerApiImpl  reason: not valid java name */
    public /* synthetic */ void m126x92aaa18c(BatchedLogErrorParcelable batchedLogErrorParcelable, ClearcutLoggerClientImpl clearcutLoggerClientImpl, final TaskCompletionSource taskCompletionSource) {
        ((IClearcutLoggerService) clearcutLoggerClientImpl.getService()).logError(new DefaultClearcutLoggerCallbacks(this) { // from class: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.3
            @Override // com.google.android.gms.clearcut.internal.DefaultClearcutLoggerCallbacks, com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks
            public void onLogError(Status status) {
                taskCompletionSource.setResult(status);
            }
        }, batchedLogErrorParcelable);
    }

    public Task logError(final BatchedLogErrorParcelable batchedLogErrorParcelable) {
        if (batchedLogErrorParcelable.getErrors().isEmpty()) {
            return Tasks.forResult(Status.RESULT_SUCCESS);
        }
        return doBestEffortWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ClearcutLoggerApiImpl.this.m126x92aaa18c(batchedLogErrorParcelable, (ClearcutLoggerClientImpl) obj, (TaskCompletionSource) obj2);
            }
        }).setFeatures(Features.LOG_ERROR).setAutoResolveMissingFeatures(false).build());
    }

    @Override // com.google.android.gms.clearcut.ClearcutLoggerApi
    public PendingResult logEvent(ClearcutLogger.LogEventBuilder logEventBuilder) {
        return doBestEffortWrite(new LogEventMethodImpl(logEventBuilder, asGoogleApiClient()));
    }
}
