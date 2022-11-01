package org.joda.time.base;

import org.joda.time.ReadableInstant;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractDateTime extends AbstractInstant implements ReadableInstant {
    public int getWeekyear() {
        return getChronology().weekyear().get(getMillis());
    }

    public int getYear() {
        return getChronology().year().get(getMillis());
    }
}
