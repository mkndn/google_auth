package com.google.android.apps.authenticator.barcode;

import android.graphics.Point;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.libraries.barhopper.Barcode;
import com.google.android.libraries.barhopper.BarhopperV2;
import com.google.android.libraries.barhopper.RecognitionOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
final class QrCodeDetector extends Detector {
    private final BarhopperV2 barhopper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QrCodeDetector() {
        BarhopperV2 barhopperV2 = new BarhopperV2();
        this.barhopper = barhopperV2;
        barhopperV2.create();
    }

    private static void applyRotationToBarcodeCorners(Barcode barcode, Frame.Metadata metadata) {
        Point[] pointArr;
        for (Point point : barcode.cornerPoints) {
            switch (metadata.getRotation()) {
                case 0:
                    break;
                case 1:
                    point.set(metadata.getHeight() - point.y, point.x);
                    break;
                case 2:
                    point.set(metadata.getWidth() - point.x, metadata.getHeight() - point.y);
                    break;
                case 3:
                    point.set(point.y, metadata.getWidth() - point.x);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected rotation " + metadata.getRotation());
            }
        }
    }

    private static RecognitionOptions recognizeQrCode() {
        RecognitionOptions recognitionOptions = new RecognitionOptions();
        recognitionOptions.setBarcodeFormats(256);
        return recognitionOptions;
    }

    @Override // com.google.android.gms.vision.Detector
    public SparseArray detect(Frame frame) {
        Barcode[] recognize;
        if (frame.getBitmap() != null) {
            recognize = this.barhopper.recognize(frame.getBitmap(), recognizeQrCode());
        } else {
            recognize = this.barhopper.recognize(frame.getMetadata().getWidth(), frame.getMetadata().getHeight(), frame.getGrayscaleImageData().array(), recognizeQrCode());
        }
        if (recognize == null) {
            Log.w("QRCodeDetector", "image data is invalid");
            return new SparseArray();
        }
        SparseArray sparseArray = new SparseArray(recognize.length);
        for (Barcode barcode : recognize) {
            applyRotationToBarcodeCorners(barcode, frame.getMetadata());
            sparseArray.append(barcode.rawValue.hashCode(), barcode);
        }
        return sparseArray;
    }

    @Override // com.google.android.gms.vision.Detector
    public void release() {
        super.release();
        this.barhopper.close();
    }
}
