package com.google.android.apps.authenticator.testability;

import android.content.Context;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.apps.authenticator.time.Clock;
import com.google.android.apps.authenticator.time.Sleeper;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DependencyInjector {
    private static DependencyInjectorModule sModule;

    private DependencyInjector() {
    }

    public static synchronized void close() {
        synchronized (DependencyInjector.class) {
            DependencyInjectorModule dependencyInjectorModule = sModule;
            if (dependencyInjectorModule != null) {
                dependencyInjectorModule.close();
                sModule = null;
            }
        }
    }

    public static synchronized void configure(DependencyInjectorModule dependencyInjectorModule) {
        synchronized (DependencyInjector.class) {
            Preconditions.checkNotNull(dependencyInjectorModule);
            close();
            sModule = dependencyInjectorModule;
        }
    }

    public static void configureForProductionIfNotConfigured(Context context) {
        Preconditions.checkNotNull(context);
        DependencyInjectorModule createProductionModule = createProductionModule();
        createProductionModule.initialize(context);
        configureIfNotConfigured(createProductionModule);
    }

    public static synchronized boolean configureIfNotConfigured(DependencyInjectorModule dependencyInjectorModule) {
        synchronized (DependencyInjector.class) {
            if (sModule != null) {
                return false;
            }
            configure(dependencyInjectorModule);
            return true;
        }
    }

    private static DependencyInjectorModule createModuleForIntegrationTesting() {
        try {
            return (DependencyInjectorModule) Class.forName(DependencyInjectorModule.class.getPackage().getName() + ".DependencyInjectorModuleForIntegrationTesting").newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static DependencyInjectorModule createProductionModule() {
        return new DependencyInjectorModule();
    }

    public static AccountDb getAccountDb() {
        return getModule().getAccountDb();
    }

    public static Context getContext() {
        return getModule().getContext();
    }

    public static synchronized DependencyInjectorModule getModule() {
        DependencyInjectorModule dependencyInjectorModule;
        synchronized (DependencyInjector.class) {
            Preconditions.checkState(sModule != null, "Not initialized");
            dependencyInjectorModule = sModule;
        }
        return dependencyInjectorModule;
    }

    public static synchronized OptionalFeatures getOptionalFeatures() {
        OptionalFeatures optionalFeatures;
        synchronized (DependencyInjector.class) {
            optionalFeatures = getModule().getOptionalFeatures();
        }
        return optionalFeatures;
    }

    public static synchronized Object getOptionalModule() {
        Object optionalDependencyInjectorModule;
        synchronized (DependencyInjector.class) {
            optionalDependencyInjectorModule = getModule().getOptionalDependencyInjectorModule();
        }
        return optionalDependencyInjectorModule;
    }

    public static StartActivityListener getStartActivityListener() {
        return getModule().getStartActivityListener();
    }

    public static StartServiceListener getStartServiceListener() {
        return getModule().getStartServiceListener();
    }

    public static void resetForIntegrationTesting(Context context) {
        Preconditions.checkNotNull(context);
        DependencyInjectorModule createModuleForIntegrationTesting = createModuleForIntegrationTesting();
        createModuleForIntegrationTesting.initialize(context);
        configure(createModuleForIntegrationTesting);
    }

    public static void setElapsedRealtimeSinceBootClock(Clock clock) {
        getModule().setElapsedRealtimeSinceBootClock(clock);
    }

    public static void setOptionalFeatures(OptionalFeatures optionalFeatures) {
        getModule().setOptionalFeatures(optionalFeatures);
    }

    public static void setSleeper(Sleeper sleeper) {
        getModule().setSleeper(sleeper);
    }

    public static void setStartActivityListener(StartActivityListener startActivityListener) {
        getModule().setStartActivityListener(startActivityListener);
    }

    public static void setStartServiceListener(StartServiceListener startServiceListener) {
        getModule().setStartServiceListener(startServiceListener);
    }
}
