/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectcompi1;

import arbol.AST;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author angel
 */
public class fmrPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form fmrPrincipal
     */
    public String palabraapredecir="";
    public String tabla;
    public int tablaint;
    public static ArrayList<JTextArea> textos=new ArrayList();
    public static ArrayList<String> url=new ArrayList();
    public static ArrayList<String> nombre=new ArrayList();
    public fmrPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pestañas = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Salidatxt = new javax.swing.JTextArea();
        predicciontxt = new javax.swing.JTextField();
        Menu = new javax.swing.JMenuBar();
        Menu1 = new javax.swing.JMenu();
        MCrear = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        MGuardar = new javax.swing.JMenuItem();
        MGuardarComo = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        MEjecutar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Pestañas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PestañasKeyTyped(evt);
            }
        });

        Salidatxt.setEditable(false);
        Salidatxt.setColumns(20);
        Salidatxt.setRows(5);
        jScrollPane1.setViewportView(Salidatxt);

        Menu1.setText("Archivo");
        Menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu1MouseClicked(evt);
            }
        });

        MCrear.setText("Crear");
        MCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCrearActionPerformed(evt);
            }
        });
        Menu1.add(MCrear);

        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Menu1.add(jMenuItem1);

        MGuardar.setText("Guardar");
        MGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MGuardarActionPerformed(evt);
            }
        });
        Menu1.add(MGuardar);

        MGuardarComo.setText("Guardar como");
        MGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MGuardarComoActionPerformed(evt);
            }
        });
        Menu1.add(MGuardarComo);

        Menu.add(Menu1);

        jMenu1.setText("Ejecutar");

        MEjecutar.setText("Ejecutar actual");
        MEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MEjecutarActionPerformed(evt);
            }
        });
        jMenu1.add(MEjecutar);

        Menu.add(jMenu1);

        jMenu2.setText("Reportes");

        jMenuItem2.setText("AST");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Errores");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        Menu.add(jMenu2);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                    .addComponent(Pestañas)
                    .addComponent(predicciontxt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(predicciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Menu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu1MouseClicked
        
    }//GEN-LAST:event_Menu1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser selectorArchivos = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos NMScript", "txt","NM");
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivos.setFileFilter(filtro);
        selectorArchivos.showOpenDialog(this);
        File archivo = selectorArchivos.getSelectedFile();
        System.out.println(archivo.getName());
        String texto="";
        if ((archivo != null) || !(archivo.getName().equals(""))) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while((linea=br.readLine())!=null){
                    texto+=linea+"\n";
                }
                System.out.println(texto);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(fmrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(fmrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.addHoja(archivo.getName(),texto,archivo.getPath());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void MGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MGuardarComoActionPerformed
        JFileChooser guardarArchivos=new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos NMScript", "txt","NM");
        guardarArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        guardarArchivos.setFileFilter(filtro);
        guardarArchivos.showSaveDialog(null);
        File archivo=guardarArchivos.getSelectedFile();
        BufferedWriter bw;
        try {
                bw=new BufferedWriter(new FileWriter(archivo));
                boolean decision = JOptionPane.showConfirmDialog(null,"¿Desea reemplazar el archivo?","Advertencia",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
                if (decision) {
                    bw.write(textos.get(Pestañas.getSelectedIndex()).getText());
                    ArrayList temp=new ArrayList();
                    ArrayList temp2=new ArrayList();
                    for (int i = 0; i < fmrPrincipal.url.size(); i++) {
                        if (i==Pestañas.getSelectedIndex()) {
                            temp.add(archivo.getPath());
                            temp2.add(archivo.getName());
                        }else{
                            temp.add(fmrPrincipal.url.get(i));
                            temp2.add(fmrPrincipal.nombre.get(i));
                        }
                    }
                    fmrPrincipal.url=temp;
                    fmrPrincipal.nombre=temp2;
                    Pestañas.setTitleAt(Pestañas.getSelectedIndex(), archivo.getName());
                    System.out.println(fmrPrincipal.url);
                    System.out.println(fmrPrincipal.nombre);
                    JOptionPane.showMessageDialog(null, "Archivo Guardado"); 
                }
                bw.close();
            } catch (IOException ex) {
                String nombre=Pestañas.getTitleAt(Pestañas.getSelectedIndex());
                String ruta=archivo.getPath()+"/"+nombre;
                File nuevo=new File(ruta);
                try {
                    bw = new BufferedWriter(new FileWriter(nuevo));
                    bw.write(textos.get(Pestañas.getSelectedIndex()).getText());
                    bw.close();
                    ArrayList temp=new ArrayList();
                    for (int i = 0; i < url.size(); i++) {
                        if (i==Pestañas.getSelectedIndex()) {
                            temp.add(nuevo.getPath());
                        }else{
                            temp.add(url.get(i));
                        }
                    }
                    url=temp;
                    System.out.println(url);
                    JOptionPane.showMessageDialog(null, "Archivo Guardado"); 
                } catch (IOException ex1) {
                    Logger.getLogger(fmrPrincipal.class.getName()).log(Level.SEVERE, null, ex1);
                } 
                
        }
    }//GEN-LAST:event_MGuardarComoActionPerformed

    private void MCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCrearActionPerformed
        String nombreArchivo = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo archivo");
        if (nombreArchivo.contains(".NM")) {
            this.addHoja(nombreArchivo);
        }else{
            System.out.println("No posee ectension");
            this.addHoja(nombreArchivo+".NM");
        }
    }//GEN-LAST:event_MCrearActionPerformed

    private void MEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MEjecutarActionPerformed
        ProyectCompi1.Salida="";
        ProyectCompi1.errores.clear();
        ProyectCompi1.imagenes.clear();
        Salidatxt.setText("");
        String texto=textos.get(Pestañas.getSelectedIndex()).getText();
        File carpetatemp=new File("temp");
        carpetatemp.mkdir();
        String tempurl="temp/archivo"+Pestañas.getSelectedIndex()+".NM";
        File temp=new File(tempurl);
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter(temp));
            bw.write(texto);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(fmrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.interpretar(tempurl);
        if (ProyectCompi1.errores.isEmpty()) {
            Salidatxt.setText(ProyectCompi1.Salida);
        }else{
            ProyectCompi1.errores.stream().map((error) -> error.tipo+" | "+error.error+" | "+error.linea+" ,"+error.columna+"\n").forEachOrdered((textoerror) -> {
                Salidatxt.setText(Salidatxt.getText()+textoerror);
            });
        }
    }//GEN-LAST:event_MEjecutarActionPerformed

    private void MGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MGuardarActionPerformed
        String urlg=fmrPrincipal.url.get(Pestañas.getSelectedIndex());
        if (urlg.equals("")) {
            MGuardarComoActionPerformed(evt);
        }else{
            File guardar = new File(urlg);
            try {
                BufferedWriter bw=new BufferedWriter(new FileWriter(guardar));
                bw.write(textos.get(Pestañas.getSelectedIndex()).getText());
                bw.close();
                JOptionPane.showMessageDialog(null, "Archivo Guardado");
                
            } catch (IOException ex) {
                Logger.getLogger(fmrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_MGuardarActionPerformed

    private void PestañasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PestañasKeyTyped
        String[] PRs={"if","for","do","while","else","switch","case","default",
            "break","continue","import","println","print","Graficar_dot",
            "Graficar_entornos","int","double","char","boolean","string","null"};
        Highlighter h = textos.get(Pestañas.getSelectedIndex()).getHighlighter();
        h.removeAllHighlights();
        buscarcomentario(textos.get(Pestañas.getSelectedIndex()));
        buscarpalabra(textos.get(Pestañas.getSelectedIndex()),"//.*",Color.GRAY);
        buscarpalabra(textos.get(Pestañas.getSelectedIndex()),"\".*\"",Color.ORANGE);
        buscarpalabra(textos.get(Pestañas.getSelectedIndex()),"'.'",Color.ORANGE);
        buscarpalabra(textos.get(Pestañas.getSelectedIndex()),"\\b(-?[0-9]+(\\.[0-9]+)?)",Color.MAGENTA);
        for (String PR:PRs) {          
            buscarpalabra(textos.get(Pestañas.getSelectedIndex()),"\\b"+PR+"\\b",Color.blue);
        }   
    }//GEN-LAST:event_PestañasKeyTyped

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        tablaint=0;
        File errores=new File("errores.html");
        BufferedWriter bw;
        try {
            bw=new BufferedWriter(new FileWriter(errores));
            tabla="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Reporte de errores</title>\n" +
                "</head>\n" +
                "<body>\n";
            tabla+="<TABLE BORDER=\"1\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"4\"> \n"
                + "<TR>\n"
                + "<TD>No.</TD>\n"
                + "<TD>Tipo</TD>\n"
                + "<TD>Descripción</TD>\n"
                + "<TD>Fila</TD>\n"
                + "<TD>Columna</TD>\n"
                + "</TR>";
            ProyectCompi1.errores.forEach((v) -> tabla+="<TR>\n"
                + "<TD>"+ tablaint++ +"</TD>\n"
                + "<TD>"+v.tipo+"</TD>\n"
                + "<TD>"+v.error+"</TD>\n"
                + "<TD>"+v.linea+"</TD>\n"
                + "<TD>"+v.columna+"</TD>\n"
                + "</TR>");
            tabla+="</TABLE>\n" +
                "</body> \n" +
                "</html>";
            bw.write(tabla);
            bw.close();
            File objetofile = new File ("errores.html");
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            Logger.getLogger(fmrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fmrPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fmrPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fmrPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fmrPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fmrPrincipal().setVisible(true);
            }
        });
    }
    
    public void addHoja(String nombre){
        fmrPrincipal.url.add("");
        fmrPrincipal.nombre.add(nombre);
        textos.add(new JTextArea());
        textos.get(textos.size()-1).setBounds(0,0,1000,455);
        textos.get(textos.size()-1).setLineWrap(true);
        textos.get(textos.size()-1).setFont(new Font("TimesRoman", Font.PLAIN, 20));
        textos.get(textos.size()-1).addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PestañasKeyTyped(evt);
            }
        });
        Pestañas.addTab(nombre,textos.get(textos.size()-1));
    }
    
    public void addHoja(String nombre,String texto,String url){
        fmrPrincipal.url.add(url);
        fmrPrincipal.nombre.add(nombre);
        textos.add(new JTextArea(texto));
        textos.get(textos.size()-1).setBounds(0,0,1000,455);
        textos.get(textos.size()-1).setLineWrap(true);
        textos.get(textos.size()-1).setFont(new Font("TimesRoman", Font.PLAIN, 20));
        textos.get(textos.size()-1).setText(texto);
        textos.get(textos.size()-1).addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PestañasKeyTyped(evt);
            }
        });
        Pestañas.addTab(nombre,new JScrollPane(textos.get(textos.size()-1)));
    }
    
    public void interpretar(String path) {
        
        
        analizadores.parser pars;

        AST arbol;
        try {
            pars = new analizadores.parser(new analizadores.Lexico(new FileInputStream(path)));
            pars.parse();
            arbol = pars.AST;
            
            if (arbol != null) { 
                arbol.Ejecutar();
                arbol.Inicio();
            } else {
                cError errora=new cError("Ejecucion","<----------> Existió un error en el análisis, no se pudo construir el árbol <---------->",0,0);
                ProyectCompi1.errores.add(errora); 
            }

        } catch (Exception ex) {
            System.out.println(ex);
            cError errora=new cError("Ejecucion","Error fatal en compilación de entrada.",0,0);
            ProyectCompi1.errores.add(errora); 
        }
    }

    public void buscarpalabra(JTextArea area1, String patron,Color color) {
        if (patron.length() >= 1) {
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(color);
            Highlighter h = area1.getHighlighter();
            
            String text = area1.getText();
            Pattern p = Pattern.compile(patron);
            Matcher m = p.matcher(text);
            while (m.find()) {
                try {
                    h.addHighlight(m.start(), m.end(), highlightPainter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(fmrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(area1, "la palabra a buscar no puede ser vacia");
        }
    }
    public void buscarcomentario(JTextArea area1) {
        DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.gray);
        Highlighter h = area1.getHighlighter();
        String text = area1.getText();
        Pattern p1 = Pattern.compile("/\\*");
        Pattern p2 = Pattern.compile("\\*/");
        Matcher m1 = p1.matcher(text);
        Matcher m2 = p2.matcher(text);
        while (m1.find()&& m2.find()) {
            try {
                h.addHighlight(m1.start(), m2.end(), highlightPainter);
            } catch (BadLocationException ex) {
                Logger.getLogger(fmrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MCrear;
    private javax.swing.JMenuItem MEjecutar;
    private javax.swing.JMenuItem MGuardar;
    private javax.swing.JMenuItem MGuardarComo;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu Menu1;
    private javax.swing.JTabbedPane Pestañas;
    private javax.swing.JTextArea Salidatxt;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField predicciontxt;
    // End of variables declaration//GEN-END:variables
}