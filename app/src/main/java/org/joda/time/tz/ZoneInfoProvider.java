package org.joda.time.tz;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.DateTimeZone;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ZoneInfoProvider implements Provider {
    private final File iFileDir;
    private final ClassLoader iLoader;
    private final String iResourcePath;
    private final Map iZoneInfoMap;

    public ZoneInfoProvider(String str) {
        this(str, null, false);
    }

    private static Map loadZoneInfoMap(InputStream inputStream) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            readZoneInfoMap(dataInputStream, concurrentHashMap);
            concurrentHashMap.put("UTC", new SoftReference(DateTimeZone.UTC));
            return concurrentHashMap;
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
            }
        }
    }

    private InputStream openResource(String str) {
        InputStream systemResourceAsStream;
        if (this.iFileDir != null) {
            return new FileInputStream(new File(this.iFileDir, str));
        }
        String concat = this.iResourcePath.concat(str);
        ClassLoader classLoader = this.iLoader;
        if (classLoader != null) {
            systemResourceAsStream = classLoader.getResourceAsStream(concat);
        } else {
            systemResourceAsStream = ClassLoader.getSystemResourceAsStream(concat);
        }
        if (systemResourceAsStream == null) {
            StringBuilder append = new StringBuilder(40).append("Resource not found: \"").append(concat).append("\" ClassLoader: ");
            ClassLoader classLoader2 = this.iLoader;
            throw new IOException(append.append(classLoader2 != null ? classLoader2.toString() : "system").toString());
        }
        return systemResourceAsStream;
    }

    private static void readZoneInfoMap(DataInputStream dataInputStream, Map map) {
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        String[] strArr = new String[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            strArr[i] = dataInputStream.readUTF().intern();
        }
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        for (int i2 = 0; i2 < readUnsignedShort2; i2++) {
            try {
                map.put(strArr[dataInputStream.readUnsignedShort()], strArr[dataInputStream.readUnsignedShort()]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IOException("Corrupt zone info map");
            }
        }
    }

    @Override // org.joda.time.tz.Provider
    public Set getAvailableIDs() {
        return new TreeSet(this.iZoneInfoMap.keySet());
    }

    @Override // org.joda.time.tz.Provider
    public DateTimeZone getZone(String str) {
        Object obj;
        if (str == null || (obj = this.iZoneInfoMap.get(str)) == null) {
            return null;
        }
        if (str.equals(obj)) {
            return loadZoneData(str);
        }
        if (obj instanceof SoftReference) {
            DateTimeZone dateTimeZone = (DateTimeZone) ((SoftReference) obj).get();
            if (dateTimeZone != null) {
                return dateTimeZone;
            }
            return loadZoneData(str);
        }
        return getZone((String) obj);
    }

    protected void uncaughtException(Exception exc) {
        Thread currentThread = Thread.currentThread();
        currentThread.getThreadGroup().uncaughtException(currentThread, exc);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private DateTimeZone loadZoneData(String str) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            inputStream = openResource(str);
        } catch (IOException e) {
            e = e;
            inputStream = null;
        } catch (Throwable th) {
            th = th;
            if (inputStream2 != null) {
            }
            throw th;
        }
        try {
            try {
                DateTimeZone readFrom = DateTimeZoneBuilder.readFrom(inputStream, str);
                this.iZoneInfoMap.put(str, new SoftReference(readFrom));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                    }
                }
                return readFrom;
            } catch (Throwable th2) {
                th = th2;
                inputStream2 = inputStream;
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            uncaughtException(e);
            this.iZoneInfoMap.remove(str);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                }
            }
            return null;
        }
    }

    private ZoneInfoProvider(String str, ClassLoader classLoader, boolean z) {
        if (str == null) {
            throw new IllegalArgumentException("No resource path provided");
        }
        if (!str.endsWith("/")) {
            String valueOf = String.valueOf(str);
            str = new StringBuilder(String.valueOf(valueOf).length() + 1).append(valueOf).append('/').toString();
        }
        this.iFileDir = null;
        this.iResourcePath = str;
        if (classLoader == null && !z) {
            classLoader = getClass().getClassLoader();
        }
        this.iLoader = classLoader;
        this.iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
    }
}
