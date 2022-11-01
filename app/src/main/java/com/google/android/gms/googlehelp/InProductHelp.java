package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InProductHelp extends AbstractSafeParcelable {
    public static final int CHANNEL_C2C = 2;
    public static final int CHANNEL_CHAT = 1;
    public static final int CHANNEL_EMAIL = 3;
    public static final int CHANNEL_NONE = 0;
    public static final int CHANNEL_PHONE = 4;
    public static final Parcelable.Creator CREATOR = new InProductHelpCreator();
    public static final int OPENING_MODE_NONE = 0;
    public static final int OPENING_MODE_SMART_JOURNEY = 1;
    private int channel;
    private String contentUrl;
    private GoogleHelp googleHelp;
    private int openingMode;
    private String searchQuery;
    private String symptom;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InProductHelp(GoogleHelp googleHelp, String str, String str2, int i, String str3, int i2) {
        this.googleHelp = googleHelp;
        this.searchQuery = str;
        this.contentUrl = str2;
        this.openingMode = i;
        this.symptom = str3;
        this.channel = i2;
    }

    public int getChannel() {
        return this.channel;
    }

    public String getContentUrl() {
        return this.contentUrl;
    }

    public GoogleHelp getGoogleHelp() {
        return this.googleHelp;
    }

    public int getOpeningMode() {
        return this.openingMode;
    }

    public String getSearchQuery() {
        return this.searchQuery;
    }

    public String getSymptom() {
        return this.symptom;
    }

    public InProductHelp setGoogleHelp(GoogleHelp googleHelp) {
        this.googleHelp = googleHelp;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        InProductHelpCreator.writeToParcel(this, parcel, i);
    }
}
