package com.google.android.libraries.phenotype.client.lockdown;

import com.google.android.libraries.phenotype.client.lockdown.buildinfo.BuildInfo;
import com.google.android.libraries.phenotype.client.lockdown.resources.FlagExemptionResource;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultimap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlagExemptionsReader {
    private final boolean doFlagLockdownRuntimeValidations;

    public FlagExemptionsReader(BuildInfo buildInfo) {
        Preconditions.checkNotNull(buildInfo, "BuildInfo must be non-null");
        this.doFlagLockdownRuntimeValidations = !buildInfo.isProductionBuild();
    }

    private static boolean isFlagExemptedHelper(String str, String str2) {
        ImmutableMultimap immutableMultimap = (ImmutableMultimap) FlagExemptionResource.exemptions.get();
        if (str == null) {
            return immutableMultimap.containsValue(str2);
        }
        return immutableMultimap.containsEntry(str, str2);
    }

    public boolean isFlagExempted(String str) {
        Preconditions.checkNotNull(str, "flagName must not be null");
        if (this.doFlagLockdownRuntimeValidations) {
            return isFlagExemptedHelper(null, str);
        }
        return true;
    }

    public boolean isFlagExempted(String str, String str2) {
        Preconditions.checkNotNull(str, "staticConfigPackage must not be null");
        Preconditions.checkNotNull(str2, "flagName must not be null");
        if (this.doFlagLockdownRuntimeValidations) {
            return isFlagExemptedHelper(str, str2);
        }
        return true;
    }
}
