/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entornos.Entorno;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;
/**
 *
 * @author angel
 */
public class Continue extends Instruccion {

    public Continue  (int linea , int columna) {
        this.linea = linea;
        this.columna = columna;
        this.Instruccion="continue";
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        if (ProyectCompi1.Nociclo()) {
            return this;
        }else{
            cError errora=new cError("Semantico","continue se esperaba dentro de un ciclo",linea,columna);
            ProyectCompi1.errores.add(errora);
        }
        
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;    
    }
    
}
