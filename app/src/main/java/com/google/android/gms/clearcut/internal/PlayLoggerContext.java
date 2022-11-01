package com.google.android.gms.clearcut.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.clearcut.PIILevel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$QosTierConfiguration$QosTier;
import java.util.EnumSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PlayLoggerContext extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new PlayLoggerContextCreator();
    public static final int INVALID_PACKAGE_VERSION_CODE = -1;
    public final Integer appMobilespecId;
    public final boolean isAnonymous;
    public final boolean logAndroidId;
    public final int logSource;
    public final String logSourceName;
    public final String packageName;
    public final int packageVersionCode;
    public final int piiLevelSet;
    public final int qosTier;
    public final boolean scrubMccMnc;
    public final String uploadAccountName;

    public PlayLoggerContext(String str, int i, int i2, String str2, boolean z, String str3, boolean z2, int i3, Integer num, boolean z3, int i4) {
        this.packageName = str;
        this.packageVersionCode = i;
        this.logSource = i2;
        this.uploadAccountName = str2;
        this.logAndroidId = z;
        this.logSourceName = str3;
        this.isAnonymous = z2;
        this.qosTier = i3;
        this.appMobilespecId = num;
        this.scrubMccMnc = z3;
        this.piiLevelSet = i4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlayLoggerContext) {
            PlayLoggerContext playLoggerContext = (PlayLoggerContext) obj;
            return Objects.equal(this.packageName, playLoggerContext.packageName) && this.packageVersionCode == playLoggerContext.packageVersionCode && this.logSource == playLoggerContext.logSource && Objects.equal(this.logSourceName, playLoggerContext.logSourceName) && Objects.equal(this.uploadAccountName, playLoggerContext.uploadAccountName) && this.logAndroidId == playLoggerContext.logAndroidId && this.isAnonymous == playLoggerContext.isAnonymous && this.qosTier == playLoggerContext.qosTier && Objects.equal(this.appMobilespecId, playLoggerContext.appMobilespecId) && this.scrubMccMnc == playLoggerContext.scrubMccMnc && this.piiLevelSet == playLoggerContext.piiLevelSet;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.packageName, Integer.valueOf(this.packageVersionCode), Integer.valueOf(this.logSource), this.logSourceName, this.uploadAccountName, Boolean.valueOf(this.logAndroidId), Boolean.valueOf(this.isAnonymous), Integer.valueOf(this.qosTier), this.appMobilespecId, Boolean.valueOf(this.scrubMccMnc), Integer.valueOf(this.piiLevelSet));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("package=").append(this.packageName).append(',');
        sb.append("packageVersionCode=").append(this.packageVersionCode).append(',');
        sb.append("logSource=").append(this.logSource).append(',');
        sb.append("logSourceName=").append(this.logSourceName).append(',');
        sb.append("uploadAccount=").append(this.uploadAccountName).append(',');
        sb.append("logAndroidId=").append(this.logAndroidId).append(',');
        sb.append("isAnonymous=").append(this.isAnonymous).append(',');
        sb.append("qosTier=").append(this.qosTier).append(',');
        sb.append("appMobilespecId=").append(this.appMobilespecId).append(',');
        sb.append("scrubMccMnc=").append(this.scrubMccMnc);
        sb.append("piiLevelset=").append(this.piiLevelSet);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        PlayLoggerContextCreator.writeToParcel(this, parcel, i);
    }

    public PlayLoggerContext(String str, int i, String str2, String str3, ClientAnalytics$QosTierConfiguration$QosTier clientAnalytics$QosTierConfiguration$QosTier, Integer num, boolean z, EnumSet enumSet) {
        this(str, i, -1, str3, enumSet.contains(PIILevel.ANDROID_ID), str2, enumSet.equals(PIILevel.deidentified), clientAnalytics$QosTierConfiguration$QosTier.getNumber(), num, z, PIILevel.encode(enumSet));
    }
}
