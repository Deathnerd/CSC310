package com.gilleland.george.utils;

/**
 * Created by Wes Gilleland on 11/4/2015.
 */
public class BinarySearchTree<K> {
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

    /*@SafeVarargs
    public final void insert(K... data){
        for(K d : data){
            this.insert(this.root, d);
        }
    }*/

    /*public BinarySearchTreeNode<K> insert(BinarySearchTreeNode root, K data){
        if(root == null){
            return new BinarySearchTreeNode<K>(data);
        } else if (root.left.compareTo(data)){
            return this.insert(root.left, data);
        }
        return this.insert(root.right, data);
    }*/
}
