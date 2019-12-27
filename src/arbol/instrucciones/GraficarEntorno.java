/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
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
public class GraficarEntorno extends Instruccion{
    
    public GraficarEntorno(int linea,int columna){
        this.columna=columna;
        this.linea=linea;
        this.Instruccion="Graficar_Entorno";
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        try {
            ent.GraficarEntorno(linea, columna);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraficarEntorno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;    
    }
    
}
