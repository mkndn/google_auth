package com.android.apksig.util;

import com.android.apksig.internal.util.FileChannelDataSource;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DataSources {
    public static DataSource asDataSource(RandomAccessFile randomAccessFile, long j, long j2) {
        return asDataSource(randomAccessFile.getChannel(), j, j2);
    }

    public static DataSource asDataSource(FileChannel fileChannel, long j, long j2) {
        if (fileChannel == null) {
            throw new NullPointerException();
        }
        return new FileChannelDataSource(fileChannel, j, j2);
    }
}
