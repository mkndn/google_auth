package com.google.android.apps.authenticator.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.mobeta.android.dslv.DragSortListView;

/* compiled from: PG */
/* loaded from: classes.dex */
public class EmptySpaceClickableDragSortListView extends DragSortListView {
    private OnEmptySpaceClickListener mOnEmptySpaceClickListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnEmptySpaceClickListener {
        void onEmptySpaceClick();
    }

    public EmptySpaceClickableDragSortListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        OnEmptySpaceClickListener onEmptySpaceClickListener;
        if (pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY()) == -1 && motionEvent.getAction() == 0 && (onEmptySpaceClickListener = this.mOnEmptySpaceClickListener) != null) {
            onEmptySpaceClickListener.onEmptySpaceClick();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setOnEmptySpaceClickListener(OnEmptySpaceClickListener onEmptySpaceClickListener) {
        this.mOnEmptySpaceClickListener = onEmptySpaceClickListener;
    }
}
