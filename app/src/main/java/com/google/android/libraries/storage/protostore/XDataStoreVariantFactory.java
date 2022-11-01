package com.google.android.libraries.storage.protostore;

import com.google.android.libraries.storage.file.SynchronousFileStorage;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class XDataStoreVariantFactory {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract XDataStoreVariant create(ProtoDataStoreConfig protoDataStoreConfig, String str, Executor executor, SynchronousFileStorage synchronousFileStorage, LibraryRestricted libraryRestricted);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String id(LibraryRestricted libraryRestricted);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postCreate(ProtoDataStoreConfig protoDataStoreConfig, XDataStoreVariant xDataStoreVariant, ProtoDataStore protoDataStore, LibraryRestricted libraryRestricted) {
    }
}
