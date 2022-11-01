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
public final class AndroidCollectionBasis$CollectionBasisFieldInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int COLLECTION_BASIS_HOLDER_INDICES_FIELD_NUMBER = 5;
    private static final AndroidCollectionBasis$CollectionBasisFieldInfo DEFAULT_INSTANCE;
    public static final int FEATURE_COLLECTION_BASES_FIELD_NUMBER = 3;
    public static final int FEATURE_COLLECTION_BASES_LIST_FIELD_NUMBER = 4;
    public static final int ID_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int PROTO_CONTENT_FIELD_NUMBER = 6;
    public static final int PROTO_CONTENT_NAMES_FIELD_NUMBER = 7;
    public static final int PROTO_NAME_FIELD_NUMBER = 1;
    private int bitField0_;
    private int id_;
    private MapFieldLite featureCollectionBases_ = MapFieldLite.emptyMapField();
    private int collectionBasisHolderIndicesMemoizedSerializedSize = -1;
    private int protoContentMemoizedSerializedSize = -1;
    private String protoName_ = "";
    private Internal.ProtobufList featureCollectionBasesList_ = emptyProtobufList();
    private Internal.IntList collectionBasisHolderIndices_ = emptyIntList();
    private Internal.IntList protoContent_ = emptyIntList();
    private Internal.ProtobufList protoContentNames_ = GeneratedMessageLite.emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FeatureCollectionBasesDefaultEntryHolder {
        static final MapEntryLite defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.INT32, 0, WireFormat.FieldType.MESSAGE, AndroidCollectionBasis$CollectionBasisHolder.getDefaultInstance());
    }

    static {
        AndroidCollectionBasis$CollectionBasisFieldInfo androidCollectionBasis$CollectionBasisFieldInfo = new AndroidCollectionBasis$CollectionBasisFieldInfo();
        DEFAULT_INSTANCE = androidCollectionBasis$CollectionBasisFieldInfo;
        GeneratedMessageLite.registerDefaultInstance(AndroidCollectionBasis$CollectionBasisFieldInfo.class, androidCollectionBasis$CollectionBasisFieldInfo);
    }

    private AndroidCollectionBasis$CollectionBasisFieldInfo() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCollectionBasisHolderIndices() {
        this.collectionBasisHolderIndices_ = emptyIntList();
    }

    public static AndroidCollectionBasis$CollectionBasisFieldInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map getMutableFeatureCollectionBasesMap() {
        return internalGetMutableFeatureCollectionBases();
    }

    private MapFieldLite internalGetFeatureCollectionBases() {
        return this.featureCollectionBases_;
    }

    private MapFieldLite internalGetMutableFeatureCollectionBases() {
        if (!this.featureCollectionBases_.isMutable()) {
            this.featureCollectionBases_ = this.featureCollectionBases_.mutableCopy();
        }
        return this.featureCollectionBases_;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AndroidCollectionBasis$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new AndroidCollectionBasis$CollectionBasisFieldInfo();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0002\u0005\u0003\u0001\u0001\u0000\u0002င\u0001\u00032\u0005'", new Object[]{"bitField0_", "id_", "featureCollectionBases_", FeatureCollectionBasesDefaultEntryHolder.defaultEntry, "collectionBasisHolderIndices_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (AndroidCollectionBasis$CollectionBasisFieldInfo.class) {
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

    public List getCollectionBasisHolderIndicesList() {
        return this.collectionBasisHolderIndices_;
    }

    public int getId() {
        return this.id_;
    }

    public boolean hasId() {
        return (this.bitField0_ & 2) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(AndroidCollectionBasis$CollectionBasisFieldInfo.DEFAULT_INSTANCE);
        }

        public Builder clearCollectionBasisHolderIndices() {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisFieldInfo) this.instance).clearCollectionBasisHolderIndices();
            return this;
        }

        public Builder putAllFeatureCollectionBases(Map map) {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisFieldInfo) this.instance).getMutableFeatureCollectionBasesMap().putAll(map);
            return this;
        }

        /* synthetic */ Builder(AndroidCollectionBasis$1 androidCollectionBasis$1) {
            this();
        }
    }

    public Map getFeatureCollectionBasesMap() {
        return Collections.unmodifiableMap(internalGetFeatureCollectionBases());
    }
}
