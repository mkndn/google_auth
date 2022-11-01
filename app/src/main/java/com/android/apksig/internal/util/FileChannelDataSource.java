package com.android.apksig.internal.util;

import android.support.v4.media.session.PlaybackStateCompat;
import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FileChannelDataSource implements DataSource {
    private final FileChannel mChannel;
    private final long mOffset;
    private final long mSize;

    public FileChannelDataSource(FileChannel fileChannel, long j, long j2) {
        if (j < 0) {
            throw new IndexOutOfBoundsException("offset: " + j2);
        }
        if (j2 < 0) {
            throw new IndexOutOfBoundsException("size: " + j2);
        }
        this.mChannel = fileChannel;
        this.mOffset = j;
        this.mSize = j2;
    }

    private static void checkChunkValid(long j, long j2, long j3) {
        if (j < 0) {
            throw new IndexOutOfBoundsException("offset: " + j);
        }
        if (j2 < 0) {
            throw new IndexOutOfBoundsException("size: " + j2);
        }
        if (j > j3) {
            throw new IndexOutOfBoundsException("offset (" + j + ") > source size (" + j3 + ")");
        }
        long j4 = j + j2;
        if (j4 < j) {
            throw new IndexOutOfBoundsException("offset (" + j + ") + size (" + j2 + ") overflow");
        }
        if (j4 > j3) {
            throw new IndexOutOfBoundsException("offset (" + j + ") + size (" + j2 + ") > source size (" + j3 + ")");
        }
    }

    @Override // com.android.apksig.util.DataSource
    public void feed(long j, long j2, DataSink dataSink) {
        checkChunkValid(j, j2, size());
        if (j2 == 0) {
            return;
        }
        long j3 = this.mOffset + j;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect((int) Math.min(j2, (long) PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED));
        while (j2 > 0) {
            int min = (int) Math.min(j2, allocateDirect.capacity());
            ByteBuffer byteBuffer = (ByteBuffer) allocateDirect.limit(min);
            synchronized (this.mChannel) {
                this.mChannel.position(j3);
                int i = min;
                while (i > 0) {
                    int read = this.mChannel.read(allocateDirect);
                    if (read >= 0) {
                        i -= read;
                    } else {
                        throw new IOException("Unexpected EOF encountered");
                    }
                }
            }
            ByteBuffer byteBuffer2 = (ByteBuffer) allocateDirect.flip();
            dataSink.consume(allocateDirect);
            ByteBuffer byteBuffer3 = (ByteBuffer) allocateDirect.clear();
            long j4 = min;
            j3 += j4;
            j2 -= j4;
        }
    }

    @Override // com.android.apksig.util.DataSource
    public ByteBuffer getByteBuffer(long j, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("size: " + i);
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        copyTo(j, i, allocate);
        ByteBuffer byteBuffer = (ByteBuffer) allocate.flip();
        return allocate;
    }

    @Override // com.android.apksig.util.DataSource
    public long size() {
        long j = this.mSize;
        if (j == -1) {
            try {
                return this.mChannel.size();
            } catch (IOException e) {
                return 0L;
            }
        }
        return j;
    }

    @Override // com.android.apksig.util.DataSource
    public FileChannelDataSource slice(long j, long j2) {
        long size = size();
        checkChunkValid(j, j2, size);
        if (j == 0 && j2 == size) {
            return this;
        }
        return new FileChannelDataSource(this.mChannel, this.mOffset + j, j2);
    }

    public void copyTo(long j, int i, ByteBuffer byteBuffer) {
        int read;
        checkChunkValid(j, i, size());
        if (i == 0) {
            return;
        }
        if (i > byteBuffer.remaining()) {
            throw new BufferOverflowException();
        }
        long j2 = this.mOffset + j;
        int limit = byteBuffer.limit();
        try {
            ByteBuffer byteBuffer2 = (ByteBuffer) byteBuffer.limit(byteBuffer.position() + i);
            while (i > 0) {
                synchronized (this.mChannel) {
                    this.mChannel.position(j2);
                    read = this.mChannel.read(byteBuffer);
                }
                j2 += read;
                i -= read;
            }
        } finally {
            ByteBuffer byteBuffer3 = (ByteBuffer) byteBuffer.limit(limit);
        }
    }
}
