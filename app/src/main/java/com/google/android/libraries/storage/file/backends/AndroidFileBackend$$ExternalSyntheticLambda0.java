package com.google.android.libraries.storage.file.backends;

import android.content.Context;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.storage.file.backends.AndroidFileBackend;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AndroidFileBackend$$ExternalSyntheticLambda0 implements AndroidFileBackend.DirectBootChecker {
    public static final /* synthetic */ AndroidFileBackend$$ExternalSyntheticLambda0 INSTANCE = new AndroidFileBackend$$ExternalSyntheticLambda0();

    private /* synthetic */ AndroidFileBackend$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.storage.file.backends.AndroidFileBackend.DirectBootChecker
    public final boolean isUserUnlocked(Context context) {
        return DirectBootUtils.isUserUnlocked(context);
    }
}
