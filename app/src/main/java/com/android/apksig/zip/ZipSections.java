package com.android.apksig.zip;

import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ZipSections {
    private final long mCentralDirectoryOffset;
    private final int mCentralDirectoryRecordCount;
    private final long mCentralDirectorySizeBytes;
    private final ByteBuffer mEocd;
    private final long mEocdOffset;

    public ZipSections(long j, long j2, int i, long j3, ByteBuffer byteBuffer) {
        this.mCentralDirectoryOffset = j;
        this.mCentralDirectorySizeBytes = j2;
        this.mCentralDirectoryRecordCount = i;
        this.mEocdOffset = j3;
        this.mEocd = byteBuffer;
    }

    public long getZipCentralDirectoryOffset() {
        return this.mCentralDirectoryOffset;
    }

    public int getZipCentralDirectoryRecordCount() {
        return this.mCentralDirectoryRecordCount;
    }

    public long getZipCentralDirectorySizeBytes() {
        return this.mCentralDirectorySizeBytes;
    }

    public ByteBuffer getZipEndOfCentralDirectory() {
        return this.mEocd;
    }

    public long getZipEndOfCentralDirectoryOffset() {
        return this.mEocdOffset;
    }
}
