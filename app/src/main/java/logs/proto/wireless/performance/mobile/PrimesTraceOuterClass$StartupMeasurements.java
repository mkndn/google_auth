package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceOuterClass$StartupMeasurements extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int APP_ATTACH_BASE_CONTEXT_FINISHED_MS_FIELD_NUMBER = 19;
    public static final int APP_ATTACH_BASE_CONTEXT_MS_FIELD_NUMBER = 17;
    public static final int APP_CLASS_LOADED_MS_FIELD_NUMBER = 3;
    public static final int APP_ON_CREATE_FINISHED_MS_FIELD_NUMBER = 18;
    public static final int APP_ON_CREATE_MS_FIELD_NUMBER = 4;
    public static final int BASELINE_TIME_MS_FIELD_NUMBER = 1;
    public static final int COMPLETE_FIELD_NUMBER = 25;
    public static final int CUSTOM_METRICS_MS_FIELD_NUMBER = 16;
    private static final PrimesTraceOuterClass$StartupMeasurements DEFAULT_INSTANCE;
    public static final int FIRST_APP_INTERACTIVE_MS_FIELD_NUMBER = 10;
    public static final int FIRST_DRAWN_MS_FIELD_NUMBER = 9;
    public static final int FIRST_ON_ACTIVITY_INIT_MS_FIELD_NUMBER = 5;
    public static final int FIRST_STARTUP_ACTIVITY_FIELD_NUMBER = 12;
    public static final int LAST_STARTUP_ACTIVITY_FIELD_NUMBER = 13;
    public static final int ONDRAW_BASED_FIRST_DRAWN_MS_FIELD_NUMBER = 23;
    public static final int ONDRAW_FRONT_OF_QUEUE_BASED_FIRST_DRAWN_MS_FIELD_NUMBER = 24;
    private static volatile Parser PARSER = null;
    public static final int PREDRAW_BASED_FIRST_DRAWN_MS_FIELD_NUMBER = 20;
    public static final int PREDRAW_FRONT_OF_QUEUE_BASED_FIRST_DRAWN_MS_FIELD_NUMBER = 21;
    public static final int PROCESS_CREATION_MS_FIELD_NUMBER = 2;
    public static final int PROCESS_START_ELAPSED_REALTIME_MS_FIELD_NUMBER = 15;
    public static final int PROCFS_PROCESS_CREATION_MS_FIELD_NUMBER = 14;
    public static final int STARTED_BY_USER_FIELD_NUMBER = 11;
    public static final int STARTUP_TYPE_FIELD_NUMBER = 22;
    private long appAttachBaseContextFinishedMs_;
    private long appAttachBaseContextMs_;
    private long appClassLoadedMs_;
    private long appOnCreateFinishedMs_;
    private long appOnCreateMs_;
    private long baselineTimeMs_;
    private int bitField0_;
    private boolean complete_;
    private MapFieldLite customMetricsMs_ = MapFieldLite.emptyMapField();
    private long firstAppInteractiveMs_;
    private long firstDrawnMs_;
    private long firstOnActivityInitMs_;
    private PrimesTraceOuterClass$StartupActivity firstStartupActivity_;
    private PrimesTraceOuterClass$StartupActivity lastStartupActivity_;
    private long ondrawBasedFirstDrawnMs_;
    private long ondrawFrontOfQueueBasedFirstDrawnMs_;
    private long predrawBasedFirstDrawnMs_;
    private long predrawFrontOfQueueBasedFirstDrawnMs_;
    private long processStartElapsedRealtimeMs_;
    private long procfsProcessCreationMs_;
    private boolean startedByUser_;
    private int startupType_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CustomMetricsMsDefaultEntryHolder {
        static final MapEntryLite defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.INT32, 0, WireFormat.FieldType.INT64, 0L);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum StartupType implements Internal.EnumLite {
        UNKNOWN(0),
        COLD(1),
        WARM(2),
        NAVIGATION_BACKGROUND(3),
        NAVIGATION_FOREGROUND(4);
        
        public static final int COLD_VALUE = 1;
        public static final int NAVIGATION_BACKGROUND_VALUE = 3;
        public static final int NAVIGATION_FOREGROUND_VALUE = 4;
        public static final int UNKNOWN_VALUE = 0;
        public static final int WARM_VALUE = 2;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.PrimesTraceOuterClass.StartupMeasurements.StartupType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public StartupType findValueByNumber(int i) {
                return StartupType.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class StartupTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new StartupTypeVerifier();

            private StartupTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return StartupType.forNumber(i) != null;
            }
        }

        StartupType(int i) {
            this.value = i;
        }

        public static StartupType forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return COLD;
                case 2:
                    return WARM;
                case 3:
                    return NAVIGATION_BACKGROUND;
                case 4:
                    return NAVIGATION_FOREGROUND;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return StartupTypeVerifier.INSTANCE;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" number=").append(getNumber());
            return sb.append(" name=").append(name()).append('>').toString();
        }
    }

    static {
        PrimesTraceOuterClass$StartupMeasurements primesTraceOuterClass$StartupMeasurements = new PrimesTraceOuterClass$StartupMeasurements();
        DEFAULT_INSTANCE = primesTraceOuterClass$StartupMeasurements;
        GeneratedMessageLite.registerDefaultInstance(PrimesTraceOuterClass$StartupMeasurements.class, primesTraceOuterClass$StartupMeasurements);
    }

    private PrimesTraceOuterClass$StartupMeasurements() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map getMutableCustomMetricsMsMap() {
        return internalGetMutableCustomMetricsMs();
    }

    private MapFieldLite internalGetMutableCustomMetricsMs() {
        if (!this.customMetricsMs_.isMutable()) {
            this.customMetricsMs_ = this.customMetricsMs_.mutableCopy();
        }
        return this.customMetricsMs_;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppAttachBaseContextFinishedMs(long j) {
        this.bitField0_ |= 64;
        this.appAttachBaseContextFinishedMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppAttachBaseContextMs(long j) {
        this.bitField0_ |= 32;
        this.appAttachBaseContextMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppClassLoadedMs(long j) {
        this.bitField0_ |= 16;
        this.appClassLoadedMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppOnCreateFinishedMs(long j) {
        this.bitField0_ |= 256;
        this.appOnCreateFinishedMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppOnCreateMs(long j) {
        this.bitField0_ |= 128;
        this.appOnCreateMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBaselineTimeMs(long j) {
        this.bitField0_ |= 1;
        this.baselineTimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setComplete(boolean z) {
        this.bitField0_ |= 262144;
        this.complete_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFirstAppInteractiveMs(long j) {
        this.bitField0_ |= 32768;
        this.firstAppInteractiveMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFirstDrawnMs(long j) {
        this.bitField0_ |= 1024;
        this.firstDrawnMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFirstOnActivityInitMs(long j) {
        this.bitField0_ |= 512;
        this.firstOnActivityInitMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFirstStartupActivity(PrimesTraceOuterClass$StartupActivity primesTraceOuterClass$StartupActivity) {
        primesTraceOuterClass$StartupActivity.getClass();
        this.firstStartupActivity_ = primesTraceOuterClass$StartupActivity;
        this.bitField0_ |= 524288;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastStartupActivity(PrimesTraceOuterClass$StartupActivity primesTraceOuterClass$StartupActivity) {
        primesTraceOuterClass$StartupActivity.getClass();
        this.lastStartupActivity_ = primesTraceOuterClass$StartupActivity;
        this.bitField0_ |= 1048576;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOndrawBasedFirstDrawnMs(long j) {
        this.bitField0_ |= 8192;
        this.ondrawBasedFirstDrawnMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOndrawFrontOfQueueBasedFirstDrawnMs(long j) {
        this.bitField0_ |= 16384;
        this.ondrawFrontOfQueueBasedFirstDrawnMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPredrawBasedFirstDrawnMs(long j) {
        this.bitField0_ |= 2048;
        this.predrawBasedFirstDrawnMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPredrawFrontOfQueueBasedFirstDrawnMs(long j) {
        this.bitField0_ |= 4096;
        this.predrawFrontOfQueueBasedFirstDrawnMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStartElapsedRealtimeMs(long j) {
        this.bitField0_ |= 4;
        this.processStartElapsedRealtimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcfsProcessCreationMs(long j) {
        this.bitField0_ |= 2;
        this.procfsProcessCreationMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartedByUser(boolean z) {
        this.bitField0_ |= 65536;
        this.startedByUser_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartupType(StartupType startupType) {
        this.startupType_ = startupType.getNumber();
        this.bitField0_ |= 131072;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (PrimesTraceOuterClass$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new PrimesTraceOuterClass$StartupMeasurements();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0015\u0000\u0001\u0001\u0019\u0015\u0001\u0000\u0000\u0001ဂ\u0000\u0003ဂ\u0004\u0004ဂ\u0007\u0005ဂ\t\tဂ\n\nဂ\u000f\u000bဇ\u0010\fဉ\u0013\rဉ\u0014\u000eဂ\u0001\u000fဂ\u0002\u00102\u0011ဂ\u0005\u0012ဂ\b\u0013ဂ\u0006\u0014ဂ\u000b\u0015ဂ\f\u0016ဌ\u0011\u0017ဂ\r\u0018ဂ\u000e\u0019ဇ\u0012", new Object[]{"bitField0_", "baselineTimeMs_", "appClassLoadedMs_", "appOnCreateMs_", "firstOnActivityInitMs_", "firstDrawnMs_", "firstAppInteractiveMs_", "startedByUser_", "firstStartupActivity_", "lastStartupActivity_", "procfsProcessCreationMs_", "processStartElapsedRealtimeMs_", "customMetricsMs_", CustomMetricsMsDefaultEntryHolder.defaultEntry, "appAttachBaseContextMs_", "appOnCreateFinishedMs_", "appAttachBaseContextFinishedMs_", "predrawBasedFirstDrawnMs_", "predrawFrontOfQueueBasedFirstDrawnMs_", "startupType_", StartupType.internalGetVerifier(), "ondrawBasedFirstDrawnMs_", "ondrawFrontOfQueueBasedFirstDrawnMs_", "complete_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PrimesTraceOuterClass$StartupMeasurements.class) {
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

    public long getAppAttachBaseContextFinishedMs() {
        return this.appAttachBaseContextFinishedMs_;
    }

    public long getAppAttachBaseContextMs() {
        return this.appAttachBaseContextMs_;
    }

    public long getAppClassLoadedMs() {
        return this.appClassLoadedMs_;
    }

    public long getAppOnCreateFinishedMs() {
        return this.appOnCreateFinishedMs_;
    }

    public long getAppOnCreateMs() {
        return this.appOnCreateMs_;
    }

    public long getBaselineTimeMs() {
        return this.baselineTimeMs_;
    }

    public long getFirstAppInteractiveMs() {
        return this.firstAppInteractiveMs_;
    }

    public long getFirstDrawnMs() {
        return this.firstDrawnMs_;
    }

    public long getFirstOnActivityInitMs() {
        return this.firstOnActivityInitMs_;
    }

    public PrimesTraceOuterClass$StartupActivity getFirstStartupActivity() {
        PrimesTraceOuterClass$StartupActivity primesTraceOuterClass$StartupActivity = this.firstStartupActivity_;
        return primesTraceOuterClass$StartupActivity == null ? PrimesTraceOuterClass$StartupActivity.getDefaultInstance() : primesTraceOuterClass$StartupActivity;
    }

    public PrimesTraceOuterClass$StartupActivity getLastStartupActivity() {
        PrimesTraceOuterClass$StartupActivity primesTraceOuterClass$StartupActivity = this.lastStartupActivity_;
        return primesTraceOuterClass$StartupActivity == null ? PrimesTraceOuterClass$StartupActivity.getDefaultInstance() : primesTraceOuterClass$StartupActivity;
    }

    public long getOndrawBasedFirstDrawnMs() {
        return this.ondrawBasedFirstDrawnMs_;
    }

    public long getOndrawFrontOfQueueBasedFirstDrawnMs() {
        return this.ondrawFrontOfQueueBasedFirstDrawnMs_;
    }

    public long getPredrawBasedFirstDrawnMs() {
        return this.predrawBasedFirstDrawnMs_;
    }

    public long getPredrawFrontOfQueueBasedFirstDrawnMs() {
        return this.predrawFrontOfQueueBasedFirstDrawnMs_;
    }

    public long getProcessStartElapsedRealtimeMs() {
        return this.processStartElapsedRealtimeMs_;
    }

    public long getProcfsProcessCreationMs() {
        return this.procfsProcessCreationMs_;
    }

    public StartupType getStartupType() {
        StartupType forNumber = StartupType.forNumber(this.startupType_);
        return forNumber == null ? StartupType.UNKNOWN : forNumber;
    }

    public boolean hasAppAttachBaseContextFinishedMs() {
        return (this.bitField0_ & 64) != 0;
    }

    public boolean hasAppAttachBaseContextMs() {
        return (this.bitField0_ & 32) != 0;
    }

    public boolean hasAppClassLoadedMs() {
        return (this.bitField0_ & 16) != 0;
    }

    public boolean hasAppOnCreateFinishedMs() {
        return (this.bitField0_ & 256) != 0;
    }

    public boolean hasAppOnCreateMs() {
        return (this.bitField0_ & 128) != 0;
    }

    public boolean hasFirstAppInteractiveMs() {
        return (this.bitField0_ & 32768) != 0;
    }

    public boolean hasFirstDrawnMs() {
        return (this.bitField0_ & 1024) != 0;
    }

    public boolean hasFirstOnActivityInitMs() {
        return (this.bitField0_ & 512) != 0;
    }

    public boolean hasFirstStartupActivity() {
        return (this.bitField0_ & 524288) != 0;
    }

    public boolean hasLastStartupActivity() {
        return (this.bitField0_ & 1048576) != 0;
    }

    public boolean hasOndrawBasedFirstDrawnMs() {
        return (this.bitField0_ & 8192) != 0;
    }

    public boolean hasOndrawFrontOfQueueBasedFirstDrawnMs() {
        return (this.bitField0_ & 16384) != 0;
    }

    public boolean hasPredrawBasedFirstDrawnMs() {
        return (this.bitField0_ & 2048) != 0;
    }

    public boolean hasPredrawFrontOfQueueBasedFirstDrawnMs() {
        return (this.bitField0_ & 4096) != 0;
    }

    public boolean hasProcessStartElapsedRealtimeMs() {
        return (this.bitField0_ & 4) != 0;
    }

    public boolean hasProcfsProcessCreationMs() {
        return (this.bitField0_ & 2) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(PrimesTraceOuterClass$StartupMeasurements.DEFAULT_INSTANCE);
        }

        public long getAppAttachBaseContextFinishedMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getAppAttachBaseContextFinishedMs();
        }

        public long getAppAttachBaseContextMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getAppAttachBaseContextMs();
        }

        public long getAppClassLoadedMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getAppClassLoadedMs();
        }

        public long getAppOnCreateFinishedMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getAppOnCreateFinishedMs();
        }

        public long getAppOnCreateMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getAppOnCreateMs();
        }

        public long getBaselineTimeMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getBaselineTimeMs();
        }

        public long getFirstAppInteractiveMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getFirstAppInteractiveMs();
        }

        public long getFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getFirstDrawnMs();
        }

        public long getFirstOnActivityInitMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getFirstOnActivityInitMs();
        }

        public PrimesTraceOuterClass$StartupActivity getFirstStartupActivity() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getFirstStartupActivity();
        }

        public PrimesTraceOuterClass$StartupActivity getLastStartupActivity() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getLastStartupActivity();
        }

        public long getOndrawBasedFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getOndrawBasedFirstDrawnMs();
        }

        public long getOndrawFrontOfQueueBasedFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getOndrawFrontOfQueueBasedFirstDrawnMs();
        }

        public long getPredrawBasedFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getPredrawBasedFirstDrawnMs();
        }

        public long getPredrawFrontOfQueueBasedFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getPredrawFrontOfQueueBasedFirstDrawnMs();
        }

        public long getProcessStartElapsedRealtimeMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getProcessStartElapsedRealtimeMs();
        }

        public long getProcfsProcessCreationMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getProcfsProcessCreationMs();
        }

        public StartupType getStartupType() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getStartupType();
        }

        public boolean hasAppAttachBaseContextFinishedMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasAppAttachBaseContextFinishedMs();
        }

        public boolean hasAppAttachBaseContextMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasAppAttachBaseContextMs();
        }

        public boolean hasAppClassLoadedMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasAppClassLoadedMs();
        }

        public boolean hasAppOnCreateFinishedMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasAppOnCreateFinishedMs();
        }

        public boolean hasAppOnCreateMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasAppOnCreateMs();
        }

        public boolean hasFirstAppInteractiveMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasFirstAppInteractiveMs();
        }

        public boolean hasFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasFirstDrawnMs();
        }

        public boolean hasFirstOnActivityInitMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasFirstOnActivityInitMs();
        }

        public boolean hasFirstStartupActivity() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasFirstStartupActivity();
        }

        public boolean hasLastStartupActivity() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasLastStartupActivity();
        }

        public boolean hasOndrawBasedFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasOndrawBasedFirstDrawnMs();
        }

        public boolean hasOndrawFrontOfQueueBasedFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasOndrawFrontOfQueueBasedFirstDrawnMs();
        }

        public boolean hasPredrawBasedFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasPredrawBasedFirstDrawnMs();
        }

        public boolean hasPredrawFrontOfQueueBasedFirstDrawnMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasPredrawFrontOfQueueBasedFirstDrawnMs();
        }

        public boolean hasProcessStartElapsedRealtimeMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasProcessStartElapsedRealtimeMs();
        }

        public boolean hasProcfsProcessCreationMs() {
            return ((PrimesTraceOuterClass$StartupMeasurements) this.instance).hasProcfsProcessCreationMs();
        }

        public Builder putCustomMetricsMs(int i, long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).getMutableCustomMetricsMsMap().put(Integer.valueOf(i), Long.valueOf(j));
            return this;
        }

        public Builder setAppAttachBaseContextFinishedMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setAppAttachBaseContextFinishedMs(j);
            return this;
        }

        public Builder setAppAttachBaseContextMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setAppAttachBaseContextMs(j);
            return this;
        }

        public Builder setAppClassLoadedMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setAppClassLoadedMs(j);
            return this;
        }

        public Builder setAppOnCreateFinishedMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setAppOnCreateFinishedMs(j);
            return this;
        }

        public Builder setAppOnCreateMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setAppOnCreateMs(j);
            return this;
        }

        public Builder setBaselineTimeMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setBaselineTimeMs(j);
            return this;
        }

        public Builder setComplete(boolean z) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setComplete(z);
            return this;
        }

        public Builder setFirstAppInteractiveMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setFirstAppInteractiveMs(j);
            return this;
        }

        public Builder setFirstDrawnMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setFirstDrawnMs(j);
            return this;
        }

        public Builder setFirstOnActivityInitMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setFirstOnActivityInitMs(j);
            return this;
        }

        public Builder setFirstStartupActivity(PrimesTraceOuterClass$StartupActivity primesTraceOuterClass$StartupActivity) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setFirstStartupActivity(primesTraceOuterClass$StartupActivity);
            return this;
        }

        public Builder setLastStartupActivity(PrimesTraceOuterClass$StartupActivity primesTraceOuterClass$StartupActivity) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setLastStartupActivity(primesTraceOuterClass$StartupActivity);
            return this;
        }

        public Builder setOndrawBasedFirstDrawnMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setOndrawBasedFirstDrawnMs(j);
            return this;
        }

        public Builder setOndrawFrontOfQueueBasedFirstDrawnMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setOndrawFrontOfQueueBasedFirstDrawnMs(j);
            return this;
        }

        public Builder setPredrawBasedFirstDrawnMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setPredrawBasedFirstDrawnMs(j);
            return this;
        }

        public Builder setPredrawFrontOfQueueBasedFirstDrawnMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setPredrawFrontOfQueueBasedFirstDrawnMs(j);
            return this;
        }

        public Builder setProcessStartElapsedRealtimeMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setProcessStartElapsedRealtimeMs(j);
            return this;
        }

        public Builder setProcfsProcessCreationMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setProcfsProcessCreationMs(j);
            return this;
        }

        public Builder setStartedByUser(boolean z) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setStartedByUser(z);
            return this;
        }

        public Builder setStartupType(StartupType startupType) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupMeasurements) this.instance).setStartupType(startupType);
            return this;
        }

        /* synthetic */ Builder(PrimesTraceOuterClass$1 primesTraceOuterClass$1) {
            this();
        }
    }
}
