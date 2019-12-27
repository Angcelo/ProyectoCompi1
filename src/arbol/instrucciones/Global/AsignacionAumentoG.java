/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.Global;

import arbol.instrucciones.*;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class AsignacionAumentoG extends Instruccion {
    
    String id;
    Expresion valor;
    
    public AsignacionAumentoG(String id, int linea, int columna) {
        this.id = id;
        this.columna = columna;
        this.linea = linea;
        this.Instruccion="Aumento";
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Simbolo sim = ent.Global.buscarG(id, linea, columna, "La variable"); 
        boolean error=true;
        if (sim != null) { 
            switch (sim.tipo.tipo) { 
                case entero:
                    error=false;
                    sim.valor=Integer.parseInt(sim.valor.toString())+1;
                    break;
                case doble:
                    error=false;
                    sim.valor=Double.parseDouble(sim.valor.toString())+1;
                    break;
                case caracter:
                    error=false;
                    sim.valor=(int) sim.valor.toString().charAt(0)+1;
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
        char[] a=nonode.toCharArray();
            String nuevo=(Integer.parseInt(a[nonode.length()-1]+"")+1)+"";
            a[nonode.length()-1]=nuevo.charAt(0);
            String nonodes=String.valueOf(a);
        return "node"+nonode+" -> "+"node"+nonodes;
    }
    
    
    
    
}
