package com.google.android.libraries.storage.file.common.internal;

import com.google.android.libraries.storage.file.common.FileConvertible;
import com.google.android.libraries.storage.file.common.Syncable;
import java.io.File;
import java.io.FileOutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BackendOutputStream extends ForwardingOutputStream implements FileConvertible, Syncable {
    private final File file;
    private final FileOutputStream fileOutputStream;

    public BackendOutputStream(FileOutputStream fileOutputStream, File file) {
        super(fileOutputStream);
        this.fileOutputStream = fileOutputStream;
        this.file = file;
    }

    public static BackendOutputStream createForWrite(File file) {
        return new BackendOutputStream(new FileOutputStream(file), file);
    }

    @Override // com.google.android.libraries.storage.file.common.Syncable
    public void sync() {
        this.fileOutputStream.getFD().sync();
    }

    @Override // com.google.android.libraries.storage.file.common.FileConvertible
    public File toFile() {
        return this.file;
    }
}
