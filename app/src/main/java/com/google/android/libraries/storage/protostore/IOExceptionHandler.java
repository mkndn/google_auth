package com.google.android.libraries.storage.protostore;

import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class IOExceptionHandler {
    public abstract ListenableFuture handleReadException(IOException iOException, IOExceptionHandlerApi iOExceptionHandlerApi);
}
