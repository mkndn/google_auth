package com.google.android.apps.authenticator.util;

import android.content.Intent;
import android.os.Parcel;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class IntentUtils {
    public static String getMandatoryExtraString(Intent intent, String str) {
        if (!intent.hasExtra(str)) {
            throw new IntentParsingException(String.format("Intent does not contain %s extra parameter", str));
        }
        return intent.getStringExtra(str);
    }

    public static byte[] marshallParcelableList(ArrayList arrayList) {
        Parcel obtain = Parcel.obtain();
        obtain.writeList(arrayList);
        obtain.setDataPosition(0);
        return obtain.marshall();
    }

    public static ArrayList unmarshallParcelableList(byte[] bArr, ClassLoader classLoader) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        return obtain.readArrayList(classLoader);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class IntentParsingException extends Exception {
        public IntentParsingException(String str) {
            super(str);
        }

        public IntentParsingException(Throwable th) {
            super(th);
        }
    }
}
