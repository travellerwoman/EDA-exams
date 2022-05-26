/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material.tree;

import material.Position;

/**
 *
 * @author jvergara
 */
public class Prueba {
    public static void Main (String args[]) {
        GenericTree<Integer> tree = new LinkedTree<> ();
        
        Position<Integer> raiz = tree.addRoot(25);
        
        Position<Integer> h1 =tree.add (30, raiz);
        if (tree.isLeaf(h1)) {
            System.out.println ("Es hoja");
        }else {
            System.out.println ("NO es hoja");
        }
        
        
        Position<Integer> h2 =tree.add (40, raiz);
        Position<Integer> h3 =tree.add (50, raiz);
        
        
        tree.add (100, h2);
    }
}
