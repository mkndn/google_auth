package com.google.common.io;

import com.google.common.base.Preconditions;
import java.nio.charset.Charset;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ByteSource {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class AsCharSource extends CharSource {
        final Charset charset;

        AsCharSource(Charset charset) {
            this.charset = (Charset) Preconditions.checkNotNull(charset);
        }

        @Override // com.google.common.io.CharSource
        public String read() {
            return new String(ByteSource.this.read(), this.charset);
        }

        public String toString() {
            return ByteSource.this.toString() + ".asCharSource(" + this.charset + ")";
        }
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    public byte[] read() {
        throw null;
    }
}
