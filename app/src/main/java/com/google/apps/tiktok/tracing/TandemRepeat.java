package com.google.apps.tiktok.tracing;

import com.google.apps.tiktok.tracing.SuffixTree;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class TandemRepeat {
    private static ImmutableMap elementToIndex(Object[] objArr) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        UnmodifiableIterator it = ImmutableSet.copyOf(objArr).iterator();
        int i = 0;
        while (it.hasNext()) {
            builder.put(it.next(), Integer.valueOf(i));
            i++;
        }
        return builder.buildOrThrow();
    }

    public static SuffixTree.TandemRepeatRegion findExcessiveRepeatedRegion(Object[] objArr) {
        ImmutableMap elementToIndex = elementToIndex(objArr);
        if (elementToIndex.size() > (objArr.length >> 2)) {
            return null;
        }
        SuffixTree.TandemRepeatRegion findLargestTandemRepeatRegion = getSuffixTree(objArr, elementToIndex).findLargestTandemRepeatRegion();
        if (findLargestTandemRepeatRegion.numSeen * (findLargestTandemRepeatRegion.end - findLargestTandemRepeatRegion.begin) < (objArr.length >> 2)) {
            return null;
        }
        return findLargestTandemRepeatRegion;
    }

    private static SuffixTree getSuffixTree(Object[] objArr, ImmutableMap immutableMap) {
        int[] iArr = new int[objArr.length + 1];
        for (int i = 0; i < objArr.length; i++) {
            iArr[i] = ((Integer) immutableMap.get(objArr[i])).intValue();
        }
        iArr[objArr.length] = immutableMap.size();
        return SuffixTree.create(iArr);
    }
}
