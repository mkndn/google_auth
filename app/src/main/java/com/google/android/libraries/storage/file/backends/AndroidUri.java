package com.google.android.libraries.storage.file.backends;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import com.google.android.libraries.storage.file.common.internal.LiteTransformFragments;
import com.google.android.libraries.storage.file.common.internal.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidUri {
    private static final Pattern MODULE_PATTERN = Pattern.compile("[a-z]+(_[a-z]+)*");
    static final Account SHARED_ACCOUNT = AccountSerialization.SHARED_ACCOUNT;
    private static final Set RESERVED_MODULES = Collections.unmodifiableSet(new HashSet(Arrays.asList("default", "unused", "special", "reserved", "shared", "virtual", "managed")));
    private static final Set VALID_LOCATIONS = Collections.unmodifiableSet(new HashSet(Arrays.asList("files", "cache", "managed", "directboot-files", "directboot-cache", "external")));

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private Account account;
        private final Context context;
        private final ImmutableList.Builder encodedSpecs;
        private String location;
        private String module;
        private String packageName;
        private String relativePath;

        private Builder(Context context) {
            this.location = "files";
            this.module = "common";
            this.account = AndroidUri.SHARED_ACCOUNT;
            this.relativePath = "";
            this.encodedSpecs = ImmutableList.builder();
            Preconditions.checkArgument(context != null, "Context cannot be null", new Object[0]);
            this.context = context;
            this.packageName = context.getPackageName();
        }

        private Builder setLocation(String str) {
            AndroidUri.validateLocation(str);
            this.location = str;
            return this;
        }

        public Uri build() {
            return new Uri.Builder().scheme("android").authority(this.packageName).path("/" + this.location + "/" + this.module + "/" + AccountSerialization.serialize(this.account) + "/" + this.relativePath).encodedFragment(LiteTransformFragments.joinTransformSpecs(this.encodedSpecs.build())).build();
        }

        public Builder setDirectBootFilesLocation() {
            return setLocation("directboot-files");
        }

        public Builder setModule(String str) {
            AndroidUri.validateModule(str);
            this.module = str;
            return this;
        }

        public Builder setRelativePath(String str) {
            if (str.startsWith("/")) {
                str = str.substring(1);
            }
            AndroidUri.validateRelativePath(str);
            this.relativePath = str;
            return this;
        }
    }

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    static void validateLocation(String str) {
        Set set = VALID_LOCATIONS;
        Preconditions.checkArgument(set.contains(str), "The only supported locations are %s: %s", set, str);
    }

    static void validateModule(String str) {
        Preconditions.checkArgument(MODULE_PATTERN.matcher(str).matches(), "Module must match [a-z]+(_[a-z]+)*: %s", str);
        Preconditions.checkArgument(!RESERVED_MODULES.contains(str), "Module name is reserved and cannot be used: %s", str);
    }

    static void validateRelativePath(String str) {
    }
}
