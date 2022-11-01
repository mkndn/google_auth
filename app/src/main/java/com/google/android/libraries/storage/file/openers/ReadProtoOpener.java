package com.google.android.libraries.storage.file.openers;

import com.google.android.libraries.storage.file.OpenContext;
import com.google.android.libraries.storage.file.Opener;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReadProtoOpener implements Opener {
    private final Parser parser;
    private ExtensionRegistryLite registry = ExtensionRegistryLite.getEmptyRegistry();

    private ReadProtoOpener(Parser parser) {
        this.parser = parser;
    }

    public static ReadProtoOpener create(MessageLite messageLite) {
        return new ReadProtoOpener(messageLite.getParserForType());
    }

    @Override // com.google.android.libraries.storage.file.Opener
    public MessageLite open(OpenContext openContext) {
        InputStream open = ReadStreamOpener.create().open(openContext);
        try {
            MessageLite messageLite = (MessageLite) this.parser.parseFrom(open, this.registry);
            if (open != null) {
                open.close();
            }
            return messageLite;
        } catch (Throwable th) {
            if (open != null) {
                try {
                    open.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }
}
