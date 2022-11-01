package com.google.android.gms.fido.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum Transport implements ReflectedParcelable {
    BLUETOOTH_CLASSIC("bt"),
    BLUETOOTH_LOW_ENERGY("ble"),
    NFC("nfc"),
    USB("usb"),
    INTERNAL("internal"),
    CABLE("cable");
    
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.google.android.gms.fido.common.Transport.1
        @Override // android.os.Parcelable.Creator
        public Transport createFromParcel(Parcel parcel) {
            try {
                return Transport.fromString(parcel.readString());
            } catch (UnsupportedTransportException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // android.os.Parcelable.Creator
        public Transport[] newArray(int i) {
            return new Transport[i];
        }
    };
    private final String value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class UnsupportedTransportException extends Exception {
        public UnsupportedTransportException(String str) {
            super(str);
        }
    }

    Transport(String str) {
        this.value = str;
    }

    public static Transport fromString(String str) {
        Transport[] values;
        for (Transport transport : values()) {
            if (str.equals(transport.value)) {
                return transport;
            }
        }
        throw new UnsupportedTransportException(String.format("Transport %s not supported", str));
    }

    public static List parseTransports(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        HashSet hashSet = new HashSet(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            String string = jSONArray.getString(i);
            if (string != null && !string.isEmpty()) {
                try {
                    hashSet.add(fromString(string));
                } catch (UnsupportedTransportException e) {
                    Log.w("Transport", "Ignoring unrecognized transport " + string);
                }
            }
        }
        return new ArrayList(hashSet);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.value);
    }
}
