package com.google.android.libraries.storage.file.openers;

import android.net.Uri;
import com.google.android.libraries.storage.file.Behavior;
import com.google.android.libraries.storage.file.OpenContext;
import com.google.android.libraries.storage.file.Opener;
import com.google.protobuf.MessageLite;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WriteProtoOpener implements Opener {
    private Behavior[] behaviors;
    private final MessageLite proto;

    private WriteProtoOpener(MessageLite messageLite) {
        this.proto = messageLite;
    }

    public static WriteProtoOpener create(MessageLite messageLite) {
        return new WriteProtoOpener(messageLite);
    }

    public WriteProtoOpener withBehaviors(Behavior... behaviorArr) {
        this.behaviors = behaviorArr;
        return this;
    }

    @Override // com.google.android.libraries.storage.file.Opener
    public Void open(OpenContext openContext) {
        Uri scratchUri = ScratchFile.scratchUri(openContext.encodedUri());
        List chainTransformsForWrite = openContext.chainTransformsForWrite(openContext.backend().openForWrite(scratchUri));
        Behavior[] behaviorArr = this.behaviors;
        if (behaviorArr != null) {
            for (Behavior behavior : behaviorArr) {
                behavior.forOutputChain(chainTransformsForWrite);
            }
        }
        try {
            OutputStream outputStream = (OutputStream) chainTransformsForWrite.get(0);
            this.proto.writeTo(outputStream);
            Behavior[] behaviorArr2 = this.behaviors;
            if (behaviorArr2 != null) {
                for (Behavior behavior2 : behaviorArr2) {
                    behavior2.commit();
                }
            }
            if (outputStream != null) {
                outputStream.close();
            }
            openContext.backend().rename(scratchUri, openContext.encodedUri());
            return null;
        } catch (Exception e) {
            try {
                openContext.backend().deleteFile(scratchUri);
            } catch (FileNotFoundException e2) {
            }
            if (e instanceof IOException) {
                throw ((IOException) e);
            }
            throw new IOException(e);
        }
    }
}
