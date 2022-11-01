package com.google.android.gms.clearcut;

import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.protobuf.ByteString;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface LogVerifier {
    boolean canLog(ClearcutLogger.LogEventBuilder logEventBuilder, ByteString byteString);
}
