package com.google.android.gms.fido.u2f.api.common;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.fido.common.api.JsonConvertible;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public abstract class ResponseData extends AbstractSafeParcelable implements ReflectedParcelable, JsonConvertible {
    public abstract JSONObject toJsonObject();
}
