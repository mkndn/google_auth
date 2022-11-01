package com.android.apksig.util;

import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface DataSink {
    void consume(ByteBuffer byteBuffer);

    void consume(byte[] bArr, int i, int i2);
}
