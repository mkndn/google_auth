package wireless.android.privacy.annotations.artifact.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidCollectionBasis$CollectionBasisHolder extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int COLLECTION_BASES_FIELD_NUMBER = 2;
    private static final AndroidCollectionBasis$CollectionBasisHolder DEFAULT_INSTANCE;
    public static final int FEATURE_NAME_FIELD_NUMBER = 1;
    public static final int FEATURE_NAME_HASH_FIELD_NUMBER = 3;
    private static volatile Parser PARSER;
    private static final Internal.ListAdapter.Converter collectionBases_converter_ = new Internal.ListAdapter.Converter() { // from class: wireless.android.privacy.annotations.artifact.proto.AndroidCollectionBasis$CollectionBasisHolder.1
        @Override // com.google.protobuf.Internal.ListAdapter.Converter
        public AndroidPrivacyAnnotationsEnums$CollectionUseCase convert(Integer num) {
            AndroidPrivacyAnnotationsEnums$CollectionUseCase forNumber = AndroidPrivacyAnnotationsEnums$CollectionUseCase.forNumber(num.intValue());
            return forNumber == null ? AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DEFAULT : forNumber;
        }
    };
    private int bitField0_;
    private int featureNameHash_;
    private String featureName_ = "";
    private Internal.IntList collectionBases_ = emptyIntList();

    static {
        AndroidCollectionBasis$CollectionBasisHolder androidCollectionBasis$CollectionBasisHolder = new AndroidCollectionBasis$CollectionBasisHolder();
        DEFAULT_INSTANCE = androidCollectionBasis$CollectionBasisHolder;
        GeneratedMessageLite.registerDefaultInstance(AndroidCollectionBasis$CollectionBasisHolder.class, androidCollectionBasis$CollectionBasisHolder);
    }

    private AndroidCollectionBasis$CollectionBasisHolder() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFeatureNameHash() {
        this.bitField0_ &= -3;
        this.featureNameHash_ = 0;
    }

    public static AndroidCollectionBasis$CollectionBasisHolder getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AndroidCollectionBasis$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new AndroidCollectionBasis$CollectionBasisHolder();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0002\u0003\u0002\u0000\u0001\u0000\u0002,\u0003င\u0001", new Object[]{"bitField0_", "collectionBases_", AndroidPrivacyAnnotationsEnums$CollectionUseCase.internalGetVerifier(), "featureNameHash_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (AndroidCollectionBasis$CollectionBasisHolder.class) {
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

    public AndroidPrivacyAnnotationsEnums$CollectionUseCase getCollectionBases(int i) {
        AndroidPrivacyAnnotationsEnums$CollectionUseCase forNumber = AndroidPrivacyAnnotationsEnums$CollectionUseCase.forNumber(this.collectionBases_.getInt(i));
        return forNumber == null ? AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DEFAULT : forNumber;
    }

    public int getCollectionBasesCount() {
        return this.collectionBases_.size();
    }

    public int getFeatureNameHash() {
        return this.featureNameHash_;
    }

    public boolean hasFeatureNameHash() {
        return (this.bitField0_ & 2) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(AndroidCollectionBasis$CollectionBasisHolder.DEFAULT_INSTANCE);
        }

        public Builder clearFeatureNameHash() {
            copyOnWrite();
            ((AndroidCollectionBasis$CollectionBasisHolder) this.instance).clearFeatureNameHash();
            return this;
        }

        /* synthetic */ Builder(AndroidCollectionBasis$1 androidCollectionBasis$1) {
            this();
        }
    }
}
