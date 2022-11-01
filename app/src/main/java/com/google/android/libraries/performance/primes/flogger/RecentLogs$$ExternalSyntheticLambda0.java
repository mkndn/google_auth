package com.google.android.libraries.performance.primes.flogger;

import com.google.android.libraries.performance.primes.flogger.RecentLogs;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class RecentLogs$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ RecentLogs$$ExternalSyntheticLambda0 INSTANCE = new RecentLogs$$ExternalSyntheticLambda0();

    private /* synthetic */ RecentLogs$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return RecentLogs.lambda$getSnapshot$0((RecentLogs.LogAndThread) obj, (RecentLogs.LogAndThread) obj2);
    }
}
