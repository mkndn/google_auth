package org.joda.time.format;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.DateTime;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DateTimeFormat {
    private static final ConcurrentHashMap PATTERN_CACHE = new ConcurrentHashMap();
    private static final DateTimeFormatter[] STYLE_CACHE = new DateTimeFormatter[25];

    private static DateTimeFormatter createFormatterForPattern(String str) {
        DateTimeFormatter dateTimeFormatter;
        if (str != null && str.length() != 0) {
            ConcurrentHashMap concurrentHashMap = PATTERN_CACHE;
            DateTimeFormatter dateTimeFormatter2 = (DateTimeFormatter) concurrentHashMap.get(str);
            if (dateTimeFormatter2 == null) {
                DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
                parsePatternTo(dateTimeFormatterBuilder, str);
                dateTimeFormatter2 = dateTimeFormatterBuilder.toFormatter();
                if (concurrentHashMap.size() < 500 && (dateTimeFormatter = (DateTimeFormatter) concurrentHashMap.putIfAbsent(str, dateTimeFormatter2)) != null) {
                    return dateTimeFormatter;
                }
            }
            return dateTimeFormatter2;
        }
        throw new IllegalArgumentException("Invalid pattern specification");
    }

    public static DateTimeFormatter forPattern(String str) {
        return createFormatterForPattern(str);
    }

    private static boolean isNumericToken(String str) {
        int length = str.length();
        if (length > 0) {
            switch (str.charAt(0)) {
                case 'C':
                case 'D':
                case 'F':
                case 'H':
                case 'K':
                case 'S':
                case 'W':
                case 'Y':
                case 'c':
                case 'd':
                case 'e':
                case 'h':
                case 'k':
                case 'm':
                case 's':
                case 'w':
                case 'x':
                case 'y':
                    return true;
                case 'M':
                    if (length <= 2) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    private static void parsePatternTo(DateTimeFormatterBuilder dateTimeFormatterBuilder, String str) {
        boolean z;
        int length = str.length();
        int[] iArr = new int[1];
        int i = 0;
        while (i < length) {
            iArr[0] = i;
            String parseToken = parseToken(str, iArr);
            int i2 = iArr[0];
            int length2 = parseToken.length();
            if (length2 != 0) {
                char charAt = parseToken.charAt(0);
                switch (charAt) {
                    case '\'':
                        String substring = parseToken.substring(1);
                        if (substring.length() == 1) {
                            dateTimeFormatterBuilder.appendLiteral(substring.charAt(0));
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendLiteral(new String(substring));
                            break;
                        }
                    case 'C':
                        dateTimeFormatterBuilder.appendCenturyOfEra(length2, length2);
                        break;
                    case 'D':
                        dateTimeFormatterBuilder.appendDayOfYear(length2);
                        break;
                    case 'E':
                        if (length2 >= 4) {
                            dateTimeFormatterBuilder.appendDayOfWeekText();
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendDayOfWeekShortText();
                            break;
                        }
                    case 'G':
                        dateTimeFormatterBuilder.appendEraText();
                        break;
                    case 'H':
                        dateTimeFormatterBuilder.appendHourOfDay(length2);
                        break;
                    case 'K':
                        dateTimeFormatterBuilder.appendHourOfHalfday(length2);
                        break;
                    case 'M':
                        if (length2 >= 3) {
                            if (length2 >= 4) {
                                dateTimeFormatterBuilder.appendMonthOfYearText();
                                break;
                            } else {
                                dateTimeFormatterBuilder.appendMonthOfYearShortText();
                                break;
                            }
                        } else {
                            dateTimeFormatterBuilder.appendMonthOfYear(length2);
                            break;
                        }
                    case 'S':
                        dateTimeFormatterBuilder.appendFractionOfSecond(length2, length2);
                        break;
                    case 'Y':
                    case 'x':
                    case 'y':
                        if (length2 == 2) {
                            if (i2 + 1 < length) {
                                iArr[0] = iArr[0] + 1;
                                z = !isNumericToken(parseToken(str, iArr));
                                iArr[0] = iArr[0] - 1;
                            } else {
                                z = true;
                            }
                            switch (charAt) {
                                case 'x':
                                    dateTimeFormatterBuilder.appendTwoDigitWeekyear(new DateTime().getWeekyear() - 30, z);
                                    continue;
                                default:
                                    dateTimeFormatterBuilder.appendTwoDigitYear(new DateTime().getYear() - 30, z);
                                    continue;
                            }
                        } else {
                            if (i2 + 1 < length) {
                                iArr[0] = iArr[0] + 1;
                                r3 = isNumericToken(parseToken(str, iArr)) ? length2 : 9;
                                iArr[0] = iArr[0] - 1;
                            }
                            switch (charAt) {
                                case 'Y':
                                    dateTimeFormatterBuilder.appendYearOfEra(length2, r3);
                                    continue;
                                case 'x':
                                    dateTimeFormatterBuilder.appendWeekyear(length2, r3);
                                    continue;
                                case 'y':
                                    dateTimeFormatterBuilder.appendYear(length2, r3);
                                    continue;
                            }
                        }
                    case 'Z':
                        if (length2 == 1) {
                            dateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", false, 2, 2);
                            break;
                        } else if (length2 == 2) {
                            dateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", true, 2, 2);
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendTimeZoneId();
                            break;
                        }
                    case 'a':
                        dateTimeFormatterBuilder.appendHalfdayOfDayText();
                        break;
                    case 'd':
                        dateTimeFormatterBuilder.appendDayOfMonth(length2);
                        break;
                    case 'e':
                        dateTimeFormatterBuilder.appendDayOfWeek(length2);
                        break;
                    case 'h':
                        dateTimeFormatterBuilder.appendClockhourOfHalfday(length2);
                        break;
                    case 'k':
                        dateTimeFormatterBuilder.appendClockhourOfDay(length2);
                        break;
                    case 'm':
                        dateTimeFormatterBuilder.appendMinuteOfHour(length2);
                        break;
                    case 's':
                        dateTimeFormatterBuilder.appendSecondOfMinute(length2);
                        break;
                    case 'w':
                        dateTimeFormatterBuilder.appendWeekOfWeekyear(length2);
                        break;
                    case 'z':
                        if (length2 >= 4) {
                            dateTimeFormatterBuilder.appendTimeZoneName();
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendTimeZoneShortName(null);
                            break;
                        }
                    default:
                        String valueOf = String.valueOf(parseToken);
                        throw new IllegalArgumentException(valueOf.length() != 0 ? "Illegal pattern component: ".concat(valueOf) : new String("Illegal pattern component: "));
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private static String parseToken(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
            sb.append(charAt);
            while (true) {
                int i2 = i + 1;
                if (i2 >= length || str.charAt(i2) != charAt) {
                    break;
                }
                sb.append(charAt);
                i = i2;
            }
        } else {
            sb.append('\'');
            boolean z = false;
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 == '\'') {
                    int i3 = i + 1;
                    if (i3 < length && str.charAt(i3) == '\'') {
                        sb.append(charAt2);
                        i = i3;
                    } else {
                        z = !z;
                    }
                } else if (z || ((charAt2 < 'A' || charAt2 > 'Z') && (charAt2 < 'a' || charAt2 > 'z'))) {
                    sb.append(charAt2);
                } else {
                    i--;
                    break;
                }
                i++;
            }
        }
        iArr[0] = i;
        return sb.toString();
    }
}
