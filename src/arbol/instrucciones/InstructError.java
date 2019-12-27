/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entornos.Entorno;
import proyectcompi1.cError;
import proyectcompi1.ProyectCompi1;

/**
 *
 * @author angel
 */
public class InstructError extends Instruccion{

    Object error;
    public InstructError(Object error,int linea,int columna){
        this.error=error;
        this.linea=linea;
        this.columna=columna;
        this.Instruccion="Error";
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        cError errors=new cError("Sintactico",this.error.toString(),this.linea,this.columna);
        ProyectCompi1.errores.add(errors);
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;    
    }
    
}
