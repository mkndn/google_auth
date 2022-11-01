package com.google.protobuf;

import com.google.protobuf.MessageLite;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractMutableMessageLite implements MutableMessageLite {
    private boolean isMutable = true;
    protected int cachedSize = -1;

    @Override // com.google.protobuf.MutableMessageLite
    public MutableMessageLite clone() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isProto1Group() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public MessageLite.Builder newBuilderForType() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UninitializedMessageException newUninitializedMessageException() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public MessageLite.Builder toBuilder() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public byte[] toByteArray() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public ByteString toByteString() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(OutputStream outputStream) {
        throw null;
    }
}
