package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class ChannelIdValue extends AbstractSafeParcelable {
    private final String jsonObjectValueAsString;
    private final String stringValue;
    private final ChannelIdValueType valueType;
    public static final Parcelable.Creator CREATOR = new ChannelIdValueCreator();
    public static final ChannelIdValue ABSENT = new ChannelIdValue();
    public static final ChannelIdValue UNAVAILABLE = new ChannelIdValue("unavailable");
    public static final ChannelIdValue UNUSED = new ChannelIdValue("unused");

    /* compiled from: PG */
    /* renamed from: com.google.android.gms.fido.u2f.api.common.ChannelIdValue$1  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$gms$fido$u2f$api$common$ChannelIdValue$ChannelIdValueType;

        static {
            int[] iArr = new int[ChannelIdValueType.values().length];
            $SwitchMap$com$google$android$gms$fido$u2f$api$common$ChannelIdValue$ChannelIdValueType = iArr;
            try {
                iArr[ChannelIdValueType.ABSENT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$gms$fido$u2f$api$common$ChannelIdValue$ChannelIdValueType[ChannelIdValueType.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$gms$fido$u2f$api$common$ChannelIdValue$ChannelIdValueType[ChannelIdValueType.OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum ChannelIdValueType implements Parcelable {
        ABSENT(0),
        STRING(1),
        OBJECT(2);
        
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.google.android.gms.fido.u2f.api.common.ChannelIdValue.ChannelIdValueType.1
            @Override // android.os.Parcelable.Creator
            public ChannelIdValueType createFromParcel(Parcel parcel) {
                try {
                    return ChannelIdValue.toChannelIdValueType(parcel.readInt());
                } catch (UnsupportedChannelIdValueTypeException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override // android.os.Parcelable.Creator
            public ChannelIdValueType[] newArray(int i) {
                return new ChannelIdValueType[i];
            }
        };
        private final int type;

        ChannelIdValueType(int i) {
            this.type = i;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.type);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class UnsupportedChannelIdValueTypeException extends Exception {
        public UnsupportedChannelIdValueTypeException(int i) {
            super(String.format("ChannelIdValueType %s not supported", Integer.valueOf(i)));
        }
    }

    private ChannelIdValue() {
        this.valueType = ChannelIdValueType.ABSENT;
        this.jsonObjectValueAsString = null;
        this.stringValue = null;
    }

    public static ChannelIdValueType toChannelIdValueType(int i) {
        ChannelIdValueType[] values;
        for (ChannelIdValueType channelIdValueType : ChannelIdValueType.values()) {
            if (i == channelIdValueType.type) {
                return channelIdValueType;
            }
        }
        throw new UnsupportedChannelIdValueTypeException(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChannelIdValue) {
            ChannelIdValue channelIdValue = (ChannelIdValue) obj;
            if (this.valueType.equals(channelIdValue.valueType)) {
                switch (AnonymousClass1.$SwitchMap$com$google$android$gms$fido$u2f$api$common$ChannelIdValue$ChannelIdValueType[this.valueType.ordinal()]) {
                    case 1:
                        return true;
                    case 2:
                        return this.stringValue.equals(channelIdValue.stringValue);
                    case 3:
                        return this.jsonObjectValueAsString.equals(channelIdValue.jsonObjectValueAsString);
                    default:
                        return false;
                }
            }
            return false;
        }
        return false;
    }

    public String getObjectValueAsString() {
        return this.jsonObjectValueAsString;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public int getTypeAsInt() {
        return this.valueType.type;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ChannelIdValueCreator.writeToParcel(this, parcel, i);
    }

    public int hashCode() {
        int hashCode = this.valueType.hashCode() + 31;
        switch (AnonymousClass1.$SwitchMap$com$google$android$gms$fido$u2f$api$common$ChannelIdValue$ChannelIdValueType[this.valueType.ordinal()]) {
            case 1:
                return hashCode;
            case 2:
                return (hashCode * 31) + this.stringValue.hashCode();
            case 3:
                return (hashCode * 31) + this.jsonObjectValueAsString.hashCode();
            default:
                return hashCode;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelIdValue(int i, String str, String str2) {
        try {
            this.valueType = toChannelIdValueType(i);
            this.stringValue = str;
            this.jsonObjectValueAsString = str2;
        } catch (UnsupportedChannelIdValueTypeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private ChannelIdValue(String str) {
        this.stringValue = (String) Preconditions.checkNotNull(str);
        this.valueType = ChannelIdValueType.STRING;
        this.jsonObjectValueAsString = null;
    }
}
