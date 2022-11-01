package com.google.android.gms.googlehelp.internal.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TogglingData extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new TogglingDataCreator();
    String callingPackageName;
    String pipFileName;
    String pipTitle;

    private TogglingData() {
    }

    public TogglingData setPipTitle(String str) {
        this.pipTitle = str;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TogglingDataCreator.writeToParcel(this, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TogglingData(String str, String str2, String str3) {
        this.callingPackageName = str;
        this.pipFileName = str2;
        this.pipTitle = str3;
    }
}
