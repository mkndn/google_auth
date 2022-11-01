package com.google.common.collect;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Collections2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static StringBuilder newStringBuilderForCollection(int i) {
        CollectPreconditions.checkNonnegative(i, "size");
        return new StringBuilder((int) Math.min(i * 8, 1073741824L));
    }
}
