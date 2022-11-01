package com.google.android.libraries.storage.file;

import android.net.Uri;
import com.google.android.libraries.storage.file.spi.Backend;
import com.google.android.libraries.storage.file.spi.Transform;
import com.google.common.collect.Iterables;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OpenContext {
    private final Backend backend;
    private final Uri encodedUri;
    private final List monitors;
    private final Uri originalUri;
    private final SynchronousFileStorage storage;
    private final List transforms;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private Backend backend;
        private Uri encodedUri;
        private List monitors;
        private Uri originalUri;
        private SynchronousFileStorage storage;
        private List transforms;

        private Builder() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public OpenContext build() {
            return new OpenContext(this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setBackend(Backend backend) {
            this.backend = backend;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setEncodedUri(Uri uri) {
            this.encodedUri = uri;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setMonitors(List list) {
            this.monitors = list;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setOriginalUri(Uri uri) {
            this.originalUri = uri;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setStorage(SynchronousFileStorage synchronousFileStorage) {
            this.storage = synchronousFileStorage;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setTransforms(List list) {
            this.transforms = list;
            return this;
        }
    }

    OpenContext(Builder builder) {
        this.storage = builder.storage;
        this.backend = builder.backend;
        this.transforms = builder.transforms;
        this.monitors = builder.monitors;
        this.originalUri = builder.originalUri;
        this.encodedUri = builder.encodedUri;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Backend backend() {
        return this.backend;
    }

    public List chainTransformsForRead(InputStream inputStream) {
        MonitorInputStream newInstance;
        ArrayList arrayList = new ArrayList();
        arrayList.add(inputStream);
        if (!this.monitors.isEmpty() && (newInstance = MonitorInputStream.newInstance(this.monitors, this.originalUri, inputStream)) != null) {
            arrayList.add(newInstance);
        }
        for (Transform transform : this.transforms) {
            arrayList.add(transform.wrapForRead(this.originalUri, (InputStream) Iterables.getLast(arrayList)));
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public List chainTransformsForWrite(OutputStream outputStream) {
        MonitorOutputStream newInstanceForWrite;
        ArrayList arrayList = new ArrayList();
        arrayList.add(outputStream);
        if (!this.monitors.isEmpty() && (newInstanceForWrite = MonitorOutputStream.newInstanceForWrite(this.monitors, this.originalUri, outputStream)) != null) {
            arrayList.add(newInstanceForWrite);
        }
        for (Transform transform : this.transforms) {
            arrayList.add(transform.wrapForWrite(this.originalUri, (OutputStream) Iterables.getLast(arrayList)));
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public Uri encodedUri() {
        return this.encodedUri;
    }

    public boolean hasTransforms() {
        return !this.transforms.isEmpty();
    }
}
