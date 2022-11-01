package com.google.android.libraries.phenotype.client.stable;

import com.google.protobuf.ByteString;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DefaultExperimentTokenDecorator implements ExperimentTokenDecorator {
    private static volatile DefaultExperimentTokenDecorator instance;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NoOpExperimentTokenDecorator extends DefaultExperimentTokenDecorator {
        @Override // com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecorator
        public void addToken(ByteString byteString, Set set, String str, String str2) {
        }
    }

    public static DefaultExperimentTokenDecorator get() {
        DefaultExperimentTokenDecorator defaultExperimentTokenDecorator = instance;
        if (defaultExperimentTokenDecorator == null) {
            synchronized (DefaultExperimentTokenDecorator.class) {
                if (instance == null) {
                    instance = new NoOpExperimentTokenDecorator();
                }
                defaultExperimentTokenDecorator = instance;
            }
        }
        return defaultExperimentTokenDecorator;
    }
}
