/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.control;

import arbol.Instruccion;
import arbol.entornos.Entorno;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class Instrucciones extends Instruccion {
    
    LinkedList <Instruccion> instrucciones;

    public Instrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Instrucciones() {
        this.instrucciones = new LinkedList();
    }

    @Override
    public String graficar(String nonode,String siguiente){
        String bloque="";
        for (int i = 0; i < instrucciones.size(); i++) {
            if (i==0) {
                bloque+="node"+nonode+i+"\n";
            }
            bloque +="node"+nonode+i+" [label=\""+instrucciones.get(i).Instruccion+"\",height=0.5];\n";
            if ((i+1)<instrucciones.size()) {
                bloque +=instrucciones.get(i).graficar(nonode+i,nonode+(i+1))+"\n";
            }else{
                switch(instrucciones.get(i).Instruccion){
                    case "IfElse":
                    case "for":
                    case "switch":
                    case "while":
                    case "do":
                        bloque +=instrucciones.get(i).graficar(nonode+i,siguiente)+"\n";
                        break;
                    default:        
                        bloque+="node"+nonode+i+" -> {node"+siguiente+"}\n";
                        break;
                }
            }
        }
        return bloque;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        for (Instruccion instruccion : instrucciones) {
            Object retorno = instruccion.ejecutar(ent);
            if (retorno != null ) {
                return retorno;
            }
        }
        
        return null;
    }
    
    
    
}
