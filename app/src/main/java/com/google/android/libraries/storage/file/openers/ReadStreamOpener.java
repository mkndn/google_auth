package com.google.android.libraries.storage.file.openers;

import com.google.android.libraries.storage.file.Behavior;
import com.google.android.libraries.storage.file.OpenContext;
import com.google.android.libraries.storage.file.Opener;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReadStreamOpener implements Opener {
    private Behavior[] behaviors;
    private boolean bufferIo = false;

    private ReadStreamOpener() {
    }

    public static ReadStreamOpener create() {
        return new ReadStreamOpener();
    }

    @Override // com.google.android.libraries.storage.file.Opener
    public InputStream open(OpenContext openContext) {
        InputStream openForRead = openContext.backend().openForRead(openContext.encodedUri());
        if (this.bufferIo) {
            openForRead = new BufferedInputStream(openForRead);
        }
        List chainTransformsForRead = openContext.chainTransformsForRead(openForRead);
        Behavior[] behaviorArr = this.behaviors;
        if (behaviorArr != null) {
            for (Behavior behavior : behaviorArr) {
                behavior.forInputChain(chainTransformsForRead);
            }
        }
        return (InputStream) chainTransformsForRead.get(0);
    }
}
