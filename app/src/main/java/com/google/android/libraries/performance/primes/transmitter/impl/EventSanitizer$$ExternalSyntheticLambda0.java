package com.google.android.libraries.performance.primes.transmitter.impl;

import com.google.common.base.Function;
import com.google.devrel.primes.hashing.Hashing;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class EventSanitizer$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ EventSanitizer$$ExternalSyntheticLambda0 INSTANCE = new EventSanitizer$$ExternalSyntheticLambda0();

    private /* synthetic */ EventSanitizer$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return Hashing.hash((String) obj);
    }
}
