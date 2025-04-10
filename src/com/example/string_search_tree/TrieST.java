package com.example.string_search_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.Queue;

public class TrieST<Value> implements StringST<Value> {
    private static final int R = 256;

    private static class Node implements IPrintableNode {
        @Nullable
        private Object val;
        private final Node[] next = new Node[R];

        @Override
        public @NotNull String getName(int indexInParent) {
            return indexInParent == -1 ? "root" : String.valueOf((char) indexInParent);
        }

        @Override
        public @Nullable String getValue() {
            return val == null ? null : val.toString();
        }

        @Override
        public int getChildrenSize() {
            return next.length;
        }

        @Override
        public IPrintableNode getChild(int index) {
            return next[index];
        }
    }

    public Node root;


    @Override
    public void put(@NotNull String key, @Nullable Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(@Nullable Node x, @NotNull String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable Value get(@NotNull String key) {
        Node node = get(root, key, 0);
        if (node == null) return null;
        return (Value) node.val;
    }

    @Nullable
    private Node get(@Nullable Node x, @NotNull String key, int d) {
        if (x == null) return null;// 节点为null，说明当前字符没有匹配到
        if (d == key.length()) return x;// 已遍历完key，说明历史路径正好是key
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);// 寻找key的下一个字符匹配的节点
    }

    @Override
    public void delete(@NotNull String key) {
        delete(root, key, 0);
    }

    @Nullable
    private Node delete(@Nullable Node x, @NotNull String key, int d) {
        if (x == null) return null;
        if (d == key.length()) x.val = null;// 当前位置正好是key，删除value
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);//找到key的下一个节点进行删除
        }

        if (x.val != null) return x;// 当前节点存了值，保留节点
        for (int i = 0; i < x.next.length; i++) {
            if (x.next[i] != null) return x;// 当前节点存在子节点，保留节点
        }
        return null;// 当前节点的值为空，且子节点都是空，则需要删除该节点
    }

    @Override
    public boolean contains(@NotNull String key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String longestPrefix(@NotNull String s) {
        return null;
    }

    @Override
    public Iterable<String> keysWithPrefix(@NotNull String s) {
        Queue<String> q = new LinkedList<>();
        collect(get(root, s, 0), s, q);
        return q;
    }

    private void collect(@Nullable Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.add(pre);
        for (char c = 0; c < x.next.length; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    @Override
    public Iterable<String> keysThatMatch(@NotNull String s) {
        Queue<String> q = new LinkedList<>();
        collect(root, "", s, q);
        return q;
    }

    private void collect(@Nullable Node x, String pre, String pat, Queue<String> q) {
        if (x == null) return;
        int d = pre.length();
        // 长度相同说明匹配到头了
        if (d == pat.length() && x.val != null) q.add(pre); // val不为null，说明是完整的key
        if (d == pat.length()) return;

        char patC = pat.charAt(d);

        for (char c = 0; c < x.next.length; c++) {
            if (patC == c || patC == '.')
                collect(x.next[c], pre + c, pat, q);
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    // size的延迟实现，性能不好
    private int size(@Nullable Node x) {
        if (x == null) return 0;
        int cnt = 0;
        if (x.val != null) cnt++;
        for (int i = 0; i < x.next.length; i++) {
            cnt += size(x.next[i]);
        }
        return cnt;
    }

    @Override
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }
}
