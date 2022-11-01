package com.google.android.apps.authenticator.barcode;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import com.google.android.apps.authenticator.barcode.BarcodeTracker;
import com.google.android.apps.authenticator.barcode.preview.CameraSourcePreview;
import com.google.android.apps.authenticator.barcode.preview.GraphicOverlay;
import com.google.android.apps.authenticator.enroll2sv.wizard.AddAccountActivity;
import com.google.android.apps.authenticator.migration.MigrationPayloadProcessor;
import com.google.android.apps.authenticator.migration.OfflineMigration;
import com.google.android.apps.authenticator.migration.UnsupportedMigrationVersionException;
import com.google.android.apps.authenticator.testability.DaggerInjector;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator.util.permissions.PermissionRequestor;
import com.google.android.apps.authenticator2.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.libraries.barhopper.Barcode;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.contrib.android.ProtoParsers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BarcodeCaptureActivity extends TestableActivity {
    public static final String INTENT_EXTRA_IMPORTED_OTP_PARAMETERS = "otp_parameters";
    public static final String INTENT_EXTRA_OTP_URL_VALUE = "barcode_value";
    public static final String INTENT_EXTRA_START_FROM_ADD_ACCOUNT = "start_from_add_account";
    private static final float PREFERRED_FRAMERATE = 30.0f;
    private static final int RC_HANDLE_CAMERA_PERM = 1;
    public static final int RESULT_ERROR_UNSUPPORTED_MIGRATION_VERSION = 3;
    public static final int RESULT_OK_SCANNED_MIGRATION_PAYLOADS = 2;
    public static final int RESULT_OK_SCANNED_OTP_URL = 1;
    private static final String TAG = "BarcodeCaptureActivity";
    private static final int TONE_DURATION = 200;
    private static final int TONE_VOLUME = 100;
    private static final long VIBRATE_DURATION = 200;
    private Detector barcodeDetector;
    boolean barcodeScannerActive;
    private CameraSource mCameraSource;
    private CameraSourcePreview mCameraSourcePreview;
    private int mCurrentRotation;
    private final BarcodeTracker.OnDetectionListener mDetectionListener = new BarcodeTracker.OnDetectionListener() { // from class: com.google.android.apps.authenticator.barcode.BarcodeCaptureActivity.1
        @Override // com.google.android.apps.authenticator.barcode.BarcodeTracker.OnDetectionListener
        public void onNewDetection(int i, final Barcode barcode) {
            BarcodeCaptureActivity.this.runOnUiThread(new Runnable() { // from class: com.google.android.apps.authenticator.barcode.BarcodeCaptureActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    if (BarcodeCaptureActivity.this.barcodeScannerActive) {
                        BarcodeCaptureActivity.this.barcodeScannerActive = false;
                        if (((AudioManager) BarcodeCaptureActivity.this.getSystemService("audio")).getRingerMode() == 2) {
                            new ToneGenerator(3, 100).startTone(44, 200);
                        }
                        BarcodeCaptureActivity.this.onBarcodeDetected(barcode.displayValue);
                    }
                }
            });
        }
    };
    private GraphicOverlay mGraphicOverlay;
    private OrientationEventListener mOrientationEventListener;
    @Inject
    PermissionRequestor mPermissionRequestor;
    private boolean mStartFromAddAccountActivity;
    List pendingBatch;

    public BarcodeCaptureActivity() {
        DaggerInjector.inject(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createCameraSource() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        int i = point.y;
        int i2 = point.x;
        if (i2 > i) {
            i = i2;
            i2 = i;
        }
        PackageManager packageManager = getPackageManager();
        int i3 = (packageManager.hasSystemFeature("android.hardware.camera") || !packageManager.hasSystemFeature("android.hardware.camera.front")) ? 0 : 1;
        this.barcodeDetector = new QrCodeDetector();
        BarcodeTracker barcodeTracker = new BarcodeTracker();
        barcodeTracker.setDetectionListener(this.mDetectionListener);
        this.barcodeDetector.setProcessor(new BarcodeCentralFocusingProcessor(this.barcodeDetector, barcodeTracker, Boolean.valueOf(i3 == 0)));
        this.mCameraSource = new CameraSource.Builder(getApplicationContext(), this.barcodeDetector).setFacing(i3).setAutoFocusEnabled(true).setFocusMode("continuous-picture").setRequestedPreviewSize(i, i2).setRequestedFps(PREFERRED_FRAMERATE).build();
    }

    private void processScannedMigrationPayload(OfflineMigration.MigrationPayload migrationPayload) {
        if (!this.pendingBatch.isEmpty() && ((OfflineMigration.MigrationPayload) this.pendingBatch.get(0)).getBatchId() != migrationPayload.getBatchId()) {
            this.pendingBatch.clear();
        }
        if (this.pendingBatch.size() == migrationPayload.getBatchIndex()) {
            this.pendingBatch.add(migrationPayload);
            if (this.pendingBatch.size() == ((OfflineMigration.MigrationPayload) this.pendingBatch.get(0)).getBatchSize()) {
                returnCompletedImportBatch();
                return;
            }
        }
        Snackbar.make(this.mGraphicOverlay, getString(R.string.advance_pending_import, new Object[]{Integer.valueOf(this.pendingBatch.size() + 1), Integer.valueOf(migrationPayload.getBatchSize())}), -2).show();
        this.barcodeScannerActive = true;
    }

    private void requestCameraPermission() {
        Log.w(TAG, "Camera permission is not granted. Requesting permission");
        final String[] strArr = {"android.permission.CAMERA"};
        if (!this.mPermissionRequestor.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
            this.mPermissionRequestor.requestPermissions(this, strArr, 1);
        } else {
            Snackbar.make(this.mGraphicOverlay, R.string.permission_camera_rationale, -2).setAction(R.string.ok, new View.OnClickListener() { // from class: com.google.android.apps.authenticator.barcode.BarcodeCaptureActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BarcodeCaptureActivity.this.mPermissionRequestor.requestPermissions(BarcodeCaptureActivity.this, strArr, 1);
                }
            }).show();
        }
    }

    private void returnCompletedImportBatch() {
        ArrayList arrayList = new ArrayList();
        for (OfflineMigration.MigrationPayload migrationPayload : this.pendingBatch) {
            arrayList.addAll(migrationPayload.getOtpParametersList());
        }
        Intent intent = new Intent();
        ProtoParsers.put(intent, INTENT_EXTRA_IMPORTED_OTP_PARAMETERS, arrayList);
        setResult(2, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCameraSource() {
        CameraSource cameraSource = this.mCameraSource;
        if (cameraSource != null) {
            this.mCameraSourcePreview.start(cameraSource, this.mGraphicOverlay);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        this.mCameraSourcePreview.stop();
        super.finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mStartFromAddAccountActivity) {
            startActivity(new Intent(this, AddAccountActivity.class));
            finish();
            return;
        }
        super.onBackPressed();
    }

    void onBarcodeDetected(String str) {
        ((Vibrator) getSystemService("vibrator")).vibrate(VIBRATE_DURATION);
        try {
            processScannedMigrationPayload(MigrationPayloadProcessor.parse(str));
        } catch (UnsupportedMigrationVersionException e) {
            setResult(3, new Intent());
            finish();
        } catch (InvalidProtocolBufferException e2) {
            if (this.pendingBatch.isEmpty()) {
                Intent intent = new Intent();
                intent.putExtra(INTENT_EXTRA_OTP_URL_VALUE, str);
                setResult(1, intent);
                finish();
                return;
            }
            Snackbar.make(this.mGraphicOverlay, getString(R.string.advance_pending_import, new Object[]{Integer.valueOf(this.pendingBatch.size() + 1), Integer.valueOf(((OfflineMigration.MigrationPayload) this.pendingBatch.get(0)).getBatchSize())}), -2).show();
            this.barcodeScannerActive = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(8192, 8192);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | 1280);
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(0);
            } else {
                getWindow().setFlags(67108864, 67108864);
            }
        } else {
            getWindow().setFlags(1024, 1024);
        }
        this.mStartFromAddAccountActivity = getIntent().getBooleanExtra(INTENT_EXTRA_START_FROM_ADD_ACCOUNT, false);
        setContentView(R.layout.barcode_capture_activity);
        this.mCameraSourcePreview = (CameraSourcePreview) findViewById(R.id.camera_source_preview);
        this.mGraphicOverlay = (GraphicOverlay) findViewById(R.id.graphic_overlay);
        this.mCurrentRotation = getWindowManager().getDefaultDisplay().getRotation();
        OrientationEventListener orientationEventListener = new OrientationEventListener(this, 2) { // from class: com.google.android.apps.authenticator.barcode.BarcodeCaptureActivity.2
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
                int rotation;
                if (i != -1 && BarcodeCaptureActivity.this.mCurrentRotation != (rotation = BarcodeCaptureActivity.this.getWindowManager().getDefaultDisplay().getRotation())) {
                    BarcodeCaptureActivity.this.mCurrentRotation = rotation;
                    if (BarcodeCaptureActivity.this.mCameraSourcePreview != null) {
                        BarcodeCaptureActivity.this.mCameraSourcePreview.stop();
                        BarcodeCaptureActivity.this.mCameraSourcePreview.release();
                    }
                    BarcodeCaptureActivity.this.createCameraSource();
                    BarcodeCaptureActivity.this.startCameraSource();
                }
            }
        };
        this.mOrientationEventListener = orientationEventListener;
        if (orientationEventListener.canDetectOrientation()) {
            this.mOrientationEventListener.enable();
        } else {
            this.mOrientationEventListener.disable();
        }
        if (this.mPermissionRequestor.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            createCameraSource();
        } else {
            requestCameraPermission();
        }
        this.barcodeScannerActive = true;
        this.pendingBatch = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CameraSourcePreview cameraSourcePreview = this.mCameraSourcePreview;
        if (cameraSourcePreview != null) {
            cameraSourcePreview.release();
        }
        OrientationEventListener orientationEventListener = this.mOrientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        Detector detector = this.barcodeDetector;
        if (detector != null) {
            detector.release();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        CameraSourcePreview cameraSourcePreview = this.mCameraSourcePreview;
        if (cameraSourcePreview != null) {
            cameraSourcePreview.stop();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1) {
            Log.e(TAG, "Got unexpected permission result: " + i);
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (iArr.length != 0 && iArr[0] == 0) {
            Log.d(TAG, "Camera permission granted - initialize the camera source");
            createCameraSource();
        } else {
            Log.e(TAG, "Permission not granted: results len = " + iArr.length + " Result code = " + String.valueOf(iArr.length > 0 ? Integer.valueOf(iArr[0]) : "(empty)"));
            new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setMessage(R.string.no_camera_permission).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.android.apps.authenticator.barcode.BarcodeCaptureActivity.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (BarcodeCaptureActivity.this.mStartFromAddAccountActivity) {
                        BarcodeCaptureActivity.this.startActivity(new Intent(BarcodeCaptureActivity.this, AddAccountActivity.class));
                    }
                    BarcodeCaptureActivity.this.finish();
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        startCameraSource();
    }
}
