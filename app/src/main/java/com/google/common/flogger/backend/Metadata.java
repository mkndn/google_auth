package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Metadata {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Empty extends Metadata {
        static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // com.google.common.flogger.backend.Metadata
        public Object findValue(MetadataKey metadataKey) {
            return null;
        }

        @Override // com.google.common.flogger.backend.Metadata
        public MetadataKey getKey(int i) {
            throw new IndexOutOfBoundsException("cannot read from empty metadata");
        }

        @Override // com.google.common.flogger.backend.Metadata
        public Object getValue(int i) {
            throw new IndexOutOfBoundsException("cannot read from empty metadata");
        }

        @Override // com.google.common.flogger.backend.Metadata
        public int size() {
            return 0;
        }
    }

    public static Metadata empty() {
        return Empty.INSTANCE;
    }

    public abstract Object findValue(MetadataKey metadataKey);

    public abstract MetadataKey getKey(int i);

    public abstract Object getValue(int i);

    public abstract int size();
}
