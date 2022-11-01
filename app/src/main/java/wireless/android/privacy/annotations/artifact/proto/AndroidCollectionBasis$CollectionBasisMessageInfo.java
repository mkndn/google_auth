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
public final class AndroidCollectionBasis$CollectionBasisMessageInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int COLLECTION_BASIS_HOLDER_INDICES_FIELD_NUMBER = 8;
    private static final AndroidCollectionBasis$CollectionBasisMessageInfo DEFAULT_INSTANCE;
    public static final int FEATURE_COLLECTION_BASES_FIELD_NUMBER = 2;
    public static final int FIELDS_COLLECTION_BASIS_HOLDER_INDICES_FIELD_NUMBER = 9;
    public static final int FIELDS_FIELD_NUMBER = 3;
    public static final int FIELDS_LIST_FIELD_NUMBER = 6;
    public static final int FIELD_COLLECTION_BASIS_HOLDER_TAG_NUMBERS_FIELD_NUMBER = 10;
    private static volatile Parser PARSER = null;
    public static final int PROTO_NAME_FIELD_NUMBER = 1;
    public static final int TAG_NUMBERS_FIELD_NUMBER = 7;
    private MapFieldLite featureCollectionBases_ = MapFieldLite.emptyMapField();
    private MapFieldLite fields_ = MapFieldLite.emptyMapField();
    private int tagNumbersMemoizedSerializedSize = -1;
    private int collectionBasisHolderIndicesMemoizedSerializedSize = -1;
    private int fieldsCollectionBasisHolderIndicesMemoizedSerializedSize = -1;
    private int fieldCollectionBasisHolderTagNumbersMemoizedSerializedSize = -1;
    private String protoName_ = "";
    private Internal.ProtobufList fieldsList_ = emptyProtobufList();
    private Internal.LongList tagNumbers_ = emptyLongList();
    private Internal.IntList collectionBasisHolderIndices_ = emptyIntList();
    private Internal.IntList fieldsCollectionBasisHolderIndices_ = emptyIntList();
    private Internal.LongList fieldCollectionBasisHolderTagNumbers_ = emptyLongList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FeatureCollectionBasesDefaultEntryHolder {
        static final MapEntryLite defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.INT32, 0, WireFormat.FieldType.MESSAGE, AndroidCollectionBasis$CollectionBasisHolder.getDefaultInstance());
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FieldsDefaultEntryHolder {
        static final MapEntryLite defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.INT64, 0L, WireFormat.FieldType.MESSAGE, AndroidCollectionBasis$CollectionBasisFieldInfo.getDefaultInstance());
    }

    static {
        AndroidCollectionBasis$CollectionBasisMessageInfo androidCollectionBasis$CollectionBasisMessageInfo = new AndroidCollectionBasis$CollectionBasisMessageInfo();
        DEFAULT_INSTANCE = androidCollectionBasis$CollectionBasisMessageInfo;
        GeneratedMessageLite.registerDefaultInstance(AndroidCollectionBasis$CollectionBasisMessageInfo.class, androidCollectionBasis$CollectionBasisMessageInfo);
    }

    private AndroidCollectionBasis$CollectionBasisMessageInfo() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCollectionBasisHolderIndices() {
        this.collectionBasisHolderIndices_ = emptyIntList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFieldCollectionBasisHolderTagNumbers() {
        this.fieldCollectionBasisHolderTagNumbers_ = emptyLongList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFieldsCollectionBasisHolderIndices() {
        this.fieldsCollectionBasisHolderIndices_ = emptyIntList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFieldsList() {
        this.fieldsList_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTagNumbers() {
        this.tagNumbers_ = emptyLongList();
    }

    public static AndroidCollectionBasis$CollectionBasisMessageInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map getMutableFeatureCollectionBasesMap() {
        return internalGetMutableFeatureCollectionBases();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map getMutableFieldsMap() {
        return internalGetMutableFields();
    }

    private MapFieldLite internalGetFeatureCollectionBases() {
        return this.featureCollectionBases_;
    }

    private MapFieldLite internalGetFields() {
        return this.fields_;
    }

    private MapFieldLite internalGetMutableFeatureCollectionBases() {
        if (!this.featureCollectionBases_.isMutable()) {
            this.featureCollectionBases_ = this.featureCollectionBases_.mutableCopy();
        }
        return this.featureCollectionBases_;
    }

    private MapFieldLite internalGetMutableFields() {
        if (!this.fields_.isMutable()) {
            this.fields_ = this.fields_.mutableCopy();
        }
        return this.fields_;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AndroidCollectionBasis$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new AndroidCollectionBasis$CollectionBasisMessageInfo();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0000\u0002\n\u0007\u0002\u0005\u0000\u00022\u00032\u0006\u001b\u0007%\b'\t'\n%", new Object[]{"featureCollectionBases_", FeatureCollectionBasesDefaultEntryHolder.defaultEntry, "fields_", FieldsDefaultEntryHolder.defaultEntry, "fieldsList_", AndroidCollectionBasis$CollectionBasisFieldInfo.class, "tagNumbers_", "collectionBasisHolderIndices_", "fieldsCollectionBasisHolderIndices_", "fieldCollectionBasisHolderTagNumbers_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (AndroidCollectionBasis$CollectionBasisMessageInfo.class) {
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

    public List getFieldCollectionBasisHolderTagNumbersList() {
        return this.fieldCollectionBasisHolderTagNumbers_;
    }

    public List getFieldsCollectionBasisHolderIndicesList() {
        return this.fieldsCollectionBasisHolderIndices_;
    }

    public List getFieldsListList() {
        return this.fieldsList_;
    }

    public List getTagNumbersList() {
        return this.tagNumbers_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(AndroidCollectionBasis$CollectionBasisMessageInfo.DEFAULT_INSTANCE);
        }

        public Builder clearCollectionBasisHolderIndices() {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisMessageInfo) this.instance).clearCollectionBasisHolderIndices();
            return this;
        }

        public Builder clearFieldCollectionBasisHolderTagNumbers() {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisMessageInfo) this.instance).clearFieldCollectionBasisHolderTagNumbers();
            return this;
        }

        public Builder clearFieldsCollectionBasisHolderIndices() {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisMessageInfo) this.instance).clearFieldsCollectionBasisHolderIndices();
            return this;
        }

        public Builder clearFieldsList() {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisMessageInfo) this.instance).clearFieldsList();
            return this;
        }

        public Builder clearTagNumbers() {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisMessageInfo) this.instance).clearTagNumbers();
            return this;
        }

        public Builder putAllFeatureCollectionBases(Map map) {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisMessageInfo) this.instance).getMutableFeatureCollectionBasesMap().putAll(map);
            return this;
        }

        public Builder putAllFields(Map map) {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisMessageInfo) this.instance).getMutableFieldsMap().putAll(map);
            return this;
        }

        /* synthetic */ Builder(AndroidCollectionBasis$1 androidCollectionBasis$1) {
            this();
        }
    }

    public Map getFeatureCollectionBasesMap() {
        return Collections.unmodifiableMap(internalGetFeatureCollectionBases());
    }

    public Map getFieldsMap() {
        return Collections.unmodifiableMap(internalGetFields());
    }

    public AndroidCollectionBasis$CollectionBasisFieldInfo getFieldsOrThrow(long j) {
        MapFieldLite internalGetFields = internalGetFields();
        if (!internalGetFields.containsKey(Long.valueOf(j))) {
            throw new IllegalArgumentException();
        }
        return (AndroidCollectionBasis$CollectionBasisFieldInfo) internalGetFields.get(Long.valueOf(j));
    }
}
