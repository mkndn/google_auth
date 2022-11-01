package com.google.privacy.delphi.common.annotations;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasis;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisSpec;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisSpecUtil {
    private static String unpackAndExpression(AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec androidPrivacyAnnotationsEnums$CollectionBasisAndSpec) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis : androidPrivacyAnnotationsEnums$CollectionBasisAndSpec.getBasisList()) {
            builder.add((Object) androidPrivacyAnnotationsEnums$CollectionBasis.name());
        }
        String join = Joiner.on(" AND ").join(builder.build());
        ArrayList arrayList = new ArrayList();
        arrayList.add(join);
        for (AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec androidPrivacyAnnotationsEnums$CollectionBasisOrSpec : androidPrivacyAnnotationsEnums$CollectionBasisAndSpec.getOrSpecList()) {
            arrayList.add(unpackOrExpression(androidPrivacyAnnotationsEnums$CollectionBasisOrSpec));
        }
        return "(" + Joiner.on(" AND ").join(arrayList) + ")";
    }

    public static String unpackBasisExpression(AndroidPrivacyAnnotationsEnums$CollectionBasisSpec androidPrivacyAnnotationsEnums$CollectionBasisSpec) {
        if (androidPrivacyAnnotationsEnums$CollectionBasisSpec.hasBasis()) {
            return androidPrivacyAnnotationsEnums$CollectionBasisSpec.getBasis().name();
        }
        if (androidPrivacyAnnotationsEnums$CollectionBasisSpec.hasAndSpec()) {
            return unpackAndExpression(androidPrivacyAnnotationsEnums$CollectionBasisSpec.getAndSpec());
        }
        if (androidPrivacyAnnotationsEnums$CollectionBasisSpec.hasOrSpec()) {
            return unpackOrExpression(androidPrivacyAnnotationsEnums$CollectionBasisSpec.getOrSpec());
        }
        return "";
    }

    private static String unpackOrExpression(AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec androidPrivacyAnnotationsEnums$CollectionBasisOrSpec) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis : androidPrivacyAnnotationsEnums$CollectionBasisOrSpec.getBasisList()) {
            builder.add((Object) androidPrivacyAnnotationsEnums$CollectionBasis.name());
        }
        String join = Joiner.on(" OR ").join(builder.build());
        ArrayList arrayList = new ArrayList();
        arrayList.add(join);
        for (AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec androidPrivacyAnnotationsEnums$CollectionBasisAndSpec : androidPrivacyAnnotationsEnums$CollectionBasisOrSpec.getAndSpecList()) {
            arrayList.add(unpackAndExpression(androidPrivacyAnnotationsEnums$CollectionBasisAndSpec));
        }
        return "(" + Joiner.on(" OR ").join(arrayList) + ")";
    }
}
