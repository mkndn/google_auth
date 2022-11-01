package com.google.android.apps.authenticator.migration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.android.apps.authenticator.AuthenticatorActivity;
import com.google.android.apps.authenticator.migration.OfflineMigration;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator2.R;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.contrib.android.ProtoParsers;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MigrationExportBarcodeActivity extends TestableActivity {
    private static final float ARC_LENGTH_DEGREES_PER_SIDE = 45.0f;
    private static final float ARC_RADIUS;
    private static final float ARC_SAGITTA;
    private static final int CANVAS_PADDING = 2;
    private static final int CANVAS_SIZE;
    public static final String EXTRA_DATA = "data";
    private static final float HALF_ARC_LENGTH;
    private static final int QR_CODE_SIZE = 600;
    private static final float SHAPE_SIZE;
    private ImageView barcodeView;
    private int currentBarcode;
    private List data;
    private Button nextNavigationButton;
    private Button previousNavigationButton;
    private TextView titleView;
    private int totalNumberOfBarcodes;

    static {
        float radians = ((float) Math.toRadians(45.0d)) / 2.0f;
        HALF_ARC_LENGTH = radians;
        float sin = 300.0f / ((float) Math.sin(radians));
        ARC_RADIUS = sin;
        float cos = sin * (1.0f - ((float) Math.cos(radians)));
        ARC_SAGITTA = cos;
        float f = cos + cos + 600.0f;
        SHAPE_SIZE = f;
        CANVAS_SIZE = ((int) f) + 4;
    }

    private void renderBarcode(OfflineMigration.MigrationPayload migrationPayload) {
        int i = CANVAS_SIZE;
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        this.barcodeView.setImageBitmap(createBitmap);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate(2.0f, 2.0f);
        Bitmap encodeAsBitmap = QRCodeEncoder.encodeAsBitmap(MigrationPayloadProcessor.serialize(migrationPayload));
        float f = ARC_SAGITTA;
        float f2 = SHAPE_SIZE;
        canvas.drawBitmap(encodeAsBitmap, (Rect) null, new RectF(f, f, f2 - f, f2 - f), (Paint) null);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(this, R.color.google_grey400));
        paint.setStrokeWidth(4.0f);
        float f3 = ARC_RADIUS;
        canvas.drawArc(new RectF((f2 / 2.0f) - f3, 0.0f, (f2 / 2.0f) + f3, f3 + f3), 247.5f, ARC_LENGTH_DEGREES_PER_SIDE, false, paint);
        canvas.drawArc(new RectF(0.0f, (f2 / 2.0f) - f3, f3 + f3, (f2 / 2.0f) + f3), 157.5f, ARC_LENGTH_DEGREES_PER_SIDE, false, paint);
        canvas.drawArc(new RectF(f2 - (f3 + f3), (f2 / 2.0f) - f3, f2, (f2 / 2.0f) + f3), -22.5f, ARC_LENGTH_DEGREES_PER_SIDE, false, paint);
        canvas.drawArc(new RectF((f2 / 2.0f) - f3, f2 - (f3 + f3), (f2 / 2.0f) + f3, f2), 67.5f, ARC_LENGTH_DEGREES_PER_SIDE, false, paint);
        canvas.drawRect(f, f, f2 - f, f2 - f, paint);
        if (this.totalNumberOfBarcodes > 1) {
            this.titleView.setText(getString(R.string.migration_export_barcode_page_title_multiple_codes, new Object[]{Integer.valueOf(this.currentBarcode + 1), Integer.valueOf(this.totalNumberOfBarcodes)}));
        } else {
            this.titleView.setText(R.string.migration_export_barcode_page_title_single_code);
        }
    }

    private void updateButtonDisplays() {
        if (this.currentBarcode < this.totalNumberOfBarcodes - 1) {
            this.nextNavigationButton.setText(getResourceString(R.string.button_next));
        } else {
            this.nextNavigationButton.setText(getResourceString(R.string.button_done));
        }
        this.previousNavigationButton.setVisibility(this.currentBarcode == 0 ? 4 : 0);
    }

    public void navigationNextButtonClicked(View view) {
        int i = this.currentBarcode;
        if (i < this.totalNumberOfBarcodes - 1) {
            List list = this.data;
            int i2 = i + 1;
            this.currentBarcode = i2;
            renderBarcode((OfflineMigration.MigrationPayload) list.get(i2));
            updateButtonDisplays();
            return;
        }
        startActivity(new Intent(this, AuthenticatorActivity.class));
    }

    public void navigationPreviousButtonClicked(View view) {
        int i = this.currentBarcode;
        if (i > 0) {
            List list = this.data;
            int i2 = i - 1;
            this.currentBarcode = i2;
            renderBarcode((OfflineMigration.MigrationPayload) list.get(i2));
            updateButtonDisplays();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(8192, 8192);
        if (getResources().getBoolean(R.bool.isTablet)) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(12);
        }
        setContentView(R.layout.migration_export_barcode_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.migration_export_barcode_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        List listTrusted = ProtoParsers.getListTrusted(getIntent(), EXTRA_DATA, OfflineMigration.MigrationPayload.getDefaultInstance(), ExtensionRegistryLite.getGeneratedRegistry());
        this.data = listTrusted;
        this.totalNumberOfBarcodes = listTrusted.size();
        this.barcodeView = (ImageView) findViewById(R.id.migration_export_barcode);
        this.nextNavigationButton = (Button) findViewById(R.id.migration_export_next_button);
        this.previousNavigationButton = (Button) findViewById(R.id.migration_export_previous_button);
        this.titleView = (TextView) findViewById(R.id.migration_export_barcode_title);
        renderBarcode((OfflineMigration.MigrationPayload) this.data.get(this.currentBarcode));
        updateButtonDisplays();
    }
}
