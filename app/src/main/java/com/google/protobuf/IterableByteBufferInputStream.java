package com.google.protobuf;

import com.google.common.primitives.UnsignedBytes;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class IterableByteBufferInputStream extends InputStream {
    private long currentAddress;
    private byte[] currentArray;
    private int currentArrayOffset;
    private ByteBuffer currentByteBuffer;
    private int currentByteBufferPos;
    private int currentIndex;
    private int dataSize = 0;
    private boolean hasArray;
    private Iterator iterator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IterableByteBufferInputStream(Iterable iterable) {
        this.iterator = iterable.iterator();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            ByteBuffer byteBuffer = (ByteBuffer) it.next();
            this.dataSize++;
        }
        this.currentIndex = -1;
        if (!getNextByteBuffer()) {
            this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
            this.currentIndex = 0;
            this.currentByteBufferPos = 0;
            this.currentAddress = 0L;
        }
    }

    private boolean getNextByteBuffer() {
        this.currentIndex++;
        if (this.iterator.hasNext()) {
            ByteBuffer byteBuffer = (ByteBuffer) this.iterator.next();
            this.currentByteBuffer = byteBuffer;
            this.currentByteBufferPos = byteBuffer.position();
            if (this.currentByteBuffer.hasArray()) {
                this.hasArray = true;
                this.currentArray = this.currentByteBuffer.array();
                this.currentArrayOffset = this.currentByteBuffer.arrayOffset();
            } else {
                this.hasArray = false;
                this.currentAddress = UnsafeUtil.addressOffset(this.currentByteBuffer);
                this.currentArray = null;
            }
            return true;
        }
        return false;
    }

    private void updateCurrentByteBufferPos(int i) {
        int i2 = this.currentByteBufferPos + i;
        this.currentByteBufferPos = i2;
        if (i2 == this.currentByteBuffer.limit()) {
            getNextByteBuffer();
        }
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.currentIndex == this.dataSize) {
            return -1;
        }
        if (this.hasArray) {
            byte b = this.currentArray[this.currentByteBufferPos + this.currentArrayOffset];
            updateCurrentByteBufferPos(1);
            return b & UnsignedBytes.MAX_VALUE;
        }
        byte b2 = UnsafeUtil.getByte(this.currentByteBufferPos + this.currentAddress);
        updateCurrentByteBufferPos(1);
        return b2 & UnsignedBytes.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (this.currentIndex == this.dataSize) {
            return -1;
        }
        int limit = this.currentByteBuffer.limit();
        int i3 = this.currentByteBufferPos;
        int i4 = limit - i3;
        if (i2 > i4) {
            i2 = i4;
        }
        if (this.hasArray) {
            System.arraycopy(this.currentArray, i3 + this.currentArrayOffset, bArr, i, i2);
            updateCurrentByteBufferPos(i2);
        } else {
            int position = this.currentByteBuffer.position();
            ByteBuffer byteBuffer = (ByteBuffer) this.currentByteBuffer.position(this.currentByteBufferPos);
            this.currentByteBuffer.get(bArr, i, i2);
            ByteBuffer byteBuffer2 = (ByteBuffer) this.currentByteBuffer.position(position);
            updateCurrentByteBufferPos(i2);
        }
        return i2;
    }
}
