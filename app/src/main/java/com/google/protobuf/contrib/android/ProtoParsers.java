package com.google.protobuf.contrib.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Preconditions;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoParsers {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InternalDontUse implements ParcelableProto {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.google.protobuf.contrib.android.ProtoParsers.InternalDontUse.1
            @Override // android.os.Parcelable.Creator
            public InternalDontUse createFromParcel(Parcel parcel) {
                byte[] bArr = new byte[parcel.readInt()];
                parcel.readByteArray(bArr);
                return new InternalDontUse(bArr, null);
            }

            @Override // android.os.Parcelable.Creator
            public InternalDontUse[] newArray(int i) {
                return new InternalDontUse[i];
            }
        };
        private volatile byte[] bytes;
        private volatile MessageLite message;

        private InternalDontUse(byte[] bArr, MessageLite messageLite) {
            Preconditions.checkArgument((bArr == null && messageLite == null) ? false : true, "Must have a message or bytes");
            this.bytes = bArr;
            this.message = messageLite;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        MessageLite getMessageUnsafe(MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
            if (this.message == null) {
                this.message = messageLite.toBuilder().mergeFrom(this.bytes, extensionRegistryLite).build();
            }
            return this.message;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (this.bytes == null) {
                byte[] bArr = new byte[this.message.getSerializedSize()];
                try {
                    this.message.writeTo(CodedOutputStream.newInstance(bArr));
                    this.bytes = bArr;
                } catch (IOException e) {
                    throw new AssertionError(e);
                }
            }
            parcel.writeInt(this.bytes.length);
            parcel.writeByteArray(this.bytes);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ParcelableProto extends Parcelable {
    }

    private static List asList(List list, MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(get((InternalDontUse) it.next(), messageLite, extensionRegistryLite));
        }
        return arrayList;
    }

    public static ParcelableProto asParcelable(MessageLite messageLite) {
        return new InternalDontUse(null, messageLite);
    }

    private static ArrayList asParcelableList(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(asParcelable((MessageLite) it.next()));
        }
        return arrayList;
    }

    public static List getList(Intent intent, String str, MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
        ArrayList arrayList;
        Parcelable parcelableExtra = intent.getParcelableExtra(str);
        if (parcelableExtra instanceof Bundle) {
            Bundle bundle = (Bundle) parcelableExtra;
            bundle.setClassLoader(InternalDontUse.class.getClassLoader());
            arrayList = bundle.getParcelableArrayList("protoparsers");
        } else {
            arrayList = (ArrayList) parcelableExtra;
        }
        return asList(arrayList, messageLite, extensionRegistryLite);
    }

    public static List getListTrusted(Intent intent, String str, MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
        try {
            return getList(intent, str, messageLite, extensionRegistryLite);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public static void put(Intent intent, String str, List list) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("protoparsers", asParcelableList(list));
        intent.putExtra(str, bundle);
    }

    private static MessageLite get(InternalDontUse internalDontUse, MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
        return internalDontUse.getMessageUnsafe(messageLite.getDefaultInstanceForType(), extensionRegistryLite);
    }
}
