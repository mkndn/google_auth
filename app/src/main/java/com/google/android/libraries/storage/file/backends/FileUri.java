package com.google.android.libraries.storage.file.backends;

import android.net.Uri;
import com.google.android.libraries.storage.file.common.internal.LiteTransformFragments;
import com.google.common.collect.ImmutableList;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FileUri {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private final ImmutableList.Builder encodedSpecs;
        private Uri.Builder uri;

        private Builder() {
            this.uri = new Uri.Builder().scheme("file").authority("").path("/");
            this.encodedSpecs = ImmutableList.builder();
        }

        public Uri build() {
            return this.uri.encodedFragment(LiteTransformFragments.joinTransformSpecs(this.encodedSpecs.build())).build();
        }

        public Builder fromFile(File file) {
            this.uri.path(file.getAbsolutePath());
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
