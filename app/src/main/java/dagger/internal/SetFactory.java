package dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SetFactory implements Factory {
    private static final Factory EMPTY_FACTORY = InstanceFactory.create(Collections.emptySet());
    private final List collectionProviders;
    private final List individualProviders;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = true;
        private final List collectionProviders;
        private final List individualProviders;

        private Builder(int i, int i2) {
            this.individualProviders = DaggerCollections.presizedList(i);
            this.collectionProviders = DaggerCollections.presizedList(i2);
        }

        public Builder addCollectionProvider(Provider provider) {
            if ($assertionsDisabled || provider != null) {
                this.collectionProviders.add(provider);
                return this;
            }
            throw new AssertionError("Codegen error? Null provider");
        }

        public Builder addProvider(Provider provider) {
            if ($assertionsDisabled || provider != null) {
                this.individualProviders.add(provider);
                return this;
            }
            throw new AssertionError("Codegen error? Null provider");
        }

        public SetFactory build() {
            boolean z = $assertionsDisabled;
            if (z || !DaggerCollections.hasDuplicates(this.individualProviders)) {
                if (z || !DaggerCollections.hasDuplicates(this.collectionProviders)) {
                    return new SetFactory(this.individualProviders, this.collectionProviders);
                }
                throw new AssertionError("Codegen error?  Duplicates in the provider list");
            }
            throw new AssertionError("Codegen error?  Duplicates in the provider list");
        }
    }

    private SetFactory(List list, List list2) {
        this.individualProviders = list;
        this.collectionProviders = list2;
    }

    public static Builder builder(int i, int i2) {
        return new Builder(i, i2);
    }

    public static Factory empty() {
        return EMPTY_FACTORY;
    }

    @Override // javax.inject.Provider
    public Set get() {
        int size = this.individualProviders.size();
        ArrayList arrayList = new ArrayList(this.collectionProviders.size());
        int size2 = this.collectionProviders.size();
        for (int i = 0; i < size2; i++) {
            Collection collection = (Collection) ((Provider) this.collectionProviders.get(i)).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet newHashSetWithExpectedSize = DaggerCollections.newHashSetWithExpectedSize(size);
        int size3 = this.individualProviders.size();
        for (int i2 = 0; i2 < size3; i2++) {
            newHashSetWithExpectedSize.add(Preconditions.checkNotNull(((Provider) this.individualProviders.get(i2)).get()));
        }
        int size4 = arrayList.size();
        for (int i3 = 0; i3 < size4; i3++) {
            for (Object obj : (Collection) arrayList.get(i3)) {
                newHashSetWithExpectedSize.add(Preconditions.checkNotNull(obj));
            }
        }
        return Collections.unmodifiableSet(newHashSetWithExpectedSize);
    }
}
