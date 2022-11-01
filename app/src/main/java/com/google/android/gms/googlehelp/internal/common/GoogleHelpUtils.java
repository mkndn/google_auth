package com.google.android.gms.googlehelp.internal.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleHelpUtils {
    public static String getActionBarTitle(Activity activity) {
        String obj = activity.getTitle().toString();
        int identifier = activity.getResources().getIdentifier("action_bar", "id", activity.getPackageName());
        if (identifier == 0) {
            return obj;
        }
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(identifier);
        if (viewGroup == null) {
            return obj;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof TextView) {
                return ((TextView) childAt).getText().toString();
            }
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap getContentScreenshot(Activity activity) {
        int statusBarHeight;
        try {
            View rootView = activity.getWindow().getDecorView().getRootView();
            View findViewById = rootView.findViewById(16908290);
            if (findViewById == null || (statusBarHeight = getStatusBarHeight(activity) + findViewById.getTop()) >= rootView.getHeight()) {
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(rootView.getWidth(), rootView.getHeight(), Bitmap.Config.RGB_565);
            rootView.draw(new Canvas(createBitmap));
            return Bitmap.createBitmap(createBitmap, 0, statusBarHeight, createBitmap.getWidth(), Math.min(createBitmap.getHeight() - statusBarHeight, findViewById.getHeight()));
        } catch (Exception e) {
            Log.w("gH_Utils", "Get screenshot failed!", e);
            return null;
        }
    }

    public static Bitmap getContentScreenshotOnUiThread(final Activity activity) {
        FutureTask futureTask = new FutureTask(new Callable() { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpUtils.1
            @Override // java.util.concurrent.Callable
            public Bitmap call() {
                return GoogleHelpUtils.getContentScreenshot(activity);
            }
        });
        activity.runOnUiThread(futureTask);
        try {
            return (Bitmap) futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.w("gH_Utils", "Taking screenshot failed!");
            return null;
        }
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier != 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
