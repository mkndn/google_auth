package com.google.common.flogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface LoggingApi {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NoOp implements LoggingApi {
        @Override // com.google.common.flogger.LoggingApi
        public final boolean isEnabled() {
            return false;
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str) {
        }

        protected final LoggingApi noOp() {
            return this;
        }

        @Override // com.google.common.flogger.LoggingApi
        public final LoggingApi withCause(Throwable th) {
            return noOp();
        }

        @Override // com.google.common.flogger.LoggingApi
        public LoggingApi withInjectedLogSite(String str, String str2, int i, String str3) {
            return noOp();
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, int i) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, long j) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, Object obj) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, Object obj, Object obj2) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, Object obj, Object obj2, Object obj3) {
        }
    }

    boolean isEnabled();

    void log(String str);

    void log(String str, int i);

    void log(String str, long j);

    void log(String str, Object obj);

    void log(String str, Object obj, Object obj2);

    void log(String str, Object obj, Object obj2, Object obj3);

    LoggingApi withCause(Throwable th);

    LoggingApi withInjectedLogSite(String str, String str2, int i, String str3);
}
