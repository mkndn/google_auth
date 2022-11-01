package com.google.android.gms.feedback;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.apps.authenticator.testability.android.os.PowerManager;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FileTeleporter extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new FileTeleporterCreator();
    byte[] fileData;
    ParcelFileDescriptor fileDescriptor;
    final String fileName;
    final String mimeType;
    private File tempDir;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileTeleporter(ParcelFileDescriptor parcelFileDescriptor, String str, String str2) {
        this.fileDescriptor = parcelFileDescriptor;
        this.mimeType = str;
        this.fileName = str2;
    }

    DataOutputStream createDataOutputStream(FileOutputStream fileOutputStream) {
        return new DataOutputStream(fileOutputStream);
    }

    FileOutputStream createFileOutputStream(File file) {
        return new FileOutputStream(file);
    }

    File createTempFile(String str, String str2, File file) {
        return File.createTempFile(str, str2, file);
    }

    void fileTeleporterCreatorWriteToParcel(Parcel parcel, int i) {
        FileTeleporterCreator.writeToParcel(this, parcel, i);
    }

    FileOutputStream getUnlinkedFileOutputStream() {
        File file = this.tempDir;
        if (file == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel.");
        }
        try {
            File createTempFile = createTempFile("teleporter", ".tmp", file);
            try {
                FileOutputStream createFileOutputStream = createFileOutputStream(createTempFile);
                this.fileDescriptor = openParcelFileDescriptor(createTempFile);
                createTempFile.delete();
                return createFileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted.");
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Could not create temporary file:", e2);
        }
    }

    ParcelFileDescriptor openParcelFileDescriptor(File file) {
        return ParcelFileDescriptor.open(file, PowerManager.ACQUIRE_CAUSES_WAKEUP);
    }

    void safeClose(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("FileTeleporter", "Could not close stream", e);
        }
    }

    public void setTempDir(File file) {
        if (file == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.tempDir = file;
    }

    void write(DataOutputStream dataOutputStream, byte[] bArr) {
        dataOutputStream.write(bArr);
    }

    void writeInt(DataOutputStream dataOutputStream, int i) {
        dataOutputStream.writeInt(i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.fileDescriptor == null) {
            DataOutputStream createDataOutputStream = createDataOutputStream(getUnlinkedFileOutputStream());
            try {
                try {
                    writeInt(createDataOutputStream, this.fileData.length);
                    writeUTF(createDataOutputStream, this.mimeType);
                    writeUTF(createDataOutputStream, this.fileName);
                    write(createDataOutputStream, this.fileData);
                } catch (IOException e) {
                    throw new IllegalStateException("Could not write into unlinked file", e);
                }
            } finally {
                safeClose(createDataOutputStream);
            }
        }
        fileTeleporterCreatorWriteToParcel(parcel, i);
    }

    void writeUTF(DataOutputStream dataOutputStream, String str) {
        dataOutputStream.writeUTF(str);
    }
}
