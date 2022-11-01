package com.google.android.libraries.storage.protostore;

import android.net.Uri;
import com.google.android.libraries.storage.file.SynchronousFileStorage;
import com.google.android.libraries.storage.protostore.common.Uris;
import com.google.android.libraries.storage.salt.SaltPersister;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoDataStoreFactory {
    private final Executor executor;
    private final Logger logger;
    private final AsyncFunction obfuscator;
    private final Map pdsCache = new HashMap();
    private final Map pdsConfigs = new HashMap();
    private final SynchronousFileStorage storage;
    private final Map variantFactories;

    public ProtoDataStoreFactory(Executor executor, SynchronousFileStorage synchronousFileStorage, Logger logger, Map map, SaltPersister saltPersister) {
        this.executor = (Executor) Preconditions.checkNotNull(executor);
        this.storage = (SynchronousFileStorage) Preconditions.checkNotNull(synchronousFileStorage);
        this.logger = (Logger) Preconditions.checkNotNull(logger);
        Map map2 = (Map) Preconditions.checkNotNull(map);
        this.variantFactories = map2;
        Preconditions.checkArgument(!map2.isEmpty());
        if (saltPersister != null) {
            final UriObfuscator create = UriObfuscator.create(saltPersister);
            this.obfuscator = new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.ProtoDataStoreFactory$$ExternalSyntheticLambda0
                @Override // com.google.common.util.concurrent.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    return ProtoDataStoreFactory.lambda$new$0(UriObfuscator.this, (Uri) obj);
                }
            };
            return;
        }
        this.obfuscator = ProtoDataStoreFactory$$ExternalSyntheticLambda1.INSTANCE;
    }

    private static void checkEquals(ProtoDataStoreConfig protoDataStoreConfig, ProtoDataStoreConfig protoDataStoreConfig2) {
        if (protoDataStoreConfig.equals(protoDataStoreConfig2)) {
            return;
        }
        String lenientFormat = Strings.lenientFormat("ProtoDataStoreConfig<%s> doesn't match previous call [uri=%s] [%s]", protoDataStoreConfig.schema().getClass().getSimpleName(), protoDataStoreConfig.uri());
        Preconditions.checkArgument(protoDataStoreConfig.uri().equals(protoDataStoreConfig2.uri()), lenientFormat, "uri");
        Preconditions.checkArgument(protoDataStoreConfig.schema().equals(protoDataStoreConfig2.schema()), lenientFormat, "schema");
        Preconditions.checkArgument(protoDataStoreConfig.handler().equals(protoDataStoreConfig2.handler()), lenientFormat, "handler");
        Preconditions.checkArgument(protoDataStoreConfig.migrations().equals(protoDataStoreConfig2.migrations()), lenientFormat, "migrations");
        Preconditions.checkArgument(protoDataStoreConfig.variantConfig().equals(protoDataStoreConfig2.variantConfig()), lenientFormat, "variantConfig");
        Preconditions.checkArgument(protoDataStoreConfig.useGeneratedExtensionRegistry() == protoDataStoreConfig2.useGeneratedExtensionRegistry(), lenientFormat, "useGeneratedExtensionRegistry");
        Preconditions.checkArgument(protoDataStoreConfig.enableTracing() == protoDataStoreConfig2.enableTracing(), lenientFormat, "enableTracing");
        throw new IllegalArgumentException(Strings.lenientFormat(lenientFormat, "unknown"));
    }

    private ProtoDataStore createInternal(ProtoDataStoreConfig protoDataStoreConfig) {
        validateConfig(protoDataStoreConfig);
        XDataStoreVariantFactory factory = getFactory(protoDataStoreConfig);
        String tracingId = getTracingId(protoDataStoreConfig);
        ListenableFuture loggingId = getLoggingId(protoDataStoreConfig);
        XDataStoreVariant create = factory.create(protoDataStoreConfig, tracingId, this.executor, this.storage, LibraryRestricted.ALLOWED);
        ProtoDataStore protoDataStore = new ProtoDataStore(create, this.logger, loggingId, protoDataStoreConfig.enableTracing());
        factory.postCreate(protoDataStoreConfig, create, protoDataStore, LibraryRestricted.ALLOWED);
        ImmutableList migrations = protoDataStoreConfig.migrations();
        if (!migrations.isEmpty()) {
            protoDataStore.addInitializer(ProtoDataMigrationInitializer.create(migrations, this.executor));
        }
        return protoDataStore;
    }

    private XDataStoreVariantFactory getFactory(ProtoDataStoreConfig protoDataStoreConfig) {
        String factoryId = protoDataStoreConfig.variantConfig().factoryId();
        XDataStoreVariantFactory xDataStoreVariantFactory = (XDataStoreVariantFactory) this.variantFactories.get(factoryId);
        Preconditions.checkArgument(xDataStoreVariantFactory != null, "No XDataStoreVariantFactory registered for ID %s", factoryId);
        return xDataStoreVariantFactory;
    }

    private ListenableFuture getLoggingId(ProtoDataStoreConfig protoDataStoreConfig) {
        return Futures.transformAsync(Futures.immediateFuture(protoDataStoreConfig.uri()), this.obfuscator, MoreExecutors.directExecutor());
    }

    private synchronized ProtoDataStore getOrCreateInternal(ProtoDataStoreConfig protoDataStoreConfig) {
        ProtoDataStore protoDataStore;
        Uri uri = protoDataStoreConfig.uri();
        protoDataStore = (ProtoDataStore) this.pdsCache.get(uri);
        if (protoDataStore == null) {
            protoDataStore = createInternal(protoDataStoreConfig);
            this.pdsCache.put(uri, protoDataStore);
            this.pdsConfigs.put(uri, protoDataStoreConfig);
        } else {
            checkEquals(protoDataStoreConfig, (ProtoDataStoreConfig) this.pdsConfigs.get(uri));
        }
        return protoDataStore;
    }

    private static String getTracingId(ProtoDataStoreConfig protoDataStoreConfig) {
        return Uris.getNameWithoutExtension(protoDataStoreConfig.uri());
    }

    public static /* synthetic */ ListenableFuture lambda$new$0(UriObfuscator uriObfuscator, Uri uri) {
        return uriObfuscator.obfuscate(uri);
    }

    private static void validateConfig(ProtoDataStoreConfig protoDataStoreConfig) {
        Uri uri = protoDataStoreConfig.uri();
        Preconditions.checkArgument(uri.isHierarchical(), "Uri must be hierarchical: %s", uri);
        Preconditions.checkArgument(Uris.getExtension(uri).equals("pb"), "Uri extension must be .pb: %s", uri);
        Preconditions.checkArgument(protoDataStoreConfig.schema() != null, "Proto schema cannot be null");
        Preconditions.checkArgument(protoDataStoreConfig.handler() != null, "Handler cannot be null");
    }

    public ProtoDataStore getOrCreate(ProtoDataStoreConfig protoDataStoreConfig) {
        return getOrCreateInternal(protoDataStoreConfig);
    }
}
