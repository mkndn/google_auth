package com.google.android.libraries.consentverifier;

import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisVerifier {
    public static final boolean DEBUG = false;
    private static CollectionBasisManager collectionBasisManager = CollectionBasisManager.getInstance();
    private static CollectionBasisVerifierDecider collectionBasisVerifierDecider = new CollectionBasisVerifierImpl();

    public static boolean collectionBasisMet(CollectionBasisContext collectionBasisContext, ProtoCollectionBasis protoCollectionBasis, byte[] bArr) {
        return collectionBasisVerifierDecider.collectionBasisMet(collectionBasisContext, protoCollectionBasis, bArr, collectionBasisManager, Optional.absent());
    }
}
