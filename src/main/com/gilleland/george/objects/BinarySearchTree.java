package com.gilleland.george.objects;

import com.gilleland.george.exceptions.EmptyTreeException;
import com.gilleland.george.exceptions.NodeNotFoundException;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Wes Gilleland on 11/4/2015.
 */
public class BinarySearchTree<K extends Comparable<K>> {
    protected BinarySearchTreeNode<K> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(BinarySearchTreeNode<K> initial_root) {
        this.root = initial_root;
    }

    public BinarySearchTree(K data) {
        this.root = new BinarySearchTreeNode<>(data);
    }

    @SafeVarargs
    public final void insert(BinarySearchTreeNode<K>... data) {
        for (BinarySearchTreeNode<K> d : data) {
            this.root = this.insert(this.root, d);
        }
    }

    /**
     * Insert data into a tree with a given root. Useful for inserting mid-tree and such.
     * All comparisons are done in lowercase for the string values. If the value already
     * exists, then its internal count is incremented
     *
     * @param root The root to start inserting
     * @param node The node to insert
     * @return The resulting tree or node
     */
    public BinarySearchTreeNode<K> insert(BinarySearchTreeNode<K> root, BinarySearchTreeNode<K> node) {
        if (root == null) {
            root = node;
            return root;
        }
        int root_comparision = node.toString().compareToIgnoreCase(root.toString());
        if (root_comparision == 0) {
            root.count += 1;
        } else if (root_comparision < 0) {
            root.left = this.insert(root.left, node);
        } else {
            root.right = this.insert(root.right, node);
        }
        return root;
    }

    /**
     * Searches through the current tree for a given key. Will return a match if
     * a node exists that has a value that starts with key or is equal to key.
     * All comparisons are in lowercase.
     *
     * @param key The key containing the value to search for
     * @return The node if it finds it
     * @throws EmptyTreeException    If the current tree is empty
     * @throws NodeNotFoundException If the node isn't found
     */
    public BinarySearchTreeNode<K> searchFirst(K key) throws EmptyTreeException, NodeNotFoundException {
        if (this.root == null) {
            throw new EmptyTreeException();
        }

        LinkedList<BinarySearchTreeNode<K>> node_queue = new LinkedList<>();
        BinarySearchTreeNode<K> current;
        node_queue.add(this.root);
        String key_string = key.toString().toLowerCase();

        while (!node_queue.isEmpty()) {
            current = node_queue.pop();
            String current_string = current.toString().toLowerCase();
            if (current_string.equals(key_string) || current_string.startsWith(key_string)) {
                return current;
            }
            if (current.left != null) {
                node_queue.add(current.left);
            }
            if (current.right != null) {
                node_queue.add(current.right);
            }
        }
        throw new NodeNotFoundException();
    }

    /**
     * Searches through the current tree for a given key. Will search through all
     * nodes in the tree. Returns nodes that either have keys equal to the key or
     * keys that start with the key as a prefix. All comparisons are done in lowercase
     *
     * @param key The key containing the value to search for
     * @return The nodes that match the key
     * @throws EmptyTreeException    If the current tree is empty
     * @throws NodeNotFoundException If no nodes are found to match the key
     */
    public ArrayList<BinarySearchTreeNode<K>> searchAll(K key) throws EmptyTreeException, NodeNotFoundException {
        if (this.root == null) {
            throw new EmptyTreeException();
        }

        LinkedList<BinarySearchTreeNode<K>> node_queue = new LinkedList<>();
        BinarySearchTreeNode<K> current;
        node_queue.add(this.root);
        String key_string = key.toString().toLowerCase();
        ArrayList<BinarySearchTreeNode<K>> results = new ArrayList<>();

        while (!node_queue.isEmpty()) {
            current = node_queue.pop();
            String current_string = current.toString().toLowerCase();
            if (current_string.equals(key_string) || current_string.startsWith(key_string)) {
                results.add(current);
            }
            if (current.left != null) {
                node_queue.add(current.left);
            }
            if (current.right != null) {
                node_queue.add(current.right);
            }
        }

        if (results.isEmpty()) {
            throw new NodeNotFoundException();
        }
        return results;
    }

    /**
     * Displays the contents of the current tree in a Breadth-First Search pattern
     */
    public void display() {
        if (this.root == null) {
            System.out.println("This tree is empty!");
            return;
        }

        LinkedList<BinarySearchTreeNode<K>> node_queue = new LinkedList<>();
        BinarySearchTreeNode<K> current;
        node_queue.add(this.root);
        while (!node_queue.isEmpty()) {
            current = node_queue.pop();
            System.out.printf("%s \n", current.toString());
            if (current.left != null) {
                node_queue.add(current.left);
            }
            if (current.right != null) {
                node_queue.add(current.right);
            }
        }
    }

    /**
     * Deletes a node from the current tree.
     *
     * @param key The key value of the node to delete
     * @throws NodeNotFoundException
     * @throws EmptyTreeException
     */
    public void delete(K key) throws NodeNotFoundException, EmptyTreeException {
        if (this.root == null) {
            throw new EmptyTreeException();
        }
        this.root = this.remove(key, this.root);
    }

    /**
     * Internal method to remove from a subtree
     *
     * @param key
     * @param root
     * @return
     */
    public BinarySearchTreeNode<K> remove(K key, BinarySearchTreeNode<K> root) {
        if (root == null) {
            return root;
        }
        int comparison = root.toString().compareToIgnoreCase(key.toString());
        if (comparison == -1) {
            root.left = this.remove(key, root.left);
        } else if (comparison == 1) {
            root.right = this.remove(key, root.right);
        } else if (root.left != null && root.right != null) {
            root.setData(this.findMin(root.right).getData());
            root.right = this.remove(root.data, root.right);
        } else {
            if (root.left != null) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    /**
     * Internal method to find the smallest item in a subtree
     *
     * @param root The root of the tree to search
     * @return Node containing the smallest item
     */
    private BinarySearchTreeNode<K> findMin(BinarySearchTreeNode<K> root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            return root;
        }
        return this.findMin(root.left);
    }

    public boolean isEmpty() {
        return this.root == null;
    }
}
