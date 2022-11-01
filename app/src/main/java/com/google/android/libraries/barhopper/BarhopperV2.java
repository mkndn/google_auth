package com.google.android.libraries.barhopper;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.barhopper.deeplearning.BarcodeDetectorClientOptions;
import java.io.Closeable;
import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BarhopperV2 implements Closeable {
    private static final long NULL_NATIVE_CONTEXT = 0;
    private static final String TAG = BarhopperV2.class.getSimpleName();
    private long nativeContext;

    public BarhopperV2() {
        System.loadLibrary("barhopper_v2");
    }

    private native void closeNative(long j);

    private native long createNative();

    private native long createNativeWithClientOptions(byte[] bArr);

    public static native Barcode parseRawValue(String str, int i);

    private native Barcode[] recognizeBitmapNative(long j, Bitmap bitmap, RecognitionOptions recognitionOptions);

    private native Barcode[] recognizeBufferNative(long j, int i, int i2, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native Barcode[] recognizeNative(long j, int i, int i2, byte[] bArr, RecognitionOptions recognitionOptions);

    private native Barcode[] recognizeStridedBufferNative(long j, int i, int i2, int i3, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native Barcode[] recognizeStridedNative(long j, int i, int i2, int i3, byte[] bArr, RecognitionOptions recognitionOptions);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j = this.nativeContext;
        if (j != 0) {
            closeNative(j);
            this.nativeContext = 0L;
        }
    }

    public void create() {
        if (this.nativeContext != 0) {
            Log.w(TAG, "Native context already exists.");
            return;
        }
        long createNative = createNative();
        this.nativeContext = createNative;
        if (createNative == 0) {
            throw new RuntimeException("Failed to create native context.");
        }
    }

    protected void finalize() {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public Barcode[] recognize(int i, int i2, int i3, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j == 0) {
            throw new RuntimeException("Native context does not exist.");
        }
        return recognizeStridedBufferNative(j, i, i2, i3, byteBuffer, recognitionOptions);
    }

    public Barcode[] recognize(int i, int i2, int i3, byte[] bArr, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j == 0) {
            throw new RuntimeException("Native context does not exist.");
        }
        return recognizeStridedNative(j, i, i2, i3, bArr, recognitionOptions);
    }

    public Barcode[] recognize(int i, int i2, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j == 0) {
            throw new RuntimeException("Native context does not exist.");
        }
        return recognizeBufferNative(j, i, i2, byteBuffer, recognitionOptions);
    }

    public void create(BarcodeDetectorClientOptions barcodeDetectorClientOptions) {
        if (this.nativeContext != 0) {
            Log.w(TAG, "Native context already exists.");
            return;
        }
        long createNativeWithClientOptions = createNativeWithClientOptions(barcodeDetectorClientOptions.toByteArray());
        this.nativeContext = createNativeWithClientOptions;
        if (createNativeWithClientOptions == 0) {
            throw new IllegalStateException("Failed to create native context with client options.");
        }
    }

    public Barcode[] recognize(int i, int i2, byte[] bArr, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j == 0) {
            throw new RuntimeException("Native context does not exist.");
        }
        return recognizeNative(j, i, i2, bArr, recognitionOptions);
    }

    public Barcode[] recognize(Bitmap bitmap, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j == 0) {
            throw new RuntimeException("Native context does not exist.");
        }
        return recognizeBitmapNative(j, bitmap, recognitionOptions);
    }
}
