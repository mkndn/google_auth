package com.google.android.apps.authenticator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.apps.authenticator2.R$styleable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CountdownIndicator extends View {
    private static final int DEFAULT_COLOR = -13606720;
    private RectF mDrawingRect;
    private double mPhase;
    private final Paint mRemainingSectorPaint;

    public CountdownIndicator(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f = (float) (this.mPhase * 360.0d);
        float f2 = 270.0f - f;
        if (this.mDrawingRect == null) {
            this.mDrawingRect = new RectF(1.0f, 1.0f, getWidth() - 1, getHeight() - 1);
        }
        if (f2 < 360.0f) {
            canvas.drawArc(this.mDrawingRect, f2, f, true, this.mRemainingSectorPaint);
        } else {
            canvas.drawOval(this.mDrawingRect, this.mRemainingSectorPaint);
        }
    }

    public void setIndicatorColor(int i) {
        this.mRemainingSectorPaint.setColor(i);
    }

    public void setPhase(double d) {
        if (d >= 0.0d && d <= 1.0d) {
            this.mPhase = d;
            invalidate();
            return;
        }
        throw new IllegalArgumentException("phase: " + d);
    }

    public CountdownIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.CountdownIndicator, 0, 0);
        int i = DEFAULT_COLOR;
        if (obtainStyledAttributes != null) {
            int indexCount = obtainStyledAttributes.getIndexCount();
            int i2 = DEFAULT_COLOR;
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.CountdownIndicator_countdownIndicatorColor) {
                    i2 = obtainStyledAttributes.getColor(index, DEFAULT_COLOR);
                }
            }
            i = i2;
        }
        Paint paint = new Paint(1);
        this.mRemainingSectorPaint = paint;
        paint.setColor(i);
    }
}
