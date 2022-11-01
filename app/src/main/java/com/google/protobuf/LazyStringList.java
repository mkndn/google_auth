package com.google.protobuf;

import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface LazyStringList extends List {
    void add(ByteString byteString);

    Object getRaw(int i);

    List getUnderlyingElements();

    LazyStringList getUnmodifiableView();
}
