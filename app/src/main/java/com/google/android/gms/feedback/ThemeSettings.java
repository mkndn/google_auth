package com.google.android.gms.feedback;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ThemeSettings extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ThemeSettingsCreator();
    public static final int DARK_THEME = 2;
    public static final int DEFAULT_PRIMARY_COLOR = 0;
    public static final int LIGHT_THEME = 0;
    @Deprecated
    public static final int LIGHT_WITH_DARK_ACTION_BAR_THEME = 1;
    public static final int SYSTEM_DEFAULT_THEME = 3;
    int primaryColor;
    int themeId;

    public ThemeSettings() {
        this(3, 0);
    }

    public ThemeSettings setTheme(int i) {
        this.themeId = i;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ThemeSettingsCreator.writeToParcel(this, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThemeSettings(int i, int i2) {
        this.themeId = i;
        this.primaryColor = i2;
    }
}
