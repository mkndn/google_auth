package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TaskApiCall {
    public static final int NO_METHOD_KEY = 0;
    private final boolean autoResolveMissingFeatures;
    private final Feature[] features;
    private final int methodKey;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private boolean autoResolveMissingFeatures;
        private RemoteCall execute;
        private Feature[] features;
        private int methodKey;

        private Builder() {
            this.autoResolveMissingFeatures = true;
            this.methodKey = 0;
        }

        public TaskApiCall build() {
            Preconditions.checkArgument(this.execute != null, "execute parameter required");
            return new TaskApiCall(this.features, this.autoResolveMissingFeatures, this.methodKey) { // from class: com.google.android.gms.common.api.internal.TaskApiCall.Builder.1
                @Override // com.google.android.gms.common.api.internal.TaskApiCall
                protected void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
                    Builder.this.execute.accept(anyClient, taskCompletionSource);
                }
            };
        }

        public Builder run(RemoteCall remoteCall) {
            this.execute = remoteCall;
            return this;
        }

        public Builder setAutoResolveMissingFeatures(boolean z) {
            this.autoResolveMissingFeatures = z;
            return this;
        }

        public Builder setFeatures(Feature... featureArr) {
            this.features = featureArr;
            return this;
        }

        public Builder setMethodKey(int i) {
            this.methodKey = i;
            return this;
        }
    }

    protected TaskApiCall(Feature[] featureArr, boolean z, int i) {
        this.features = featureArr;
        this.autoResolveMissingFeatures = featureArr != null && z;
        this.methodKey = i;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource);

    public Feature[] getFeatures() {
        return this.features;
    }

    public int getMethodKey() {
        return this.methodKey;
    }

    public boolean shouldAutoResolveMissingFeatures() {
        return this.autoResolveMissingFeatures;
    }
}
