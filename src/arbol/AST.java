package arbol;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angel
 */
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.SimboloMF;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import proyectcompi1.ProyectCompi1;
public class AST {
    
    public LinkedList<Instruccion> lista_instrucciones;
    public Instruccion Clase;
    Entorno tablaGlobal;
    public static String archivo;

    public AST() {
        tablaGlobal = new Entorno("Global",null);
    }
    
    public void SetInstrucciones(LinkedList<Instruccion> lista_instrucciones){
        this.lista_instrucciones=lista_instrucciones;
        this.Clase=null;
    }
    
    public void SetInstrucciones(Instruccion Clase){
        this.Clase=Clase;
        this.lista_instrucciones=null;
    }
    
    public void Ejecutar () {
        if (lista_instrucciones!=null) {
            lista_instrucciones.forEach((instruccion) -> {
                instruccion.ejecutar(tablaGlobal);
            });
        }else{
            Clase.ejecutar(tablaGlobal);
        }
    }
    
    public Entorno getEntorno(){
        return tablaGlobal;
    }
    
    public void Inicio(){
        Entorno temp;
        for(temp=tablaGlobal;temp!=null;temp=temp.anterior){
            Simbolo inicio=temp.buscar("main#", 0, 0);
            if (inicio!=null) {
                Entorno entinicio=new Entorno("main",tablaGlobal);
                ((SimboloMF)inicio).getBloque().ejecutar(entinicio);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No exite void main()", "Advertencia", 0);
    }
    
    public void Graficar(String AST) throws IOException, InterruptedException{
        File file=new File("AST"+AST+".dot");
        BufferedWriter bw;
        bw=new BufferedWriter(new FileWriter(file));
        archivo="digraph matriz{ \n rankdir=LR; \n"; 
        int i;
        for (i=0;i<lista_instrucciones.size();i++) {
            archivo+="node"+i+" [label=\""+lista_instrucciones.get(i).Instruccion+"\",height=0.5];\n";
            archivo+=lista_instrucciones.get(i).graficar(i+"",(i+1)+"")+"\n";
        }
        archivo+="node"+i+" [label=\"Aceptacion\",height=0.5];\n";
        archivo+="}";
        bw.write(archivo);
        bw.close();
        String cmd = "dot -Tjpg AST"+AST+".dot -o AST"+AST+".jpg"; //Comando de apagado en linux
        Runtime.getRuntime().exec(cmd); 
        Thread.sleep(1000);
        File objetofile = new File ("AST"+AST+".jpg");
        if (objetofile.exists()) {
            ProyectCompi1.imagenes.add(objetofile);
        }
        Desktop.getDesktop().open(objetofile);
    }
    
}
