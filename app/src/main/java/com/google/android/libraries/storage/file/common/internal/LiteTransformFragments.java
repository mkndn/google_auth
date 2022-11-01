package com.google.android.libraries.storage.file.common.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LiteTransformFragments {
    private static final Pattern XFORM_NAME_PATTERN = Pattern.compile("(\\w+).*");

    public static String joinTransformSpecs(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return "transform=" + Joiner.on("+").join(list);
    }

    public static String parseSpecName(String str) {
        Matcher matcher = XFORM_NAME_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid fragment spec: " + str);
        }
        return matcher.group(1);
    }

    public static ImmutableList parseTransformNames(Uri uri) {
        ImmutableList.Builder builder = ImmutableList.builder();
        UnmodifiableIterator it = parseTransformSpecs(uri).iterator();
        while (it.hasNext()) {
            builder.add((Object) parseSpecName((String) it.next()));
        }
        return builder.build();
    }

    public static ImmutableList parseTransformSpecs(Uri uri) {
        String encodedFragment = uri.getEncodedFragment();
        if (!TextUtils.isEmpty(encodedFragment) && encodedFragment.startsWith("transform=")) {
            return ImmutableList.copyOf(Splitter.on("+").omitEmptyStrings().split(encodedFragment.substring("transform=".length())));
        }
        return ImmutableList.of();
    }
}
