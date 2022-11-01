package com.google.android.libraries.storage.protostore;

import android.net.Uri;
import com.google.android.libraries.storage.protostore.ProtoDataStoreConfig;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.MessageLite;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_ProtoDataStoreConfig extends ProtoDataStoreConfig {
    private final boolean enableTracing;
    private final IOExceptionHandler handler;
    private final ImmutableList migrations;
    private final MessageLite schema;
    private final Uri uri;
    private final boolean useGeneratedExtensionRegistry;
    private final VariantConfig variantConfig;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends ProtoDataStoreConfig.Builder {
        private boolean enableTracing;
        private IOExceptionHandler handler;
        private ImmutableList migrations;
        private ImmutableList.Builder migrationsBuilder$;
        private MessageLite schema;
        private byte set$0;
        private Uri uri;
        private boolean useGeneratedExtensionRegistry;
        private VariantConfig variantConfig;

        @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig.Builder
        public ProtoDataStoreConfig build() {
            ImmutableList.Builder builder = this.migrationsBuilder$;
            if (builder != null) {
                this.migrations = builder.build();
            } else if (this.migrations == null) {
                this.migrations = ImmutableList.of();
            }
            if (this.set$0 == 3 && this.uri != null && this.schema != null && this.handler != null && this.variantConfig != null) {
                return new AutoValue_ProtoDataStoreConfig(this.uri, this.schema, this.handler, this.migrations, this.variantConfig, this.useGeneratedExtensionRegistry, this.enableTracing);
            }
            StringBuilder sb = new StringBuilder();
            if (this.uri == null) {
                sb.append(" uri");
            }
            if (this.schema == null) {
                sb.append(" schema");
            }
            if (this.handler == null) {
                sb.append(" handler");
            }
            if (this.variantConfig == null) {
                sb.append(" variantConfig");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" useGeneratedExtensionRegistry");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" enableTracing");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig.Builder
        public ProtoDataStoreConfig.Builder setEnableTracing(boolean z) {
            this.enableTracing = z;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig.Builder
        public ProtoDataStoreConfig.Builder setHandler(IOExceptionHandler iOExceptionHandler) {
            if (iOExceptionHandler == null) {
                throw new NullPointerException("Null handler");
            }
            this.handler = iOExceptionHandler;
            return this;
        }

        @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig.Builder
        public ProtoDataStoreConfig.Builder setSchema(MessageLite messageLite) {
            if (messageLite == null) {
                throw new NullPointerException("Null schema");
            }
            this.schema = messageLite;
            return this;
        }

        @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig.Builder
        public ProtoDataStoreConfig.Builder setUri(Uri uri) {
            if (uri == null) {
                throw new NullPointerException("Null uri");
            }
            this.uri = uri;
            return this;
        }

        @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig.Builder
        public ProtoDataStoreConfig.Builder setUseGeneratedExtensionRegistry(boolean z) {
            this.useGeneratedExtensionRegistry = z;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public ProtoDataStoreConfig.Builder setVariantConfig(VariantConfig variantConfig) {
            if (variantConfig == null) {
                throw new NullPointerException("Null variantConfig");
            }
            this.variantConfig = variantConfig;
            return this;
        }
    }

    private AutoValue_ProtoDataStoreConfig(Uri uri, MessageLite messageLite, IOExceptionHandler iOExceptionHandler, ImmutableList immutableList, VariantConfig variantConfig, boolean z, boolean z2) {
        this.uri = uri;
        this.schema = messageLite;
        this.handler = iOExceptionHandler;
        this.migrations = immutableList;
        this.variantConfig = variantConfig;
        this.useGeneratedExtensionRegistry = z;
        this.enableTracing = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig
    public boolean enableTracing() {
        return this.enableTracing;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ProtoDataStoreConfig) {
            ProtoDataStoreConfig protoDataStoreConfig = (ProtoDataStoreConfig) obj;
            return this.uri.equals(protoDataStoreConfig.uri()) && this.schema.equals(protoDataStoreConfig.schema()) && this.handler.equals(protoDataStoreConfig.handler()) && this.migrations.equals(protoDataStoreConfig.migrations()) && this.variantConfig.equals(protoDataStoreConfig.variantConfig()) && this.useGeneratedExtensionRegistry == protoDataStoreConfig.useGeneratedExtensionRegistry() && this.enableTracing == protoDataStoreConfig.enableTracing();
        }
        return false;
    }

    @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig
    public IOExceptionHandler handler() {
        return this.handler;
    }

    @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig
    public ImmutableList migrations() {
        return this.migrations;
    }

    @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig
    public MessageLite schema() {
        return this.schema;
    }

    public String toString() {
        String valueOf = String.valueOf(this.uri);
        String valueOf2 = String.valueOf(this.schema);
        String valueOf3 = String.valueOf(this.handler);
        String valueOf4 = String.valueOf(this.migrations);
        String valueOf5 = String.valueOf(this.variantConfig);
        boolean z = this.useGeneratedExtensionRegistry;
        return "ProtoDataStoreConfig{uri=" + valueOf + ", schema=" + valueOf2 + ", handler=" + valueOf3 + ", migrations=" + valueOf4 + ", variantConfig=" + valueOf5 + ", useGeneratedExtensionRegistry=" + z + ", enableTracing=" + this.enableTracing + "}";
    }

    @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig
    public Uri uri() {
        return this.uri;
    }

    @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig
    public boolean useGeneratedExtensionRegistry() {
        return this.useGeneratedExtensionRegistry;
    }

    @Override // com.google.android.libraries.storage.protostore.ProtoDataStoreConfig
    public VariantConfig variantConfig() {
        return this.variantConfig;
    }

    public int hashCode() {
        return ((((((((((((this.uri.hashCode() ^ 1000003) * 1000003) ^ this.schema.hashCode()) * 1000003) ^ this.handler.hashCode()) * 1000003) ^ this.migrations.hashCode()) * 1000003) ^ this.variantConfig.hashCode()) * 1000003) ^ (this.useGeneratedExtensionRegistry ? 1231 : 1237)) * 1000003) ^ (this.enableTracing ? 1231 : 1237);
    }
}
