package org.joda.time.format;

import java.util.Arrays;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DateTimeParserBucket {
    private final Chronology iChrono;
    private int iDefaultYear;
    private Locale iLocale;
    private final long iMillis;
    private Integer iOffset;
    private Integer iPivotYear;
    private SavedField[] iSavedFields = new SavedField[8];
    private int iSavedFieldsCount;
    private boolean iSavedFieldsShared;
    private Object iSavedState;
    private DateTimeZone iZone;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SavedField implements Comparable {
        final DateTimeField iField;
        final Locale iLocale;
        final String iText;
        final int iValue;

        SavedField(DateTimeField dateTimeField, int i) {
            this.iField = dateTimeField;
            this.iValue = i;
            this.iText = null;
            this.iLocale = null;
        }

        long set(long j, boolean z) {
            long j2;
            String str = this.iText;
            if (str == null) {
                j2 = this.iField.set(j, this.iValue);
            } else {
                j2 = this.iField.set(j, str, this.iLocale);
            }
            if (z) {
                return this.iField.roundFloor(j2);
            }
            return j2;
        }

        @Override // java.lang.Comparable
        public int compareTo(SavedField savedField) {
            DateTimeField dateTimeField = savedField.iField;
            int compareReverse = DateTimeParserBucket.compareReverse(this.iField.getRangeDurationField(), dateTimeField.getRangeDurationField());
            if (compareReverse != 0) {
                return compareReverse;
            }
            return DateTimeParserBucket.compareReverse(this.iField.getDurationField(), dateTimeField.getDurationField());
        }

        SavedField(DateTimeField dateTimeField, String str, Locale locale) {
            this.iField = dateTimeField;
            this.iValue = 0;
            this.iText = str;
            this.iLocale = locale;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class SavedState {
        final Integer iOffset;
        final SavedField[] iSavedFields;
        final int iSavedFieldsCount;
        final DateTimeZone iZone;

        SavedState() {
            this.iZone = DateTimeParserBucket.this.iZone;
            this.iOffset = DateTimeParserBucket.this.iOffset;
            this.iSavedFields = DateTimeParserBucket.this.iSavedFields;
            this.iSavedFieldsCount = DateTimeParserBucket.this.iSavedFieldsCount;
        }

        boolean restoreState(DateTimeParserBucket dateTimeParserBucket) {
            if (dateTimeParserBucket != DateTimeParserBucket.this) {
                return false;
            }
            dateTimeParserBucket.iZone = this.iZone;
            dateTimeParserBucket.iOffset = this.iOffset;
            dateTimeParserBucket.iSavedFields = this.iSavedFields;
            if (this.iSavedFieldsCount < dateTimeParserBucket.iSavedFieldsCount) {
                dateTimeParserBucket.iSavedFieldsShared = true;
            }
            dateTimeParserBucket.iSavedFieldsCount = this.iSavedFieldsCount;
            return true;
        }
    }

    public DateTimeParserBucket(long j, Chronology chronology, Locale locale, Integer num, int i) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iMillis = j;
        this.iZone = chronology2.getZone();
        this.iChrono = chronology2.withUTC();
        this.iLocale = locale == null ? Locale.getDefault() : locale;
        this.iPivotYear = num;
        this.iDefaultYear = i;
    }

    static int compareReverse(DurationField durationField, DurationField durationField2) {
        if (durationField != null && durationField.isSupported()) {
            if (durationField2 == null || !durationField2.isSupported()) {
                return 1;
            }
            return -durationField.compareTo(durationField2);
        } else if (durationField2 == null || !durationField2.isSupported()) {
            return 0;
        } else {
            return -1;
        }
    }

    private static void sort(SavedField[] savedFieldArr, int i) {
        if (i > 10) {
            Arrays.sort(savedFieldArr, 0, i);
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2;
            while (i3 > 0) {
                int i4 = i3 - 1;
                if (savedFieldArr[i4].compareTo(savedFieldArr[i3]) > 0) {
                    SavedField savedField = savedFieldArr[i3];
                    savedFieldArr[i3] = savedFieldArr[i4];
                    savedFieldArr[i4] = savedField;
                    i3 = i4;
                }
            }
        }
    }

    public long computeMillis(boolean z, String str) {
        SavedField[] savedFieldArr = this.iSavedFields;
        int i = this.iSavedFieldsCount;
        if (this.iSavedFieldsShared) {
            savedFieldArr = (SavedField[]) savedFieldArr.clone();
            this.iSavedFields = savedFieldArr;
            this.iSavedFieldsShared = false;
        }
        sort(savedFieldArr, i);
        if (i > 0) {
            DurationField field = DurationFieldType.months().getField(this.iChrono);
            DurationField field2 = DurationFieldType.days().getField(this.iChrono);
            DurationField durationField = savedFieldArr[0].iField.getDurationField();
            if (compareReverse(durationField, field) >= 0 && compareReverse(durationField, field2) <= 0) {
                saveField(DateTimeFieldType.year(), this.iDefaultYear);
                return computeMillis(z, str);
            }
        }
        long j = this.iMillis;
        for (int i2 = 0; i2 < i; i2++) {
            try {
                j = savedFieldArr[i2].set(j, z);
            } catch (IllegalFieldValueException e) {
                if (str != null) {
                    e.prependMessage(new StringBuilder(String.valueOf(str).length() + 15).append("Cannot parse \"").append(str).append('\"').toString());
                }
                throw e;
            }
        }
        if (z) {
            int i3 = 0;
            while (i3 < i) {
                j = savedFieldArr[i3].set(j, i3 == i + (-1));
                i3++;
            }
        }
        Integer num = this.iOffset;
        if (num != null) {
            return j - num.intValue();
        }
        DateTimeZone dateTimeZone = this.iZone;
        if (dateTimeZone != null) {
            int offsetFromLocal = dateTimeZone.getOffsetFromLocal(j);
            long j2 = j - offsetFromLocal;
            if (offsetFromLocal != this.iZone.getOffset(j2)) {
                String valueOf = String.valueOf(this.iZone);
                String sb = new StringBuilder(String.valueOf(valueOf).length() + 53).append("Illegal instant due to time zone offset transition (").append(valueOf).append(')').toString();
                if (str != null) {
                    sb = new StringBuilder(String.valueOf(str).length() + 17 + String.valueOf(sb).length()).append("Cannot parse \"").append(str).append("\": ").append(sb).toString();
                }
                throw new IllegalInstantException(sb);
            }
            return j2;
        }
        return j;
    }

    public Chronology getChronology() {
        return this.iChrono;
    }

    public Locale getLocale() {
        return this.iLocale;
    }

    public Integer getPivotYear() {
        return this.iPivotYear;
    }

    public boolean restoreState(Object obj) {
        if ((obj instanceof SavedState) && ((SavedState) obj).restoreState(this)) {
            this.iSavedState = obj;
            return true;
        }
        return false;
    }

    public void saveField(DateTimeField dateTimeField, int i) {
        saveField(new SavedField(dateTimeField, i));
    }

    public Object saveState() {
        if (this.iSavedState == null) {
            this.iSavedState = new SavedState();
        }
        return this.iSavedState;
    }

    public void setOffset(Integer num) {
        this.iSavedState = null;
        this.iOffset = num;
    }

    public void setZone(DateTimeZone dateTimeZone) {
        this.iSavedState = null;
        this.iZone = dateTimeZone;
    }

    public void saveField(DateTimeFieldType dateTimeFieldType, int i) {
        saveField(new SavedField(dateTimeFieldType.getField(this.iChrono), i));
    }

    public void saveField(DateTimeFieldType dateTimeFieldType, String str, Locale locale) {
        saveField(new SavedField(dateTimeFieldType.getField(this.iChrono), str, locale));
    }

    private void saveField(SavedField savedField) {
        SavedField[] savedFieldArr = this.iSavedFields;
        int i = this.iSavedFieldsCount;
        if (i == savedFieldArr.length || this.iSavedFieldsShared) {
            SavedField[] savedFieldArr2 = new SavedField[i == savedFieldArr.length ? i + i : savedFieldArr.length];
            System.arraycopy(savedFieldArr, 0, savedFieldArr2, 0, i);
            this.iSavedFields = savedFieldArr2;
            this.iSavedFieldsShared = false;
            savedFieldArr = savedFieldArr2;
        }
        this.iSavedState = null;
        savedFieldArr[i] = savedField;
        this.iSavedFieldsCount = i + 1;
    }
}
