package com.google.android.libraries.concurrent;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface InternalExecutorDecorator {
    ListeningScheduledExecutorService decorate(ListeningScheduledExecutorService listeningScheduledExecutorService);
}
