package examen.netflix;

import java.util.ArrayList;
import java.util.HashSet;

public class Movie {

    String titulo;
    int ano;
    ArrayList<String> tipo;

    public Movie() {
    }

    public Movie(String titulo, int ano, ArrayList<String> tipo) {
        if (titulo != null && ano > 1500 && tipo != null){
            this.titulo = titulo;
            this.ano = ano;
            this.tipo = tipo;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public ArrayList<String> getTipo() {
        return tipo;
    }

    public void setTipo(ArrayList<String> tipo) {
        this.tipo = tipo;
    }
}
