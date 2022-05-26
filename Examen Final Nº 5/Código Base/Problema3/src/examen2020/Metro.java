package examen2020;

import java.util.List;



/**
 *
 * @author mayte
 */
public class Metro {
    
    
    /**
    * Return the number of lines in the Metro.
    * @return the number of lines. 
    */
    public int numberOfLines(){
        throw new RuntimeException("Not implemented yet."); 
    }
    
    /**
    * Get one line.
    * @param i The identifier of the line.
    * @return the Line which is corresponding with the identifier. 
    */
    public Line getLine(int lineNumber){
        throw new RuntimeException("Not implemented yet."); 
    }
    
    /**
    * Add a new line to the Metro.
    * @return the identifier of the new line. 
    */
    public int addLine(){
        throw new RuntimeException("Not implemented yet."); 
    }
    
    /**
    * Add a new station to the line. Is possible that 
    * one station belongs to two lines or more.
    * @param l The identifier of the line.
    * @param station The name of the station.  
    */
    //Añade una nueva estacion a una línea de metro
    public void addStationToLine(int lineNUmber, String stationName){
        throw new RuntimeException("Not implemented yet."); 
    }
    
    /**
    * Get the minimum path between two stations.
    * @param startStationName The name of station at the beginning of the way.
    * @param endStationName The name of the final station.
    * @return A list with the sequence of the stations between the first and
    * the last station. 
    */
    public List<String> pathBetweenStations(String startStationName, String endStationName){
        throw new RuntimeException("Not implemented yet."); 
    }
}
