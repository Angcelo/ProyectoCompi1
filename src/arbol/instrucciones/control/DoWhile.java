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
import arbol.instrucciones.Break;
import arbol.instrucciones.Continue;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class DoWhile extends Instruccion{
    
    Instrucciones bloque;
    Expresion Condiciones;
    
    public DoWhile(Expresion condiciones,Instrucciones bloque){
        this.Condiciones=condiciones;
        this.bloque=bloque;
        this.Instruccion="do";
    }

    @Override
    public Object ejecutar(Entorno ent) {
        ProyectCompi1.pilaCiclos.push(ProyectCompi1.SwitchCiclo.Ciclo);
        
        Expresion condiciones = this.Condiciones.getValor(ent);
        
        if (condiciones.tipo.tipo == Tipo.EnumTipo.booleano) {
            
            
            boolean condicion;
            
            do {
                Entorno entornoWhile = new Entorno ("DoWhile",ent);
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
                condiciones  = this.Condiciones.getValor(ent);
                condicion = Boolean.parseBoolean(condiciones.valor.toString());
            }while (condicion);
            
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
        return cadena;    }
    
    
}
