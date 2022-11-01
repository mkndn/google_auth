package com.google.android.libraries.storage.file.backends;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.libraries.storage.file.common.MalformedUriException;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidUriAdapter {
    private final AccountManager accountManager;
    private final Context context;

    private AndroidUriAdapter(Context context, AccountManager accountManager) {
        this.context = context;
        this.accountManager = accountManager;
    }

    public static AndroidUriAdapter forContext(Context context, AccountManager accountManager) {
        return new AndroidUriAdapter(context, accountManager);
    }

    public static void validate(Uri uri) {
        if (!uri.getScheme().equals("android")) {
            throw new MalformedUriException("Scheme must be 'android'");
        }
        if (uri.getPathSegments().isEmpty()) {
            throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
        }
        if (!TextUtils.isEmpty(uri.getQuery())) {
            throw new MalformedUriException("Did not expect uri to have query");
        }
    }

    public File toFile(Uri uri) {
        File externalFilesDir;
        validate(uri);
        ArrayList arrayList = new ArrayList(uri.getPathSegments());
        String str = (String) arrayList.get(0);
        switch (str.hashCode()) {
            case -1820761141:
                if (str.equals("external")) {
                    externalFilesDir = this.context.getExternalFilesDir(null);
                    return new File(externalFilesDir, TextUtils.join(File.separator, arrayList.subList(1, arrayList.size())));
                }
                throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
            case 94416770:
                if (str.equals("cache")) {
                    externalFilesDir = this.context.getCacheDir();
                    return new File(externalFilesDir, TextUtils.join(File.separator, arrayList.subList(1, arrayList.size())));
                }
                throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
            case 97434231:
                if (str.equals("files")) {
                    externalFilesDir = AndroidFileEnvironment.getFilesDirWithPreNWorkaround(this.context);
                    return new File(externalFilesDir, TextUtils.join(File.separator, arrayList.subList(1, arrayList.size())));
                }
                throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
            case 835260319:
                if (str.equals("managed")) {
                    File file = new File(AndroidFileEnvironment.getFilesDirWithPreNWorkaround(this.context), "managed");
                    if (arrayList.size() >= 3) {
                        try {
                            Account deserialize = AccountSerialization.deserialize((String) arrayList.get(2));
                            if (!AccountSerialization.isSharedAccount(deserialize)) {
                                AccountManager accountManager = this.accountManager;
                                if (accountManager == null) {
                                    throw new MalformedUriException("AccountManager cannot be null");
                                }
                                try {
                                    arrayList.set(2, Integer.toString(((Integer) accountManager.getAccountId(deserialize).get()).intValue()));
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    throw new MalformedUriException(e);
                                } catch (ExecutionException e2) {
                                    throw new MalformedUriException(e2.getCause());
                                }
                            }
                        } catch (IllegalArgumentException e3) {
                            throw new MalformedUriException(e3);
                        }
                    }
                    externalFilesDir = file;
                    return new File(externalFilesDir, TextUtils.join(File.separator, arrayList.subList(1, arrayList.size())));
                }
                throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
            case 988548496:
                if (str.equals("directboot-cache")) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        externalFilesDir = this.context.createDeviceProtectedStorageContext().getCacheDir();
                        return new File(externalFilesDir, TextUtils.join(File.separator, arrayList.subList(1, arrayList.size())));
                    }
                    throw new MalformedUriException(String.format("Direct boot only exists on N or greater: current SDK %s", Integer.valueOf(Build.VERSION.SDK_INT)));
                }
                throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
            case 991565957:
                if (str.equals("directboot-files")) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        externalFilesDir = this.context.createDeviceProtectedStorageContext().getFilesDir();
                        return new File(externalFilesDir, TextUtils.join(File.separator, arrayList.subList(1, arrayList.size())));
                    }
                    throw new MalformedUriException(String.format("Direct boot only exists on N or greater: current SDK %s", Integer.valueOf(Build.VERSION.SDK_INT)));
                }
                throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
            default:
                throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
        }
    }
}
