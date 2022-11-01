package com.google.android.libraries.storage.file.openers;

import com.google.android.libraries.storage.file.Behavior;
import com.google.android.libraries.storage.file.OpenContext;
import com.google.android.libraries.storage.file.Opener;
import java.io.OutputStream;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WriteStreamOpener implements Opener {
    private Behavior[] behaviors;

    private WriteStreamOpener() {
    }

    public static WriteStreamOpener create() {
        return new WriteStreamOpener();
    }

    @Override // com.google.android.libraries.storage.file.Opener
    public OutputStream open(OpenContext openContext) {
        List chainTransformsForWrite = openContext.chainTransformsForWrite(openContext.backend().openForWrite(openContext.encodedUri()));
        Behavior[] behaviorArr = this.behaviors;
        if (behaviorArr != null) {
            for (Behavior behavior : behaviorArr) {
                behavior.forOutputChain(chainTransformsForWrite);
            }
        }
        return (OutputStream) chainTransformsForWrite.get(0);
    }

    public WriteStreamOpener withBehaviors(Behavior... behaviorArr) {
        this.behaviors = behaviorArr;
        return this;
    }
}
