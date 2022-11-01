package com.android.apksig.internal.zip;

import com.android.apksig.zip.ZipFormatException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CentralDirectoryRecord {
    public static final Comparator BY_LOCAL_FILE_HEADER_OFFSET_COMPARATOR = new ByLocalFileHeaderOffsetComparator();
    private final long mCompressedSize;
    private final short mCompressionMethod;
    private final long mCrc32;
    private final ByteBuffer mData;
    private final short mGpFlags;
    private final int mLastModificationDate;
    private final int mLastModificationTime;
    private final long mLocalFileHeaderOffset;
    private final String mName;
    private final int mNameSizeBytes;
    private final long mUncompressedSize;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class ByLocalFileHeaderOffsetComparator implements Comparator {
        private ByLocalFileHeaderOffsetComparator() {
        }

        @Override // java.util.Comparator
        public int compare(CentralDirectoryRecord centralDirectoryRecord, CentralDirectoryRecord centralDirectoryRecord2) {
            long localFileHeaderOffset = centralDirectoryRecord.getLocalFileHeaderOffset();
            long localFileHeaderOffset2 = centralDirectoryRecord2.getLocalFileHeaderOffset();
            if (localFileHeaderOffset > localFileHeaderOffset2) {
                return 1;
            }
            return localFileHeaderOffset < localFileHeaderOffset2 ? -1 : 0;
        }
    }

    private CentralDirectoryRecord(ByteBuffer byteBuffer, short s, short s2, int i, int i2, long j, long j2, long j3, long j4, String str, int i3) {
        this.mData = byteBuffer;
        this.mGpFlags = s;
        this.mCompressionMethod = s2;
        this.mLastModificationDate = i2;
        this.mLastModificationTime = i;
        this.mCrc32 = j;
        this.mCompressedSize = j2;
        this.mUncompressedSize = j3;
        this.mLocalFileHeaderOffset = j4;
        this.mName = str;
        this.mNameSizeBytes = i3;
    }

    public static CentralDirectoryRecord getRecord(ByteBuffer byteBuffer) {
        ZipUtils.assertByteOrderLittleEndian(byteBuffer);
        if (byteBuffer.remaining() < 46) {
            throw new ZipFormatException("Input too short. Need at least: 46 bytes, available: " + byteBuffer.remaining() + " bytes", new BufferUnderflowException());
        }
        int position = byteBuffer.position();
        int i = byteBuffer.getInt();
        if (i == 33639248) {
            ByteBuffer byteBuffer2 = (ByteBuffer) byteBuffer.position(position + 8);
            short s = byteBuffer.getShort();
            short s2 = byteBuffer.getShort();
            int unsignedInt16 = ZipUtils.getUnsignedInt16(byteBuffer);
            int unsignedInt162 = ZipUtils.getUnsignedInt16(byteBuffer);
            long unsignedInt32 = ZipUtils.getUnsignedInt32(byteBuffer);
            long unsignedInt322 = ZipUtils.getUnsignedInt32(byteBuffer);
            long unsignedInt323 = ZipUtils.getUnsignedInt32(byteBuffer);
            int unsignedInt163 = ZipUtils.getUnsignedInt16(byteBuffer);
            int unsignedInt164 = ZipUtils.getUnsignedInt16(byteBuffer);
            int unsignedInt165 = ZipUtils.getUnsignedInt16(byteBuffer);
            ByteBuffer byteBuffer3 = (ByteBuffer) byteBuffer.position(position + 42);
            long unsignedInt324 = ZipUtils.getUnsignedInt32(byteBuffer);
            ByteBuffer byteBuffer4 = (ByteBuffer) byteBuffer.position(position);
            int i2 = unsignedInt163 + 46 + unsignedInt164 + unsignedInt165;
            if (i2 <= byteBuffer.remaining()) {
                String name = getName(byteBuffer, position + 46, unsignedInt163);
                ByteBuffer byteBuffer5 = (ByteBuffer) byteBuffer.position(position);
                int limit = byteBuffer.limit();
                int i3 = position + i2;
                try {
                    ByteBuffer byteBuffer6 = (ByteBuffer) byteBuffer.limit(i3);
                    ByteBuffer slice = byteBuffer.slice();
                    ByteBuffer byteBuffer7 = (ByteBuffer) byteBuffer.limit(limit);
                    ByteBuffer byteBuffer8 = (ByteBuffer) byteBuffer.position(i3);
                    return new CentralDirectoryRecord(slice, s, s2, unsignedInt16, unsignedInt162, unsignedInt32, unsignedInt322, unsignedInt323, unsignedInt324, name, unsignedInt163);
                } catch (Throwable th) {
                    ByteBuffer byteBuffer9 = (ByteBuffer) byteBuffer.limit(limit);
                    throw th;
                }
            }
            throw new ZipFormatException("Input too short. Need: " + i2 + " bytes, available: " + byteBuffer.remaining() + " bytes", new BufferUnderflowException());
        }
        throw new ZipFormatException("Not a Central Directory record. Signature: 0x" + Long.toHexString(i & 4294967295L));
    }

    public long getCompressedSize() {
        return this.mCompressedSize;
    }

    public short getCompressionMethod() {
        return this.mCompressionMethod;
    }

    public long getCrc32() {
        return this.mCrc32;
    }

    public short getGpFlags() {
        return this.mGpFlags;
    }

    public long getLocalFileHeaderOffset() {
        return this.mLocalFileHeaderOffset;
    }

    public String getName() {
        return this.mName;
    }

    public int getNameSizeBytes() {
        return this.mNameSizeBytes;
    }

    public long getUncompressedSize() {
        return this.mUncompressedSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getName(ByteBuffer byteBuffer, int i, int i2) {
        byte[] bArr;
        int i3;
        if (byteBuffer.hasArray()) {
            bArr = byteBuffer.array();
            i3 = byteBuffer.arrayOffset() + i;
        } else {
            bArr = new byte[i2];
            int position = byteBuffer.position();
            try {
                ByteBuffer byteBuffer2 = (ByteBuffer) byteBuffer.position(i);
                byteBuffer.get(bArr);
                ByteBuffer byteBuffer3 = (ByteBuffer) byteBuffer.position(position);
                i3 = 0;
            } catch (Throwable th) {
                ByteBuffer byteBuffer4 = (ByteBuffer) byteBuffer.position(position);
                throw th;
            }
        }
        return new String(bArr, i3, i2, StandardCharsets.UTF_8);
    }
}
