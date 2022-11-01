package logs.proto.wireless.performance.mobile;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetric$AndroidMemoryStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ANON_RSS_KB_FIELD_NUMBER = 22;
    public static final int AVAILABLE_MEMORY_KB_FIELD_NUMBER = 17;
    public static final int DALVIK_PRIVATE_DIRTY_KB_FIELD_NUMBER = 4;
    public static final int DALVIK_PSS_KB_FIELD_NUMBER = 1;
    private static final MemoryMetric$AndroidMemoryStats DEFAULT_INSTANCE;
    public static final int NATIVE_PRIVATE_DIRTY_KB_FIELD_NUMBER = 5;
    public static final int NATIVE_PSS_KB_FIELD_NUMBER = 2;
    public static final int OTHER_GRAPHICS_PSS_KB_FIELD_NUMBER = 10;
    public static final int OTHER_PRIVATE_DIRTY_KB_FIELD_NUMBER = 6;
    public static final int OTHER_PSS_KB_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int RSS_HWM_KB_FIELD_NUMBER = 20;
    public static final int SUMMARY_CODE_KB_FIELD_NUMBER = 12;
    public static final int SUMMARY_GRAPHICS_KB_FIELD_NUMBER = 14;
    public static final int SUMMARY_JAVA_HEAP_KB_FIELD_NUMBER = 11;
    public static final int SUMMARY_PRIVATE_OTHER_KB_FIELD_NUMBER = 15;
    public static final int SUMMARY_STACK_KB_FIELD_NUMBER = 13;
    public static final int SUMMARY_SYSTEM_KB_FIELD_NUMBER = 16;
    public static final int SWAP_KB_FIELD_NUMBER = 23;
    public static final int TOTAL_MEMORY_MB_FIELD_NUMBER = 18;
    public static final int TOTAL_PRIVATE_CLEAN_KB_FIELD_NUMBER = 7;
    public static final int TOTAL_PSS_BY_MEM_INFO_KB_FIELD_NUMBER = 19;
    public static final int TOTAL_RSS_KB_FIELD_NUMBER = 21;
    public static final int TOTAL_SHARED_DIRTY_KB_FIELD_NUMBER = 8;
    public static final int TOTAL_SWAPPABLE_PSS_KB_FIELD_NUMBER = 9;
    public static final int VM_SIZE_KB_FIELD_NUMBER = 24;
    private long anonRssKb_;
    private int availableMemoryKb_;
    private int bitField0_;
    private int dalvikPrivateDirtyKb_;
    private int dalvikPssKb_;
    private int nativePrivateDirtyKb_;
    private int nativePssKb_;
    private int otherGraphicsPssKb_;
    private int otherPrivateDirtyKb_;
    private int otherPssKb_;
    private long rssHwmKb_;
    private int summaryCodeKb_;
    private int summaryGraphicsKb_;
    private int summaryJavaHeapKb_;
    private int summaryPrivateOtherKb_;
    private int summaryStackKb_;
    private int summarySystemKb_;
    private long swapKb_;
    private int totalMemoryMb_;
    private int totalPrivateCleanKb_;
    private int totalPssByMemInfoKb_;
    private long totalRssKb_;
    private int totalSharedDirtyKb_;
    private int totalSwappablePssKb_;
    private long vmSizeKb_;

    static {
        MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats = new MemoryMetric$AndroidMemoryStats();
        DEFAULT_INSTANCE = memoryMetric$AndroidMemoryStats;
        GeneratedMessageLite.registerDefaultInstance(MemoryMetric$AndroidMemoryStats.class, memoryMetric$AndroidMemoryStats);
    }

    private MemoryMetric$AndroidMemoryStats() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnonRssKb(long j) {
        this.bitField0_ |= 2097152;
        this.anonRssKb_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAvailableMemoryKb(int i) {
        this.bitField0_ |= 131072;
        this.availableMemoryKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDalvikPrivateDirtyKb(int i) {
        this.bitField0_ |= 8;
        this.dalvikPrivateDirtyKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDalvikPssKb(int i) {
        this.bitField0_ |= 1;
        this.dalvikPssKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNativePrivateDirtyKb(int i) {
        this.bitField0_ |= 16;
        this.nativePrivateDirtyKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNativePssKb(int i) {
        this.bitField0_ |= 2;
        this.nativePssKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOtherGraphicsPssKb(int i) {
        this.bitField0_ |= 1024;
        this.otherGraphicsPssKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOtherPrivateDirtyKb(int i) {
        this.bitField0_ |= 32;
        this.otherPrivateDirtyKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOtherPssKb(int i) {
        this.bitField0_ |= 4;
        this.otherPssKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRssHwmKb(long j) {
        this.bitField0_ |= 524288;
        this.rssHwmKb_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSummaryCodeKb(int i) {
        this.bitField0_ |= 4096;
        this.summaryCodeKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSummaryGraphicsKb(int i) {
        this.bitField0_ |= 16384;
        this.summaryGraphicsKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSummaryJavaHeapKb(int i) {
        this.bitField0_ |= 2048;
        this.summaryJavaHeapKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSummaryPrivateOtherKb(int i) {
        this.bitField0_ |= 32768;
        this.summaryPrivateOtherKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSummaryStackKb(int i) {
        this.bitField0_ |= 8192;
        this.summaryStackKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSummarySystemKb(int i) {
        this.bitField0_ |= 65536;
        this.summarySystemKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwapKb(long j) {
        this.bitField0_ |= AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
        this.swapKb_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalMemoryMb(int i) {
        this.bitField0_ |= 262144;
        this.totalMemoryMb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalPrivateCleanKb(int i) {
        this.bitField0_ |= 128;
        this.totalPrivateCleanKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalPssByMemInfoKb(int i) {
        this.bitField0_ |= 64;
        this.totalPssByMemInfoKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalRssKb(long j) {
        this.bitField0_ |= 1048576;
        this.totalRssKb_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalSharedDirtyKb(int i) {
        this.bitField0_ |= 256;
        this.totalSharedDirtyKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalSwappablePssKb(int i) {
        this.bitField0_ |= 512;
        this.totalSwappablePssKb_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVmSizeKb(long j) {
        this.bitField0_ |= 8388608;
        this.vmSizeKb_ = j;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (MemoryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new MemoryMetric$AndroidMemoryStats();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0018\u0000\u0001\u0001\u0018\u0018\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0007\bင\b\tင\t\nင\n\u000bင\u000b\fင\f\rင\r\u000eင\u000e\u000fင\u000f\u0010င\u0010\u0011င\u0011\u0012င\u0012\u0013င\u0006\u0014ဂ\u0013\u0015ဂ\u0014\u0016ဂ\u0015\u0017ဂ\u0016\u0018ဂ\u0017", new Object[]{"bitField0_", "dalvikPssKb_", "nativePssKb_", "otherPssKb_", "dalvikPrivateDirtyKb_", "nativePrivateDirtyKb_", "otherPrivateDirtyKb_", "totalPrivateCleanKb_", "totalSharedDirtyKb_", "totalSwappablePssKb_", "otherGraphicsPssKb_", "summaryJavaHeapKb_", "summaryCodeKb_", "summaryStackKb_", "summaryGraphicsKb_", "summaryPrivateOtherKb_", "summarySystemKb_", "availableMemoryKb_", "totalMemoryMb_", "totalPssByMemInfoKb_", "rssHwmKb_", "totalRssKb_", "anonRssKb_", "swapKb_", "vmSizeKb_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MemoryMetric$AndroidMemoryStats.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(MemoryMetric$AndroidMemoryStats.DEFAULT_INSTANCE);
        }

        public Builder setAnonRssKb(long j) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setAnonRssKb(j);
            return this;
        }

        public Builder setAvailableMemoryKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setAvailableMemoryKb(i);
            return this;
        }

        public Builder setDalvikPrivateDirtyKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setDalvikPrivateDirtyKb(i);
            return this;
        }

        public Builder setDalvikPssKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setDalvikPssKb(i);
            return this;
        }

        public Builder setNativePrivateDirtyKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setNativePrivateDirtyKb(i);
            return this;
        }

        public Builder setNativePssKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setNativePssKb(i);
            return this;
        }

        public Builder setOtherGraphicsPssKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setOtherGraphicsPssKb(i);
            return this;
        }

        public Builder setOtherPrivateDirtyKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setOtherPrivateDirtyKb(i);
            return this;
        }

        public Builder setOtherPssKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setOtherPssKb(i);
            return this;
        }

        public Builder setRssHwmKb(long j) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setRssHwmKb(j);
            return this;
        }

        public Builder setSummaryCodeKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setSummaryCodeKb(i);
            return this;
        }

        public Builder setSummaryGraphicsKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setSummaryGraphicsKb(i);
            return this;
        }

        public Builder setSummaryJavaHeapKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setSummaryJavaHeapKb(i);
            return this;
        }

        public Builder setSummaryPrivateOtherKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setSummaryPrivateOtherKb(i);
            return this;
        }

        public Builder setSummaryStackKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setSummaryStackKb(i);
            return this;
        }

        public Builder setSummarySystemKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setSummarySystemKb(i);
            return this;
        }

        public Builder setSwapKb(long j) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setSwapKb(j);
            return this;
        }

        public Builder setTotalMemoryMb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setTotalMemoryMb(i);
            return this;
        }

        public Builder setTotalPrivateCleanKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setTotalPrivateCleanKb(i);
            return this;
        }

        public Builder setTotalPssByMemInfoKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setTotalPssByMemInfoKb(i);
            return this;
        }

        public Builder setTotalRssKb(long j) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setTotalRssKb(j);
            return this;
        }

        public Builder setTotalSharedDirtyKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setTotalSharedDirtyKb(i);
            return this;
        }

        public Builder setTotalSwappablePssKb(int i) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setTotalSwappablePssKb(i);
            return this;
        }

        public Builder setVmSizeKb(long j) {
            copyOnWrite();
            ((MemoryMetric$AndroidMemoryStats) this.instance).setVmSizeKb(j);
            return this;
        }

        /* synthetic */ Builder(MemoryMetric$1 memoryMetric$1) {
            this();
        }
    }
}
