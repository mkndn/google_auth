package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class SignRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new SignRequestParamsCreator();
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    private final Uri appId;
    private final Set appIds;
    private final ChannelIdValue channelIdValue;
    private final byte[] defaultSignChallenge;
    private final String displayHint;
    private final List registeredKeys;
    private final Integer requestId;
    private final Double timeoutSeconds;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        Uri appId;
        ChannelIdValue channelIdValue;
        byte[] defaultSignChallenge;
        String displayHint;
        List registeredKeys;
        Integer requestId;
        Double timeoutSeconds;

        public SignRequestParams build() {
            return new SignRequestParams(this.requestId, this.timeoutSeconds, this.appId, this.defaultSignChallenge, this.registeredKeys, this.channelIdValue, this.displayHint);
        }

        public Builder setAppId(Uri uri) {
            this.appId = uri;
            return this;
        }

        public Builder setChannelIdValue(ChannelIdValue channelIdValue) {
            this.channelIdValue = channelIdValue;
            return this;
        }

        public Builder setDefaultSignChallenge(byte[] bArr) {
            this.defaultSignChallenge = bArr;
            return this;
        }

        public Builder setRegisteredKeys(List list) {
            this.registeredKeys = list;
            return this;
        }

        public Builder setRequestId(Integer num) {
            this.requestId = num;
            return this;
        }

        public Builder setTimeoutSeconds(Double d) {
            this.timeoutSeconds = d;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignRequestParams(Integer num, Double d, Uri uri, byte[] bArr, List list, ChannelIdValue channelIdValue, String str) {
        this.requestId = num;
        this.timeoutSeconds = d;
        this.appId = uri;
        this.defaultSignChallenge = bArr;
        boolean z = true;
        Preconditions.checkArgument((list == null || list.isEmpty()) ? false : true, "registeredKeys must not be null or empty");
        this.registeredKeys = list;
        this.channelIdValue = channelIdValue;
        this.appIds = validateAppIds(uri, list);
        if (str != null && str.length() > 80) {
            z = false;
        }
        Preconditions.checkArgument(z, "Display Hint cannot be longer than 80 characters");
        this.displayHint = str;
    }

    static Set validateAppIds(Uri uri, List list) {
        HashSet hashSet = new HashSet();
        if (uri != null) {
            hashSet.add(uri);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RegisteredKey registeredKey = (RegisteredKey) it.next();
            boolean z = false;
            Preconditions.checkArgument((registeredKey.getAppId() == null && uri == null) ? false : true, "registered key has null appId and no request appId is provided");
            Preconditions.checkArgument((registeredKey.getChallengeValue() == null && list == null) ? true : true, "register request has null challenge and no default challenge isprovided");
            if (registeredKey.getAppId() != null) {
                hashSet.add(Uri.parse(registeredKey.getAppId()));
            }
        }
        return hashSet;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SignRequestParams) {
            SignRequestParams signRequestParams = (SignRequestParams) obj;
            return Objects.equal(this.requestId, signRequestParams.requestId) && Objects.equal(this.timeoutSeconds, signRequestParams.timeoutSeconds) && Objects.equal(this.appId, signRequestParams.appId) && Arrays.equals(this.defaultSignChallenge, signRequestParams.defaultSignChallenge) && this.registeredKeys.containsAll(signRequestParams.registeredKeys) && signRequestParams.registeredKeys.containsAll(this.registeredKeys) && Objects.equal(this.channelIdValue, signRequestParams.channelIdValue) && Objects.equal(this.displayHint, signRequestParams.displayHint);
        }
        return false;
    }

    public Uri getAppId() {
        return this.appId;
    }

    public ChannelIdValue getChannelIdValue() {
        return this.channelIdValue;
    }

    public byte[] getDefaultSignChallenge() {
        return this.defaultSignChallenge;
    }

    public String getDisplayHint() {
        return this.displayHint;
    }

    public List getRegisteredKeys() {
        return this.registeredKeys;
    }

    public Integer getRequestId() {
        return this.requestId;
    }

    public Double getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    public int hashCode() {
        return Objects.hashCode(this.requestId, this.appId, this.timeoutSeconds, this.registeredKeys, this.channelIdValue, this.displayHint, Integer.valueOf(Arrays.hashCode(this.defaultSignChallenge)));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        SignRequestParamsCreator.writeToParcel(this, parcel, i);
    }
}
