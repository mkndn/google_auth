package wireless.android.privacy.annotations.artifact.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidCollectionBasis$CollectionBasisTagMapping extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int COLLECTION_BASIS_HOLDER_FIELD_NUMBER = 5;
    private static final AndroidCollectionBasis$CollectionBasisTagMapping DEFAULT_INSTANCE;
    public static final int MESSAGES_FIELD_NUMBER = 1;
    public static final int MESSAGES_LIST_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int PROTO_HASH_NAMES_FIELD_NUMBER = 4;
    public static final int PROTO_TO_JAVA_HASHES_FIELD_NUMBER = 2;
    private MapFieldLite messages_ = MapFieldLite.emptyMapField();
    private MapFieldLite protoToJavaHashes_ = MapFieldLite.emptyMapField();
    private int protoHashNamesMemoizedSerializedSize = -1;
    private Internal.ProtobufList messagesList_ = emptyProtobufList();
    private Internal.IntList protoHashNames_ = emptyIntList();
    private Internal.ProtobufList collectionBasisHolder_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MessagesDefaultEntryHolder {
        static final MapEntryLite defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.INT32, 0, WireFormat.FieldType.MESSAGE, AndroidCollectionBasis$CollectionBasisMessageInfo.getDefaultInstance());
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ProtoToJavaHashesDefaultEntryHolder {
        static final MapEntryLite defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.INT32, 0, WireFormat.FieldType.INT32, 0);
    }

    static {
        AndroidCollectionBasis$CollectionBasisTagMapping androidCollectionBasis$CollectionBasisTagMapping = new AndroidCollectionBasis$CollectionBasisTagMapping();
        DEFAULT_INSTANCE = androidCollectionBasis$CollectionBasisTagMapping;
        GeneratedMessageLite.registerDefaultInstance(AndroidCollectionBasis$CollectionBasisTagMapping.class, androidCollectionBasis$CollectionBasisTagMapping);
    }

    private AndroidCollectionBasis$CollectionBasisTagMapping() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map getMutableMessagesMap() {
        return internalGetMutableMessages();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map getMutableProtoToJavaHashesMap() {
        return internalGetMutableProtoToJavaHashes();
    }

    private MapFieldLite internalGetMessages() {
        return this.messages_;
    }

    private MapFieldLite internalGetMutableMessages() {
        if (!this.messages_.isMutable()) {
            this.messages_ = this.messages_.mutableCopy();
        }
        return this.messages_;
    }

    private MapFieldLite internalGetMutableProtoToJavaHashes() {
        if (!this.protoToJavaHashes_.isMutable()) {
            this.protoToJavaHashes_ = this.protoToJavaHashes_.mutableCopy();
        }
        return this.protoToJavaHashes_;
    }

    private MapFieldLite internalGetProtoToJavaHashes() {
        return this.protoToJavaHashes_;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AndroidCollectionBasis$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new AndroidCollectionBasis$CollectionBasisTagMapping();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0000\u0001\u0005\u0005\u0002\u0003\u0000\u00012\u00022\u0003\u001b\u0004'\u0005\u001b", new Object[]{"messages_", MessagesDefaultEntryHolder.defaultEntry, "protoToJavaHashes_", ProtoToJavaHashesDefaultEntryHolder.defaultEntry, "messagesList_", AndroidCollectionBasis$CollectionBasisMessageInfo.class, "protoHashNames_", "collectionBasisHolder_", AndroidCollectionBasis$CollectionBasisHolder.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (AndroidCollectionBasis$CollectionBasisTagMapping.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public List getCollectionBasisHolderList() {
        return this.collectionBasisHolder_;
    }

    public List getMessagesListList() {
        return this.messagesList_;
    }

    public List getProtoHashNamesList() {
        return this.protoHashNames_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(AndroidCollectionBasis$CollectionBasisTagMapping.DEFAULT_INSTANCE);
        }

        public Builder putAllMessages(Map map) {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisTagMapping) this.instance).getMutableMessagesMap().putAll(map);
            return this;
        }

        public Builder putAllProtoToJavaHashes(Map map) {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisTagMapping) this.instance).getMutableProtoToJavaHashesMap().putAll(map);
            return this;
        }

        /* synthetic */ Builder(AndroidCollectionBasis$1 androidCollectionBasis$1) {
            this();
        }
    }

    public Map getMessagesMap() {
        return Collections.unmodifiableMap(internalGetMessages());
    }

    public Map getProtoToJavaHashesMap() {
        return Collections.unmodifiableMap(internalGetProtoToJavaHashes());
    }

    public int getProtoToJavaHashesOrThrow(int i) {
        MapFieldLite internalGetProtoToJavaHashes = internalGetProtoToJavaHashes();
        if (!internalGetProtoToJavaHashes.containsKey(Integer.valueOf(i))) {
            throw new IllegalArgumentException();
        }
        return ((Integer) internalGetProtoToJavaHashes.get(Integer.valueOf(i))).intValue();
    }
}
