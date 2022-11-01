package com.google.android.gms.feedback;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LogOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new LogOptionsCreator();
    boolean includeContentCaptureDumpLogs;
    boolean includeFullMainLogs;
    boolean includeFullSystemLogs;
    boolean includeRadioLogs;
    String logFilter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogOptions(String str, boolean z, boolean z2, boolean z3, boolean z4) {
        this.logFilter = str;
        this.includeRadioLogs = z;
        this.includeFullSystemLogs = z2;
        this.includeFullMainLogs = z3;
        this.includeContentCaptureDumpLogs = z4;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        LogOptionsCreator.writeToParcel(this, parcel, i);
    }
}
