package com.google.android.gms.people;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.util.IOUtils;
import java.io.FileInputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PeopleClientUtil {
    public static Bitmap decodeFileDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor == null) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
        try {
            return BitmapFactory.decodeStream(fileInputStream);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
    }
}
