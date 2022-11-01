package com.google.apps.tiktok.tracing;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SuffixTree {
    public static final int END_INDEX = 1073741824;
    private int activeEdge;
    private int activeLength;
    private Node activeNode;
    private final int[] input;
    private int remainingCharacters;
    private final Node root;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Candidate {
        final int begin;
        final int end;
        final Node node;
        final int numSeen;

        private Candidate(Node node, int i, int i2, int i3) {
            this.numSeen = i;
            this.begin = i2;
            this.end = i3;
            this.node = node;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Node {
        int begin;
        final Map children = new HashMap(0);
        final int end;
        Node suffixLink;

        Node(int i, int i2, Node node) {
            if (i > i2) {
                throw new IllegalArgumentException();
            }
            this.begin = i;
            this.end = i2;
            this.suffixLink = node;
        }

        public String toString() {
            return "Node" + System.identityHashCode(this);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TandemRepeatRegion {
        final int begin;
        final int end;
        final int numSeen;

        public TandemRepeatRegion(int i, int i2, int i3) {
            this.begin = i;
            this.end = i2;
            this.numSeen = i3;
        }
    }

    private SuffixTree(int[] iArr) {
        this.input = iArr;
        Node node = new Node(-1, -1, null);
        this.root = node;
        this.activeNode = node;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SuffixTree create(int[] iArr) {
        SuffixTree suffixTree = new SuffixTree(iArr);
        for (int i = 0; i < iArr.length; i++) {
            suffixTree.insert(i);
        }
        return suffixTree;
    }

    private void insert(int i) {
        this.remainingCharacters++;
        int i2 = this.input[i];
        Node node = null;
        while (this.remainingCharacters > 0) {
            if (this.activeLength == 0) {
                if (!this.activeNode.children.containsKey(Integer.valueOf(i2))) {
                    this.activeNode.children.put(Integer.valueOf(i2), new Node(i, 1073741824, null));
                    if (node != null) {
                        node.suffixLink = this.activeNode;
                    }
                    this.remainingCharacters--;
                    followSuffixLink();
                    node = null;
                } else {
                    if (node != null) {
                        node.suffixLink = this.activeNode;
                    }
                    this.activeEdge = i;
                    this.activeLength++;
                    walkDown();
                    return;
                }
            } else if (this.input[((Node) this.activeNode.children.get(Integer.valueOf(this.input[this.activeEdge]))).begin + this.activeLength] != i2) {
                Node cutBranch = cutBranch();
                if (node != null) {
                    node.suffixLink = cutBranch;
                }
                cutBranch.children.put(Integer.valueOf(i2), new Node(i, 1073741824, null));
                this.remainingCharacters--;
                followSuffixLink();
                node = cutBranch;
            } else {
                if (node != null) {
                    node.suffixLink = this.activeNode;
                }
                this.activeLength++;
                walkDown();
                return;
            }
        }
    }

    private void printChildren(Node node, StringBuilder sb) {
        for (Node node2 : node.children.values()) {
            sb.append("  ").append(node).append(" -> ").append(node2).append(" [label=\"").append(Arrays.toString(Arrays.copyOfRange(this.input, node2.begin, Math.min(this.input.length, node2.end + 1)))).append("\"]\n");
            printChildren(node2, sb);
        }
    }

    private boolean regionEquals(int i, int i2, int i3, int i4) {
        if (i < 0 || i3 < 0) {
            return false;
        }
        int min = Math.min(this.input.length, i2);
        if (min - i != Math.min(this.input.length, i4) - i3) {
            return false;
        }
        for (int i5 = i; i5 <= min; i5++) {
            int[] iArr = this.input;
            if (iArr[i5] != iArr[(i3 + i5) - i]) {
                return false;
            }
        }
        return true;
    }

    Node cutBranch() {
        Node node = (Node) this.activeNode.children.get(Integer.valueOf(this.input[this.activeEdge]));
        Node node2 = new Node(node.begin, (node.begin + this.activeLength) - 1, null);
        this.activeNode.children.put(Integer.valueOf(this.input[this.activeEdge]), node2);
        node2.children.put(Integer.valueOf(this.input[node2.end + 1]), node);
        node.begin = node2.end + 1;
        return node2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x009b, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TandemRepeatRegion findLargestTandemRepeatRegion() {
        Candidate candidate;
        ArrayDeque arrayDeque = new ArrayDeque();
        Candidate candidate2 = new Candidate(this.root, 0, -1, -1);
        arrayDeque.push(candidate2);
        while (!arrayDeque.isEmpty()) {
            Candidate candidate3 = (Candidate) arrayDeque.pop();
            for (Node node : candidate3.node.children.values()) {
                if (!regionEquals(candidate3.begin, candidate3.end, node.begin, node.end) && (!node.children.isEmpty() || !regionEquals(candidate3.begin, candidate3.end, node.begin, (node.begin + candidate3.end) - candidate3.begin))) {
                    candidate = new Candidate(node, 1, node.begin, node.end);
                } else {
                    candidate = new Candidate(node, candidate3.numSeen + 1, candidate3.begin, candidate3.end);
                }
                if (candidate2.numSeen < candidate.numSeen) {
                    candidate2 = candidate;
                }
                arrayDeque.push(candidate);
            }
        }
        int min = Math.min(this.input.length, candidate2.end + 1);
        Node node2 = this.root;
        int i = 0;
        loop2: while (true) {
            node2 = (Node) node2.children.get(Integer.valueOf(this.input[candidate2.begin + (i % (min - candidate2.begin))]));
            if (node2 == null) {
                break;
            }
            for (int i2 = node2.begin; i2 < node2.end + 1; i2++) {
                int[] iArr = this.input;
                if (i2 < iArr.length) {
                    if (iArr[candidate2.begin + (i % (min - candidate2.begin))] != this.input[i2]) {
                        break loop2;
                    }
                    i++;
                }
            }
        }
        return new TandemRepeatRegion(candidate2.begin, min, i / (min - candidate2.begin));
    }

    void followSuffixLink() {
        if (this.activeNode.suffixLink != null) {
            this.activeNode = this.activeNode.suffixLink;
        } else {
            this.activeNode = this.root;
            int i = this.activeLength;
            if (i > 0) {
                this.activeLength = i - 1;
            }
            if (this.remainingCharacters > 0) {
                this.activeEdge++;
            }
        }
        walkDown();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("digraph {\n");
        printChildren(this.root, sb);
        sb.append("}");
        return sb.toString();
    }

    void walkDown() {
        if (this.activeLength == 0) {
            return;
        }
        Node node = (Node) this.activeNode.children.get(Integer.valueOf(this.input[this.activeEdge]));
        while ((node.end - node.begin) + 1 <= this.activeLength) {
            this.activeEdge += (node.end - node.begin) + 1;
            this.activeNode = node;
            int i = this.activeLength - ((node.end - node.begin) + 1);
            this.activeLength = i;
            if (i > 0) {
                node = (Node) this.activeNode.children.get(Integer.valueOf(this.input[this.activeEdge]));
            }
        }
    }
}
