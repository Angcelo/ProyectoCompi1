/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.control;

import arbol.Instruccion;
import arbol.entornos.Entorno;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class If extends Instruccion {

    LinkedList <CondicionIf> condiciones;
    Instrucciones bloqueElse ;

    public If(LinkedList<CondicionIf> instrucciones, Instrucciones bloqueElse) {
        this.condiciones = instrucciones;
        this.bloqueElse = bloqueElse;
        this.Instruccion="IfElse";
    }

    public If(LinkedList<CondicionIf> instrucciones) {
        this.condiciones = instrucciones;
        this.Instruccion="IfElse";
        
    }
    
    @Override
    public String graficar(String nonode,String siguiente){
        String bloque="";
        int i=0;
        for (i = 0; i < condiciones.size(); i++) {
            bloque +="node"+nonode+" -> node"+nonode+i+"\n";
            if (i==0) {
                bloque +="node"+nonode+i+" [label=\""+condiciones.get(i).Instruccion+"\",height=0.5];\n";
            }else{
                bloque +="node"+nonode+i+" [label=\"else "+condiciones.get(i).Instruccion+"\",height=0.5];\n";
            }
            bloque +="node"+nonode+i+"->"+condiciones.get(i).bloque.graficar(nonode+i,siguiente);
        }
        if (this.bloqueElse!=null) {
            bloque +="node"+nonode+" -> node"+nonode+i+"\n";
            bloque +="node"+nonode+i+" [label=\"Else\",height=0.5];\n";     
            bloque +="node"+nonode+i+"->"+bloqueElse.graficar(nonode+i,siguiente);
        }
        if (condiciones.isEmpty()) {
            bloque += "node"+siguiente+"\n";
        }
        return bloque;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        ArrayList<Object> retorno=new ArrayList();
        
        for (CondicionIf condicion : condiciones) {
            retorno = condicion.ejecutar(ent);
        }
        
        boolean ejecutado=(boolean)retorno.get(0);
        
        if (this.bloqueElse != null && !ejecutado) {
            Entorno nuevo  = new Entorno ("else",ent,ent.Global);
            retorno.set(1, bloqueElse.ejecutar(nuevo));
        }
        
        return retorno.get(1);
    }
    
    
    
}
