package com.google.android.apps.authenticator.barcode;

import com.google.android.gms.vision.Tracker;
import com.google.android.libraries.barhopper.Barcode;

/* compiled from: PG */
/* loaded from: classes.dex */
class BarcodeTracker extends Tracker {
    private OnDetectionListener onDetectionListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnDetectionListener {
        void onNewDetection(int i, Barcode barcode);
    }

    @Override // com.google.android.gms.vision.Tracker
    public void onNewItem(int i, Barcode barcode) {
        OnDetectionListener onDetectionListener = this.onDetectionListener;
        if (onDetectionListener != null) {
            onDetectionListener.onNewDetection(i, barcode);
        }
    }

    public void setDetectionListener(OnDetectionListener onDetectionListener) {
        this.onDetectionListener = onDetectionListener;
    }
}
