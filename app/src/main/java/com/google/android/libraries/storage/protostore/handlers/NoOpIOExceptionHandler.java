package com.google.android.libraries.storage.protostore.handlers;

import com.google.android.libraries.storage.protostore.IOExceptionHandler;
import com.google.android.libraries.storage.protostore.IOExceptionHandlerApi;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoOpIOExceptionHandler extends IOExceptionHandler {
    private static final NoOpIOExceptionHandler INSTANCE = new NoOpIOExceptionHandler();

    private NoOpIOExceptionHandler() {
    }

    public static final NoOpIOExceptionHandler getInstance() {
        return INSTANCE;
    }

    @Override // com.google.android.libraries.storage.protostore.IOExceptionHandler
    public ListenableFuture handleReadException(IOException iOException, IOExceptionHandlerApi iOExceptionHandlerApi) {
        return Futures.immediateFailedFuture(iOException);
    }
}
