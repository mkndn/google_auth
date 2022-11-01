package com.google.android.libraries.storage.protostore;

import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Serializer {
    Object defaultValue();

    Object readFrom(InputStream inputStream);

    void writeTo(Object obj, OutputStream outputStream);
}
