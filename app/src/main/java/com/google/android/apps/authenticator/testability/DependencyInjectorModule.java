package com.google.android.apps.authenticator.testability;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.apps.authenticator.AuthenticatorActivity;
import com.google.android.apps.authenticator.AuthenticatorApplication;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.apps.authenticator.time.Clock;
import com.google.android.apps.authenticator.time.DefaultSleeper;
import com.google.android.apps.authenticator.time.ElapsedRealtimeSinceBootClock;
import com.google.android.apps.authenticator.time.Sleeper;
import com.google.android.apps.authenticator.time.SystemWallClock;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DependencyInjectorModule {
    private AccountDb mAccountDb;
    private Context mContext;
    private Clock mElapsedTimeSinceBootClock;
    private Object mOptionalDependencyInjectorModule;
    private OptionalFeatures mOptionalFeatures;
    private Sleeper mSleeper;
    private StartActivityListener mStartActivityListener;
    private StartServiceListener mStartServiceListener;
    private Clock mSystemWallClock;

    private void invokeOptionalDependencyInjectorModuleClose(Object obj) {
        try {
            obj.getClass().getMethod("close", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException("Failed to close optional dependency injector", e);
        }
    }

    public synchronized void close() {
        AccountDb accountDb = this.mAccountDb;
        if (accountDb != null) {
            accountDb.close();
        }
        Object obj = this.mOptionalDependencyInjectorModule;
        if (obj != null) {
            invokeOptionalDependencyInjectorModuleClose(obj);
        }
        this.mContext = null;
        this.mAccountDb = null;
        this.mStartActivityListener = null;
        this.mStartServiceListener = null;
        this.mSystemWallClock = null;
        this.mElapsedTimeSinceBootClock = null;
        this.mSleeper = null;
        this.mOptionalFeatures = null;
        this.mOptionalDependencyInjectorModule = null;
    }

    protected AccountDb createAccountDb() {
        return new AccountDb(getContext());
    }

    protected Object createOptionalDependencyInjectorModule() {
        try {
            return Class.forName(AuthenticatorApplication.class.getPackage().getName() + ".corp.testability.OptionalDependencyInjectorModule").getConstructor(DependencyInjectorModule.class).newInstance(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected OptionalFeatures createOptionalFeatures() {
        try {
            try {
                return (OptionalFeatures) Class.forName(AuthenticatorActivity.class.getPackage().getName() + ".corp.NonMarketBuildOptionalFeatures").newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to instantiate optional features module", e);
            }
        } catch (ClassNotFoundException e2) {
            return new MarketBuildOptionalFeatures();
        }
    }

    protected PackageManager createPackageManager() {
        return getContext().getPackageManager();
    }

    protected Sleeper createSleeper() {
        return new DefaultSleeper();
    }

    public synchronized AccountDb getAccountDb() {
        if (this.mAccountDb == null) {
            this.mAccountDb = createAccountDb();
        }
        return this.mAccountDb;
    }

    public synchronized Context getContext() {
        Preconditions.checkState(this.mContext != null, "Context not set");
        return this.mContext;
    }

    public synchronized Clock getElapsedRealtimeSinceBootClock() {
        if (this.mElapsedTimeSinceBootClock == null) {
            this.mElapsedTimeSinceBootClock = new ElapsedRealtimeSinceBootClock();
        }
        return this.mElapsedTimeSinceBootClock;
    }

    public synchronized Object getOptionalDependencyInjectorModule() {
        if (this.mOptionalDependencyInjectorModule == null) {
            this.mOptionalDependencyInjectorModule = createOptionalDependencyInjectorModule();
        }
        return this.mOptionalDependencyInjectorModule;
    }

    public synchronized OptionalFeatures getOptionalFeatures() {
        if (this.mOptionalFeatures == null) {
            this.mOptionalFeatures = createOptionalFeatures();
        }
        return this.mOptionalFeatures;
    }

    public synchronized Sleeper getSleeper() {
        if (this.mSleeper == null) {
            this.mSleeper = createSleeper();
        }
        return this.mSleeper;
    }

    public synchronized StartActivityListener getStartActivityListener() {
        return this.mStartActivityListener;
    }

    public synchronized StartServiceListener getStartServiceListener() {
        return this.mStartServiceListener;
    }

    public synchronized Clock getSystemWallClock() {
        if (this.mSystemWallClock == null) {
            this.mSystemWallClock = new SystemWallClock();
        }
        return this.mSystemWallClock;
    }

    public synchronized void initialize(Context context) {
        this.mContext = context;
    }

    public synchronized void setElapsedRealtimeSinceBootClock(Clock clock) {
        this.mElapsedTimeSinceBootClock = clock;
    }

    public synchronized void setOptionalFeatures(OptionalFeatures optionalFeatures) {
        this.mOptionalFeatures = optionalFeatures;
    }

    public synchronized void setSleeper(Sleeper sleeper) {
        this.mSleeper = sleeper;
    }

    public synchronized void setStartActivityListener(StartActivityListener startActivityListener) {
        this.mStartActivityListener = startActivityListener;
    }

    public synchronized void setStartServiceListener(StartServiceListener startServiceListener) {
        this.mStartServiceListener = startServiceListener;
    }
}
