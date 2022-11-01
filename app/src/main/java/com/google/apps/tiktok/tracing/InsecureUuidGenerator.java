package com.google.apps.tiktok.tracing;

import android.os.StrictMode;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
final class InsecureUuidGenerator {
    private static final InsecureUuidGenerator instance;
    private final AtomicLong state;
    private final UUID universallyUniqueXorSeed;

    static {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            instance = new InsecureUuidGenerator(UUID.randomUUID(), new SecureRandom().nextLong());
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    InsecureUuidGenerator(UUID uuid, long j) {
        this.universallyUniqueXorSeed = uuid;
        this.state = new AtomicLong((j ^ 25214903917L) & 281474976710655L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InsecureUuidGenerator getInstance() {
        return instance;
    }

    long nextLong() {
        long j;
        long j2;
        long j3;
        do {
            j = this.state.get();
            long j4 = ((j * 25214903917L) + 11) & 281474976710655L;
            j2 = ((25214903917L * j4) + 11) & 281474976710655L;
            j3 = (((int) (j4 >>> 16)) << 32) + ((int) (j2 >>> 16));
        } while (!this.state.compareAndSet(j, j2));
        return j3;
    }

    public UUID nextUuid() {
        long nextLong = nextLong();
        return new UUID((nextLong & (-61441)) ^ this.universallyUniqueXorSeed.getMostSignificantBits(), (nextLong() >>> 2) ^ this.universallyUniqueXorSeed.getLeastSignificantBits());
    }
}
