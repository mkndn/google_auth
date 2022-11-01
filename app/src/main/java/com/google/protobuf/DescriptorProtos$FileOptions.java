package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DescriptorProtos$FileOptions extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final int CC_API_VERSION_FIELD_NUMBER = 2;
    public static final int CC_ENABLE_ARENAS_FIELD_NUMBER = 31;
    public static final int CC_ENABLE_METHOD_HANDLES_FIELD_NUMBER = 49;
    public static final int CC_GENERIC_SERVICES_FIELD_NUMBER = 16;
    public static final int CC_UTF8_VERIFICATION_FIELD_NUMBER = 24;
    public static final int CSHARP_NAMESPACE_FIELD_NUMBER = 37;
    private static final DescriptorProtos$FileOptions DEFAULT_INSTANCE;
    public static final int DEPRECATED_FIELD_NUMBER = 23;
    public static final int GO_API_FLAG_FIELD_NUMBER = 46;
    public static final int GO_PACKAGE_FIELD_NUMBER = 11;
    public static final int JAVASCRIPT_PACKAGE_FIELD_NUMBER = 12;
    public static final int JAVA_ALT_API_PACKAGE_FIELD_NUMBER = 19;
    public static final int JAVA_API_VERSION_FIELD_NUMBER = 5;
    public static final int JAVA_ENABLE_DUAL_GENERATE_MUTABLE_API_FIELD_NUMBER = 26;
    public static final int JAVA_GENERIC_SERVICES_FIELD_NUMBER = 17;
    public static final int JAVA_JAVA5_ENUMS_FIELD_NUMBER = 7;
    public static final int JAVA_MULTIPLE_FILES_FIELD_NUMBER = 10;
    public static final int JAVA_MULTIPLE_FILES_MUTABLE_PACKAGE_FIELD_NUMBER = 29;
    public static final int JAVA_MUTABLE_API_FIELD_NUMBER = 28;
    public static final int JAVA_OUTER_CLASSNAME_FIELD_NUMBER = 8;
    public static final int JAVA_PACKAGE_FIELD_NUMBER = 1;
    public static final int JAVA_STRING_CHECK_UTF8_FIELD_NUMBER = 27;
    public static final int JAVA_USE_JAVAPROTO2_FIELD_NUMBER = 6;
    public static final int JSPB_USE_CORRECT_PROTO2_SEMANTICS_FIELD_NUMBER = 48;
    public static final int OBJC_CLASS_PREFIX_FIELD_NUMBER = 36;
    public static final int OPTIMIZE_FOR_FIELD_NUMBER = 9;
    private static volatile Parser PARSER = null;
    public static final int PHP_CLASS_PREFIX_FIELD_NUMBER = 40;
    public static final int PHP_GENERIC_SERVICES_FIELD_NUMBER = 42;
    public static final int PHP_METADATA_NAMESPACE_FIELD_NUMBER = 44;
    public static final int PHP_NAMESPACE_FIELD_NUMBER = 41;
    public static final int PY_GENERIC_SERVICES_FIELD_NUMBER = 18;
    public static final int RUBY_PACKAGE_FIELD_NUMBER = 45;
    public static final int SWIFT_PREFIX_FIELD_NUMBER = 39;
    public static final int SZL_API_VERSION_FIELD_NUMBER = 14;
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    public static final int USE_JAVA_STUBBY_LIBRARY_FIELD_NUMBER = 47;
    private byte memoizedIsInitialized = 2;
    private int ccApiVersion_ = 2;
    private boolean ccUtf8Verification_ = true;
    private String javaPackage_ = "";
    private int javaApiVersion_ = 2;
    private boolean javaUseJavaproto2_ = true;
    private boolean javaJava5Enums_ = true;
    private String javaAltApiPackage_ = "";
    private String javaOuterClassname_ = "";
    private String javaMultipleFilesMutablePackage_ = "";
    private int optimizeFor_ = 1;
    private String goPackage_ = "";
    private String goApiFlag_ = "";
    private String javascriptPackage_ = "";
    private int szlApiVersion_ = 2;
    private boolean ccEnableArenas_ = true;
    private String objcClassPrefix_ = "";
    private String csharpNamespace_ = "";
    private String swiftPrefix_ = "";
    private String phpClassPrefix_ = "";
    private String phpNamespace_ = "";
    private String phpMetadataNamespace_ = "";
    private String rubyPackage_ = "";
    private boolean jspbUseCorrectProto2Semantics_ = true;
    private Internal.ProtobufList uninterpretedOption_ = emptyProtobufList();

    static {
        DescriptorProtos$FileOptions descriptorProtos$FileOptions = new DescriptorProtos$FileOptions();
        DEFAULT_INSTANCE = descriptorProtos$FileOptions;
        GeneratedMessageLite.registerDefaultInstance(DescriptorProtos$FileOptions.class, descriptorProtos$FileOptions);
    }

    private DescriptorProtos$FileOptions() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (DescriptorProtos$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new DescriptorProtos$FileOptions();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (DescriptorProtos$FileOptions.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return Byte.valueOf(this.memoizedIsInitialized);
            case 7:
                this.memoizedIsInitialized = obj == null ? (byte) 0 : (byte) 1;
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private Builder() {
            super(DescriptorProtos$FileOptions.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(DescriptorProtos$1 descriptorProtos$1) {
            this();
        }
    }
}
