package com.google.android.apps.authenticator.auditlog;

import org.joda.time.Instant;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public interface AuditLogEvent extends Comparable {

    /* compiled from: PG */
    /* renamed from: com.google.android.apps.authenticator.auditlog.AuditLogEvent$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static int $default$compareTo(AuditLogEvent auditLogEvent, AuditLogEvent auditLogEvent2) {
            return (auditLogEvent.id() > auditLogEvent2.id() ? 1 : (auditLogEvent.id() == auditLogEvent2.id() ? 0 : -1));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Visitor {
        Object visit(AddAccountEvent addAccountEvent);

        Object visit(ExportEvent exportEvent);

        Object visit(ImportEvent importEvent);
    }

    Object accept(Visitor visitor);

    long age();

    int compareTo(AuditLogEvent auditLogEvent);

    long id();

    Instant timestamp();
}
