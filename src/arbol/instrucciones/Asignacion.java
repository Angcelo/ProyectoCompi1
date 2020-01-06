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
import arbol.expresiones.Arreglos;
import arbol.expresiones.ExpresionMF;
import arbol.expresiones.Id;
import arbol.expresiones.Instancia;
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
        Entorno temp=new Entorno("Ejecutar",ent);
        for (int i=(ids.size()-1); i>0;i--) {
            Expresion e_=ids.get(i).getValor(temp);
            if(e_.tipo.tipo==Tipo.EnumTipo.objeto){
                if (e_.valor!=null) {
                    temp=((Entorno)e_.valor);
                    temp.anterior=ent;
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
        }   
        if (valor==null) {
            ids.get(0).getValor(temp);
            return null;
        }else if(((Id)ids.get(0)).busqueda!=null){
            Simbolo sim=temp.buscar(((Id)ids.get(0)).getid(), linea, columna, "La variable");
            LinkedList<Integer> buscar=((Id)ids.get(0)).busqueda;
            if (sim!=null) {
                Expresion resultado = valor.getValor(ent);
                ((Arreglos)sim.valor).Reemplazar((Arreglos)sim.valor, buscar, resultado, 0, linea, columna);
            }
        }else{
            Simbolo sim=temp.buscar(((Id)ids.get(0)).getid(), linea, columna, "La variable");
            Expresion e_=ids.get(0).getValor(temp);
            boolean error=true;
            if (sim != null) { 
                Expresion resultado = valor.getValor(ent);
                    if (sim.tipo.Dimension!=resultado.tipo.Dimension) {
                    cError errora=new cError("Semantico","'"+((Id)ids.get(0)).getid()+"' asignacion de tipos incompatibles "+sim.tipo.tipo+"D:"+sim.tipo.Dimension+" no se puede convertir a "+resultado.tipo+"D:"+resultado.tipo.Dimension,linea,columna);
                    ProyectCompi1.errores.add(errora); 
                    return null;
                }
                if (sim.tipo.Dimension>-1) {
                   switch (sim.tipo.tipo) { 
                    case entero:
                        switch (resultado.tipo.tipo) {
                            case entero:
                            case caracter:
                                sim.valor=resultado.valor;
                                error=false;
                                break;
                            case nulo:
                                sim.valor=null;
                                error=false;
                                break;
                        }
                        break;
                    case doble:
                        switch (resultado.tipo.tipo) {
                            case caracter:
                            case entero:
                            case doble:
                                sim.valor=resultado.valor;
                                error=false;
                                break;
                            case nulo:
                                sim.valor=null;
                                error=false;
                                break;
                        }
                        break;
                    case caracter:
                        switch (resultado.tipo.tipo) {
                            case caracter:
                                sim.valor=resultado.valor;
                                error=false;
                                break;
                            case nulo:
                                sim.valor=null;
                                error=false;
                                break;
                        }
                        break;
                    case booleano:
                        switch (resultado.tipo.tipo) {
                            case booleano:
                                sim.valor=resultado.valor;
                                error=false;
                                break;
                            case nulo:
                                sim.valor=null;
                                error=false;
                                break;
                        }
                        break;
                    case cadena:
                        switch (resultado.tipo.tipo) {
                            case cadena:
                                sim.valor=resultado.valor;
                                error=false;
                                break;
                            case nulo:
                                sim.valor=null;
                                error=false;
                                break;
                        }
                        break;
                    case objeto:
                        switch(resultado.tipo.tipo){
                            case objeto:
                                sim.valor=resultado.valor;
                                error=false;
                                break;
                            case nulo:
                                sim.valor=null;
                                error=false;
                                break;
                        }
                        break;
                    }
                }else{
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
                        case objeto:
                            switch(resultado.tipo.tipo){
                                case objeto:
                                    if (sim.tipo.tr == null ? resultado.tipo.tr == null : sim.tipo.tr.equals(resultado.tipo.tr)) {
                                        sim.valor=resultado.valor;
                                    }else{
                                        cError errora=new cError("Semantico","asignacion de tipos incompatibles "+sim.tipo.tr+" no se puede convertir a "+resultado.tipo.tr,linea,columna);
                                        ProyectCompi1.errores.add(errora);
                                    }
                                    error=false;
                                    break;
                                case nulo:
                                    sim.valor=null;
                                    error=false;
                                break;
                            }
                    }
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
