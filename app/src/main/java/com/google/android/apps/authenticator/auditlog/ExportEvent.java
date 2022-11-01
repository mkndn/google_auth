package com.google.android.apps.authenticator.auditlog;

import com.google.android.apps.authenticator.auditlog.AuditLogEvent;
import com.google.android.apps.authenticator.auditlog.AutoValue_ExportEvent;
import org.joda.time.Instant;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ExportEvent implements AuditLogEvent {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract ExportEvent build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setAge(long j);

        public abstract Builder setAmount(int i);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setId(long j);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setTimestamp(Instant instant);
    }

    public static Builder builder() {
        return new AutoValue_ExportEvent.Builder().setId(-1L).setAge(0L).setTimestamp(Instant.now());
    }

    @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public Object accept(AuditLogEvent.Visitor visitor) {
        return visitor.visit(this);
    }

    @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public abstract long age();

    public abstract int amount();

    @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public /* synthetic */ int compareTo(AuditLogEvent auditLogEvent) {
        return AuditLogEvent.CC.$default$compareTo((AuditLogEvent) this, auditLogEvent);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        int compareTo;
        compareTo = compareTo((AuditLogEvent) obj);
        return compareTo;
    }

    @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public abstract long id();

    @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent
    public abstract Instant timestamp();
}
