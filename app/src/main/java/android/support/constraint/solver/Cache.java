package android.support.constraint.solver;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Cache {
    Pools$Pool arrayRowPool = new Pools$Pool(256) { // from class: android.support.constraint.solver.Pools$SimplePool
        private final Object[] mPool;
        private int mPoolSize;

        /* JADX INFO: Access modifiers changed from: package-private */
        {
            if (r2 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.mPool = new Object[r2];
        }

        @Override // android.support.constraint.solver.Pools$Pool
        public Object acquire() {
            int i = this.mPoolSize;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = this.mPool;
                Object obj = objArr[i2];
                objArr[i2] = null;
                this.mPoolSize = i - 1;
                return obj;
            }
            return null;
        }

        @Override // android.support.constraint.solver.Pools$Pool
        public boolean release(Object obj) {
            int i = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (i < objArr.length) {
                objArr[i] = obj;
                this.mPoolSize = i + 1;
                return true;
            }
            return false;
        }

        @Override // android.support.constraint.solver.Pools$Pool
        public void releaseAll(Object[] objArr, int i) {
            if (i > objArr.length) {
                i = objArr.length;
            }
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr[i2];
                int i3 = this.mPoolSize;
                Object[] objArr2 = this.mPool;
                if (i3 < objArr2.length) {
                    objArr2[i3] = obj;
                    this.mPoolSize = i3 + 1;
                }
            }
        }
    };
    Pools$Pool solverVariablePool = new Pools$Pool(256) { // from class: android.support.constraint.solver.Pools$SimplePool
        private final Object[] mPool;
        private int mPoolSize;

        /* JADX INFO: Access modifiers changed from: package-private */
        {
            if (r2 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.mPool = new Object[r2];
        }

        @Override // android.support.constraint.solver.Pools$Pool
        public Object acquire() {
            int i = this.mPoolSize;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = this.mPool;
                Object obj = objArr[i2];
                objArr[i2] = null;
                this.mPoolSize = i - 1;
                return obj;
            }
            return null;
        }

        @Override // android.support.constraint.solver.Pools$Pool
        public boolean release(Object obj) {
            int i = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (i < objArr.length) {
                objArr[i] = obj;
                this.mPoolSize = i + 1;
                return true;
            }
            return false;
        }

        @Override // android.support.constraint.solver.Pools$Pool
        public void releaseAll(Object[] objArr, int i) {
            if (i > objArr.length) {
                i = objArr.length;
            }
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr[i2];
                int i3 = this.mPoolSize;
                Object[] objArr2 = this.mPool;
                if (i3 < objArr2.length) {
                    objArr2[i3] = obj;
                    this.mPoolSize = i3 + 1;
                }
            }
        }
    };
    SolverVariable[] mIndexedVariables = new SolverVariable[32];
}
