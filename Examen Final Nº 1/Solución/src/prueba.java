import java.util.HashMap;
import java.util.Map;

public class prueba {
    public static void main (String args[]) {
        Persona p = new Persona();
        p.edad=20;
        p.dni="4857372";

        Map<String, Persona> mapa = new HashMap<>();
        mapa.put (p.dni, p);

        /////////////
        Persona persona = mapa.get ("4857372");
        //System.out.println(persona.dni+" "+persona.edad);
        persona.edad=21;


       // System.out.println(p.edad);

        /////////
        Map<Integer, Integer> mapa2 = new HashMap<>();
        mapa2.put (20,20);
        System.out.println(mapa2.size());
        mapa2.put (20,40);
        System.out.println(mapa2.size());
    }
}
