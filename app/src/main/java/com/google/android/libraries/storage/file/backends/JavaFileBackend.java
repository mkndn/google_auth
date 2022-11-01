package com.google.android.libraries.storage.file.backends;

import android.net.Uri;
import com.google.android.libraries.storage.file.common.LockScope;
import com.google.android.libraries.storage.file.common.internal.BackendInputStream;
import com.google.android.libraries.storage.file.common.internal.BackendOutputStream;
import com.google.android.libraries.storage.file.spi.Backend;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JavaFileBackend implements Backend {
    private final LockScope lockScope;

    public JavaFileBackend() {
        this(new LockScope());
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public void deleteFile(Uri uri) {
        File file = FileUriAdapter.instance().toFile(uri);
        if (file.isDirectory()) {
            throw new FileNotFoundException(String.format("%s is a directory", uri));
        }
        if (!file.delete()) {
            if (!file.exists()) {
                throw new FileNotFoundException(String.format("%s does not exist", uri));
            }
            throw new IOException(String.format("%s could not be deleted", uri));
        }
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public boolean exists(Uri uri) {
        return FileUriAdapter.instance().toFile(uri).exists();
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public String name() {
        return "file";
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public InputStream openForRead(Uri uri) {
        return BackendInputStream.create(FileUriAdapter.instance().toFile(uri));
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public OutputStream openForWrite(Uri uri) {
        File file = FileUriAdapter.instance().toFile(uri);
        Files.createParentDirs(file);
        return BackendOutputStream.createForWrite(file);
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public void rename(Uri uri, Uri uri2) {
        File file = FileUriAdapter.instance().toFile(uri);
        File file2 = FileUriAdapter.instance().toFile(uri2);
        Files.createParentDirs(file2);
        if (!file.renameTo(file2)) {
            throw new IOException(String.format("%s could not be renamed to %s", uri, uri2));
        }
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public File toFile(Uri uri) {
        return FileUriAdapter.instance().toFile(uri);
    }

    public JavaFileBackend(LockScope lockScope) {
        this.lockScope = lockScope;
    }
}
