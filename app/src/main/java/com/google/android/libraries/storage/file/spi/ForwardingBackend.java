package com.google.android.libraries.storage.file.spi;

import android.net.Uri;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingBackend implements Backend {
    protected abstract Backend delegate();

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public void deleteFile(Uri uri) {
        delegate().deleteFile(rewriteUri(uri));
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public boolean exists(Uri uri) {
        return delegate().exists(rewriteUri(uri));
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public InputStream openForRead(Uri uri) {
        return delegate().openForRead(rewriteUri(uri));
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public OutputStream openForWrite(Uri uri) {
        return delegate().openForWrite(rewriteUri(uri));
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public void rename(Uri uri, Uri uri2) {
        delegate().rename(rewriteUri(uri), rewriteUri(uri2));
    }

    protected Uri rewriteUri(Uri uri) {
        throw null;
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public /* synthetic */ File toFile(Uri uri) {
        throw null;
    }
}
