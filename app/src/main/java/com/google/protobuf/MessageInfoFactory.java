package com.google.protobuf;

/* compiled from: PG */
/* loaded from: classes.dex */
interface MessageInfoFactory {
    boolean isSupported(Class cls);

    MessageInfo messageInfoFor(Class cls);
}
