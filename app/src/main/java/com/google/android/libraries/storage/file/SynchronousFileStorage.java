package com.google.android.libraries.storage.file;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.libraries.storage.file.common.UnsupportedFileStorageOperation;
import com.google.android.libraries.storage.file.common.internal.LiteTransformFragments;
import com.google.android.libraries.storage.file.spi.Backend;
import com.google.android.libraries.storage.file.spi.Transform;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SynchronousFileStorage {
    private final Map backends;
    private final List monitors;
    private final Map transforms;

    public SynchronousFileStorage(List list) {
        this(list, Collections.emptyList(), Collections.emptyList());
    }

    private static final Uri encodeFilename(List list, Uri uri) {
        if (list.isEmpty()) {
            return uri;
        }
        ArrayList arrayList = new ArrayList(uri.getPathSegments());
        if (!arrayList.isEmpty() && !uri.getPath().endsWith("/")) {
            String str = (String) arrayList.get(arrayList.size() - 1);
            ListIterator listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                str = ((Transform) listIterator.previous()).encode(uri, str);
            }
            arrayList.set(arrayList.size() - 1, str);
            return uri.buildUpon().path(TextUtils.join("/", arrayList)).encodedFragment(null).build();
        }
        return uri;
    }

    private Backend getBackend(String str) {
        Backend backend = (Backend) this.backends.get(str);
        if (backend == null) {
            throw new UnsupportedFileStorageOperation(String.format("Cannot open, unregistered backend: %s", str));
        }
        return backend;
    }

    private OpenContext getContext(Uri uri) {
        ImmutableList enabledTransforms = getEnabledTransforms(uri);
        return OpenContext.builder().setStorage(this).setBackend(getBackend(uri.getScheme())).setMonitors(this.monitors).setTransforms(enabledTransforms).setOriginalUri(uri).setEncodedUri(encodeFilename(enabledTransforms, uri)).build();
    }

    private ImmutableList getEnabledTransforms(Uri uri) {
        ImmutableList.Builder builder = ImmutableList.builder();
        UnmodifiableIterator it = LiteTransformFragments.parseTransformNames(uri).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Transform transform = (Transform) this.transforms.get(str);
            if (transform != null) {
                builder.add((Object) transform);
            } else {
                throw new UnsupportedFileStorageOperation("No such transform: " + str + ": " + String.valueOf(uri));
            }
        }
        return builder.build().reverse();
    }

    private void registerPlugins(List list, List list2, List list3) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Backend backend = (Backend) it.next();
            if (TextUtils.isEmpty(backend.name())) {
                Log.w("MobStore.FileStorage", "Cannot register backend, name empty");
            } else {
                Backend backend2 = (Backend) this.backends.put(backend.name(), backend);
                if (backend2 != null) {
                    String canonicalName = backend2.getClass().getCanonicalName();
                    throw new IllegalArgumentException("Cannot override Backend " + canonicalName + " with " + backend.getClass().getCanonicalName());
                }
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            Transform transform = (Transform) it2.next();
            if (TextUtils.isEmpty(transform.name())) {
                Log.w("MobStore.FileStorage", "Cannot register transform, name empty");
            } else {
                Transform transform2 = (Transform) this.transforms.put(transform.name(), transform);
                if (transform2 != null) {
                    String canonicalName2 = transform2.getClass().getCanonicalName();
                    throw new IllegalArgumentException("Cannot to override Transform " + canonicalName2 + " with " + transform.getClass().getCanonicalName());
                }
            }
        }
        this.monitors.addAll(list3);
    }

    public void deleteFile(Uri uri) {
        OpenContext context = getContext(uri);
        context.backend().deleteFile(context.encodedUri());
    }

    public boolean exists(Uri uri) {
        OpenContext context = getContext(uri);
        return context.backend().exists(context.encodedUri());
    }

    public Object open(Uri uri, Opener opener) {
        return opener.open(getContext(uri));
    }

    public void rename(Uri uri, Uri uri2) {
        OpenContext context = getContext(uri);
        OpenContext context2 = getContext(uri2);
        if (context.backend() != context2.backend()) {
            throw new UnsupportedFileStorageOperation("Cannot rename file across backends");
        }
        context.backend().rename(context.encodedUri(), context2.encodedUri());
    }

    public SynchronousFileStorage(List list, List list2, List list3) {
        this.backends = new HashMap();
        this.transforms = new HashMap();
        this.monitors = new ArrayList();
        registerPlugins(list, list2, list3);
    }
}
