package com.google.android.libraries.consentverifier;

import android.content.Context;
import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.common.base.Optional;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_CollectionBasisContext extends CollectionBasisContext {
    private final Optional accountNames;
    private final Context context;
    private final Optional executor;
    private final boolean googlerOverridesCheckbox;
    private final Optional stacktrace;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends CollectionBasisContext.Builder {
        private Context context;
        private boolean googlerOverridesCheckbox;
        private byte set$0;
        private Optional accountNames = Optional.absent();
        private Optional stacktrace = Optional.absent();
        private Optional executor = Optional.absent();

        @Override // com.google.android.libraries.consentverifier.CollectionBasisContext.Builder
        public CollectionBasisContext build() {
            if (this.set$0 == 1 && this.context != null) {
                return new AutoValue_CollectionBasisContext(this.context, this.accountNames, this.stacktrace, this.googlerOverridesCheckbox, this.executor);
            }
            StringBuilder sb = new StringBuilder();
            if (this.context == null) {
                sb.append(" context");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" googlerOverridesCheckbox");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.consentverifier.CollectionBasisContext.Builder
        public CollectionBasisContext.Builder setContext(Context context) {
            if (context == null) {
                throw new NullPointerException("Null context");
            }
            this.context = context;
            return this;
        }

        @Override // com.google.android.libraries.consentverifier.CollectionBasisContext.Builder
        public CollectionBasisContext.Builder setExecutor(Executor executor) {
            this.executor = Optional.of(executor);
            return this;
        }

        @Override // com.google.android.libraries.consentverifier.CollectionBasisContext.Builder
        public CollectionBasisContext.Builder setGooglerOverridesCheckbox(boolean z) {
            this.googlerOverridesCheckbox = z;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.android.libraries.consentverifier.CollectionBasisContext.Builder
        public CollectionBasisContext.Builder setStacktrace(Throwable th) {
            this.stacktrace = Optional.of(th);
            return this;
        }
    }

    private AutoValue_CollectionBasisContext(Context context, Optional optional, Optional optional2, boolean z, Optional optional3) {
        this.context = context;
        this.accountNames = optional;
        this.stacktrace = optional2;
        this.googlerOverridesCheckbox = z;
        this.executor = optional3;
    }

    @Override // com.google.android.libraries.consentverifier.CollectionBasisContext
    public Optional accountNames() {
        return this.accountNames;
    }

    @Override // com.google.android.libraries.consentverifier.CollectionBasisContext
    public Context context() {
        return this.context;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CollectionBasisContext) {
            CollectionBasisContext collectionBasisContext = (CollectionBasisContext) obj;
            return this.context.equals(collectionBasisContext.context()) && this.accountNames.equals(collectionBasisContext.accountNames()) && this.stacktrace.equals(collectionBasisContext.stacktrace()) && this.googlerOverridesCheckbox == collectionBasisContext.googlerOverridesCheckbox() && this.executor.equals(collectionBasisContext.executor());
        }
        return false;
    }

    @Override // com.google.android.libraries.consentverifier.CollectionBasisContext
    public Optional executor() {
        return this.executor;
    }

    @Override // com.google.android.libraries.consentverifier.CollectionBasisContext
    public boolean googlerOverridesCheckbox() {
        return this.googlerOverridesCheckbox;
    }

    @Override // com.google.android.libraries.consentverifier.CollectionBasisContext
    public Optional stacktrace() {
        return this.stacktrace;
    }

    public String toString() {
        String valueOf = String.valueOf(this.context);
        String valueOf2 = String.valueOf(this.accountNames);
        String valueOf3 = String.valueOf(this.stacktrace);
        boolean z = this.googlerOverridesCheckbox;
        return "CollectionBasisContext{context=" + valueOf + ", accountNames=" + valueOf2 + ", stacktrace=" + valueOf3 + ", googlerOverridesCheckbox=" + z + ", executor=" + String.valueOf(this.executor) + "}";
    }

    public int hashCode() {
        return ((((((((this.context.hashCode() ^ 1000003) * 1000003) ^ this.accountNames.hashCode()) * 1000003) ^ this.stacktrace.hashCode()) * 1000003) ^ (this.googlerOverridesCheckbox ? 1231 : 1237)) * 1000003) ^ this.executor.hashCode();
    }
}
