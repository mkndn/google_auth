package com.google.android.apps.authenticator.migration;

import android.net.Uri;
import android.util.Base64;
import com.google.android.apps.authenticator.migration.OfflineMigration;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MigrationPayloadProcessor {
    private static final String MIGRATION_AUTHORITY = "offline";
    private static final String MIGRATION_SCHEME = "otpauth-migration";
    private static final String PAYLOAD_QUERY_PARAM = "data";

    private MigrationPayloadProcessor() {
    }

    public static OfflineMigration.MigrationPayload parse(String str) {
        Uri parse = Uri.parse(str);
        if (!MIGRATION_SCHEME.equals(parse.getScheme())) {
            throw new InvalidProtocolBufferException("Wrong scheme for migration URI");
        }
        if (!MIGRATION_AUTHORITY.equals(parse.getAuthority())) {
            throw new InvalidProtocolBufferException("Wrong authority in migration URI");
        }
        if (parse.getQueryParameter("data") == null) {
            throw new InvalidProtocolBufferException("Missing data parameter in migration URI");
        }
        try {
            OfflineMigration.MigrationPayload parseFrom = OfflineMigration.MigrationPayload.parseFrom(Base64.decode(parse.getQueryParameter("data"), 0), ExtensionRegistryLite.getGeneratedRegistry());
            if (!parseFrom.hasVersion()) {
                throw new InvalidProtocolBufferException("Missing version in migration payload");
            }
            if (parseFrom.getVersion() > 1) {
                throw new UnsupportedMigrationVersionException(parseFrom.getVersion());
            }
            if (parseFrom.getBatchSize() > 0 && parseFrom.getBatchIndex() >= 0 && parseFrom.getBatchSize() > parseFrom.getBatchIndex()) {
                return parseFrom;
            }
            throw new InvalidProtocolBufferException("Invalid batch parameters");
        } catch (IllegalArgumentException e) {
            throw new InvalidProtocolBufferException("Invalid base64: " + e.getMessage());
        }
    }

    public static String serialize(OfflineMigration.MigrationPayload migrationPayload) {
        return new Uri.Builder().scheme(MIGRATION_SCHEME).authority(MIGRATION_AUTHORITY).appendQueryParameter("data", Base64.encodeToString(migrationPayload.toByteArray(), 2)).build().toString();
    }
}
