package com.google.android.libraries.performance.primes.metrics.storage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.libraries.performance.primes.metrics.storage.DirStatsConfigurations;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.GoogleLogger;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackageMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class DirStatsCapture {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/storage/DirStatsCapture");

    static void collectDirStats(Map map, List list, int i, ImmutableList immutableList, int i2) {
        SinglePassTraversal singlePassTraversal = new SinglePassTraversal(i, i2, immutableList);
        list.addAll(singlePassTraversal.postProcessDirEntries(singlePassTraversal.scanDirectories(map)));
    }

    private static Map getBaseDirs(Context context, boolean z) {
        EnumMap enumMap = new EnumMap(SystemHealthProto$PackageMetric.DirStats.StorageContext.class);
        File dataDir = getDataDir(context);
        if (dataDir != null) {
            enumMap.put((EnumMap) SystemHealthProto$PackageMetric.DirStats.StorageContext.CREDENTIAL_ENCRYPTED, (SystemHealthProto$PackageMetric.DirStats.StorageContext) dataDir);
        }
        if (!z) {
            return enumMap;
        }
        File deviceEncryptedDataDir = getDeviceEncryptedDataDir(context);
        if (deviceEncryptedDataDir != null && dataDir != null && !isSameDirectory(deviceEncryptedDataDir, dataDir)) {
            enumMap.put((EnumMap) SystemHealthProto$PackageMetric.DirStats.StorageContext.DEVICE_ENCRYPTED, (SystemHealthProto$PackageMetric.DirStats.StorageContext) deviceEncryptedDataDir);
        }
        return enumMap;
    }

    private static File getDataDir(Context context) {
        try {
            return new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).dataDir);
        } catch (PackageManager.NameNotFoundException e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/DirStatsCapture", "getDataDir", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_DEFAULT_HANDLER_APPS_VALUE, "DirStatsCapture.java")).log("Failed to use package manager getting data directory from context instead.");
            File filesDir = context.getFilesDir();
            if (filesDir != null) {
                return filesDir.getParentFile();
            }
            return null;
        }
    }

    private static File getDeviceEncryptedDataDir(Context context) {
        if (Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return context.createDeviceProtectedStorageContext().getDataDir();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableList getDirStats(Context context, int i, ImmutableList immutableList, boolean z) {
        ThreadUtil.ensureBackgroundThread();
        ArrayList arrayList = new ArrayList();
        try {
            collectDirStats(getBaseDirs(context, z), arrayList, i, immutableList, 512);
            return ImmutableList.copyOf((Collection) arrayList);
        } catch (Exception e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/DirStatsCapture", "getDirStats", 352, "DirStatsCapture.java")).log("Failed to retrieve DirStats.");
            return ImmutableList.of();
        }
    }

    static boolean isSameDirectory(File file, File file2) {
        try {
            return file.getCanonicalPath().equals(file2.getCanonicalPath());
        } catch (IOException e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atSevere()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/DirStatsCapture", "isSameDirectory", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DROIDGUARD_ATTESTATION_VALUE, "DirStatsCapture.java")).log("Failed to retrieve canonical paths.");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean matchesFile(List list, String str) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((DirStatsConfigurations.PathMatcher) it.next()).matches(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SinglePassTraversal {
        private final List listPathMatchers;
        private final int maxDepth;
        private final int maxDirStats;

        SinglePassTraversal(int i, int i2, List list) {
            this.maxDepth = i;
            this.maxDirStats = i2;
            this.listPathMatchers = list;
        }

        List postProcessDirEntries(List list) {
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                DirEntry dirEntry = (DirEntry) list.get(size);
                if (dirEntry.isDirectory && dirEntry.parent != null) {
                    dirEntry.parent.cumulativeSize += dirEntry.cumulativeSize;
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                DirEntry dirEntry2 = (DirEntry) it.next();
                if (arrayList.size() >= this.maxDirStats || dirEntry2.depth > this.maxDepth) {
                    break;
                }
                arrayList.add((SystemHealthProto$PackageMetric.DirStats) SystemHealthProto$PackageMetric.DirStats.newBuilder().setStorageContext(dirEntry2.storageContext).setDirPath(dirEntry2.relativePath).setSizeBytes(dirEntry2.cumulativeSize).build());
            }
            return arrayList;
        }

        List scanDirectories(Map map) {
            ArrayList arrayList = new ArrayList();
            PriorityQueue priorityQueue = new PriorityQueue();
            for (Map.Entry entry : map.entrySet()) {
                try {
                    priorityQueue.add(new DirEntry((SystemHealthProto$PackageMetric.DirStats.StorageContext) entry.getKey(), ((File) entry.getValue()).getCanonicalFile()));
                } catch (Exception e) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) DirStatsCapture.logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/DirStatsCapture$SinglePassTraversal", "scanDirectories", 115, "DirStatsCapture.java")).log("couldn't canonicalize %s, skipping", entry);
                }
            }
            while (true) {
                DirEntry dirEntry = (DirEntry) priorityQueue.poll();
                if (dirEntry != null) {
                    arrayList.add(dirEntry);
                    if (dirEntry.isDirectory) {
                        try {
                            if (Build.VERSION.SDK_INT >= 26) {
                                DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(dirEntry.baseDir.toPath().resolve(dirEntry.relativePath));
                                try {
                                    for (Path path : newDirectoryStream) {
                                        BasicFileAttributes readAttributes = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
                                        if (!readAttributes.isSymbolicLink()) {
                                            if (readAttributes.isRegularFile()) {
                                                long size = readAttributes.size();
                                                dirEntry.cumulativeSize += size;
                                                if (!this.listPathMatchers.isEmpty() && priorityQueue.size() + arrayList.size() <= this.maxDirStats) {
                                                    DirEntry dirEntry2 = new DirEntry(dirEntry, false, path.getFileName().toString());
                                                    if (DirStatsCapture.matchesFile(this.listPathMatchers, dirEntry2.relativePath)) {
                                                        dirEntry2.cumulativeSize = size;
                                                        priorityQueue.add(dirEntry2);
                                                    }
                                                }
                                            } else if (readAttributes.isDirectory()) {
                                                priorityQueue.add(new DirEntry(dirEntry, true, path.getFileName().toString()));
                                            }
                                        }
                                    }
                                    if (newDirectoryStream != null) {
                                        newDirectoryStream.close();
                                    }
                                } catch (Throwable th) {
                                    if (newDirectoryStream != null) {
                                        try {
                                            newDirectoryStream.close();
                                        } catch (Throwable th2) {
                                            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                                        }
                                    }
                                    throw th;
                                    break;
                                }
                            } else {
                                File file = new File(dirEntry.baseDir, dirEntry.relativePath);
                                String[] list = file.list();
                                if (list != null) {
                                    for (String str : list) {
                                        File file2 = new File(file, str);
                                        if (file2.equals(file2.getCanonicalFile())) {
                                            if (file2.isFile()) {
                                                long length = file2.length();
                                                dirEntry.cumulativeSize += length;
                                                if (!this.listPathMatchers.isEmpty() && priorityQueue.size() + arrayList.size() <= this.maxDirStats) {
                                                    DirEntry dirEntry3 = new DirEntry(dirEntry, false, str);
                                                    if (DirStatsCapture.matchesFile(this.listPathMatchers, dirEntry3.relativePath)) {
                                                        dirEntry3.cumulativeSize = length;
                                                        priorityQueue.add(dirEntry3);
                                                    }
                                                }
                                            } else if (file2.isDirectory()) {
                                                priorityQueue.add(new DirEntry(dirEntry, true, str));
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (IOException | SecurityException e2) {
                            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) DirStatsCapture.logger.atWarning()).withCause(e2)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/DirStatsCapture$SinglePassTraversal", "scanDirectories", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_APP_OR_API_PRODUCT_DEVELOPMENT_VALUE, "DirStatsCapture.java")).log("exception while collecting DirStats for dir %s", dirEntry.relativePath);
                        }
                    }
                } else {
                    return arrayList;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class DirEntry implements Comparable {
            final File baseDir;
            long cumulativeSize;
            final int depth;
            final boolean isDirectory;
            final DirEntry parent;
            final String relativePath;
            final SystemHealthProto$PackageMetric.DirStats.StorageContext storageContext;

            DirEntry(DirEntry dirEntry, boolean z, String str) {
                this.cumulativeSize = 0L;
                this.storageContext = dirEntry.storageContext;
                this.baseDir = dirEntry.baseDir;
                this.parent = dirEntry;
                this.depth = dirEntry.depth + 1;
                this.isDirectory = z;
                if (dirEntry.depth != 0) {
                    str = dirEntry.relativePath + "/" + str;
                }
                this.relativePath = str;
            }

            @Override // java.lang.Comparable
            public int compareTo(DirEntry dirEntry) {
                int i = this.depth;
                int i2 = dirEntry.depth;
                if (i != i2) {
                    return i < i2 ? -1 : 1;
                }
                boolean z = this.isDirectory;
                if (z != dirEntry.isDirectory) {
                    return z ? -1 : 1;
                }
                return this.relativePath.compareTo(dirEntry.relativePath);
            }

            DirEntry(SystemHealthProto$PackageMetric.DirStats.StorageContext storageContext, File file) {
                this.cumulativeSize = 0L;
                this.storageContext = storageContext;
                this.baseDir = file;
                this.parent = null;
                this.depth = 0;
                this.isDirectory = true;
                this.relativePath = "";
            }
        }
    }
}
