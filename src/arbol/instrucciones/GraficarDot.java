/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class GraficarDot extends Instruccion{

    Expresion Ruta;
    Expresion Cadena;
    
    public GraficarDot(Expresion ruta,Expresion cadena){
        this.Ruta=ruta;
        this.Cadena=cadena;
        this.Instruccion="Graficar_Dot";
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Expresion ruta=Ruta.getValor(ent);
        Expresion cadena=Cadena.getValor(ent);
        String nuevaruta;
        int i = ruta.valor.toString().lastIndexOf('.');
        String extension;
        if (i > 0) {
            extension = ruta.valor.toString().substring(i+1);
            nuevaruta=ruta.valor.toString().replace(extension, "dot");
            System.out.println(nuevaruta);
        }else{
            nuevaruta=ruta.valor.toString()+".dot";
            System.out.println(nuevaruta);
        }
        File dot=new File(nuevaruta);
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(dot))) {
                bw.write(cadena.valor.toString());
                bw.close();
                String cmd = "dot -Tjpg "+nuevaruta+" -o "+ruta.valor.toString(); //Comando de apagado en linux
                Runtime.getRuntime().exec(cmd); 
                Thread.sleep(1000);
                File objetofile = new File (ruta.valor.toString());
                Desktop.getDesktop().open(objetofile);
            } catch (InterruptedException ex) {
                Logger.getLogger(GraficarDot.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            cError errora=new cError("Ejecucion","No se puedo crear archivo",linea,columna);
            ProyectCompi1.errores.add(errora); 
        }
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;    
    }
    
}
