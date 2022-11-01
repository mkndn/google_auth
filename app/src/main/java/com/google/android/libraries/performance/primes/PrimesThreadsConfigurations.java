package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.AutoValue_PrimesThreadsConfigurations;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesThreadsConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        abstract PrimesThreadsConfigurations autoBuild();

        public PrimesThreadsConfigurations build() {
            PrimesThreadsConfigurations autoBuild = autoBuild();
            Preconditions.checkState(autoBuild.getPrimesMetricExecutorPoolSize() > 0 && autoBuild.getPrimesMetricExecutorPoolSize() <= 2, "Thread pool size must be less than or equal to %s", 2);
            return autoBuild;
        }

        public abstract Builder setEnableDeferredTasks(boolean z);

        public abstract Builder setPrimesMetricExecutorPoolSize(int i);
    }

    public static Builder newBuilder() {
        return new AutoValue_PrimesThreadsConfigurations.Builder().setPrimesMetricExecutorPriority(11).setPrimesMetricExecutorPoolSize(2).setEnableDeferredTasks(true);
    }

    public abstract boolean getEnableDeferredTasks();

    public abstract ListeningScheduledExecutorService getPrimesExecutorService();

    public abstract int getPrimesMetricExecutorPoolSize();

    public abstract int getPrimesMetricExecutorPriority();
}
