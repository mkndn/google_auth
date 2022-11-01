package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.clearcut.internal.LogVerifierResultParcelable;
import com.google.android.gms.clearcut.internal.PlayLoggerContext;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.common.base.Supplier;
import com.google.common.logging.ClientVisualElements$ClientVisualElementsProto;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$LogEvent;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LogEventParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new LogEventParcelableCreator();
    private static final String[] EMPTY_ARRAY = new String[0];
    public boolean addPhenotypeExperimentTokens;
    public final ClientVisualElements$ClientVisualElementsProto clientVisualElements;
    public int[] experimentIds;
    public byte[][] experimentTokens;
    public ExperimentTokens[] experimentTokensParcelables;
    public ClientAnalytics$LogEvent logEvent;
    public byte[] logEventBytes;
    public LogVerifierResultParcelable logVerifierResult;
    public String[] mendelPackages;
    private final String[] mendelPackagesToFilter;
    public PlayLoggerContext playLoggerContext;
    public final Supplier sourceExtensionByteStringProvider;
    public int[] testCodes;

    public LogEventParcelable(PlayLoggerContext playLoggerContext, ClientAnalytics$LogEvent clientAnalytics$LogEvent, Supplier supplier, ClientVisualElements$ClientVisualElementsProto clientVisualElements$ClientVisualElementsProto, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, ExperimentTokens[] experimentTokensArr, boolean z, String[] strArr2) {
        this.playLoggerContext = playLoggerContext;
        this.logEvent = clientAnalytics$LogEvent;
        this.sourceExtensionByteStringProvider = supplier;
        this.clientVisualElements = clientVisualElements$ClientVisualElementsProto;
        this.testCodes = iArr;
        this.mendelPackages = strArr;
        this.experimentIds = iArr2;
        this.experimentTokens = bArr;
        this.experimentTokensParcelables = experimentTokensArr;
        this.mendelPackagesToFilter = strArr2;
        this.addPhenotypeExperimentTokens = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogEventParcelable) {
            LogEventParcelable logEventParcelable = (LogEventParcelable) obj;
            return Objects.equal(this.playLoggerContext, logEventParcelable.playLoggerContext) && Arrays.equals(this.logEventBytes, logEventParcelable.logEventBytes) && Arrays.equals(this.testCodes, logEventParcelable.testCodes) && Arrays.equals(this.mendelPackages, logEventParcelable.mendelPackages) && Objects.equal(this.logEvent, logEventParcelable.logEvent) && Objects.equal(this.sourceExtensionByteStringProvider, logEventParcelable.sourceExtensionByteStringProvider) && Objects.equal(this.clientVisualElements, logEventParcelable.clientVisualElements) && Arrays.equals(this.experimentIds, logEventParcelable.experimentIds) && Arrays.deepEquals(this.experimentTokens, logEventParcelable.experimentTokens) && Arrays.equals(this.experimentTokensParcelables, logEventParcelable.experimentTokensParcelables) && Arrays.equals(this.mendelPackagesToFilter, logEventParcelable.mendelPackagesToFilter) && this.addPhenotypeExperimentTokens == logEventParcelable.addPhenotypeExperimentTokens && Objects.equal(this.logVerifierResult, logEventParcelable.logVerifierResult);
        }
        return false;
    }

    public String[] getMendelPackagesToFilter() {
        String[] strArr = this.mendelPackagesToFilter;
        if (strArr == null) {
            return EMPTY_ARRAY;
        }
        return strArr;
    }

    public int hashCode() {
        return Objects.hashCode(this.playLoggerContext, this.logEventBytes, this.testCodes, this.mendelPackages, this.logEvent, this.sourceExtensionByteStringProvider, this.clientVisualElements, this.experimentIds, this.experimentTokens, this.experimentTokensParcelables, Boolean.valueOf(this.addPhenotypeExperimentTokens), this.mendelPackagesToFilter, this.logVerifierResult);
    }

    public String toString() {
        StringBuilder append = new StringBuilder("LogEventParcelable[").append(this.playLoggerContext).append(", ").append("LogEventBytes: ").append(this.logEventBytes == null ? null : new String(this.logEventBytes, StandardCharsets.UTF_8)).append(", ").append("TestCodes: ").append(Arrays.toString(this.testCodes)).append(", ").append("MendelPackages: ").append(Arrays.toString(this.mendelPackages)).append(", ").append("LogEvent: ").append(this.logEvent).append(", ").append("SourceExtensionByteStringProvider: ").append(this.sourceExtensionByteStringProvider).append(", ").append("VeProducer: ").append(this.clientVisualElements).append(", ").append("ExperimentIDs: ").append(Arrays.toString(this.experimentIds)).append(", ").append("ExperimentTokens: ").append(Arrays.deepToString(this.experimentTokens)).append(", ").append("ExperimentTokensParcelables: ").append(Arrays.toString(this.experimentTokensParcelables)).append(", ").append("MendelPackagesToFilter: ").append(Arrays.toString(this.mendelPackagesToFilter)).append("AddPhenotypeExperimentTokens: ").append(this.addPhenotypeExperimentTokens).append(", ").append("LogVerifierResult: ");
        LogVerifierResultParcelable logVerifierResultParcelable = this.logVerifierResult;
        return append.append(logVerifierResultParcelable != null ? logVerifierResultParcelable.toString() : null).append("]").toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        LogEventParcelableCreator.writeToParcel(this, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogEventParcelable(PlayLoggerContext playLoggerContext, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr2, boolean z, ExperimentTokens[] experimentTokensArr, LogVerifierResultParcelable logVerifierResultParcelable, String[] strArr2) {
        this.playLoggerContext = playLoggerContext;
        this.logEventBytes = bArr;
        this.testCodes = iArr;
        this.mendelPackages = strArr;
        this.logEvent = null;
        this.sourceExtensionByteStringProvider = null;
        this.clientVisualElements = null;
        this.experimentIds = iArr2;
        this.experimentTokens = bArr2;
        this.experimentTokensParcelables = experimentTokensArr;
        this.addPhenotypeExperimentTokens = z;
        this.mendelPackagesToFilter = strArr2;
        this.logVerifierResult = logVerifierResultParcelable;
    }
}
