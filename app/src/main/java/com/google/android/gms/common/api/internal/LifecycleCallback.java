package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LifecycleCallback {
    protected final LifecycleFragment mLifecycleFragment;

    /* JADX INFO: Access modifiers changed from: protected */
    public LifecycleCallback(LifecycleFragment lifecycleFragment) {
        this.mLifecycleFragment = lifecycleFragment;
    }

    private static LifecycleFragment getChimeraLifecycleFragmentImpl(LifecycleActivity lifecycleActivity) {
        if (BuildConstants.IS_PACKAGE_SIDE) {
            return ChimeraLifecycleFragmentImpl.getInstance(lifecycleActivity.asChimeraActivity());
        }
        throw new IllegalStateException("Method not available in SDK.");
    }

    public static LifecycleFragment getFragment(Activity activity) {
        return getFragment(new LifecycleActivity(activity));
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public Activity getActivity() {
        return (Activity) Preconditions.checkNotNull(this.mLifecycleFragment.getLifecycleActivity());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onCreate(Bundle bundle) {
    }

    public void onDestroy() {
    }

    public void onResume() {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    protected static LifecycleFragment getFragment(LifecycleActivity lifecycleActivity) {
        if (lifecycleActivity.isSupport()) {
            return SupportLifecycleFragmentImpl.getInstance(lifecycleActivity.asFragmentActivity());
        }
        if (lifecycleActivity.isAndroid()) {
            return LifecycleFragmentImpl.getInstance(lifecycleActivity.asActivity());
        }
        if (BuildConstants.IS_PACKAGE_SIDE && lifecycleActivity.isChimera()) {
            return getChimeraLifecycleFragmentImpl(lifecycleActivity);
        }
        throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
    }
}
