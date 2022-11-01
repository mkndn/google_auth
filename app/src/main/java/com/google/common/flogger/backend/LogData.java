package com.google.common.flogger.backend;

import com.google.common.flogger.LogSite;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface LogData {
    Object[] getArguments();

    Level getLevel();

    Object getLiteralArgument();

    LogSite getLogSite();

    Metadata getMetadata();

    TemplateContext getTemplateContext();

    long getTimestampNanos();

    boolean wasForced();
}
