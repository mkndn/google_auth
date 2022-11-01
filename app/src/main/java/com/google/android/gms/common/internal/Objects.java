package com.google.android.gms.common.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Objects {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ToStringHelper {
        private final List mFieldStrings;
        private final Object mInstance;

        private ToStringHelper(Object obj) {
            this.mInstance = Preconditions.checkNotNull(obj);
            this.mFieldStrings = new ArrayList();
        }

        public ToStringHelper add(String str, Object obj) {
            this.mFieldStrings.add(((String) Preconditions.checkNotNull(str)) + "=" + String.valueOf(obj));
            return this;
        }

        public String toString() {
            StringBuilder append = new StringBuilder(100).append(this.mInstance.getClass().getSimpleName()).append('{');
            int size = this.mFieldStrings.size();
            for (int i = 0; i < size; i++) {
                append.append((String) this.mFieldStrings.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }

    public static boolean checkBundlesEquality(Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return bundle == bundle2;
        } else if (bundle.size() != bundle2.size()) {
            return false;
        } else {
            Set<String> keySet = bundle.keySet();
            if (keySet.containsAll(bundle2.keySet())) {
                for (String str : keySet) {
                    if (!equal(bundle.get(str), bundle2.get(str))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj);
    }
}
