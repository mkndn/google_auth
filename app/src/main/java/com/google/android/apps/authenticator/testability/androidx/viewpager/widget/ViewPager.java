package com.google.android.apps.authenticator.testability.androidx.viewpager.widget;

import androidx.viewpager.widget.PagerAdapter;
import com.google.android.apps.authenticator.testability.android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ViewPager extends View {
    private final androidx.viewpager.widget.ViewPager viewPager;

    public ViewPager(androidx.viewpager.widget.ViewPager viewPager) {
        super(viewPager);
        this.viewPager = viewPager;
    }

    public androidx.viewpager.widget.ViewPager getAndroidViewPager() {
        return this.viewPager;
    }

    public View getChildAt(int i) {
        android.view.View childAt = this.viewPager.getChildAt(i);
        if (childAt != null) {
            return new View(childAt);
        }
        return null;
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        this.viewPager.setAdapter(pagerAdapter);
    }
}
