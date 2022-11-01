package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UnsupportedDateTimeField extends DateTimeField implements Serializable {
    private static HashMap cCache = null;
    private static final long serialVersionUID = -1934618396111902255L;
    private final DurationField iDurationField;
    private final DateTimeFieldType iType;

    private UnsupportedDateTimeField(DateTimeFieldType dateTimeFieldType, DurationField durationField) {
        if (dateTimeFieldType != null && durationField != null) {
            this.iType = dateTimeFieldType;
            this.iDurationField = durationField;
            return;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0024 A[Catch: all -> 0x0032, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0008, B:16:0x0024, B:8:0x0011, B:10:0x0019), top: B:26:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0030 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized UnsupportedDateTimeField getInstance(DateTimeFieldType dateTimeFieldType, DurationField durationField) {
        synchronized (UnsupportedDateTimeField.class) {
            HashMap hashMap = cCache;
            UnsupportedDateTimeField unsupportedDateTimeField = null;
            if (hashMap == null) {
                cCache = new HashMap(7);
            } else {
                UnsupportedDateTimeField unsupportedDateTimeField2 = (UnsupportedDateTimeField) hashMap.get(dateTimeFieldType);
                if (unsupportedDateTimeField2 == null || unsupportedDateTimeField2.getDurationField() == durationField) {
                    unsupportedDateTimeField = unsupportedDateTimeField2;
                    if (unsupportedDateTimeField != null) {
                        UnsupportedDateTimeField unsupportedDateTimeField3 = new UnsupportedDateTimeField(dateTimeFieldType, durationField);
                        cCache.put(dateTimeFieldType, unsupportedDateTimeField3);
                        return unsupportedDateTimeField3;
                    }
                    return unsupportedDateTimeField;
                }
            }
            if (unsupportedDateTimeField != null) {
            }
        }
    }

    private Object readResolve() {
        return getInstance(this.iType, this.iDurationField);
    }

    private UnsupportedOperationException unsupported() {
        String valueOf = String.valueOf(this.iType);
        return new UnsupportedOperationException(new StringBuilder(String.valueOf(valueOf).length() + 21).append(valueOf).append(" field is unsupported").toString());
    }

    @Override // org.joda.time.DateTimeField
    public long add(long j, int i) {
        return getDurationField().add(j, i);
    }

    @Override // org.joda.time.DateTimeField
    public int get(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsShortText(int i, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsText(int i, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getDurationField() {
        return this.iDurationField;
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getLeapDurationField() {
        return null;
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumTextLength(Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumValue() {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMinimumValue() {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getName() {
        return this.iType.getName();
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getRangeDurationField() {
        return null;
    }

    @Override // org.joda.time.DateTimeField
    public DateTimeFieldType getType() {
        return this.iType;
    }

    @Override // org.joda.time.DateTimeField
    public boolean isLeap(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public boolean isSupported() {
        return false;
    }

    @Override // org.joda.time.DateTimeField
    public long remainder(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundCeiling(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundFloor(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundHalfCeiling(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundHalfEven(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundHalfFloor(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long set(long j, int i) {
        throw unsupported();
    }

    public String toString() {
        return "UnsupportedDateTimeField";
    }

    @Override // org.joda.time.DateTimeField
    public String getAsShortText(long j, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsText(long j, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long set(long j, String str, Locale locale) {
        throw unsupported();
    }
}
