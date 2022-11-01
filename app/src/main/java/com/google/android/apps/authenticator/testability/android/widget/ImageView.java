package com.google.android.apps.authenticator.testability.android.widget;

import android.graphics.Bitmap;
import com.google.android.apps.authenticator.testability.android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ImageView extends View {
    private final android.widget.ImageView imageView;

    public ImageView(android.widget.ImageView imageView) {
        super(imageView);
        this.imageView = imageView;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.imageView.setImageBitmap(bitmap);
    }
}
