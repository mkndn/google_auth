package org.joda.time;

import org.joda.time.format.DateTimeFormat;

/* compiled from: PG */
/* loaded from: classes.dex */
public class IllegalInstantException extends IllegalArgumentException {
    private static final long serialVersionUID = 2858712538216L;

    public IllegalInstantException(long j, String str) {
        super(createMessage(j, str));
    }

    private static String createMessage(long j, String str) {
        String print = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(j));
        String sb = str != null ? new StringBuilder(String.valueOf(str).length() + 3).append(" (").append(str).append(")").toString() : "";
        return new StringBuilder(String.valueOf(print).length() + 82 + String.valueOf(sb).length()).append("Illegal instant due to time zone offset transition (daylight savings time 'gap'): ").append(print).append(sb).toString();
    }

    public IllegalInstantException(String str) {
        super(str);
    }
}
