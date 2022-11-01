package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class RegisterListenerMethod {
    public void clearListener() {
        throw null;
    }

    public Feature[] getRequiredFeatures() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void registerListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource);

    public boolean shouldAutoResolveMissingFeatures() {
        throw null;
    }
}
