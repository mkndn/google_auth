package com.google.android.libraries.phenotype.client;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.phenotype.Phenotype;
import com.google.android.libraries.phenotype.client.api.PhenotypeClient;
import com.google.android.libraries.phenotype.client.api.ThinPhenotypeClient;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.libraries.phenotype.client.stable.ProcessReaper;
import com.google.android.libraries.storage.file.SynchronousFileStorage;
import com.google.android.libraries.storage.file.backends.AndroidFileBackend;
import com.google.apps.tiktok.inject.SingletonEntryPoints;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.Collections;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeContext {
    private final Supplier clientProvider;
    private final Context context;
    private final Supplier executorProvider;
    private final Optional optionalProcessReaper;
    private final Supplier storageProvider;
    private static final Object LOCK = new Object();
    private static Context applicationContext = null;
    private static volatile PhenotypeContext instance = null;
    private static volatile PhenotypeContext phenotypeContextForTest = null;
    private static final Supplier EXECUTOR = Suppliers.memoize(PhenotypeContext$$ExternalSyntheticLambda3.INSTANCE);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface PhenotypeApplication {
        Optional getPhenotypeContext();
    }

    public PhenotypeContext(Context context) {
        this(context, EXECUTOR);
    }

    public static PhenotypeContext get() {
        PhenotypeContextTestMode.setContextRead();
        Context context = applicationContext;
        if (context == null) {
            if (PhenotypeContextTestMode.testMode()) {
                return null;
            }
            PhenotypeContextTestMode.setContextReadStackTraceIfNotSet();
            throw new IllegalStateException("Must call PhenotypeContext.setContext() first");
        }
        return getPhenotypeContextFrom(context);
    }

    private static Supplier getDefaultPhenotypeClient(final Context context) {
        return Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.phenotype.client.PhenotypeContext$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return PhenotypeContext.lambda$getDefaultPhenotypeClient$3(context);
            }
        });
    }

    private static Supplier getDefaultStorageBackend(final Context context) {
        return Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.phenotype.client.PhenotypeContext$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return PhenotypeContext.lambda$getDefaultStorageBackend$2(context);
            }
        });
    }

    public static PhenotypeContext getPhenotypeContextFrom(Context context) {
        PhenotypeContext phenotypeContext;
        PhenotypeApplication phenotypeApplication = null;
        if (PhenotypeContextTestMode.testMode()) {
            return null;
        }
        PhenotypeContext phenotypeContext2 = instance;
        if (phenotypeContext2 == null) {
            synchronized (LOCK) {
                phenotypeContext2 = instance;
                if (phenotypeContext2 == null) {
                    Context applicationContext2 = context.getApplicationContext();
                    try {
                        phenotypeApplication = (PhenotypeApplication) SingletonEntryPoints.getEntryPoint(applicationContext2, PhenotypeApplication.class);
                    } catch (IllegalStateException e) {
                    }
                    Optional absent = Optional.absent();
                    if (phenotypeApplication != null) {
                        absent = phenotypeApplication.getPhenotypeContext();
                    } else if (applicationContext2 instanceof PhenotypeApplication) {
                        absent = ((PhenotypeApplication) applicationContext2).getPhenotypeContext();
                    } else {
                        Log.d("PhenotypeContext", "Application doesn't implement PhenotypeApplication interface, falling back to globally set context. See go/phenotype-flag#process-stable-init for more info.");
                    }
                    if (absent.isPresent()) {
                        phenotypeContext = (PhenotypeContext) absent.get();
                    } else {
                        phenotypeContext = new PhenotypeContext(applicationContext2);
                    }
                    instance = phenotypeContext;
                    phenotypeContext2 = phenotypeContext;
                }
            }
        }
        PhenotypeContext phenotypeContext3 = phenotypeContextForTest;
        return phenotypeContext3 == null ? phenotypeContext2 : phenotypeContext3;
    }

    public static boolean isTestMode() {
        boolean testMode = PhenotypeContextTestMode.testMode();
        if (applicationContext == null && !testMode) {
            PhenotypeContextTestMode.setTestModeReadStackTraceIfNotSet();
        }
        return testMode;
    }

    public static /* synthetic */ PhenotypeClient lambda$getDefaultPhenotypeClient$3(Context context) {
        return new ThinPhenotypeClient(Phenotype.getInstance(context));
    }

    public static /* synthetic */ SynchronousFileStorage lambda$getDefaultStorageBackend$2(Context context) {
        return new SynchronousFileStorage(Collections.singletonList(AndroidFileBackend.builder(context).build()));
    }

    public static /* synthetic */ Thread lambda$static$0(Runnable runnable) {
        return new Thread(runnable, "ProcessStablePhenotypeFlag");
    }

    public static void setContext(Context context) {
        synchronized (LOCK) {
            if (applicationContext == null && !PhenotypeContextTestMode.testMode()) {
                applicationContext = context.getApplicationContext();
            }
        }
    }

    public Context getContext() {
        return this.context;
    }

    public ListeningScheduledExecutorService getExecutor() {
        return (ListeningScheduledExecutorService) this.executorProvider.get();
    }

    public PhenotypeClient getPhenotypeClient() {
        return (PhenotypeClient) this.clientProvider.get();
    }

    public ProcessReaper getProcessReaper() {
        return (ProcessReaper) this.optionalProcessReaper.orNull();
    }

    public SynchronousFileStorage getStorageBackend() {
        return (SynchronousFileStorage) this.storageProvider.get();
    }

    public PhenotypeContext(Context context, Supplier supplier) {
        this(context, supplier, getDefaultPhenotypeClient(context), Optional.of(new PhenotypeProcessReaper(supplier)), getDefaultStorageBackend(context));
    }

    public PhenotypeContext(Context context, Supplier supplier, Supplier supplier2, Optional optional, Supplier supplier3) {
        Context applicationContext2 = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext2);
        Preconditions.checkNotNull(supplier);
        Preconditions.checkNotNull(supplier2);
        Preconditions.checkNotNull(optional);
        Preconditions.checkNotNull(supplier3);
        this.context = applicationContext2;
        this.executorProvider = Suppliers.memoize(supplier);
        this.clientProvider = Suppliers.memoize(supplier2);
        this.optionalProcessReaper = optional;
        this.storageProvider = Suppliers.memoize(supplier3);
    }
}
