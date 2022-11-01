package com.google.android.libraries.storage.protostore;

import com.google.android.libraries.storage.file.SynchronousFileStorage;
import com.google.android.libraries.storage.protostore.loggers.NoOpLogger;
import com.google.android.libraries.storage.salt.SaltPersister;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoDataStoreFactoryBuilder {
    private Executor executor;
    private Logger logger = NoOpLogger.INSTANCE;
    private final HashMap pdsFactories = new HashMap();
    private SaltPersister salter;
    private SynchronousFileStorage storage;

    public ProtoDataStoreFactoryBuilder addFactory(XDataStoreVariantFactory xDataStoreVariantFactory) {
        String id = xDataStoreVariantFactory.id(LibraryRestricted.ALLOWED);
        Preconditions.checkArgument(!this.pdsFactories.containsKey(id), "There is already a factory registered for the ID %s", id);
        this.pdsFactories.put(id, xDataStoreVariantFactory);
        return this;
    }

    public ProtoDataStoreFactory build() {
        return new ProtoDataStoreFactory(this.executor, this.storage, this.logger, this.pdsFactories, this.salter);
    }

    public ProtoDataStoreFactoryBuilder setExecutor(Executor executor) {
        this.executor = executor;
        return this;
    }

    public ProtoDataStoreFactoryBuilder setStorage(SynchronousFileStorage synchronousFileStorage) {
        this.storage = synchronousFileStorage;
        return this;
    }
}
