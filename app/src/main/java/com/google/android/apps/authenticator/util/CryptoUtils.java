package com.google.android.apps.authenticator.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CryptoUtils {
    public static final String DIGEST_SHA_256 = "SHA-256";
    public static final String DIGEST_SHA_512 = "SHA-512";
    public static final String HMAC_SHA_256 = "HmacSHA256";

    private CryptoUtils() {
    }

    public static boolean constantTimeArrayEquals(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return bArr == bArr2;
        } else if (bArr.length != bArr2.length) {
            return false;
        } else {
            byte b = 0;
            for (int i = 0; i < bArr2.length; i++) {
                b = (byte) (b | (bArr[i] ^ bArr2[i]));
            }
            return b == 0;
        }
    }

    public static byte[] digest(String str, byte[] bArr) {
        try {
            return MessageDigest.getInstance(str).digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Unsupported digest algorithm: " + str, e);
        }
    }

    public static byte[] generateMac(String str, SecretKey secretKey, byte[] bArr) {
        try {
            Mac mac = Mac.getInstance(str);
            try {
                mac.init(secretKey);
                return mac.doFinal(bArr);
            } catch (InvalidKeyException e) {
                throw new IllegalArgumentException("Invalid MAC key", e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalArgumentException("Unsupported MAC algorithm: " + str, e2);
        }
    }

    public static boolean verifyMac(String str, SecretKey secretKey, byte[] bArr, byte[] bArr2) {
        return constantTimeArrayEquals(generateMac(str, secretKey, bArr), bArr2);
    }
}
