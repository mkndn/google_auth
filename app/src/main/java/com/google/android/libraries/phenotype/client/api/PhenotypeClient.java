package com.google.android.libraries.phenotype.client.api;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface PhenotypeClient {
    ListenableFuture commitToConfiguration(String str);

    ListenableFuture getConfigurationSnapshot(String str, String str2, String str3);
}
