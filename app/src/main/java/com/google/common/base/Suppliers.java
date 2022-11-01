package com.google.common.base;

import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Suppliers {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class MemoizingSupplier implements Supplier, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier delegate;
        volatile transient boolean initialized;
        transient Object value;

        MemoizingSupplier(Supplier supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        public Object get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        Object obj = this.delegate.get();
                        this.value = obj;
                        this.initialized = true;
                        return obj;
                    }
                }
            }
            return NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            return "Suppliers.memoize(" + (this.initialized ? "<supplier that returned " + this.value + ">" : this.delegate) + ")";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class NonSerializableMemoizingSupplier implements Supplier {
        volatile Supplier delegate;
        volatile boolean initialized;
        Object value;

        NonSerializableMemoizingSupplier(Supplier supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        public Object get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        Supplier supplier = this.delegate;
                        supplier.getClass();
                        Object obj = supplier.get();
                        this.value = obj;
                        this.initialized = true;
                        this.delegate = null;
                        return obj;
                    }
                }
            }
            return NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            Object obj = this.delegate;
            StringBuilder append = new StringBuilder().append("Suppliers.memoize(");
            if (obj == null) {
                obj = "<supplier that returned " + this.value + ">";
            }
            return append.append(obj).append(")").toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class SupplierOfInstance implements Supplier, Serializable {
        private static final long serialVersionUID = 0;
        final Object instance;

        SupplierOfInstance(Object obj) {
            this.instance = obj;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        @Override // com.google.common.base.Supplier
        public Object get() {
            return this.instance;
        }

        public int hashCode() {
            return Objects.hashCode(this.instance);
        }

        public String toString() {
            return "Suppliers.ofInstance(" + this.instance + ")";
        }
    }

    public static Supplier memoize(Supplier supplier) {
        if (!(supplier instanceof NonSerializableMemoizingSupplier) && !(supplier instanceof MemoizingSupplier)) {
            if (supplier instanceof Serializable) {
                return new MemoizingSupplier(supplier);
            }
            return new NonSerializableMemoizingSupplier(supplier);
        }
        return supplier;
    }

    public static Supplier ofInstance(Object obj) {
        return new SupplierOfInstance(obj);
    }
}
