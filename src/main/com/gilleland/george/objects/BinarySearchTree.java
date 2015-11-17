package com.gilleland.george.objects;

import com.gilleland.george.exceptions.EmptyTreeException;
import com.gilleland.george.exceptions.NodeNotFoundException;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * An attempt at a generic Binary Search Tree
 */
public class BinarySearchTree<K extends Comparable<K>> {
    /**
     * The root of the whole tree
     */
    protected BinarySearchTreeNode<K> root;

    /**
     * Initialize an empty tree
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Initialize a tree with an initial root of {@link BinarySearchTreeNode}
     *
     * @param initial_root The initial node object for the root
     */
    public BinarySearchTree(BinarySearchTreeNode<K> initial_root) {
        this.root = initial_root;
    }

    /**
     * Initialize a tree with the root set with data
     * @param data The data to set for the root of the new tree
     */
    public BinarySearchTree(K data) {
        this.root = new BinarySearchTreeNode<>(data);
    }

    /**
     * Takes in any number of {@link BinarySearchTreeNode} objects as arguments
     * and inserts them into the tree one by one.
     * @param data The data to insert
     */
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
        /*
         * If the root is null, then the tree is empty and the node we're inserting
         * should be the root node. Set the root to the node and return the new root.
         */
        if (root == null) {
            root = node;
            return root;
        }
        /*
         * Otherwise do a comparison on their string values (ignoring case).
         * If the comparison is 0, then they are equal and the internal reference count
         * for the root should be increased by 1. Otherwise if the comparison is less
         * than 0, then the node should be inserted as a left-hand child of the root.
         * Finally if the comparison is greater than 0 the node should be inserted as a
         * right-hand child of the root.
         */
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
     * All comparisons are in lowercase. Works like {@link #searchAll(Comparable)}
     * except it returns the first match instead of all matches.
     *
     * @param key The key containing the value to search for
     * @return The node if it finds it
     * @throws EmptyTreeException    If the current tree is empty
     * @throws NodeNotFoundException If the node isn't found
     * @see #searchAll(Comparable)
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
        /*
         * Null check for the root. If this is true, then
         * the programmer needs to know that they're operating
         * on an empty data set.
         */
        if (this.root == null) {
            throw new EmptyTreeException();
        }

        /*
         * Create a new LinkedList Queue (using Java's implementation
         * because I'm lazy and don't really trust my code right now) to
         * add the children of the current level's nodes. Starts from the
         * left of the current level, goes to the right. At each node, it adds
         * (in this order) the left child (if it exists) and the right child
         * (if it exists) to the queue. If the current node contains the key
         * we have or starts with the key, then it is added to the result set.
         * Otherwise it is skipped, its children are added to the queue,
         * and the cycle continues until the queue is empty.
         */
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
     * Displays the contents of the current tree in a Breadth-First Search pattern.
     * If the tree is empty (root is null), then a message will print out saying so
     * and the method will return. Otherwise, internally, a Queue is made
     * of all children of the current level, where they are then iterated over
     * (and their children are added to the queue and so on), printing out the
     * data they hold.
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
     * @param key  The key to identify the node to remove
     * @param root The root of the subtree to do the removing from
     * @return The new root of the tree
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

    /**
     * Checks if the root is null, which means the tree is empty
     *
     * @return Is the root null?
     */
    public boolean isEmpty() {
        return this.root == null;
    }
}
