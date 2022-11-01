package com.google.android.apps.authenticator.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.StyleSpan;
import androidx.core.view.ViewCompat;
import com.google.common.base.Strings;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.xml.sax.XMLReader;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Utilities {
    private static final Uri GSERVICES_CONTENT_PROVIDER_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final String GSERVICES_KEY_ANDROID_ID = "android_id";
    public static final long MINUTE_IN_MILLIS = 60000;
    public static final long SECOND_IN_MILLIS = 1000;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class StyledTextTagHandler implements Html.TagHandler {
        private StyledTextTagHandler() {
        }

        private static void appendNewlineIfNoTrailingNewline(Editable editable) {
            int length = editable.length();
            if (length != 0) {
                if (editable.charAt(length - 1) != '\n') {
                    editable.append('\n');
                    return;
                }
                return;
            }
            editable.append('\n');
        }

        private static void ensureAtLeastTwoTrailingNewlines(Editable editable) {
            appendNewlineIfNoTrailingNewline(editable);
            int length = editable.length();
            if (length != 1) {
                if (editable.charAt(length - 2) != '\n') {
                    editable.append('\n');
                    return;
                }
                return;
            }
            editable.append('\n');
        }

        @Override // android.text.Html.TagHandler
        public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
            if ("ul".equalsIgnoreCase(str)) {
                ensureAtLeastTwoTrailingNewlines(editable);
            } else if ("li".equalsIgnoreCase(str)) {
                appendNewlineIfNoTrailingNewline(editable);
                int length = editable.length();
                if (z) {
                    editable.setSpan(new BulletSpan(), length, length, 17);
                    return;
                }
                BulletSpan[] bulletSpanArr = (BulletSpan[]) editable.getSpans(0, length, BulletSpan.class);
                if (bulletSpanArr.length > 0) {
                    BulletSpan bulletSpan = bulletSpanArr[bulletSpanArr.length - 1];
                    int spanStart = editable.getSpanStart(bulletSpan);
                    editable.removeSpan(bulletSpan);
                    if (spanStart != length) {
                        editable.setSpan(new LeadingMarginSpan.Standard(10), spanStart, length, 33);
                        editable.setSpan(new BulletSpan(10), spanStart, length, 33);
                    }
                }
            }
        }
    }

    private Utilities() {
    }

    public static Spanned addColorToStyleSpans(Spanned spanned, int i) {
        StyleSpan[] styleSpanArr = (StyleSpan[]) spanned.getSpans(0, spanned.length(), StyleSpan.class);
        if (styleSpanArr != null && styleSpanArr.length != 0) {
            if (!(spanned instanceof Spannable)) {
                spanned = new SpannableString(spanned);
            }
            Spannable spannable = (Spannable) spanned;
            for (StyleSpan styleSpan : styleSpanArr) {
                spannable.setSpan(new ForegroundColorSpan(i), spanned.getSpanStart(styleSpan), spanned.getSpanEnd(styleSpan), spanned.getSpanFlags(styleSpan));
            }
            return spanned;
        }
        return spanned;
    }

    public static byte[] getAsciiBytes(String str) {
        try {
            return str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("US-ASCII character encoding not supported", e);
        }
    }

    public static String getCombinedTextForIssuerAndAccountName(String str, String str2) {
        if (!Strings.isNullOrEmpty(str)) {
            return str + " (" + str2 + ")";
        }
        return str2;
    }

    public static long getGlsAndroidId(Context context) {
        String gservicesKeyValue = getGservicesKeyValue(context, GSERVICES_KEY_ANDROID_ID);
        if (gservicesKeyValue == null) {
            return 0L;
        }
        try {
            return Long.parseLong(gservicesKeyValue);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    private static String getGservicesKeyValue(Context context, String str) {
        Cursor query = context.getContentResolver().query(GSERVICES_CONTENT_PROVIDER_URI, null, null, new String[]{str}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(1);
                    if (query != null) {
                        query.close();
                    }
                    return string;
                }
            } catch (Throwable th) {
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
        if (query != null) {
            query.close();
            return null;
        }
        return null;
    }

    public static String getStyledPincode(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() != 6) {
            return str;
        }
        for (int i = 0; i < 6; i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return str;
            }
        }
        String substring = str.substring(0, 3);
        return substring + " " + str.substring(3, 6);
    }

    public static Spanned getStyledTextFromHtml(String str) {
        return removeTrailingNewlines(Html.fromHtml(str, null, new StyledTextTagHandler()));
    }

    public static final long millisToSeconds(long j) {
        return j / 1000;
    }

    public static final long secondsToMillis(long j) {
        return j * 1000;
    }

    static String getCssColor(int i) {
        return String.format(Locale.US, "#%06x", Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK));
    }

    private static Spanned removeTrailingNewlines(Spanned spanned) {
        int i = 0;
        for (int length = spanned.length() - 1; length >= 0; length--) {
            char charAt = spanned.charAt(length);
            if (charAt != '\n' && charAt != '\r') {
                break;
            }
            i++;
        }
        if (i == 0) {
            return spanned;
        }
        return new SpannedString(spanned.subSequence(0, spanned.length() - i));
    }
}
