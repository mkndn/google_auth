package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto;
import com.google.common.logging.proto2api.Logrecord$ThrowableProto;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ThrowableMapper {
    public static Logrecord$ThrowableProto.Builder apply(Logrecord$ThrowableProto.Builder builder, ExceptionMessageMappingFunction exceptionMessageMappingFunction) {
        Logrecord$ThrowableBlockProto outermost = builder.getOutermost();
        if (outermost.hasMessage()) {
            String message = outermost.getMessage();
            String apply = exceptionMessageMappingFunction.apply(message);
            if (!message.equals(apply)) {
                builder.setOutermost((Logrecord$ThrowableBlockProto) ((Logrecord$ThrowableBlockProto.Builder) outermost.toBuilder()).setMessage(apply).build());
            }
        }
        if (builder.hasGraph()) {
            return applyGraph(builder, exceptionMessageMappingFunction);
        }
        return applyCauses(builder, exceptionMessageMappingFunction);
    }

    private static Logrecord$ThrowableProto.Builder applyCauses(Logrecord$ThrowableProto.Builder builder, ExceptionMessageMappingFunction exceptionMessageMappingFunction) {
        for (int i = 0; i < builder.getCausesCount(); i++) {
            Logrecord$ThrowableBlockProto causes = builder.getCauses(i);
            if (causes.hasMessage()) {
                String message = causes.getMessage();
                String apply = exceptionMessageMappingFunction.apply(message);
                if (!message.equals(apply)) {
                    builder.setCauses(i, (Logrecord$ThrowableBlockProto) ((Logrecord$ThrowableBlockProto.Builder) causes.toBuilder()).setMessage(apply).build());
                }
            }
        }
        return builder;
    }

    private static Logrecord$ThrowableProto.Builder applyGraph(Logrecord$ThrowableProto.Builder builder, ExceptionMessageMappingFunction exceptionMessageMappingFunction) {
        Logrecord$ThrowableProto.ThrowableGraph graph = builder.getGraph();
        Logrecord$ThrowableProto.ThrowableGraph.Builder builder2 = null;
        for (int i = 0; i < graph.getNodesCount(); i++) {
            Logrecord$ThrowableProto.ThrowableNode nodes = graph.getNodes(i);
            Logrecord$ThrowableBlockProto throwableInfo = nodes.getThrowableInfo();
            if (throwableInfo.hasMessage()) {
                String message = throwableInfo.getMessage();
                String apply = exceptionMessageMappingFunction.apply(message);
                if (!message.equals(apply)) {
                    if (builder2 == null) {
                        builder2 = (Logrecord$ThrowableProto.ThrowableGraph.Builder) graph.toBuilder();
                    }
                    builder2.setNodes(i, (Logrecord$ThrowableProto.ThrowableNode) ((Logrecord$ThrowableProto.ThrowableNode.Builder) nodes.toBuilder()).setThrowableInfo((Logrecord$ThrowableBlockProto) ((Logrecord$ThrowableBlockProto.Builder) throwableInfo.toBuilder()).setMessage(apply).build()).build());
                }
            }
        }
        if (builder2 != null) {
            builder.setGraph((Logrecord$ThrowableProto.ThrowableGraph) builder2.build());
        }
        return builder;
    }
}
