package smartpalacemap;

import material.graphs.*;

import java.util.*;

/**
 *
 * @author Fill your name here.
 */
public class SmartPalaceMap {
    private Graph<Room, Integer> mapaMuseo = new ELGraph<>();
    private Map<Room, Vertex<Room>> mapaVertex = new HashMap<>();
    /**
    * @param connectedRooms Rooms connected to the new created room.
    *                      It can be null if there aren't any connected room.
    * @return The new created room.
    */
    public Room insertRoom(List<Room> connectedRooms) {
        Room r = new Room();
        //insertamos la habitación como nodo en el grafo
        Vertex<Room> nodo = mapaMuseo.insertVertex(r);
        //Añadimos una referencia al nodo que guarda la habitación en el grafo
        mapaVertex.put (r, nodo);
        if (connectedRooms!=null) {
            //Creamos adyacencias
            for (Room vecina : connectedRooms) {
                mapaMuseo.insertEdge(nodo, mapaVertex.get(vecina), 0);
            }
        }
        return r;
    }
    
    /**
    * @param  room1 Initial room of the path.
    * @param  room2 Final room of the path.
    * @return the ordered list of rooms. 
    *         The first room will be room1 and the last one will be room2. 
    *         If no path is found it will return null.
    */
    public List<Room> getPath(Room room1, Room room2) {
        BreadthSearch<Room, Integer> utilidades = new BreadthSearch<>();
        //public List<Edge<E>> getPath(Graph<V, E> graph, Vertex<V> startVertex, Vertex<V> endVertex)
        List<Edge<Integer>> recorrido = utilidades.getPath(mapaMuseo, mapaVertex.get(room1), mapaVertex.get(room2));
        List<Room> toReturn = new ArrayList<>(recorrido.size()+1);
        toReturn.add (room1);
        Set<Room> conjunto = new HashSet<>();
        conjunto.add (room1);

        for (Edge<Integer> arco: recorrido) {
            Vertex<Room> v1= mapaMuseo.endVertices(arco).get (0);
            Vertex<Room> v2= mapaMuseo.endVertices(arco).get (1);
            if (conjunto.contains(v1.getElement())) {
                toReturn.add(v2.getElement());
                conjunto.add(v2.getElement());
            }else {
                toReturn.add(v1.getElement());
                conjunto.add(v1.getElement());
            }
        }
        return toReturn;
    }
}
