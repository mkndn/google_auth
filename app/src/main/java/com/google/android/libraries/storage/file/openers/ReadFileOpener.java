package com.google.android.libraries.storage.file.openers;

import android.content.Context;
import android.util.Log;
import com.google.android.libraries.storage.file.OpenContext;
import com.google.android.libraries.storage.file.Opener;
import com.google.android.libraries.storage.file.common.FileConvertible;
import com.google.android.libraries.storage.file.common.ReleasableResource;
import com.google.android.libraries.storage.file.common.UnsupportedFileStorageOperation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReadFileOpener implements Opener {
    private static final AtomicInteger FIFO_COUNTER = new AtomicInteger();
    private Context context;
    private ExecutorService executor;
    private Future pumpFuture;
    private boolean shortCircuit = false;

    private ReadFileOpener() {
    }

    public static ReadFileOpener create() {
        return new ReadFileOpener();
    }

    public static /* synthetic */ Throwable lambda$pipeToFile$0(File file, InputStream inputStream) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            file.delete();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    inputStream.close();
                    fileOutputStream.close();
                    return null;
                }
            }
        } catch (IOException e) {
            Log.w("ReadFileOpener", "pump", e);
            return e;
        } catch (Throwable th) {
            Log.e("ReadFileOpener", "pump", th);
            return th;
        }
    }

    private File pipeToFile(final InputStream inputStream) {
        final File makeFifo = Pipes.makeFifo(this.context.getCacheDir(), "ReadFileOpener", FIFO_COUNTER);
        this.pumpFuture = this.executor.submit(new Callable() { // from class: com.google.android.libraries.storage.file.openers.ReadFileOpener$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ReadFileOpener.lambda$pipeToFile$0(makeFifo, inputStream);
            }
        });
        return makeFifo;
    }

    @Override // com.google.android.libraries.storage.file.Opener
    public File open(OpenContext openContext) {
        if (this.shortCircuit) {
            if (openContext.hasTransforms()) {
                throw new UnsupportedFileStorageOperation("Short circuit would skip transforms.");
            }
            return openContext.backend().toFile(openContext.encodedUri());
        }
        ReleasableResource create = ReleasableResource.create(ReadStreamOpener.create().open(openContext));
        try {
            if (create.get() instanceof FileConvertible) {
                File file = ((FileConvertible) create.get()).toFile();
                if (create != null) {
                    create.close();
                }
                return file;
            } else if (this.executor != null) {
                File pipeToFile = pipeToFile((InputStream) create.release());
                if (create != null) {
                    create.close();
                }
                return pipeToFile;
            } else {
                throw new IOException("Not convertible and fallback to pipe is disabled.");
            }
        } catch (Throwable th) {
            if (create != null) {
                try {
                    create.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public ReadFileOpener withShortCircuit() {
        this.shortCircuit = true;
        return this;
    }
}
