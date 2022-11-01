package com.google.android.gms.vision;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.vision.Frame;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.io.IOException;
import java.lang.Thread;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class CameraSource {
    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_FRONT = 1;
    private final IdentityHashMap bytesToByteBuffer;
    private Camera camera;
    private final Object cameraLock;
    private Context context;
    private SurfaceTexture dummySurfaceTexture;
    private int facing;
    private String focusMode;
    private FrameProcessingRunnable frameProcessor;
    private Size previewSize;
    private Thread processingThread;
    private boolean requestedAutoFocus;
    private float requestedFps;
    private int requestedPreviewHeight;
    private int requestedPreviewWidth;
    private int rotation;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private CameraSource cameraSource;
        private final Detector detector;

        public Builder(Context context, Detector detector) {
            CameraSource cameraSource = new CameraSource();
            this.cameraSource = cameraSource;
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            }
            if (detector == null) {
                throw new IllegalArgumentException("No detector supplied.");
            }
            this.detector = detector;
            cameraSource.context = context;
        }

        public CameraSource build() {
            CameraSource cameraSource = this.cameraSource;
            CameraSource cameraSource2 = this.cameraSource;
            cameraSource2.getClass();
            cameraSource.frameProcessor = new FrameProcessingRunnable(this.detector);
            return this.cameraSource;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            this.cameraSource.requestedAutoFocus = z;
            return this;
        }

        public Builder setFacing(int i) {
            if (i != 0 && i != 1) {
                throw new IllegalArgumentException("Invalid camera: " + i);
            }
            this.cameraSource.facing = i;
            return this;
        }

        public Builder setFocusMode(String str) {
            if (!str.equals("continuous-video") && !str.equals("continuous-picture")) {
                Log.w("CameraSource", String.format("FocusMode %s is not supported for now.", str));
                this.cameraSource.focusMode = null;
            } else {
                this.cameraSource.focusMode = str;
            }
            return this;
        }

        public Builder setRequestedFps(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("Invalid fps: " + f);
            }
            this.cameraSource.requestedFps = f;
            return this;
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i > 0 && i <= 1000000 && i2 > 0 && i2 <= 1000000) {
                this.cameraSource.requestedPreviewWidth = i;
                this.cameraSource.requestedPreviewHeight = i2;
                return this;
            }
            throw new IllegalArgumentException("Invalid preview size: " + i + "x" + i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CameraPreviewCallback implements Camera.PreviewCallback {
        private CameraPreviewCallback() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.frameProcessor.setNextFrame(bArr, camera);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class FrameProcessingRunnable implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = true;
        private Detector detector;
        private ByteBuffer pendingFrameData;
        private long pendingTimeMillis;
        private long startTimeMillis = SystemClock.elapsedRealtime();
        private final Object lock = new Object();
        private boolean active = true;
        private int pendingFrameId = 0;

        FrameProcessingRunnable(Detector detector) {
            this.detector = detector;
        }

        void release() {
            if (!$assertionsDisabled && CameraSource.this.processingThread != null && CameraSource.this.processingThread.getState() != Thread.State.TERMINATED) {
                throw new AssertionError();
            }
            Detector detector = this.detector;
            if (detector != null) {
                detector.release();
                this.detector = null;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            Frame build;
            ByteBuffer byteBuffer;
            while (true) {
                synchronized (this.lock) {
                    while (true) {
                        z = this.active;
                        if (!z || this.pendingFrameData != null) {
                            break;
                        }
                        try {
                            this.lock.wait();
                        } catch (InterruptedException e) {
                            Log.d("CameraSource", "Frame processing loop terminated.", e);
                            return;
                        }
                    }
                    if (!z) {
                        return;
                    }
                    build = new Frame.Builder().setImageData((ByteBuffer) Preconditions.checkNotNull(this.pendingFrameData), CameraSource.this.previewSize.getWidth(), CameraSource.this.previewSize.getHeight(), 17).setId(this.pendingFrameId).setTimestampMillis(this.pendingTimeMillis).setRotation(CameraSource.this.rotation).build();
                    byteBuffer = this.pendingFrameData;
                    this.pendingFrameData = null;
                }
                try {
                    ((Detector) Preconditions.checkNotNull(this.detector)).receiveFrame(build);
                } catch (Exception e2) {
                    Log.e("CameraSource", "Exception thrown from receiver.", e2);
                } finally {
                    ((Camera) Preconditions.checkNotNull(CameraSource.this.camera)).addCallbackBuffer(((ByteBuffer) Preconditions.checkNotNull(byteBuffer)).array());
                }
            }
        }

        void setActive(boolean z) {
            synchronized (this.lock) {
                this.active = z;
                this.lock.notifyAll();
            }
        }

        void setNextFrame(byte[] bArr, Camera camera) {
            synchronized (this.lock) {
                ByteBuffer byteBuffer = this.pendingFrameData;
                if (byteBuffer != null) {
                    camera.addCallbackBuffer(byteBuffer.array());
                    this.pendingFrameData = null;
                }
                if (!CameraSource.this.bytesToByteBuffer.containsKey(bArr)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.pendingTimeMillis = SystemClock.elapsedRealtime() - this.startTimeMillis;
                this.pendingFrameId++;
                this.pendingFrameData = (ByteBuffer) CameraSource.this.bytesToByteBuffer.get(bArr);
                this.lock.notifyAll();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SizePair {
        private Size picture;
        private Size preview;

        public SizePair(Camera.Size size, Camera.Size size2) {
            this.preview = new Size(size.width, size.height);
            if (size2 != null) {
                this.picture = new Size(size2.width, size2.height);
            }
        }

        public Size pictureSize() {
            return this.picture;
        }

        public Size previewSize() {
            return this.preview;
        }
    }

    private CameraSource() {
        this.cameraLock = new Object();
        this.facing = 0;
        this.requestedFps = 30.0f;
        this.requestedPreviewWidth = 1024;
        this.requestedPreviewHeight = 768;
        this.requestedAutoFocus = false;
        this.bytesToByteBuffer = new IdentityHashMap();
    }

    private Camera createCamera() {
        int idForRequestedCamera = getIdForRequestedCamera(this.facing);
        if (idForRequestedCamera == -1) {
            throw new IOException("Could not find requested camera.");
        }
        Camera open = Camera.open(idForRequestedCamera);
        SizePair selectSizePair = selectSizePair(open, this.requestedPreviewWidth, this.requestedPreviewHeight);
        if (selectSizePair == null) {
            throw new IOException("Could not find suitable preview size.");
        }
        Size pictureSize = selectSizePair.pictureSize();
        this.previewSize = selectSizePair.previewSize();
        int[] selectPreviewFpsRange = selectPreviewFpsRange(open, this.requestedFps);
        if (selectPreviewFpsRange == null) {
            throw new IOException("Could not find suitable preview frames per second range.");
        }
        Camera.Parameters parameters = open.getParameters();
        if (pictureSize != null) {
            parameters.setPictureSize(pictureSize.getWidth(), pictureSize.getHeight());
        }
        parameters.setPreviewSize(this.previewSize.getWidth(), this.previewSize.getHeight());
        parameters.setPreviewFpsRange(selectPreviewFpsRange[0], selectPreviewFpsRange[1]);
        parameters.setPreviewFormat(17);
        setRotation(open, parameters, idForRequestedCamera, ((WindowManager) Preconditions.checkNotNull((WindowManager) this.context.getSystemService("window"))).getDefaultDisplay().getRotation());
        if (this.focusMode != null) {
            if (parameters.getSupportedFocusModes().contains(this.focusMode)) {
                parameters.setFocusMode((String) Preconditions.checkNotNull(this.focusMode));
            } else {
                Log.w("CameraSource", String.format("FocusMode %s is not supported on this device.", this.focusMode));
                this.focusMode = null;
            }
        }
        if (this.focusMode == null && this.requestedAutoFocus) {
            if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                parameters.setFocusMode("continuous-video");
                this.focusMode = "continuous-video";
            } else {
                Log.i("CameraSource", "Camera auto focus is not supported on this device.");
            }
        }
        open.setParameters(parameters);
        open.setPreviewCallbackWithBuffer(new CameraPreviewCallback());
        open.addCallbackBuffer(createPreviewBuffer(this.previewSize));
        open.addCallbackBuffer(createPreviewBuffer(this.previewSize));
        open.addCallbackBuffer(createPreviewBuffer(this.previewSize));
        open.addCallbackBuffer(createPreviewBuffer(this.previewSize));
        return open;
    }

    private byte[] createPreviewBuffer(Size size) {
        double height = size.getHeight() * size.getWidth() * ImageFormat.getBitsPerPixel(17);
        Double.isNaN(height);
        byte[] bArr = new byte[((int) Math.ceil(height / 8.0d)) + 1];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray() && wrap.array() == bArr) {
            this.bytesToByteBuffer.put(bArr, wrap);
            return bArr;
        }
        throw new IllegalStateException("Failed to create valid buffer for camera source.");
    }

    static List generateValidPreviewSizeList(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        ArrayList arrayList = new ArrayList();
        for (Camera.Size size : supportedPreviewSizes) {
            float f = size.width / size.height;
            Iterator<Camera.Size> it = supportedPictureSizes.iterator();
            while (true) {
                if (it.hasNext()) {
                    Camera.Size next = it.next();
                    if (Math.abs(f - (next.width / next.height)) < 0.01f) {
                        arrayList.add(new SizePair(size, next));
                        break;
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (Camera.Size size2 : supportedPreviewSizes) {
                arrayList.add(new SizePair(size2, null));
            }
        }
        return arrayList;
    }

    private static int getIdForRequestedCamera(int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i2 = 0; i2 < Camera.getNumberOfCameras(); i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == i) {
                return i2;
            }
        }
        return -1;
    }

    static int[] selectPreviewFpsRange(Camera camera, float f) {
        int i = (int) (f * 1000.0f);
        int[] iArr = null;
        int i2 = Integer.MAX_VALUE;
        for (int[] iArr2 : camera.getParameters().getSupportedPreviewFpsRange()) {
            int abs = Math.abs(i - iArr2[0]) + Math.abs(i - iArr2[1]);
            if (abs < i2) {
                iArr = iArr2;
                i2 = abs;
            }
        }
        return (int[]) Preconditions.checkNotNull(iArr);
    }

    static SizePair selectSizePair(Camera camera, int i, int i2) {
        SizePair sizePair = null;
        int i3 = Integer.MAX_VALUE;
        for (SizePair sizePair2 : generateValidPreviewSizeList(camera)) {
            Size previewSize = sizePair2.previewSize();
            int abs = Math.abs(previewSize.getWidth() - i) + Math.abs(previewSize.getHeight() - i2);
            if (abs < i3) {
                sizePair = sizePair2;
                i3 = abs;
            }
        }
        return (SizePair) Preconditions.checkNotNull(sizePair);
    }

    public Size getPreviewSize() {
        return this.previewSize;
    }

    public void release() {
        synchronized (this.cameraLock) {
            stop();
            this.frameProcessor.release();
        }
    }

    public CameraSource start(SurfaceHolder surfaceHolder) {
        synchronized (this.cameraLock) {
            if (this.camera != null) {
                return this;
            }
            Camera createCamera = createCamera();
            this.camera = createCamera;
            createCamera.setPreviewDisplay(surfaceHolder);
            this.camera.startPreview();
            this.processingThread = new Thread(this.frameProcessor);
            this.frameProcessor.setActive(true);
            Thread thread = this.processingThread;
            if (thread != null) {
                thread.start();
            }
            return this;
        }
    }

    public void stop() {
        synchronized (this.cameraLock) {
            this.frameProcessor.setActive(false);
            Thread thread = this.processingThread;
            if (thread != null) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.processingThread = null;
            }
            Camera camera = this.camera;
            if (camera != null) {
                camera.stopPreview();
                this.camera.setPreviewCallbackWithBuffer(null);
                try {
                    this.camera.setPreviewTexture(null);
                    this.dummySurfaceTexture = null;
                    this.camera.setPreviewDisplay(null);
                } catch (Exception e2) {
                    Log.e("CameraSource", "Failed to clear camera preview: " + String.valueOf(e2));
                }
                ((Camera) Preconditions.checkNotNull(this.camera)).release();
                this.camera = null;
            }
            this.bytesToByteBuffer.clear();
        }
    }

    void setRotation(Camera camera, Camera.Parameters parameters, int i, int i2) {
        int i3;
        int i4;
        int i5 = 0;
        switch (i2) {
            case 0:
                break;
            case 1:
                i5 = 90;
                break;
            case 2:
                i5 = AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_MEASURING_USER_ENGAGEMENT_VALUE;
                break;
            case 3:
                i5 = AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_RECENT_ACTIVITY_VALUE;
                break;
            default:
                Log.e("CameraSource", "Bad rotation value: " + i2);
                break;
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            i3 = (cameraInfo.orientation + i5) % AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_STREET_VIEW_LOCATION_GPS_VALUE;
            i4 = (360 - i3) % AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_STREET_VIEW_LOCATION_GPS_VALUE;
        } else {
            i3 = ((cameraInfo.orientation - i5) + AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_STREET_VIEW_LOCATION_GPS_VALUE) % AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_STREET_VIEW_LOCATION_GPS_VALUE;
            i4 = i3;
        }
        this.rotation = i3 / 90;
        camera.setDisplayOrientation(i4);
        parameters.setRotation(i3);
    }
}
