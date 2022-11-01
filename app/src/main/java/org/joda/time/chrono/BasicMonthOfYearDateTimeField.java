package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class BasicMonthOfYearDateTimeField extends ImpreciseDateTimeField {
    private final BasicChronology iChronology;
    private final int iLeapMonth;
    private final int iMax;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicMonthOfYearDateTimeField(BasicChronology basicChronology, int i) {
        super(DateTimeFieldType.monthOfYear(), basicChronology.getAverageMillisPerMonth());
        this.iChronology = basicChronology;
        this.iMax = basicChronology.getMaxMonth();
        this.iLeapMonth = i;
    }

    @Override // org.joda.time.field.ImpreciseDateTimeField, org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long add(long j, int i) {
        int i2;
        int i3;
        if (i == 0) {
            return j;
        }
        long millisOfDay = this.iChronology.getMillisOfDay(j);
        int year = this.iChronology.getYear(j);
        int monthOfYear = this.iChronology.getMonthOfYear(j, year);
        int i4 = (monthOfYear - 1) + i;
        if (i4 >= 0) {
            int i5 = this.iMax;
            i2 = (i4 / i5) + year;
            i3 = (i4 % i5) + 1;
        } else {
            i2 = ((i4 / this.iMax) + year) - 1;
            int abs = Math.abs(i4);
            int i6 = this.iMax;
            int i7 = abs % i6;
            if (i7 == 0) {
                i7 = i6;
            }
            i3 = (i6 - i7) + 1;
            if (i3 == 1) {
                i2++;
            }
        }
        int dayOfMonth = this.iChronology.getDayOfMonth(j, year, monthOfYear);
        int daysInYearMonth = this.iChronology.getDaysInYearMonth(i2, i3);
        if (dayOfMonth > daysInYearMonth) {
            dayOfMonth = daysInYearMonth;
        }
        return this.iChronology.getYearMonthDayMillis(i2, i3, dayOfMonth) + millisOfDay;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int get(long j) {
        return this.iChronology.getMonthOfYear(j);
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public DurationField getLeapDurationField() {
        return this.iChronology.days();
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMaximumValue() {
        return this.iMax;
    }

    @Override // org.joda.time.DateTimeField
    public int getMinimumValue() {
        return 1;
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getRangeDurationField() {
        return this.iChronology.years();
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public boolean isLeap(long j) {
        int year = this.iChronology.getYear(j);
        return this.iChronology.isLeapYear(year) && this.iChronology.getMonthOfYear(j, year) == this.iLeapMonth;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long remainder(long j) {
        return j - roundFloor(j);
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long roundFloor(long j) {
        int year = this.iChronology.getYear(j);
        return this.iChronology.getYearMonthMillis(year, this.iChronology.getMonthOfYear(j, year));
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long set(long j, int i) {
        FieldUtils.verifyValueBounds(this, i, 1, this.iMax);
        int year = this.iChronology.getYear(j);
        int dayOfMonth = this.iChronology.getDayOfMonth(j, year);
        int daysInYearMonth = this.iChronology.getDaysInYearMonth(year, i);
        if (dayOfMonth > daysInYearMonth) {
            dayOfMonth = daysInYearMonth;
        }
        return this.iChronology.getYearMonthDayMillis(year, i, dayOfMonth) + this.iChronology.getMillisOfDay(j);
    }

    @Override // org.joda.time.field.ImpreciseDateTimeField
    public long add(long j, long j2) {
        long j3;
        long j4;
        int i = (int) j2;
        if (i == j2) {
            return add(j, i);
        }
        long millisOfDay = this.iChronology.getMillisOfDay(j);
        int year = this.iChronology.getYear(j);
        int monthOfYear = this.iChronology.getMonthOfYear(j, year);
        long j5 = (monthOfYear - 1) + j2;
        if (j5 >= 0) {
            int i2 = this.iMax;
            j3 = year + (j5 / i2);
            j4 = (j5 % i2) + 1;
        } else {
            j3 = (year + (j5 / this.iMax)) - 1;
            long abs = Math.abs(j5);
            int i3 = this.iMax;
            int i4 = (int) (abs % i3);
            if (i4 == 0) {
                i4 = i3;
            }
            j4 = (i3 - i4) + 1;
            if (j4 == 1) {
                j3++;
            }
        }
        if (j3 >= this.iChronology.getMinYear() && j3 <= this.iChronology.getMaxYear()) {
            int i5 = (int) j3;
            int i6 = (int) j4;
            int dayOfMonth = this.iChronology.getDayOfMonth(j, year, monthOfYear);
            int daysInYearMonth = this.iChronology.getDaysInYearMonth(i5, i6);
            if (dayOfMonth > daysInYearMonth) {
                dayOfMonth = daysInYearMonth;
            }
            return this.iChronology.getYearMonthDayMillis(i5, i6, dayOfMonth) + millisOfDay;
        }
        throw new IllegalArgumentException(new StringBuilder(58).append("Magnitude of add amount is too large: ").append(j2).toString());
    }
}
