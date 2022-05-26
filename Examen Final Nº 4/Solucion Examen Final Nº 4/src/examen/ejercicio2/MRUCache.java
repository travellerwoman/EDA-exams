/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.ejercicio2;

import java.io.File;
import java.util.ArrayList;
import material.maps.Entry;
import material.maps.HashTableMapLP;

/**
 *
 * @author jvergara
 */
public class MRUCache {

    public MRUCache(int max) {

    }

    public String getFile(String fname) {
        return "";
    }

    protected MyFile readFileFromDisk(String fname) {
        return new MyFile ("");
    }

    public void printMRU() {

    }

    public static void main(String args[]) {
       // Number of entries in MRU cache is set to 10 
       MRUCache h1=new MRUCache(10);
        for (int i = 1; i <= 20; i++) {
            // files are stored in a subdirectory called data
            h1.getFile("data" + File.separatorChar + i);
        }
        h1.printMRU();
    }
}
