package com.google.android.gms.common.util;

import android.os.StrictMode;

/* compiled from: PG */
/* loaded from: classes.dex */
public class StrictModeUtils {
    public static StrictMode.VmPolicy setPermitUnsafeIntentLaunchPolicy() {
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        if (PlatformVersion.isAtLeastS()) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(vmPolicy).permitUnsafeIntentLaunch().build());
        }
        return vmPolicy;
    }
}
