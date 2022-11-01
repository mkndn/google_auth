package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class RegisterRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new RegisterRequestParamsCreator();
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    private final Uri appId;
    private Set appIds;
    private final ChannelIdValue channelIdValue;
    private final String displayHint;
    private final List registerRequests;
    private final List registeredKeys;
    private final Integer requestId;
    private final Double timeoutSeconds;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        Uri appId;
        ChannelIdValue channelIdValue;
        String displayHint;
        List registerRequests;
        List registeredKeys;
        Integer requestId;
        Double timeoutSeconds;

        public RegisterRequestParams build() {
            return new RegisterRequestParams(this.requestId, this.timeoutSeconds, this.appId, this.registerRequests, this.registeredKeys, this.channelIdValue, this.displayHint);
        }

        public Builder setAppId(Uri uri) {
            this.appId = uri;
            return this;
        }

        public Builder setChannelIdValue(ChannelIdValue channelIdValue) {
            this.channelIdValue = channelIdValue;
            return this;
        }

        public Builder setRegisterRequests(List list) {
            this.registerRequests = list;
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
    public RegisterRequestParams(Integer num, Double d, Uri uri, List list, List list2, ChannelIdValue channelIdValue, String str) {
        this.requestId = num;
        this.timeoutSeconds = d;
        this.appId = uri;
        boolean z = true;
        Preconditions.checkArgument((list == null || list.isEmpty()) ? false : true, "empty list of register requests is provided");
        this.registerRequests = list;
        this.registeredKeys = list2;
        this.channelIdValue = channelIdValue;
        this.appIds = validateAppIds(uri, list, list2);
        if (str != null && str.length() > 80) {
            z = false;
        }
        Preconditions.checkArgument(z, "Display Hint cannot be longer than 80 characters");
        this.displayHint = str;
    }

    static Set validateAppIds(Uri uri, List list, List list2) {
        HashSet hashSet = new HashSet();
        if (uri != null) {
            hashSet.add(uri);
        }
        Iterator it = list.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            RegisterRequest registerRequest = (RegisterRequest) it.next();
            Preconditions.checkArgument((uri == null && registerRequest.getAppId() == null) ? true : true, "register request has null appId and no request appId is provided");
            if (registerRequest.getAppId() != null) {
                hashSet.add(Uri.parse(registerRequest.getAppId()));
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            RegisteredKey registeredKey = (RegisteredKey) it2.next();
            Preconditions.checkArgument((uri == null && registeredKey.getAppId() == null) ? false : true, "registered key has null appId and no request appId is provided");
            if (registeredKey.getAppId() != null) {
                hashSet.add(Uri.parse(registeredKey.getAppId()));
            }
        }
        return hashSet;
    }

    public boolean equals(Object obj) {
        List list;
        List list2;
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisterRequestParams) {
            RegisterRequestParams registerRequestParams = (RegisterRequestParams) obj;
            return Objects.equal(this.requestId, registerRequestParams.requestId) && Objects.equal(this.timeoutSeconds, registerRequestParams.timeoutSeconds) && Objects.equal(this.appId, registerRequestParams.appId) && Objects.equal(this.registerRequests, registerRequestParams.registerRequests) && (((list = this.registeredKeys) == null && registerRequestParams.registeredKeys == null) || (list != null && (list2 = registerRequestParams.registeredKeys) != null && list.containsAll(list2) && registerRequestParams.registeredKeys.containsAll(this.registeredKeys))) && Objects.equal(this.channelIdValue, registerRequestParams.channelIdValue) && Objects.equal(this.displayHint, registerRequestParams.displayHint);
        }
        return false;
    }

    public Uri getAppId() {
        return this.appId;
    }

    public ChannelIdValue getChannelIdValue() {
        return this.channelIdValue;
    }

    public String getDisplayHint() {
        return this.displayHint;
    }

    public List getRegisterRequests() {
        return this.registerRequests;
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
        return Objects.hashCode(this.requestId, this.appId, this.timeoutSeconds, this.registerRequests, this.registeredKeys, this.channelIdValue, this.displayHint);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        RegisterRequestParamsCreator.writeToParcel(this, parcel, i);
    }
}
