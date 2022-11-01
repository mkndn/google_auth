package com.google.android.material.shape;

import android.view.View;
import com.google.android.material.internal.ViewUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MaterialShapeUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static CornerTreatment createCornerTreatment(int i) {
        switch (i) {
            case 0:
                return new RoundedCornerTreatment();
            case 1:
                return new CutCornerTreatment();
            default:
                return createDefaultCornerTreatment();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CornerTreatment createDefaultCornerTreatment() {
        return new RoundedCornerTreatment();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static EdgeTreatment createDefaultEdgeTreatment() {
        return new EdgeTreatment();
    }

    public static void setParentAbsoluteElevation(View view, MaterialShapeDrawable materialShapeDrawable) {
        if (materialShapeDrawable.isElevationOverlayEnabled()) {
            materialShapeDrawable.setParentAbsoluteElevation(ViewUtils.getParentAbsoluteElevation(view));
        }
    }
}
