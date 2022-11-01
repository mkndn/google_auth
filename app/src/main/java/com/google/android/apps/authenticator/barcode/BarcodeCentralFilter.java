package com.google.android.apps.authenticator.barcode;

import android.graphics.Rect;
import com.google.android.gms.common.images.Size;
import com.google.android.libraries.barhopper.Barcode;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BarcodeCentralFilter {
    private static final float EXTRA_PADDING_SQUARE_PERCENT = 0.03f;
    private static final float SQUARE_SIZE_PERCENT = 0.65f;
    private static Size mCameraSize;
    private static int mCanvasHeight;
    private static int mCanvasWidth;
    private static Rect mSavedSquareFrame;

    public static boolean barcodeIsInsideScannerSquare(Barcode barcode) {
        if (mSavedSquareFrame == null || mCameraSize == null) {
            return false;
        }
        Rect boundingBox = barcode.getBoundingBox();
        int i = boundingBox.left;
        int i2 = boundingBox.top;
        int i3 = boundingBox.right;
        int i4 = boundingBox.bottom;
        int width = mCameraSize.getWidth();
        int height = mCameraSize.getHeight();
        int i5 = mCanvasWidth;
        int i6 = mCanvasHeight;
        return mSavedSquareFrame.contains(new Rect((i * i5) / width, (i2 * i6) / height, (i3 * i5) / width, (i4 * i6) / height));
    }

    public static Rect getExtraSquareFrame(int i, int i2) {
        int min = (int) (Math.min(i, i2) * SQUARE_SIZE_PERCENT);
        int i3 = (int) (min * EXTRA_PADDING_SQUARE_PERCENT);
        Rect rect = new Rect(((i - min) / 2) - i3, ((i2 - min) / 2) - i3, ((i + min) / 2) + i3, ((min + i2) / 2) + i3);
        mSavedSquareFrame = rect;
        mCanvasWidth = i;
        mCanvasHeight = i2;
        return rect;
    }

    public static Rect getSquareFrame(int i, int i2) {
        int min = (int) (Math.min(i, i2) * SQUARE_SIZE_PERCENT);
        return new Rect((i - min) / 2, (i2 - min) / 2, (i + min) / 2, (i2 + min) / 2);
    }

    public static void storeCameraSize(Size size) {
        mCameraSize = size;
    }
}
