package com.google.android.libraries.performance.primes.metrics.network;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.flogger.GoogleLogger;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.NetworkMetric$CacheStats;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkConnectionInfo;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkEventUsage;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkUsageMetric;
import logs.proto.wireless.performance.mobile.NetworkMetric$PeerDistance;
import logs.proto.wireless.performance.mobile.NetworkMetric$RequestFailedReason;
import logs.proto.wireless.performance.mobile.NetworkMetric$RequestNegotiatedProtocol;
import logs.proto.wireless.performance.mobile.NetworkMetric$RpcStats;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetricCollector {
    private final Provider configsProvider;
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/network/NetworkMetricCollector");
    private static final ImmutableSet WHITELISTED_DOMAINS = ImmutableSet.of((Object) "googleapis.com", (Object) "adwords.google.com", (Object) "m.google.com", (Object) "sandbox.google.com");
    private static final Pattern CONTENT_TYPE_PATTERN = Pattern.compile("(?:[^\\/]*\\/)([^;]*)");
    private static final Pattern PARAMETERS_PATTERN = Pattern.compile("([^\\?]+)(\\?+)");
    private static final Pattern PATH_WITH_SUBDOMAIN_PATTERN = Pattern.compile("((?:https?:\\/\\/|)[a-zA-Z0-9-_\\.]+(?::\\d+)?)(.*)?");
    private static final Pattern ALLOWED_SANITIZED_PATH_PATTERN = Pattern.compile("([a-zA-Z0-9-_]+)");
    private static final Pattern IP_ADDRESS_PATTERN = Pattern.compile("\\b([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})(:\\d{1,5})?\\b");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.metrics.network.NetworkMetricCollector$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$logs$proto$wireless$performance$mobile$NetworkMetric$PeerDistance;

        static {
            int[] iArr = new int[NetworkMetric$PeerDistance.values().length];
            $SwitchMap$logs$proto$wireless$performance$mobile$NetworkMetric$PeerDistance = iArr;
            try {
                iArr[NetworkMetric$PeerDistance.PEER_DISTANCE_IN_PROCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$logs$proto$wireless$performance$mobile$NetworkMetric$PeerDistance[NetworkMetric$PeerDistance.PEER_DISTANCE_INTER_PROCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$logs$proto$wireless$performance$mobile$NetworkMetric$PeerDistance[NetworkMetric$PeerDistance.PEER_DISTANCE_UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public NetworkMetricCollector(Provider provider) {
        this.configsProvider = provider;
    }

    static String extractContentType(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        Matcher matcher = CONTENT_TYPE_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.group(0);
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/network/NetworkMetricCollector", "extractContentType", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SMART_REPLY_PRODUCT_IMPROVEMENT_VALUE, "NetworkMetricCollector.java")).log("contentType extraction failed for %s, skipping logging path", str);
        return null;
    }

    static String getDomainFromUrl(String str) {
        Matcher matcher = PATH_WITH_SUBDOMAIN_PATTERN.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    private static NetworkMetric$RequestNegotiatedProtocol getNegotiationProtocol(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return NetworkMetric$RequestNegotiatedProtocol.REQUEST_NEGOTIATED_PROTOCOL_UNKNOWN;
        }
        if (str.equals("http/1.1")) {
            return NetworkMetric$RequestNegotiatedProtocol.REQUEST_NEGOTIATED_PROTOCOL_HTTP11;
        }
        if (str.equals("spdy/2")) {
            return NetworkMetric$RequestNegotiatedProtocol.REQUEST_NEGOTIATED_PROTOCOL_SPDY2;
        }
        if (str.equals("spdy/3")) {
            return NetworkMetric$RequestNegotiatedProtocol.REQUEST_NEGOTIATED_PROTOCOL_SPDY3;
        }
        if (str.equals("spdy/3.1")) {
            return NetworkMetric$RequestNegotiatedProtocol.REQUEST_NEGOTIATED_PROTOCOL_SPDY31;
        }
        if (str.startsWith("h2")) {
            return NetworkMetric$RequestNegotiatedProtocol.REQUEST_NEGOTIATED_PROTOCOL_SPDY4;
        }
        if (str.equals("quic/1+spdy/3")) {
            return NetworkMetric$RequestNegotiatedProtocol.REQUEST_NEGOTIATED_PROTOCOL_QUIC1_SPDY3;
        }
        if (str.equals("http/2+quic/43")) {
            return NetworkMetric$RequestNegotiatedProtocol.REQUEST_NEGOTIATED_PROTOCOL_HTTP2_QUIC43;
        }
        return NetworkMetric$RequestNegotiatedProtocol.REQUEST_NEGOTIATED_PROTOCOL_UNKNOWN;
    }

    static String getRelativePathFromUrl(String str) {
        Matcher matcher = PATH_WITH_SUBDOMAIN_PATTERN.matcher(str);
        if (matcher.matches()) {
            return matcher.group(2);
        }
        return null;
    }

    private static boolean isOnDevice(NetworkMetric$PeerDistance networkMetric$PeerDistance) {
        if (networkMetric$PeerDistance == null) {
            return false;
        }
        switch (AnonymousClass1.$SwitchMap$logs$proto$wireless$performance$mobile$NetworkMetric$PeerDistance[networkMetric$PeerDistance.ordinal()]) {
            case 1:
            case 2:
                return true;
            case 3:
                return false;
            default:
                return false;
        }
    }

    private static boolean isWhitelistedDomain(String str) {
        UnmodifiableIterator it = WHITELISTED_DOMAINS.iterator();
        while (it.hasNext()) {
            if (str.contains((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static String sanitizeUrl(String str, UrlSanitizer urlSanitizer, boolean z) {
        boolean z2;
        Matcher matcher;
        String trimIpAddress;
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        if (urlSanitizer != null && !z) {
            str = urlSanitizer.sanitizeUrl(str);
        }
        if (!z) {
            String domainFromUrl = getDomainFromUrl(str);
            if (domainFromUrl != null) {
                str = domainFromUrl;
            } else {
                z2 = false;
                matcher = PARAMETERS_PATTERN.matcher(str);
                if (matcher.find()) {
                    str = matcher.group(1);
                    z2 = true;
                }
                trimIpAddress = trimIpAddress(str);
                if (trimIpAddress != null && !trimIpAddress.equals(str)) {
                    z2 = true;
                }
                if (trimIpAddress != null) {
                    Matcher matcher2 = IP_ADDRESS_PATTERN.matcher(trimIpAddress);
                    if (matcher2.find()) {
                        trimIpAddress = matcher2.replaceFirst("<ip>");
                        z2 = true;
                    }
                }
                if (trimIpAddress != null || z2) {
                    return trimIpAddress;
                }
                Matcher matcher3 = ALLOWED_SANITIZED_PATH_PATTERN.matcher(trimIpAddress);
                if (matcher3.find()) {
                    return matcher3.group(1);
                }
                return null;
            }
        }
        z2 = true;
        matcher = PARAMETERS_PATTERN.matcher(str);
        if (matcher.find()) {
        }
        trimIpAddress = trimIpAddress(str);
        if (trimIpAddress != null) {
            z2 = true;
        }
        if (trimIpAddress != null) {
        }
        if (trimIpAddress != null) {
        }
        return trimIpAddress;
    }

    static String trimIpAddress(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = IP_ADDRESS_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.replaceFirst("<ip>");
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SystemHealthProto$SystemHealthMetric getMetric(NetworkEvent... networkEventArr) {
        String str;
        String trimIpAddress;
        String relativePathFromUrl;
        String extractContentType;
        NetworkMetric$NetworkUsageMetric.Builder newBuilder = NetworkMetric$NetworkUsageMetric.newBuilder();
        for (int i = 0; i < networkEventArr.length; i++) {
            NetworkMetric$NetworkEventUsage.Builder newBuilder2 = NetworkMetric$NetworkEventUsage.newBuilder();
            if (networkEventArr[i].bytesUploaded > 0) {
                newBuilder2.setRequestSizeBytes(networkEventArr[i].bytesUploaded);
            }
            if (networkEventArr[i].bytesDownloaded > 0) {
                newBuilder2.setResponseSizeBytes(networkEventArr[i].bytesDownloaded);
            }
            if (networkEventArr[i].timeToResponseDataFinishMs > 0) {
                newBuilder2.setTimeToResponseDataFinishMs((int) networkEventArr[i].timeToResponseDataFinishMs);
            }
            if (networkEventArr[i].timeToResponseHeaderMs > 0) {
                newBuilder2.setTimeToResponseHeaderMs((int) networkEventArr[i].timeToResponseHeaderMs);
            }
            if (networkEventArr[i].httpStatusCode >= 0) {
                newBuilder2.setHttpStatusCode(networkEventArr[i].httpStatusCode);
            }
            if (networkEventArr[i].rpcStatusCode >= 0) {
                newBuilder2.setRpcStats((NetworkMetric$RpcStats) NetworkMetric$RpcStats.newBuilder().setRpcStatusCode(networkEventArr[i].rpcStatusCode).build());
            }
            if (networkEventArr[i].contentType != null && (extractContentType = extractContentType(networkEventArr[i].contentType)) != null) {
                newBuilder2.setContentType(extractContentType);
            }
            newBuilder2.setRequestNegotiatedProtocol(getNegotiationProtocol(networkEventArr[i].negotiationProtocol));
            UrlSanitizer urlSanitizer = ((NetworkConfigurations) this.configsProvider.get()).getUrlSanitizer();
            String str2 = networkEventArr[i].requestPath;
            if (str2 == null) {
                str = null;
            } else if (networkEventArr[i].isConstantRpcPath) {
                str = networkEventArr[i].domainPath;
                newBuilder2.setConstantRpcPath(str2);
            } else {
                String domainFromUrl = getDomainFromUrl(str2);
                if (((NetworkConfigurations) this.configsProvider.get()).getEnableUrlAutoSanitization() && (isWhitelistedDomain(str2) || isOnDevice(networkEventArr[i].serverDistance))) {
                    String sanitizeUrl = sanitizeUrl(str2, urlSanitizer, true);
                    if (sanitizeUrl != null && (relativePathFromUrl = getRelativePathFromUrl(sanitizeUrl)) != null) {
                        newBuilder2.setRpcPath(relativePathFromUrl);
                    }
                } else {
                    String sanitizeUrl2 = sanitizeUrl(str2, urlSanitizer, false);
                    if (sanitizeUrl2 != null) {
                        newBuilder2.setRequestPath(sanitizeUrl2);
                    }
                }
                str = domainFromUrl;
            }
            if (str != null && (trimIpAddress = trimIpAddress(str)) != null) {
                newBuilder2.setDomainPath(trimIpAddress);
            }
            if (networkEventArr[i].processStats != null) {
                newBuilder2.setProcessStats(networkEventArr[i].processStats);
            }
            newBuilder2.setNetworkingStack((NetworkMetric$NetworkEventUsage.NetworkingStack) Optional.fromNullable(NetworkMetric$NetworkEventUsage.NetworkingStack.forNumber(networkEventArr[i].getNetworkingStackType())).or(NetworkMetric$NetworkEventUsage.NetworkingStack.UNKNOWN));
            NetworkMetric$NetworkConnectionInfo.Builder newBuilder3 = NetworkMetric$NetworkConnectionInfo.newBuilder();
            if (networkEventArr[i].networkType != null) {
                newBuilder3.setNetworkType(networkEventArr[i].networkType);
            }
            newBuilder2.setNetworkConnectionInfo(newBuilder3);
            if (networkEventArr[i].serverDistance != null) {
                newBuilder2.setServerDistance(networkEventArr[i].serverDistance);
            }
            if (networkEventArr[i].metricExtension != null) {
                newBuilder2.setMetricExtension(networkEventArr[i].metricExtension);
            }
            if (networkEventArr[i].startTimeMs > 0) {
                newBuilder2.setStartTimeMs(networkEventArr[i].startTimeMs);
            }
            if (networkEventArr[i].cacheLookupCount > 0) {
                NetworkMetric$CacheStats.Builder lookupCount = NetworkMetric$CacheStats.newBuilder().setLookupCount(networkEventArr[i].cacheLookupCount);
                if (networkEventArr[i].cacheHitCount > 0) {
                    lookupCount.setHitCount(networkEventArr[i].cacheHitCount);
                }
                newBuilder2.setCacheStats((NetworkMetric$CacheStats) lookupCount.build());
            }
            newBuilder2.setRequestStatus(networkEventArr[i].requestStatus).setRequestFailedReason(NetworkMetric$RequestFailedReason.forNumber(networkEventArr[i].requestFailedReason)).setQuicDetailedErrorCode(networkEventArr[i].quicDetailedErrorCode).setRetryCount(networkEventArr[i].getRetryCount());
            newBuilder.addNetworkEventUsage(newBuilder2);
        }
        SystemHealthProto$SystemHealthMetric.Builder networkUsageMetric = SystemHealthProto$SystemHealthMetric.newBuilder().setNetworkUsageMetric(newBuilder);
        try {
            Optional metricExtensionProvider = ((NetworkConfigurations) this.configsProvider.get()).getMetricExtensionProvider();
            if (metricExtensionProvider.isPresent()) {
                Optional metricExtension = ((NetworkMetricExtensionProvider) metricExtensionProvider.get()).getMetricExtension();
                if (metricExtension.isPresent()) {
                    networkUsageMetric.setMetricExtension((ExtensionMetric$MetricExtension) metricExtension.get());
                }
            }
        } catch (Exception e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/network/NetworkMetricCollector", "getMetric", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNICORN_SETUP_VALUE, "NetworkMetricCollector.java")).log("Exception while getting network metric extension!");
        }
        return (SystemHealthProto$SystemHealthMetric) networkUsageMetric.build();
    }
}
