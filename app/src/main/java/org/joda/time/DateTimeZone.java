package org.joda.time;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.FormatUtils;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.FixedDateTimeZone;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DateTimeZone implements Serializable {
    public static final DateTimeZone UTC = new FixedDateTimeZone("UTC", "UTC", 0, 0);
    private static Set cAvailableIDs = null;
    private static volatile DateTimeZone cDefault = null;
    private static NameProvider cNameProvider = null;
    private static DateTimeFormatter cOffsetFormatter = null;
    private static Provider cProvider = null;
    private static Map cZoneIdConversion = null;
    private static Map iFixedOffsetCache = null;
    private static final long serialVersionUID = 5546345482340108586L;
    private final String iID;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Stub implements Serializable {
        private static final long serialVersionUID = -6471952376487863581L;
        private transient String iID;

        Stub(String str) {
            this.iID = str;
        }

        private void readObject(ObjectInputStream objectInputStream) {
            this.iID = objectInputStream.readUTF();
        }

        private Object readResolve() {
            return DateTimeZone.forID(this.iID);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.writeUTF(this.iID);
        }
    }

    static {
        setProvider0(null);
        setNameProvider0(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DateTimeZone(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        this.iID = str;
    }

    private static synchronized DateTimeZone fixedOffsetZone(String str, int i) {
        DateTimeZone dateTimeZone;
        synchronized (DateTimeZone.class) {
            if (i == 0) {
                return UTC;
            }
            if (iFixedOffsetCache == null) {
                iFixedOffsetCache = new HashMap();
            }
            Reference reference = (Reference) iFixedOffsetCache.get(str);
            if (reference == null || (dateTimeZone = (DateTimeZone) reference.get()) == null) {
                FixedDateTimeZone fixedDateTimeZone = new FixedDateTimeZone(str, null, i, i);
                iFixedOffsetCache.put(str, new SoftReference(fixedDateTimeZone));
                return fixedDateTimeZone;
            }
            return dateTimeZone;
        }
    }

    public static DateTimeZone forID(String str) {
        if (str == null) {
            return getDefault();
        }
        if (str.equals("UTC")) {
            return UTC;
        }
        DateTimeZone zone = cProvider.getZone(str);
        if (zone != null) {
            return zone;
        }
        if (!str.startsWith("+") && !str.startsWith("-")) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 41).append("The datetime zone id '").append(str).append("' is not recognised").toString());
        }
        int parseOffset = parseOffset(str);
        if (parseOffset == 0) {
            return UTC;
        }
        return fixedOffsetZone(printOffset(parseOffset), parseOffset);
    }

    public static DateTimeZone forTimeZone(TimeZone timeZone) {
        DateTimeZone dateTimeZone;
        if (timeZone == null) {
            return getDefault();
        }
        String id = timeZone.getID();
        if (id.equals("UTC")) {
            return UTC;
        }
        String convertedId = getConvertedId(id);
        if (convertedId != null) {
            dateTimeZone = cProvider.getZone(convertedId);
        } else {
            dateTimeZone = null;
        }
        if (dateTimeZone == null) {
            dateTimeZone = cProvider.getZone(id);
        }
        if (dateTimeZone != null) {
            return dateTimeZone;
        }
        if (convertedId == null) {
            String id2 = timeZone.getID();
            if (id2.startsWith("GMT+") || id2.startsWith("GMT-")) {
                int parseOffset = parseOffset(id2.substring(3));
                if (parseOffset == 0) {
                    return UTC;
                }
                return fixedOffsetZone(printOffset(parseOffset), parseOffset);
            }
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(id).length() + 41).append("The datetime zone id '").append(id).append("' is not recognised").toString());
    }

    public static Set getAvailableIDs() {
        return cAvailableIDs;
    }

    private static synchronized String getConvertedId(String str) {
        String str2;
        synchronized (DateTimeZone.class) {
            Map map = cZoneIdConversion;
            if (map == null) {
                map = new HashMap();
                map.put("GMT", "UTC");
                map.put("WET", "WET");
                map.put("CET", "CET");
                map.put("MET", "CET");
                map.put("ECT", "CET");
                map.put("EET", "EET");
                map.put("MIT", "Pacific/Apia");
                map.put("HST", "Pacific/Honolulu");
                map.put("AST", "America/Anchorage");
                map.put("PST", "America/Los_Angeles");
                map.put("MST", "America/Denver");
                map.put("PNT", "America/Phoenix");
                map.put("CST", "America/Chicago");
                map.put("EST", "America/New_York");
                map.put("IET", "America/Indiana/Indianapolis");
                map.put("PRT", "America/Puerto_Rico");
                map.put("CNT", "America/St_Johns");
                map.put("AGT", "America/Argentina/Buenos_Aires");
                map.put("BET", "America/Sao_Paulo");
                map.put("ART", "Africa/Cairo");
                map.put("CAT", "Africa/Harare");
                map.put("EAT", "Africa/Addis_Ababa");
                map.put("NET", "Asia/Yerevan");
                map.put("PLT", "Asia/Karachi");
                map.put("IST", "Asia/Kolkata");
                map.put("BST", "Asia/Dhaka");
                map.put("VST", "Asia/Ho_Chi_Minh");
                map.put("CTT", "Asia/Shanghai");
                map.put("JST", "Asia/Tokyo");
                map.put("ACT", "Australia/Darwin");
                map.put("AET", "Australia/Sydney");
                map.put("SST", "Pacific/Guadalcanal");
                map.put("NST", "Pacific/Auckland");
                cZoneIdConversion = map;
            }
            str2 = (String) map.get(str);
        }
        return str2;
    }

    public static DateTimeZone getDefault() {
        DateTimeZone dateTimeZone = cDefault;
        if (dateTimeZone == null) {
            synchronized (DateTimeZone.class) {
                dateTimeZone = cDefault;
                if (dateTimeZone == null) {
                    dateTimeZone = null;
                    try {
                        String property = System.getProperty("user.timezone");
                        if (property != null) {
                            dateTimeZone = forID(property);
                        }
                    } catch (RuntimeException e) {
                    }
                    if (dateTimeZone == null) {
                        try {
                            dateTimeZone = forTimeZone(TimeZone.getDefault());
                        } catch (IllegalArgumentException e2) {
                        }
                    }
                    if (dateTimeZone == null) {
                        dateTimeZone = UTC;
                    }
                    cDefault = dateTimeZone;
                }
            }
        }
        return dateTimeZone;
    }

    public static NameProvider getNameProvider() {
        return cNameProvider;
    }

    private static synchronized DateTimeFormatter offsetFormatter() {
        DateTimeFormatter dateTimeFormatter;
        synchronized (DateTimeZone.class) {
            if (cOffsetFormatter == null) {
                cOffsetFormatter = new DateTimeFormatterBuilder().appendTimeZoneOffset(null, true, 2, 4).toFormatter();
            }
            dateTimeFormatter = cOffsetFormatter;
        }
        return dateTimeFormatter;
    }

    private static int parseOffset(String str) {
        return -((int) offsetFormatter().withChronology(new BaseChronology() { // from class: org.joda.time.DateTimeZone.1
            private static final long serialVersionUID = -3128740902654445468L;

            @Override // org.joda.time.Chronology
            public DateTimeZone getZone() {
                return null;
            }

            public String toString() {
                return getClass().getName();
            }

            @Override // org.joda.time.Chronology
            public Chronology withUTC() {
                return this;
            }

            @Override // org.joda.time.Chronology
            public Chronology withZone(DateTimeZone dateTimeZone) {
                return this;
            }
        }).parseMillis(str));
    }

    private static String printOffset(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i >= 0) {
            stringBuffer.append('+');
        } else {
            stringBuffer.append('-');
            i = -i;
        }
        int i2 = i / 3600000;
        FormatUtils.appendPaddedInteger(stringBuffer, i2, 2);
        int i3 = i - (i2 * 3600000);
        int i4 = i3 / 60000;
        stringBuffer.append(':');
        FormatUtils.appendPaddedInteger(stringBuffer, i4, 2);
        int i5 = i3 - (i4 * 60000);
        if (i5 == 0) {
            return stringBuffer.toString();
        }
        int i6 = i5 / 1000;
        stringBuffer.append(':');
        FormatUtils.appendPaddedInteger(stringBuffer, i6, 2);
        int i7 = i5 - (i6 * 1000);
        if (i7 == 0) {
            return stringBuffer.toString();
        }
        stringBuffer.append('.');
        FormatUtils.appendPaddedInteger(stringBuffer, i7, 3);
        return stringBuffer.toString();
    }

    private static void setNameProvider0(NameProvider nameProvider) {
        if (nameProvider == null) {
            nameProvider = getDefaultNameProvider();
        }
        cNameProvider = nameProvider;
    }

    private static void setProvider0(Provider provider) {
        if (provider == null) {
            provider = getDefaultProvider();
        }
        Set availableIDs = provider.getAvailableIDs();
        if (availableIDs != null && availableIDs.size() != 0) {
            if (availableIDs.contains("UTC")) {
                if (!UTC.equals(provider.getZone("UTC"))) {
                    throw new IllegalArgumentException("Invalid UTC zone provided");
                }
                cProvider = provider;
                cAvailableIDs = availableIDs;
                return;
            }
            throw new IllegalArgumentException("The provider doesn't support UTC");
        }
        throw new IllegalArgumentException("The provider doesn't have any available ids");
    }

    public long convertLocalToUTC(long j, boolean z) {
        long j2;
        int offset = getOffset(j);
        long j3 = j - offset;
        int offset2 = getOffset(j3);
        if (offset != offset2 && (z || offset < 0)) {
            long nextTransition = nextTransition(j3);
            if (nextTransition == j3) {
                nextTransition = Long.MAX_VALUE;
            }
            long j4 = j - offset2;
            long nextTransition2 = nextTransition(j4);
            if (nextTransition != (nextTransition2 != j4 ? nextTransition2 : Long.MAX_VALUE)) {
                if (z) {
                    throw new IllegalInstantException(j, getID());
                }
                long j5 = offset;
                j2 = j - j5;
                if ((j ^ j2) >= 0 && (j ^ j5) < 0) {
                    throw new ArithmeticException("Subtracting time zone offset caused overflow");
                }
                return j2;
            }
        }
        offset = offset2;
        long j52 = offset;
        j2 = j - j52;
        if ((j ^ j2) >= 0) {
        }
        return j2;
    }

    public long convertUTCToLocal(long j) {
        long offset = getOffset(j);
        long j2 = j + offset;
        if ((j ^ j2) < 0 && (j ^ offset) >= 0) {
            throw new ArithmeticException("Adding time zone offset caused overflow");
        }
        return j2;
    }

    public abstract boolean equals(Object obj);

    public final String getID() {
        return this.iID;
    }

    public String getName(long j, Locale locale) {
        String name;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String nameKey = getNameKey(j);
        if (nameKey == null) {
            return this.iID;
        }
        NameProvider nameProvider = getNameProvider();
        if (nameProvider instanceof DefaultNameProvider) {
            name = ((DefaultNameProvider) nameProvider).getName(locale, this.iID, nameKey, isStandardOffset(j));
        } else {
            name = nameProvider.getName(locale, this.iID, nameKey);
        }
        if (name != null) {
            return name;
        }
        return printOffset(getOffset(j));
    }

    public abstract String getNameKey(long j);

    public abstract int getOffset(long j);

    public int getOffsetFromLocal(long j) {
        int offset = getOffset(j);
        long j2 = j - offset;
        int offset2 = getOffset(j2);
        if (offset != offset2) {
            if (offset - offset2 < 0 && nextTransition(j2) != nextTransition(j - offset2)) {
                return offset;
            }
        } else if (offset >= 0) {
            long previousTransition = previousTransition(j2);
            if (previousTransition < j2) {
                int offset3 = getOffset(previousTransition);
                if (j2 - previousTransition <= offset3 - offset) {
                    return offset3;
                }
            }
        }
        return offset2;
    }

    public String getShortName(long j, Locale locale) {
        String shortName;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String nameKey = getNameKey(j);
        if (nameKey == null) {
            return this.iID;
        }
        NameProvider nameProvider = getNameProvider();
        if (nameProvider instanceof DefaultNameProvider) {
            shortName = ((DefaultNameProvider) nameProvider).getShortName(locale, this.iID, nameKey, isStandardOffset(j));
        } else {
            shortName = nameProvider.getShortName(locale, this.iID, nameKey);
        }
        if (shortName != null) {
            return shortName;
        }
        return printOffset(getOffset(j));
    }

    public abstract int getStandardOffset(long j);

    public int hashCode() {
        return getID().hashCode() + 57;
    }

    public abstract boolean isFixed();

    public boolean isStandardOffset(long j) {
        return getOffset(j) == getStandardOffset(j);
    }

    public abstract long nextTransition(long j);

    public abstract long previousTransition(long j);

    public String toString() {
        return getID();
    }

    protected Object writeReplace() {
        return new Stub(this.iID);
    }

    private static NameProvider getDefaultNameProvider() {
        NameProvider nameProvider = null;
        try {
            String property = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
            if (property != null) {
                try {
                    nameProvider = (NameProvider) Class.forName(property).newInstance();
                } catch (Exception e) {
                    Thread currentThread = Thread.currentThread();
                    currentThread.getThreadGroup().uncaughtException(currentThread, e);
                }
            }
        } catch (SecurityException e2) {
        }
        if (nameProvider == null) {
            return new DefaultNameProvider();
        }
        return nameProvider;
    }

    private static Provider getDefaultProvider() {
        ZoneInfoProvider zoneInfoProvider = null;
        try {
            String property = System.getProperty("org.joda.time.DateTimeZone.Provider");
            if (property != null) {
                try {
                    zoneInfoProvider = (Provider) Class.forName(property).newInstance();
                } catch (Exception e) {
                    Thread currentThread = Thread.currentThread();
                    currentThread.getThreadGroup().uncaughtException(currentThread, e);
                }
            }
        } catch (SecurityException e2) {
        }
        if (zoneInfoProvider == null) {
            try {
                zoneInfoProvider = new ZoneInfoProvider("org/joda/time/tz/data");
            } catch (Exception e3) {
                Thread currentThread2 = Thread.currentThread();
                currentThread2.getThreadGroup().uncaughtException(currentThread2, e3);
            }
        }
        if (zoneInfoProvider == null) {
            return new UTCProvider();
        }
        return zoneInfoProvider;
    }

    public long convertLocalToUTC(long j, boolean z, long j2) {
        int offset = getOffset(j2);
        long j3 = j - offset;
        if (getOffset(j3) == offset) {
            return j3;
        }
        return convertLocalToUTC(j, z);
    }
}
