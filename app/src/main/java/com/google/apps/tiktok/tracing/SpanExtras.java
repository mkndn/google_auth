package com.google.apps.tiktok.tracing;

import androidx.collection.SimpleArrayMap;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SpanExtras {
    private final SpanExtras delegate;
    private boolean isFrozen;
    private final SimpleArrayMap map;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SpanExtrasImpl extends SpanExtras {
        static final SpanExtras EMPTY_EXTRAS = new SpanExtrasImpl(null, new SimpleArrayMap(0)).freeze();

        private SpanExtrasImpl(SpanExtras spanExtras, SimpleArrayMap simpleArrayMap) {
            super(simpleArrayMap);
        }
    }

    private SpanExtras(SpanExtras spanExtras, SimpleArrayMap simpleArrayMap) {
        this.isFrozen = false;
        if (spanExtras != null) {
            Preconditions.checkArgument(spanExtras.isFrozen);
        }
        this.delegate = spanExtras;
        this.map = simpleArrayMap;
    }

    public static SpanExtras empty() {
        return SpanExtrasImpl.EMPTY_EXTRAS;
    }

    final SpanExtras freeze() {
        if (this.isFrozen) {
            throw new IllegalStateException("Already frozen");
        }
        this.isFrozen = true;
        if (this.delegate != null && this.map.isEmpty()) {
            return this.delegate;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isFrozen() {
        return this.isFrozen;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SpanExtras<");
        for (SpanExtras spanExtras = this; spanExtras != null; spanExtras = spanExtras.delegate) {
            for (int i = 0; i < spanExtras.map.size(); i++) {
                sb.append(this.map.valueAt(i)).append("], ");
            }
        }
        return sb.append(">").toString();
    }
}
