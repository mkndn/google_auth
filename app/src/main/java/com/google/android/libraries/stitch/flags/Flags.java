package com.google.android.libraries.stitch.flags;

import com.google.android.libraries.stitch.util.SystemProperties;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Flags {
    public static boolean get(DefaultFalseFlag defaultFalseFlag) {
        return "true".equals(SystemProperties.getString(defaultFalseFlag.getName(), defaultFalseFlag.getDefaultValue() ? "true" : "false"));
    }
}
