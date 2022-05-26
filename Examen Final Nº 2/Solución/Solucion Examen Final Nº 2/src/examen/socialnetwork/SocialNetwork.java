package examen.socialnetwork;

import material.graphs.*;

import java.util.*;

/**
 * Stores a social network and analyses the relevance of
 * its users.
 * @author J. Velez, A. Duarte, J. Sanchez-Oro,
 */
public class SocialNetwork {
    private Graph<String,Integer> redSocial;
    private Map<String, Vertex<String>> agendaUsuarios;

    public SocialNetwork() {
        redSocial = new ELGraph<>();
        agendaUsuarios = new HashMap<>();
    }

    /**
     * Adds a new user to the social network
     * @param user the name of the user to be added
     */
    public void addUser(String user) {
        Vertex<String> v = redSocial.insertVertex(user);
        agendaUsuarios.put (user, v);
    }

    /**
     * Sets a friendship relation between two user
     * @param user1 name of the first user
     * @param user2 name of the second user
     */
    public void makeFriends(String user1, String user2) {
        Vertex<String> v1 = agendaUsuarios.get (user1);
        Vertex<String> v2 = agendaUsuarios.get (user2);
        redSocial.insertEdge(v1,v2,0);
    }

    /**
     * Removes all users in the social network with a number of friends
     * lower than a given number k
     * @param k value for filtering
     * @return users removed from the social network
     */
    public Iterable<String> filter(int k) {
        boolean hemosBorradoAlgo=true;
        Set<String> nombresBorrados = new HashSet<>();

        while (hemosBorradoAlgo) {
            LinkedList<Vertex<String>> listaParaBorrar = new LinkedList<>();
            for (Vertex<String> nodo : redSocial.vertices()) {
                if (redSocial.incidentEdges(nodo).size() < k) {
                    //Apuntamos ese nodo para borrarlo despues
                    listaParaBorrar.add(nodo);
                }
            }

            if (listaParaBorrar.size()==0) {
                hemosBorradoAlgo=false;
            }
            for (Vertex<String> v : listaParaBorrar) {
                redSocial.removeVertex(v);
                nombresBorrados.add(v.getElement());
            }
        }
        return nombresBorrados;

    }

    private void copiarGrafo(Graph<String, Integer> grafoOrigen, Graph<String, Integer> grafoDestino) {
        Map<String, Vertex<String>> agendaAuxiliar = new HashMap<>();
        for (Vertex<String> v: grafoOrigen.vertices()) {
            Vertex<String> nuevo = grafoDestino.insertVertex(v.getElement());
            agendaAuxiliar.put (nuevo.getElement(), nuevo);
        }

        for (Edge<Integer> arco: grafoOrigen.edges()) {
            Vertex<String> vOrigen = grafoOrigen.endVertices(arco).get(0);
            Vertex<String> vDestino = grafoOrigen.endVertices(arco).get(1);
            grafoDestino.insertEdge(agendaAuxiliar.get (vOrigen.getElement()),agendaAuxiliar.get (vDestino.getElement()),0);
        }
    }

    /**
     * Evaluates how many groups there are in the social network
     * @return number of groups in the social network
     */
    public int groups() {
        int ngroups=0;
        BreadthSearch<String, Integer> utilidades = new BreadthSearch<>();

        Graph<String,Integer> redSocialCopia = new ELGraph<>();
        copiarGrafo (redSocial, redSocialCopia);
        while (redSocialCopia.vertices().size()>0) {
            ngroups++;
            //1- Sacar un nodo para empezar el recorrido
            Vertex<String> v = redSocialCopia.vertices().iterator().next();
            //2- Recorremos desde ese nodo
            //Set<Vertex<V>> traverse(Graph<V, E> g, Vertex<V> root)

            Set<Vertex<String>> recorrido = utilidades.traverse(redSocialCopia, v);
            //3- Borramos los nodos que hemos visitado, del grafo.
            for (Vertex<String> nodo : recorrido) {
                redSocialCopia.removeVertex(nodo);
            }
        }
        return ngroups;
    }




}

