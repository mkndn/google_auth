package com.google.android.libraries.performance.primes.persistent;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import javax.inject.Inject;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentStorage {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/persistent/PersistentStorage");
    private final Context application;
    private final Provider sharedPreferences;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public PersistentStorage(Context context, Provider provider) {
        this.application = context;
        this.sharedPreferences = provider;
    }

    private byte[] read(String str) {
        ThreadUtil.ensureBackgroundThread();
        if (DirectBootUtils.isUserUnlocked(this.application)) {
            return Base64.decode(((SharedPreferences) this.sharedPreferences.get()).getString(str, ""), 0);
        }
        return null;
    }

    private boolean write(String str, byte b, byte[] bArr) {
        ThreadUtil.ensureBackgroundThread();
        if (DirectBootUtils.isUserUnlocked(this.application)) {
            byte[] bArr2 = new byte[bArr.length + 1];
            bArr2[0] = b;
            System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
            return ((SharedPreferences) this.sharedPreferences.get()).edit().putString(str, Base64.encodeToString(bArr2, 0)).commit();
        }
        return false;
    }

    public MessageLite readProto(String str, Parser parser) {
        byte[] read = read(str);
        if (read == null || read.length == 0) {
            return null;
        }
        if (read[0] == 1) {
            try {
                return (MessageLite) parser.parseFrom(read, 1, read.length - 1, ExtensionRegistryLite.getGeneratedRegistry());
            } catch (InvalidProtocolBufferException e) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/persistent/PersistentStorage", "readProto", 80, "PersistentStorage.java")).log("failure reading proto");
            }
        } else {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/persistent/PersistentStorage", "readProto", 83, "PersistentStorage.java")).log("wrong header");
        }
        return null;
    }

    public boolean remove(String str) {
        ThreadUtil.ensureBackgroundThread();
        if (DirectBootUtils.isUserUnlocked(this.application)) {
            return ((SharedPreferences) this.sharedPreferences.get()).edit().remove(str).commit();
        }
        return false;
    }

    public boolean writeProto(String str, MessageLite messageLite) {
        return write(str, (byte) 1, ((MessageLite) Preconditions.checkNotNull(messageLite)).toByteArray());
    }
}
