/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.SimboloMF;
import arbol.entornos.Tipo;
import arbol.instrucciones.control.Instrucciones;
import java.util.LinkedList;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;


/**
 *
 * @author angel
 */
public class DeclaracionMF extends Instruccion{

    String nombre;
    Tipo tipo;
    Instrucciones bloque;
    LinkedList<Instruccion> parametros;
    
    public DeclaracionMF(Tipo tipo,String nombre,LinkedList<Instruccion> parametros,Instrucciones bloque, int linea, int columna){
        this.bloque=bloque;
        this.tipo=tipo;
        this.nombre=nombre;
        this.parametros=parametros;
        this.linea=linea;
        this.columna=columna;
    }
    
    public DeclaracionMF(Tipo tipo,String nombre,Instrucciones bloque, int linea, int columna){
        this.bloque=bloque;
        this.nombre=nombre;
        this.tipo=tipo;
        this.parametros=null;
        this.linea=linea;
        this.columna=columna;
    }
    
    public DeclaracionMF(String nombre,LinkedList<Instruccion> parametros,Instrucciones bloque, int linea, int columna){
        this.bloque=bloque;
        this.tipo=null;
        this.nombre=nombre;
        this.parametros=parametros;
        this.linea=linea;
        this.columna=columna;
    }
    
    public DeclaracionMF(String nombre,Instrucciones bloque, int linea, int columna){
        this.bloque=bloque;
        this.nombre=nombre;
        this.tipo=null;
        this.parametros=null;
        this.linea=linea;
        this.columna=columna;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        SimboloMF sim = new SimboloMF(tipo,nombre);
        sim.setDatos(parametros, bloque);
        String nombre_;
        if (tipo==null) {
            nombre_=nombre+"$";
        }else{
            nombre_=nombre+"#";
        }
        if (parametros!=null) {
            nombre_ = parametros.stream().map((parametro) -> ((Declaracion)parametro).tipo.tipo.toString()).reduce(nombre_, String::concat);
        }
        if (tipo==null) { 
            sim.tipo=new Tipo(Tipo.EnumTipo.contructor);
            ent.Global.insertar(nombre_, sim, linea, columna, "El constructor");
        }else if (tipo.tipo==Tipo.EnumTipo.vacio) {
            ent.Global.insertar(nombre_, sim, linea, columna, "El metodo");
        }else{
            ent.Global.insertar(nombre_, sim, linea, columna, "La funcion");
        }
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        System.out.println("Graficar");
        return "";
    }
    
}
