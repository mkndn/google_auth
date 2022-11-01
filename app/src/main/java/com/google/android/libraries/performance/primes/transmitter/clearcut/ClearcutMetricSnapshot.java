package com.google.android.libraries.performance.primes.transmitter.clearcut;

import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearcutMetricSnapshot extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ANONYMOUS_FIELD_NUMBER = 3;
    public static final int CLEARCUT_METRIC_SNAPSHOT_FIELD_NUMBER = 334728578;
    private static final ClearcutMetricSnapshot DEFAULT_INSTANCE;
    public static final int EXPERIMENT_IDS_FIELD_NUMBER = 6;
    public static final int LOG_SOURCE_FIELD_NUMBER = 1;
    public static final int MENDEL_PACKAGE_NAME_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int UPLOAD_ACCOUNT_NAME_FIELD_NUMBER = 5;
    public static final int ZWIEBACK_COOKIE_OVERRIDE_FIELD_NUMBER = 4;
    public static final GeneratedMessageLite.GeneratedExtension clearcutMetricSnapshot;
    private boolean anonymous_;
    private int bitField0_;
    private int experimentIdsMemoizedSerializedSize = -1;
    private String logSource_ = "";
    private String mendelPackageName_ = "";
    private String zwiebackCookieOverride_ = "";
    private String uploadAccountName_ = "";
    private Internal.IntList experimentIds_ = emptyIntList();

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricSnapshot$1  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    static {
        ClearcutMetricSnapshot clearcutMetricSnapshot2 = new ClearcutMetricSnapshot();
        DEFAULT_INSTANCE = clearcutMetricSnapshot2;
        GeneratedMessageLite.registerDefaultInstance(ClearcutMetricSnapshot.class, clearcutMetricSnapshot2);
        clearcutMetricSnapshot = GeneratedMessageLite.newSingularGeneratedExtension(MetricSnapshot.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, CLEARCUT_METRIC_SNAPSHOT_FIELD_NUMBER, WireFormat.FieldType.MESSAGE, ClearcutMetricSnapshot.class);
    }

    private ClearcutMetricSnapshot() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllExperimentIds(Iterable iterable) {
        ensureExperimentIdsIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.experimentIds_);
    }

    private void ensureExperimentIdsIsMutable() {
        Internal.IntList intList = this.experimentIds_;
        if (!intList.isModifiable()) {
            this.experimentIds_ = GeneratedMessageLite.mutableCopy(intList);
        }
    }

    public static ClearcutMetricSnapshot getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnonymous(boolean z) {
        this.bitField0_ |= 4;
        this.anonymous_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLogSource(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.logSource_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMendelPackageName(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.mendelPackageName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUploadAccountName(String str) {
        str.getClass();
        this.bitField0_ |= 16;
        this.uploadAccountName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setZwiebackCookieOverride(String str) {
        str.getClass();
        this.bitField0_ |= 8;
        this.zwiebackCookieOverride_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ClearcutMetricSnapshot();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006'", new Object[]{"bitField0_", "logSource_", "mendelPackageName_", "anonymous_", "zwiebackCookieOverride_", "uploadAccountName_", "experimentIds_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ClearcutMetricSnapshot.class) {
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

    public boolean getAnonymous() {
        return this.anonymous_;
    }

    public List getExperimentIdsList() {
        return this.experimentIds_;
    }

    public String getLogSource() {
        return this.logSource_;
    }

    public String getMendelPackageName() {
        return this.mendelPackageName_;
    }

    public String getUploadAccountName() {
        return this.uploadAccountName_;
    }

    public String getZwiebackCookieOverride() {
        return this.zwiebackCookieOverride_;
    }

    public boolean hasMendelPackageName() {
        return (this.bitField0_ & 2) != 0;
    }

    public boolean hasUploadAccountName() {
        return (this.bitField0_ & 16) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(ClearcutMetricSnapshot.DEFAULT_INSTANCE);
        }

        public Builder addAllExperimentIds(Iterable iterable) {
            copyOnWrite();
            ((ClearcutMetricSnapshot) this.instance).addAllExperimentIds(iterable);
            return this;
        }

        public Builder setAnonymous(boolean z) {
            copyOnWrite();
            ((ClearcutMetricSnapshot) this.instance).setAnonymous(z);
            return this;
        }

        public Builder setLogSource(String str) {
            copyOnWrite();
            ((ClearcutMetricSnapshot) this.instance).setLogSource(str);
            return this;
        }

        public Builder setMendelPackageName(String str) {
            copyOnWrite();
            ((ClearcutMetricSnapshot) this.instance).setMendelPackageName(str);
            return this;
        }

        public Builder setUploadAccountName(String str) {
            copyOnWrite();
            ((ClearcutMetricSnapshot) this.instance).setUploadAccountName(str);
            return this;
        }

        public Builder setZwiebackCookieOverride(String str) {
            copyOnWrite();
            ((ClearcutMetricSnapshot) this.instance).setZwiebackCookieOverride(str);
            return this;
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
