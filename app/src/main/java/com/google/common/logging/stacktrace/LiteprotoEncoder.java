package com.google.common.logging.stacktrace;

import android.os.Build;
import com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto;
import com.google.common.logging.proto2api.Logrecord$ThrowableProto;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.IdentityHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LiteprotoEncoder {
    private static void addElement(Logrecord$ThrowableBlockProto.Builder builder, StackTraceElement stackTraceElement) {
        Logrecord$ThrowableBlockProto.StackTraceElement.Builder newBuilder = Logrecord$ThrowableBlockProto.StackTraceElement.newBuilder();
        if (stackTraceElement != null) {
            encodeElement(newBuilder, stackTraceElement);
        }
        builder.addStackTraceElement(newBuilder);
    }

    private static Logrecord$ThrowableBlockProto.Builder createMinimalBlock() {
        return Logrecord$ThrowableBlockProto.newBuilder().setOriginalClass("");
    }

    public static Logrecord$ThrowableProto.ThrowableNode.Builder createNode(Throwable th, boolean z) {
        return Logrecord$ThrowableProto.ThrowableNode.newBuilder().setThrowableInfo(fillBlock(th, z));
    }

    public static Logrecord$ThrowableProto.Builder encodeThrowableWithGraph(Throwable th, boolean z) {
        Throwable[] m;
        Logrecord$ThrowableProto.Builder newBuilder = Logrecord$ThrowableProto.newBuilder();
        newBuilder.setOutermost(createMinimalBlock());
        IdentityHashMap identityHashMap = new IdentityHashMap();
        ArrayList<Logrecord$ThrowableProto.ThrowableNode.Builder> arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(th);
        identityHashMap.put(th, 0);
        arrayList.add(createNode(th, z));
        while (!arrayDeque.isEmpty()) {
            Throwable th2 = (Throwable) arrayDeque.remove();
            Integer num = (Integer) identityHashMap.get(th2);
            num.getClass();
            int intValue = num.intValue();
            if (th2.getCause() != null) {
                Throwable cause = th2.getCause();
                if (!identityHashMap.containsKey(cause)) {
                    identityHashMap.put(cause, Integer.valueOf(identityHashMap.size()));
                    arrayList.add(createNode(cause, z));
                    arrayDeque.add(cause);
                }
                ((Logrecord$ThrowableProto.ThrowableNode.Builder) arrayList.get(intValue)).setCause(((Integer) identityHashMap.get(cause)).intValue());
            }
            if (Build.VERSION.SDK_INT >= 19) {
                for (Throwable th3 : LiteprotoEncoder$$ExternalSyntheticBackport0.m(th2)) {
                    if (!identityHashMap.containsKey(th3)) {
                        identityHashMap.put(th3, Integer.valueOf(identityHashMap.size()));
                        arrayList.add(createNode(th3, z));
                        arrayDeque.add(th3);
                    }
                    ((Logrecord$ThrowableProto.ThrowableNode.Builder) arrayList.get(intValue)).addSuppressed(((Integer) identityHashMap.get(th3)).intValue());
                }
            }
        }
        Logrecord$ThrowableProto.ThrowableGraph.Builder newBuilder2 = Logrecord$ThrowableProto.ThrowableGraph.newBuilder();
        for (Logrecord$ThrowableProto.ThrowableNode.Builder builder : arrayList) {
            newBuilder2.addNodes(builder);
        }
        newBuilder.setGraph(newBuilder2);
        return newBuilder;
    }

    private static Logrecord$ThrowableBlockProto.Builder fillBlock(Throwable th, boolean z) {
        StackTraceElement[] stackTraceElementArr;
        Logrecord$ThrowableBlockProto.Builder newBuilder = Logrecord$ThrowableBlockProto.newBuilder();
        newBuilder.setOriginalClass(th.getClass().getName());
        if (z && th.getMessage() != null) {
            newBuilder.setMessage(th.getMessage());
        }
        try {
            stackTraceElementArr = th.getStackTrace();
        } catch (NullPointerException e) {
            stackTraceElementArr = null;
        }
        if (stackTraceElementArr != null) {
            for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                addElement(newBuilder, stackTraceElement);
            }
        }
        return newBuilder;
    }

    static void encodeElement(Logrecord$ThrowableBlockProto.StackTraceElement.Builder builder, StackTraceElement stackTraceElement) {
        builder.setDeclaringClass(stackTraceElement.getClassName()).setMethodName(stackTraceElement.getMethodName()).setLineNumber(stackTraceElement.getLineNumber());
        if (stackTraceElement.getFileName() != null) {
            builder.setFileName(stackTraceElement.getFileName());
        }
    }
}
