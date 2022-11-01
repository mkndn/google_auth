package com.google.common.flogger;

import com.google.common.flogger.LogContext;
import com.google.common.flogger.backend.Metadata;
import com.google.common.flogger.parser.DefaultPrintfMessageParser;
import com.google.common.flogger.parser.MessageParser;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GoogleLogContext extends LogContext implements LoggingApi {
    /* JADX INFO: Access modifiers changed from: protected */
    public GoogleLogContext(Level level, boolean z) {
        super(level, z);
    }

    @Override // com.google.common.flogger.LogContext
    protected final MessageParser getMessageParser() {
        return DefaultPrintfMessageParser.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.flogger.LogContext
    public boolean postProcess(LogSiteKey logSiteKey) {
        Metadata metadata = getMetadata();
        int size = metadata.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            } else if (metadata.getKey(i).getLabel() == "eye3tag") {
                if (metadata.findValue(LogContext.Key.LOG_CAUSE) == null && metadata.findValue(LogContext.Key.CONTEXT_STACK_SIZE) == null) {
                    addMetadata(LogContext.Key.CONTEXT_STACK_SIZE, StackSize.SMALL);
                }
            } else {
                i++;
            }
        }
        return super.postProcess(logSiteKey);
    }
}
