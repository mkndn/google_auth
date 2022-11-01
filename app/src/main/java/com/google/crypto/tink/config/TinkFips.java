package com.google.crypto.tink.config;

import com.google.crypto.tink.config.internal.TinkFipsUtil;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TinkFips {
    public static boolean useOnlyFips() {
        return TinkFipsUtil.useOnlyFips();
    }
}
