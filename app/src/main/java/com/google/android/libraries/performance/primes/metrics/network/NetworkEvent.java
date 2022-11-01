package com.google.android.libraries.performance.primes.metrics.network;

import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.clock.impl.SystemClockImpl;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkConnectionInfo;
import logs.proto.wireless.performance.mobile.NetworkMetric$PeerDistance;
import logs.proto.wireless.performance.mobile.NetworkMetric$RequestStatus;
import logs.proto.wireless.performance.mobile.ProcessProto$AndroidProcessStats;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkEvent {
    private static Clock clock = new SystemClockImpl();
    int bytesDownloaded;
    int bytesUploaded;
    int cacheHitCount;
    int cacheLookupCount;
    String contentType;
    final String domainPath;
    int httpStatusCode;
    final boolean isConstantRpcPath;
    ExtensionMetric$MetricExtension metricExtension;
    String negotiationProtocol;
    NetworkMetric$NetworkConnectionInfo.NetworkType networkType;
    ProcessProto$AndroidProcessStats processStats;
    int quicDetailedErrorCode;
    int requestFailedReason;
    final String requestPath;
    NetworkMetric$RequestStatus requestStatus;
    int rpcStatusCode;
    NetworkMetric$PeerDistance serverDistance;
    final long startTimeMs;
    long timeToResponseDataFinishMs;
    long timeToResponseHeaderMs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNetworkingStackType() {
        throw null;
    }

    public int getRetryCount() {
        throw null;
    }
}
