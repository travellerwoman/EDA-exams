package examen2020;


import material.graphs.Vertex;

import java.util.LinkedList;

/**
 *
 * @author mayte
 */
public class Line {
    private int numeroLinea;
    private LinkedList<Vertex<String>> listaEstaciones;

    public Line (int numero) {
        this.numeroLinea = numero;
        listaEstaciones = new LinkedList<>();
    }

    public void addStation (Vertex<String> station) {
        listaEstaciones.add(station);
    }

    public Vertex<String> getLast () {
        if (listaEstaciones.isEmpty()) {
            return null;
        }else {
            return listaEstaciones.getLast();
        }
    }
    /**
     * Return all the names of the stations in the line.
     * @return A string with the sequence of all stations in the line. 
     */
    @Override
    public String toString(){
        String toReturn="";
        //[Cuatro Caminos, San Bernardo, Opera, Sol, Principe de Vergara, Goya, Manuel Becerra, Ventas]
        toReturn="[";
        for (int i=0;i<listaEstaciones.size()-1;i++) {
            toReturn=toReturn+listaEstaciones.get (i).getElement()+", ";
        }
        toReturn=toReturn+listaEstaciones.get (listaEstaciones.size()-1).getElement();
        toReturn=toReturn+"]";
        return toReturn;
    }
   
    
}
