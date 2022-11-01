package com.google.android.libraries.storage.protostore.serializers;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_ProtoSerializer extends ProtoSerializer {
    private final MessageLite defaultValue;
    private final ExtensionRegistryLite extensionRegistryLite;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ProtoSerializer(MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
        if (messageLite == null) {
            throw new NullPointerException("Null defaultValue");
        }
        this.defaultValue = messageLite;
        if (extensionRegistryLite == null) {
            throw new NullPointerException("Null extensionRegistryLite");
        }
        this.extensionRegistryLite = extensionRegistryLite;
    }

    @Override // com.google.android.libraries.storage.protostore.serializers.ProtoSerializer, com.google.android.libraries.storage.protostore.Serializer
    public MessageLite defaultValue() {
        return this.defaultValue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ProtoSerializer) {
            ProtoSerializer protoSerializer = (ProtoSerializer) obj;
            return this.defaultValue.equals(protoSerializer.defaultValue()) && this.extensionRegistryLite.equals(protoSerializer.extensionRegistryLite());
        }
        return false;
    }

    @Override // com.google.android.libraries.storage.protostore.serializers.ProtoSerializer
    public ExtensionRegistryLite extensionRegistryLite() {
        return this.extensionRegistryLite;
    }

    public String toString() {
        String valueOf = String.valueOf(this.defaultValue);
        return "ProtoSerializer{defaultValue=" + valueOf + ", extensionRegistryLite=" + String.valueOf(this.extensionRegistryLite) + "}";
    }

    public int hashCode() {
        return ((this.defaultValue.hashCode() ^ 1000003) * 1000003) ^ this.extensionRegistryLite.hashCode();
    }
}
