package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Configurations extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ConfigurationsCreator();
    public final Map configurationMap = new TreeMap();
    public final long configurationVersion;
    public final Configuration[] configurations;
    public final byte[] experimentToken;
    public final boolean isDelta;
    public final String serverToken;
    public final String snapshotToken;

    public Configurations(String str, String str2, Configuration[] configurationArr, boolean z, byte[] bArr, long j) {
        this.snapshotToken = str;
        this.serverToken = str2;
        this.configurations = configurationArr;
        this.isDelta = z;
        this.experimentToken = bArr;
        this.configurationVersion = j;
        for (Configuration configuration : configurationArr) {
            this.configurationMap.put(Integer.valueOf(configuration.flagType), configuration);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Configurations) {
            Configurations configurations = (Configurations) obj;
            return PhenotypeCore.equals(this.snapshotToken, configurations.snapshotToken) && PhenotypeCore.equals(this.serverToken, configurations.serverToken) && this.configurationMap.equals(configurations.configurationMap) && this.isDelta == configurations.isDelta && Arrays.equals(this.experimentToken, configurations.experimentToken) && this.configurationVersion == configurations.configurationVersion;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.snapshotToken, this.serverToken, this.configurationMap, Boolean.valueOf(this.isDelta), this.experimentToken, Long.valueOf(this.configurationVersion)});
    }

    public String toString() {
        String encodeToString;
        StringBuilder sb = new StringBuilder("Configurations('");
        sb.append(this.snapshotToken);
        sb.append('\'');
        sb.append(", ");
        sb.append('\'');
        sb.append(this.serverToken);
        sb.append('\'');
        sb.append(", ");
        sb.append('(');
        for (Configuration configuration : this.configurationMap.values()) {
            sb.append(configuration);
            sb.append(", ");
        }
        sb.append(')');
        sb.append(", ");
        sb.append(this.isDelta);
        sb.append(", ");
        byte[] bArr = this.experimentToken;
        if (bArr == null) {
            encodeToString = "null";
        } else {
            encodeToString = Base64.encodeToString(bArr, 3);
        }
        sb.append(encodeToString);
        sb.append(", ");
        sb.append(this.configurationVersion);
        sb.append(')');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ConfigurationsCreator.writeToParcel(this, parcel, i);
    }
}
