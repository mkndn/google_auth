package com.google.apps.tiktok.tracing;

import android.text.TextUtils;
import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceInfo {
    private final ImmutableList extras;
    private final ImmutableList spanNames;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TraceInfo(ImmutableList immutableList, ImmutableList immutableList2) {
        this.spanNames = immutableList;
        this.extras = immutableList2;
    }

    public String toString() {
        return TextUtils.join(" -> ", this.spanNames);
    }
}
