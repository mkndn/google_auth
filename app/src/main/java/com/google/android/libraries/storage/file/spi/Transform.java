package com.google.android.libraries.storage.file.spi;

import android.net.Uri;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Transform {
    String encode(Uri uri, String str);

    String name();

    InputStream wrapForRead(Uri uri, InputStream inputStream);

    OutputStream wrapForWrite(Uri uri, OutputStream outputStream);
}
