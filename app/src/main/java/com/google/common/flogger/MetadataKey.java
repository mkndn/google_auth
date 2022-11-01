package com.google.common.flogger;

import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.util.Checks;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MetadataKey {
    private final long bloomFilterMask;
    private final boolean canRepeat;
    private final Class clazz;
    private final boolean isCustom;
    private final String label;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface KeyValueHandler {
        void handle(String str, Object obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MetadataKey(String str, Class cls, boolean z) {
        this(str, cls, z, true);
    }

    private long createBloomFilterMaskFromSystemHashcode() {
        int identityHashCode = System.identityHashCode(this);
        long j = 0;
        for (int i = 0; i < 5; i++) {
            j |= 1 << (identityHashCode & 63);
            identityHashCode >>>= 6;
        }
        return j;
    }

    public static MetadataKey single(String str, Class cls) {
        return new MetadataKey(str, cls, false, false);
    }

    public final boolean canRepeat() {
        return this.canRepeat;
    }

    public final Object cast(Object obj) {
        return this.clazz.cast(obj);
    }

    protected void emit(Object obj, KeyValueHandler keyValueHandler) {
        keyValueHandler.handle(getLabel(), obj);
    }

    protected void emitRepeated(Iterator it, KeyValueHandler keyValueHandler) {
        while (it.hasNext()) {
            emit(it.next(), keyValueHandler);
        }
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final long getBloomFilterMask() {
        return this.bloomFilterMask;
    }

    public final String getLabel() {
        return this.label;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final void safeEmit(Object obj, KeyValueHandler keyValueHandler) {
        if (this.isCustom && Platform.getCurrentRecursionDepth() > 20) {
            keyValueHandler.handle(getLabel(), obj);
        } else {
            emit(obj, keyValueHandler);
        }
    }

    public final void safeEmitRepeated(Iterator it, KeyValueHandler keyValueHandler) {
        Checks.checkState(this.canRepeat, "non repeating key");
        if (this.isCustom && Platform.getCurrentRecursionDepth() > 20) {
            while (it.hasNext()) {
                keyValueHandler.handle(getLabel(), it.next());
            }
            return;
        }
        emitRepeated(it, keyValueHandler);
    }

    public final String toString() {
        return getClass().getName() + "/" + this.label + "[" + this.clazz.getName() + "]";
    }

    private MetadataKey(String str, Class cls, boolean z, boolean z2) {
        this.label = Checks.checkMetadataIdentifier(str);
        this.clazz = (Class) Checks.checkNotNull(cls, "class");
        this.canRepeat = z;
        this.isCustom = z2;
        this.bloomFilterMask = createBloomFilterMaskFromSystemHashcode();
    }
}
