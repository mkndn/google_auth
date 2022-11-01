package com.android.apksig.util;

import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface DataSource {
    void feed(long j, long j2, DataSink dataSink);

    ByteBuffer getByteBuffer(long j, int i);

    long size();

    DataSource slice(long j, long j2);
}
