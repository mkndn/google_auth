package com.google.android.gms.fido.u2f.api.messagebased;

import com.google.android.gms.fido.common.api.JsonConvertible;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public interface RequestMessage extends JsonConvertible {
    Integer getRequestId();

    RequestType getRequestType();
}
