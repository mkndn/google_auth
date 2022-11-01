package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

/* compiled from: PG */
/* loaded from: classes.dex */
final class BasicWeekOfWeekyearDateTimeField extends PreciseDurationDateTimeField {
    private final BasicChronology iChronology;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicWeekOfWeekyearDateTimeField(BasicChronology basicChronology, DurationField durationField) {
        super(DateTimeFieldType.weekOfWeekyear(), durationField);
        this.iChronology = basicChronology;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int get(long j) {
        return this.iChronology.getWeekOfWeekyear(j);
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMaximumValue() {
        return 53;
    }

    @Override // org.joda.time.field.PreciseDurationDateTimeField
    protected int getMaximumValueForSet(long j, int i) {
        if (i > 52) {
            return getMaximumValue(j);
        }
        return 52;
    }

    @Override // org.joda.time.field.PreciseDurationDateTimeField, org.joda.time.DateTimeField
    public int getMinimumValue() {
        return 1;
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getRangeDurationField() {
        return this.iChronology.weekyears();
    }

    @Override // org.joda.time.field.PreciseDurationDateTimeField, org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long remainder(long j) {
        return super.remainder(j + 259200000);
    }

    @Override // org.joda.time.field.PreciseDurationDateTimeField, org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long roundCeiling(long j) {
        return super.roundCeiling(j + 259200000) - 259200000;
    }

    @Override // org.joda.time.field.PreciseDurationDateTimeField, org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long roundFloor(long j) {
        return super.roundFloor(j + 259200000) - 259200000;
    }

    @Override // org.joda.time.field.BaseDateTimeField
    public int getMaximumValue(long j) {
        return this.iChronology.getWeeksInYear(this.iChronology.getWeekyear(j));
    }
}
