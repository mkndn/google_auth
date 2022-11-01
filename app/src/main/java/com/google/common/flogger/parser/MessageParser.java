package com.google.common.flogger.parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class MessageParser {
    public static final int MAX_ARG_COUNT = 1000000;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void parseImpl(MessageBuilder messageBuilder);

    public abstract void unescape(StringBuilder sb, String str, int i, int i2);
}
