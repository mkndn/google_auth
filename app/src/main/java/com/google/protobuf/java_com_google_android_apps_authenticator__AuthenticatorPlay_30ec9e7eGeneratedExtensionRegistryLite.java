package com.google.protobuf;

import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricSnapshot;
import com.google.protobuf.GeneratedMessageLite;

/* compiled from: PG */
/* loaded from: classes.dex */
final class java_com_google_android_apps_authenticator__AuthenticatorPlay_30ec9e7eGeneratedExtensionRegistryLite extends ExtensionRegistryLite {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Loader extends GeneratedExtensionRegistryLoader {
        @Override // com.google.protobuf.GeneratedExtensionRegistryLoader
        protected final ExtensionRegistryLite getInstance() {
            return new java_com_google_android_apps_authenticator__AuthenticatorPlay_30ec9e7eGeneratedExtensionRegistryLite();
        }
    }

    private java_com_google_android_apps_authenticator__AuthenticatorPlay_30ec9e7eGeneratedExtensionRegistryLite() {
        super(true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.protobuf.ExtensionRegistryLite
    public GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber(MessageLite messageLite, int i) {
        String name = messageLite.getClass().getName();
        switch (name.hashCode()) {
            case -2075765246:
                name.equals("com.google.common.logging.VisualElementLite$VisualElementLiteProto");
                return null;
            case -922627218:
                name.equals("com.google.protobuf.DescriptorProtos$EnumOptions");
                return null;
            case -771329763:
                name.equals("com.google.protobuf.DescriptorProtos$EnumValueOptions");
                return null;
            case -769276105:
                name.equals("com.google.protos.proto2.bridge.MessageSet");
                return null;
            case -561616335:
                if (name.equals("com.google.android.libraries.performance.primes.transmitter.MetricSnapshot")) {
                    switch (i) {
                        case ClearcutMetricSnapshot.CLEARCUT_METRIC_SNAPSHOT_FIELD_NUMBER /* 334728578 */:
                            return ClearcutMetricSnapshot.clearcutMetricSnapshot;
                        default:
                            return null;
                    }
                }
                break;
            case 91040304:
                name.equals("com.google.protobuf.DescriptorProtos$OneofOptions");
                return null;
            case 150746598:
                name.equals("com.google.protobuf.DescriptorProtos$MessageOptions");
                return null;
            case 586655602:
                name.equals("com.google.experiments.mobile.base.ApplicationProperties");
                return null;
            case 587596435:
                name.equals("com.google.protobuf.DescriptorProtos$FileOptions");
                return null;
            case 1260860755:
                name.equals("com.google.protobuf.DescriptorProtos$FieldOptions");
                return null;
            case 1763336973:
                name.equals("com.google.knowledge.graph.protomesh.Protomesh$StagingProto");
                return null;
        }
        return null;
    }
}
