/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entornos.Entorno;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;
/**
 *
 * @author angel
 */
public class Break extends Instruccion {

    public Break(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
        this.Instruccion="break";
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Object retorno=null;
        
        if (ProyectCompi1.esVacia()) {
            retorno= this;
        }else {
            cError errora=new cError("Semantico"," break se esperaba dentro de un ciclo o switch",linea,columna);
            ProyectCompi1.errores.add(errora);
        }
        
        return retorno;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;
    }
    
    
    
}
