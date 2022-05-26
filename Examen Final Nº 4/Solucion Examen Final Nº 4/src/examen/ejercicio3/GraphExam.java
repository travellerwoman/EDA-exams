/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.ejercicio3;

import java.util.*;

import material.Position;
import material.graphs.Edge;
import material.graphs.Graph;
import material.graphs.Vertex;
import material.tree.binarysearchtree.AVLTree;
import material.tree.binarysearchtree.BinarySearchTree;

/**
 *
 * @author jvergara
 */
public class GraphExam {

    public Collection<Autor> getRanking (Graph<Autor, Integer> grafo) {
        ArrayList<Autor> lista = new ArrayList<>();

        BinarySearchTree<Autor> avl = new AVLTree<>();
        for (Vertex<Autor> v: grafo.vertices()) {
            avl.insert(v.getElement());
        }
        for (Position<Autor> p: avl) {
            lista.add (p.getElement());
        }
        return lista;
        
    }
    
    public Collection<Autor> getDistanciaK (Graph<Autor, Integer> grafo, Vertex<Autor> autor, int distancia) {
        LinkedList<Autor> resultado = new LinkedList<>();

        Queue<Vertex<Autor>> colaNodos = new ArrayDeque<>();
        Queue<Integer> colaDistancia = new ArrayDeque<>();
        HashSet<Vertex<Autor>> visitados = new HashSet<>();

        colaNodos.add(autor);
        colaDistancia.add (0);

        while (!colaNodos.isEmpty()) {
            Vertex<Autor> nodo = colaNodos.remove();
            Integer distan = colaDistancia.remove();
            if (!visitados.contains(nodo)) {
                visitados.add(nodo);
                //imprimir el nodo
                if (distan==distancia) {
                    resultado.add(nodo.getElement());
                }else if (distan<distancia) {
                    for (Edge<Integer> arco : grafo.incidentEdges(nodo)) {
                        Vertex<Autor> adyacente = grafo.opposite(nodo, arco);
                        if (!visitados.contains(adyacente)) {
                            colaNodos.add(adyacente);
                            colaDistancia.add(distan + 1);
                        }
                    }
                }
            }
        }

        return resultado;
    }
}

















