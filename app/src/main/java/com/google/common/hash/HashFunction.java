package com.google.common.hash;

import java.nio.charset.Charset;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface HashFunction {
    HashCode hashString(CharSequence charSequence, Charset charset);
}
