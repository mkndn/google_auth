package com.google.common.flogger.context;

import com.google.common.flogger.backend.Metadata;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ContextDataProvider {
    public static ContextDataProvider getNoOpProvider() {
        return NoOpContextDataProvider.getNoOpInstance();
    }

    public Metadata getMetadata() {
        return Metadata.empty();
    }

    public Tags getTags() {
        return Tags.empty();
    }

    public boolean shouldForceLogging(String str, Level level, boolean z) {
        return false;
    }
}
