package com.google.common.flogger;

import com.google.common.flogger.util.Checks;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LogSite implements LogSiteKey {
    public static final LogSite INVALID = new LogSite() { // from class: com.google.common.flogger.LogSite.1
        @Override // com.google.common.flogger.LogSite
        public String getClassName() {
            return "<unknown class>";
        }

        @Override // com.google.common.flogger.LogSite
        public String getFileName() {
            return null;
        }

        @Override // com.google.common.flogger.LogSite
        public int getLineNumber() {
            return 0;
        }

        @Override // com.google.common.flogger.LogSite
        public String getMethodName() {
            return "<unknown method>";
        }
    };
    public static final int UNKNOWN_LINE = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InjectedLogSite extends LogSite {
        private final int encodedLineNumber;
        private int hashcode;
        private final String internalClassName;
        private final String methodName;
        private final String sourceFileName;

        private InjectedLogSite(String str, String str2, int i, String str3) {
            this.hashcode = 0;
            this.internalClassName = (String) Checks.checkNotNull(str, "class name");
            this.methodName = (String) Checks.checkNotNull(str2, "method name");
            this.encodedLineNumber = i;
            this.sourceFileName = str3;
        }

        public boolean equals(Object obj) {
            if (obj instanceof InjectedLogSite) {
                InjectedLogSite injectedLogSite = (InjectedLogSite) obj;
                return this.internalClassName.equals(injectedLogSite.internalClassName) && this.methodName.equals(injectedLogSite.methodName) && this.encodedLineNumber == injectedLogSite.encodedLineNumber;
            }
            return false;
        }

        @Override // com.google.common.flogger.LogSite
        public String getClassName() {
            return this.internalClassName.replace('/', '.');
        }

        @Override // com.google.common.flogger.LogSite
        public String getFileName() {
            return this.sourceFileName;
        }

        @Override // com.google.common.flogger.LogSite
        public int getLineNumber() {
            return (char) this.encodedLineNumber;
        }

        @Override // com.google.common.flogger.LogSite
        public String getMethodName() {
            return this.methodName;
        }

        public int hashCode() {
            if (this.hashcode == 0) {
                this.hashcode = ((((this.internalClassName.hashCode() + 4867) * 31) + this.methodName.hashCode()) * 31) + this.encodedLineNumber;
            }
            return this.hashcode;
        }
    }

    @Deprecated
    public static LogSite injectedLogSite(String str, String str2, int i, String str3) {
        return new InjectedLogSite(str, str2, i, str3);
    }

    public abstract String getClassName();

    public abstract String getFileName();

    public abstract int getLineNumber();

    public abstract String getMethodName();

    public final String toString() {
        StringBuilder append = new StringBuilder().append("LogSite{ class=").append(getClassName()).append(", method=").append(getMethodName()).append(", line=").append(getLineNumber());
        if (getFileName() != null) {
            append.append(", file=").append(getFileName());
        }
        return append.append(" }").toString();
    }
}
