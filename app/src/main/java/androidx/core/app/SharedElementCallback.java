package androidx.core.app;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SharedElementCallback {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnSharedElementsReadyListener {
    }

    public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
        throw null;
    }

    public View onCreateSnapshotView(Context context, Parcelable parcelable) {
        throw null;
    }

    public void onMapSharedElements(List list, Map map) {
        throw null;
    }

    public void onRejectSharedElements(List list) {
        throw null;
    }

    public void onSharedElementEnd(List list, List list2, List list3) {
        throw null;
    }

    public void onSharedElementStart(List list, List list2, List list3) {
        throw null;
    }

    public void onSharedElementsArrived(List list, List list2, OnSharedElementsReadyListener onSharedElementsReadyListener) {
        throw null;
    }
}
