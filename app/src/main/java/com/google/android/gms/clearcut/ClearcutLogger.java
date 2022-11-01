package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiPrivileged;
import com.google.android.gms.clearcut.internal.ClearcutLoggerClientImpl;
import com.google.android.gms.clearcut.internal.LogSamplerImpl;
import com.google.android.gms.clearcut.internal.PlayLoggerContext;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Supplier;
import com.google.common.logging.ClientVisualElements$ClientVisualElementsProto;
import com.google.prod.mobile.mobilespec.MobilespecId;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$LogEvent;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$QosTierConfiguration$QosTier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearcutLogger {
    @Deprecated
    public static final Api API;
    public static final Api.AbstractClientBuilder CLIENT_BUILDER;
    public static final Api.ClientKey CLIENT_KEY;
    private static final byte[][] EMPTY_BYTES;
    private static final ExperimentTokens[] EMPTY_EXPERIMENT_TOKENS;
    private static final String[] EMPTY_STRING;
    private static volatile int packageVersionCode;
    private static final List processGlobalEventModifiers;
    private MobilespecId appMobilespecId;
    private final Clock clock;
    private final Context context;
    private final List eventModifiers;
    private final LogSampler logSampler;
    private final String logSourceName;
    final ClearcutLoggerApi loggerApi;
    final ClearcutLoggerApiPrivileged loggerApiPrivileged;
    private final TimeZoneOffsetProvider offsetProvider;
    private final String packageName;
    private final EnumSet piiLevelSet;
    private ClientAnalytics$QosTierConfiguration$QosTier qosTier;
    private final boolean scrubMccMnc;
    private final String uploadAccountName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private ClearcutLoggerApi clearcutLoggerApi;
        private Clock clock;
        private final Context context;
        private Supplier logErrorQueueEnabledSupplier;
        private LogSampler logSampler;
        private final String logSourceName;
        private TimeZoneOffsetProvider offsetProvider;
        private EnumSet piiLevelSet;
        private ClientAnalytics$QosTierConfiguration$QosTier qosTier;
        private boolean scrubMccMnc;
        private String uploadAccountName;

        private Builder(Context context, String str) {
            this.logErrorQueueEnabledSupplier = ClearcutLogger$Builder$$ExternalSyntheticLambda0.INSTANCE;
            this.piiLevelSet = PIILevel.noRestrictions;
            this.scrubMccMnc = false;
            this.context = (Context) Preconditions.checkNotNull(context);
            this.logSourceName = Preconditions.checkNotEmpty(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Boolean lambda$new$0() {
            return false;
        }

        public ClearcutLogger build() {
            ClearcutLoggerApi clearcutLoggerApi;
            ClearcutLogger.checkPIISettings(this.piiLevelSet, this.uploadAccountName);
            Context context = this.context;
            String str = this.logSourceName;
            String str2 = this.uploadAccountName;
            EnumSet enumSet = this.piiLevelSet;
            boolean z = this.scrubMccMnc;
            ClearcutLoggerApi clearcutLoggerApi2 = this.clearcutLoggerApi;
            if (clearcutLoggerApi2 == null) {
                clearcutLoggerApi = ClearcutLoggerApiImpl.getInstance(context, this.logErrorQueueEnabledSupplier);
            } else {
                clearcutLoggerApi = clearcutLoggerApi2;
            }
            Clock clock = this.clock;
            TimeZoneOffsetProvider timeZoneOffsetProvider = this.offsetProvider;
            LogSampler logSampler = this.logSampler;
            if (logSampler == null) {
                logSampler = new LogSamplerImpl(this.context);
            }
            ClearcutLogger clearcutLogger = new ClearcutLogger(context, str, str2, enumSet, z, clearcutLoggerApi, clock, timeZoneOffsetProvider, logSampler);
            ClientAnalytics$QosTierConfiguration$QosTier clientAnalytics$QosTierConfiguration$QosTier = this.qosTier;
            if (clientAnalytics$QosTierConfiguration$QosTier != null) {
                clearcutLogger.setQosTier(clientAnalytics$QosTierConfiguration$QosTier);
            }
            return clearcutLogger;
        }

        Builder setPiiLevelSet(EnumSet enumSet) {
            this.piiLevelSet = (EnumSet) Preconditions.checkNotNull(enumSet);
            ClearcutLogger.checkPIILevelSet(enumSet);
            return this;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface EventModifier {
        LogEventBuilder apply(LogEventBuilder logEventBuilder);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class LogEventBuilder {
        private boolean addPhenotypeExperimentTokens;
        private ClientVisualElements$ClientVisualElementsProto clientVisualElements;
        private ArrayList experimentIds;
        private ArrayList experimentTokensBytes;
        private ArrayList experimentTokensParcelables;
        boolean isConsumed;
        private final ClientAnalytics$LogEvent.Builder logEvent;
        private String logSourceName;
        private LogVerifier logVerifier;
        private final ClearcutLogger logger;
        private ArrayList mendelPackages;
        private Set mendelPackagesToFilter;
        private ClientAnalytics$QosTierConfiguration$QosTier qosTier;
        private final Supplier sourceExtensionByteStringProvider;
        private ArrayList testCodes;
        private String uploadAccountName;

        private LogEventBuilder(ClearcutLogger clearcutLogger, ByteString byteString, Supplier supplier) {
            ClientAnalytics$LogEvent.Builder newBuilder = ClientAnalytics$LogEvent.newBuilder();
            this.logEvent = newBuilder;
            this.isConsumed = false;
            this.clientVisualElements = null;
            this.testCodes = null;
            this.mendelPackages = null;
            this.experimentIds = null;
            this.experimentTokensParcelables = null;
            this.experimentTokensBytes = null;
            this.addPhenotypeExperimentTokens = true;
            this.logger = clearcutLogger;
            this.logSourceName = clearcutLogger.logSourceName;
            this.uploadAccountName = clearcutLogger.uploadAccountName;
            this.qosTier = clearcutLogger.qosTier;
            newBuilder.setEventTimeMs(clearcutLogger.clock.currentTimeMillis());
            newBuilder.setTimezoneOffsetSeconds(clearcutLogger.offsetProvider.getOffsetSeconds(newBuilder.getEventTimeMs()));
            if (DirectBootUtils.isDirectBoot(clearcutLogger.context)) {
                newBuilder.setInDirectBootMode(true);
            }
            if (clearcutLogger.clock.elapsedRealtime() != 0) {
                newBuilder.setEventUptimeMs(clearcutLogger.clock.elapsedRealtime());
            }
            if (byteString != null) {
                newBuilder.setSourceExtension(byteString);
            }
            this.sourceExtensionByteStringProvider = supplier;
        }

        public LogEventBuilder addExperimentIds(int[] iArr) {
            if (this.logger.isDeidentified()) {
                throw new IllegalArgumentException("addExperimentIds forbidden on deidentified logger");
            }
            if (iArr != null && iArr.length != 0) {
                if (this.experimentIds == null) {
                    this.experimentIds = new ArrayList();
                }
                for (int i : iArr) {
                    this.experimentIds.add(Integer.valueOf(i));
                }
                return this;
            }
            return this;
        }

        public LogEventBuilder addMendelPackage(String str) {
            if (this.logger.isDeidentified()) {
                throw new IllegalArgumentException("addMendelPackage forbidden on deidentified logger");
            }
            if (this.mendelPackages == null) {
                this.mendelPackages = new ArrayList();
            }
            this.mendelPackages.add(str);
            return this;
        }

        public int getEventCode() {
            return this.logEvent.getEventCode();
        }

        public LogEventParcelable getLogEventParcelable() {
            String[] strArr;
            byte[][] bArr;
            ExperimentTokens[] experimentTokensArr;
            String[] strArr2;
            PlayLoggerContext playLoggerContext = new PlayLoggerContext(this.logger.packageName, ClearcutLogger.getMemoizedPackageVersionCode(this.logger.context), this.logSourceName, this.uploadAccountName, this.qosTier, this.logger.appMobilespecId != null ? Integer.valueOf(this.logger.appMobilespecId.getCode()) : null, this.logger.scrubMccMnc, this.logger.piiLevelSet);
            ClientAnalytics$LogEvent clientAnalytics$LogEvent = (ClientAnalytics$LogEvent) this.logEvent.build();
            Supplier supplier = this.sourceExtensionByteStringProvider;
            ClientVisualElements$ClientVisualElementsProto clientVisualElements$ClientVisualElementsProto = this.clientVisualElements;
            int[] intArray = ClearcutLogger.toIntArray(this.testCodes);
            ArrayList arrayList = this.mendelPackages;
            if (arrayList != null) {
                strArr = (String[]) arrayList.toArray(ClearcutLogger.EMPTY_STRING);
            } else {
                strArr = null;
            }
            int[] intArray2 = ClearcutLogger.toIntArray(this.experimentIds);
            ArrayList arrayList2 = this.experimentTokensBytes;
            if (arrayList2 != null) {
                bArr = (byte[][]) arrayList2.toArray(ClearcutLogger.EMPTY_BYTES);
            } else {
                bArr = null;
            }
            ArrayList arrayList3 = this.experimentTokensParcelables;
            if (arrayList3 != null) {
                experimentTokensArr = (ExperimentTokens[]) arrayList3.toArray(ClearcutLogger.EMPTY_EXPERIMENT_TOKENS);
            } else {
                experimentTokensArr = null;
            }
            boolean z = this.addPhenotypeExperimentTokens;
            Set set = this.mendelPackagesToFilter;
            if (set != null) {
                strArr2 = (String[]) set.toArray(ClearcutLogger.EMPTY_STRING);
            } else {
                strArr2 = null;
            }
            return new LogEventParcelable(playLoggerContext, clientAnalytics$LogEvent, supplier, clientVisualElements$ClientVisualElementsProto, intArray, strArr, intArray2, bArr, experimentTokensArr, z, strArr2);
        }

        public String getLogSourceName() {
            return this.logSourceName;
        }

        public LogVerifier getLogVerifier() {
            return this.logVerifier;
        }

        public ClearcutLogger getLogger() {
            return this.logger;
        }

        public void log() {
            logAsync();
        }

        public PendingResult logAsync() {
            if (this.isConsumed) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.isConsumed = true;
            return this.logger.loggerApi.logEvent(this);
        }

        public Task logAsyncTask() {
            return PendingResultUtil.toVoidTask(logAsync());
        }

        public LogEventBuilder setEventCode(int i) {
            this.logEvent.setEventCode(i);
            return this;
        }

        @Deprecated
        public LogEventBuilder setLogVerifier(LogVerifier logVerifier) {
            this.logVerifier = logVerifier;
            return this;
        }

        public LogEventBuilder setUploadAccountName(String str) {
            if (!this.logger.getPIILevelSet().contains(PIILevel.ACCOUNT_NAME)) {
                throw new IllegalStateException("setUploadAccountName forbidden on deidentified logger");
            }
            this.uploadAccountName = str;
            return this;
        }

        public LogEventBuilder setZwiebackCookieOverride(String str) {
            if (this.logger.isDeidentified()) {
                throw new IllegalStateException("setZwiebackCookieOverride forbidden on deidentified logger");
            }
            this.logEvent.setZwiebackCookieOverride(str);
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("ClearcutLogger.LogEventBuilder[");
            sb.append("uploadAccount: ");
            sb.append(this.uploadAccountName);
            sb.append(", logSourceName: ");
            sb.append(this.logSourceName);
            sb.append(", qosTier: ");
            sb.append(this.qosTier.getNumber());
            sb.append(", SourceExtensionByteStringProvider: ");
            sb.append(this.sourceExtensionByteStringProvider);
            sb.append(", veMessage: ");
            sb.append(this.clientVisualElements);
            sb.append(", testCodes: ");
            ArrayList arrayList = this.testCodes;
            sb.append(arrayList != null ? ClearcutLogger.join(arrayList) : null);
            sb.append(", mendelPackages: ");
            ArrayList arrayList2 = this.mendelPackages;
            sb.append(arrayList2 != null ? ClearcutLogger.join(arrayList2) : null);
            sb.append(", experimentIds: ");
            ArrayList arrayList3 = this.experimentIds;
            sb.append(arrayList3 != null ? ClearcutLogger.join(arrayList3) : null);
            sb.append(", experimentTokens: ");
            ArrayList arrayList4 = this.experimentTokensParcelables;
            sb.append(arrayList4 != null ? ClearcutLogger.join(arrayList4) : null);
            sb.append(", experimentTokensBytes: ");
            List stringify = ClearcutLogger.stringify(this.experimentTokensBytes);
            sb.append(stringify != null ? ClearcutLogger.join(stringify) : null);
            sb.append(", addPhenotype: ");
            sb.append(this.addPhenotypeExperimentTokens);
            sb.append(", logVerifier: ");
            sb.append(this.logVerifier);
            sb.append("]");
            return sb.toString();
        }

        public LogEventBuilder applyEventModifiers() {
            LogEventBuilder logEventBuilder = this;
            for (EventModifier eventModifier : this.logger.eventModifiers) {
                logEventBuilder = eventModifier.apply(logEventBuilder);
                if (logEventBuilder == null) {
                    return null;
                }
            }
            for (EventModifier eventModifier2 : ClearcutLogger.processGlobalEventModifiers) {
                logEventBuilder = eventModifier2.apply(logEventBuilder);
                if (logEventBuilder == null) {
                    return null;
                }
            }
            return logEventBuilder;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface LogSampler {
        boolean shouldLog(String str, int i, int i2);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class TimeZoneOffsetProvider {
        public long getOffsetSeconds(long j) {
            return TimeZone.getDefault().getOffset(j) / 1000;
        }
    }

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY = clientKey;
        Api.AbstractClientBuilder abstractClientBuilder = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.clearcut.ClearcutLogger.1
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public ClearcutLoggerClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new ClearcutLoggerClientImpl(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
            }
        };
        CLIENT_BUILDER = abstractClientBuilder;
        API = new Api("ClearcutLogger.API", abstractClientBuilder, clientKey);
        EMPTY_EXPERIMENT_TOKENS = new ExperimentTokens[0];
        EMPTY_STRING = new String[0];
        EMPTY_BYTES = new byte[0];
        processGlobalEventModifiers = new CopyOnWriteArrayList();
        packageVersionCode = -1;
    }

    private ClearcutLogger(Context context, String str, String str2, EnumSet enumSet, boolean z, ClearcutLoggerApi clearcutLoggerApi, Clock clock, TimeZoneOffsetProvider timeZoneOffsetProvider, LogSampler logSampler) {
        this.eventModifiers = new CopyOnWriteArrayList();
        this.appMobilespecId = null;
        this.qosTier = ClientAnalytics$QosTierConfiguration$QosTier.DEFAULT;
        checkPIISettings(enumSet, str2);
        this.context = context.getApplicationContext();
        this.packageName = context.getPackageName();
        this.logSourceName = str;
        this.uploadAccountName = str2;
        this.piiLevelSet = enumSet;
        this.scrubMccMnc = z;
        this.loggerApi = clearcutLoggerApi;
        this.clock = clock == null ? DefaultClock.getInstance() : clock;
        this.offsetProvider = timeZoneOffsetProvider == null ? new TimeZoneOffsetProvider() : timeZoneOffsetProvider;
        this.qosTier = ClientAnalytics$QosTierConfiguration$QosTier.DEFAULT;
        this.logSampler = logSampler;
        if (clearcutLoggerApi instanceof ClearcutLoggerApiPrivileged) {
            this.loggerApiPrivileged = (ClearcutLoggerApiPrivileged) clearcutLoggerApi;
        } else {
            this.loggerApiPrivileged = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkPIILevelSet(EnumSet enumSet) {
        if (!enumSet.equals(PIILevel.zwiebackOnly) && !enumSet.equals(PIILevel.noRestrictions) && !enumSet.equals(PIILevel.deidentified)) {
            throw new IllegalArgumentException("piiLevelSet must be one of ZWIEBACK_ONLY, NO_RESTRICTIONS, or PIILevel.DEIDENTIFIED");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkPIISettings(EnumSet enumSet, String str) {
        if (!enumSet.contains(PIILevel.ACCOUNT_NAME)) {
            Preconditions.checkArgument(str == null, "Upload account name cannot be used with a deidentified or pseudonymous logger.");
        }
        checkPIILevelSet(enumSet);
    }

    public static ClearcutLogger deidentifiedLogger(Context context, String str) {
        return newDeidentifiedLoggerBuilder(context, str).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getMemoizedPackageVersionCode(Context context) {
        if (packageVersionCode == -1) {
            synchronized (ClearcutLogger.class) {
                if (packageVersionCode == -1) {
                    try {
                        packageVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.wtf("ClearcutLogger", "This can't happen.", e);
                    }
                }
            }
        }
        return packageVersionCode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String join(Iterable iterable) {
        if (iterable == null) {
            return "null";
        }
        return Joiner.on(", ").join(iterable);
    }

    public static Builder newBuilder(Context context, String str) {
        return new Builder(context, str);
    }

    public static Builder newDeidentifiedLoggerBuilder(Context context, String str) {
        return newBuilder(context, str).setPiiLevelSet(PIILevel.deidentified);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List stringify(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Arrays.toString((byte[]) it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int[] toIntArray(ArrayList arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = ((Integer) it.next()).intValue();
            i++;
        }
        return iArr;
    }

    public LogSampler getLogSampler() {
        return this.logSampler;
    }

    public EnumSet getPIILevelSet() {
        return this.piiLevelSet;
    }

    public boolean isDeidentified() {
        return this.piiLevelSet.equals(PIILevel.deidentified);
    }

    LogEventBuilder newEvent(Supplier supplier) {
        return new LogEventBuilder(null, supplier);
    }

    public ClearcutLogger setQosTier(ClientAnalytics$QosTierConfiguration$QosTier clientAnalytics$QosTierConfiguration$QosTier) {
        if (clientAnalytics$QosTierConfiguration$QosTier == null) {
            clientAnalytics$QosTierConfiguration$QosTier = ClientAnalytics$QosTierConfiguration$QosTier.DEFAULT;
        }
        this.qosTier = clientAnalytics$QosTierConfiguration$QosTier;
        return this;
    }

    @Deprecated
    public LogEventBuilder newEvent(final MessageLite messageLite) {
        messageLite.getClass();
        return newEvent(new Supplier() { // from class: com.google.android.gms.clearcut.ClearcutLogger$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return MessageLite.this.toByteString();
            }
        });
    }
}
