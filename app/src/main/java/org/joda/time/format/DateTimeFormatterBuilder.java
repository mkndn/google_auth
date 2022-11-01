package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DateTimeFormatterBuilder {
    private ArrayList iElementPairs = new ArrayList();
    private Object iFormatter;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class CharacterLiteral implements DateTimePrinter, DateTimeParser {
        private final char iValue;

        CharacterLiteral(char c) {
            this.iValue = c;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return 1;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return 1;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            char upperCase;
            char upperCase2;
            if (i >= str.length()) {
                return i ^ (-1);
            }
            char charAt = str.charAt(i);
            char c = this.iValue;
            if (charAt != c && (upperCase = Character.toUpperCase(charAt)) != (upperCase2 = Character.toUpperCase(c)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                return i ^ (-1);
            }
            return i + 1;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            stringBuffer.append(this.iValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Composite implements DateTimePrinter, DateTimeParser {
        private final int iParsedLengthEstimate;
        private final DateTimeParser[] iParsers;
        private final int iPrintedLengthEstimate;
        private final DateTimePrinter[] iPrinters;

        Composite(List list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (!arrayList.contains(null) && !arrayList.isEmpty()) {
                int size = arrayList.size();
                this.iPrinters = new DateTimePrinter[size];
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    DateTimePrinter dateTimePrinter = (DateTimePrinter) arrayList.get(i2);
                    i += dateTimePrinter.estimatePrintedLength();
                    this.iPrinters[i2] = dateTimePrinter;
                }
                this.iPrintedLengthEstimate = i;
            } else {
                this.iPrinters = null;
                this.iPrintedLengthEstimate = 0;
            }
            if (!arrayList2.contains(null) && !arrayList2.isEmpty()) {
                int size2 = arrayList2.size();
                this.iParsers = new DateTimeParser[size2];
                int i3 = 0;
                for (int i4 = 0; i4 < size2; i4++) {
                    DateTimeParser dateTimeParser = (DateTimeParser) arrayList2.get(i4);
                    i3 += dateTimeParser.estimateParsedLength();
                    this.iParsers[i4] = dateTimeParser;
                }
                this.iParsedLengthEstimate = i3;
                return;
            }
            this.iParsers = null;
            this.iParsedLengthEstimate = 0;
        }

        private void addArrayToList(List list, Object[] objArr) {
            if (objArr != null) {
                for (Object obj : objArr) {
                    list.add(obj);
                }
            }
        }

        private void decompose(List list, List list2, List list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof Composite) {
                    addArrayToList(list2, ((Composite) obj).iPrinters);
                } else {
                    list2.add(obj);
                }
                Object obj2 = list.get(i + 1);
                if (obj2 instanceof Composite) {
                    addArrayToList(list3, ((Composite) obj2).iParsers);
                } else {
                    list3.add(obj2);
                }
            }
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return this.iPrintedLengthEstimate;
        }

        boolean isParser() {
            return this.iParsers != null;
        }

        boolean isPrinter() {
            return this.iPrinters != null;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            DateTimeParser[] dateTimeParserArr = this.iParsers;
            if (dateTimeParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = dateTimeParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = dateTimeParserArr[i2].parseInto(dateTimeParserBucket, str, i);
            }
            return i;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            Locale locale2;
            DateTimePrinter[] dateTimePrinterArr = this.iPrinters;
            if (dateTimePrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            if (locale == null) {
                locale2 = Locale.getDefault();
            } else {
                locale2 = locale;
            }
            for (DateTimePrinter dateTimePrinter : dateTimePrinterArr) {
                dateTimePrinter.printTo(stringBuffer, j, chronology, i, dateTimeZone, locale2);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class FixedNumber extends PaddedNumber {
        protected FixedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z, i);
        }

        @Override // org.joda.time.format.DateTimeFormatterBuilder.NumberFormatter, org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            char charAt;
            int parseInto = super.parseInto(dateTimeParserBucket, str, i);
            if (parseInto < 0) {
                return parseInto;
            }
            int i2 = this.iMaxParsedDigits + i;
            if (parseInto != i2) {
                if (this.iSigned && ((charAt = str.charAt(i)) == '-' || charAt == '+')) {
                    i2++;
                }
                if (parseInto > i2) {
                    return (i2 + 1) ^ (-1);
                }
                if (parseInto < i2) {
                    return parseInto ^ (-1);
                }
            }
            return parseInto;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class MatchingParser implements DateTimeParser {
        private final int iParsedLengthEstimate;
        private final DateTimeParser[] iParsers;

        MatchingParser(DateTimeParser[] dateTimeParserArr) {
            int estimateParsedLength;
            this.iParsers = dateTimeParserArr;
            int length = dateTimeParserArr.length;
            int i = 0;
            while (true) {
                length--;
                if (length >= 0) {
                    DateTimeParser dateTimeParser = dateTimeParserArr[length];
                    if (dateTimeParser != null && (estimateParsedLength = dateTimeParser.estimateParsedLength()) > i) {
                        i = estimateParsedLength;
                    }
                } else {
                    this.iParsedLengthEstimate = i;
                    return;
                }
            }
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            int i2;
            int i3;
            DateTimeParser[] dateTimeParserArr = this.iParsers;
            int length = dateTimeParserArr.length;
            Object saveState = dateTimeParserBucket.saveState();
            Object obj = null;
            boolean z = false;
            int i4 = i;
            int i5 = i4;
            int i6 = 0;
            while (true) {
                if (i6 >= length) {
                    break;
                }
                DateTimeParser dateTimeParser = dateTimeParserArr[i6];
                if (dateTimeParser != null) {
                    int parseInto = dateTimeParser.parseInto(dateTimeParserBucket, str, i);
                    if (parseInto >= i) {
                        if (parseInto <= i4) {
                            continue;
                        } else if (parseInto >= str.length() || (i3 = i6 + 1) >= length || dateTimeParserArr[i3] == null) {
                            break;
                        } else {
                            obj = dateTimeParserBucket.saveState();
                            i4 = parseInto;
                        }
                    } else if (parseInto < 0 && (i2 = parseInto ^ (-1)) > i5) {
                        i5 = i2;
                    }
                    dateTimeParserBucket.restoreState(saveState);
                    i6++;
                } else if (i4 <= i) {
                    return i;
                } else {
                    z = true;
                }
            }
            if (i4 <= i && (i4 != i || !z)) {
                return i5 ^ (-1);
            }
            if (obj != null) {
                dateTimeParserBucket.restoreState(obj);
            }
            return i4;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class NumberFormatter implements DateTimePrinter, DateTimeParser {
        protected final DateTimeFieldType iFieldType;
        protected final int iMaxParsedDigits;
        protected final boolean iSigned;

        NumberFormatter(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iMaxParsedDigits = i;
            this.iSigned = z;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return this.iMaxParsedDigits;
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0056, code lost:
            if (r5 <= '9') goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0059, code lost:
            r2 = r2 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0060, code lost:
            return r13 ^ (-1);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            int i2;
            int i3;
            char charAt;
            int min = Math.min(this.iMaxParsedDigits, str.length() - i);
            int i4 = 0;
            boolean z = false;
            while (i4 < min) {
                int i5 = i + i4;
                char charAt2 = str.charAt(i5);
                if (i4 != 0 || ((charAt2 != '-' && charAt2 != '+') || !this.iSigned)) {
                    break;
                }
                z = charAt2 == '-';
                int i6 = i4 + 1;
                if (i6 >= min || (charAt = str.charAt(i5 + 1)) < '0' || charAt > '9') {
                    break;
                }
                if (z) {
                    i4 = i6;
                } else {
                    i++;
                }
                min = Math.min(min + 1, str.length() - i);
            }
            if (i4 >= 9) {
                i2 = i4 + i;
                i3 = Integer.parseInt(str.substring(i, i2));
            } else {
                int i7 = z ? i + 1 : i;
                try {
                    int charAt3 = str.charAt(i7) - '0';
                    i2 = i4 + i;
                    for (int i8 = i7 + 1; i8 < i2; i8++) {
                        charAt3 = (((charAt3 << 3) + (charAt3 + charAt3)) + str.charAt(i8)) - 48;
                    }
                    i3 = z ? -charAt3 : charAt3;
                } catch (StringIndexOutOfBoundsException e) {
                    return i ^ (-1);
                }
            }
            dateTimeParserBucket.saveField(this.iFieldType, i3);
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class PaddedNumber extends NumberFormatter {
        protected final int iMinPrintedDigits;

        protected PaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z, int i2) {
            super(dateTimeFieldType, i, z);
            this.iMinPrintedDigits = i2;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            try {
                FormatUtils.appendPaddedInteger(stringBuffer, this.iFieldType.getField(chronology).get(j), this.iMinPrintedDigits);
            } catch (RuntimeException e) {
                DateTimeFormatterBuilder.appendUnknownString(stringBuffer, this.iMinPrintedDigits);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class StringLiteral implements DateTimePrinter, DateTimeParser {
        private final String iValue;

        StringLiteral(String str) {
            this.iValue = str;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return this.iValue.length();
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return this.iValue.length();
        }

        @Override // org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            String str2 = this.iValue;
            if (str.regionMatches(true, i, str2, 0, str2.length())) {
                return i + this.iValue.length();
            }
            return i ^ (-1);
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            stringBuffer.append(this.iValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class TextField implements DateTimePrinter, DateTimeParser {
        private static Map cParseCache = new HashMap();
        private final DateTimeFieldType iFieldType;
        private final boolean iShort;

        TextField(DateTimeFieldType dateTimeFieldType, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iShort = z;
        }

        private String print(long j, Chronology chronology, Locale locale) {
            DateTimeField field = this.iFieldType.getField(chronology);
            if (this.iShort) {
                return field.getAsShortText(j, locale);
            }
            return field.getAsText(j, locale);
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return this.iShort ? 6 : 20;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            int intValue;
            Set set;
            Locale locale = dateTimeParserBucket.getLocale();
            synchronized (cParseCache) {
                Map map = (Map) cParseCache.get(locale);
                if (map == null) {
                    map = new HashMap();
                    cParseCache.put(locale, map);
                }
                Object[] objArr = (Object[]) map.get(this.iFieldType);
                if (objArr == null) {
                    set = new HashSet(32);
                    MutableDateTime.Property property = new MutableDateTime(0L, DateTimeZone.UTC).property(this.iFieldType);
                    int minimumValueOverall = property.getMinimumValueOverall();
                    int maximumValueOverall = property.getMaximumValueOverall();
                    if (maximumValueOverall - minimumValueOverall > 32) {
                        return i ^ (-1);
                    }
                    intValue = property.getMaximumTextLength(locale);
                    while (minimumValueOverall <= maximumValueOverall) {
                        property.set(minimumValueOverall);
                        set.add(property.getAsShortText(locale));
                        set.add(property.getAsShortText(locale).toLowerCase(locale));
                        set.add(property.getAsShortText(locale).toUpperCase(locale));
                        set.add(property.getAsText(locale));
                        set.add(property.getAsText(locale).toLowerCase(locale));
                        set.add(property.getAsText(locale).toUpperCase(locale));
                        minimumValueOverall++;
                    }
                    if ("en".equals(locale.getLanguage()) && this.iFieldType == DateTimeFieldType.era()) {
                        set.add("BCE");
                        set.add("bce");
                        set.add("CE");
                        set.add("ce");
                        intValue = 3;
                    }
                    map.put(this.iFieldType, new Object[]{set, Integer.valueOf(intValue)});
                } else {
                    intValue = ((Integer) objArr[1]).intValue();
                    set = (Set) objArr[0];
                }
                for (int min = Math.min(str.length(), intValue + i); min > i; min--) {
                    String substring = str.substring(i, min);
                    if (set.contains(substring)) {
                        dateTimeParserBucket.saveField(this.iFieldType, substring, locale);
                        return min;
                    }
                }
                return i ^ (-1);
            }
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            try {
                stringBuffer.append(print(j, chronology, locale));
            } catch (RuntimeException e) {
                stringBuffer.append((char) 65533);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    enum TimeZoneId implements DateTimePrinter, DateTimeParser {
        INSTANCE;
        
        static final Set ALL_IDS;
        static final int MAX_LENGTH;

        static {
            int i = 0;
            Set<String> availableIDs = DateTimeZone.getAvailableIDs();
            ALL_IDS = availableIDs;
            for (String str : availableIDs) {
                i = Math.max(i, str.length());
            }
            MAX_LENGTH = i;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return MAX_LENGTH;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return MAX_LENGTH;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            String substring = str.substring(i);
            String str2 = null;
            for (String str3 : ALL_IDS) {
                if (substring.startsWith(str3) && (str2 == null || str3.length() > str2.length())) {
                    str2 = str3;
                }
            }
            if (str2 != null) {
                dateTimeParserBucket.setZone(DateTimeZone.forID(str2));
                return i + str2.length();
            }
            return i ^ (-1);
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            stringBuffer.append(dateTimeZone != null ? dateTimeZone.getID() : "");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class TimeZoneName implements DateTimePrinter, DateTimeParser {
        private final Map iParseLookup;
        private final int iType;

        TimeZoneName(int i, Map map) {
            this.iType = i;
            this.iParseLookup = map;
        }

        private String print(long j, DateTimeZone dateTimeZone, Locale locale) {
            if (dateTimeZone == null) {
                return "";
            }
            switch (this.iType) {
                case 0:
                    return dateTimeZone.getName(j, locale);
                case 1:
                    return dateTimeZone.getShortName(j, locale);
                default:
                    return "";
            }
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            Map map = this.iParseLookup;
            if (map == null) {
                map = DateTimeUtils.getDefaultTimeZoneNames();
            }
            String substring = str.substring(i);
            String str2 = null;
            for (String str3 : map.keySet()) {
                if (substring.startsWith(str3) && (str2 == null || str3.length() > str2.length())) {
                    str2 = str3;
                }
            }
            if (str2 != null) {
                dateTimeParserBucket.setZone((DateTimeZone) map.get(str2));
                return i + str2.length();
            }
            return i ^ (-1);
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            stringBuffer.append(print(j - i, dateTimeZone, locale));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class TimeZoneOffset implements DateTimePrinter, DateTimeParser {
        private final int iMaxFields;
        private final int iMinFields;
        private final boolean iShowSeparators;
        private final String iZeroOffsetParseText;
        private final String iZeroOffsetPrintText;

        TimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
            this.iZeroOffsetPrintText = str;
            this.iZeroOffsetParseText = str2;
            this.iShowSeparators = z;
            if (i > 0 && i2 >= i) {
                if (i > 4) {
                    i = 4;
                    i2 = 4;
                }
                this.iMinFields = i;
                this.iMaxFields = i2;
                return;
            }
            throw new IllegalArgumentException();
        }

        private int digitCount(String str, int i, int i2) {
            int i3 = 0;
            for (int min = Math.min(str.length() - i, i2); min > 0; min--) {
                char charAt = str.charAt(i + i3);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i3++;
            }
            return i3;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            int i = this.iMinFields;
            int i2 = i + 1;
            int i3 = i2 + i2;
            if (this.iShowSeparators) {
                i3 += i - 1;
            }
            String str = this.iZeroOffsetPrintText;
            return (str == null || str.length() <= i3) ? i3 : this.iZeroOffsetPrintText.length();
        }

        /* JADX WARN: Code restructure failed: missing block: B:44:0x008c, code lost:
            if (r6 <= '9') goto L36;
         */
        @Override // org.joda.time.format.DateTimeParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            boolean z;
            char charAt;
            int length = str.length() - i;
            String str2 = this.iZeroOffsetParseText;
            boolean z2 = false;
            if (str2 != null) {
                if (str2.length() != 0) {
                    String str3 = this.iZeroOffsetParseText;
                    if (str.regionMatches(true, i, str3, 0, str3.length())) {
                        dateTimeParserBucket.setOffset(0);
                        return i + this.iZeroOffsetParseText.length();
                    }
                } else if (length <= 0 || ((charAt = str.charAt(i)) != '-' && charAt != '+')) {
                    dateTimeParserBucket.setOffset(0);
                    return i;
                }
            }
            if (length <= 1) {
                return i ^ (-1);
            }
            char charAt2 = str.charAt(i);
            if (charAt2 == '-') {
                z = true;
            } else if (charAt2 != '+') {
                return i ^ (-1);
            } else {
                z = false;
            }
            int i2 = i + 1;
            if (digitCount(str, i2, 2) < 2) {
                return i2 ^ (-1);
            }
            int parseTwoDigits = FormatUtils.parseTwoDigits(str, i2);
            if (parseTwoDigits > 23) {
                return i2 ^ (-1);
            }
            int i3 = parseTwoDigits * 3600000;
            int i4 = length - 3;
            int i5 = i2 + 2;
            if (i4 > 0) {
                char charAt3 = str.charAt(i5);
                if (charAt3 == ':') {
                    i4--;
                    i5++;
                    z2 = true;
                } else if (charAt3 >= '0') {
                }
                int digitCount = digitCount(str, i5, 2);
                if (digitCount != 0 || z2) {
                    if (digitCount < 2) {
                        return i5 ^ (-1);
                    }
                    int parseTwoDigits2 = FormatUtils.parseTwoDigits(str, i5);
                    if (parseTwoDigits2 > 59) {
                        return i5 ^ (-1);
                    }
                    i3 += parseTwoDigits2 * 60000;
                    int i6 = i4 - 2;
                    i5 += 2;
                    if (i6 > 0) {
                        if (z2) {
                            if (str.charAt(i5) == ':') {
                                i6--;
                                i5++;
                            }
                        }
                        int digitCount2 = digitCount(str, i5, 2);
                        if (digitCount2 != 0 || z2) {
                            if (digitCount2 < 2) {
                                return i5 ^ (-1);
                            }
                            int parseTwoDigits3 = FormatUtils.parseTwoDigits(str, i5);
                            if (parseTwoDigits3 > 59) {
                                return i5 ^ (-1);
                            }
                            i3 += parseTwoDigits3 * 1000;
                            i5 += 2;
                            if (i6 - 2 > 0) {
                                if (z2) {
                                    if (str.charAt(i5) == '.' || str.charAt(i5) == ',') {
                                        i5++;
                                    }
                                }
                                int digitCount3 = digitCount(str, i5, 3);
                                if (digitCount3 != 0 || z2) {
                                    if (digitCount3 <= 0) {
                                        return i5 ^ (-1);
                                    }
                                    int i7 = i5 + 1;
                                    i3 += (str.charAt(i5) - '0') * 100;
                                    if (digitCount3 > 1) {
                                        i5 = i7 + 1;
                                        i3 += (str.charAt(i7) - '0') * 10;
                                        if (digitCount3 > 2) {
                                            i3 += str.charAt(i5) - '0';
                                            i5++;
                                        }
                                    } else {
                                        i5 = i7;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (z) {
                i3 = -i3;
            }
            dateTimeParserBucket.setOffset(Integer.valueOf(i3));
            return i5;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            String str;
            if (dateTimeZone == null) {
                return;
            }
            if (i == 0 && (str = this.iZeroOffsetPrintText) != null) {
                stringBuffer.append(str);
                return;
            }
            if (i >= 0) {
                stringBuffer.append('+');
            } else {
                stringBuffer.append('-');
                i = -i;
            }
            int i2 = i / 3600000;
            FormatUtils.appendPaddedInteger(stringBuffer, i2, 2);
            if (this.iMaxFields == 1) {
                return;
            }
            int i3 = i - (i2 * 3600000);
            if (i3 == 0 && this.iMinFields <= 1) {
                return;
            }
            int i4 = i3 / 60000;
            if (this.iShowSeparators) {
                stringBuffer.append(':');
            }
            FormatUtils.appendPaddedInteger(stringBuffer, i4, 2);
            if (this.iMaxFields == 2) {
                return;
            }
            int i5 = i3 - (i4 * 60000);
            if (i5 == 0 && this.iMinFields <= 2) {
                return;
            }
            int i6 = i5 / 1000;
            if (this.iShowSeparators) {
                stringBuffer.append(':');
            }
            FormatUtils.appendPaddedInteger(stringBuffer, i6, 2);
            if (this.iMaxFields == 3) {
                return;
            }
            int i7 = i5 - (i6 * 1000);
            if (i7 == 0 && this.iMinFields <= 3) {
                return;
            }
            if (this.iShowSeparators) {
                stringBuffer.append('.');
            }
            FormatUtils.appendPaddedInteger(stringBuffer, i7, 3);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class TwoDigitYear implements DateTimePrinter, DateTimeParser {
        private final boolean iLenientParse;
        private final int iPivot;
        private final DateTimeFieldType iType;

        TwoDigitYear(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iType = dateTimeFieldType;
            this.iPivot = i;
            this.iLenientParse = z;
        }

        private int getTwoDigitYear(long j, Chronology chronology) {
            try {
                int i = this.iType.getField(chronology).get(j);
                if (i < 0) {
                    i = -i;
                }
                return i % 100;
            } catch (RuntimeException e) {
                return -1;
            }
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return this.iLenientParse ? 4 : 2;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return 2;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            int i2;
            int i3;
            int i4;
            int i5;
            int length = str.length() - i;
            if (!this.iLenientParse) {
                if (Math.min(2, length) < 2) {
                    return i ^ (-1);
                }
            } else {
                int i6 = 0;
                boolean z = false;
                boolean z2 = false;
                while (i6 < length) {
                    char charAt = str.charAt(i + i6);
                    if (i6 == 0 && (charAt == '-' || charAt == '+')) {
                        z2 = charAt == '-';
                        if (z2) {
                            i6++;
                            z = true;
                        } else {
                            i++;
                            length--;
                            z = true;
                        }
                    } else if (charAt < '0' || charAt > '9') {
                        break;
                    } else {
                        i6++;
                    }
                }
                if (i6 == 0) {
                    return i ^ (-1);
                }
                if (z || i6 != 2) {
                    if (i6 >= 9) {
                        i3 = i6 + i;
                        i4 = Integer.parseInt(str.substring(i, i3));
                    } else {
                        if (z2) {
                            i2 = i + 1;
                        } else {
                            i2 = i;
                        }
                        try {
                            int charAt2 = str.charAt(i2) - '0';
                            i3 = i6 + i;
                            for (int i7 = i2 + 1; i7 < i3; i7++) {
                                charAt2 = (((charAt2 << 3) + (charAt2 + charAt2)) + str.charAt(i7)) - 48;
                            }
                            if (z2) {
                                i4 = -charAt2;
                            } else {
                                i4 = charAt2;
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                            return i ^ (-1);
                        }
                    }
                    dateTimeParserBucket.saveField(this.iType, i4);
                    return i3;
                }
            }
            char charAt3 = str.charAt(i);
            if (charAt3 < '0' || charAt3 > '9') {
                return i ^ (-1);
            }
            int i8 = charAt3 - '0';
            char charAt4 = str.charAt(i + 1);
            if (charAt4 >= '0' && charAt4 <= '9') {
                int i9 = (((i8 << 3) + (i8 + i8)) + charAt4) - 48;
                int i10 = this.iPivot;
                if (dateTimeParserBucket.getPivotYear() != null) {
                    i10 = dateTimeParserBucket.getPivotYear().intValue();
                }
                int i11 = i10 - 50;
                if (i11 < 0) {
                    i5 = ((i11 + 1) % 100) + 99;
                } else {
                    i5 = i11 % 100;
                }
                dateTimeParserBucket.saveField(this.iType, i9 + ((i11 + (i9 < i5 ? 100 : 0)) - i5));
                return i + 2;
            }
            return i ^ (-1);
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            int twoDigitYear = getTwoDigitYear(j, chronology);
            if (twoDigitYear < 0) {
                stringBuffer.append((char) 65533);
                stringBuffer.append((char) 65533);
                return;
            }
            FormatUtils.appendPaddedInteger(stringBuffer, twoDigitYear, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class UnpaddedNumber extends NumberFormatter {
        protected UnpaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z);
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            try {
                FormatUtils.appendUnpaddedInteger(stringBuffer, this.iFieldType.getField(chronology).get(j));
            } catch (RuntimeException e) {
                stringBuffer.append((char) 65533);
            }
        }
    }

    private DateTimeFormatterBuilder append0(Object obj) {
        this.iFormatter = null;
        this.iElementPairs.add(obj);
        this.iElementPairs.add(obj);
        return this;
    }

    static void appendUnknownString(StringBuffer stringBuffer, int i) {
        while (true) {
            i--;
            if (i >= 0) {
                stringBuffer.append((char) 65533);
            } else {
                return;
            }
        }
    }

    private void checkParser(DateTimeParser dateTimeParser) {
        if (dateTimeParser == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
    }

    private void checkPrinter(DateTimePrinter dateTimePrinter) {
        if (dateTimePrinter == null) {
            throw new IllegalArgumentException("No printer supplied");
        }
    }

    private Object getFormatter() {
        Object obj = this.iFormatter;
        if (obj == null) {
            if (this.iElementPairs.size() == 2) {
                Object obj2 = this.iElementPairs.get(0);
                Object obj3 = this.iElementPairs.get(1);
                if (obj2 != null) {
                    if (obj2 == obj3 || obj3 == null) {
                        obj = obj2;
                    }
                } else {
                    obj = obj3;
                }
            }
            if (obj == null) {
                obj = new Composite(this.iElementPairs);
            }
            this.iFormatter = obj;
        }
        return obj;
    }

    private boolean isParser(Object obj) {
        if (obj instanceof DateTimeParser) {
            if (obj instanceof Composite) {
                return ((Composite) obj).isParser();
            }
            return true;
        }
        return false;
    }

    private boolean isPrinter(Object obj) {
        if (obj instanceof DateTimePrinter) {
            if (obj instanceof Composite) {
                return ((Composite) obj).isPrinter();
            }
            return true;
        }
        return false;
    }

    static void printUnknownString(Writer writer, int i) {
        while (true) {
            i--;
            if (i >= 0) {
                writer.write(65533);
            } else {
                return;
            }
        }
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            throw new IllegalArgumentException("No formatter supplied");
        }
        return append0(dateTimeFormatter.getPrinter(), dateTimeFormatter.getParser());
    }

    public DateTimeFormatterBuilder appendCenturyOfEra(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendClockhourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfMonth(int i) {
        return appendDecimal(DateTimeFieldType.dayOfMonth(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfWeek(int i) {
        return appendDecimal(DateTimeFieldType.dayOfWeek(), i, 1);
    }

    public DateTimeFormatterBuilder appendDayOfWeekShortText() {
        return appendShortText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendDayOfWeekText() {
        return appendText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendDayOfYear(int i) {
        return appendDecimal(DateTimeFieldType.dayOfYear(), i, 3);
    }

    public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        }
        if (i <= 1) {
            return append0(new UnpaddedNumber(dateTimeFieldType, i2, false));
        }
        return append0(new PaddedNumber(dateTimeFieldType, i2, false, i));
    }

    public DateTimeFormatterBuilder appendEraText() {
        return appendText(DateTimeFieldType.era());
    }

    public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException(new StringBuilder(37).append("Illegal number of digits: ").append(i).toString());
        }
        return append0(new FixedNumber(dateTimeFieldType, i, false));
    }

    public DateTimeFormatterBuilder appendFraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i >= 0 && i2 > 0) {
            return append0(new Fraction(dateTimeFieldType, i, i2));
        }
        throw new IllegalArgumentException();
    }

    public DateTimeFormatterBuilder appendFractionOfHour(int i, int i2) {
        return appendFraction(DateTimeFieldType.hourOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfMinute(int i, int i2) {
        return appendFraction(DateTimeFieldType.minuteOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfSecond(int i, int i2) {
        return appendFraction(DateTimeFieldType.secondOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendHalfdayOfDayText() {
        return appendText(DateTimeFieldType.halfdayOfDay());
    }

    public DateTimeFormatterBuilder appendHourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.hourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.hourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        return append0(new CharacterLiteral(c));
    }

    public DateTimeFormatterBuilder appendMinuteOfHour(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfHour(), i, 2);
    }

    public DateTimeFormatterBuilder appendMonthOfYear(int i) {
        return appendDecimal(DateTimeFieldType.monthOfYear(), i, 2);
    }

    public DateTimeFormatterBuilder appendMonthOfYearShortText() {
        return appendShortText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendMonthOfYearText() {
        return appendText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, new MatchingParser(new DateTimeParser[]{dateTimeParser, null}));
    }

    public DateTimeFormatterBuilder appendSecondOfMinute(int i) {
        return appendDecimal(DateTimeFieldType.secondOfMinute(), i, 2);
    }

    public DateTimeFormatterBuilder appendShortText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        return append0(new TextField(dateTimeFieldType, true));
    }

    public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i >= 0 && i2 > 0) {
            if (i <= 1) {
                return append0(new UnpaddedNumber(dateTimeFieldType, i2, true));
            }
            return append0(new PaddedNumber(dateTimeFieldType, i2, true, i));
        }
        throw new IllegalArgumentException();
    }

    public DateTimeFormatterBuilder appendText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        return append0(new TextField(dateTimeFieldType, false));
    }

    public DateTimeFormatterBuilder appendTimeZoneId() {
        return append0(TimeZoneId.INSTANCE, TimeZoneId.INSTANCE);
    }

    public DateTimeFormatterBuilder appendTimeZoneName() {
        return append0(new TimeZoneName(0, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str2, z, i, i2));
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName(Map map) {
        TimeZoneName timeZoneName = new TimeZoneName(1, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), i, z));
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.year(), i, z));
    }

    public DateTimeFormatterBuilder appendWeekOfWeekyear(int i) {
        return appendDecimal(DateTimeFieldType.weekOfWeekyear(), i, 2);
    }

    public DateTimeFormatterBuilder appendWeekyear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.weekyear(), i, i2);
    }

    public DateTimeFormatterBuilder appendYear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.year(), i, i2);
    }

    public DateTimeFormatterBuilder appendYearOfEra(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfEra(), i, i2);
    }

    public DateTimeFormatter toFormatter() {
        DateTimePrinter dateTimePrinter;
        Object formatter = getFormatter();
        if (isPrinter(formatter)) {
            dateTimePrinter = (DateTimePrinter) formatter;
        } else {
            dateTimePrinter = null;
        }
        DateTimeParser dateTimeParser = isParser(formatter) ? (DateTimeParser) formatter : null;
        if (dateTimePrinter == null && dateTimeParser == null) {
            throw new UnsupportedOperationException("Both printing and parsing not supported");
        }
        return new DateTimeFormatter(dateTimePrinter, dateTimeParser);
    }

    public DateTimeParser toParser() {
        Object formatter = getFormatter();
        if (isParser(formatter)) {
            return (DateTimeParser) formatter;
        }
        throw new UnsupportedOperationException("Parsing is not supported");
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        switch (str.length()) {
            case 0:
                return this;
            case 1:
                return append0(new CharacterLiteral(str.charAt(0)));
            default:
                return append0(new StringLiteral(str));
        }
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str, z, i, i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Fraction implements DateTimePrinter, DateTimeParser {
        private final DateTimeFieldType iFieldType;
        protected int iMaxDigits;
        protected int iMinDigits;

        protected Fraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
            this.iFieldType = dateTimeFieldType;
            i2 = i2 > 18 ? 18 : i2;
            this.iMinDigits = i;
            this.iMaxDigits = i2;
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x008d A[LOOP:0: B:3:0x000a->B:28:0x008d, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x007f A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private long[] getFractionData(long j, DateTimeField dateTimeField) {
            long j2;
            long unitMillis = dateTimeField.getDurationField().getUnitMillis();
            int i = this.iMaxDigits;
            while (true) {
                switch (i) {
                    case 1:
                        j2 = 10;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                            return new long[]{(j * j2) / unitMillis, i};
                        }
                        i--;
                    case 2:
                        j2 = 100;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 3:
                        j2 = 1000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 4:
                        j2 = 10000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 5:
                        j2 = 100000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 6:
                        j2 = 1000000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 7:
                        j2 = 10000000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 8:
                        j2 = 100000000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 9:
                        j2 = 1000000000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 10:
                        j2 = 10000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 11:
                        j2 = 100000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 12:
                        j2 = 1000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 13:
                        j2 = 10000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 14:
                        j2 = 100000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 15:
                        j2 = 1000000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 16:
                        j2 = 10000000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 17:
                        j2 = 100000000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 18:
                        j2 = 1000000000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    default:
                        j2 = 1;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                }
            }
        }

        @Override // org.joda.time.format.DateTimeParser
        public int estimateParsedLength() {
            return this.iMaxDigits;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public int estimatePrintedLength() {
            return this.iMaxDigits;
        }

        @Override // org.joda.time.format.DateTimeParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            DateTimeField field = this.iFieldType.getField(dateTimeParserBucket.getChronology());
            int min = Math.min(this.iMaxDigits, str.length() - i);
            long unitMillis = field.getDurationField().getUnitMillis() * 10;
            long j = 0;
            int i2 = 0;
            while (i2 < min) {
                char charAt = str.charAt(i + i2);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i2++;
                unitMillis /= 10;
                j += (charAt - '0') * unitMillis;
            }
            long j2 = j / 10;
            if (i2 == 0) {
                return i ^ (-1);
            }
            if (j2 > 2147483647L) {
                return i ^ (-1);
            }
            dateTimeParserBucket.saveField(new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, field.getDurationField()), (int) j2);
            return i + i2;
        }

        @Override // org.joda.time.format.DateTimePrinter
        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            try {
                printTo(stringBuffer, null, j, chronology);
            } catch (IOException e) {
            }
        }

        protected void printTo(StringBuffer stringBuffer, Writer writer, long j, Chronology chronology) {
            String l;
            DateTimeField field = this.iFieldType.getField(chronology);
            int i = this.iMinDigits;
            try {
                long remainder = field.remainder(j);
                if (remainder == 0) {
                    if (stringBuffer != null) {
                        while (true) {
                            i--;
                            if (i >= 0) {
                                stringBuffer.append('0');
                            } else {
                                return;
                            }
                        }
                    } else {
                        while (true) {
                            i--;
                            if (i >= 0) {
                                writer.write(48);
                            } else {
                                return;
                            }
                        }
                    }
                } else {
                    long[] fractionData = getFractionData(remainder, field);
                    int i2 = 0;
                    long j2 = fractionData[0];
                    int i3 = (int) fractionData[1];
                    if ((2147483647L & j2) == j2) {
                        l = Integer.toString((int) j2);
                    } else {
                        l = Long.toString(j2);
                    }
                    int length = l.length();
                    while (length < i3) {
                        if (stringBuffer != null) {
                            stringBuffer.append('0');
                        } else {
                            writer.write(48);
                        }
                        i--;
                        i3--;
                    }
                    if (i < i3) {
                        while (i < i3 && length > 1) {
                            int i4 = length - 1;
                            if (l.charAt(i4) != '0') {
                                break;
                            }
                            i3--;
                            length = i4;
                        }
                        if (length < l.length()) {
                            if (stringBuffer != null) {
                                while (i2 < length) {
                                    stringBuffer.append(l.charAt(i2));
                                    i2++;
                                }
                                return;
                            }
                            while (i2 < length) {
                                writer.write(l.charAt(i2));
                                i2++;
                            }
                            return;
                        }
                    }
                    if (stringBuffer != null) {
                        stringBuffer.append(l);
                    } else {
                        writer.write(l);
                    }
                }
            } catch (RuntimeException e) {
                if (stringBuffer != null) {
                    DateTimeFormatterBuilder.appendUnknownString(stringBuffer, i);
                } else {
                    DateTimeFormatterBuilder.printUnknownString(writer, i);
                }
            }
        }
    }

    public DateTimeFormatterBuilder append(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, dateTimeParser);
    }

    private DateTimeFormatterBuilder append0(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser) {
        this.iFormatter = null;
        this.iElementPairs.add(dateTimePrinter);
        this.iElementPairs.add(dateTimeParser);
        return this;
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser[] dateTimeParserArr) {
        if (dateTimePrinter != null) {
            checkPrinter(dateTimePrinter);
        }
        if (dateTimeParserArr == null) {
            throw new IllegalArgumentException("No parsers supplied");
        }
        int length = dateTimeParserArr.length;
        int i = 0;
        if (length == 1) {
            DateTimeParser dateTimeParser = dateTimeParserArr[0];
            if (dateTimeParser == null) {
                throw new IllegalArgumentException("No parser supplied");
            }
            return append0(dateTimePrinter, dateTimeParser);
        }
        DateTimeParser[] dateTimeParserArr2 = new DateTimeParser[length];
        while (i < length - 1) {
            DateTimeParser dateTimeParser2 = dateTimeParserArr[i];
            dateTimeParserArr2[i] = dateTimeParser2;
            if (dateTimeParser2 == null) {
                throw new IllegalArgumentException("Incomplete parser array");
            }
            i++;
        }
        dateTimeParserArr2[i] = dateTimeParserArr[i];
        return append0(dateTimePrinter, new MatchingParser(dateTimeParserArr2));
    }
}
