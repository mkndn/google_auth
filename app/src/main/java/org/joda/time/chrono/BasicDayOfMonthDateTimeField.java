package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

/* compiled from: PG */
/* loaded from: classes.dex */
final class BasicDayOfMonthDateTimeField extends PreciseDurationDateTimeField {
    private final BasicChronology iChronology;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicDayOfMonthDateTimeField(BasicChronology basicChronology, DurationField durationField) {
        super(DateTimeFieldType.dayOfMonth(), durationField);
        this.iChronology = basicChronology;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int get(long j) {
        return this.iChronology.getDayOfMonth(j);
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMaximumValue() {
        return this.iChronology.getDaysInMonthMax();
    }

    @Override // org.joda.time.field.PreciseDurationDateTimeField
    protected int getMaximumValueForSet(long j, int i) {
        return this.iChronology.getDaysInMonthMaxForSet(j, i);
    }

    @Override // org.joda.time.field.PreciseDurationDateTimeField, org.joda.time.DateTimeField
    public int getMinimumValue() {
        return 1;
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getRangeDurationField() {
        return this.iChronology.months();
    }

    @Override // org.joda.time.field.BaseDateTimeField
    public int getMaximumValue(long j) {
        return this.iChronology.getDaysInMonthMax(j);
    }
}
