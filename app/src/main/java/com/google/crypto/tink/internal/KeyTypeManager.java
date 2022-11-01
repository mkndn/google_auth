package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class KeyTypeManager {
    private final Class clazz;
    private final Map factories;
    private final Class firstPrimitiveClass;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class KeyFactory {
        private final Class clazz;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class KeyFormat {
            public Object keyFormat;
            public KeyTemplate.OutputPrefixType outputPrefixType;

            public KeyFormat(Object obj, KeyTemplate.OutputPrefixType outputPrefixType) {
                this.keyFormat = obj;
                this.outputPrefixType = outputPrefixType;
            }
        }

        public KeyFactory(Class cls) {
            this.clazz = cls;
        }

        public Map keyFormats() {
            return Collections.emptyMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SafeVarargs
    public KeyTypeManager(Class cls, PrimitiveFactory... primitiveFactoryArr) {
        this.clazz = cls;
        HashMap hashMap = new HashMap();
        for (PrimitiveFactory primitiveFactory : primitiveFactoryArr) {
            if (!hashMap.containsKey(primitiveFactory.getPrimitiveClass())) {
                hashMap.put(primitiveFactory.getPrimitiveClass(), primitiveFactory);
            } else {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive " + primitiveFactory.getPrimitiveClass().getCanonicalName());
            }
        }
        if (primitiveFactoryArr.length > 0) {
            this.firstPrimitiveClass = primitiveFactoryArr[0].getPrimitiveClass();
        } else {
            this.firstPrimitiveClass = Void.class;
        }
        this.factories = Collections.unmodifiableMap(hashMap);
    }

    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public final Class firstSupportedPrimitiveClass() {
        return this.firstPrimitiveClass;
    }

    public abstract String getKeyType();

    public KeyFactory keyFactory() {
        throw null;
    }

    public final Set supportedPrimitives() {
        return this.factories.keySet();
    }
}
