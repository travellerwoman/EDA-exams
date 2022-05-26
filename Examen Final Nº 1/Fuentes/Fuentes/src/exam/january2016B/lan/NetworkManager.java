package exam.january2016B.lan;

import material.Pair;
import material.graphs.*;

import java.util.*;

/**
 * _______________________________________________________________ 1.5pt
 * 1. Definir las propiedades privadas
 *    método addRouter - router
 *                    |- lista routers a los que se conecta
 *                    |- anchos de banda
 *
 *    método getRouters - router
 *                     |-> lista routers conectados
 *
 *_______________________________________________________________ 1pt
 * 2. método addTerminal - terminal
 *                      |- router
 *                      |- ancho de banda
 *
 *    método getRouter - terminal
 *                    |-> router conectado
 *
 *_______________________________________________________________ 1pt
 * 3. método findHops
 *
 * _______________________________________________________________
 *  Dudas:
 *  1. Se pueden modificar las clases Router, Host y Terminal?
 *
 *
 * */

/**
 *
 * @author maria
 */
public class NetworkManager {

    Graph<Router, Integer> routersGraph;
    Map<Router, List<Terminal>> routerTerminalsMap;
    Map<Terminal, Pair<Router, Integer>> terminalRouterMap;
    Map<Router, Vertex<Router>> routerDictionary;

    public NetworkManager(){
        routersGraph = new ELGraph<>();
        routerTerminalsMap = new HashMap<>();
        terminalRouterMap = new HashMap<>();
        routerDictionary = new HashMap<>();
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
        terminalRouterMap.put(terminal, new Pair<>(router, bps));
        List<Terminal> list = routerTerminalsMap.get(router);
        list.add(terminal);
    }

    /**
     * Adds a router to the network
     *
     * @param router The router to be added.
     * @param routerList The router list to which this router is connected
     * @param bps The bps for each router in routerList
     */
    public void addRouter(Router router, List<Router> routerList, List<Integer> bps) {
        // Si no contiene el router se añade, sino se ignora porque el enunciado no dice nada
        if (!routerDictionary.containsKey(router)){
            Vertex<Router> vertex = routersGraph.insertVertex(router);

            // Añadir el nuevo vertice a todas las estructuras
            routerDictionary.put(router, vertex);
            routerTerminalsMap.put(router, new LinkedList<>());

            if(routerList != null && bps!= null && routerList.size() == bps.size()) {
                int i = 0;
                for (Router routerAux : routerList) {
                    // si el router que intento añadir no existe,
                    // lo ignora porque el enunciado no dice nada
                    if (routerDictionary.containsKey(routerAux)) {
                        Vertex<Router> verterAux = routerDictionary.get(routerAux);
                        routersGraph.insertEdge(vertex, verterAux, bps.get(i));
                    }
                }
            }
        }
    }

    /**
     * @param r router I want the connected routers to
     * @return the routers connected to the router r
     */
    public List<Router> getRouters(Router r) {
        // Si existe el router devuelvo los routers conectados
        if (routerDictionary.containsKey(r)){
            Vertex<Router> vertex = routerDictionary.get(r);

            List<Router> routerList = new LinkedList<>();
            for (Edge<Integer> edge : routersGraph.incidentEdges(vertex)){
                Vertex<Router> oppositeVertex = routersGraph.opposite(vertex, edge);
                routerList.add(oppositeVertex.getElement());
            }
            // Si no tiene conexiones, devuelve la lista vacía
            return routerList;
        }
        // Si no existe r, devuelvo null
        return null;
    }

    /**
     * @param t
     * @return the router connected to the terminal t
     */
    public Router getRouter(Terminal t) {
        return terminalRouterMap.get(t).getKey();
    }

    /**
     *
     * @param t1
     * @param t2
     * @return Return the number of jumps between t1 and t2
     */
    public int findHops(Terminal t1, Terminal t2) {
        BreadthSearch<Router, Integer> utils = new BreadthSearch<>();

        if (terminalRouterMap.containsKey(t1) && terminalRouterMap.containsKey(t2)) {
            Router router1 = terminalRouterMap.get(t1).getKey();
            Router router2 = terminalRouterMap.get(t2).getKey();

            Vertex<Router> vertex1 = routerDictionary.get(router1);
            Vertex<Router> vertex2 = routerDictionary.get(router2);

            List<Edge<Integer>> collection = utils.getPath(routersGraph, vertex1, vertex2);
            int hops = 2;
            if (collection != null) {
                hops += collection.size();
            }
            return hops;
        }
        throw new RuntimeException("Terminal not found");
    }
}
