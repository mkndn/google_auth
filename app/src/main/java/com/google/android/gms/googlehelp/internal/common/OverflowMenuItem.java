package com.google.android.gms.googlehelp.internal.common;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OverflowMenuItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new OverflowMenuItemCreator();
    final int id;
    final Intent intent;
    final String title;

    public OverflowMenuItem(int i, String str, Intent intent) {
        this.id = i;
        this.title = str;
        this.intent = intent;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        OverflowMenuItemCreator.writeToParcel(this, parcel, i);
    }
}
