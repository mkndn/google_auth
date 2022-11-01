package com.google.android.libraries.storage.file.openers;

import android.os.Build;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import com.google.android.libraries.storage.file.common.UnsupportedFileStorageOperation;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Pipes {
    private static final int MY_PID = Process.myPid();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File makeFifo(File file, String str, AtomicInteger atomicInteger) {
        if (Build.VERSION.SDK_INT < 21) {
            throw new UnsupportedFileStorageOperation(String.format("FIFOs require SDK level 21+; current level is %d", Integer.valueOf(Build.VERSION.SDK_INT)));
        }
        File file2 = new File(file, ".mobstore-" + str + "-" + MY_PID + "-" + atomicInteger.getAndIncrement() + ".fifo");
        file2.delete();
        try {
            Os.mkfifo(file2.getAbsolutePath(), OsConstants.S_IRUSR | OsConstants.S_IWUSR);
            return file2;
        } catch (ErrnoException e) {
            file2.delete();
            throw new IOException(e);
        }
    }
}
