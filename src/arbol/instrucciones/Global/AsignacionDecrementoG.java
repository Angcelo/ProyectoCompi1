/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.Global;

import arbol.instrucciones.*;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class AsignacionDecrementoG extends Instruccion {
    
    String id;

    public AsignacionDecrementoG(String id, int linea, int columna) {
        this.id = id;
        this.columna = columna;
        this.linea = linea;
        this.Instruccion="Disminucion";
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Simbolo sim = ent.Global.buscarG(id, linea, columna, "La variable"); 
        boolean error=true;
        if (sim != null) { 

            switch (sim.tipo.tipo) { 
                case entero:
                    error=false;
                    sim.valor=Integer.parseInt(sim.valor.toString())-1;
                    break;
                case doble:
                    error=false;
                    sim.valor=Double.parseDouble(sim.valor.toString())-1;
                    break;
                case caracter:
                    error=false;
                    sim.valor=(int) sim.valor.toString().charAt(0)-1;
                    break;
            }
            if (error) {
                cError errora=new cError("Semantico","'"+id+"' tipo de dato incorrecto",linea,columna);
                ProyectCompi1.errores.add(errora);
            }
        } 
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;
    }
    
    
    
    
}
