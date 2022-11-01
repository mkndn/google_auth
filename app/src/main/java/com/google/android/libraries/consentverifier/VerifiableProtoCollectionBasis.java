package com.google.android.libraries.consentverifier;

/* compiled from: PG */
/* loaded from: classes.dex */
public class VerifiableProtoCollectionBasis {
    private final ProtoCollectionBasis basis;

    public VerifiableProtoCollectionBasis(ProtoCollectionBasis protoCollectionBasis) {
        this.basis = protoCollectionBasis;
    }

    public boolean collectionBasisMet(CollectionBasisContext collectionBasisContext, byte[] bArr) {
        return CollectionBasisVerifier.collectionBasisMet(collectionBasisContext, this.basis, bArr);
    }

    public String toString() {
        return this.basis.toString();
    }
}
