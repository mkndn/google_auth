package com.google.android.gms.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionUtils {
    @Deprecated
    public static List listOf(Object obj) {
        return Collections.singletonList(obj);
    }

    private static List makeListWithSize(int i, boolean z) {
        return new ArrayList(i);
    }

    public static List mutableListOf(Object obj) {
        return mutableListOfWithSize(1, obj);
    }

    public static List mutableListOfWithSize(int i, Object obj) {
        List makeListWithSize = makeListWithSize(Math.max(i, 1), true);
        makeListWithSize.add(obj);
        return makeListWithSize;
    }
}
