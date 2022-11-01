package com.google.protobuf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class UnknownFieldSchema {
    abstract void addFixed32(Object obj, int i, int i2);

    abstract void addFixed64(Object obj, int i, long j);

    abstract void addGroup(Object obj, int i, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void addLengthDelimited(Object obj, int i, ByteString byteString);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void addVarint(Object obj, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object getBuilderFromMessage(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object getFromMessage(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getSerializedSize(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getSerializedSizeAsMessageSet(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void makeImmutable(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object merge(Object obj, Object obj2);

    final void mergeFrom(Object obj, Reader reader) {
        while (reader.getFieldNumber() != Integer.MAX_VALUE && mergeOneFieldFrom(obj, reader)) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean mergeOneFieldFrom(Object obj, Reader reader) {
        int tag = reader.getTag();
        int tagFieldNumber = WireFormat.getTagFieldNumber(tag);
        switch (WireFormat.getTagWireType(tag)) {
            case 0:
                addVarint(obj, tagFieldNumber, reader.readInt64());
                return true;
            case 1:
                addFixed64(obj, tagFieldNumber, reader.readFixed64());
                return true;
            case 2:
                addLengthDelimited(obj, tagFieldNumber, reader.readBytes());
                return true;
            case 3:
                Object newBuilder = newBuilder();
                int makeTag = WireFormat.makeTag(tagFieldNumber, 4);
                mergeFrom(newBuilder, reader);
                if (makeTag != reader.getTag()) {
                    throw InvalidProtocolBufferException.invalidEndTag();
                }
                addGroup(obj, tagFieldNumber, toImmutable(newBuilder));
                return true;
            case 4:
                return false;
            case 5:
                addFixed32(obj, tagFieldNumber, reader.readFixed32());
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object newBuilder();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setBuilderToMessage(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setToMessage(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean shouldDiscardUnknownFields(Reader reader);

    abstract Object toImmutable(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void writeAsMessageSetTo(Object obj, Writer writer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void writeTo(Object obj, Writer writer);
}
