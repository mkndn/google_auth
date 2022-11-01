package com.google.android.apps.authenticator.auditlog;

import com.google.android.apps.authenticator.auditlog.AddAccountEvent;
import com.google.android.apps.authenticator.otp.AccountDb;
import org.joda.time.Instant;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_AddAccountEvent extends AddAccountEvent {
    private final AccountDb.AccountIndex accountIndex;
    private final long age;
    private final long id;
    private final Instant timestamp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends AddAccountEvent.Builder {
        private AccountDb.AccountIndex accountIndex;
        private long age;
        private long id;
        private byte set$0;
        private Instant timestamp;

        @Override // com.google.android.apps.authenticator.auditlog.AddAccountEvent.Builder
        public AddAccountEvent build() {
            if (this.set$0 == 3 && this.timestamp != null && this.accountIndex != null) {
                return new AutoValue_AddAccountEvent(this.id, this.timestamp, this.accountIndex, this.age);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" id");
            }
            if (this.timestamp == null) {
                sb.append(" timestamp");
            }
            if (this.accountIndex == null) {
                sb.append(" accountIndex");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" age");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.apps.authenticator.auditlog.AddAccountEvent.Builder
        public AddAccountEvent.Builder setAccountIndex(AccountDb.AccountIndex accountIndex) {
            if (accountIndex == null) {
                throw new NullPointerException("Null accountIndex");
            }
            this.accountIndex = accountIndex;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.apps.authenticator.auditlog.AddAccountEvent.Builder
        public AddAccountEvent.Builder setAge(long j) {
            this.age = j;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.apps.authenticator.auditlog.AddAccountEvent.Builder
        public AddAccountEvent.Builder setId(long j) {
            this.id = j;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.apps.authenticator.auditlog.AddAccountEvent.Builder
        public AddAccountEvent.Builder setTimestamp(Instant instant) {
            if (instant == null) {
                throw new NullPointerException("Null timestamp");
            }
            this.timestamp = instant;
            return this;
        }
    }

    private AutoValue_AddAccountEvent(long j, Instant instant, AccountDb.AccountIndex accountIndex, long j2) {
        this.id = j;
        this.timestamp = instant;
        this.accountIndex = accountIndex;
        this.age = j2;
    }

    @Override // com.google.android.apps.authenticator.auditlog.AddAccountEvent
    public AccountDb.AccountIndex accountIndex() {
        return this.accountIndex;
    }

    @Override // com.google.android.apps.authenticator.auditlog.AddAccountEvent, com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public long age() {
        return this.age;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddAccountEvent) {
            AddAccountEvent addAccountEvent = (AddAccountEvent) obj;
            return this.id == addAccountEvent.id() && this.timestamp.equals(addAccountEvent.timestamp()) && this.accountIndex.equals(addAccountEvent.accountIndex()) && this.age == addAccountEvent.age();
        }
        return false;
    }

    @Override // com.google.android.apps.authenticator.auditlog.AddAccountEvent, com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public long id() {
        return this.id;
    }

    @Override // com.google.android.apps.authenticator.auditlog.AddAccountEvent, com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public Instant timestamp() {
        return this.timestamp;
    }

    public String toString() {
        long j = this.id;
        String valueOf = String.valueOf(this.timestamp);
        String valueOf2 = String.valueOf(this.accountIndex);
        return "AddAccountEvent{id=" + j + ", timestamp=" + valueOf + ", accountIndex=" + valueOf2 + ", age=" + this.age + "}";
    }

    public int hashCode() {
        long j = this.id;
        long j2 = this.age;
        return ((int) (j2 ^ (j2 >>> 32))) ^ ((((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.timestamp.hashCode()) * 1000003) ^ this.accountIndex.hashCode()) * 1000003);
    }
}
