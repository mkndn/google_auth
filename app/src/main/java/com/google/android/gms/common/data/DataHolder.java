package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Parcelable.Creator CREATOR = new DataHolderCreator();
    private static final Builder EMPTY = new Builder(new String[0], null) { // from class: com.google.android.gms.common.data.DataHolder.1
    };
    Bundle mColumnBundle;
    private final String[] mColumns;
    private Object mLeakIdentifier;
    private final Bundle mMetadata;
    int mRowCount;
    private final int mStatusCode;
    final int mVersionCode;
    int[] mWindowRowOffsets;
    private final CursorWindow[] mWindows;
    boolean mClosed = false;
    private boolean mIsLeakDetectionEnabled = true;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private final String[] mColumns;
        private boolean mIsSorted;
        private final ArrayList mRows;
        private String mSortedColumn;
        private final String mUniqueColumn;
        private final HashMap mUniqueColumnIndices;

        private Builder(String[] strArr, String str) {
            this.mColumns = (String[]) Preconditions.checkNotNull(strArr);
            this.mRows = new ArrayList();
            this.mUniqueColumn = str;
            this.mUniqueColumnIndices = new HashMap();
            this.mIsSorted = false;
            this.mSortedColumn = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mVersionCode = i;
        this.mColumns = strArr;
        this.mWindows = cursorWindowArr;
        this.mStatusCode = i2;
        this.mMetadata = bundle;
        if (BuildConstants.APK_IS_DEBUG_APK) {
            this.mLeakIdentifier = Log.getStackTraceString(new Throwable());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                int i = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr = this.mWindows;
                    if (i >= cursorWindowArr.length) {
                        break;
                    }
                    cursorWindowArr[i].close();
                    i++;
                }
            }
        }
    }

    protected void finalize() {
        try {
            if (this.mIsLeakDetectionEnabled && this.mWindows.length > 0 && !isClosed()) {
                close();
                if (BuildConstants.APK_IS_DEBUG_APK) {
                    throw new IllegalStateException("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (" + this.mLeakIdentifier.toString() + ")");
                } else {
                    Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: " + toString() + ")");
                }
            }
        } finally {
            super.finalize();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getColumns() {
        return this.mColumns;
    }

    public Bundle getMetadata() {
        return this.mMetadata;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CursorWindow[] getWindows() {
        return this.mWindows;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void validateContents() {
        this.mColumnBundle = new Bundle();
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = this.mColumns;
            if (i2 >= strArr.length) {
                break;
            }
            this.mColumnBundle.putInt(strArr[i2], i2);
            i2++;
        }
        this.mWindowRowOffsets = new int[this.mWindows.length];
        int i3 = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.mWindows;
            if (i < cursorWindowArr.length) {
                this.mWindowRowOffsets[i] = i3;
                i3 += this.mWindows[i].getNumRows() - (i3 - cursorWindowArr[i].getStartPosition());
                i++;
            } else {
                this.mRowCount = i3;
                return;
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        DataHolderCreator.writeToParcel(this, parcel, i);
        if ((i & 1) != 0) {
            close();
        }
    }
}
