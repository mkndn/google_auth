package googledata.experiments.mobile.gmscore.auth_account.features;

import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.protos.experiments.proto.TypedFeatures$StringListParam;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class GetTokenRefactorFlagsImpl$$ExternalSyntheticLambda0 implements PhenotypeFlag.BytesConverter {
    public static final /* synthetic */ GetTokenRefactorFlagsImpl$$ExternalSyntheticLambda0 INSTANCE = new GetTokenRefactorFlagsImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ GetTokenRefactorFlagsImpl$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag.BytesConverter
    public final Object fromBytes(byte[] bArr) {
        return TypedFeatures$StringListParam.parseFrom(bArr);
    }
}
