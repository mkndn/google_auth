package com.google.android.gms.clearcut.internal;

import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.wireless.android.play.playlog.proto.LogSamplingRulesProto$LogSamplingRules;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class LogSamplerImpl$$ExternalSyntheticLambda0 implements PhenotypeFlag.BytesConverter {
    public static final /* synthetic */ LogSamplerImpl$$ExternalSyntheticLambda0 INSTANCE = new LogSamplerImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ LogSamplerImpl$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag.BytesConverter
    public final Object fromBytes(byte[] bArr) {
        return LogSamplingRulesProto$LogSamplingRules.parseFrom(bArr);
    }
}
