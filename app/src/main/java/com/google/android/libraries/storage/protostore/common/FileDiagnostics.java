package com.google.android.libraries.storage.protostore.common;

import android.net.Uri;
import android.os.Build;
import android.system.Os;
import com.google.android.libraries.storage.file.SynchronousFileStorage;
import com.google.android.libraries.storage.file.openers.ReadFileOpener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FileDiagnostics {
    public static IOException attachFileDebugInfoV1(SynchronousFileStorage synchronousFileStorage, Uri uri, IOException iOException) {
        try {
            return attachFileStacktrace((File) synchronousFileStorage.open(uri, ReadFileOpener.create().withShortCircuit()), iOException);
        } catch (IOException e) {
            return new IOException(iOException);
        }
    }

    private static IOException attachFileStacktrace(File file, IOException iOException) {
        if (file.exists()) {
            if (file.isFile()) {
                if (file.canRead()) {
                    if (file.canWrite()) {
                        return attachParentStacktrace(file, iOException);
                    }
                    return attachParentStacktrace(file, iOException);
                } else if (file.canWrite()) {
                    return attachParentStacktrace(file, iOException);
                } else {
                    return attachParentStacktrace(file, iOException);
                }
            } else if (file.canRead()) {
                if (file.canWrite()) {
                    return attachParentStacktrace(file, iOException);
                }
                return attachParentStacktrace(file, iOException);
            } else if (file.canWrite()) {
                return attachParentStacktrace(file, iOException);
            } else {
                return attachParentStacktrace(file, iOException);
            }
        }
        return attachParentStacktrace(file, iOException);
    }

    private static IOException attachFilesystemMessage(File file, IOException iOException) {
        String str = "Inoperable file:";
        try {
            str = "Inoperable file:" + String.format(Locale.US, " canonical[%s] freeSpace[%d]", file.getCanonicalPath(), Long.valueOf(file.getFreeSpace()));
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    str = str + String.format(Locale.US, " mode[%d]", Integer.valueOf(Os.stat(file.getCanonicalPath()).st_mode));
                } catch (Exception e) {
                }
            }
        } catch (IOException e2) {
            str = str + " failed";
        }
        return new IOException(str, iOException);
    }

    private static IOException attachParentStacktrace(File file, IOException iOException) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return attachFilesystemMessage(file, iOException);
        }
        if (parentFile.exists()) {
            if (parentFile.isDirectory()) {
                if (parentFile.canRead()) {
                    if (parentFile.canWrite()) {
                        return attachFilesystemMessage(file, iOException);
                    }
                    return attachFilesystemMessage(file, iOException);
                } else if (parentFile.canWrite()) {
                    return attachFilesystemMessage(file, iOException);
                } else {
                    return attachFilesystemMessage(file, iOException);
                }
            } else if (parentFile.canRead()) {
                if (parentFile.canWrite()) {
                    return attachFilesystemMessage(file, iOException);
                }
                return attachFilesystemMessage(file, iOException);
            } else if (parentFile.canWrite()) {
                return attachFilesystemMessage(file, iOException);
            } else {
                return attachFilesystemMessage(file, iOException);
            }
        }
        return attachFilesystemMessage(file, iOException);
    }
}
