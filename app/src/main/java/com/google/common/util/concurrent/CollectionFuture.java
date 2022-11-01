package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AggregateFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class CollectionFuture extends AggregateFuture {
    private List values;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ListFuture extends CollectionFuture {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ListFuture(ImmutableCollection immutableCollection, boolean z) {
            super(immutableCollection, z);
            init();
        }

        @Override // com.google.common.util.concurrent.CollectionFuture
        public List combine(List list) {
            ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Present present = (Present) it.next();
                newArrayListWithCapacity.add(present != null ? present.value : null);
            }
            return Collections.unmodifiableList(newArrayListWithCapacity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Present {
        Object value;

        Present(Object obj) {
            this.value = obj;
        }
    }

    CollectionFuture(ImmutableCollection immutableCollection, boolean z) {
        super(immutableCollection, z, true);
        List newArrayListWithCapacity;
        if (immutableCollection.isEmpty()) {
            newArrayListWithCapacity = Collections.emptyList();
        } else {
            newArrayListWithCapacity = Lists.newArrayListWithCapacity(immutableCollection.size());
        }
        for (int i = 0; i < immutableCollection.size(); i++) {
            newArrayListWithCapacity.add(null);
        }
        this.values = newArrayListWithCapacity;
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    final void collectOneValue(int i, Object obj) {
        List list = this.values;
        if (list != null) {
            list.set(i, new Present(obj));
        }
    }

    abstract Object combine(List list);

    @Override // com.google.common.util.concurrent.AggregateFuture
    final void handleAllCompleted() {
        List list = this.values;
        if (list != null) {
            set(combine(list));
        }
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    void releaseResources(AggregateFuture.ReleaseResourcesReason releaseResourcesReason) {
        super.releaseResources(releaseResourcesReason);
        this.values = null;
    }
}
