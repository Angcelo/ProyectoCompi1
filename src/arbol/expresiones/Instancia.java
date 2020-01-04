/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.entornos.Entorno;
import arbol.entornos.Tipo;
import arbol.instrucciones.Clase;
import java.util.LinkedList;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class Instancia extends Expresion{
    
    Tipo clase;
    LinkedList<Expresion> parametros;
    public Entorno cent;

    public Instancia(Tipo clase, LinkedList<Expresion> parametros, int linea, int columna) {
        this.clase = clase;
        this.parametros = parametros;
        this.tipo=new Tipo(Tipo.EnumTipo.clase,clase.tr,-1);
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        cent =new Entorno(clase.tr,null);
        LinkedList parametros_=null;
        if (parametros!=null) {
            parametros_=new LinkedList();
            for (Expresion parametro:parametros) {
                parametros_.add(parametro.getValor(ent));
            }
        }
        if (ProyectCompi1.L_clases.containsKey(clase.tr)) {
            Clase nueva=ProyectCompi1.L_clases.get(clase.tr);
            nueva.param=parametros_;
            nueva.ejecutar(cent);
        }else{
            cError errora=new cError("Semantico","'"+clase.tr+"' no existe",linea,columna);
            ProyectCompi1.errores.add(errora); 
        }
        Literal l=new Literal(new Tipo(Tipo.EnumTipo.cadena),this.getClass());
        return l;
    }
    
}
