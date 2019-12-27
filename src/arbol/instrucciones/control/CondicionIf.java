/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.control;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import static arbol.entornos.Tipo.EnumTipo.booleano;
import arbol.instrucciones.Break;
import arbol.instrucciones.Continue;
import java.util.ArrayList;

/**
 *
 * @author angel
 */
public class CondicionIf extends Instruccion  {
    
    Expresion condicion;
    Instrucciones bloque;
    boolean ejecutado;
    
    public CondicionIf(Expresion condicion, Instrucciones bloque) {
        this.condicion = condicion;
        this.bloque = bloque;
        this.Instruccion="If";
        this.ejecutado=false;
    }
    
    @Override
    public String graficar(String nonode,String siguiente){
        return bloque.graficar(nonode,siguiente);
    }
    
    @Override
    public ArrayList<Object> ejecutar(Entorno ent) {
        ArrayList<Object> retornos=new ArrayList();
        Expresion condicion_ = this.condicion.getValor(ent);
        
        if (condicion_.tipo.tipo == booleano) {
            boolean ejecutar = Boolean.parseBoolean(condicion_.valor.toString());
            if (ejecutar) {
                retornos.add(true);
                Entorno nuevo = new Entorno("if",ent,ent.Global);
                retornos.add(this.bloque.ejecutar(nuevo));
                return retornos;
            }
        }    
        retornos.add(false);
        retornos.add(null);
        return retornos;
    }
}
