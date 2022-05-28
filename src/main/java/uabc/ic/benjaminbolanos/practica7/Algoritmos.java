package uabc.ic.benjaminbolanos.practica7;

import java.text.Collator;
import java.util.ArrayList;

/**
 *
 * @author benjabolanos
 */
public class Algoritmos {
    
    //Valores para saber que algoritmo utilizar
    public static final int BUSQUEDA_BINARIA = 0;
    public static final int BUSQUEDA_HASH = 1;

    /**
     * Algoritmo de Busqueda Binaria, recibe el ArrayList en el cual buscar el
     * Elemento indicado. También recibe un Collator para saber como comparar
     * las palabras.
     * @param a ArrayList de String con los elementos en la cual buscar
     * @param elemento Elemento (String) que se buscará
     * @param c Collator para comparar Strings
     * @return Retorna posicion del elemento dentro del ArrayList
     */
    public static int busquedaBinaria(ArrayList<String> a, String elemento, Collator c){
        if(a.isEmpty()) return -1;
        
        int ini = 0, fin = a.size()-1, med;
        
        while(ini <= fin){
            med = (fin+ini)/2;
            if(c.equals(a.get(med), elemento)){
                return med;
            } else if(c.compare(a.get(med), elemento) < 0){
                ini = med+1;
            } else {
                fin = med-1;
            }
        }
        
        return -1;
    }
    
    /**
     * Funcion hash para buscar dentro del ArrayList de ArrayList de String.
     * @param a ArrayList de String con los elementos en la cual buscar
     * @param elemento Elemento (String) que se buscará
     * @param c Collator para comparar Strings
     * @return Retorna posicion del elemento dentro del ArrayList
     */
    public static int busquedaHash(ArrayList<ArrayList<String>> a, String elemento, Collator c){
        int hashValue = Math.abs(elemento.hashCode()) % 10000;
        int index = 0;
        
        for(String str: a.get(hashValue)){
            if(c.equals(str, elemento)){
                return index;
            }
            index++;
        }
        
        return -1;
    }
}
