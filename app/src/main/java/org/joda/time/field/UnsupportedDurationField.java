package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UnsupportedDurationField extends DurationField implements Serializable {
    private static HashMap cCache = null;
    private static final long serialVersionUID = -6390301302770925357L;
    private final DurationFieldType iType;

    private UnsupportedDurationField(DurationFieldType durationFieldType) {
        this.iType = durationFieldType;
    }

    public static synchronized UnsupportedDurationField getInstance(DurationFieldType durationFieldType) {
        UnsupportedDurationField unsupportedDurationField;
        synchronized (UnsupportedDurationField.class) {
            HashMap hashMap = cCache;
            if (hashMap == null) {
                cCache = new HashMap(7);
                unsupportedDurationField = null;
            } else {
                unsupportedDurationField = (UnsupportedDurationField) hashMap.get(durationFieldType);
            }
            if (unsupportedDurationField == null) {
                unsupportedDurationField = new UnsupportedDurationField(durationFieldType);
                cCache.put(durationFieldType, unsupportedDurationField);
            }
        }
        return unsupportedDurationField;
    }

    private Object readResolve() {
        return getInstance(this.iType);
    }

    private UnsupportedOperationException unsupported() {
        String valueOf = String.valueOf(this.iType);
        return new UnsupportedOperationException(new StringBuilder(String.valueOf(valueOf).length() + 21).append(valueOf).append(" field is unsupported").toString());
    }

    @Override // org.joda.time.DurationField
    public long add(long j, int i) {
        throw unsupported();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UnsupportedDurationField) {
            UnsupportedDurationField unsupportedDurationField = (UnsupportedDurationField) obj;
            if (unsupportedDurationField.getName() == null) {
                return getName() == null;
            }
            return unsupportedDurationField.getName().equals(getName());
        }
        return false;
    }

    public String getName() {
        return this.iType.getName();
    }

    @Override // org.joda.time.DurationField
    public final DurationFieldType getType() {
        return this.iType;
    }

    @Override // org.joda.time.DurationField
    public long getUnitMillis() {
        return 0L;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    @Override // org.joda.time.DurationField
    public boolean isPrecise() {
        return true;
    }

    @Override // org.joda.time.DurationField
    public boolean isSupported() {
        return false;
    }

    public String toString() {
        String name = getName();
        return new StringBuilder(String.valueOf(name).length() + 26).append("UnsupportedDurationField[").append(name).append(']').toString();
    }

    @Override // org.joda.time.DurationField
    public long add(long j, long j2) {
        throw unsupported();
    }

    @Override // java.lang.Comparable
    public int compareTo(DurationField durationField) {
        return 0;
    }
}
