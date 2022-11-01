package com.google.android.libraries.storage.file.spi;

import android.net.Uri;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Backend {
    void deleteFile(Uri uri);

    boolean exists(Uri uri);

    String name();

    InputStream openForRead(Uri uri);

    OutputStream openForWrite(Uri uri);

    void rename(Uri uri, Uri uri2);

    File toFile(Uri uri);
}
