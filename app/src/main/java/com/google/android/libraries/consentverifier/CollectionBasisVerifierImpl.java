package com.google.android.libraries.consentverifier;

import android.os.Looper;
import android.os.NetworkOnMainThreadException;
import android.privacy.annotations.mappings.UseCaseMappings;
import android.util.LruCache;
import com.google.android.libraries.clock.impl.SystemClockImpl;
import com.google.android.libraries.consentverifier.cache.CollectionBasisMessageInfoCache;
import com.google.android.libraries.consentverifier.flags.Flags;
import com.google.android.libraries.consentverifier.initializer.Initializer;
import com.google.android.libraries.consentverifier.logging.AppInfoHelper;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLogger;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLoggerFactory;
import com.google.android.libraries.consentverifier.logging.VerificationFailureLogger;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.privacy.delphi.annotations.generator.NameHasher;
import com.google.protobuf.Any;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.WireFormat;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import com.google.protos.collection_basis_verifier.logging.VerificationFailureEnum$VerificationFailure;
import com.google.protos.collection_basis_verifier.logging.VerificationFailureLogOuterClass$VerificationFailureLog;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import wireless.android.privacy.annotations.artifact.proto.AndroidCollectionBasis$CollectionBasisFieldInfo;
import wireless.android.privacy.annotations.artifact.proto.AndroidCollectionBasis$CollectionBasisHolder;
import wireless.android.privacy.annotations.artifact.proto.AndroidCollectionBasis$CollectionBasisMessageInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CollectionBasisVerifierImpl implements CollectionBasisVerifierDecider {
    private static final int ANY_JAVA_NAME_HASH = NameHasher.computeHash(Any.class.getName());
    private static final ImmutableMap WIRE_FORMAT_TYPES = new ImmutableMap.Builder().put(0, "WIRETYPE_VARINT").put(1, "WIRETYPE_FIXED64").put(2, "WIRETYPE_LENGTH_DELIMITED").put(3, "WIRETYPE_START_GROUP").put(4, "WIRETYPE_END_GROUP").put(5, "WIRETYPE_FIXED32").buildOrThrow();
    private static final Map lastFailureLoggingTimes = new HashMap();
    private static final AppInfoHelper appInfoHelper = new AppInfoHelper();
    private final LruCache messageCache = new CollectionBasisMessageInfoCache(4096);
    private final LruCache protoToJavaCache = new LruCache(100);
    private final Initializer initializer = new Initializer();

    private static boolean consentsSatisfied(CollectionBasisContext collectionBasisContext, AndroidCollectionBasis$CollectionBasisHolder androidCollectionBasis$CollectionBasisHolder, CollectionBasisManager collectionBasisManager, VerificationFailureLogger verificationFailureLogger, Optional optional) {
        for (int i = 0; androidCollectionBasis$CollectionBasisHolder != null && i < androidCollectionBasis$CollectionBasisHolder.getCollectionBasesCount(); i++) {
            AndroidPrivacyAnnotationsEnums$CollectionUseCase collectionBases = androidCollectionBasis$CollectionBasisHolder.getCollectionBases(i);
            if (!collectionBasisManager.verifyConsent(collectionBases, collectionBasisContext)) {
                if (Flags.enableLogging()) {
                    VerificationFailureLogOuterClass$VerificationFailureLog.Builder basisExpression = verificationFailureLogger.build(VerificationFailureEnum$VerificationFailure.VF_COLLECTION_BASIS_REJECTED).setUseCase(collectionBases).setBasisExpression(UseCaseMappings.getCollectionBasisSpecs(collectionBases));
                    if (optional.isPresent()) {
                        basisExpression.addFieldPath(((Integer) optional.get()).intValue());
                    }
                    verificationFailureLogger.log(basisExpression);
                }
                return false;
            }
        }
        return true;
    }

    private static AndroidCollectionBasis$CollectionBasisHolder getCollectionBasisHolderForFeature(AndroidCollectionBasis$CollectionBasisFieldInfo androidCollectionBasis$CollectionBasisFieldInfo, int i) {
        return (AndroidCollectionBasis$CollectionBasisHolder) androidCollectionBasis$CollectionBasisFieldInfo.getFeatureCollectionBasesMap().get(Integer.valueOf(i));
    }

    private static boolean hasConsents(AndroidCollectionBasis$CollectionBasisHolder androidCollectionBasis$CollectionBasisHolder) {
        return (androidCollectionBasis$CollectionBasisHolder == null || androidCollectionBasis$CollectionBasisHolder.getCollectionBasesCount() == 0) ? false : true;
    }

    private static boolean isAnyTypeUrlField(int i, int i2) {
        return i == ANY_JAVA_NAME_HASH && i2 == 1;
    }

    private static boolean isAnyValueField(int i, int i2) {
        return i == ANY_JAVA_NAME_HASH && i2 == 2;
    }

    private static boolean isAnyValueFieldWithAType(int i, int i2, String str) {
        return str != null && isAnyValueField(i, i2);
    }

    private static Integer lookupAnyValueProto(CollectionBasisMapping collectionBasisMapping, String str) {
        if (str == null || !str.startsWith("type.googleapis.com/")) {
            return null;
        }
        try {
            return Integer.valueOf(collectionBasisMapping.getProtoToJavaHashOrThrow(NameHasher.computeHash(str.substring("type.googleapis.com/".length()))));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x022d A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean traverseMessage(CollectionBasisContext collectionBasisContext, int i, int i2, byte[] bArr, CollectionBasisMapping collectionBasisMapping, CollectionBasisManager collectionBasisManager, CollectionBasisLogger collectionBasisLogger) {
        CollectionBasisContext collectionBasisContext2;
        VerificationFailureLogger verificationFailureLogger;
        AndroidCollectionBasis$CollectionBasisMessageInfo androidCollectionBasis$CollectionBasisMessageInfo;
        int totalBytesRead;
        int i3;
        Integer num;
        int i4;
        boolean z;
        AndroidCollectionBasis$CollectionBasisMessageInfo messageOrThrow;
        CodedInputStream newInstance = CodedInputStream.newInstance(bArr);
        ArrayDeque arrayDeque = new ArrayDeque();
        VerificationFailureLogger verificationFailureLogger2 = new VerificationFailureLogger(appInfoHelper, lastFailureLoggingTimes, new SystemClockImpl(), collectionBasisLogger, collectionBasisContext, i, i2, bArr.length, arrayDeque);
        int i5 = i;
        AndroidCollectionBasis$CollectionBasisMessageInfo message = collectionBasisMapping.getMessage(i5);
        if (message == null) {
            if (Flags.enableLogging()) {
                verificationFailureLogger2.log(verificationFailureLogger2.build(VerificationFailureEnum$VerificationFailure.VF_ANNOTATION_MISSING_FAILURE));
            }
            return false;
        }
        if (newInstance.isAtEnd()) {
            collectionBasisContext2 = collectionBasisContext;
            verificationFailureLogger = verificationFailureLogger2;
        } else {
            collectionBasisContext2 = collectionBasisContext;
            verificationFailureLogger = verificationFailureLogger2;
            if (!consentsSatisfied(collectionBasisContext2, getCollectionBasisHolderForFeature(message, i2), collectionBasisManager, verificationFailureLogger, Optional.absent())) {
                return false;
            }
        }
        boolean hasConsents = hasConsents(getCollectionBasisHolderForFeature(message, i2));
        String str = null;
        Integer num2 = null;
        int i6 = 0;
        while (!newInstance.isAtEnd()) {
            int readTag = newInstance.readTag();
            int tagFieldNumber = WireFormat.getTagFieldNumber(readTag);
            int tagWireType = WireFormat.getTagWireType(readTag);
            boolean z2 = hasConsents;
            long j = tagFieldNumber;
            ArrayDeque arrayDeque2 = arrayDeque;
            if (message.getFieldsMap().containsKey(Long.valueOf(j))) {
                AndroidCollectionBasis$CollectionBasisFieldInfo fieldsOrThrow = message.getFieldsOrThrow(j);
                androidCollectionBasis$CollectionBasisMessageInfo = message;
                boolean z3 = (tagWireType == 2 || tagWireType == 3 || tagWireType == 4) ? false : true;
                if (z3) {
                    arrayDeque = arrayDeque2;
                } else if (!fieldsOrThrow.hasId() && !isAnyValueFieldWithAType(i5, tagFieldNumber, str)) {
                    arrayDeque = arrayDeque2;
                } else if (tagWireType == 2 || tagWireType == 3) {
                    if (collectionBasisMapping.hasMessage(fieldsOrThrow.getId()) || isAnyValueField(i5, tagFieldNumber)) {
                        arrayDeque = arrayDeque2;
                        arrayDeque.push(new MessageContext(i5, num2, i6, z2, tagFieldNumber));
                        if (isAnyValueField(i5, tagFieldNumber)) {
                            Integer lookupAnyValueProto = lookupAnyValueProto(collectionBasisMapping, str);
                            if (lookupAnyValueProto == null) {
                                if (Flags.enableLogging()) {
                                    verificationFailureLogger.log(verificationFailureLogger.build(VerificationFailureEnum$VerificationFailure.VF_ANY_LOOKUP_FAILURE).setAnyUrl(Strings.nullToEmpty(str)));
                                    return false;
                                }
                                return false;
                            }
                            i5 = lookupAnyValueProto.intValue();
                        } else {
                            i5 = fieldsOrThrow.getId();
                        }
                        num2 = tagWireType == 3 ? null : Integer.valueOf(newInstance.readRawVarint32());
                        i6 = newInstance.getTotalBytesRead();
                        message = collectionBasisMapping.getMessageOrThrow(i5);
                        hasConsents = z2 || hasConsents(getCollectionBasisHolderForFeature(fieldsOrThrow, i2)) || hasConsents(getCollectionBasisHolderForFeature(message, i2));
                        if ((num2 == null || num2.intValue() > 0) && !(consentsSatisfied(collectionBasisContext2, getCollectionBasisHolderForFeature(fieldsOrThrow, i2), collectionBasisManager, verificationFailureLogger, Optional.of(Integer.valueOf(tagFieldNumber))) && consentsSatisfied(collectionBasisContext2, getCollectionBasisHolderForFeature(message, i2), collectionBasisManager, verificationFailureLogger, Optional.absent()))) {
                            return false;
                        }
                        str = null;
                        if (num2 != null || tagWireType == 4) {
                            totalBytesRead = num2 == null ? newInstance.getTotalBytesRead() : i6 + num2.intValue();
                            while (true) {
                                if (newInstance.getTotalBytesRead() < totalBytesRead) {
                                    break;
                                } else if (newInstance.getTotalBytesRead() > totalBytesRead) {
                                    if (Flags.enableLogging()) {
                                        verificationFailureLogger.log(verificationFailureLogger.build(VerificationFailureEnum$VerificationFailure.VF_PROTOBUF_FORMAT_FAILURE));
                                        return false;
                                    }
                                    return false;
                                } else if (arrayDeque.isEmpty()) {
                                    if (Flags.enableLogging()) {
                                        verificationFailureLogger.log(verificationFailureLogger.build(VerificationFailureEnum$VerificationFailure.VF_PROTOBUF_FORMAT_FAILURE));
                                        return false;
                                    }
                                    return false;
                                } else {
                                    MessageContext messageContext = (MessageContext) arrayDeque.pop();
                                    i3 = messageContext.messageId;
                                    num = messageContext.messageLength;
                                    i4 = messageContext.messageStart;
                                    z = messageContext.hasConsent;
                                    messageOrThrow = collectionBasisMapping.getMessageOrThrow(i3);
                                    if (num == null) {
                                        num2 = num;
                                        i6 = i4;
                                        hasConsents = z;
                                        i5 = i3;
                                        message = messageOrThrow;
                                        break;
                                    }
                                    num2 = num;
                                    i6 = i4;
                                    totalBytesRead = num.intValue() + i4;
                                    hasConsents = z;
                                    i5 = i3;
                                    message = messageOrThrow;
                                }
                            }
                        }
                    } else if (!consentsSatisfied(collectionBasisContext2, getCollectionBasisHolderForFeature(fieldsOrThrow, i2), collectionBasisManager, verificationFailureLogger, Optional.of(Integer.valueOf(tagFieldNumber)))) {
                        return false;
                    } else {
                        newInstance.skipField(readTag);
                    }
                }
                if (z3 && fieldsOrThrow.hasId() && collectionBasisMapping.hasMessage(fieldsOrThrow.getId())) {
                    if (Flags.enableLogging()) {
                        verificationFailureLogger.log(verificationFailureLogger.build(VerificationFailureEnum$VerificationFailure.VF_FIELD_TYPE_MISMATCH).addFieldPath(j));
                        return false;
                    }
                    return false;
                }
                AndroidCollectionBasis$CollectionBasisHolder collectionBasisHolderForFeature = getCollectionBasisHolderForFeature(fieldsOrThrow, i2);
                if (!z2 && !hasConsents(collectionBasisHolderForFeature)) {
                    if (Flags.enableLogging()) {
                        verificationFailureLogger.log(verificationFailureLogger.build(VerificationFailureEnum$VerificationFailure.VF_FIELD_NOT_ANNOTATED).addFieldPath(j));
                        return false;
                    }
                    return false;
                } else if (!consentsSatisfied(collectionBasisContext2, collectionBasisHolderForFeature, collectionBasisManager, verificationFailureLogger, Optional.of(Integer.valueOf(tagFieldNumber)))) {
                    return false;
                } else {
                    if (isAnyTypeUrlField(i5, tagFieldNumber)) {
                        str = newInstance.readString();
                    } else {
                        newInstance.skipField(readTag);
                        str = null;
                    }
                    message = androidCollectionBasis$CollectionBasisMessageInfo;
                    hasConsents = z2;
                    if (num2 != null) {
                    }
                    if (num2 == null) {
                    }
                    while (true) {
                        if (newInstance.getTotalBytesRead() < totalBytesRead) {
                            break;
                        }
                        num2 = num;
                        i6 = i4;
                        totalBytesRead = num.intValue() + i4;
                        hasConsents = z;
                        i5 = i3;
                        message = messageOrThrow;
                    }
                }
            } else if (!z2) {
                if (Flags.enableLogging()) {
                    verificationFailureLogger.log(verificationFailureLogger.build(VerificationFailureEnum$VerificationFailure.VF_FIELD_NOT_ANNOTATED).addFieldPath(j));
                    return false;
                }
                return false;
            } else {
                newInstance.skipField(readTag);
                androidCollectionBasis$CollectionBasisMessageInfo = message;
            }
            message = androidCollectionBasis$CollectionBasisMessageInfo;
            hasConsents = z2;
            arrayDeque = arrayDeque2;
            if (num2 != null) {
            }
            if (num2 == null) {
            }
            while (true) {
                if (newInstance.getTotalBytesRead() < totalBytesRead) {
                    break;
                }
                num2 = num;
                i6 = i4;
                totalBytesRead = num.intValue() + i4;
                hasConsents = z;
                i5 = i3;
                message = messageOrThrow;
            }
        }
        return true;
    }

    @Override // com.google.android.libraries.consentverifier.CollectionBasisVerifierDecider
    public boolean collectionBasisMet(CollectionBasisContext collectionBasisContext, ProtoCollectionBasis protoCollectionBasis, byte[] bArr, CollectionBasisManager collectionBasisManager, Optional optional) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new NetworkOnMainThreadException();
        }
        Initializer initializer = this.initializer;
        AppInfoHelper appInfoHelper2 = appInfoHelper;
        initializer.maybeInit(collectionBasisContext, appInfoHelper2);
        if (Flags.enableAllFeatures()) {
            if (!optional.isPresent()) {
                optional = Optional.of(CollectionBasisLoggerFactory.getCollectionBasisLogger(collectionBasisContext.context(), appInfoHelper2));
            }
            try {
                return traverseMessage(collectionBasisContext, protoCollectionBasis.javaClassNameHash(), protoCollectionBasis.featureNameHash(), bArr, new CollectionBasisMapping(collectionBasisContext.context(), protoCollectionBasis.mappingRes(), this.messageCache, this.protoToJavaCache), collectionBasisManager, (CollectionBasisLogger) optional.get());
            } catch (IOException e) {
                if (Flags.enableLogging()) {
                    ((CollectionBasisLogger) optional.get()).logEvent((VerificationFailureLogOuterClass$VerificationFailureLog) VerificationFailureLogOuterClass$VerificationFailureLog.newBuilder().setAppName(collectionBasisContext.context().getPackageName()).setAppVersionCode(appInfoHelper.getVersionCode(collectionBasisContext.context())).setProtoId(protoCollectionBasis.javaClassNameHash()).setFeatureId(protoCollectionBasis.featureNameHash()).setDataLength(bArr.length).setVerificationFailure(VerificationFailureEnum$VerificationFailure.VF_ANNOTATION_LOAD_FAILURE).build());
                    return false;
                }
                return false;
            }
        }
        return true;
    }

    private static AndroidCollectionBasis$CollectionBasisHolder getCollectionBasisHolderForFeature(AndroidCollectionBasis$CollectionBasisMessageInfo androidCollectionBasis$CollectionBasisMessageInfo, int i) {
        return (AndroidCollectionBasis$CollectionBasisHolder) androidCollectionBasis$CollectionBasisMessageInfo.getFeatureCollectionBasesMap().get(Integer.valueOf(i));
    }
}
