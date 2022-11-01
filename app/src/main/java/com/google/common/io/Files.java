package com.google.common.io;

import com.google.common.base.Preconditions;
import com.google.common.graph.SuccessorsFunction;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Files {
    private static final SuccessorsFunction FILE_TREE = new SuccessorsFunction() { // from class: com.google.common.io.Files.2
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.common.io.Files$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 {
    }

    public static ByteSource asByteSource(File file) {
        return new FileByteSource(file, null);
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return asByteSource(file).asCharSource(charset);
    }

    public static void createParentDirs(File file) {
        Preconditions.checkNotNull(file);
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile == null) {
            return;
        }
        parentFile.mkdirs();
        if (!parentFile.isDirectory()) {
            throw new IOException("Unable to create parent directories of " + file);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FileByteSource extends ByteSource {
        private final File file;

        private FileByteSource(File file) {
            this.file = (File) Preconditions.checkNotNull(file);
        }

        public FileInputStream openStream() {
            return new FileInputStream(this.file);
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() {
            try {
                FileInputStream fileInputStream = (FileInputStream) Closer.create().register(openStream());
                return ByteStreams.toByteArray(fileInputStream, fileInputStream.getChannel().size());
            } finally {
            }
        }

        public String toString() {
            return "Files.asByteSource(" + this.file + ")";
        }

        /* synthetic */ FileByteSource(File file, AnonymousClass1 anonymousClass1) {
            this(file);
        }
    }
}
