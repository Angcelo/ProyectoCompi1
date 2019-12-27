/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.instrucciones.Global.*;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.SimboloClase;
import arbol.entornos.Tipo;
import arbol.expresiones.Id;
import arbol.expresiones.IdG;
import arbol.expresiones.Literal;
import java.util.LinkedList;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class Asignacion extends Instruccion {
    
    LinkedList<Expresion> ids;
    Expresion valor;

    public Asignacion(LinkedList<Expresion> ids, int linea, int columna, Expresion valor) {
        this.ids = ids;
        this.valor = valor;
        this.columna = columna;
        this.linea = linea;
        this.Instruccion="Asignacion";
    }
    
    public Asignacion(LinkedList<Expresion> ids, int columna, int linea){
        this.ids=ids;
        this.columna=columna;
        this.linea=linea;
        this.valor=null;
        this.Instruccion="Asignacion";
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Entorno temp=ent;
        for (int i=(ids.size()-1); i>0;i--) {
            if (ids.get(i).getClass()==Id.class) {
                Simbolo sim = temp.buscar(((Id)ids.get(i)).getid(), linea, columna, "La variable");
                if(sim.getClass()==SimboloClase.class){
                    if (sim.tipo.tipo!=Tipo.EnumTipo.nulo) {
                        temp=((SimboloClase)sim).entClase;
                    }else{
                        cError errora=new cError("Semantico",((Id)ids.get(i)).getid()+"La clase no esta inicializada",linea,columna);
                        ProyectCompi1.errores.add(errora);
                        return null;
                    }
                }else{
                    cError errora=new cError("Semantico",((Id)ids.get(i)).getid()+" solo se puede referenciar una clase",linea,columna);
                    ProyectCompi1.errores.add(errora);
                    return null;
                }
            }else if(ids.get(i).getClass()==IdG.class){
                Simbolo sim = temp.Global.buscar(((IdG)ids.get(i)).getid(), linea, columna, "La variable");
                if(sim.getClass()==SimboloClase.class){
                    if (sim.tipo.tipo!=Tipo.EnumTipo.nulo) {
                        temp=((SimboloClase)sim).entClase;
                    }else{
                        cError errora=new cError("Semantico",((IdG)ids.get(i)).getid()+"La clase no esta inicializada",linea,columna);
                        ProyectCompi1.errores.add(errora);
                        return null;
                    }
                }else{
                    cError errora=new cError("Semantico",((IdG)ids.get(i)).getid()+" solo se puede referenciar una clase",linea,columna);
                    ProyectCompi1.errores.add(errora);
                    return null;
                }
            }
        }   
        if (valor==null) {
            return ids.get(0).getValor(temp);
        }else{
            Simbolo sim=null;
            if (ids.get(0).getClass()==Id.class) {
                sim = temp.buscar(((Id)ids.get(ids.size()-1)).getid(), linea, columna, "La variable");
            }else if(ids.get(0).getClass()==IdG.class){
                sim = temp.Global.buscar(((IdG)ids.get(ids.size()-1)).getid(), linea, columna, "La variable");
            }
            boolean error=true;
            if (sim != null) { 
                Expresion resultado = valor.getValor(temp);
                switch (sim.tipo.tipo) { 
                    case entero:
                        switch (resultado.tipo.tipo) {
                            case entero:
                                sim.valor = (int)Double.parseDouble(resultado.valor.toString());
                                error=false;
                                break;
                            case caracter:
                                int ascii = (int) resultado.valor.toString().charAt(0);
                                sim.valor = ascii;
                                error=false;
                                break;
                        }
                        break;
                    case doble:
                        switch (resultado.tipo.tipo) {
                            case caracter:
                                int ascii = (int) resultado.valor.toString().charAt(0);
                                sim.valor = ascii;
                                error=false;
                                break;
                            case entero:
                            case doble:
                                sim.valor = resultado.valor;
                                error=false;
                                break;
                        }
                        break;
                    case caracter:
                        switch (resultado.tipo.tipo) {
                            case caracter:
                                sim.valor = resultado.valor;
                                error=false;
                                break;
                        }
                        break;
                    case booleano:
                        switch (resultado.tipo.tipo) {
                            case booleano:
                                sim.valor = resultado.valor;
                                error=false;
                                break;
                        }
                        break;
                    case cadena:
                        switch (resultado.tipo.tipo) {
                            case cadena:
                                sim.valor = resultado.valor;
                                error=false;
                                break;
                        }
                        break;
                }
                if (error) {
                    cError errora=new cError("Semantico","asignacion de tipos incompatibles "+sim.tipo.tipo+" no se puede convertir a "+resultado.tipo.tipo,linea,columna);
                    ProyectCompi1.errores.add(errora);
                }
            } 
        }
        return null;
    }

    @Override
    public String graficar(String nonode,String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;
    }
    
    
    
    
}
