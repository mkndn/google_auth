package com.google.common.hash;

import java.nio.charset.Charset;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class AbstractHashFunction implements HashFunction {
    public HashCode hashBytes(byte[] bArr) {
        return hashBytes(bArr, 0, bArr.length);
    }

    public HashCode hashBytes(byte[] bArr, int i, int i2) {
        throw null;
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        throw null;
    }
}
