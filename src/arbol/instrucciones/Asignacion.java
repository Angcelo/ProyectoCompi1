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
import arbol.expresiones.Id;
import arbol.expresiones.Instancia;
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
            Simbolo sim = temp.buscar(((Id)ids.get(i)).getid(), linea, columna, "La variable");
            if(sim.tipo.tipo==Tipo.EnumTipo.clase){
                if (sim.tipo.tipo!=Tipo.EnumTipo.nulo) {
                    temp=((Instancia)sim.valor).cent;
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
            return ids.get(0).getValor(ent);
        }else{
            Simbolo sim=temp.buscar(((Id)ids.get(0)).getid(), linea, columna, "La variable");
            boolean error=true;
            if (sim != null) { 
                Expresion resultado = valor.getValor(ent);
                if (sim.tipo.Dimension>-1) {
                    if (valor!=null && valor.getClass()==Arreglos.class) {
                        valor.getValor(ent);
                        if(((Arreglos)valor).Tipos((Arreglos)valor, sim.tipo,linea,columna)){
                            sim.valor=valor;
                        }
                    }else if(valor.tipo.tipo==Tipo.EnumTipo.nulo){ 
                        sim.valor=new Literal(new Tipo(Tipo.EnumTipo.nulo),null);
                    }else{
                        if (((Id)ids.get(0)).busqueda!=null) {
                            ((Arreglos)sim.valor).Reemplazar((Arreglos)sim.valor, ((Id)ids.get(0)).busqueda, valor, 0, linea, columna);
                            error=false;
                        }else{
                            cError errora=new cError("Semantico","'"+((Id)ids.get(ids.size()-1)).getid()+"' asignacion incorrecta para un arreglo",linea,columna);
                            ProyectCompi1.errores.add(errora); 
                        }
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
                        case nulo:
                        case clase:
                            switch(valor.tipo.tipo){
                                case clase:
                                if (sim.tipo.tr == null ? valor.tipo.tr == null : sim.tipo.tr.equals(valor.tipo.tr)) {
                                    sim.tipo=new Tipo(Tipo.EnumTipo.clase,sim.tipo.tr,-1);
                                    sim.valor=valor;
                                    error=false;
                                    break;
                                }
                                case nulo:
                                    sim.tipo=new Tipo(Tipo.EnumTipo.nulo,sim.tipo.tr,-1);
                                    sim.valor=sim.valor=new Literal(new Tipo(Tipo.EnumTipo.nulo,sim.tipo.tr,-1),null);;
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
