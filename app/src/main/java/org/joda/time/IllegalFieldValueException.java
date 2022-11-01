package org.joda.time;

/* compiled from: PG */
/* loaded from: classes.dex */
public class IllegalFieldValueException extends IllegalArgumentException {
    private static final long serialVersionUID = 6305711765985447737L;
    private final DateTimeFieldType iDateTimeFieldType;
    private final DurationFieldType iDurationFieldType;
    private final String iFieldName;
    private final Number iLowerBound;
    private String iMessage;
    private final Number iNumberValue;
    private final String iStringValue;
    private final Number iUpperBound;

    public IllegalFieldValueException(DateTimeFieldType dateTimeFieldType, Number number, Number number2, Number number3) {
        super(createMessage(dateTimeFieldType.getName(), number, number2, number3, null));
        this.iDateTimeFieldType = dateTimeFieldType;
        this.iDurationFieldType = null;
        this.iFieldName = dateTimeFieldType.getName();
        this.iNumberValue = number;
        this.iStringValue = null;
        this.iLowerBound = number2;
        this.iUpperBound = number3;
        this.iMessage = super.getMessage();
    }

    private static String createMessage(String str, Number number, Number number2, Number number3, String str2) {
        StringBuilder append = new StringBuilder().append("Value ").append(number).append(" for ").append(str).append(' ');
        if (number2 == null) {
            if (number3 == null) {
                append.append("is not supported");
            } else {
                append.append("must not be larger than ").append(number3);
            }
        } else if (number3 == null) {
            append.append("must not be smaller than ").append(number2);
        } else {
            append.append("must be in the range [").append(number2).append(',').append(number3).append(']');
        }
        if (str2 != null) {
            append.append(": ").append(str2);
        }
        return append.toString();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.iMessage;
    }

    public void prependMessage(String str) {
        String str2 = this.iMessage;
        if (str2 == null) {
            this.iMessage = str;
        } else if (str != null) {
            this.iMessage = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(str2).length()).append(str).append(": ").append(str2).toString();
        }
    }

    public IllegalFieldValueException(DateTimeFieldType dateTimeFieldType, Number number, String str) {
        super(createMessage(dateTimeFieldType.getName(), number, null, null, str));
        this.iDateTimeFieldType = dateTimeFieldType;
        this.iDurationFieldType = null;
        this.iFieldName = dateTimeFieldType.getName();
        this.iNumberValue = number;
        this.iStringValue = null;
        this.iLowerBound = null;
        this.iUpperBound = null;
        this.iMessage = super.getMessage();
    }

    private static String createMessage(String str, String str2) {
        StringBuffer append = new StringBuffer().append("Value ");
        if (str2 == null) {
            append.append("null");
        } else {
            append.append('\"');
            append.append(str2);
            append.append('\"');
        }
        append.append(" for ").append(str).append(' ').append("is not supported");
        return append.toString();
    }

    public IllegalFieldValueException(DateTimeFieldType dateTimeFieldType, String str) {
        super(createMessage(dateTimeFieldType.getName(), str));
        this.iDateTimeFieldType = dateTimeFieldType;
        this.iDurationFieldType = null;
        this.iFieldName = dateTimeFieldType.getName();
        this.iStringValue = str;
        this.iNumberValue = null;
        this.iLowerBound = null;
        this.iUpperBound = null;
        this.iMessage = super.getMessage();
    }
}
