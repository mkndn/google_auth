package com.android.apksig.internal.zip;

import com.android.apksig.internal.util.ByteBufferSink;
import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import com.android.apksig.zip.ZipFormatException;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LocalFileRecord {
    private static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.allocate(0);
    private final boolean mDataCompressed;
    private final long mDataSize;
    private final int mDataStartOffset;
    private final ByteBuffer mExtra;
    private final String mName;
    private final int mNameSizeBytes;
    private final long mSize;
    private final long mStartOffsetInArchive;
    private final long mUncompressedDataSize;

    private LocalFileRecord(String str, int i, ByteBuffer byteBuffer, long j, long j2, int i2, long j3, boolean z, long j4) {
        this.mName = str;
        this.mNameSizeBytes = i;
        this.mExtra = byteBuffer;
        this.mStartOffsetInArchive = j;
        this.mSize = j2;
        this.mDataStartOffset = i2;
        this.mDataSize = j3;
        this.mDataCompressed = z;
        this.mUncompressedDataSize = j4;
    }

    private static LocalFileRecord getRecord(DataSource dataSource, CentralDirectoryRecord centralDirectoryRecord, long j, boolean z, boolean z2) {
        long j2;
        DataSource dataSource2;
        long j3;
        String name = centralDirectoryRecord.getName();
        int nameSizeBytes = centralDirectoryRecord.getNameSizeBytes();
        int i = nameSizeBytes + 30;
        long localFileHeaderOffset = centralDirectoryRecord.getLocalFileHeaderOffset();
        long j4 = i + localFileHeaderOffset;
        if (j4 > j) {
            throw new ZipFormatException("Local File Header of " + name + " extends beyond start of Central Directory. LFH end: " + j4 + ", CD start: " + j);
        }
        try {
            ByteBuffer byteBuffer = dataSource.getByteBuffer(localFileHeaderOffset, i);
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            int i2 = byteBuffer.getInt();
            if (i2 != 67324752) {
                throw new ZipFormatException("Not a Local File Header record for entry " + name + ". Signature: 0x" + Long.toHexString(i2 & 4294967295L));
            }
            int i3 = byteBuffer.getShort(6) & 8;
            boolean z3 = i3 != 0;
            boolean z4 = (centralDirectoryRecord.getGpFlags() & 8) != 0;
            if (z3 != z4) {
                throw new ZipFormatException("Data Descriptor presence mismatch between Local File Header and Central Directory for entry " + name + ". LFH: " + z3 + ", CD: " + z4);
            }
            long crc32 = centralDirectoryRecord.getCrc32();
            boolean z5 = z3;
            long compressedSize = centralDirectoryRecord.getCompressedSize();
            long uncompressedSize = centralDirectoryRecord.getUncompressedSize();
            if (z5) {
                j2 = localFileHeaderOffset;
            } else {
                j2 = localFileHeaderOffset;
                long unsignedInt32 = ZipUtils.getUnsignedInt32(byteBuffer, 14);
                if (unsignedInt32 != crc32) {
                    throw new ZipFormatException("CRC-32 mismatch between Local File Header and Central Directory for entry " + name + ". LFH: " + unsignedInt32 + ", CD: " + crc32);
                }
                long unsignedInt322 = ZipUtils.getUnsignedInt32(byteBuffer, 18);
                if (unsignedInt322 != compressedSize) {
                    throw new ZipFormatException("Compressed size mismatch between Local File Header and Central Directory for entry " + name + ". LFH: " + unsignedInt322 + ", CD: " + compressedSize);
                }
                long unsignedInt323 = ZipUtils.getUnsignedInt32(byteBuffer, 22);
                if (unsignedInt323 != uncompressedSize) {
                    throw new ZipFormatException("Uncompressed size mismatch between Local File Header and Central Directory for entry " + name + ". LFH: " + unsignedInt323 + ", CD: " + uncompressedSize);
                }
            }
            int unsignedInt16 = ZipUtils.getUnsignedInt16(byteBuffer, 26);
            if (unsignedInt16 > nameSizeBytes) {
                throw new ZipFormatException("Name mismatch between Local File Header and Central Directory for entry" + name + ". LFH: " + unsignedInt16 + " bytes, CD: " + nameSizeBytes + " bytes");
            }
            String name2 = CentralDirectoryRecord.getName(byteBuffer, 30, unsignedInt16);
            if (!name.equals(name2)) {
                throw new ZipFormatException("Name mismatch between Local File Header and Central Directory. LFH: \"" + name2 + "\", CD: \"" + name + "\"");
            }
            int unsignedInt162 = ZipUtils.getUnsignedInt16(byteBuffer, 28);
            long j5 = j2 + 30 + unsignedInt16;
            long j6 = unsignedInt162 + j5;
            boolean z6 = centralDirectoryRecord.getCompressionMethod() != 0;
            if (!z6) {
                compressedSize = uncompressedSize;
            }
            long j7 = j6 + compressedSize;
            if (j7 > j) {
                throw new ZipFormatException("Local File Header data of " + name + " overlaps with Central Directory. LFH data start: " + j6 + ", LFH data end: " + j7 + ", CD start: " + j);
            }
            ByteBuffer byteBuffer2 = EMPTY_BYTE_BUFFER;
            if (z && unsignedInt162 > 0) {
                dataSource2 = dataSource;
                byteBuffer2 = dataSource2.getByteBuffer(j5, unsignedInt162);
            } else {
                dataSource2 = dataSource;
            }
            if (z2 && i3 != 0) {
                long j8 = 12 + j7;
                if (j8 <= j) {
                    j3 = compressedSize;
                    ByteBuffer byteBuffer3 = dataSource2.getByteBuffer(j7, 4);
                    byteBuffer3.order(ByteOrder.LITTLE_ENDIAN);
                    if (byteBuffer3.getInt() == 134695760) {
                        j8 += 4;
                        if (j8 > j) {
                            throw new ZipFormatException("Data Descriptor of " + name + " overlaps with Central Directory. Data Descriptor end: " + j7 + ", CD start: " + j);
                        }
                    }
                    j7 = j8;
                } else {
                    throw new ZipFormatException("Data Descriptor of " + name + " overlaps with Central Directory. Data Descriptor end: " + j7 + ", CD start: " + j);
                }
            } else {
                j3 = compressedSize;
            }
            return new LocalFileRecord(name, nameSizeBytes, byteBuffer2, j2, j7 - j2, unsignedInt16 + 30 + unsignedInt162, j3, z6, uncompressedSize);
        } catch (IOException e) {
            throw new IOException("Failed to read Local File Header of " + name, e);
        }
    }

    public static byte[] getUncompressedData(DataSource dataSource, CentralDirectoryRecord centralDirectoryRecord, long j) {
        if (centralDirectoryRecord.getUncompressedSize() > 2147483647L) {
            String name = centralDirectoryRecord.getName();
            throw new IOException(name + " too large: " + centralDirectoryRecord.getUncompressedSize());
        }
        byte[] bArr = new byte[(int) centralDirectoryRecord.getUncompressedSize()];
        outputUncompressedData(dataSource, centralDirectoryRecord, j, new ByteBufferSink(ByteBuffer.wrap(bArr)));
        return bArr;
    }

    public static void outputUncompressedData(DataSource dataSource, CentralDirectoryRecord centralDirectoryRecord, long j, DataSink dataSink) {
        getRecord(dataSource, centralDirectoryRecord, j, false, false).outputUncompressedData(dataSource, dataSink);
    }

    public void outputUncompressedData(DataSource dataSource, DataSink dataSink) {
        long j = this.mStartOffsetInArchive + this.mDataStartOffset;
        try {
            if (this.mDataCompressed) {
                try {
                    InflateSinkAdapter inflateSinkAdapter = new InflateSinkAdapter(dataSink);
                    dataSource.feed(j, this.mDataSize, inflateSinkAdapter);
                    long outputByteCount = inflateSinkAdapter.getOutputByteCount();
                    if (outputByteCount != this.mUncompressedDataSize) {
                        throw new ZipFormatException("Unexpected size of uncompressed data of " + this.mName + ". Expected: " + this.mUncompressedDataSize + " bytes, actual: " + outputByteCount + " bytes");
                    } else {
                        inflateSinkAdapter.close();
                        return;
                    }
                } catch (IOException e) {
                    if (e.getCause() instanceof DataFormatException) {
                        throw new ZipFormatException("Data of entry " + this.mName + " malformed", e);
                    }
                    throw e;
                }
            }
            dataSource.feed(j, this.mDataSize, dataSink);
        } catch (IOException e2) {
            throw new IOException("Failed to read data of " + (this.mDataCompressed ? "compressed" : "uncompressed") + " entry " + this.mName, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class InflateSinkAdapter implements DataSink, Closeable {
        private boolean mClosed;
        private final DataSink mDelegate;
        private Inflater mInflater;
        private byte[] mInputBuffer;
        private byte[] mOutputBuffer;
        private long mOutputByteCount;

        private InflateSinkAdapter(DataSink dataSink) {
            this.mInflater = new Inflater(true);
            this.mDelegate = dataSink;
        }

        private void checkNotClosed() {
            if (this.mClosed) {
                throw new IllegalStateException("Closed");
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.mClosed = true;
            this.mInputBuffer = null;
            this.mOutputBuffer = null;
            Inflater inflater = this.mInflater;
            if (inflater != null) {
                inflater.end();
                this.mInflater = null;
            }
        }

        @Override // com.android.apksig.util.DataSink
        public void consume(ByteBuffer byteBuffer) {
            checkNotClosed();
            if (byteBuffer.hasArray()) {
                consume(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
                ByteBuffer byteBuffer2 = (ByteBuffer) byteBuffer.position(byteBuffer.limit());
                return;
            }
            if (this.mInputBuffer == null) {
                this.mInputBuffer = new byte[65536];
            }
            while (byteBuffer.hasRemaining()) {
                int min = Math.min(byteBuffer.remaining(), this.mInputBuffer.length);
                byteBuffer.get(this.mInputBuffer, 0, min);
                consume(this.mInputBuffer, 0, min);
            }
        }

        public long getOutputByteCount() {
            return this.mOutputByteCount;
        }

        @Override // com.android.apksig.util.DataSink
        public void consume(byte[] bArr, int i, int i2) {
            checkNotClosed();
            this.mInflater.setInput(bArr, i, i2);
            if (this.mOutputBuffer == null) {
                this.mOutputBuffer = new byte[65536];
            }
            while (!this.mInflater.finished()) {
                try {
                    int inflate = this.mInflater.inflate(this.mOutputBuffer);
                    if (inflate != 0) {
                        this.mDelegate.consume(this.mOutputBuffer, 0, inflate);
                        this.mOutputByteCount += inflate;
                    } else {
                        return;
                    }
                } catch (DataFormatException e) {
                    throw new IOException("Failed to inflate data", e);
                }
            }
        }
    }
}
