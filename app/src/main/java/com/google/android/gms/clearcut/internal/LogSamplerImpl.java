package com.google.android.gms.clearcut.internal;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.phenotype.Phenotype;
import com.google.android.gsf.Gservices;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.wireless.android.play.playlog.proto.LogSamplingRulesProto$LogSamplingRules;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LogSamplerImpl implements ClearcutLogger.LogSampler {
    private final Context context;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final PhenotypeFlag.Factory FLAG_FACTORY = new PhenotypeFlag.Factory(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).withGservicePrefix("gms:playlog:service:samplingrules_").withPhenotypePrefix("LogSamplingRulesV2__");
    private static final ConcurrentHashMap flagMap = new ConcurrentHashMap();
    static Boolean hasGserviceReadPermission = null;
    static Long androidId = null;

    public LogSamplerImpl(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        if (applicationContext != null) {
            PhenotypeFlag.maybeInit(applicationContext);
        }
    }

    private static boolean canReadGservice(Context context) {
        if (hasGserviceReadPermission == null) {
            hasGserviceReadPermission = Boolean.valueOf(Wrappers.packageManager(context).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return hasGserviceReadPermission.booleanValue();
    }

    static long getFingerprint(String str, long j) {
        if (str != null && !str.isEmpty()) {
            byte[] bytes = str.getBytes(UTF_8);
            ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
            allocate.put(bytes);
            allocate.putLong(j);
            return FarmHashFingerprint64.hashBytes(allocate.array());
        }
        return FarmHashFingerprint64.hashBytes(ByteBuffer.allocate(8).putLong(j).array());
    }

    static boolean shouldAccept(long j, long j2, long j3) {
        if (j2 >= 0 && j3 > 0 && UnsignedMod.mod(j, j3) >= j2) {
            return false;
        }
        return true;
    }

    long getAndroidId(Context context) {
        if (DirectBootUtils.isDirectBoot(context)) {
            return 0L;
        }
        if (androidId == null) {
            if (context == null) {
                return 0L;
            }
            if (canReadGservice(context)) {
                androidId = Long.valueOf(Gservices.getLong(context.getContentResolver(), "android_id", 0L));
            } else {
                androidId = 0L;
            }
        }
        return androidId.longValue();
    }

    List getMatchingSamplingRules(String str) {
        if (this.context == null) {
            return Collections.emptyList();
        }
        ConcurrentHashMap concurrentHashMap = flagMap;
        PhenotypeFlag phenotypeFlag = (PhenotypeFlag) concurrentHashMap.get(str);
        if (phenotypeFlag == null) {
            phenotypeFlag = FLAG_FACTORY.createFlag(str, LogSamplingRulesProto$LogSamplingRules.getDefaultInstance(), LogSamplerImpl$$ExternalSyntheticLambda0.INSTANCE);
            PhenotypeFlag phenotypeFlag2 = (PhenotypeFlag) concurrentHashMap.putIfAbsent(str, phenotypeFlag);
            if (phenotypeFlag2 != null) {
                phenotypeFlag = phenotypeFlag2;
            }
        }
        return ((LogSamplingRulesProto$LogSamplingRules) phenotypeFlag.get()).getRulesList();
    }

    @Override // com.google.android.gms.clearcut.ClearcutLogger.LogSampler
    public boolean shouldLog(String str, int i, int i2) {
        for (LogSamplingRulesProto$LogSamplingRules.Rule rule : getMatchingSamplingRules(str, i, i2)) {
            if (!shouldAccept(getFingerprint(rule.getCorrelationToken(), getAndroidId(this.context)), rule.getKeepNumerator(), rule.getKeepDenominator())) {
                return false;
            }
        }
        return true;
    }

    List getMatchingSamplingRules(String str, int i, int i2) {
        if (str == null || str.isEmpty()) {
            if (i >= 0) {
                str = String.valueOf(i);
            } else {
                str = null;
            }
        }
        if (str == null) {
            return new ArrayList();
        }
        List<LogSamplingRulesProto$LogSamplingRules.Rule> matchingSamplingRules = getMatchingSamplingRules(str);
        ArrayList arrayList = new ArrayList();
        for (LogSamplingRulesProto$LogSamplingRules.Rule rule : matchingSamplingRules) {
            if (!rule.hasEventCode() || rule.getEventCode() == 0 || rule.getEventCode() == i2) {
                arrayList.add(rule);
            }
        }
        return arrayList;
    }
}
