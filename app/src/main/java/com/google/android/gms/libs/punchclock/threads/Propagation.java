package com.google.android.gms.libs.punchclock.threads;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Propagation {

    /* compiled from: PG */
    /* renamed from: com.google.android.gms.libs.punchclock.threads.Propagation$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static Propagation getInstance() {
            return Holder.instance;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Holder {
        private static final Propagation NOOP;
        private static Propagation instance;

        static {
            Propagation propagation = new Propagation() { // from class: com.google.android.gms.libs.punchclock.threads.Propagation.Holder.1
                @Override // com.google.android.gms.libs.punchclock.threads.Propagation
                public TraceResumer getStrongTrace() {
                    return TraceResumer.NOOP;
                }
            };
            NOOP = propagation;
            instance = propagation;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TraceResumer {
        public static final TraceResumer NOOP = new TraceResumer() { // from class: com.google.android.gms.libs.punchclock.threads.Propagation.TraceResumer.1
            @Override // com.google.android.gms.libs.punchclock.threads.Propagation.TraceResumer
            public void run(Runnable runnable) {
                runnable.run();
            }
        };

        void run(Runnable runnable);
    }

    TraceResumer getStrongTrace();
}
