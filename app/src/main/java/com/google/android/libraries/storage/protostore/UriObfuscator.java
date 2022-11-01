package com.google.android.libraries.storage.protostore;

import android.net.Uri;
import com.google.android.libraries.storage.salt.SaltPersister;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class UriObfuscator {
    private final HashFunction hasher;
    private final SaltPersister salter;

    private UriObfuscator(SaltPersister saltPersister, HashFunction hashFunction) {
        this.salter = saltPersister;
        this.hasher = hashFunction;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UriObfuscator create(SaltPersister saltPersister) {
        return new UriObfuscator(saltPersister, Hashing.murmur3_32_fixed());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$obfuscate$0$com-google-android-libraries-storage-protostore-UriObfuscator  reason: not valid java name */
    public /* synthetic */ String m407xd445af37(Uri uri, String str) {
        return this.hasher.hashString(String.valueOf(uri) + str, Charsets.UTF_8).toString();
    }

    public ListenableFuture obfuscate(final Uri uri) {
        return Futures.transform(this.salter.get(), new Function() { // from class: com.google.android.libraries.storage.protostore.UriObfuscator$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return UriObfuscator.this.m407xd445af37(uri, (String) obj);
            }
        }, MoreExecutors.directExecutor());
    }
}
