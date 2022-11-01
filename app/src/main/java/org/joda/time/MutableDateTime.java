package org.joda.time;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.joda.time.base.BaseDateTime;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.ISODateTimeFormat;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MutableDateTime extends BaseDateTime implements ReadableInstant, Cloneable, Serializable {
    public static final int ROUND_CEILING = 2;
    public static final int ROUND_FLOOR = 1;
    public static final int ROUND_HALF_CEILING = 4;
    public static final int ROUND_HALF_EVEN = 5;
    public static final int ROUND_HALF_FLOOR = 3;
    public static final int ROUND_NONE = 0;
    private static final long serialVersionUID = 2852608688135209575L;
    private DateTimeField iRoundingField;
    private int iRoundingMode;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Property extends AbstractReadableInstantFieldProperty {
        private static final long serialVersionUID = -4481126543819298617L;
        private DateTimeField iField;
        private MutableDateTime iInstant;

        Property(MutableDateTime mutableDateTime, DateTimeField dateTimeField) {
            this.iInstant = mutableDateTime;
            this.iField = dateTimeField;
        }

        private void readObject(ObjectInputStream objectInputStream) {
            this.iInstant = (MutableDateTime) objectInputStream.readObject();
            this.iField = ((DateTimeFieldType) objectInputStream.readObject()).getField(this.iInstant.getChronology());
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.writeObject(this.iInstant);
            objectOutputStream.writeObject(this.iField.getType());
        }

        @Override // org.joda.time.field.AbstractReadableInstantFieldProperty
        protected Chronology getChronology() {
            return this.iInstant.getChronology();
        }

        @Override // org.joda.time.field.AbstractReadableInstantFieldProperty
        public DateTimeField getField() {
            return this.iField;
        }

        @Override // org.joda.time.field.AbstractReadableInstantFieldProperty
        protected long getMillis() {
            return this.iInstant.getMillis();
        }

        public MutableDateTime set(int i) {
            this.iInstant.setMillis(getField().set(this.iInstant.getMillis(), i));
            return this.iInstant;
        }
    }

    public MutableDateTime() {
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError("Clone error");
        }
    }

    public Property property(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");
        }
        DateTimeField field = dateTimeFieldType.getField(getChronology());
        if (!field.isSupported()) {
            String valueOf = String.valueOf(dateTimeFieldType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 25).append("Field '").append(valueOf).append("' is not supported").toString());
        }
        return new Property(this, field);
    }

    @Override // org.joda.time.base.BaseDateTime
    public void setMillis(long j) {
        switch (this.iRoundingMode) {
            case 1:
                j = this.iRoundingField.roundFloor(j);
                break;
            case 2:
                j = this.iRoundingField.roundCeiling(j);
                break;
            case 3:
                j = this.iRoundingField.roundHalfFloor(j);
                break;
            case 4:
                j = this.iRoundingField.roundHalfCeiling(j);
                break;
            case 5:
                j = this.iRoundingField.roundHalfEven(j);
                break;
        }
        super.setMillis(j);
    }

    @Override // org.joda.time.base.AbstractInstant
    public String toString() {
        return ISODateTimeFormat.dateTime().print(this);
    }

    public MutableDateTime(long j, DateTimeZone dateTimeZone) {
        super(j, dateTimeZone);
    }
}
