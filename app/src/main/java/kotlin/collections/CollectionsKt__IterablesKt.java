package kotlin.collections;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {
    public static final int collectionSizeOrDefault(Iterable iterable, int i) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }
}
