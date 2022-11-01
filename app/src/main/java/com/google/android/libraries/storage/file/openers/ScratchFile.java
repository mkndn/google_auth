package com.google.android.libraries.storage.file.openers;

import android.net.Uri;
import android.os.Process;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ScratchFile {
    private static final AtomicLong SCRATCH_COUNTER = new AtomicLong();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Uri scratchUri(Uri uri) {
        return uri.buildUpon().path(uri.getPath() + (".mobstore_tmp-" + Process.myPid() + "-" + Thread.currentThread().getId() + "-" + System.currentTimeMillis() + "-" + SCRATCH_COUNTER.getAndIncrement())).build();
    }
}
