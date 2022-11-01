package com.google.android.libraries.storage.protostore.handlers;

import android.os.Build;
import com.google.android.libraries.storage.protostore.IOExceptionHandler;
import com.google.android.libraries.storage.protostore.IOExceptionHandlerApi;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReplaceFileIOExceptionHandler extends IOExceptionHandler {
    private final MessageLite defaultProto;

    public ReplaceFileIOExceptionHandler(MessageLite messageLite) {
        this.defaultProto = messageLite;
    }

    public static /* synthetic */ ListenableFuture lambda$handleReadException$0(IOException iOException, IOException iOException2) {
        if (Build.VERSION.SDK_INT >= 19) {
            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(iOException, iOException2);
        }
        throw iOException;
    }

    @Override // com.google.android.libraries.storage.protostore.IOExceptionHandler
    public ListenableFuture handleReadException(final IOException iOException, IOExceptionHandlerApi iOExceptionHandlerApi) {
        if (!(iOException.getCause() instanceof InvalidProtocolBufferException)) {
            return Futures.immediateFailedFuture(iOException);
        }
        return Futures.catchingAsync(iOExceptionHandlerApi.replaceData(Futures.immediateFuture(this.defaultProto)), IOException.class, new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.handlers.ReplaceFileIOExceptionHandler$$ExternalSyntheticLambda1
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return ReplaceFileIOExceptionHandler.lambda$handleReadException$0(iOException, (IOException) obj);
            }
        }, MoreExecutors.directExecutor());
    }
}
