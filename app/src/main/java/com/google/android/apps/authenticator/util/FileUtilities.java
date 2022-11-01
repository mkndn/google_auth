package com.google.android.apps.authenticator.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FileUtilities {
    public static final String DATABASES_PATH = "databases";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class StatStruct {
        public long atime;
        public long blksize;
        public long blocks;
        public long ctime;
        public long dev;
        public int gid;
        public long ino;
        public int mode;
        public long mtime;
        public long nlink;
        public long rdev;
        public long size;
        public int uid;

        public String toString() {
            return new String(String.format("StatStruct{ dev: %d, ino: %d, mode: %o (octal), nlink: %d, uid: %d, gid: %d, rdev: %d, size: %d, blksize: %d, blocks: %d, atime: %d, mtime: %d, ctime: %d }\n", Long.valueOf(this.dev), Long.valueOf(this.ino), Integer.valueOf(this.mode), Long.valueOf(this.nlink), Integer.valueOf(this.uid), Integer.valueOf(this.gid), Long.valueOf(this.rdev), Long.valueOf(this.size), Long.valueOf(this.blksize), Long.valueOf(this.blocks), Long.valueOf(this.atime), Long.valueOf(this.mtime), Long.valueOf(this.ctime)));
        }
    }

    private FileUtilities() {
    }

    private static int getFileStatusInt(Object obj, String str) {
        return Class.forName("android.os.FileUtils$FileStatus").getField(str).getInt(obj);
    }

    private static long getFileStatusLong(Object obj, String str) {
        return Class.forName("android.os.FileUtils$FileStatus").getField(str).getLong(obj);
    }

    public static String getFilesystemInfoForErrorString(Context context) {
        String exc;
        String[] strArr = {context.getApplicationInfo().dataDir, context.getDatabasePath(DATABASES_PATH).getParent(), context.getDatabasePath(DATABASES_PATH).getAbsolutePath()};
        StringBuilder sb = new StringBuilder();
        int myUid = Process.myUid();
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            try {
                StatStruct stat = getStat(str);
                try {
                    if (stat.uid == 0) {
                        exc = "root";
                    } else {
                        PackageManager packageManager = context.getPackageManager();
                        exc = packageManager != null ? packageManager.getNameForUid(stat.uid) : null;
                    }
                } catch (Exception e) {
                    exc = e.toString();
                }
                sb.append(str + " directory stat (my UID: " + myUid);
                if (exc != null) {
                    sb.append(", dir owner UID name: " + exc + "): ");
                } else {
                    sb.append("): ");
                }
                sb.append(stat.toString() + "\n");
            } catch (IOException e2) {
                sb.append(str + " directory stat threw an exception: " + String.valueOf(e2) + "\n");
            }
        }
        return sb.toString();
    }

    private static int getLibcoreFileStatusInt(Object obj, String str) {
        return Class.forName("libcore.io.StructStat").getField(str).getInt(obj);
    }

    private static long getLibcoreFileStatusLong(Object obj, String str) {
        return Class.forName("libcore.io.StructStat").getField(str).getLong(obj);
    }

    public static StatStruct getStat(String str) {
        try {
            Object newInstance = Class.forName("android.os.FileUtils$FileStatus").newInstance();
            if (((Boolean) Class.forName("android.os.FileUtils").getMethod("getFileStatus", String.class, Class.forName("android.os.FileUtils$FileStatus")).invoke(null, str, newInstance)).booleanValue()) {
                StatStruct statStruct = new StatStruct();
                statStruct.dev = getFileStatusInt(newInstance, "dev");
                statStruct.ino = getFileStatusInt(newInstance, "ino");
                statStruct.mode = getFileStatusInt(newInstance, "mode");
                statStruct.nlink = getFileStatusInt(newInstance, "nlink");
                statStruct.uid = getFileStatusInt(newInstance, "uid");
                statStruct.gid = getFileStatusInt(newInstance, "gid");
                statStruct.rdev = getFileStatusInt(newInstance, "rdev");
                statStruct.size = getFileStatusLong(newInstance, "size");
                statStruct.blksize = getFileStatusInt(newInstance, "blksize");
                statStruct.blocks = getFileStatusLong(newInstance, "blocks");
                statStruct.atime = getFileStatusLong(newInstance, "atime");
                statStruct.mtime = getFileStatusLong(newInstance, "mtime");
                statStruct.ctime = getFileStatusLong(newInstance, "ctime");
                return statStruct;
            }
            throw new IOException("FileUtils.getFileStatus returned with failure.");
        } catch (ClassNotFoundException e) {
            try {
                Object obj = Class.forName("libcore.io.Libcore").getField("os").get(null);
                Object invoke = obj.getClass().getMethod("stat", String.class).invoke(obj, str);
                if (invoke != null) {
                    StatStruct statStruct2 = new StatStruct();
                    statStruct2.dev = getLibcoreFileStatusLong(invoke, "st_dev");
                    statStruct2.ino = getLibcoreFileStatusLong(invoke, "st_ino");
                    statStruct2.mode = getLibcoreFileStatusInt(invoke, "st_mode");
                    statStruct2.nlink = getLibcoreFileStatusLong(invoke, "st_nlink");
                    statStruct2.uid = getLibcoreFileStatusInt(invoke, "st_uid");
                    statStruct2.gid = getLibcoreFileStatusInt(invoke, "st_gid");
                    statStruct2.rdev = getLibcoreFileStatusLong(invoke, "st_rdev");
                    statStruct2.size = getLibcoreFileStatusLong(invoke, "st_size");
                    statStruct2.blksize = getLibcoreFileStatusLong(invoke, "st_blksize");
                    statStruct2.blocks = getLibcoreFileStatusLong(invoke, "st_blocks");
                    statStruct2.atime = getLibcoreFileStatusLong(invoke, "st_atime");
                    statStruct2.mtime = getLibcoreFileStatusLong(invoke, "st_mtime");
                    statStruct2.ctime = getLibcoreFileStatusLong(invoke, "st_ctime");
                    return statStruct2;
                }
                throw new IOException("Libcore.os.stat returned null");
            } catch (Exception e2) {
                throw new IOException("Failed to get FileStatus: " + String.valueOf(e2));
            }
        } catch (Exception e3) {
            throw new IOException("Failed to get FileStatus: " + String.valueOf(e3));
        }
    }

    public static void restrictAccessToOwnerOnly(String str) {
        try {
            int intValue = ((Integer) Class.forName("android.os.FileUtils").getMethod("setPermissions", String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(null, str, 448, -1, -1)).intValue();
            if (intValue != 0) {
                throw new IOException("FileUtils.setPermissions failed with error code " + intValue);
            }
        } catch (Exception e) {
            throw new IOException("Failed to set permissions: " + String.valueOf(e));
        }
    }
}
