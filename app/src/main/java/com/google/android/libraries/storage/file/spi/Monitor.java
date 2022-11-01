package com.google.android.libraries.storage.file.spi;

import android.net.Uri;
import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Monitor {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface InputMonitor extends Closeable {
        void bytesRead(byte[] bArr, int i, int i2);

        @Override // java.io.Closeable, java.lang.AutoCloseable
        void close();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OutputMonitor extends Closeable {
        void bytesWritten(byte[] bArr, int i, int i2);

        @Override // java.io.Closeable, java.lang.AutoCloseable
        void close();
    }

    InputMonitor monitorRead(Uri uri);

    OutputMonitor monitorWrite(Uri uri);
}
