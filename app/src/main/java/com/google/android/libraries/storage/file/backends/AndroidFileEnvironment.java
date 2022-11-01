package com.google.android.libraries.storage.file.backends;

import android.content.Context;
import android.os.SystemClock;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidFileEnvironment {
    public static File getDeviceProtectedDataDir(Context context) {
        return getFilesDirWithPreNWorkaround(context.createDeviceProtectedStorageContext()).getParentFile();
    }

    public static File getFilesDirWithPreNWorkaround(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            SystemClock.sleep(100L);
            filesDir = context.getFilesDir();
            if (filesDir == null) {
                throw new IllegalStateException("getFilesDir returned null twice.");
            }
        }
        return filesDir;
    }
}
