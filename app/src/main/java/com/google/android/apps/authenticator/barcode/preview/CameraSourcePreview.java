package com.google.android.apps.authenticator.barcode.preview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import com.google.android.apps.authenticator.barcode.BarcodeCentralFilter;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.CameraSource;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CameraSourcePreview extends ViewGroup {
    private static final int DEFAULT_PREVIEW_HEIGHT = 320;
    private static final int DEFAULT_PREVIEW_WIDTH = 240;
    private static final String TAG = "CameraSourcePreview";
    private CameraSource cameraSource;
    private GraphicOverlay graphicOverlay;
    private boolean startRequested;
    private boolean surfaceAvailable;
    private final SurfaceView surfaceView;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class SurfaceCallback implements SurfaceHolder.Callback {
        private SurfaceCallback() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            CameraSourcePreview.this.surfaceAvailable = true;
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            CameraSourcePreview.this.surfaceAvailable = true;
            CameraSourcePreview.this.tryToStart();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            CameraSourcePreview.this.surfaceAvailable = false;
        }
    }

    public CameraSourcePreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.startRequested = false;
        this.surfaceAvailable = false;
        SurfaceView surfaceView = new SurfaceView(context);
        this.surfaceView = surfaceView;
        surfaceView.getHolder().addCallback(new SurfaceCallback());
        addView(surfaceView);
    }

    private boolean isPortraitMode() {
        return getContext().getResources().getConfiguration().orientation == 1;
    }

    private void startIfReady() {
        if (this.startRequested && this.surfaceAvailable) {
            try {
                this.cameraSource.start(this.surfaceView.getHolder());
                requestLayout();
            } catch (IOException e) {
                Log.e(TAG, "Could not start camera source", e);
                release();
            } catch (SecurityException e2) {
                Log.e(TAG, "Do not have permission to start the camera", e2);
            } catch (RuntimeException e3) {
                Log.e(TAG, "Runtime Exception!", e3);
            }
            this.graphicOverlay.showContent();
            this.startRequested = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToStart() {
        try {
            startIfReady();
        } catch (SecurityException e) {
            Log.e(TAG, "Do not have permission to start the camera", e);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(i, i2);
        }
    }

    public void release() {
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource != null) {
            cameraSource.release();
            this.cameraSource = null;
        }
    }

    public void start(CameraSource cameraSource, GraphicOverlay graphicOverlay) {
        this.graphicOverlay = graphicOverlay;
        if (cameraSource != null) {
            this.cameraSource = cameraSource;
            this.startRequested = true;
            startIfReady();
            return;
        }
        release();
    }

    public void stop() {
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource != null) {
            cameraSource.stop();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Size previewSize;
        CameraSource cameraSource = this.cameraSource;
        int i5 = 320;
        int i6 = 240;
        if (cameraSource != null && (previewSize = cameraSource.getPreviewSize()) != null) {
            i6 = previewSize.getWidth();
            i5 = previewSize.getHeight();
        }
        if (!isPortraitMode()) {
            int i7 = i6;
            i6 = i5;
            i5 = i7;
        }
        BarcodeCentralFilter.storeCameraSize(new Size(i5, i6));
        int i8 = i3 - i;
        int i9 = i4 - i2;
        if (isPortraitMode()) {
            double d = i8;
            double d2 = i5;
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = i6;
            Double.isNaN(d3);
            int i10 = (int) ((d / d2) * d3);
            int i11 = (i9 - i10) / 2;
            for (int i12 = 0; i12 < getChildCount(); i12++) {
                getChildAt(i12).layout(0, i11, i8, i10 + i11);
            }
        } else {
            double d4 = i9;
            double d5 = i6;
            Double.isNaN(d4);
            Double.isNaN(d5);
            double d6 = i5;
            Double.isNaN(d6);
            int i13 = (int) ((d4 / d5) * d6);
            int i14 = (i8 - i13) / 2;
            for (int i15 = 0; i15 < getChildCount(); i15++) {
                getChildAt(i15).layout(i14, 0, i13 + i14, i9);
            }
        }
        tryToStart();
    }
}
