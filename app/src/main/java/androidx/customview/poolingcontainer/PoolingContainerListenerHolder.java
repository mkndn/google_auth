package androidx.customview.poolingcontainer;

import java.util.ArrayList;
import kotlin.collections.CollectionsKt;

/* compiled from: PG */
/* loaded from: classes.dex */
final class PoolingContainerListenerHolder {
    private final ArrayList listeners = new ArrayList();

    public final void onRelease() {
        for (int lastIndex = CollectionsKt.getLastIndex(this.listeners); lastIndex >= 0; lastIndex--) {
            ((PoolingContainerListener) this.listeners.get(lastIndex)).onRelease();
        }
    }
}
