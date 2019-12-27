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
import arbol.instrucciones.Global.AsignacionG;
import arbol.instrucciones.Break;
import arbol.instrucciones.Continue;
import proyectcompi1.ProyectCompi1;
/**
 *
 * @author angel
 */
public class For  extends Instruccion{

    Instrucciones bloque;
    Instruccion var;
    Expresion Comparacion;
    Instruccion Aumento;
    
    public For(Instruccion var,Expresion Comparacion,Instruccion Aumento,Instrucciones bloque){
        this.var=var;
        this.bloque=bloque;
        this.Comparacion=Comparacion;
        this.Aumento=Aumento;
        this.Instruccion="for";
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        var.ejecutar(ent);
        ProyectCompi1.pilaCiclos.push(ProyectCompi1.SwitchCiclo.Ciclo);
        Expresion comp=this.Comparacion.getValor(ent);
        if (comp.tipo.tipo==Tipo.EnumTipo.booleano) {
            boolean condicion = Boolean.parseBoolean(comp.valor.toString());
            while(condicion){
                Entorno entornofor=new Entorno("for",ent,ent.Global);
                Object retorno=bloque.ejecutar(entornofor);
                if (retorno!=null) {
                    if (retorno.getClass()==Break.class) {
                        break;
                    }
                    else if (retorno.getClass()==Continue.class) {
                        
                    }
                    else{
                        ProyectCompi1.pilaCiclos.pop();
                        return retorno;
                    }
                }
                this.Aumento.ejecutar(ent);
                comp=this.Comparacion.getValor(ent);
                condicion=Boolean.parseBoolean(comp.valor.toString());
            }
        }else{
            System.out.println("Error sintáctico: se esperaba valor booleano");
        }
        ProyectCompi1.pilaCiclos.pop();
        return null;
    }

    @Override
    public String graficar(String nonode,String siguiente) {
        String bloque="";
        String repetir=this.bloque.graficar(nonode,siguiente+", node"+nonode);
        bloque +="node"+nonode+" -> "+repetir;
        return bloque;
    }
    
}
