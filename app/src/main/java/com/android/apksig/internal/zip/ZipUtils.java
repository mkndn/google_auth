package com.android.apksig.internal.zip;

import androidx.core.internal.view.SupportMenu;
import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.util.DataSource;
import com.android.apksig.zip.ZipFormatException;
import com.android.apksig.zip.ZipSections;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ZipUtils {
    public static final short COMPRESSION_METHOD_DEFLATED = 8;
    public static final short COMPRESSION_METHOD_STORED = 0;
    public static final short GP_FLAG_DATA_DESCRIPTOR_USED = 8;
    public static final short GP_FLAG_EFS = 2048;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void assertByteOrderLittleEndian(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    public static Pair findZipEndOfCentralDirectoryRecord(DataSource dataSource) {
        if (dataSource.size() < 22) {
            return null;
        }
        Pair findZipEndOfCentralDirectoryRecord = findZipEndOfCentralDirectoryRecord(dataSource, 0);
        if (findZipEndOfCentralDirectoryRecord != null) {
            return findZipEndOfCentralDirectoryRecord;
        }
        return findZipEndOfCentralDirectoryRecord(dataSource, SupportMenu.USER_MASK);
    }

    public static int getUnsignedInt16(ByteBuffer byteBuffer) {
        return (char) byteBuffer.getShort();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getUnsignedInt32(ByteBuffer byteBuffer) {
        return byteBuffer.getInt() & 4294967295L;
    }

    public static long getZipEocdCentralDirectoryOffset(ByteBuffer byteBuffer) {
        assertByteOrderLittleEndian(byteBuffer);
        return getUnsignedInt32(byteBuffer, byteBuffer.position() + 16);
    }

    public static long getZipEocdCentralDirectorySizeBytes(ByteBuffer byteBuffer) {
        assertByteOrderLittleEndian(byteBuffer);
        return getUnsignedInt32(byteBuffer, byteBuffer.position() + 12);
    }

    public static int getZipEocdCentralDirectoryTotalRecordCount(ByteBuffer byteBuffer) {
        assertByteOrderLittleEndian(byteBuffer);
        return getUnsignedInt16(byteBuffer, byteBuffer.position() + 10);
    }

    public static List parseZipCentralDirectory(DataSource dataSource, ZipSections zipSections) {
        long zipCentralDirectorySizeBytes = zipSections.getZipCentralDirectorySizeBytes();
        if (zipCentralDirectorySizeBytes > 2147483647L) {
            throw new ApkFormatException("ZIP Central Directory too large: " + zipCentralDirectorySizeBytes);
        }
        long zipCentralDirectoryOffset = zipSections.getZipCentralDirectoryOffset();
        ByteBuffer byteBuffer = dataSource.getByteBuffer(zipCentralDirectoryOffset, (int) zipCentralDirectorySizeBytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        int zipCentralDirectoryRecordCount = zipSections.getZipCentralDirectoryRecordCount();
        ArrayList arrayList = new ArrayList(zipCentralDirectoryRecordCount);
        for (int i = 0; i < zipCentralDirectoryRecordCount; i++) {
            int position = byteBuffer.position();
            try {
                CentralDirectoryRecord record = CentralDirectoryRecord.getRecord(byteBuffer);
                if (!record.getName().endsWith("/")) {
                    arrayList.add(record);
                }
            } catch (ZipFormatException e) {
                throw new ApkFormatException("Malformed ZIP Central Directory record #" + (i + 1) + " at file offset " + (zipCentralDirectoryOffset + position), e);
            }
        }
        return arrayList;
    }

    public static int getUnsignedInt16(ByteBuffer byteBuffer, int i) {
        return (char) byteBuffer.getShort(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getUnsignedInt32(ByteBuffer byteBuffer, int i) {
        return byteBuffer.getInt(i) & 4294967295L;
    }

    private static Pair findZipEndOfCentralDirectoryRecord(DataSource dataSource, int i) {
        if (i >= 0 && i <= 65535) {
            long size = dataSource.size();
            if (size < 22) {
                return null;
            }
            int min = ((int) Math.min(i, (-22) + size)) + 22;
            long j = size - min;
            ByteBuffer byteBuffer = dataSource.getByteBuffer(j, min);
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            int findZipEndOfCentralDirectoryRecord = findZipEndOfCentralDirectoryRecord(byteBuffer);
            if (findZipEndOfCentralDirectoryRecord == -1) {
                return null;
            }
            ByteBuffer byteBuffer2 = (ByteBuffer) byteBuffer.position(findZipEndOfCentralDirectoryRecord);
            ByteBuffer slice = byteBuffer.slice();
            slice.order(ByteOrder.LITTLE_ENDIAN);
            return Pair.of(slice, Long.valueOf(j + findZipEndOfCentralDirectoryRecord));
        }
        throw new IllegalArgumentException("maxCommentSize: " + i);
    }

    private static int findZipEndOfCentralDirectoryRecord(ByteBuffer byteBuffer) {
        assertByteOrderLittleEndian(byteBuffer);
        int capacity = byteBuffer.capacity();
        if (capacity < 22) {
            return -1;
        }
        int i = capacity - 22;
        int min = Math.min(i, (int) SupportMenu.USER_MASK);
        for (int i2 = 0; i2 <= min; i2++) {
            int i3 = i - i2;
            if (byteBuffer.getInt(i3) == 101010256 && getUnsignedInt16(byteBuffer, i3 + 20) == i2) {
                return i3;
            }
        }
        return -1;
    }
}
