package com.google.common.flogger.backend;

import com.google.common.flogger.parser.MessageParser;
import com.google.common.flogger.util.Checks;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TemplateContext {
    private final String message;
    private final MessageParser parser;

    public TemplateContext(MessageParser messageParser, String str) {
        this.parser = (MessageParser) Checks.checkNotNull(messageParser, "parser");
        this.message = (String) Checks.checkNotNull(str, "message");
    }

    public boolean equals(Object obj) {
        if (obj instanceof TemplateContext) {
            TemplateContext templateContext = (TemplateContext) obj;
            return this.parser.equals(templateContext.parser) && this.message.equals(templateContext.message);
        }
        return false;
    }

    public String getMessage() {
        return this.message;
    }

    public MessageParser getParser() {
        return this.parser;
    }

    public int hashCode() {
        return this.parser.hashCode() ^ this.message.hashCode();
    }
}
