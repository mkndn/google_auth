package com.google.android.libraries.storage.protostore.serializers;

import com.google.android.libraries.storage.protostore.Serializer;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ProtoSerializer implements Serializer {
    public static ProtoSerializer create(MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
        return new AutoValue_ProtoSerializer(messageLite, extensionRegistryLite);
    }

    @Override // com.google.android.libraries.storage.protostore.Serializer
    public abstract MessageLite defaultValue();

    @Override // com.google.android.libraries.storage.protostore.Serializer
    public /* bridge */ /* synthetic */ Object defaultValue() {
        throw null;
    }

    public abstract ExtensionRegistryLite extensionRegistryLite();

    @Override // com.google.android.libraries.storage.protostore.Serializer
    public MessageLite readFrom(InputStream inputStream) {
        return (MessageLite) defaultValue().getParserForType().parseFrom(inputStream, extensionRegistryLite());
    }

    @Override // com.google.android.libraries.storage.protostore.Serializer
    public void writeTo(MessageLite messageLite, OutputStream outputStream) {
        messageLite.writeTo(outputStream);
    }
}
