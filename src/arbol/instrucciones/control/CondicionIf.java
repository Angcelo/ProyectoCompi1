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
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

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
    public Object ejecutar(Entorno ent) {
        Object retornos=null;
        Expresion condicion_ = this.condicion.getValor(ent);
        
        if (condicion_.tipo.tipo == booleano) {
            boolean ejecutar = Boolean.parseBoolean(condicion_.valor.toString());
            if (ejecutar) {
                Entorno nuevo = new Entorno("if",ent);
                retornos = this.bloque.ejecutar(nuevo);
                this.ejecutado=true;
                return retornos;
            }
        }else{
            cError errora=new cError("Semantico","Se esperaba valor booleano",linea,columna);
            ProyectCompi1.errores.add(errora);
        }  
        this.ejecutado=false;
        return retornos;
    }
}
