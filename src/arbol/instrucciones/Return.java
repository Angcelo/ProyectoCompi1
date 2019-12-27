/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;
/**
 *
 * @author angel
 */
public class Return extends Instruccion{

    Expresion e;
    
    public Return(){
        e=null;
    }
    
    public Return(Expresion e){
        this.e=e;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Expresion e_=e.getValor(ent);
        
        if (ProyectCompi1.pilaMF.contains(ProyectCompi1.MF.metodo) && e == null) {
            return null;
        }else if(ProyectCompi1.pilaMF.contains(ProyectCompi1.MF.funcion) && e != null){
            return e_;
        }else if(ProyectCompi1.pilaMF.contains(ProyectCompi1.MF.metodo) && e != null){
            cError errora=new cError("Semantico","Un metodo no puede retornar un valor",linea,columna);
            ProyectCompi1.errores.add(errora); 
        }else{
           cError errora=new cError("Semantico","Una funcion debe retornar un valor",linea,columna);
           ProyectCompi1.errores.add(errora); 
        }
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
