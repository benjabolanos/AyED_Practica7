package uabc.ic.benjaminbolanos.practica7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benjabolanos
 */
public class Diccionario {
    private final ArrayList<String> diccionarioNormal;
    private final ArrayList<ArrayList<String>> diccionarioHash;
    private final ArrayList<String> palabrasOmitidas;
    
    private final Collator collator;
    
    public Diccionario(){
        diccionarioNormal = new ArrayList<>();
        diccionarioHash = new ArrayList<>();
        palabrasOmitidas = new ArrayList<>();
        collator = Collator.getInstance(new Locale("es"));
        collator.setStrength(Collator.TERTIARY);
    }
    
    /**
     * Toma el archivo donde se guarda el diccionario y crea los diccionarios
     * (Normal y Hash) con las palabras del archivo. Despues de agregar todas las
     * palabras, ordena el diccionario
     */
    public void crearDiccionarios(){
        //Archivo donde se guardan las palabras del diccionario
        File archivo = new File("src/main/resources/archivos/listado-general.txt");
        
        //Lector con Charset UTF-8 para que tambien pueda leer palabras con acentos
        try(BufferedReader lector = new BufferedReader(new FileReader(archivo, Charset.forName("UTF-8")))){
            
            //Cada linea es una palabra, asi que se agrega cada linea como una palabra
            String palabra;
            while((palabra = lector.readLine()) != null){
                agregarPalabra(palabra);
            }
            
            //Ordena el diccionario
            ordenarDiccionario();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Diccionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Diccionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Toma el diccionario normal y guarda cada palabra dentro del archivo de diccionario
     */
    public void actualizarArchivo(){
        File archivo = new File("src/main/resources/archivos/listado-general.txt");
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo,Charset.forName("UTF-8")))){
            
            for(String palabra: diccionarioNormal){
                escritor.append(palabra).append("\n");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Diccionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    /**
     * Método para agregar una palabra a los diccionarios Normal y de Hash
     * @param palabra Palabra que se agregará a los diccionarios
     */
    public void agregarPalabra(String palabra){
        diccionarioNormal.add(palabra);
        
        //Calcular valor hash de palabra, hacerlo positivo y sacar modulo con 10000
        int hashValue = Math.abs(palabra.hashCode()) % 10000;
        
        //Agrandar el diccionario hash en caso de que el hashvalue sea mayor
        if(diccionarioHash.size() < hashValue){
            diccionarioHash.ensureCapacity(hashValue+1);
            
            //Se crean más cubetas para cubrir el espacio
            for(int i = diccionarioHash.size(); i <= hashValue; i++){
                diccionarioHash.add(new ArrayList<>());
            }
        }
        
        //Se añade la palabra en la cubeta del indice de su hash
        diccionarioHash.get(hashValue).add(palabra);
    }
    
    /**
     * Ordena el diccionario normal con el metodo de Collections, utilizando
     * un Collator
     */
    public void ordenarDiccionario(){
        Collections.sort(diccionarioNormal, collator);
    }
    
    /**
     * Agrega una palabra al ArrayList de palabras omitidas y ordena la lista.
     * @param palabraOmitida Palabra a agregar
     */
    public void agregarPalabraOmitida(String palabraOmitida){
        palabrasOmitidas.add(palabraOmitida);
        Collections.sort(palabrasOmitidas,collator);
    }
    
    /**
     * Guarda la información del nuevo diccionario y reinicia los ArrayList
     */
    public void guardarDiccionario(){
        actualizarArchivo();
        palabrasOmitidas.clear();
        diccionarioNormal.clear();
        diccionarioHash.clear();
    }
    
    /**
     * Busca una palabra con el algoritmo indicado.
     * @param palabra Palabra a buscar
     * @param algoritmo Algoritmo a utilizar
     * @return Retorna true si encuentra la palabra
     */
    public boolean buscarPalabra(String palabra, int algoritmo){
        
        return switch (algoritmo) {
            case Algoritmos.BUSQUEDA_BINARIA ->
                Algoritmos.busquedaBinaria(diccionarioNormal, palabra, collator) != -1 ||
                Algoritmos.busquedaBinaria(palabrasOmitidas,palabra, collator) != -1;
            
                
            case Algoritmos.BUSQUEDA_HASH ->
                Algoritmos.busquedaHash(diccionarioHash, palabra, collator) != -1 ||
                Algoritmos.busquedaBinaria(palabrasOmitidas, palabra, collator) != -1;
            
            default -> false;
        };
    }
}
