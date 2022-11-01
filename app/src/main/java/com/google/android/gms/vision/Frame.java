package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Frame {
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;
    private Bitmap bitmap;
    private ByteBuffer grayscaleData;
    private final Metadata metadata;
    private PlanesHolder planesHolder;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private final Frame frame = new Frame();

        public Frame build() {
            if (this.frame.grayscaleData == null && this.frame.bitmap == null && this.frame.planesHolder == null) {
                throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
            }
            return this.frame;
        }

        public Builder setId(int i) {
            this.frame.getMetadata().id = i;
            return this;
        }

        public Builder setImageData(ByteBuffer byteBuffer, int i, int i2, int i3) {
            if (byteBuffer == null) {
                throw new IllegalArgumentException("Null image data supplied.");
            }
            if (byteBuffer.capacity() < i * i2) {
                throw new IllegalArgumentException("Invalid image data size.");
            }
            switch (i3) {
                case 16:
                case 17:
                case 842094169:
                    this.frame.grayscaleData = byteBuffer;
                    Metadata metadata = this.frame.getMetadata();
                    metadata.width = i;
                    metadata.height = i2;
                    metadata.format = i3;
                    return this;
                default:
                    throw new IllegalArgumentException("Unsupported image format: " + i3);
            }
        }

        public Builder setRotation(int i) {
            this.frame.getMetadata().rotation = i;
            return this;
        }

        public Builder setTimestampMillis(long j) {
            this.frame.getMetadata().timestampMillis = j;
            return this;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class PlanesHolder {
    }

    private Frame() {
        this.metadata = new Metadata();
        this.grayscaleData = null;
        this.planesHolder = null;
        this.bitmap = null;
    }

    private ByteBuffer convertBitmapToGrayscale() {
        Bitmap bitmap = this.bitmap;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = this.bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        this.bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((Color.red(iArr[i2]) * 0.299f) + (Color.green(iArr[i2]) * 0.587f) + (Color.blue(iArr[i2]) * 0.114f));
        }
        return ByteBuffer.wrap(bArr);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public ByteBuffer getGrayscaleImageData() {
        if (this.bitmap != null) {
            return convertBitmapToGrayscale();
        }
        return this.grayscaleData;
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Metadata {
        private int format;
        private int height;
        private int id;
        private int rotation;
        private long timestampMillis;
        private int width;

        public Metadata() {
            this.format = -1;
        }

        public int getFormat() {
            return this.format;
        }

        public int getHeight() {
            return this.height;
        }

        public int getId() {
            return this.id;
        }

        public int getRotation() {
            return this.rotation;
        }

        public long getTimestampMillis() {
            return this.timestampMillis;
        }

        public int getWidth() {
            return this.width;
        }

        public void makeUpright() {
            if (this.rotation % 2 != 0) {
                int i = this.width;
                this.width = this.height;
                this.height = i;
            }
            this.rotation = 0;
        }

        public Metadata(Metadata metadata) {
            this.format = -1;
            this.width = metadata.getWidth();
            this.height = metadata.getHeight();
            this.id = metadata.getId();
            this.timestampMillis = metadata.getTimestampMillis();
            this.rotation = metadata.getRotation();
            this.format = metadata.getFormat();
        }
    }
}
