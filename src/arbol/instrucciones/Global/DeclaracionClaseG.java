/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.Global;

import arbol.instrucciones.*;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.SimboloClase;
import arbol.entornos.SimboloMF;
import arbol.entornos.Tipo;
import java.util.LinkedList;
import java.util.Map;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class DeclaracionClaseG extends Instruccion{

    String nombre;
    String clase;
    String clase2;
    LinkedList<Expresion> parametros;
    boolean declarar;
    
    public DeclaracionClaseG(String clase,String nombre,boolean declarar,int linea,int columna){
        this.nombre=nombre;
        this.clase=clase;
        this.declarar=declarar;
    }
    
    public DeclaracionClaseG(String clase,String nombre,LinkedList<Expresion> parametros,String clase2,boolean declarar,int linea,int columna){
        this.nombre=nombre;
        this.clase=clase;
        this.clase2=clase;
        this.parametros=parametros;
        this.declarar=declarar;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        SimboloClase sim=new SimboloClase(new Tipo(Tipo.EnumTipo.nulo),clase);
        if (ProyectCompi1.L_clases.containsKey(clase)) {
            sim.Bloque=ProyectCompi1.L_clases.get(clase);
            if (declarar && (clase == null ? clase2 == null : clase.equals(clase2))) {
                sim.entClase=new Entorno(nombre,ent,null);
                sim.entClase.Global=sim.entClase;
                LinkedList<Expresion> parametros_=new LinkedList();
                if (parametros!=null) {
                    for (Expresion parametro : parametros) {
                        parametros_.add(parametro.getValor(ent));
                    }
                    sim.Bloque.param=parametros_;
                }
                sim.Bloque.ejecutar(sim.entClase);
                if (parametros==null) {
                    sim.entClase.Constructor(linea,columna);
                }else{
                    sim.entClase.Constructor(parametros_,linea,columna);
                }
                sim.tipo=new Tipo(Tipo.EnumTipo.clase,clase);
            }
            ent.Global.insertar(nombre, sim, linea, columna, "La clase");
        }else{
            cError errora=new cError("Semantico","'"+this.nombre+"' no existe la clase "+this.clase,linea,columna);
            ProyectCompi1.errores.add(errora); 
        }
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
