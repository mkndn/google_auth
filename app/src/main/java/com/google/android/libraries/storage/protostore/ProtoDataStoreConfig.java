package com.google.android.libraries.storage.protostore;

import android.net.Uri;
import com.google.android.libraries.storage.protostore.AutoValue_ProtoDataStoreConfig;
import com.google.android.libraries.storage.protostore.handlers.NoOpIOExceptionHandler;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.MessageLite;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ProtoDataStoreConfig {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract ProtoDataStoreConfig build();

        public abstract Builder setEnableTracing(boolean z);

        public abstract Builder setHandler(IOExceptionHandler iOExceptionHandler);

        public abstract Builder setSchema(MessageLite messageLite);

        public abstract Builder setUri(Uri uri);

        public abstract Builder setUseGeneratedExtensionRegistry(boolean z);
    }

    public static Builder builder() {
        return new AutoValue_ProtoDataStoreConfig.Builder().setVariantConfig(SingleProcConfig.getInstance()).setHandler(NoOpIOExceptionHandler.getInstance()).setEnableTracing(false).setUseGeneratedExtensionRegistry(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean enableTracing();

    public abstract IOExceptionHandler handler();

    public abstract ImmutableList migrations();

    public abstract MessageLite schema();

    public abstract Uri uri();

    public abstract boolean useGeneratedExtensionRegistry();

    public abstract VariantConfig variantConfig();
}
