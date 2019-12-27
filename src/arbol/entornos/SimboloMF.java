/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entornos;

import arbol.Instruccion;
import arbol.instrucciones.control.Instrucciones;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class SimboloMF extends Simbolo{

    LinkedList<Instruccion> parametros;
    Instrucciones bloque;
    
    public SimboloMF(Tipo tipo, Object valor) {
        super(tipo, valor);
    }
    
    public void setDatos(LinkedList<Instruccion> parametros,Instrucciones bloque){
        this.parametros=parametros;
        this.bloque=bloque;
    }
    
    public LinkedList<Instruccion> getParametros(){
        return this.parametros;
    }
    
    public Instrucciones getBloque(){
        return this.bloque;
    }
}
