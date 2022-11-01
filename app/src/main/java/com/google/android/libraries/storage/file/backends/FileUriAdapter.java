package com.google.android.libraries.storage.file.backends;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.libraries.storage.file.common.MalformedUriException;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FileUriAdapter {
    private static final FileUriAdapter INSTANCE = new FileUriAdapter();

    private FileUriAdapter() {
    }

    public static FileUriAdapter instance() {
        return INSTANCE;
    }

    public File toFile(Uri uri) {
        if (!uri.getScheme().equals("file")) {
            throw new MalformedUriException("Scheme must be 'file'");
        }
        if (!TextUtils.isEmpty(uri.getQuery())) {
            throw new MalformedUriException("Did not expect uri to have query");
        }
        if (!TextUtils.isEmpty(uri.getAuthority())) {
            throw new MalformedUriException("Did not expect uri to have authority");
        }
        return new File(uri.getPath());
    }
}
