package com.google.android.libraries.storage.protostore.loggers;

import com.google.android.libraries.storage.protostore.Logger;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoOpLogger implements Logger {
    public static final NoOpLogger INSTANCE = new NoOpLogger();

    private NoOpLogger() {
    }

    @Override // com.google.android.libraries.storage.protostore.Logger
    public void logUpdateData(ListenableFuture listenableFuture) {
    }
}
