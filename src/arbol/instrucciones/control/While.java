/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.control;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Tipo;
import arbol.instrucciones.*;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class While extends Instruccion {

    Instrucciones bloque;
    Expresion valorWhile;

    public While(Expresion valorWhile , Instrucciones bloque) {
        this.bloque = bloque;
        this.valorWhile = valorWhile;
        this.Instruccion="while";
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        ProyectCompi1.pilaCiclos.push(ProyectCompi1.SwitchCiclo.Ciclo);
        
        Expresion valorWhile_ = this.valorWhile.getValor(ent);
        
        if (valorWhile_.tipo.tipo == Tipo.EnumTipo.booleano) {
            
            
            boolean condicion = Boolean.parseBoolean(valorWhile_.valor.toString());
            
            while (condicion) {
                Entorno entornoWhile = new Entorno ("while",ent);
                Object retorno = bloque.ejecutar(entornoWhile);
                
                if(retorno != null) {
                    
                    if (retorno.getClass() == Break.class) {
                        
                        break;
                    }else if (retorno.getClass() == Continue.class)  { 
                        
                    }else{
                        ProyectCompi1.pilaCiclos.pop();
                        return retorno;
                    }
                }
                valorWhile_  = this.valorWhile.getValor(ent);
                condicion = Boolean.parseBoolean(valorWhile_.valor.toString());
            }
            
        } else {
            cError errora=new cError("Semantico","Se esperaba valor booleano",linea,columna);
            ProyectCompi1.errores.add(errora);
        }
        
        
        ProyectCompi1.pilaCiclos.pop();
        return null;
    }

    @Override
    public String graficar(String nonode,String siguiente) {
        String cadena="";
        String repetir=this.bloque.graficar(nonode,siguiente+", node"+nonode);
        cadena +="node"+nonode+" -> "+repetir;
        return cadena;
    }
    
}
