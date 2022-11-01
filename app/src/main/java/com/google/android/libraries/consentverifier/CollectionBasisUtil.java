package com.google.android.libraries.consentverifier;

import android.content.Context;
import com.google.android.gms.common.internal.BuildConstants;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisUtil {
    public static boolean isRunningInGmsCore(Context context) {
        return BuildConstants.IS_PACKAGE_SIDE || "com.google.android.gms".equals(context.getPackageName());
    }
}
