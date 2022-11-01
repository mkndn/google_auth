package com.google.common.logging.stacktrace;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class LiteprotoEncoder$$ExternalSyntheticBackport0 {
    public static /* synthetic */ Throwable[] m(Throwable th) {
        try {
            return (Throwable[]) Throwable.class.getDeclaredMethod("getSuppressed", new Class[0]).invoke(th, new Object[0]);
        } catch (Exception e) {
            return new Throwable[0];
        }
    }
}
