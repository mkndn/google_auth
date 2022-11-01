package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public interface Reader {
    public static final int READ_DONE = Integer.MAX_VALUE;
    public static final int TAG_UNKNOWN = 0;

    int getFieldNumber();

    int getTag();

    boolean readBool();

    void readBoolList(List list);

    ByteString readBytes();

    void readBytesList(List list);

    double readDouble();

    void readDoubleList(List list);

    int readEnum();

    void readEnumList(List list);

    int readFixed32();

    void readFixed32List(List list);

    long readFixed64();

    void readFixed64List(List list);

    float readFloat();

    void readFloatList(List list);

    @Deprecated
    Object readGroup(Class cls, ExtensionRegistryLite extensionRegistryLite);

    @Deprecated
    Object readGroupBySchemaWithCheck(Schema schema, ExtensionRegistryLite extensionRegistryLite);

    @Deprecated
    void readGroupList(List list, Schema schema, ExtensionRegistryLite extensionRegistryLite);

    int readInt32();

    void readInt32List(List list);

    long readInt64();

    void readInt64List(List list);

    void readMap(Map map, MapEntryLite.Metadata metadata, ExtensionRegistryLite extensionRegistryLite);

    Object readMessage(Class cls, ExtensionRegistryLite extensionRegistryLite);

    Object readMessageBySchemaWithCheck(Schema schema, ExtensionRegistryLite extensionRegistryLite);

    void readMessageList(List list, Schema schema, ExtensionRegistryLite extensionRegistryLite);

    int readSFixed32();

    void readSFixed32List(List list);

    long readSFixed64();

    void readSFixed64List(List list);

    int readSInt32();

    void readSInt32List(List list);

    long readSInt64();

    void readSInt64List(List list);

    String readString();

    void readStringList(List list);

    void readStringListRequireUtf8(List list);

    String readStringRequireUtf8();

    int readUInt32();

    void readUInt32List(List list);

    long readUInt64();

    void readUInt64List(List list);

    boolean skipField();
}
