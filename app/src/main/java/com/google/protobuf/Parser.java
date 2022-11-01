package com.google.protobuf;

import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Parser {
    Object parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite);

    Object parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite);

    Object parseFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite);

    Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);
}
