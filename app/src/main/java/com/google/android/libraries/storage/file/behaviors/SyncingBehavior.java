package com.google.android.libraries.storage.file.behaviors;

import com.google.android.libraries.storage.file.Behavior;
import com.google.android.libraries.storage.file.common.Syncable;
import com.google.android.libraries.storage.file.common.UnsupportedFileStorageOperation;
import com.google.common.collect.Iterables;
import java.io.OutputStream;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SyncingBehavior implements Behavior {
    private OutputStream headStream;
    private Syncable syncable;

    @Override // com.google.android.libraries.storage.file.Behavior
    public void commit() {
        sync();
    }

    @Override // com.google.android.libraries.storage.file.Behavior
    public /* synthetic */ void forInputChain(List list) {
        Behavior.CC.$default$forInputChain(this, list);
    }

    @Override // com.google.android.libraries.storage.file.Behavior
    public void forOutputChain(List list) {
        OutputStream outputStream = (OutputStream) Iterables.getLast(list);
        if (outputStream instanceof Syncable) {
            this.syncable = (Syncable) outputStream;
            this.headStream = (OutputStream) list.get(0);
        }
    }

    public void sync() {
        if (this.syncable == null) {
            throw new UnsupportedFileStorageOperation("Cannot sync underlying stream");
        }
        this.headStream.flush();
        this.syncable.sync();
    }
}
