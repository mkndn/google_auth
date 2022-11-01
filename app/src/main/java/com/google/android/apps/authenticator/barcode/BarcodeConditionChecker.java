package com.google.android.apps.authenticator.barcode;

import android.app.Activity;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BarcodeConditionChecker {
    private BarcodeConditionChecker() {
    }

    public static boolean isCameraAvailableOnDevice(Activity activity) {
        if (!activity.getPackageManager().hasSystemFeature("android.hardware.camera") && !activity.getPackageManager().hasSystemFeature("android.hardware.camera.front")) {
            return false;
        }
        return true;
    }
}
