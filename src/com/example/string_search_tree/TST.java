package com.example.string_search_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TST<Value> implements StringST<Value> {
    private class Node implements IPrintableNode {
        char c;
        @Nullable
        Node left, mid, right;
        @Nullable
        Value val;

        @Override
        public @NotNull String getName(int indexInParent) {
            return String.valueOf(c);
        }

        @Override
        public @Nullable String getValue() {
            return val == null ? null : val.toString();
        }

        @Override
        public int getChildrenSize() {
            return 3;
        }

        @Override
        public IPrintableNode getChild(int index) {
            IPrintableNode r;
            switch (index) {
                case 0:
                    r = left;
                    break;
                case 1:
                    r = mid;
                    break;
                case 2:
                    r = right;
                    break;
                default:
                    r = null;
            }
            if (r == null) {
                r = new IPrintableNode() {
                    @Override
                    public @NotNull String getName(int indexInParent) {
                        return "null";
                    }

                    @Override
                    public @Nullable String getValue() {
                        return null;
                    }

                    @Override
                    public int getChildrenSize() {
                        return 0;
                    }

                    @Override
                    public IPrintableNode getChild(int index) {
                        return null;
                    }
                };
            }
            return r;
        }
    }

    @Nullable
    public Node root;

    @Override
    public void put(@NotNull String key, @Nullable Value val) {
        root = put(root, key, val, 0);
    }

    private @NotNull Node put(@Nullable Node x, @NotNull String key, @Nullable Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d == key.length() - 1) {
            x.val = val;
        } else {
            x.mid = put(x.mid, key, val, d + 1);
        }
        return x;
    }

    @Override
    public @Nullable Value get(@NotNull String key) {
        Node node = get(root, key, 0);
        return node != null ? node.val : null;
    }

    @Nullable
    private Node get(@Nullable Node x, @NotNull String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);

        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d == key.length() - 1) return x;
        else return get(x.mid, key, d + 1);
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
