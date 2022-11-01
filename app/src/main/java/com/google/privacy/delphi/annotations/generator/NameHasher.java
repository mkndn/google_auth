package com.google.privacy.delphi.annotations.generator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NameHasher {
    public static int computeHash(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            i = (i * 31) + str.charAt(i2);
        }
        return i;
    }
}
