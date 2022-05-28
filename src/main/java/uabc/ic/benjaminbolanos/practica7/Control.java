package uabc.ic.benjaminbolanos.practica7;

import java.awt.Component;

/**
 *
 * @author benjabolanos
 */
public class Control {
    private final Diccionario diccionario;
    private final ArchivoDeTexto archivoDeTexto;
    
    private int indiceActual;
    
    private String palabraParaCorregir;
    private int inicioPalabraParaCorregir;
    private int finPalabraParaCorregir;
    
    private int algoritmoActual;
    
    //Estadisticas
    private int palabrasErroneas;
    private int palabrasOmitidas;
    private int palabrasAgregadas;
    
    public Control(){
        diccionario = new Diccionario();
        archivoDeTexto = new ArchivoDeTexto();
        indiceActual = 0;
        algoritmoActual = Algoritmos.BUSQUEDA_BINARIA;
    }
    
    /**
     * Selecciona un archivo con JFileChooser y lo lee. Indica si se seleccionó
     * un archivo.
     * @param component Componente padre para el JFileChooser
     * @return True si se seleccionó un archivo.
     */
    public boolean seleccionarArchivo(Component component){
        if(archivoDeTexto.seleccionarArchivo(component)){
            archivoDeTexto.leerArchivo();
            return true;
        }
        return false;
    }
    
    /**
     * Busca cada palabra a partir del indiceActual, si no encuentra alguna
     * guarda los valores de la palabra no encontrada y termina la funcion. Retorna
     * si encuentra una palabra para corregir.
     * @return True si no encuentra una palabra en el diccionario
     */
    public boolean hayPalabraParaCorregir(){
        
        while(indiceActual < archivoDeTexto.getPalabras().size()){
            palabraParaCorregir = archivoDeTexto.palabraEn(indiceActual);
            if(!diccionario.buscarPalabra(palabraParaCorregir, algoritmoActual)){
                inicioPalabraParaCorregir = archivoDeTexto.getInicioPalabras().get(indiceActual);
                finPalabraParaCorregir = archivoDeTexto.getFinPalabras().get(indiceActual);
                palabrasErroneas++;
                return true;
            }
            indiceActual++;
        }
        return false;
    }
    
    /**
     * Agrega una nueva palabra al diccionario y los ordena. También, aumenta
     * el contador de palabras agregadas.
     * @param nuevaPalabra 
     */
    public void agregarPalabra(String nuevaPalabra){
        diccionario.agregarPalabra(nuevaPalabra);
        diccionario.ordenarDiccionario();
        palabrasAgregadas++;
    }
    
    /**
     * Agrega la palabra actual a las palabras omitidas. Aumenta el contador
     * de palabras omitidas.
     */
    public void omitirPalabraActual(){
        diccionario.agregarPalabraOmitida(palabraParaCorregir);
        palabrasOmitidas++;
    }
    
    /**
     * Omite la palabra actual al solo aumentar el indice de las palabras.
     * Aumenta el contador de palabras omitidas.
     */
    public void omitirPorAhora(){
        indiceActual++;
        palabrasOmitidas++;
    }
    
    /**
     * Sustituye la palabra actual con la nueva palabra ingresada.
     * @param nuevaPalabra Palabra por la cual sustituir la palabra actual.
     */
    public void sustituir(String nuevaPalabra){
        archivoDeTexto.sustituir(palabraParaCorregir, nuevaPalabra);
        if(diccionario.buscarPalabra(nuevaPalabra, algoritmoActual)){
            agregarPalabra(nuevaPalabra);
        }
        indiceActual++; 
    }
    
    /**
     * Termina la revisión reiniciando el indice actual, guardando el archivo
     * corregido y guardando el diccionario.
     */
    public void terminarRevision(){
        indiceActual = 0;
        archivoDeTexto.guardarArchivo();
        diccionario.guardarDiccionario();
    }
    
    /**
     * Reinicia la revision al crear los diccionario y reiniciar contadores
     * de palabras erroneas, omitidas y agregadas.
     */
    public void reiniciarRevision(){
        diccionario.crearDiccionarios();
        palabrasErroneas = 0;
        palabrasOmitidas = 0;
        palabrasAgregadas = 0;
    }

    public String getPalabraParaCorregir() {
        return palabraParaCorregir;
    }

    public int getInicioPalabraParaCorregir() {
        return inicioPalabraParaCorregir;
    }

    public int getFinPalabraParaCorregir() {
        return finPalabraParaCorregir;
    }
    
    public String getContenidoTexto(){
        return archivoDeTexto.getContenidoArchivo().toString();
    }

    public void setAlgoritmoActual(int algoritmoActual) {
        this.algoritmoActual = algoritmoActual;
    }

    /**
     * Retorna un String con las estadisticas de las palabras erroneas, omitidas
     * y agregadas.
     * @return String de las estadisticas
     */
    public String estadisticasToString(){
        return "Palabras Erroneas: " + palabrasErroneas +
                "\nPalabras Omitidas: " + palabrasOmitidas +
                "\nPalabras Agregadas a Diccionario: " + palabrasAgregadas;
    }
}
