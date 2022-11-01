package com.google.android.libraries.phenotype.client;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.common.base.Optional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HermeticFileOverridesReader {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CachingReader {
        private static volatile Optional cachedOverrides = null;

        private CachingReader() {
        }

        public static Optional readFromFileAndCacheIfEligible(Context context) {
            Optional optional;
            synchronized (CachingReader.class) {
                optional = cachedOverrides;
                if (optional == null) {
                    optional = new HermeticFileOverridesReader().readFromFileIfEligible(context);
                    cachedOverrides = optional;
                }
            }
            return optional;
        }
    }

    private static final String copyString(String str) {
        return new String(str);
    }

    private static Optional findOverridesFile(Context context) {
        try {
            File file = new File(context.getDir("phenotype_hermetic", 0), "overrides.txt");
            return file.exists() ? Optional.of(file) : Optional.absent();
        } catch (RuntimeException e) {
            Log.e("HermeticFileOverrides", "no data dir", e);
            return Optional.absent();
        }
    }

    private static HermeticFileOverrides parseFromFile(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
            HashMap hashMap = new HashMap();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split(" ", 3);
                    if (split.length != 3) {
                        Log.e("HermeticFileOverrides", "Invalid: " + readLine);
                    } else {
                        String copyString = copyString(split[0]);
                        String decode = Uri.decode(copyString(split[1]));
                        String str = (String) hashMap.get(split[2]);
                        if (str == null) {
                            String copyString2 = copyString(split[2]);
                            str = Uri.decode(copyString2);
                            if (str.length() < 1024 || str == copyString2) {
                                hashMap.put(copyString2, str);
                            }
                        }
                        if (!simpleArrayMap.containsKey(copyString)) {
                            simpleArrayMap.put(copyString, new SimpleArrayMap());
                        }
                        ((SimpleArrayMap) simpleArrayMap.get(copyString)).put(decode, str);
                    }
                } else {
                    Log.i("HermeticFileOverrides", "Parsed " + String.valueOf(file));
                    DefaultHermeticFileOverrides defaultHermeticFileOverrides = new DefaultHermeticFileOverrides(simpleArrayMap);
                    bufferedReader.close();
                    return defaultHermeticFileOverrides;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    boolean isEligible(String str, String str2) {
        if ((!str.equals("eng") && !str.equals("userdebug")) || (!str2.contains("dev-keys") && !str2.contains("test-keys"))) {
            return false;
        }
        return true;
    }

    Optional readFromFile(Context context) {
        Optional absent;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            StrictMode.allowThreadDiskWrites();
            Optional findOverridesFile = findOverridesFile(context);
            if (findOverridesFile.isPresent()) {
                absent = Optional.of(parseFromFile((File) findOverridesFile.get()));
            } else {
                absent = Optional.absent();
            }
            return absent;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public Optional readFromFileIfEligible(Context context) {
        if (!isEligible(Build.TYPE, Build.TAGS)) {
            return Optional.absent();
        }
        return readFromFile(DirectBootUtils.getDeviceProtectedStorageContextOrFallback(context));
    }
}
