package com.android.apksig.internal.apk;

import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ApkSigningBlockUtilsLite$$ExternalSyntheticLambda1 implements Comparator {
    public static final /* synthetic */ ApkSigningBlockUtilsLite$$ExternalSyntheticLambda1 INSTANCE = new ApkSigningBlockUtilsLite$$ExternalSyntheticLambda1();

    private /* synthetic */ ApkSigningBlockUtilsLite$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int m;
        m = ApkSigningBlockUtilsLite$$ExternalSyntheticBackport0.m(((ApkSupportedSignature) obj).algorithm.getId(), ((ApkSupportedSignature) obj2).algorithm.getId());
        return m;
    }
}
