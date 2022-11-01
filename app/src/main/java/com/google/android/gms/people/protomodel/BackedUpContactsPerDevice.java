package com.google.android.gms.people.protomodel;

import android.os.Parcelable;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface BackedUpContactsPerDevice extends Parcelable {
    Long getAndroidDeviceId();

    String getDeviceDisplayName();

    String getDeviceId();

    DeviceVersion getDeviceVersion();

    Long getLastRestoreTimestampMs();

    Long getLastUpdatedTimestampMs();

    List getSourceStats();
}
