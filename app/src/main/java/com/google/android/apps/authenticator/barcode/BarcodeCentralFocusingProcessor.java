package com.google.android.apps.authenticator.barcode;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.FocusingProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.libraries.barhopper.Barcode;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BarcodeCentralFocusingProcessor extends FocusingProcessor {
    private final boolean mCentralFilterEnabled;

    public BarcodeCentralFocusingProcessor(Detector detector, Tracker tracker, Boolean bool) {
        super(detector, tracker);
        this.mCentralFilterEnabled = bool.booleanValue();
    }

    @Override // com.google.android.gms.vision.FocusingProcessor
    public int selectFocus(Detector.Detections detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            if (!this.mCentralFilterEnabled || BarcodeCentralFilter.barcodeIsInsideScannerSquare((Barcode) detectedItems.valueAt(i))) {
                return keyAt;
            }
        }
        return -1;
    }
}
