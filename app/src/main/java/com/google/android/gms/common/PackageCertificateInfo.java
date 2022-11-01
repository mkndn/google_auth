package com.google.android.gms.common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class PackageCertificateInfo {
    private final long minimumStampedVersionNumber;
    private final ImmutableList orderedProdCerts;
    private final ImmutableList orderedTestCerts;
    private final String packageName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class Builder {
        private String packageName = null;
        private long minimumStampedVersionNumber = -1;
        private ImmutableList orderedTestCerts = ImmutableList.of();
        private ImmutableList orderedProdCerts = ImmutableList.of();

        Builder() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PackageCertificateInfo build() {
            if (this.packageName == null) {
                throw new IllegalStateException("packageName must be defined");
            }
            if (this.minimumStampedVersionNumber < 0) {
                throw new IllegalStateException("minimumStampedVersionNumber must be greater than or equal to 0");
            }
            if (this.orderedTestCerts.isEmpty() && this.orderedProdCerts.isEmpty()) {
                throw new IllegalStateException("Either orderedTestCerts or orderedProdCerts must have at least one cert");
            }
            return new PackageCertificateInfo(this.packageName, this.minimumStampedVersionNumber, this.orderedTestCerts, this.orderedProdCerts);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setMinimumStampedVersionNumber(long j) {
            this.minimumStampedVersionNumber = j;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setOrderedProdCerts(List list) {
            Preconditions.checkNotNull(list);
            this.orderedProdCerts = ImmutableList.copyOf((Collection) list);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setOrderedTestCerts(List list) {
            Preconditions.checkNotNull(list);
            this.orderedTestCerts = ImmutableList.copyOf((Collection) list);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setPackageName(String str) {
            this.packageName = str;
            return this;
        }
    }

    private PackageCertificateInfo(String str, long j, ImmutableList immutableList, ImmutableList immutableList2) {
        this.packageName = str;
        this.minimumStampedVersionNumber = j;
        this.orderedTestCerts = immutableList;
        this.orderedProdCerts = immutableList2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getMinimumStampedVersionNumber() {
        return this.minimumStampedVersionNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableList getOrderedProdCerts() {
        return this.orderedProdCerts;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableList getOrderedTestCerts() {
        return this.orderedTestCerts;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPackageName() {
        return this.packageName;
    }
}
