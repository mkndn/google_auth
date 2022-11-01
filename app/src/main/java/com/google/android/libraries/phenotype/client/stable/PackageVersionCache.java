package com.google.android.libraries.phenotype.client.stable;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PackageVersionCache {
    private final AtomicInteger version = new AtomicInteger();

    public int get() {
        return this.version.get();
    }

    public void increment() {
        this.version.incrementAndGet();
    }
}
