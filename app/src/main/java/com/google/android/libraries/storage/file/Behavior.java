package com.google.android.libraries.storage.file;

import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Behavior {

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.storage.file.Behavior$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static void $default$forInputChain(Behavior behavior, List list) {
        }
    }

    void commit();

    void forInputChain(List list);

    void forOutputChain(List list);
}
