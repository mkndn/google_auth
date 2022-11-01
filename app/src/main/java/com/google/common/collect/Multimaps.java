package com.google.common.collect;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Multimaps {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean equalsImpl(Multimap multimap, Object obj) {
        if (obj == multimap) {
            return true;
        }
        if (obj instanceof Multimap) {
            return multimap.asMap().equals(((Multimap) obj).asMap());
        }
        return false;
    }
}
