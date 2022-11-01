package com.google.android.libraries.social.licenses;

import android.content.Context;
import android.content.res.Resources;
import com.google.common.base.Preconditions;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Licenses {
    public static ArrayList getLicenseListFromMetadata(String str, String str2) {
        String[] split = str.split("\n");
        ArrayList arrayList = new ArrayList(split.length);
        for (String str3 : split) {
            int indexOf = str3.indexOf(32);
            String[] split2 = str3.substring(0, indexOf).split(":");
            Preconditions.checkState(split2.length == 2 && indexOf > 0, "Invalid license meta-data line:\n" + str3);
            arrayList.add(License.create(str3.substring(indexOf + 1), Long.parseLong(split2[0]), Integer.parseInt(split2[1]), str2));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static String getLicenseText(Context context, License license) {
        long licenseOffset = license.getLicenseOffset();
        int licenseLength = license.getLicenseLength();
        String path = license.getPath();
        if (path.isEmpty()) {
            return getTextFromResource(context, "third_party_licenses", licenseOffset, licenseLength);
        }
        try {
            String textFromInputStream = getTextFromInputStream(new BufferedInputStream(new FileInputStream(path)), licenseOffset, licenseLength);
            if (textFromInputStream != null) {
                if (!textFromInputStream.isEmpty()) {
                    return textFromInputStream;
                }
            }
        } catch (FileNotFoundException e) {
        }
        throw new RuntimeException(path + " does not contain res/raw/third_party_licenses");
    }

    private static String getTextFromInputStream(InputStream inputStream, long j, int i) {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStream.skip(j);
            if (i <= 0) {
                i = Integer.MAX_VALUE;
            }
            while (i > 0) {
                int read = inputStream.read(bArr, 0, Math.min(i, 1024));
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
                i -= read;
            }
            inputStream.close();
            try {
                return byteArrayOutputStream.toString("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unsupported encoding UTF8. This should always be supported.", e);
            }
        } catch (IOException e2) {
            throw new RuntimeException("Failed to read license or metadata text.", e2);
        }
    }

    private static String getTextFromResource(Context context, String str, long j, int i) {
        Resources resources = context.getApplicationContext().getResources();
        return getTextFromInputStream(resources.openRawResource(resources.getIdentifier(str, "raw", resources.getResourcePackageName(R$id.dummy_placeholder))), j, i);
    }

    public static ArrayList getLicenses(Context context) {
        return getLicenseListFromMetadata(getTextFromResource(context.getApplicationContext(), "third_party_license_metadata", 0L, -1), "");
    }
}
