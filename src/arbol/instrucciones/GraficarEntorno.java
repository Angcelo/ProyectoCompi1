/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entornos.Entorno;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
