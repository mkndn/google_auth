package com.google.android.apps.authenticator.otp;

import com.google.common.base.Ascii;
import com.google.common.flogger.parser.MessageParser;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.crypto.Mac;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PasscodeGenerator {
    private static final int ADJACENT_INTERVALS = 1;
    private static final int[] DIGITS_POWER = {1, 10, 100, 1000, 10000, 100000, MessageParser.MAX_ARG_COUNT, 10000000, 100000000, 1000000000};
    private static final int MAX_PASSCODE_LENGTH = 9;
    private static final int PASS_CODE_LENGTH = 6;
    private final int codeLength;
    private final Signer signer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Signer {
        byte[] sign(byte[] bArr);
    }

    public PasscodeGenerator(Signer signer) {
        this(signer, 6);
    }

    private int hashToInt(byte[] bArr, int i) {
        try {
            return new DataInputStream(new ByteArrayInputStream(bArr, i, bArr.length - i)).readInt();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String padOutput(int i) {
        String num = Integer.toString(i);
        for (int length = num.length(); length < this.codeLength; length++) {
            num = "0" + num;
        }
        return num;
    }

    public String generateResponseCode(long j) {
        return generateResponseCode(ByteBuffer.allocate(8).putLong(j).array());
    }

    public boolean verifyResponseCode(long j, String str) {
        return generateResponseCode(j, null).equals(str);
    }

    public boolean verifyTimeoutCode(long j, String str) {
        return verifyTimeoutCode(str, j, 1, 1);
    }

    public boolean verifyTimeoutCode(String str, long j, int i, int i2) {
        int max = Math.max(i, 0);
        int max2 = Math.max(i2, 0);
        for (int i3 = -max; i3 <= max2; i3++) {
            if (generateResponseCode(j - i3, null).equals(str)) {
                return true;
            }
        }
        return false;
    }

    public PasscodeGenerator(Signer signer, int i) {
        if (i >= 0 && i <= 9) {
            this.signer = signer;
            this.codeLength = i;
            return;
        }
        throw new IllegalArgumentException("PassCodeLength must be between 1 and 9 digits.");
    }

    public String generateResponseCode(long j, byte[] bArr) {
        if (bArr == null) {
            return generateResponseCode(j);
        }
        return generateResponseCode(ByteBuffer.allocate(bArr.length + 8).putLong(j).put(bArr, 0, bArr.length).array());
    }

    public PasscodeGenerator(Mac mac) {
        this(mac, 6);
    }

    public PasscodeGenerator(final Mac mac, int i) {
        this(new Signer() { // from class: com.google.android.apps.authenticator.otp.PasscodeGenerator.1
            @Override // com.google.android.apps.authenticator.otp.PasscodeGenerator.Signer
            public byte[] sign(byte[] bArr) {
                return mac.doFinal(bArr);
            }
        }, i);
    }

    public String generateResponseCode(byte[] bArr) {
        byte[] sign = this.signer.sign(bArr);
        return padOutput((hashToInt(sign, sign[sign.length - 1] & Ascii.SI) & Integer.MAX_VALUE) % DIGITS_POWER[this.codeLength]);
    }
}
