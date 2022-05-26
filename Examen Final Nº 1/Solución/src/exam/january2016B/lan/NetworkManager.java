package exam.january2016B.lan;

import material.graphs.*;

import java.util.*;


/**
 *
 * @author jvelez
 */
public class NetworkManager {
     private Graph<Host, Integer> red;
     private Map<Host, Vertex<Host>> agendaVertex;
     private Set<Host> routers;
     public NetworkManager () {
         red = new ELGraph<>();
         agendaVertex = new HashMap<>();
         routers = new HashSet<> ();
     }
    /**
     * Adds a terminal to the network.
     *
     * @param terminal The terminal to be added.
     * @param router The router in which the terminal is added
     * @param bps The bits per second of the connection between the router and
     * the terminal.
     */
    public void addTerminal(Terminal terminal, Router router, int bps) {
        Vertex<Host> nodo = red.insertVertex(terminal);
        agendaVertex.put (terminal, nodo);
        red.insertEdge(nodo, agendaVertex.get (router), bps);
    }

    /**
     * Adds a router to the network
     *
     * @param router The router to be added.
     * @param routerList The router list to which ths router is connected
     * @param bps The bps for each router in routerList
     */
    public void addRouter(Router router, List<Router> routerList, List<Integer> bps) {
        Vertex<Host> v = red.insertVertex(router);
        agendaVertex.put (router, v);
        routers.add (router);
        if (routerList!=null) {
            for (int i = 0; i < routerList.size(); i++) {
                Router routerDestino = routerList.get(i);
                int velocidad = bps.get(i);
                //Unimos el Vertice v con el nuevo router
                //Buscamos el routerDestino en el grafo para ver en que Vertex esta metido
                Vertex<Host> verticeDestino = agendaVertex.get(routerDestino);
                red.insertEdge(v, verticeDestino, velocidad);

            }
        }
    }

    /**
     * @param r
     * @return the routers connected to the router r
     */
    public List<Router> getRouters(Router r) {
        ArrayList<Router> toReturn = new ArrayList<>();

        Vertex<Host> v1 = agendaVertex.get (r);
        for (Vertex<Host> v2: red.vertices()) {
            if ((red.areAdjacent(v1, v2)!=null) && routers.contains(v2.getElement())){
                toReturn.add ((Router) v2.getElement());
            }
        }
        return toReturn;
    }

    /**
     * @param t
     * @return the router connected to the terminal t
     */
    public Router getRouter(Terminal t) {
        Vertex<Host> v1 = agendaVertex.get (t);
        Edge<Integer> arco = red.incidentEdges(v1).iterator().next();
        Vertex<Host> routerVertex =  red.opposite(v1,arco);
        return (Router) routerVertex.getElement();
    }

    /**
     *
     * @param t1
     * @param t2
     * @return Return the number of jumps between t1 and t2
     */
    public int findHops(Terminal t1, Terminal t2) {
        BreadthSearch<Host, Integer> utilidades = new BreadthSearch<>();
        //public List<Edge<E>> getPath(Graph<V, E> graph, Vertex<V> startVertex, Vertex<V> endVertex)
        List<Edge<Integer>> recorrido = utilidades.getPath(red, agendaVertex.get(t1), agendaVertex.get(t2));
        return recorrido.size();
    }
}
