package examen2020;

import material.graphs.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author mayte
 */
public class Metro {

    private int numeroLineas;
    private Map<Integer, Line> lineas;
    private Map<String, Vertex<String>> agenda;
    private Graph<String, Integer> grafo;

    public Metro () {
        numeroLineas=0;
        lineas = new HashMap<>();
        agenda = new HashMap<>();
        grafo = new ELGraph<>();
    }
    /**
    * Return the number of lines in the Metro.
    * @return the number of lines. 
    */
    public int numberOfLines(){
        return this.numeroLineas;
    }
    
    /**
    * Get one line.
    * @param lineNumber The identifier of the line.
    * @return the Line which is corresponding with the identifier. 
    */
    public Line getLine(int lineNumber){
        return lineas.get (lineNumber);
    }
    
    /**
    * Add a new line to the Metro.
    * @return the identifier of the new line. 
    */
    public int addLine(){
        this.numeroLineas++;
        Line line = new Line(this.numeroLineas);
        this.lineas.put (this.numeroLineas, line);
        return this.numeroLineas;
    }
    
    /**
    * Add a new station to the line. Is possible that 
    * one station belongs to two lines or more.
    * @param lineNUmber The identifier of the line.
    * @param stationName The name of the station.
    */
    //Añade una nueva estacion a una línea de metro
    public void addStationToLine(int lineNUmber, String stationName){
        Line linea = lineas.get (lineNUmber);
        //1- Comprobamos si esa estacion ya esta en el grafo por pertenecer a otra linea previamente
        Vertex<String> nodo = this.agenda.get (stationName);
        if (nodo==null) { //Esa estacion es nueva, así es que tenemos que crear el nodo en el grafo
            Vertex<String> nuevoNodo = grafo.insertVertex(stationName);
            //Ahora, preguntamos a la linea a ver si tiene alguna estacion ya.
            Vertex<String> ultimaEstacion = linea.getLast();
            if (ultimaEstacion==null) { //Esa linea es nueva, es la primera estacion de la linea
                linea.addStation(nuevoNodo);
            }else { //Esa linea tiene estaciones y lo que hacemos en ese caso es unir en el grafo su ultima estacion con la nueva y meter a la linea la nueva estacion
                linea.addStation(nuevoNodo);
                grafo.insertEdge(ultimaEstacion, nuevoNodo,0);
            }
            //Actualizamos la agenda
            agenda.put (stationName, nuevoNodo);
        }else { //Esa estacion ya existe, pertenece a otra linea ya. No hay que insertar esa estacion en el grafo porque ya esta
            //Ahora, preguntamos a la linea a ver si tiene alguna estacion ya.
            Vertex<String> ultimaEstacion = linea.getLast();
            if (ultimaEstacion==null) { //Esa linea es nueva, es la primera estacion de la linea
                linea.addStation(nodo);
            }else { //Esa linea tiene estaciones y lo que hacemos en ese caso es unir en el grafo su ultima estacion con la nueva y meter a la linea la nueva estacion
                linea.addStation(nodo);
                grafo.insertEdge(ultimaEstacion, nodo,0);
            }
        }
    }
    
    /**
    * Get the minimum path between two stations.
    * @param startStationName The name of station at the beginning of the way.
    * @param endStationName The name of the final station.
    * @return A list with the sequence of the stations between the first and
    * the last station. 
    */
    public List<String> pathBetweenStations(String startStationName, String endStationName){
        BreadthSearch<String, Integer> utilidades = new BreadthSearch<>();
        Vertex<String> nodoOrigen = agenda.get (startStationName);
        Vertex<String> nodoDestino = agenda.get (endStationName);
        List<Edge<Integer>> listaArcos = utilidades.getPath(grafo, nodoOrigen, nodoDestino);
        List<String> toReturn = new LinkedList<>();
        toReturn.add(startStationName);
        for (Edge<Integer> arco: listaArcos){
            Vertex<String> nodo1 = grafo.endVertices(arco).get(0);
            Vertex<String> nodo2 = grafo.endVertices(arco).get(1);
            if (toReturn.contains(nodo1.getElement())) {
                toReturn.add(nodo2.getElement());
            }else {
                toReturn.add(nodo1.getElement());
            }
        }
        return toReturn;
    }
}
