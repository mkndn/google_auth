package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface LifecycleEventObserver extends LifecycleObserver {
    void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event);
}
