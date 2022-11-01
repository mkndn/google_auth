package com.mobeta.android.dslv;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DragSortItemViewCheckable extends DragSortItemView implements Checkable {
    public DragSortItemViewCheckable(Context context) {
        super(context);
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        View childAt = getChildAt(0);
        if (!(childAt instanceof Checkable)) {
            return false;
        }
        return ((Checkable) childAt).isChecked();
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        View childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).setChecked(z);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        View childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).toggle();
        }
    }
}
