package uabc.ic.benjaminbolanos.practica7;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author benjabolanos
 */
public class ArchivoDeTexto {

    private File archivoOriginal;
    private final StringBuilder contenidoArchivo;

    private final ArrayList<String> palabras;
    private final ArrayList<Integer> inicioPalabras;
    private final ArrayList<Integer> finPalabras;

    private final Collator collator;

    public ArchivoDeTexto() {
        contenidoArchivo = new StringBuilder();
        palabras = new ArrayList<>();
        inicioPalabras = new ArrayList<>();
        finPalabras = new ArrayList<>();
        collator = Collator.getInstance(new Locale("es"));
        collator.setStrength(Collator.TERTIARY);
    }

    /**
     * Abre un JFileChooser y en caso de haber seleccionado un archivo, lo
     * guarda en archivoOriginal
     * @param component Componente padre del JFileChooser
     * @return Retorna si se seleccionó un archivo.
     */
    public boolean seleccionarArchivo(Component component) {
        //Se crea un file chooser que solo acepte archivos .txt
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de texto(*.txt)", "txt");
        fileChooser.setAcceptAllFileFilterUsed(Boolean.FALSE);
        fileChooser.setFileFilter(filter);

        int seleccion = fileChooser.showOpenDialog(component);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            archivoOriginal = fileChooser.getSelectedFile();
            return true;
        }

        return false;
    }

    /**
     * Lee el contenido del archivo y lo guarda como un StringBuilder.
     */
    public void leerArchivo() {
        try ( BufferedReader lector = new BufferedReader(new FileReader(archivoOriginal, Charset.forName("UTF-8")))) {

            for (int i = 0; i < 30; i++) {
                contenidoArchivo.append(lector.readLine()).append("\n");
            }

            //Actualiza el contenido
            actualizarContenido();

        } catch (IOException ex) {
            Logger.getLogger(ArchivoDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Actualiza el contenido encontrado en el archivo. Usa Pattern y Matcher
     * para encontrar todas las palabras en el StringBuilder del contenido del 
     * archivo. Reinicia las palabras encontradas y sus indices de inicio y fin.
     * 
     */
    private void actualizarContenido() {
        Pattern pattern = Pattern.compile("[A-zÁ-ú]+");
        Matcher matcher = pattern.matcher(contenidoArchivo);
        
        inicioPalabras.clear();
        palabras.clear();
        finPalabras.clear();

        //Por cada coincidencia, toma esa palabra, su indice de inicio y final
        while (matcher.find()) {
            inicioPalabras.add(matcher.start());
            palabras.add(matcher.group());
            finPalabras.add(matcher.end());
        }
    }

    /**
     * Actualiza el StringBuilder, por cada palabraAnterior encontrada, en su lugar
     * pone la palabraNueva
     * @param palabraAnterior Palabra a sustituir
     * @param palabraNueva Palabra por la que se sustituirá
     */
    public void sustituir(String palabraAnterior, String palabraNueva) {
        //Recorre todas las palabras del archivo
        for (int i = 0; i < palabras.size(); i++) {
            //Si la palabra coincide con la palabra a sustituir
            if (collator.equals(palabras.get(i), palabraAnterior)) {
                //Hace el cambio y actualiza el contenido
                contenidoArchivo.replace(inicioPalabras.get(i), finPalabras.get(i), palabraNueva);
                actualizarContenido();
            }
        }
    }
    
    /**
     * Toma el nombre del archivoOriginal y crea uno nuevo con el extra de "_corregido".
     * Escribe en el nuevo archivo el contenido del archivo.
     */
    public void guardarArchivo(){
        String nombreArchivo = archivoOriginal.getName().replaceFirst("[.][^.]+$","");
        File archivoNuevo = new File("src/main/resources/archivos/"+nombreArchivo + "_corregido.txt");
        
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoNuevo, Charset.forName("UTF-8")))){
            escritor.append(contenidoArchivo);
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retorna la palabra en el indice indicada
     * @param indice Indice de la palabra buscada
     * @return String en el indice indicado del ArrayList de palabras
     */
    public String palabraEn(int indice) {
        return palabras.get(indice);
    }
    
    public StringBuilder getContenidoArchivo() {
        return contenidoArchivo;
    }
    
    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public ArrayList<Integer> getInicioPalabras() {
        return inicioPalabras;
    }

    public ArrayList<Integer> getFinPalabras() {
        return finPalabras;
    }
}
