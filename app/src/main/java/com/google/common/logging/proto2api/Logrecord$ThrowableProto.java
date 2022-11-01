package com.google.common.logging.proto2api;

import com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Logrecord$ThrowableProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CAUSES_FIELD_NUMBER = 2;
    private static final Logrecord$ThrowableProto DEFAULT_INSTANCE;
    public static final int GRAPH_FIELD_NUMBER = 4;
    public static final int LANGUAGE_FIELD_NUMBER = 3;
    public static final int OUTERMOST_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int bitField0_;
    private Object kind_;
    private Logrecord$ThrowableBlockProto outermost_;
    private int kindCase_ = 0;
    private byte memoizedIsInitialized = 2;
    private Internal.ProtobufList causes_ = emptyProtobufList();
    private int language_ = 1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ThrowableGraph extends GeneratedMessageLite implements MessageLiteOrBuilder {
        private static final ThrowableGraph DEFAULT_INSTANCE;
        public static final int NODES_FIELD_NUMBER = 1;
        private static volatile Parser PARSER;
        private byte memoizedIsInitialized = 2;
        private Internal.ProtobufList nodes_ = emptyProtobufList();

        static {
            ThrowableGraph throwableGraph = new ThrowableGraph();
            DEFAULT_INSTANCE = throwableGraph;
            GeneratedMessageLite.registerDefaultInstance(ThrowableGraph.class, throwableGraph);
        }

        private ThrowableGraph() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNodes(ThrowableNode throwableNode) {
            throwableNode.getClass();
            ensureNodesIsMutable();
            this.nodes_.add(throwableNode);
        }

        private void ensureNodesIsMutable() {
            Internal.ProtobufList protobufList = this.nodes_;
            if (!protobufList.isModifiable()) {
                this.nodes_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
        }

        public static ThrowableGraph getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNodes(int i, ThrowableNode throwableNode) {
            throwableNode.getClass();
            ensureNodesIsMutable();
            this.nodes_.set(i, throwableNode);
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (Logrecord$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ThrowableGraph();
                case 2:
                    return new Builder(null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"nodes_", ThrowableNode.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ThrowableGraph.class) {
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

        public ThrowableNode getNodes(int i) {
            return (ThrowableNode) this.nodes_.get(i);
        }

        public int getNodesCount() {
            return this.nodes_.size();
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
            private Builder() {
                super(ThrowableGraph.DEFAULT_INSTANCE);
            }

            public Builder addNodes(ThrowableNode.Builder builder) {
                copyOnWrite();
                ((ThrowableGraph) this.instance).addNodes((ThrowableNode) builder.build());
                return this;
            }

            public Builder setNodes(int i, ThrowableNode throwableNode) {
                copyOnWrite();
                ((ThrowableGraph) this.instance).setNodes(i, throwableNode);
                return this;
            }

            /* synthetic */ Builder(Logrecord$1 logrecord$1) {
                this();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ThrowableNode extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final int CAUSE_FIELD_NUMBER = 2;
        private static final ThrowableNode DEFAULT_INSTANCE;
        private static volatile Parser PARSER = null;
        public static final int SUPPRESSED_FIELD_NUMBER = 3;
        public static final int THROWABLEINFO_FIELD_NUMBER = 1;
        private int bitField0_;
        private int cause_;
        private Logrecord$ThrowableBlockProto throwableInfo_;
        private int suppressedMemoizedSerializedSize = -1;
        private byte memoizedIsInitialized = 2;
        private Internal.IntList suppressed_ = emptyIntList();

        static {
            ThrowableNode throwableNode = new ThrowableNode();
            DEFAULT_INSTANCE = throwableNode;
            GeneratedMessageLite.registerDefaultInstance(ThrowableNode.class, throwableNode);
        }

        private ThrowableNode() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSuppressed(int i) {
            ensureSuppressedIsMutable();
            this.suppressed_.addInt(i);
        }

        private void ensureSuppressedIsMutable() {
            Internal.IntList intList = this.suppressed_;
            if (!intList.isModifiable()) {
                this.suppressed_ = GeneratedMessageLite.mutableCopy(intList);
            }
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCause(int i) {
            this.bitField0_ |= 2;
            this.cause_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setThrowableInfo(Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
            logrecord$ThrowableBlockProto.getClass();
            this.throwableInfo_ = logrecord$ThrowableBlockProto;
            this.bitField0_ |= 1;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (Logrecord$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ThrowableNode();
                case 2:
                    return new Builder(null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0001\u0001ᐉ\u0000\u0002င\u0001\u0003'", new Object[]{"bitField0_", "throwableInfo_", "cause_", "suppressed_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (ThrowableNode.class) {
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

        public Logrecord$ThrowableBlockProto getThrowableInfo() {
            Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto = this.throwableInfo_;
            return logrecord$ThrowableBlockProto == null ? Logrecord$ThrowableBlockProto.getDefaultInstance() : logrecord$ThrowableBlockProto;
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
            private Builder() {
                super(ThrowableNode.DEFAULT_INSTANCE);
            }

            public Builder addSuppressed(int i) {
                copyOnWrite();
                ((ThrowableNode) this.instance).addSuppressed(i);
                return this;
            }

            public Builder setCause(int i) {
                copyOnWrite();
                ((ThrowableNode) this.instance).setCause(i);
                return this;
            }

            public Builder setThrowableInfo(Logrecord$ThrowableBlockProto.Builder builder) {
                copyOnWrite();
                ((ThrowableNode) this.instance).setThrowableInfo((Logrecord$ThrowableBlockProto) builder.build());
                return this;
            }

            /* synthetic */ Builder(Logrecord$1 logrecord$1) {
                this();
            }

            public Builder setThrowableInfo(Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
                copyOnWrite();
                ((ThrowableNode) this.instance).setThrowableInfo(logrecord$ThrowableBlockProto);
                return this;
            }
        }
    }

    static {
        Logrecord$ThrowableProto logrecord$ThrowableProto = new Logrecord$ThrowableProto();
        DEFAULT_INSTANCE = logrecord$ThrowableProto;
        GeneratedMessageLite.registerDefaultInstance(Logrecord$ThrowableProto.class, logrecord$ThrowableProto);
    }

    private Logrecord$ThrowableProto() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCauses(Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
        logrecord$ThrowableBlockProto.getClass();
        ensureCausesIsMutable();
        this.causes_.add(logrecord$ThrowableBlockProto);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCauses() {
        this.causes_ = emptyProtobufList();
    }

    private void ensureCausesIsMutable() {
        Internal.ProtobufList protobufList = this.causes_;
        if (!protobufList.isModifiable()) {
            this.causes_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static Logrecord$ThrowableProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCauses(int i, Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
        logrecord$ThrowableBlockProto.getClass();
        ensureCausesIsMutable();
        this.causes_.set(i, logrecord$ThrowableBlockProto);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGraph(ThrowableGraph throwableGraph) {
        throwableGraph.getClass();
        this.kind_ = throwableGraph;
        this.kindCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOutermost(Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
        logrecord$ThrowableBlockProto.getClass();
        this.outermost_ = logrecord$ThrowableBlockProto;
        this.bitField0_ |= 1;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Logrecord$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Logrecord$ThrowableProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0001\u0001\u0001\u0004\u0003\u0000\u0001\u0003\u0001ᔉ\u0000\u0002Л\u0004ᐼ\u0000", new Object[]{"kind_", "kindCase_", "bitField0_", "outermost_", "causes_", Logrecord$ThrowableBlockProto.class, ThrowableGraph.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Logrecord$ThrowableProto.class) {
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

    public Logrecord$ThrowableBlockProto getCauses(int i) {
        return (Logrecord$ThrowableBlockProto) this.causes_.get(i);
    }

    public int getCausesCount() {
        return this.causes_.size();
    }

    public List getCausesList() {
        return this.causes_;
    }

    public ThrowableGraph getGraph() {
        if (this.kindCase_ == 4) {
            return (ThrowableGraph) this.kind_;
        }
        return ThrowableGraph.getDefaultInstance();
    }

    public Logrecord$ThrowableBlockProto getOutermost() {
        Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto = this.outermost_;
        return logrecord$ThrowableBlockProto == null ? Logrecord$ThrowableBlockProto.getDefaultInstance() : logrecord$ThrowableBlockProto;
    }

    public boolean hasGraph() {
        return this.kindCase_ == 4;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(Logrecord$ThrowableProto.DEFAULT_INSTANCE);
        }

        public Builder addCauses(Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
            copyOnWrite();
            ((Logrecord$ThrowableProto) this.instance).addCauses(logrecord$ThrowableBlockProto);
            return this;
        }

        public Builder clearCauses() {
            copyOnWrite();
            ((Logrecord$ThrowableProto) this.instance).clearCauses();
            return this;
        }

        public Logrecord$ThrowableBlockProto getCauses(int i) {
            return ((Logrecord$ThrowableProto) this.instance).getCauses(i);
        }

        public int getCausesCount() {
            return ((Logrecord$ThrowableProto) this.instance).getCausesCount();
        }

        public List getCausesList() {
            return Collections.unmodifiableList(((Logrecord$ThrowableProto) this.instance).getCausesList());
        }

        public ThrowableGraph getGraph() {
            return ((Logrecord$ThrowableProto) this.instance).getGraph();
        }

        public Logrecord$ThrowableBlockProto getOutermost() {
            return ((Logrecord$ThrowableProto) this.instance).getOutermost();
        }

        public boolean hasGraph() {
            return ((Logrecord$ThrowableProto) this.instance).hasGraph();
        }

        public Builder setCauses(int i, Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
            copyOnWrite();
            ((Logrecord$ThrowableProto) this.instance).setCauses(i, logrecord$ThrowableBlockProto);
            return this;
        }

        public Builder setGraph(ThrowableGraph.Builder builder) {
            copyOnWrite();
            ((Logrecord$ThrowableProto) this.instance).setGraph((ThrowableGraph) builder.build());
            return this;
        }

        public Builder setOutermost(Logrecord$ThrowableBlockProto.Builder builder) {
            copyOnWrite();
            ((Logrecord$ThrowableProto) this.instance).setOutermost((Logrecord$ThrowableBlockProto) builder.build());
            return this;
        }

        /* synthetic */ Builder(Logrecord$1 logrecord$1) {
            this();
        }

        public Builder setGraph(ThrowableGraph throwableGraph) {
            copyOnWrite();
            ((Logrecord$ThrowableProto) this.instance).setGraph(throwableGraph);
            return this;
        }

        public Builder setOutermost(Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
            copyOnWrite();
            ((Logrecord$ThrowableProto) this.instance).setOutermost(logrecord$ThrowableBlockProto);
            return this;
        }
    }
}
