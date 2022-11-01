package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.PrimesThreadsConfigurations;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_PrimesThreadsConfigurations extends PrimesThreadsConfigurations {
    private final boolean enableDeferredTasks;
    private final ListeningScheduledExecutorService primesExecutorService;
    private final int primesMetricExecutorPoolSize;
    private final int primesMetricExecutorPriority;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends PrimesThreadsConfigurations.Builder {
        private boolean enableDeferredTasks;
        private ListeningScheduledExecutorService primesExecutorService;
        private int primesMetricExecutorPoolSize;
        private int primesMetricExecutorPriority;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.PrimesThreadsConfigurations.Builder
        PrimesThreadsConfigurations autoBuild() {
            if (this.set$0 != 7) {
                StringBuilder sb = new StringBuilder();
                if ((this.set$0 & 1) == 0) {
                    sb.append(" primesMetricExecutorPriority");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" primesMetricExecutorPoolSize");
                }
                if ((this.set$0 & 4) == 0) {
                    sb.append(" enableDeferredTasks");
                }
                throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
            }
            return new AutoValue_PrimesThreadsConfigurations(this.primesExecutorService, this.primesMetricExecutorPriority, this.primesMetricExecutorPoolSize, this.enableDeferredTasks);
        }

        @Override // com.google.android.libraries.performance.primes.PrimesThreadsConfigurations.Builder
        public PrimesThreadsConfigurations.Builder setEnableDeferredTasks(boolean z) {
            this.enableDeferredTasks = z;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.PrimesThreadsConfigurations.Builder
        public PrimesThreadsConfigurations.Builder setPrimesMetricExecutorPoolSize(int i) {
            this.primesMetricExecutorPoolSize = i;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        public PrimesThreadsConfigurations.Builder setPrimesMetricExecutorPriority(int i) {
            this.primesMetricExecutorPriority = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_PrimesThreadsConfigurations(ListeningScheduledExecutorService listeningScheduledExecutorService, int i, int i2, boolean z) {
        this.primesExecutorService = listeningScheduledExecutorService;
        this.primesMetricExecutorPriority = i;
        this.primesMetricExecutorPoolSize = i2;
        this.enableDeferredTasks = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PrimesThreadsConfigurations) {
            PrimesThreadsConfigurations primesThreadsConfigurations = (PrimesThreadsConfigurations) obj;
            ListeningScheduledExecutorService listeningScheduledExecutorService = this.primesExecutorService;
            if (listeningScheduledExecutorService != null ? listeningScheduledExecutorService.equals(primesThreadsConfigurations.getPrimesExecutorService()) : primesThreadsConfigurations.getPrimesExecutorService() == null) {
                if (this.primesMetricExecutorPriority == primesThreadsConfigurations.getPrimesMetricExecutorPriority() && this.primesMetricExecutorPoolSize == primesThreadsConfigurations.getPrimesMetricExecutorPoolSize() && this.enableDeferredTasks == primesThreadsConfigurations.getEnableDeferredTasks()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.PrimesThreadsConfigurations
    public boolean getEnableDeferredTasks() {
        return this.enableDeferredTasks;
    }

    @Override // com.google.android.libraries.performance.primes.PrimesThreadsConfigurations
    public ListeningScheduledExecutorService getPrimesExecutorService() {
        return this.primesExecutorService;
    }

    @Override // com.google.android.libraries.performance.primes.PrimesThreadsConfigurations
    public int getPrimesMetricExecutorPoolSize() {
        return this.primesMetricExecutorPoolSize;
    }

    @Override // com.google.android.libraries.performance.primes.PrimesThreadsConfigurations
    public int getPrimesMetricExecutorPriority() {
        return this.primesMetricExecutorPriority;
    }

    public String toString() {
        String valueOf = String.valueOf(this.primesExecutorService);
        int i = this.primesMetricExecutorPriority;
        int i2 = this.primesMetricExecutorPoolSize;
        return "PrimesThreadsConfigurations{primesExecutorService=" + valueOf + ", primesMetricExecutorPriority=" + i + ", primesMetricExecutorPoolSize=" + i2 + ", enableDeferredTasks=" + this.enableDeferredTasks + "}";
    }

    public int hashCode() {
        ListeningScheduledExecutorService listeningScheduledExecutorService = this.primesExecutorService;
        return (((((((listeningScheduledExecutorService == null ? 0 : listeningScheduledExecutorService.hashCode()) ^ 1000003) * 1000003) ^ this.primesMetricExecutorPriority) * 1000003) ^ this.primesMetricExecutorPoolSize) * 1000003) ^ (this.enableDeferredTasks ? 1231 : 1237);
    }
}
