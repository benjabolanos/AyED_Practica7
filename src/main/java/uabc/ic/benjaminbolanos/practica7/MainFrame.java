package uabc.ic.benjaminbolanos.practica7;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

/**
 *
 * @author bbola
 */
public class MainFrame extends javax.swing.JFrame {
    private final Control control;
    private final HighlightPainter painter;
    private final Highlighter highlighter;
    /**
     * Constructor que inicializa componentes y el Highlighter para sombrear
     * texto dentro de un JTextArea
     */
    public MainFrame() {
        initComponents();
        control = new Control();
        highlighter = textContenido.getHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
    }
    
    /**
     * Busca la siguiente palabra a corregir. Si encuentra una, marca la palabra
     * dentro del texto con el Highlighter. Si ya no encuentra, la revision
     * termina.
     */
    private void revisarSiguiente(){
        highlighter.removeAllHighlights();
        if(control.hayPalabraParaCorregir()){
            textCorrecion.setText(control.getPalabraParaCorregir());
            try {
                
                highlighter.addHighlight(control.getInicioPalabraParaCorregir(), 
                        control.getFinPalabraParaCorregir(), painter);
            
            } catch (BadLocationException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            terminarRevision();
        }
    }
    
    /**
     * Termina la revision y muestra el panel de RevisionTerminada. Muestra un
     * JDialog con las estadisticas de la revision.
     */
    private void terminarRevision(){
        control.terminarRevision();
        CardLayout cl = (CardLayout) panelChecker.getLayout();
        cl.show(panelChecker, "revisionterminada");
        textContenido.setText(control.getContenidoTexto());
        JOptionPane.showMessageDialog(
                this, 
                control.estadisticasToString(), 
                "Revision Terminada", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardsPanel = new javax.swing.JPanel();
        panelInicio = new javax.swing.JPanel();
        labelSelectorTitulo = new javax.swing.JLabel();
        labelSelectorDesc = new javax.swing.JLabel();
        btnSeleccionarArchivo = new javax.swing.JButton();
        panelSpellChecker = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textContenido = new javax.swing.JTextArea();
        panelChecker = new javax.swing.JPanel();
        panelRevision = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textCorrecion = new javax.swing.JTextArea();
        btnAgregarPalabra = new javax.swing.JButton();
        btnOmitir = new javax.swing.JButton();
        btnOmitirTemporal = new javax.swing.JButton();
        btnSustituir = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();
        toggleAlgoritmo = new javax.swing.JToggleButton();
        textPalabraSustituta = new javax.swing.JTextField();
        panelRevisionTerminada = new javax.swing.JPanel();
        labelRevisionTerminada = new javax.swing.JLabel();
        btnRevisarOtro = new javax.swing.JButton();
        btnRevisarDeNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Practica 7 Spell Checker");
        setLocationByPlatform(true);

        cardsPanel.setLayout(new java.awt.CardLayout());

        labelSelectorTitulo.setFont(new java.awt.Font("Roboto Medium", 1, 48)); // NOI18N
        labelSelectorTitulo.setText("Spell Checker");

        labelSelectorDesc.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        labelSelectorDesc.setText("Para iniciar seleccione un archivo:");

        btnSeleccionarArchivo.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        btnSeleccionarArchivo.setText("Seleccionar Archivo");
        btnSeleccionarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSelectorDesc)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInicioLayout.createSequentialGroup()
                        .addComponent(labelSelectorTitulo)
                        .addGap(29, 29, 29)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInicioLayout.createSequentialGroup()
                .addContainerGap(516, Short.MAX_VALUE)
                .addComponent(btnSeleccionarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(507, 507, 507))
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(labelSelectorTitulo)
                .addGap(101, 101, 101)
                .addComponent(labelSelectorDesc)
                .addGap(118, 118, 118)
                .addComponent(btnSeleccionarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );

        cardsPanel.add(panelInicio, "selector");

        textContenido.setEditable(false);
        textContenido.setBackground(new java.awt.Color(86, 91, 95));
        textContenido.setColumns(20);
        textContenido.setRows(5);
        jScrollPane1.setViewportView(textContenido);

        panelChecker.setLayout(new java.awt.CardLayout());

        textCorrecion.setEditable(false);
        textCorrecion.setBackground(new java.awt.Color(86, 91, 95));
        textCorrecion.setColumns(20);
        textCorrecion.setRows(5);
        jScrollPane2.setViewportView(textCorrecion);

        btnAgregarPalabra.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnAgregarPalabra.setText("Agregar al Diccionario");
        btnAgregarPalabra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPalabraActionPerformed(evt);
            }
        });

        btnOmitir.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnOmitir.setText("Omitir");
        btnOmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOmitirActionPerformed(evt);
            }
        });

        btnOmitirTemporal.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnOmitirTemporal.setText("Omitir por ahora");
        btnOmitirTemporal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOmitirTemporalActionPerformed(evt);
            }
        });

        btnSustituir.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnSustituir.setText("Sustituir");
        btnSustituir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSustituirActionPerformed(evt);
            }
        });

        btnTerminar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnTerminar.setText("Terminar revision");
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });

        toggleAlgoritmo.setBackground(new java.awt.Color(115, 147, 179));
        toggleAlgoritmo.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        toggleAlgoritmo.setForeground(new java.awt.Color(51, 51, 51));
        toggleAlgoritmo.setText("Busqueda Binaria");
        toggleAlgoritmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleAlgoritmoActionPerformed(evt);
            }
        });

        textPalabraSustituta.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelRevisionLayout = new javax.swing.GroupLayout(panelRevision);
        panelRevision.setLayout(panelRevisionLayout);
        panelRevisionLayout.setHorizontalGroup(
            panelRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRevisionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRevisionLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRevisionLayout.createSequentialGroup()
                        .addGap(0, 56, Short.MAX_VALUE)
                        .addComponent(textPalabraSustituta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(toggleAlgoritmo, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(btnTerminar, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(btnSustituir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOmitirTemporal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOmitir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarPalabra, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                        .addGap(205, 205, 205))))
        );
        panelRevisionLayout.setVerticalGroup(
            panelRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRevisionLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(toggleAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOmitir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOmitirTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSustituir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPalabraSustituta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        panelChecker.add(panelRevision, "revision");

        labelRevisionTerminada.setFont(new java.awt.Font("Roboto Medium", 1, 30)); // NOI18N
        labelRevisionTerminada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRevisionTerminada.setText("Has terminado de revisar este texto.");

        btnRevisarOtro.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnRevisarOtro.setText("Revisar otro archivo");
        btnRevisarOtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarOtroActionPerformed(evt);
            }
        });

        btnRevisarDeNuevo.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnRevisarDeNuevo.setText("Revisar de Nuevo");
        btnRevisarDeNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarDeNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRevisionTerminadaLayout = new javax.swing.GroupLayout(panelRevisionTerminada);
        panelRevisionTerminada.setLayout(panelRevisionTerminadaLayout);
        panelRevisionTerminadaLayout.setHorizontalGroup(
            panelRevisionTerminadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRevisionTerminadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRevisionTerminada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRevisionTerminadaLayout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addGroup(panelRevisionTerminadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnRevisarDeNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRevisarOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(215, 215, 215))
        );
        panelRevisionTerminadaLayout.setVerticalGroup(
            panelRevisionTerminadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRevisionTerminadaLayout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(labelRevisionTerminada, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRevisarOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRevisarDeNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        panelChecker.add(panelRevisionTerminada, "revisionterminada");

        javax.swing.GroupLayout panelSpellCheckerLayout = new javax.swing.GroupLayout(panelSpellChecker);
        panelSpellChecker.setLayout(panelSpellCheckerLayout);
        panelSpellCheckerLayout.setHorizontalGroup(
            panelSpellCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSpellCheckerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelChecker, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE))
        );
        panelSpellCheckerLayout.setVerticalGroup(
            panelSpellCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSpellCheckerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(panelChecker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cardsPanel.add(panelSpellChecker, "spellchecker");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento para boton 'SeleccionarArchivo'. Manda a seleccionar un archivo, 
     * si un archivo es seleccionado se muestra el panel del SpellChecker y 
     * muestra el contenido del archivo.
     * @param evt 
     */
    private void btnSeleccionarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarArchivoActionPerformed
        if(control.seleccionarArchivo(cardsPanel)){
            control.reiniciarRevision();
            CardLayout cl = (CardLayout) cardsPanel.getLayout();
            cl.show(cardsPanel, "spellchecker");
            CardLayout cl2 = (CardLayout) panelChecker.getLayout();
            cl2.show(panelChecker, "revision");
            textContenido.setText(control.getContenidoTexto());
            textContenido.setCaretPosition(0);
            revisarSiguiente();
        }
    }//GEN-LAST:event_btnSeleccionarArchivoActionPerformed

    /**
     * Evento para boton 'RevisarOtro'. Muestra el panel para seleccionar un
     * nuevo archivo.
     * @param evt 
     */
    private void btnRevisarOtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevisarOtroActionPerformed
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, "selector");
    }//GEN-LAST:event_btnRevisarOtroActionPerformed

    /**
     * Evento para boton 'AgregarPalabra', añade la palabra actual y revisa el 
     * siguiente.
     * @param evt 
     */
    private void btnAgregarPalabraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPalabraActionPerformed
        control.agregarPalabra(control.getPalabraParaCorregir());
        revisarSiguiente();
    }//GEN-LAST:event_btnAgregarPalabraActionPerformed

    /**
     * Evento para ToggleBoton de 'Algoritmo', si está seleccionado se utiliza
     * la Busqueda Hash, si no, se usa la Busqueda Binaria.
     * @param evt 
     */
    private void toggleAlgoritmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleAlgoritmoActionPerformed
        if(toggleAlgoritmo.isSelected()){
            toggleAlgoritmo.setText("Busqueda Hash");
            control.setAlgoritmoActual(Algoritmos.BUSQUEDA_HASH);
        } else {
            toggleAlgoritmo.setText("Busqueda Binaria");
            control.setAlgoritmoActual(Algoritmos.BUSQUEDA_BINARIA);
        }
    }//GEN-LAST:event_toggleAlgoritmoActionPerformed

    /**
     * Evento para 'OmitirPorAhora', omite la palabra actual y revisa la
     * siguiente.
     * @param evt 
     */
    private void btnOmitirTemporalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOmitirTemporalActionPerformed
        control.omitirPorAhora();
        revisarSiguiente();
    }//GEN-LAST:event_btnOmitirTemporalActionPerformed

    /**
     * Evento para boton de 'Omitir', omite esta palabra por el resto de la 
     * revision y continua revisando
     * @param evt 
     */
    private void btnOmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOmitirActionPerformed
        control.omitirPalabraActual();
        revisarSiguiente();
    }//GEN-LAST:event_btnOmitirActionPerformed

    /**
     * Evento para boton 'Sustituir', toma la palabra de textPalabraSustituta,
     * si esta no es un String vacio, sustituye todas las veces que se repita
     * la palabra actual con la nueva palabra.
     * @param evt 
     */
    private void btnSustituirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSustituirActionPerformed
        String palabraNueva = textPalabraSustituta.getText().trim();
        
        if(palabraNueva.equals("")){
            JOptionPane.showMessageDialog(
                    this, 
                    "No ingresaste una palabra para sustituir",
                    "Cuidado", 
                    JOptionPane.WARNING_MESSAGE);
        } else {
            control.sustituir(palabraNueva);
            textPalabraSustituta.setText("");
            textContenido.setText(control.getContenidoTexto());
            revisarSiguiente();
        }
    }//GEN-LAST:event_btnSustituirActionPerformed

    /**
     * Evento para boton 'Terminar' que manda a terminar la revision
     * @param evt 
     */
    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        terminarRevision();
    }//GEN-LAST:event_btnTerminarActionPerformed

    /**
     * Evento para boton 'RevisarDeNuevo' que reinicia la revision y muestra
     * los paneles de revision.
     * @param evt 
     */
    private void btnRevisarDeNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevisarDeNuevoActionPerformed
        control.reiniciarRevision();
        CardLayout cl = (CardLayout) panelChecker.getLayout();
        cl.show(panelChecker, "revision");
        revisarSiguiente();
    }//GEN-LAST:event_btnRevisarDeNuevoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPalabra;
    private javax.swing.JButton btnOmitir;
    private javax.swing.JButton btnOmitirTemporal;
    private javax.swing.JButton btnRevisarDeNuevo;
    private javax.swing.JButton btnRevisarOtro;
    private javax.swing.JButton btnSeleccionarArchivo;
    private javax.swing.JButton btnSustituir;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JPanel cardsPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelRevisionTerminada;
    private javax.swing.JLabel labelSelectorDesc;
    private javax.swing.JLabel labelSelectorTitulo;
    private javax.swing.JPanel panelChecker;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelRevision;
    private javax.swing.JPanel panelRevisionTerminada;
    private javax.swing.JPanel panelSpellChecker;
    private javax.swing.JTextArea textContenido;
    private javax.swing.JTextArea textCorrecion;
    private javax.swing.JTextField textPalabraSustituta;
    private javax.swing.JToggleButton toggleAlgoritmo;
    // End of variables declaration//GEN-END:variables
}