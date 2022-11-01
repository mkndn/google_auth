package com.google.android.libraries.concurrent;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import javax.inject.Inject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExecutorDecorator {
    private final InternalExecutorDecorator internalDecorator;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ExecutorDecorator(Optional optional) {
        this.internalDecorator = (InternalExecutorDecorator) optional.orNull();
    }

    public ListeningScheduledExecutorService decorate(ListeningScheduledExecutorService listeningScheduledExecutorService) {
        InternalExecutorDecorator internalExecutorDecorator = this.internalDecorator;
        if (internalExecutorDecorator == null) {
            return listeningScheduledExecutorService;
        }
        return internalExecutorDecorator.decorate(listeningScheduledExecutorService);
    }
}
