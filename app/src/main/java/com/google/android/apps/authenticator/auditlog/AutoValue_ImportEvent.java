package com.google.android.apps.authenticator.auditlog;

import com.google.android.apps.authenticator.auditlog.ImportEvent;
import org.joda.time.Instant;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_ImportEvent extends ImportEvent {
    private final long age;
    private final int amount;
    private final long id;
    private final Instant timestamp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends ImportEvent.Builder {
        private long age;
        private int amount;
        private long id;
        private byte set$0;
        private Instant timestamp;

        @Override // com.google.android.apps.authenticator.auditlog.ImportEvent.Builder
        public ImportEvent build() {
            if (this.set$0 == 7 && this.timestamp != null) {
                return new AutoValue_ImportEvent(this.id, this.timestamp, this.amount, this.age);
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
        @Override // com.google.android.apps.authenticator.auditlog.ImportEvent.Builder
        public ImportEvent.Builder setAge(long j) {
            this.age = j;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.android.apps.authenticator.auditlog.ImportEvent.Builder
        public ImportEvent.Builder setAmount(int i) {
            this.amount = i;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.apps.authenticator.auditlog.ImportEvent.Builder
        public ImportEvent.Builder setId(long j) {
            this.id = j;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.apps.authenticator.auditlog.ImportEvent.Builder
        public ImportEvent.Builder setTimestamp(Instant instant) {
            if (instant == null) {
                throw new NullPointerException("Null timestamp");
            }
            this.timestamp = instant;
            return this;
        }
    }

    private AutoValue_ImportEvent(long j, Instant instant, int i, long j2) {
        this.id = j;
        this.timestamp = instant;
        this.amount = i;
        this.age = j2;
    }

    @Override // com.google.android.apps.authenticator.auditlog.ImportEvent, com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public long age() {
        return this.age;
    }

    @Override // com.google.android.apps.authenticator.auditlog.ImportEvent
    public int amount() {
        return this.amount;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImportEvent) {
            ImportEvent importEvent = (ImportEvent) obj;
            return this.id == importEvent.id() && this.timestamp.equals(importEvent.timestamp()) && this.amount == importEvent.amount() && this.age == importEvent.age();
        }
        return false;
    }

    @Override // com.google.android.apps.authenticator.auditlog.ImportEvent, com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public long id() {
        return this.id;
    }

    @Override // com.google.android.apps.authenticator.auditlog.ImportEvent, com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public Instant timestamp() {
        return this.timestamp;
    }

    public String toString() {
        long j = this.id;
        String valueOf = String.valueOf(this.timestamp);
        int i = this.amount;
        return "ImportEvent{id=" + j + ", timestamp=" + valueOf + ", amount=" + i + ", age=" + this.age + "}";
    }

    public int hashCode() {
        long j = this.id;
        long j2 = this.age;
        return ((int) (j2 ^ (j2 >>> 32))) ^ ((((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.timestamp.hashCode()) * 1000003) ^ this.amount) * 1000003);
    }
}
