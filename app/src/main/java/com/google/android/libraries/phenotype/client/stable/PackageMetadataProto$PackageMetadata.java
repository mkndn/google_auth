package com.google.android.libraries.phenotype.client.stable;

import com.google.experiments.mobile.base.AndroidBacking;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PackageMetadataProto$PackageMetadata extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ACCOUNT_SCOPED_FIELD_NUMBER = 7;
    public static final int AUTO_SUBPACKAGE_FIELD_NUMBER = 2;
    public static final int BACKING_FIELD_NUMBER = 4;
    private static final PackageMetadataProto$PackageMetadata DEFAULT_INSTANCE;
    public static final int DIRECT_BOOT_AWARE_FIELD_NUMBER = 6;
    public static final int LOG_SOURCE_NAMES_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int STATIC_CONFIG_PACKAGE_FIELD_NUMBER = 1;
    public static final int STICKY_ACCOUNT_SUPPORT_FIELD_NUMBER = 5;
    private boolean accountScoped_;
    private boolean autoSubpackage_;
    private int backing_;
    private int bitField0_;
    private boolean directBootAware_;
    private boolean stickyAccountSupport_;
    private String staticConfigPackage_ = "";
    private Internal.ProtobufList logSourceNames_ = GeneratedMessageLite.emptyProtobufList();

    static {
        PackageMetadataProto$PackageMetadata packageMetadataProto$PackageMetadata = new PackageMetadataProto$PackageMetadata();
        DEFAULT_INSTANCE = packageMetadataProto$PackageMetadata;
        GeneratedMessageLite.registerDefaultInstance(PackageMetadataProto$PackageMetadata.class, packageMetadataProto$PackageMetadata);
    }

    private PackageMetadataProto$PackageMetadata() {
    }

    public static PackageMetadataProto$PackageMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (PackageMetadataProto$PackageMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (PackageMetadataProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new PackageMetadataProto$PackageMetadata();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0004ဌ\u0002\u0005ဇ\u0003\u0006ဇ\u0005\u0007ဇ\u0004", new Object[]{"bitField0_", "staticConfigPackage_", "autoSubpackage_", "backing_", AndroidBacking.internalGetVerifier(), "stickyAccountSupport_", "directBootAware_", "accountScoped_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PackageMetadataProto$PackageMetadata.class) {
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

    public boolean getAccountScoped() {
        return this.accountScoped_;
    }

    public boolean getAutoSubpackage() {
        return this.autoSubpackage_;
    }

    public AndroidBacking getBacking() {
        AndroidBacking forNumber = AndroidBacking.forNumber(this.backing_);
        return forNumber == null ? AndroidBacking.UNKNOWN : forNumber;
    }

    public boolean getDirectBootAware() {
        return this.directBootAware_;
    }

    public String getStaticConfigPackage() {
        return this.staticConfigPackage_;
    }

    public boolean getStickyAccountSupport() {
        return this.stickyAccountSupport_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(PackageMetadataProto$PackageMetadata.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(PackageMetadataProto$1 packageMetadataProto$1) {
            this();
        }
    }
}
