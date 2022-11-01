package com.google.android.libraries.storage.file.backends;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.libraries.storage.file.common.FileStorageUnavailableException;
import com.google.android.libraries.storage.file.common.LockScope;
import com.google.android.libraries.storage.file.common.MalformedUriException;
import com.google.android.libraries.storage.file.common.internal.Preconditions;
import com.google.android.libraries.storage.file.spi.Backend;
import com.google.android.libraries.storage.file.spi.ForwardingBackend;
import com.google.android.libraries.storage.ttl.MobStoreGc;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidFileBackend extends ForwardingBackend {
    private final AccountManager accountManager;
    private final Backend backend;
    private final Context context;
    private final DirectBootChecker directBootChecker;
    private final MobStoreGc gc;
    private String lazyDpsDataDirPath;
    private final Object lock;
    private final Backend remoteBackend;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private AccountManager accountManager;
        private Backend backend;
        private final Context context;
        private MobStoreGc gc;
        private LockScope lockScope;
        private Backend remoteBackend;

        private Builder(Context context) {
            this.lockScope = new LockScope();
            Preconditions.checkArgument(context != null, "Context cannot be null", new Object[0]);
            this.context = context.getApplicationContext();
        }

        public AndroidFileBackend build() {
            return new AndroidFileBackend(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface DirectBootChecker {
        boolean isUserUnlocked(Context context);
    }

    private AndroidFileBackend(Builder builder) {
        this.lock = new Object();
        this.backend = builder.backend != null ? builder.backend : new JavaFileBackend(builder.lockScope);
        this.context = builder.context;
        this.gc = builder.gc;
        this.remoteBackend = builder.remoteBackend;
        this.accountManager = builder.accountManager;
        this.directBootChecker = AndroidFileBackend$$ExternalSyntheticLambda0.INSTANCE;
    }

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    private String getDpsDataDirPath() {
        String str;
        synchronized (this.lock) {
            if (this.lazyDpsDataDirPath == null) {
                this.lazyDpsDataDirPath = AndroidFileEnvironment.getDeviceProtectedDataDir(this.context).getAbsolutePath();
            }
            str = this.lazyDpsDataDirPath;
        }
        return str;
    }

    private boolean isRemoteAuthority(Uri uri) {
        return (TextUtils.isEmpty(uri.getAuthority()) || this.context.getPackageName().equals(uri.getAuthority())) ? false : true;
    }

    private void throwIfRemoteBackendUnavailable() {
        if (this.remoteBackend == null) {
            throw new FileStorageUnavailableException("Android backend cannot perform remote operations without a remote backend");
        }
    }

    private void throwIfRemoteUri(Uri uri) {
        if (isRemoteAuthority(uri)) {
            throw new IOException("operation is not permitted in other authorities.");
        }
    }

    private void throwIfStorageIsLocked(File file) {
        if (this.directBootChecker.isUserUnlocked(this.context)) {
            return;
        }
        if (!file.getAbsolutePath().startsWith(getDpsDataDirPath())) {
            throw new FileStorageUnavailableException("Cannot access credential-protected data from direct boot");
        }
    }

    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend
    protected Backend delegate() {
        return this.backend;
    }

    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend, com.google.android.libraries.storage.file.spi.Backend
    public boolean exists(Uri uri) {
        if (isRemoteAuthority(uri)) {
            throwIfRemoteBackendUnavailable();
            return this.remoteBackend.exists(uri);
        }
        return super.exists(uri);
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public String name() {
        return "android";
    }

    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend, com.google.android.libraries.storage.file.spi.Backend
    public InputStream openForRead(Uri uri) {
        if (isRemoteAuthority(uri)) {
            throwIfRemoteBackendUnavailable();
            return this.remoteBackend.openForRead(uri);
        }
        return super.openForRead(uri);
    }

    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend
    protected Uri rewriteUri(Uri uri) {
        if (isRemoteAuthority(uri)) {
            throw new MalformedUriException("Operation across authorities is not allowed.");
        }
        return FileUri.builder().fromFile(toFile(uri)).build();
    }

    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend, com.google.android.libraries.storage.file.spi.Backend
    public File toFile(Uri uri) {
        throwIfRemoteUri(uri);
        File file = AndroidUriAdapter.forContext(this.context, this.accountManager).toFile(uri);
        throwIfStorageIsLocked(file);
        return file;
    }
}
