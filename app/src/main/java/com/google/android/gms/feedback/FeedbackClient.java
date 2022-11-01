package com.google.android.gms.feedback;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import com.google.android.gms.common.api.GoogleApi;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FeedbackClient extends GoogleApi {
    public static Bitmap getScreenshot(Activity activity) {
        try {
            return getScreenshot(activity.getWindow().getDecorView().getRootView());
        } catch (Exception e) {
            Log.w("gF_FeedbackClient", "Get screenshot failed!", e);
            return null;
        }
    }

    public static Bitmap getScreenshot(View view) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
            view.draw(new Canvas(createBitmap));
            return createBitmap;
        } catch (Error | Exception e) {
            Log.w("gF_FeedbackClient", "Get screenshot failed!", e);
            return null;
        }
    }
}
