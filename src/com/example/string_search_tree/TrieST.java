package com.example.string_search_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TrieST<Value> implements StringST<Value> {
    private static final int R = 256;

    private static class Node implements IPrintableNode {
        @Nullable
        private Object val;
        private final Node[] next = new Node[R];

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

    }

    @Override
    public boolean contains(@NotNull String key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String longestPrefix(@NotNull String s) {
        return null;
    }

    @Override
    public Iterable<String> keysWithPrefix(@NotNull String s) {
        return null;
    }

    @Override
    public Iterable<String> keysThatMatch(@NotNull String s) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<String> keys() {
        return null;
    }
}
