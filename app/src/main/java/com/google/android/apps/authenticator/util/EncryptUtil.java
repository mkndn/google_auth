package com.google.android.apps.authenticator.util;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import android.util.Log;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.integration.android.AndroidKeystoreAesGcm;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.security.auth.x500.X500Principal;

/* compiled from: PG */
/* loaded from: classes.dex */
public class EncryptUtil {
    private static final String ACCOUNT_NAME = "Authenticator";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String ANDROID_OPEN_SSL = "AndroidOpenSSL";
    private static final String LOCAL_TAG = "EncryptUtil";
    private static final String RSA_ALGORITHM = "RSA";
    private static final String RSA_ECB_WITHPAD = "RSA/ECB/PKCS1Padding";
    private KeyStore keyStore;

    private KeyStore.PrivateKeyEntry getOrGenerateRSAKey(Context context) {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        this.keyStore = keyStore;
        keyStore.load(null);
        if (!this.keyStore.containsAlias(ACCOUNT_NAME)) {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 20);
            KeyPairGeneratorSpec build = new KeyPairGeneratorSpec.Builder(context).setAlias(ACCOUNT_NAME).setSubject(new X500Principal(String.format("CN=%s", ACCOUNT_NAME))).setSerialNumber(BigInteger.TEN).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM, ANDROID_KEY_STORE);
            keyPairGenerator.initialize(build);
            keyPairGenerator.generateKeyPair();
        }
        return (KeyStore.PrivateKeyEntry) this.keyStore.getEntry(ACCOUNT_NAME, null);
    }

    private String unwrapSecretInternal(String str) {
        AeadConfig.register();
        return new String(new AndroidKeystoreAesGcm(ACCOUNT_NAME).decrypt(Base64.decode(str, 0), new byte[0]), StandardCharsets.UTF_8);
    }

    private byte[] wrapSecretInternal(String str) {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        this.keyStore = keyStore;
        keyStore.load(null);
        if (!this.keyStore.containsAlias(ACCOUNT_NAME)) {
            generateEncryptionKey();
        }
        AeadConfig.register();
        return new AndroidKeystoreAesGcm(ACCOUNT_NAME).encrypt(str.getBytes(StandardCharsets.UTF_8), new byte[0]);
    }

    public String unwrapSecret(String str, Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                return unwrapSecretInternal(str);
            }
            return unwrapSecretInternal(str, context);
        } catch (IOException | GeneralSecurityException e) {
            Log.e(LOCAL_TAG, "An error has occurred during decryption", e);
            return "";
        }
    }

    public String wrapSecret(String str, Context context) {
        byte[] wrapSecretInternal;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                wrapSecretInternal = wrapSecretInternal(str);
            } else {
                wrapSecretInternal = wrapSecretInternal(str, context);
            }
            return Base64.encodeToString(wrapSecretInternal, 0);
        } catch (IOException | GeneralSecurityException e) {
            Log.e(LOCAL_TAG, "An error has occurred during encryption", e);
            return "";
        }
    }

    private void generateEncryptionKey() {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEY_STORE);
        keyGenerator.init(new KeyGenParameterSpec.Builder(ACCOUNT_NAME, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").build());
        keyGenerator.generateKey();
    }

    private String unwrapSecretInternal(String str, Context context) {
        byte[] decode = Base64.decode(str, 0);
        KeyStore.PrivateKeyEntry orGenerateRSAKey = getOrGenerateRSAKey(context);
        Cipher cipher = Cipher.getInstance(RSA_ECB_WITHPAD, ANDROID_OPEN_SSL);
        cipher.init(2, orGenerateRSAKey.getPrivateKey());
        CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(decode), cipher);
        ArrayList arrayList = new ArrayList();
        for (int read = cipherInputStream.read(); read != -1; read = cipherInputStream.read()) {
            arrayList.add(Byte.valueOf((byte) read));
        }
        int size = arrayList.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = ((Byte) arrayList.get(i)).byteValue();
        }
        return new String(bArr, StandardCharsets.UTF_8);
    }

    private byte[] wrapSecretInternal(String str, Context context) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        KeyStore.PrivateKeyEntry orGenerateRSAKey = getOrGenerateRSAKey(context);
        Cipher cipher = Cipher.getInstance(RSA_ECB_WITHPAD, ANDROID_OPEN_SSL);
        cipher.init(1, orGenerateRSAKey.getCertificate().getPublicKey());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, cipher);
        cipherOutputStream.write(bytes);
        cipherOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
