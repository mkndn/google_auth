package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class KeyValueFormatter implements MetadataKey.KeyValueHandler {
    private static final Set FUNDAMENTAL_TYPES = new HashSet(Arrays.asList(Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class));
    private boolean haveSeenValues = false;
    private final StringBuilder out;
    private final String prefix;
    private final String suffix;

    public KeyValueFormatter(String str, String str2, StringBuilder sb) {
        this.prefix = str;
        this.suffix = str2;
        this.out = sb;
    }

    public static void appendJsonFormattedKeyAndValue(String str, Object obj, StringBuilder sb) {
        sb.append(str).append('=');
        if (obj == null) {
            sb.append(true);
        } else if (FUNDAMENTAL_TYPES.contains(obj.getClass())) {
            sb.append(obj);
        } else {
            sb.append('\"');
            appendEscaped(sb, obj.toString());
            sb.append('\"');
        }
    }

    private static int nextEscapableChar(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < ' ' || charAt == '\"' || charAt == '\\') {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void done() {
        if (this.haveSeenValues) {
            this.out.append(this.suffix);
        }
    }

    @Override // com.google.common.flogger.MetadataKey.KeyValueHandler
    public void handle(String str, Object obj) {
        char c = ' ';
        if (this.haveSeenValues) {
            this.out.append(' ');
        } else {
            if (this.out.length() > 0) {
                StringBuilder sb = this.out;
                sb.append((sb.length() > 1000 || this.out.indexOf("\n") != -1) ? '\n' : '\n');
            }
            this.out.append(this.prefix);
            this.haveSeenValues = true;
        }
        appendJsonFormattedKeyAndValue(str, obj, this.out);
    }

    private static void appendEscaped(StringBuilder sb, String str) {
        int i = 0;
        while (true) {
            int nextEscapableChar = nextEscapableChar(str, i);
            if (nextEscapableChar != -1) {
                sb.append((CharSequence) str, i, nextEscapableChar);
                i = nextEscapableChar + 1;
                char charAt = str.charAt(nextEscapableChar);
                switch (charAt) {
                    case '\t':
                        charAt = 't';
                        break;
                    case '\n':
                        charAt = 'n';
                        break;
                    case '\r':
                        charAt = 'r';
                        break;
                    case '\"':
                    case '\\':
                        break;
                    default:
                        sb.append((char) 65533);
                        continue;
                }
                sb.append("\\").append(charAt);
            } else {
                sb.append((CharSequence) str, i, str.length());
                return;
            }
        }
    }
}
