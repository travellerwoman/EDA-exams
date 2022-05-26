/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material.tree;

import material.Position;

/**
 *
 * @author jvelez
 * @param <E> Elements stored in the tree nodes
 */
public interface GenericTree<E> extends Tree<E> {

    /**
     * Adds a root node to an empty tree
     * @param e
     * @return 
     */
    @Override
    public Position<E> addRoot(E e);

    /**
     * Add a new node whose parent is pointed by a given position.
     *
     * @param element
     * @param p The position of the parent, e the element stored in the new
     * created node.
     * @return 
     */
    public Position<E> add(E element, Position<E> p);

    /**
     * Add a new node whose parent is pointed by a given position, and set the
     * child at the position n if possible.
     *
     * @param element
     * @param p The position of the parent, e the element stored in the new
     * created node.
     * @param n
     * @return
     */
    public Position<E> add(E element, Position<E> p, final int n);

    /**
     * Swap the elements at two nodes
     *
     * @param p1
     * @param p2
     */
    public void swapElements(Position<E> p1, Position<E> p2);

    /**
     * Replaces the element at a node.
     *
     * @param p
     * @param e
     * @return
     */
    public E replace(Position<E> p, E e);

    /**
     * Remove a node and its corresponding subtree rooted at node.
     *
     * @param p The position of the node to be removed.
     */
    public void remove(Position<E> p);
}
