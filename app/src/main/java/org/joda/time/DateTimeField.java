package org.joda.time;

import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DateTimeField {
    public abstract long add(long j, int i);

    public abstract int get(long j);

    public abstract String getAsShortText(int i, Locale locale);

    public abstract String getAsShortText(long j, Locale locale);

    public abstract String getAsText(int i, Locale locale);

    public abstract String getAsText(long j, Locale locale);

    public abstract DurationField getDurationField();

    public abstract DurationField getLeapDurationField();

    public abstract int getMaximumTextLength(Locale locale);

    public abstract int getMaximumValue();

    public abstract int getMinimumValue();

    public abstract String getName();

    public abstract DurationField getRangeDurationField();

    public abstract DateTimeFieldType getType();

    public abstract boolean isLeap(long j);

    public abstract boolean isSupported();

    public abstract long remainder(long j);

    public abstract long roundCeiling(long j);

    public abstract long roundFloor(long j);

    public abstract long roundHalfCeiling(long j);

    public abstract long roundHalfEven(long j);

    public abstract long roundHalfFloor(long j);

    public abstract long set(long j, int i);

    public abstract long set(long j, String str, Locale locale);
}
