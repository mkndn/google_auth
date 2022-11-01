package org.joda.time.base;

import org.joda.time.ReadableInstant;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISODateTimeFormat;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractInstant implements ReadableInstant {
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReadableInstant) {
            ReadableInstant readableInstant = (ReadableInstant) obj;
            return getMillis() == readableInstant.getMillis() && FieldUtils.equals(getChronology(), readableInstant.getChronology());
        }
        return false;
    }

    public String toString() {
        return ISODateTimeFormat.dateTime().print(this);
    }

    @Override // java.lang.Comparable
    public int compareTo(ReadableInstant readableInstant) {
        if (this == readableInstant) {
            return 0;
        }
        long millis = readableInstant.getMillis();
        long millis2 = getMillis();
        if (millis2 == millis) {
            return 0;
        }
        return millis2 < millis ? -1 : 1;
    }

    public int hashCode() {
        return ((int) (getMillis() ^ (getMillis() >>> 32))) + getChronology().hashCode();
    }
}
