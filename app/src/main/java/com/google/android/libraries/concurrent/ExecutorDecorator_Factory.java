package com.google.android.libraries.concurrent;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExecutorDecorator_Factory implements Factory {
    private final Provider internalDecoratorProvider;

    public ExecutorDecorator_Factory(Provider provider) {
        this.internalDecoratorProvider = provider;
    }

    public static ExecutorDecorator_Factory create(Provider provider) {
        return new ExecutorDecorator_Factory(provider);
    }

    public static ExecutorDecorator newInstance(Optional optional) {
        return new ExecutorDecorator(optional);
    }

    @Override // javax.inject.Provider
    public ExecutorDecorator get() {
        return newInstance((Optional) this.internalDecoratorProvider.get());
    }
}
