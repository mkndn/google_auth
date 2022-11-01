package com.google.android.apps.authenticator.testability;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.google.android.apps.authenticator.testability.android.widget.Button;
import com.google.android.apps.authenticator.testability.android.widget.ImageView;
import com.google.android.apps.authenticator.testability.android.widget.ListView;
import com.google.android.apps.authenticator.testability.android.widget.TextView;
import com.google.android.apps.authenticator.testability.androidx.viewpager.widget.ViewPager;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TestableActivity extends AppCompatActivity {
    public void executeOnUiThread(Runnable runnable) {
        super.runOnUiThread(runnable);
    }

    public ViewPager findViewPagerById(int i) {
        View findViewById = super.findViewById(i);
        if (findViewById != null) {
            return new ViewPager((androidx.viewpager.widget.ViewPager) findViewById);
        }
        return null;
    }

    public Button getButtonById(int i) {
        return new Button((android.widget.Button) super.findViewById(i));
    }

    public Drawable getDrawableById(int i) {
        return super.getDrawable(i);
    }

    public ImageView getImageViewById(int i) {
        return new ImageView((android.widget.ImageView) super.findViewById(i));
    }

    public ListView getListViewById(int i) {
        return new ListView((android.widget.ListView) super.findViewById(i));
    }

    public String getResourceString(int i) {
        return super.getString(i);
    }

    public TextView getTextViewById(int i) {
        return new TextView((android.widget.TextView) super.findViewById(i));
    }

    public void setActivityResult(int i, Intent intent) {
        super.setResult(i, intent);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        StartActivityListener startActivityListener = DependencyInjector.getStartActivityListener();
        if (startActivityListener != null && startActivityListener.onStartActivityInvoked(this, intent)) {
            return;
        }
        super.startActivity(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        StartActivityListener startActivityListener = DependencyInjector.getStartActivityListener();
        if (startActivityListener != null && startActivityListener.onStartActivityInvoked(this, intent)) {
            return;
        }
        super.startActivityForResult(intent, i);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ComponentName startService(Intent intent) {
        StartServiceListener startServiceListener = DependencyInjector.getStartServiceListener();
        if (startServiceListener == null || !startServiceListener.onStartServiceInvoked(this, intent)) {
            return super.startService(intent);
        }
        return null;
    }
}
