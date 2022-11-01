package com.google.common.util.concurrent;

import java.util.concurrent.Callable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Callables {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$returning$0(Object obj) {
        return obj;
    }

    public static Callable returning(final Object obj) {
        return new Callable() { // from class: com.google.common.util.concurrent.Callables$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Callables.lambda$returning$0(obj);
            }
        };
    }
}
