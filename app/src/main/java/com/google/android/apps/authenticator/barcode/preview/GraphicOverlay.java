package com.google.android.apps.authenticator.barcode.preview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.google.android.apps.authenticator.barcode.BarcodeCentralFilter;
import com.google.android.apps.authenticator2.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GraphicOverlay extends View {
    private static final int BARCODE_INSTRUCTION_TEXT_MARGIN_TOP = 40;
    private static final int BARCODE_INSTRUCTION_TEXT_SIZE = 16;
    private boolean drawContent;
    private Bitmap scannerBitmap;

    public GraphicOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.drawContent = false;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (!this.drawContent) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Rect squareFrame = BarcodeCentralFilter.getSquareFrame(width, height);
        if (this.scannerBitmap == null) {
            this.scannerBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.barcode_scanner_image);
        }
        Paint paint = new Paint(1);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.barcode_scanner_border_background));
        float f = width;
        canvas.drawRect(0.0f, 0.0f, f, squareFrame.top, paint);
        canvas.drawRect(0.0f, squareFrame.top, squareFrame.left, squareFrame.bottom + 1, paint);
        canvas.drawRect(squareFrame.right + 1, squareFrame.top, f, squareFrame.bottom + 1, paint);
        canvas.drawRect(0.0f, squareFrame.bottom + 1, f, height, paint);
        paint.setAlpha(255);
        canvas.drawBitmap(this.scannerBitmap, (Rect) null, BarcodeCentralFilter.getExtraSquareFrame(width, height), paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-1);
        paint2.setTypeface(Typeface.create("sans-serif", 0));
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(TypedValue.applyDimension(2, 16.0f, getResources().getDisplayMetrics()));
        canvas.drawText(getResources().getString(R.string.barcode_instruction_text), width / 2, squareFrame.bottom + TypedValue.applyDimension(2, 40.0f, getResources().getDisplayMetrics()) + ((paint2.ascent() + paint2.descent()) / 2.0f), paint2);
    }

    public void showContent() {
        this.drawContent = true;
        invalidate();
    }
}
