package org.joda.time.format;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DateTimeFormatter {
    private final Chronology iChrono;
    private final int iDefaultYear;
    private final Locale iLocale;
    private final boolean iOffsetParsed;
    private final DateTimeParser iParser;
    private final Integer iPivotYear;
    private final DateTimePrinter iPrinter;
    private final DateTimeZone iZone;

    public DateTimeFormatter(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser) {
        this.iPrinter = dateTimePrinter;
        this.iParser = dateTimeParser;
        this.iLocale = null;
        this.iOffsetParsed = false;
        this.iChrono = null;
        this.iZone = null;
        this.iPivotYear = null;
        this.iDefaultYear = CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
    }

    private void printTo(StringBuffer stringBuffer, long j, Chronology chronology) {
        DateTimePrinter requirePrinter = requirePrinter();
        Chronology selectChronology = selectChronology(chronology);
        DateTimeZone zone = selectChronology.getZone();
        int offset = zone.getOffset(j);
        long j2 = offset;
        long j3 = j + j2;
        if ((j ^ j3) < 0 && (j2 ^ j) >= 0) {
            zone = DateTimeZone.UTC;
            offset = 0;
            j3 = j;
        }
        requirePrinter.printTo(stringBuffer, j3, selectChronology.withUTC(), offset, zone, this.iLocale);
    }

    private DateTimeParser requireParser() {
        DateTimeParser dateTimeParser = this.iParser;
        if (dateTimeParser == null) {
            throw new UnsupportedOperationException("Parsing not supported");
        }
        return dateTimeParser;
    }

    private DateTimePrinter requirePrinter() {
        DateTimePrinter dateTimePrinter = this.iPrinter;
        if (dateTimePrinter == null) {
            throw new UnsupportedOperationException("Printing not supported");
        }
        return dateTimePrinter;
    }

    private Chronology selectChronology(Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        Chronology chronology3 = this.iChrono;
        if (chronology3 != null) {
            chronology2 = chronology3;
        }
        DateTimeZone dateTimeZone = this.iZone;
        if (dateTimeZone != null) {
            return chronology2.withZone(dateTimeZone);
        }
        return chronology2;
    }

    public DateTimeParser getParser() {
        return this.iParser;
    }

    public DateTimePrinter getPrinter() {
        return this.iPrinter;
    }

    public long parseMillis(String str) {
        DateTimeParser requireParser = requireParser();
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0L, selectChronology(this.iChrono), this.iLocale, this.iPivotYear, this.iDefaultYear);
        int parseInto = requireParser.parseInto(dateTimeParserBucket, str, 0);
        if (parseInto >= 0) {
            if (parseInto >= str.length()) {
                return dateTimeParserBucket.computeMillis(true, str);
            }
        } else {
            parseInto ^= -1;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, parseInto));
    }

    public String print(ReadableInstant readableInstant) {
        StringBuffer stringBuffer = new StringBuffer(requirePrinter().estimatePrintedLength());
        printTo(stringBuffer, readableInstant);
        return stringBuffer.toString();
    }

    public DateTimeFormatter withChronology(Chronology chronology) {
        if (this.iChrono == chronology) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, chronology, this.iZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withZone(DateTimeZone dateTimeZone) {
        if (this.iZone == dateTimeZone) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, false, this.iChrono, dateTimeZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withZoneUTC() {
        return withZone(DateTimeZone.UTC);
    }

    private DateTimeFormatter(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser, Locale locale, boolean z, Chronology chronology, DateTimeZone dateTimeZone, Integer num, int i) {
        this.iPrinter = dateTimePrinter;
        this.iParser = dateTimeParser;
        this.iLocale = locale;
        this.iOffsetParsed = z;
        this.iChrono = chronology;
        this.iZone = dateTimeZone;
        this.iPivotYear = num;
        this.iDefaultYear = i;
    }

    public void printTo(StringBuffer stringBuffer, ReadableInstant readableInstant) {
        printTo(stringBuffer, DateTimeUtils.getInstantMillis(readableInstant), DateTimeUtils.getInstantChronology(readableInstant));
    }
}
