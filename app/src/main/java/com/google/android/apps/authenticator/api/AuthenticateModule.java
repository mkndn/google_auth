package com.google.android.apps.authenticator.api;

import android.content.Context;
import com.google.android.apps.authenticator.common.ApplicationContext;
import java.security.MessageDigest;
import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AuthenticateModule {
    @Singleton
    public FacetIdCalculator provideFacetIdCalculator(@ApplicationContext Context context) {
        return new FacetIdCalculator(context);
    }

    @Singleton
    public PackageSignatureFingerprintProvider providePackageSignatureFingerprintProvider(MessageDigest messageDigest, @ApplicationContext Context context) {
        return new PackageSignatureFingerprintProvider(messageDigest, context.getPackageManager());
    }
}
