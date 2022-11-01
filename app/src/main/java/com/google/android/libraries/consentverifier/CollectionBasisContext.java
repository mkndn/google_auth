package com.google.android.libraries.consentverifier;

import android.content.Context;
import com.google.android.libraries.consentverifier.AutoValue_CollectionBasisContext;
import com.google.common.base.Optional;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CollectionBasisContext {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract CollectionBasisContext build();

        public abstract Builder setContext(Context context);

        public abstract Builder setExecutor(Executor executor);

        public abstract Builder setGooglerOverridesCheckbox(boolean z);

        public abstract Builder setStacktrace(Throwable th);
    }

    public static Builder builder() {
        return new AutoValue_CollectionBasisContext.Builder().setGooglerOverridesCheckbox(false);
    }

    public abstract Optional accountNames();

    public abstract Context context();

    public abstract Optional executor();

    public abstract boolean googlerOverridesCheckbox();

    public abstract Optional stacktrace();
}
