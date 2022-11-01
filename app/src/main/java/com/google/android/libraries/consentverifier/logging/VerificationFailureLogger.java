package com.google.android.libraries.consentverifier.logging;

import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.android.libraries.consentverifier.MessageContext;
import com.google.android.libraries.consentverifier.flags.Flags;
import com.google.common.collect.ImmutableList;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import com.google.protos.collection_basis_verifier.logging.VerificationFailureEnum$VerificationFailure;
import com.google.protos.collection_basis_verifier.logging.VerificationFailureLogOuterClass$VerificationFailureLog;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class VerificationFailureLogger {
    private final AppInfoHelper appInfoHelper;
    private final Clock clock;
    private final CollectionBasisContext context;
    private final int dataLength;
    private final int featureHash;
    private final Map lastLoggingTimes;
    private final CollectionBasisLogger logger;
    private final int messageId;
    private final ArrayDeque messageStack;

    public VerificationFailureLogger(AppInfoHelper appInfoHelper, Map map, Clock clock, CollectionBasisLogger collectionBasisLogger, CollectionBasisContext collectionBasisContext, int i, int i2, int i3, ArrayDeque arrayDeque) {
        this.appInfoHelper = appInfoHelper;
        this.lastLoggingTimes = map;
        this.clock = clock;
        this.logger = collectionBasisLogger;
        this.context = collectionBasisContext;
        this.messageId = i;
        this.featureHash = i2;
        this.dataLength = i3;
        this.messageStack = arrayDeque;
    }

    private ImmutableList getFieldPath() {
        ImmutableList.Builder builder = ImmutableList.builder();
        Iterator descendingIterator = this.messageStack.descendingIterator();
        while (descendingIterator.hasNext()) {
            builder.add((Object) Long.valueOf(((MessageContext) descendingIterator.next()).fieldNumber));
        }
        return builder.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005e A[Catch: all -> 0x0069, TryCatch #0 {, blocks: (B:14:0x0039, B:18:0x004f, B:24:0x005e, B:25:0x0067), top: B:30:0x0039 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean shouldLogAndUpdate(VerificationFailureLogOuterClass$VerificationFailureLog verificationFailureLogOuterClass$VerificationFailureLog) {
        boolean z = false;
        if (Flags.enableLoggingFieldNotAnnotated() || verificationFailureLogOuterClass$VerificationFailureLog.getVerificationFailure().getNumber() != 6) {
            if (Flags.enableLoggingUcNeverCollect() || verificationFailureLogOuterClass$VerificationFailureLog.getUseCase() != AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_NEVER_COLLECT) {
                VerificationFailureKey create = VerificationFailureKey.create(Long.valueOf(verificationFailureLogOuterClass$VerificationFailureLog.getProtoId()), verificationFailureLogOuterClass$VerificationFailureLog.getVerificationFailure());
                long failureLogCooldownPeriodMs = Flags.failureLogCooldownPeriodMs();
                synchronized (this.lastLoggingTimes) {
                    Long l = (Long) this.lastLoggingTimes.get(create);
                    long currentTimeMillis = this.clock.currentTimeMillis();
                    if (l != null && failureLogCooldownPeriodMs > 0 && l.longValue() + failureLogCooldownPeriodMs >= currentTimeMillis) {
                        if (z) {
                            this.lastLoggingTimes.put(create, Long.valueOf(currentTimeMillis));
                        }
                    }
                    z = true;
                    if (z) {
                    }
                }
                return z;
            }
            return false;
        }
        return false;
    }

    private String stackTraceAsString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        int length = stringWriter2.length();
        long maxStackTraceSize = Flags.maxStackTraceSize();
        if (maxStackTraceSize < length && maxStackTraceSize >= 0) {
            length = (int) maxStackTraceSize;
        }
        return stringWriter2.substring(0, length);
    }

    public VerificationFailureLogOuterClass$VerificationFailureLog.Builder build(VerificationFailureEnum$VerificationFailure verificationFailureEnum$VerificationFailure) {
        return VerificationFailureLogOuterClass$VerificationFailureLog.newBuilder().setAppName(this.context.context().getPackageName()).setAppVersionCode(this.appInfoHelper.getVersionCode(this.context.context())).setProtoId(this.messageId).setFeatureId(this.featureHash).setDataLength(this.dataLength).addAllFieldPath(getFieldPath()).setVerificationFailure(verificationFailureEnum$VerificationFailure);
    }

    public void log(VerificationFailureLogOuterClass$VerificationFailureLog.Builder builder) {
        if (!builder.hasVerificationFailure()) {
            builder.setVerificationFailure(VerificationFailureEnum$VerificationFailure.VF_UNKNOWN);
        }
        VerificationFailureLogOuterClass$VerificationFailureLog verificationFailureLogOuterClass$VerificationFailureLog = (VerificationFailureLogOuterClass$VerificationFailureLog) builder.setStackTrace(stackTraceAsString((Throwable) this.context.stacktrace().or(new Throwable()))).build();
        if (shouldLogAndUpdate(verificationFailureLogOuterClass$VerificationFailureLog)) {
            this.logger.logEvent(verificationFailureLogOuterClass$VerificationFailureLog);
        }
    }
}
