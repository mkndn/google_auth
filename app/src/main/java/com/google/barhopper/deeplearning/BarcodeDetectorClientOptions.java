package com.google.barhopper.deeplearning;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BarcodeDetectorClientOptions extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ANCHOR_LAYERS_FIELD_NUMBER = 6;
    public static final int COMPUTE_SETTINGS_FIELD_NUMBER = 9;
    private static final BarcodeDetectorClientOptions DEFAULT_INSTANCE;
    public static final int EXTRA_SCALES_FIELD_NUMBER = 7;
    public static final int IOU_THRESHOLD_FIELD_NUMBER = 5;
    public static final int MAX_DETECTIONS_FIELD_NUMBER = 3;
    public static final int MODEL_FIELD_NUMBER = 2;
    public static final int MODEL_FILE_FIELD_NUMBER = 1;
    public static final int NUM_CLASSES_FIELD_NUMBER = 12;
    public static final int NUM_KEY_POINTS_FIELD_NUMBER = 11;
    public static final int NUM_THREADS_FIELD_NUMBER = 8;
    private static volatile Parser PARSER = null;
    public static final int SCORE_THRESHOLD_FIELD_NUMBER = 4;
    public static final int TRAINED_IMAGE_SIZE_FIELD_NUMBER = 10;
    private String modelFile_ = "";
    private ByteString model_ = ByteString.EMPTY;
    private int maxDetections_ = 10;
    private float scoreThreshold_ = 0.5f;
    private float iouThreshold_ = 0.05f;
    private Internal.FloatList extraScales_ = emptyFloatList();
    private int numThreads_ = 1;
    private int trainedImageSize_ = AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_NAVIGATION_VALUE;
    private int numKeyPoints_ = 4;
    private int numClasses_ = 2;

    /* compiled from: PG */
    /* renamed from: com.google.barhopper.deeplearning.BarcodeDetectorClientOptions$1  reason: invalid class name */
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
        BarcodeDetectorClientOptions barcodeDetectorClientOptions = new BarcodeDetectorClientOptions();
        DEFAULT_INSTANCE = barcodeDetectorClientOptions;
        GeneratedMessageLite.registerDefaultInstance(BarcodeDetectorClientOptions.class, barcodeDetectorClientOptions);
    }

    private BarcodeDetectorClientOptions() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BarcodeDetectorClientOptions();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BarcodeDetectorClientOptions.class) {
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
            super(BarcodeDetectorClientOptions.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
