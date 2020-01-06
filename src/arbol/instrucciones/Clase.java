/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.Tipo;
import arbol.instrucciones.control.Instrucciones;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class Clase extends Instruccion{
    
    LinkedList<Instruccion> lista_importados;
    Instrucciones lista_instrucciones;
    public String nombre;
    public LinkedList<Expresion> param;
    
    public Clase(LinkedList<Instruccion> lista_importados, String nombre, Instrucciones lista_instrucciones, int linea, int columna){
        this.param = null;
        this.lista_importados=lista_importados;
        this.lista_instrucciones=lista_instrucciones;
        this.nombre=nombre;
        this.linea=linea;
        this.columna=columna;
    }
    
    public Clase(String nombre, Instrucciones lista_instrucciones, int linea, int columna){
        this.param = null;
        this.lista_importados=null;
        this.lista_instrucciones=lista_instrucciones;
        this.nombre=nombre;
        this.linea=linea;
        this.columna=columna;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        ent.nombre=nombre;
        if (lista_importados!=null) {
            lista_importados.forEach((importado) -> {
                importado.ejecutar(ent);
            });
        }
        lista_instrucciones.ejecutar(ent);
        Simbolo rthis = new Simbolo(new Tipo(Tipo.EnumTipo.objeto,"this",-1),ent);
        ent.insertar("this", rthis, linea, columna, "Global");
        ent.Constructor();
        if (param!=null) {
            ent.Constructor(param,linea,columna);
        }
        return null;
    }
    
    @Override
    public String graficar(String nonode, String siguiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
