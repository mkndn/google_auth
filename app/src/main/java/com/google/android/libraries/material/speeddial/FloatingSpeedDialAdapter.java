package com.google.android.libraries.material.speeddial;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class FloatingSpeedDialAdapter extends BaseSpeedDialAdapter {
    private final LayoutInflater inflater;
    private final int layoutResId;
    private OnItemSelectedListener onItemSelectedListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnItemSelectedListener {
        void onItemSelected(FloatingSpeedDialViewHolder floatingSpeedDialViewHolder, int i);
    }

    public FloatingSpeedDialAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.FloatingSpeedDialAdapter, R$attr.floatingSpeedDialAdapterStyle, 0);
        this.layoutResId = obtainStyledAttributes.getResourceId(R$styleable.FloatingSpeedDialAdapter_android_layout, R$layout.mtrl_internal_speed_dial_item);
        obtainStyledAttributes.recycle();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        return R$id.mtrl_internal_speed_dial_item;
    }

    protected boolean isLabelMaterialShapeDrawableBackgroundEnabled() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateViewHolder$0$com-google-android-libraries-material-speeddial-FloatingSpeedDialAdapter  reason: not valid java name */
    public /* synthetic */ void m265xc7192f4f(FloatingSpeedDialViewHolder floatingSpeedDialViewHolder, View view) {
        OnItemSelectedListener onItemSelectedListener = this.onItemSelectedListener;
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(floatingSpeedDialViewHolder, floatingSpeedDialViewHolder.getAdapterPosition());
        }
    }

    public final void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final FloatingSpeedDialViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final FloatingSpeedDialViewHolder floatingSpeedDialViewHolder = new FloatingSpeedDialViewHolder(this.inflater.inflate(this.layoutResId, viewGroup, false));
        if (isLabelMaterialShapeDrawableBackgroundEnabled()) {
            floatingSpeedDialViewHolder.setLabelMaterialShapeDrawableBackground();
        }
        floatingSpeedDialViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.material.speeddial.FloatingSpeedDialAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FloatingSpeedDialAdapter.this.m265xc7192f4f(floatingSpeedDialViewHolder, view);
            }
        });
        return floatingSpeedDialViewHolder;
    }
}
