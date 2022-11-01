package org.joda.time;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DurationField implements Comparable {
    public abstract long add(long j, int i);

    public abstract long add(long j, long j2);

    public abstract DurationFieldType getType();

    public abstract long getUnitMillis();

    public abstract boolean isPrecise();

    public abstract boolean isSupported();
}
