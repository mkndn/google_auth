package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OfflineSuggestion extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new OfflineSuggestionCreator();
    final String body;
    final String browseUrl;
    final String id;
    final String title;

    public OfflineSuggestion(String str, String str2, String str3, String str4) {
        this.id = str;
        this.title = str2;
        this.body = str4;
        this.browseUrl = str3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        OfflineSuggestionCreator.writeToParcel(this, parcel, i);
    }
}
