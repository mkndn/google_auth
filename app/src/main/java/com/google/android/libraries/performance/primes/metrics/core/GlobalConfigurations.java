package com.google.android.libraries.performance.primes.metrics.core;

import com.google.common.base.Supplier;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GlobalConfigurations {
    GlobalConfigurations() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Supplier getComponentNameSupplier();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Provider getExtensionProvider();
}
