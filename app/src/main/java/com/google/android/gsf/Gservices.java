package com.google.android.gsf;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Gservices {
    static HashMap sCache;
    private static boolean sPreloaded;
    private static Object sVersionToken;
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri CONTENT_PREFIX_URI = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern TRUE_PATTERN = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern FALSE_PATTERN = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean sInvalidateCache = new AtomicBoolean();
    static final HashMap sBooleanCache = new HashMap();
    static final HashMap sIntCache = new HashMap();
    static final HashMap sLongCache = new HashMap();
    static final HashMap sFloatCache = new HashMap();
    static String[] sPreloadedPrefixes = new String[0];

    private static void bulkCacheByPrefixLocked(ContentResolver contentResolver, String[] strArr) {
        sCache.putAll(getStringsByPrefix(contentResolver, strArr));
        sPreloaded = true;
    }

    private static void ensureCacheInitializedLocked(ContentResolver contentResolver) {
        if (sCache == null) {
            sInvalidateCache.set(false);
            sCache = new HashMap();
            sVersionToken = new Object();
            sPreloaded = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new ContentObserver(null) { // from class: com.google.android.gsf.Gservices.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    Gservices.sInvalidateCache.set(true);
                }
            });
        } else if (sInvalidateCache.getAndSet(false)) {
            sCache.clear();
            sBooleanCache.clear();
            sIntCache.clear();
            sLongCache.clear();
            sFloatCache.clear();
            sVersionToken = new Object();
            sPreloaded = false;
        }
    }

    public static long getLong(ContentResolver contentResolver, String str, long j) {
        Object versionToken = getVersionToken(contentResolver);
        Long l = (Long) getValue(sLongCache, str, Long.valueOf(j));
        if (l != null) {
            return l.longValue();
        }
        String string = getString(contentResolver, str);
        if (string != null) {
            try {
                long parseLong = Long.parseLong(string);
                l = Long.valueOf(parseLong);
                j = parseLong;
            } catch (NumberFormatException e) {
            }
        }
        putValueAndRemoveFromStringCache(versionToken, sLongCache, str, l);
        return j;
    }

    @Deprecated
    public static String getString(ContentResolver contentResolver, String str) {
        return getString(contentResolver, str, null);
    }

    public static Map getStringsByPrefix(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(CONTENT_PREFIX_URI, null, null, strArr, null);
        TreeMap treeMap = new TreeMap();
        if (query == null) {
            return treeMap;
        }
        while (query.moveToNext()) {
            try {
                treeMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return treeMap;
    }

    private static Object getValue(HashMap hashMap, String str, Object obj) {
        synchronized (Gservices.class) {
            if (hashMap.containsKey(str)) {
                Object obj2 = hashMap.get(str);
                if (obj2 != null) {
                    obj = obj2;
                }
                return obj;
            }
            return null;
        }
    }

    public static Object getVersionToken(ContentResolver contentResolver) {
        Object obj;
        synchronized (Gservices.class) {
            ensureCacheInitializedLocked(contentResolver);
            obj = sVersionToken;
        }
        return obj;
    }

    private static void putStringCache(Object obj, String str, String str2) {
        synchronized (Gservices.class) {
            if (obj == sVersionToken) {
                sCache.put(str, str2);
            }
        }
    }

    private static void putValueAndRemoveFromStringCache(Object obj, HashMap hashMap, String str, Object obj2) {
        synchronized (Gservices.class) {
            if (obj == sVersionToken) {
                hashMap.put(str, obj2);
                sCache.remove(str);
            }
        }
    }

    public static String getString(ContentResolver contentResolver, String str, String str2) {
        synchronized (Gservices.class) {
            ensureCacheInitializedLocked(contentResolver);
            Object obj = sVersionToken;
            if (sCache.containsKey(str)) {
                String str3 = (String) sCache.get(str);
                if (str3 != null) {
                    str2 = str3;
                }
                return str2;
            }
            for (String str4 : sPreloadedPrefixes) {
                if (str.startsWith(str4)) {
                    if (!sPreloaded || sCache.isEmpty()) {
                        bulkCacheByPrefixLocked(contentResolver, sPreloadedPrefixes);
                        if (sCache.containsKey(str)) {
                            String str5 = (String) sCache.get(str);
                            if (str5 != null) {
                                str2 = str5;
                            }
                            return str2;
                        }
                    }
                    return str2;
                }
            }
            Cursor query = contentResolver.query(CONTENT_URI, null, null, new String[]{str}, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                }
                return str2;
            }
            try {
                if (!query.moveToFirst()) {
                    putStringCache(obj, str, null);
                    if (query != null) {
                        query.close();
                    }
                    return str2;
                }
                String string = query.getString(1);
                if (string != null && string.equals(str2)) {
                    string = str2;
                }
                putStringCache(obj, str, string);
                if (string != null) {
                    str2 = string;
                }
                if (query != null) {
                    query.close();
                }
                return str2;
            } catch (Throwable th) {
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }
}
