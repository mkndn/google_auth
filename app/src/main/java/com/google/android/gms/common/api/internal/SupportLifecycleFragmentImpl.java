package com.google.android.gms.common.api.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import androidx.collection.ArrayMap;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SupportLifecycleFragmentImpl extends Fragment implements LifecycleFragment {
    public static final int CREATED = 1;
    public static final int DESTROYED = 5;
    public static final int NOT_CREATED = 0;
    public static final int RESUMED = 3;
    public static final int STARTED = 2;
    public static final int STOPPED = 4;
    private static WeakHashMap mFragmentByActivity = new WeakHashMap();
    private Bundle mSavedInstanceState;
    private Map mLifecycleCallbacksByTag = Collections.synchronizedMap(new ArrayMap());
    private int mStatus = 0;

    public static SupportLifecycleFragmentImpl getInstance(FragmentActivity fragmentActivity) {
        SupportLifecycleFragmentImpl supportLifecycleFragmentImpl;
        WeakReference weakReference = (WeakReference) mFragmentByActivity.get(fragmentActivity);
        if (weakReference == null || (supportLifecycleFragmentImpl = (SupportLifecycleFragmentImpl) weakReference.get()) == null) {
            try {
                SupportLifecycleFragmentImpl supportLifecycleFragmentImpl2 = (SupportLifecycleFragmentImpl) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
                if (supportLifecycleFragmentImpl2 == null || supportLifecycleFragmentImpl2.isRemoving()) {
                    supportLifecycleFragmentImpl2 = new SupportLifecycleFragmentImpl();
                    fragmentActivity.getSupportFragmentManager().beginTransaction().add(supportLifecycleFragmentImpl2, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
                }
                mFragmentByActivity.put(fragmentActivity, new WeakReference(supportLifecycleFragmentImpl2));
                return supportLifecycleFragmentImpl2;
            } catch (ClassCastException e) {
                throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
            }
        }
        return supportLifecycleFragmentImpl;
    }

    private void invokePastLifecycleEvents(final String str, final LifecycleCallback lifecycleCallback) {
        if (this.mStatus > 0) {
            new TracingHandler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.common.api.internal.SupportLifecycleFragmentImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    if (SupportLifecycleFragmentImpl.this.mStatus > 0) {
                        lifecycleCallback.onCreate(SupportLifecycleFragmentImpl.this.mSavedInstanceState != null ? SupportLifecycleFragmentImpl.this.mSavedInstanceState.getBundle(str) : null);
                    }
                    if (SupportLifecycleFragmentImpl.this.mStatus >= 2) {
                        lifecycleCallback.onStart();
                    }
                    if (SupportLifecycleFragmentImpl.this.mStatus >= 3) {
                        lifecycleCallback.onResume();
                    }
                    if (SupportLifecycleFragmentImpl.this.mStatus >= 4) {
                        lifecycleCallback.onStop();
                    }
                    if (SupportLifecycleFragmentImpl.this.mStatus >= 5) {
                        lifecycleCallback.onDestroy();
                    }
                }
            });
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public void addCallback(String str, LifecycleCallback lifecycleCallback) {
        if (!this.mLifecycleCallbacksByTag.containsKey(str)) {
            this.mLifecycleCallbacksByTag.put(str, lifecycleCallback);
            invokePastLifecycleEvents(str, lifecycleCallback);
            return;
        }
        throw new IllegalArgumentException("LifecycleCallback with tag " + str + " already added to this fragment.");
    }

    @Override // android.support.v4.app.Fragment
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback lifecycleCallback : this.mLifecycleCallbacksByTag.values()) {
            lifecycleCallback.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public LifecycleCallback getCallbackOrNull(String str, Class cls) {
        return (LifecycleCallback) cls.cast(this.mLifecycleCallbacksByTag.get(str));
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback lifecycleCallback : this.mLifecycleCallbacksByTag.values()) {
            lifecycleCallback.onActivityResult(i, i2, intent);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mStatus = 1;
        this.mSavedInstanceState = bundle;
        for (Map.Entry entry : this.mLifecycleCallbacksByTag.entrySet()) {
            ((LifecycleCallback) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mStatus = 5;
        for (LifecycleCallback lifecycleCallback : this.mLifecycleCallbacksByTag.values()) {
            lifecycleCallback.onDestroy();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.mStatus = 3;
        for (LifecycleCallback lifecycleCallback : this.mLifecycleCallbacksByTag.values()) {
            lifecycleCallback.onResume();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null) {
            return;
        }
        for (Map.Entry entry : this.mLifecycleCallbacksByTag.entrySet()) {
            Bundle bundle2 = new Bundle();
            ((LifecycleCallback) entry.getValue()).onSaveInstanceState(bundle2);
            bundle.putBundle((String) entry.getKey(), bundle2);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        this.mStatus = 2;
        for (LifecycleCallback lifecycleCallback : this.mLifecycleCallbacksByTag.values()) {
            lifecycleCallback.onStart();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        this.mStatus = 4;
        for (LifecycleCallback lifecycleCallback : this.mLifecycleCallbacksByTag.values()) {
            lifecycleCallback.onStop();
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public FragmentActivity getLifecycleActivity() {
        return getActivity();
    }
}
