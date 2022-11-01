package com.google.android.libraries.material.speeddial;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FloatingSpeedDialViewHolder extends BaseSpeedDialViewHolder {
    private final FloatingActionButton fab;
    private final TextView label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FloatingSpeedDialViewHolder(View view) {
        super(view);
        this.fab = (FloatingActionButton) view.findViewById(R$id.mtrl_internal_speed_dial_item_fab);
        this.label = (TextView) view.findViewById(R$id.mtrl_internal_speed_dial_item_label);
    }

    private static int getColorBackground(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842801, typedValue, true);
        return typedValue.data;
    }

    private static Drawable getSelectableItemBackground(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843534, typedValue, true);
        return AppCompatResources.getDrawable(context, typedValue.resourceId);
    }

    public FloatingActionButton getFab() {
        return this.fab;
    }

    public void setLabel(int i) {
        this.label.setVisibility(0);
        this.label.setText(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLabelMaterialShapeDrawableBackground() {
        Context context = this.label.getContext();
        float dimension = context.getResources().getDimension(R$dimen.mtrl_floating_speed_dial_label_elevation);
        this.label.setBackgroundDrawable(createLabelBackground(context));
        ViewCompat.setElevation(this.label, dimension);
    }

    private static Drawable createLabelBackground(Context context) {
        float dimension = context.getResources().getDimension(R$dimen.speed_dial_label_corner_radius);
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        materialShapeDrawable.setTint(getColorBackground(context));
        materialShapeDrawable.setShapeAppearanceModel(ShapeAppearanceModel.builder().setAllCorners(0, dimension).build());
        return new LayerDrawable(new Drawable[]{materialShapeDrawable, getSelectableItemBackground(context)});
    }
}
