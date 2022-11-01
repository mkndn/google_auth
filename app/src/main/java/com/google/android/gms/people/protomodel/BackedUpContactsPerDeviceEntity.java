package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BackedUpContactsPerDeviceEntity extends AbstractSafeParcelable implements BackedUpContactsPerDevice {
    public static final Parcelable.Creator CREATOR = new BackedUpContactsPerDeviceCreator();
    private final Long mAndroidDeviceId;
    private final String mDeviceDisplayName;
    private final String mDeviceId;
    private final DeviceVersionEntity mDeviceVersion;
    private final Long mLastRestoreTimestampMs;
    private final Long mLastUpdatedTimestampMs;
    private final List mSourceStats;
    private List mSourceStatsInternal;

    public BackedUpContactsPerDeviceEntity(String str, Long l, List list, String str2, Long l2, Long l3, DeviceVersionEntity deviceVersionEntity) {
        this.mDeviceId = str;
        this.mAndroidDeviceId = l;
        this.mSourceStats = list;
        this.mDeviceDisplayName = str2;
        this.mLastUpdatedTimestampMs = l2;
        this.mLastRestoreTimestampMs = l3;
        this.mDeviceVersion = deviceVersionEntity;
    }

    public static boolean equals(BackedUpContactsPerDevice backedUpContactsPerDevice, BackedUpContactsPerDevice backedUpContactsPerDevice2) {
        return Objects.equal(backedUpContactsPerDevice.getDeviceId(), backedUpContactsPerDevice2.getDeviceId()) && Objects.equal(backedUpContactsPerDevice.getAndroidDeviceId(), backedUpContactsPerDevice2.getAndroidDeviceId()) && Objects.equal(backedUpContactsPerDevice.getSourceStats(), backedUpContactsPerDevice2.getSourceStats()) && Objects.equal(backedUpContactsPerDevice.getDeviceDisplayName(), backedUpContactsPerDevice2.getDeviceDisplayName()) && Objects.equal(backedUpContactsPerDevice.getLastUpdatedTimestampMs(), backedUpContactsPerDevice2.getLastUpdatedTimestampMs()) && Objects.equal(backedUpContactsPerDevice.getLastRestoreTimestampMs(), backedUpContactsPerDevice2.getLastRestoreTimestampMs()) && Objects.equal(backedUpContactsPerDevice.getDeviceVersion(), backedUpContactsPerDevice2.getDeviceVersion());
    }

    @Override // com.google.android.gms.people.protomodel.BackedUpContactsPerDevice
    public Long getAndroidDeviceId() {
        return this.mAndroidDeviceId;
    }

    @Override // com.google.android.gms.people.protomodel.BackedUpContactsPerDevice
    public String getDeviceDisplayName() {
        return this.mDeviceDisplayName;
    }

    @Override // com.google.android.gms.people.protomodel.BackedUpContactsPerDevice
    public String getDeviceId() {
        return this.mDeviceId;
    }

    @Override // com.google.android.gms.people.protomodel.BackedUpContactsPerDevice
    public DeviceVersion getDeviceVersion() {
        return this.mDeviceVersion;
    }

    @Override // com.google.android.gms.people.protomodel.BackedUpContactsPerDevice
    public Long getLastRestoreTimestampMs() {
        return this.mLastRestoreTimestampMs;
    }

    @Override // com.google.android.gms.people.protomodel.BackedUpContactsPerDevice
    public Long getLastUpdatedTimestampMs() {
        return this.mLastUpdatedTimestampMs;
    }

    @Override // com.google.android.gms.people.protomodel.BackedUpContactsPerDevice
    public List getSourceStats() {
        if (this.mSourceStatsInternal == null && this.mSourceStats != null) {
            this.mSourceStatsInternal = new ArrayList(this.mSourceStats.size());
            for (SourceStats sourceStats : this.mSourceStats) {
                this.mSourceStatsInternal.add(sourceStats);
            }
        }
        return this.mSourceStatsInternal;
    }

    public int hashCode() {
        return hashCode(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        BackedUpContactsPerDeviceCreator.writeToParcel(this, parcel, i);
    }

    public static int hashCode(BackedUpContactsPerDevice backedUpContactsPerDevice) {
        return Objects.hashCode(backedUpContactsPerDevice.getDeviceId(), backedUpContactsPerDevice.getAndroidDeviceId(), backedUpContactsPerDevice.getSourceStats(), backedUpContactsPerDevice.getDeviceDisplayName(), backedUpContactsPerDevice.getLastUpdatedTimestampMs(), backedUpContactsPerDevice.getLastRestoreTimestampMs(), backedUpContactsPerDevice.getDeviceVersion());
    }

    public boolean equals(Object obj) {
        if (obj instanceof BackedUpContactsPerDevice) {
            if (this == obj) {
                return true;
            }
            return equals(this, (BackedUpContactsPerDevice) obj);
        }
        return false;
    }
}
