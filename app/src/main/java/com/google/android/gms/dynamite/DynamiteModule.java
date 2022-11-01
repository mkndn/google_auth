package com.google.android.gms.dynamite;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Field;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DynamiteModule {
    public static final int LOCAL = -1;
    public static final int NONE = 0;
    public static final int NO_SELECTION = 0;
    public static final int REMOTE = 1;
    private static int sDynamiteLoaderVersion = -1;
    private static Boolean sPackageSide = null;
    private static Boolean isGmsCoreValid = null;
    private static final ThreadLocal sCurrentQueryResult = new ThreadLocal();
    private static final ThreadLocal startTimeMs = new ThreadLocal() { // from class: com.google.android.gms.dynamite.DynamiteModule.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Long initialValue() {
            return 0L;
        }
    };
    private static final VersionPolicy.IVersions DEFAULT_VERSIONS = new VersionPolicy.IVersions() { // from class: com.google.android.gms.dynamite.DynamiteModule.2
    };
    public static final VersionPolicy PREFER_REMOTE = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.3
    };
    public static final VersionPolicy PREFER_LOCAL = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.4
    };
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.5
    };
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.6
    };
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.7
    };
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.8
    };
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION_NO_FORCE_STAGING = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.9
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface VersionPolicy {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface IVersions {
        }
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (!Objects.equal(declaredField.get(null), str)) {
                Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + str + "'");
                return 0;
            }
            return declaredField2.getInt(null);
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e2) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: " + e2.getMessage());
            return 0;
        }
    }
}
