package com.google.android.apps.authenticator.migration;

import android.graphics.Bitmap;
import com.google.common.collect.ImmutableMap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class QRCodeEncoder {
    private static final int BLACK = -16777216;
    private static final ImmutableMap ENCODE_HINTS = ImmutableMap.of((Object) EncodeHintType.ERROR_CORRECTION, (Object) ErrorCorrectionLevel.M);
    private static final int WHITE = -1;

    private QRCodeEncoder() {
    }

    public static Bitmap encodeAsBitmap(String str) {
        try {
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, 1, 1, ENCODE_HINTS);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[width * height];
            for (int i = 0; i < height; i++) {
                int i2 = i * width;
                for (int i3 = 0; i3 < width; i3++) {
                    iArr[i2 + i3] = encode.get(i3, i) ? -16777216 : -1;
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return createBitmap;
        } catch (WriterException e) {
            throw new IllegalStateException(e);
        }
    }
}
