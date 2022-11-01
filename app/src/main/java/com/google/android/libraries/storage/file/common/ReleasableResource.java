package com.google.android.libraries.storage.file.common;

import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReleasableResource implements Closeable {
    private Closeable resource;

    private ReleasableResource(Closeable closeable) {
        this.resource = closeable;
    }

    public static ReleasableResource create(Closeable closeable) {
        return new ReleasableResource(closeable);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Closeable closeable = this.resource;
        if (closeable != null) {
            closeable.close();
        }
    }

    public Closeable get() {
        return this.resource;
    }

    public Closeable release() {
        Closeable closeable = this.resource;
        this.resource = null;
        return closeable;
    }
}
