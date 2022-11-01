package com.google.android.libraries.consentverifier;

import android.content.Context;
import android.content.res.Resources;
import android.util.LruCache;
import com.google.common.collect.ImmutableList;
import com.google.privacy.delphi.annotations.generator.NameHasher;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import wireless.android.privacy.annotations.artifact.proto.AndroidCollectionBasis$CollectionBasisFieldInfo;
import wireless.android.privacy.annotations.artifact.proto.AndroidCollectionBasis$CollectionBasisHolder;
import wireless.android.privacy.annotations.artifact.proto.AndroidCollectionBasis$CollectionBasisMessageInfo;
import wireless.android.privacy.annotations.artifact.proto.AndroidCollectionBasis$CollectionBasisTagMapping;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CollectionBasisMapping {
    private static final int DEFAULT_FEATURE_HASH = NameHasher.computeHash("DEFAULT");
    private final Context context;
    private AndroidCollectionBasis$CollectionBasisTagMapping fullMapping;
    private final Integer mappingRawRes;
    private final LruCache messageCache;
    private final LruCache protoToJavaCache;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TagMappingDecodingException extends IOException {
        TagMappingDecodingException(String str) {
            super(str);
        }
    }

    public CollectionBasisMapping(Context context, int i, LruCache lruCache, LruCache lruCache2) {
        this.context = context;
        this.mappingRawRes = Integer.valueOf(i);
        this.messageCache = lruCache;
        this.protoToJavaCache = lruCache2;
    }

    private static Map buildCollectionBasisHolderMap(List list, ImmutableList immutableList) {
        int i;
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            if (num.intValue() < immutableList.size()) {
                AndroidCollectionBasis$CollectionBasisHolder androidCollectionBasis$CollectionBasisHolder = (AndroidCollectionBasis$CollectionBasisHolder) immutableList.get(num.intValue());
                if (androidCollectionBasis$CollectionBasisHolder.hasFeatureNameHash()) {
                    i = androidCollectionBasis$CollectionBasisHolder.getFeatureNameHash();
                } else {
                    i = DEFAULT_FEATURE_HASH;
                }
                hashMap.put(Integer.valueOf(i), (AndroidCollectionBasis$CollectionBasisHolder) ((AndroidCollectionBasis$CollectionBasisHolder.Builder) androidCollectionBasis$CollectionBasisHolder.toBuilder()).clearFeatureNameHash().build());
            } else {
                throw new TagMappingDecodingException(String.format("CollectionBasisHolder index(%d) exceeds list size(%d)", num, Integer.valueOf(immutableList.size())));
            }
        }
        return hashMap;
    }

    private AndroidCollectionBasis$CollectionBasisTagMapping loadMapping() {
        Context context = this.context;
        if (context != null && this.mappingRawRes != null) {
            try {
                InputStream openRawResource = context.getResources().openRawResource(this.mappingRawRes.intValue());
                AndroidCollectionBasis$CollectionBasisTagMapping unpackCollectionBasisTagMapping = unpackCollectionBasisTagMapping((AndroidCollectionBasis$CollectionBasisTagMapping) ((AndroidCollectionBasis$CollectionBasisTagMapping.Builder) AndroidCollectionBasis$CollectionBasisTagMapping.newBuilder().mergeFrom(CodedInputStream.newInstance(openRawResource), ExtensionRegistryLite.getEmptyRegistry())).build());
                if (openRawResource != null) {
                    openRawResource.close();
                }
                return unpackCollectionBasisTagMapping;
            } catch (Resources.NotFoundException e) {
                throw new IOException(e);
            }
        }
        throw new IOException("No context to load resource from");
    }

    public AndroidCollectionBasis$CollectionBasisMessageInfo getMessage(int i) {
        AndroidCollectionBasis$CollectionBasisMessageInfo androidCollectionBasis$CollectionBasisMessageInfo = (AndroidCollectionBasis$CollectionBasisMessageInfo) this.messageCache.get(Integer.valueOf(i));
        if (androidCollectionBasis$CollectionBasisMessageInfo == null) {
            if (this.fullMapping == null) {
                this.fullMapping = loadMapping();
            }
            androidCollectionBasis$CollectionBasisMessageInfo = (AndroidCollectionBasis$CollectionBasisMessageInfo) this.fullMapping.getMessagesMap().get(Integer.valueOf(i));
            if (androidCollectionBasis$CollectionBasisMessageInfo != null) {
                this.messageCache.put(Integer.valueOf(i), androidCollectionBasis$CollectionBasisMessageInfo);
                return androidCollectionBasis$CollectionBasisMessageInfo;
            }
        }
        return androidCollectionBasis$CollectionBasisMessageInfo;
    }

    public AndroidCollectionBasis$CollectionBasisMessageInfo getMessageOrThrow(int i) {
        AndroidCollectionBasis$CollectionBasisMessageInfo message = getMessage(i);
        if (message == null) {
            throw new IllegalArgumentException();
        }
        return message;
    }

    public int getProtoToJavaHashOrThrow(int i) {
        Integer num = (Integer) this.protoToJavaCache.get(Integer.valueOf(i));
        if (num == null) {
            if (this.fullMapping == null) {
                this.fullMapping = loadMapping();
            }
            num = Integer.valueOf(this.fullMapping.getProtoToJavaHashesOrThrow(i));
            this.protoToJavaCache.put(Integer.valueOf(i), num);
        }
        return num.intValue();
    }

    public boolean hasMessage(int i) {
        return getMessage(i) != null;
    }

    static AndroidCollectionBasis$CollectionBasisTagMapping unpackCollectionBasisTagMapping(AndroidCollectionBasis$CollectionBasisTagMapping androidCollectionBasis$CollectionBasisTagMapping) {
        List messagesListList = androidCollectionBasis$CollectionBasisTagMapping.getMessagesListList();
        List protoHashNamesList = androidCollectionBasis$CollectionBasisTagMapping.getProtoHashNamesList();
        if (messagesListList.size() != protoHashNamesList.size()) {
            throw new TagMappingDecodingException(String.format("ProtoHashNamesList[%d] and MessagesList[%d] must have same size", Integer.valueOf(protoHashNamesList.size()), Integer.valueOf(messagesListList.size())));
        }
        HashMap hashMap = new HashMap();
        ImmutableList copyOf = ImmutableList.copyOf((Collection) androidCollectionBasis$CollectionBasisTagMapping.getCollectionBasisHolderList());
        Iterator it = protoHashNamesList.iterator();
        Iterator it2 = messagesListList.iterator();
        while (it.hasNext() && it2.hasNext()) {
            hashMap.put((Integer) it.next(), unpackMessageInfo((AndroidCollectionBasis$CollectionBasisMessageInfo) it2.next(), copyOf));
        }
        return (AndroidCollectionBasis$CollectionBasisTagMapping) AndroidCollectionBasis$CollectionBasisTagMapping.newBuilder().putAllMessages(hashMap).putAllProtoToJavaHashes(androidCollectionBasis$CollectionBasisTagMapping.getProtoToJavaHashesMap()).build();
    }

    private static Map unpackFieldInfo(AndroidCollectionBasis$CollectionBasisMessageInfo androidCollectionBasis$CollectionBasisMessageInfo, ImmutableList immutableList) {
        GeneratedMessageLite build;
        List fieldsCollectionBasisHolderIndicesList = androidCollectionBasis$CollectionBasisMessageInfo.getFieldsCollectionBasisHolderIndicesList();
        List fieldCollectionBasisHolderTagNumbersList = androidCollectionBasis$CollectionBasisMessageInfo.getFieldCollectionBasisHolderTagNumbersList();
        if (fieldsCollectionBasisHolderIndicesList.size() != fieldCollectionBasisHolderTagNumbersList.size()) {
            throw new TagMappingDecodingException(String.format("TagNumbersList[%d] and CollectionBasisFieldList[%d] must have same size", Integer.valueOf(fieldCollectionBasisHolderTagNumbersList.size()), Integer.valueOf(fieldsCollectionBasisHolderIndicesList.size())));
        }
        HashMap hashMap = new HashMap();
        Iterator it = fieldsCollectionBasisHolderIndicesList.iterator();
        Iterator it2 = fieldCollectionBasisHolderTagNumbersList.iterator();
        while (it2.hasNext() && it.hasNext()) {
            hashMap.put((Long) it2.next(), (AndroidCollectionBasis$CollectionBasisFieldInfo) AndroidCollectionBasis$CollectionBasisFieldInfo.newBuilder().putAllFeatureCollectionBases(buildCollectionBasisHolderMap(ImmutableList.of((Object) ((Integer) it.next())), immutableList)).build());
        }
        List fieldsListList = androidCollectionBasis$CollectionBasisMessageInfo.getFieldsListList();
        List tagNumbersList = androidCollectionBasis$CollectionBasisMessageInfo.getTagNumbersList();
        if (fieldsListList.size() != tagNumbersList.size()) {
            throw new TagMappingDecodingException(String.format("TagNumbersList[%d] and CollectionBasisFieldList[%d] must have same size", Integer.valueOf(tagNumbersList.size()), Integer.valueOf(fieldsListList.size())));
        }
        Iterator it3 = tagNumbersList.iterator();
        Iterator it4 = fieldsListList.iterator();
        while (it3.hasNext() && it4.hasNext()) {
            Long l = (Long) it3.next();
            AndroidCollectionBasis$CollectionBasisFieldInfo androidCollectionBasis$CollectionBasisFieldInfo = (AndroidCollectionBasis$CollectionBasisFieldInfo) it4.next();
            Map buildCollectionBasisHolderMap = buildCollectionBasisHolderMap(androidCollectionBasis$CollectionBasisFieldInfo.getCollectionBasisHolderIndicesList(), immutableList);
            if (hashMap.containsKey(l)) {
                build = ((AndroidCollectionBasis$CollectionBasisFieldInfo.Builder) ((AndroidCollectionBasis$CollectionBasisFieldInfo.Builder) androidCollectionBasis$CollectionBasisFieldInfo.toBuilder()).mergeFrom((GeneratedMessageLite) ((AndroidCollectionBasis$CollectionBasisFieldInfo) hashMap.get(l)))).build();
            } else {
                build = ((AndroidCollectionBasis$CollectionBasisFieldInfo.Builder) androidCollectionBasis$CollectionBasisFieldInfo.toBuilder()).putAllFeatureCollectionBases(buildCollectionBasisHolderMap).clearCollectionBasisHolderIndices().build();
            }
            hashMap.put(l, (AndroidCollectionBasis$CollectionBasisFieldInfo) build);
        }
        return hashMap;
    }

    private static AndroidCollectionBasis$CollectionBasisMessageInfo unpackMessageInfo(AndroidCollectionBasis$CollectionBasisMessageInfo androidCollectionBasis$CollectionBasisMessageInfo, ImmutableList immutableList) {
        Map buildCollectionBasisHolderMap = buildCollectionBasisHolderMap(androidCollectionBasis$CollectionBasisMessageInfo.getCollectionBasisHolderIndicesList(), immutableList);
        return (AndroidCollectionBasis$CollectionBasisMessageInfo) ((AndroidCollectionBasis$CollectionBasisMessageInfo.Builder) androidCollectionBasis$CollectionBasisMessageInfo.toBuilder()).putAllFeatureCollectionBases(buildCollectionBasisHolderMap).clearCollectionBasisHolderIndices().putAllFields(unpackFieldInfo(androidCollectionBasis$CollectionBasisMessageInfo, immutableList)).clearTagNumbers().clearFieldsList().clearFieldsCollectionBasisHolderIndices().clearFieldCollectionBasisHolderTagNumbers().build();
    }
}
