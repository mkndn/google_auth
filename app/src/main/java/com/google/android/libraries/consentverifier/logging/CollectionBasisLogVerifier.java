package com.google.android.libraries.consentverifier.logging;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.LogVerifier;
import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.android.libraries.consentverifier.ProtoCollectionBasis;
import com.google.android.libraries.consentverifier.VerifiableProtoCollectionBasis;
import com.google.android.libraries.consentverifier.flags.Flags;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.protobuf.ByteString;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CollectionBasisLogVerifier implements LogVerifier {
    private final CollectionBasisContext collectionBasisContext;
    private final VerifiableProtoCollectionBasis verifiableBasis;

    protected CollectionBasisLogVerifier(Context context, VerifiableProtoCollectionBasis verifiableProtoCollectionBasis, boolean z, Optional optional) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(verifiableProtoCollectionBasis);
        if (optional.isPresent()) {
            Preconditions.checkNotNull((Executor) optional.get());
        }
        Context applicationContext = context.getApplicationContext();
        CollectionBasisContext.Builder googlerOverridesCheckbox = CollectionBasisContext.builder().setContext(applicationContext).setStacktrace(new Throwable()).setGooglerOverridesCheckbox(z);
        if (optional.isPresent()) {
            googlerOverridesCheckbox.setExecutor((Executor) optional.get());
        }
        this.collectionBasisContext = googlerOverridesCheckbox.build();
        this.verifiableBasis = verifiableProtoCollectionBasis;
    }

    public static LogVerifier newInstance(Context context, ProtoCollectionBasis protoCollectionBasis) {
        return new CollectionBasisLogVerifier(context, new VerifiableProtoCollectionBasis(protoCollectionBasis), false, Optional.absent());
    }

    @Override // com.google.android.gms.clearcut.LogVerifier
    public boolean canLog(ClearcutLogger.LogEventBuilder logEventBuilder, ByteString byteString) {
        boolean collectionBasisMet = this.verifiableBasis.collectionBasisMet(this.collectionBasisContext, byteString.toByteArray());
        if (Flags.enableUsingLogVerifierResult()) {
            return collectionBasisMet;
        }
        return true;
    }

    public String toString() {
        return "CollectionBasisLogVerifier{collectionBasisContext=" + this.collectionBasisContext + ", basis=" + this.verifiableBasis + "}";
    }
}
