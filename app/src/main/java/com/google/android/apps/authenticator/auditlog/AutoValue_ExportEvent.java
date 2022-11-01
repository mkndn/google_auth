package com.google.android.apps.authenticator.auditlog;

import com.google.android.apps.authenticator.auditlog.ExportEvent;
import org.joda.time.Instant;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_ExportEvent extends ExportEvent {
    private final long age;
    private final int amount;
    private final long id;
    private final Instant timestamp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends ExportEvent.Builder {
        private long age;
        private int amount;
        private long id;
        private byte set$0;
        private Instant timestamp;

        @Override // com.google.android.apps.authenticator.auditlog.ExportEvent.Builder
        public ExportEvent build() {
            if (this.set$0 == 7 && this.timestamp != null) {
                return new AutoValue_ExportEvent(this.id, this.timestamp, this.amount, this.age);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" id");
            }
            if (this.timestamp == null) {
                sb.append(" timestamp");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" amount");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" age");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.apps.authenticator.auditlog.ExportEvent.Builder
        public ExportEvent.Builder setAge(long j) {
            this.age = j;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.android.apps.authenticator.auditlog.ExportEvent.Builder
        public ExportEvent.Builder setAmount(int i) {
            this.amount = i;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.apps.authenticator.auditlog.ExportEvent.Builder
        public ExportEvent.Builder setId(long j) {
            this.id = j;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.apps.authenticator.auditlog.ExportEvent.Builder
        public ExportEvent.Builder setTimestamp(Instant instant) {
            if (instant == null) {
                throw new NullPointerException("Null timestamp");
            }
            this.timestamp = instant;
            return this;
        }
    }

    private AutoValue_ExportEvent(long j, Instant instant, int i, long j2) {
        this.id = j;
        this.timestamp = instant;
        this.amount = i;
        this.age = j2;
    }

    @Override // com.google.android.apps.authenticator.auditlog.ExportEvent, com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public long age() {
        return this.age;
    }

    @Override // com.google.android.apps.authenticator.auditlog.ExportEvent
    public int amount() {
        return this.amount;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ExportEvent) {
            ExportEvent exportEvent = (ExportEvent) obj;
            return this.id == exportEvent.id() && this.timestamp.equals(exportEvent.timestamp()) && this.amount == exportEvent.amount() && this.age == exportEvent.age();
        }
        return false;
    }

    @Override // com.google.android.apps.authenticator.auditlog.ExportEvent, com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public long id() {
        return this.id;
    }

    @Override // com.google.android.apps.authenticator.auditlog.ExportEvent, com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public Instant timestamp() {
        return this.timestamp;
    }

    public String toString() {
        long j = this.id;
        String valueOf = String.valueOf(this.timestamp);
        int i = this.amount;
        return "ExportEvent{id=" + j + ", timestamp=" + valueOf + ", amount=" + i + ", age=" + this.age + "}";
    }

    public int hashCode() {
        long j = this.id;
        long j2 = this.age;
        return ((int) (j2 ^ (j2 >>> 32))) ^ ((((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.timestamp.hashCode()) * 1000003) ^ this.amount) * 1000003);
    }
}
