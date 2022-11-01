package com.google.android.apps.authenticator.util;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PendingIntent implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.google.android.apps.authenticator.util.PendingIntent.1
        @Override // android.os.Parcelable.Creator
        public PendingIntent createFromParcel(Parcel parcel) {
            return new PendingIntent((android.app.PendingIntent) parcel.readParcelable(null));
        }

        @Override // android.os.Parcelable.Creator
        public PendingIntent[] newArray(int i) {
            return new PendingIntent[i];
        }
    };
    private final android.app.PendingIntent mPendingIntent;

    private PendingIntent(android.app.PendingIntent pendingIntent) {
        this.mPendingIntent = pendingIntent;
    }

    public static PendingIntent fromPendingIntent(Object obj) {
        if (obj instanceof PendingIntent) {
            return (PendingIntent) obj;
        }
        if (obj instanceof android.app.PendingIntent) {
            return new PendingIntent((android.app.PendingIntent) obj);
        }
        throw new RuntimeException(String.format("Parameter %s is not a PendingIntent !", obj.toString()));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void send(Context context, int i, Intent intent) {
        this.mPendingIntent.send(context, i, intent);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mPendingIntent, i);
    }
}
