package com.google.android.libraries.consentverifier;

import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BaseProtoCollectionBasis implements ProtoCollectionBasis {
    private final int featureNameHash;
    private final int javaClassNameHash;
    private final int mappingRes;

    public BaseProtoCollectionBasis(int i, int i2, int i3) {
        this.featureNameHash = i;
        this.javaClassNameHash = i2;
        this.mappingRes = i3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ProtoCollectionBasis) {
            ProtoCollectionBasis protoCollectionBasis = (ProtoCollectionBasis) obj;
            return javaClassNameHash() == protoCollectionBasis.javaClassNameHash() && featureNameHash() == protoCollectionBasis.featureNameHash() && mappingRes() == protoCollectionBasis.mappingRes();
        }
        return false;
    }

    @Override // com.google.android.libraries.consentverifier.ProtoCollectionBasis
    public int featureNameHash() {
        return this.featureNameHash;
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{javaClassNameHash(), featureNameHash(), mappingRes()});
    }

    @Override // com.google.android.libraries.consentverifier.ProtoCollectionBasis
    public int javaClassNameHash() {
        return this.javaClassNameHash;
    }

    @Override // com.google.android.libraries.consentverifier.ProtoCollectionBasis
    public int mappingRes() {
        return this.mappingRes;
    }

    public String toString() {
        int javaClassNameHash = javaClassNameHash();
        int featureNameHash = featureNameHash();
        return "java_hash=" + javaClassNameHash + ",feature_hash=" + featureNameHash + ",res=" + mappingRes();
    }
}
