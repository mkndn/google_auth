package android.support.v7.widget;

import android.graphics.Rect;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface FitWindowsViewGroup {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnFitSystemWindowsListener {
        void onFitSystemWindows(Rect rect);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener onFitSystemWindowsListener);
}
