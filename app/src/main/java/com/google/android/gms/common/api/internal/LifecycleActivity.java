package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LifecycleActivity {
    private final Object mActivity;

    public LifecycleActivity(Activity activity) {
        this.mActivity = Preconditions.checkNotNull(activity, "Activity must not be null");
    }

    public Activity asActivity() {
        return (Activity) this.mActivity;
    }

    public com.google.android.chimera.Activity asChimeraActivity() {
        return (com.google.android.chimera.Activity) this.mActivity;
    }

    public FragmentActivity asFragmentActivity() {
        return (FragmentActivity) this.mActivity;
    }

    public boolean isAndroid() {
        return this.mActivity instanceof Activity;
    }

    public boolean isChimera() {
        return BuildConstants.IS_PACKAGE_SIDE && (this.mActivity instanceof com.google.android.chimera.Activity);
    }

    public boolean isSupport() {
        return this.mActivity instanceof FragmentActivity;
    }
}
