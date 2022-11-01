package com.google.android.libraries.consentverifier;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MessageContext {
    public final int fieldNumber;
    final boolean hasConsent;
    final int messageId;
    final Integer messageLength;
    final int messageStart;

    public MessageContext(int i, Integer num, int i2, boolean z, int i3) {
        this.messageId = i;
        this.messageLength = num;
        this.messageStart = i2;
        this.hasConsent = z;
        this.fieldNumber = i3;
    }
}
