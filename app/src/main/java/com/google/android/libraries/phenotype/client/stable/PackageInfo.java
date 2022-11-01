package com.google.android.libraries.phenotype.client.stable;

import android.content.Context;
import android.util.Log;
import com.google.android.libraries.phenotype.client.PhenotypeConstants;
import com.google.common.collect.ImmutableMap;
import com.google.experiments.mobile.base.AndroidBacking;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PackageInfo {
    private static final Object PACKAGES_LOCK = new Object();
    private static volatile Map packagesFromAssets = null;
    private final boolean accountScoped;
    private final AndroidBacking backing;
    private final String configPackage;
    private final boolean directBootAware;
    private final boolean stickyAccountSupport;

    PackageInfo(Context context, PackageMetadataProto$PackageMetadata packageMetadataProto$PackageMetadata) {
        String staticConfigPackage;
        if (packageMetadataProto$PackageMetadata.getAutoSubpackage()) {
            staticConfigPackage = PhenotypeConstants.getSubpackagedName(context, packageMetadataProto$PackageMetadata.getStaticConfigPackage());
        } else {
            staticConfigPackage = packageMetadataProto$PackageMetadata.getStaticConfigPackage();
        }
        this.configPackage = staticConfigPackage;
        this.backing = packageMetadataProto$PackageMetadata.getBacking();
        this.directBootAware = packageMetadataProto$PackageMetadata.getDirectBootAware();
        this.stickyAccountSupport = packageMetadataProto$PackageMetadata.getStickyAccountSupport();
        this.accountScoped = packageMetadataProto$PackageMetadata.getAccountScoped();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map getRegisteredPackages(Context context) {
        String[] list;
        InputStream open;
        Map map = packagesFromAssets;
        if (map == null) {
            synchronized (PACKAGES_LOCK) {
                map = packagesFromAssets;
                if (map == null) {
                    ImmutableMap.Builder builder = ImmutableMap.builder();
                    try {
                        for (String str : context.getAssets().list("phenotype")) {
                            try {
                                open = context.getAssets().open("phenotype/" + str);
                            } catch (InvalidProtocolBufferException e) {
                                Log.e("PackageInfo", "Unable to read Phenotype PackageMetadata for " + str, e);
                            }
                            try {
                                PackageInfo packageInfo = new PackageInfo(context, PackageMetadataProto$PackageMetadata.parseFrom(open, ExtensionRegistryLite.getGeneratedRegistry()));
                                builder.put(packageInfo.getConfigPackage(), packageInfo);
                                if (open != null) {
                                    open.close();
                                }
                            } catch (Throwable th) {
                                if (open != null) {
                                    try {
                                        open.close();
                                    } catch (Throwable th2) {
                                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                                    }
                                }
                                throw th;
                                break;
                            }
                        }
                    } catch (IOException e2) {
                        Log.e("PackageInfo", "Unable to read Phenotype PackageMetadata from assets.", e2);
                    }
                    ImmutableMap buildOrThrow = builder.buildOrThrow();
                    packagesFromAssets = buildOrThrow;
                    map = buildOrThrow;
                }
            }
        }
        return map;
    }

    String getConfigPackage() {
        return this.configPackage;
    }
}
