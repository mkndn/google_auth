package androidx.core.os;

import android.os.Build;
import android.os.LocaleList;
import androidx.core.text.ICUCompat;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LocaleListCompat {
    private static final LocaleListCompat sEmptyLocaleList = create(new Locale[0]);
    private final LocaleListInterface mImpl;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class Api21Impl {
        private static final Locale[] PSEUDO_LOCALE = {new Locale("en", "XA"), new Locale("ar", "XB")};

        static Locale forLanguageTag(String str) {
            return Locale.forLanguageTag(str);
        }

        private static boolean isPseudoLocale(Locale locale) {
            for (Locale locale2 : PSEUDO_LOCALE) {
                if (locale2.equals(locale)) {
                    return true;
                }
            }
            return false;
        }

        static boolean matchesLanguageAndScript(Locale locale, Locale locale2) {
            if (locale.equals(locale2)) {
                return true;
            }
            if (!locale.getLanguage().equals(locale2.getLanguage()) || isPseudoLocale(locale) || isPseudoLocale(locale2)) {
                return false;
            }
            String maximizeAndGetScript = ICUCompat.maximizeAndGetScript(locale);
            if (maximizeAndGetScript.isEmpty()) {
                String country = locale.getCountry();
                return country.isEmpty() || country.equals(locale2.getCountry());
            }
            return maximizeAndGetScript.equals(ICUCompat.maximizeAndGetScript(locale2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Api24Impl {
        static LocaleList createLocaleList(Locale... localeArr) {
            return new LocaleList(localeArr);
        }

        static LocaleList getAdjustedDefault() {
            return LocaleList.getAdjustedDefault();
        }

        static LocaleList getDefault() {
            return LocaleList.getDefault();
        }
    }

    private LocaleListCompat(LocaleListInterface localeListInterface) {
        this.mImpl = localeListInterface;
    }

    public static LocaleListCompat create(Locale... localeArr) {
        if (Build.VERSION.SDK_INT >= 24) {
            return wrap(Api24Impl.createLocaleList(localeArr));
        }
        return new LocaleListCompat(new LocaleListCompatWrapper(localeArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Locale forLanguageTagCompat(String str) {
        if (str.contains("-")) {
            String[] split = str.split("-", -1);
            if (split.length > 2) {
                return new Locale(split[0], split[1], split[2]);
            }
            if (split.length > 1) {
                return new Locale(split[0], split[1]);
            }
            if (split.length == 1) {
                return new Locale(split[0]);
            }
        } else if (str.contains("_")) {
            String[] split2 = str.split("_", -1);
            if (split2.length > 2) {
                return new Locale(split2[0], split2[1], split2[2]);
            }
            if (split2.length > 1) {
                return new Locale(split2[0], split2[1]);
            }
            if (split2.length == 1) {
                return new Locale(split2[0]);
            }
        } else {
            return new Locale(str);
        }
        throw new IllegalArgumentException("Can not parse language tag: [" + str + "]");
    }

    public static LocaleListCompat forLanguageTags(String str) {
        Locale forLanguageTagCompat;
        if (str == null || str.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] split = str.split(",", -1);
        int length = split.length;
        Locale[] localeArr = new Locale[length];
        for (int i = 0; i < length; i++) {
            if (Build.VERSION.SDK_INT >= 21) {
                forLanguageTagCompat = Api21Impl.forLanguageTag(split[i]);
            } else {
                forLanguageTagCompat = forLanguageTagCompat(split[i]);
            }
            localeArr[i] = forLanguageTagCompat;
        }
        return create(localeArr);
    }

    public static LocaleListCompat getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    public static LocaleListCompat wrap(LocaleList localeList) {
        return new LocaleListCompat(new LocaleListPlatformWrapper(localeList));
    }

    public boolean equals(Object obj) {
        return (obj instanceof LocaleListCompat) && this.mImpl.equals(((LocaleListCompat) obj).mImpl);
    }

    public Locale get(int i) {
        return this.mImpl.get(i);
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public boolean isEmpty() {
        return this.mImpl.isEmpty();
    }

    public int size() {
        return this.mImpl.size();
    }

    public String toLanguageTags() {
        return this.mImpl.toLanguageTags();
    }

    public String toString() {
        return this.mImpl.toString();
    }
}
