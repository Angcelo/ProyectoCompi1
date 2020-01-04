/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.expresiones.Arreglos;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.Tipo;
import arbol.expresiones.Literal;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class Declaracion extends Instruccion {

    public Tipo tipo;
    public String id;
    public Expresion valor;

    public Declaracion(Tipo tipo, String id, Expresion valor, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
        this.Instruccion="declaracion";
    }
    
    public Declaracion(Tipo tipo, String id, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.valor = null;
        this.linea = linea;
        this.columna = columna;
        this.Instruccion="declaracion";
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        if (this.tipo.Dimension>-1) {
            if (valor!=null && valor.getClass()==Arreglos.class) {
                valor.getValor(ent);
                if(valor.tipo.tipo==tipo.tipo){
                    Simbolo sim=new Simbolo(tipo,valor);
                    ent.insertar(id, sim, linea, columna, "El arreglo");
                }
            }else if(valor==null || valor.tipo.tipo==Tipo.EnumTipo.nulo){ 
                ent.insertar(id, new Simbolo(tipo, null), linea, columna, Instruccion);
            }else{
                cError errora=new cError("Semantico","'"+id+"' asignacion incorrecta para un arreglo",linea,columna);
                ProyectCompi1.errores.add(errora); 
            }
        }else if (valor != null) { 
            Simbolo simbolo;
            boolean error=true;
            Expresion resultado = valor.getValor(ent);
            switch (tipo.tipo) { 
                case entero:
                    switch (resultado.tipo.tipo) {
                        case entero:
                            simbolo = new Simbolo(tipo, (int)Double.parseDouble(resultado.valor.toString()));
                            ent.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            simbolo = new Simbolo(tipo, ascii);
                            ent.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
                case doble:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            simbolo = new Simbolo(tipo, ascii);
                            ent.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                        case entero:
                        case doble:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
                case caracter:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
                case booleano:
                    switch (resultado.tipo.tipo) {
                        case booleano:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
                case cadena:
                    switch (resultado.tipo.tipo) {
                        case cadena:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
                case nulo:
                    switch(valor.tipo.tipo){
                        case clase:
                            if (tipo.tr == null ? valor.tipo.tr == null : tipo.tr.equals(valor.tipo.tr)) {
                                simbolo=new Simbolo(new Tipo(Tipo.EnumTipo.clase,tipo.tr,-1),valor);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
                                error=false;
                                break;
                            }
                        case nulo:
                            simbolo=new Simbolo(new Tipo(Tipo.EnumTipo.nulo,tipo.tr,-1),null);
                            ent.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
            }
            if (error) {
                cError errora=new cError("Semantico","'"+id+"' asignacion de tipos incompatibles "+tipo.tipo+" no se puede convertir a "+resultado.tipo.tipo,linea,columna);
                ProyectCompi1.errores.add(errora); 
            }
        }else{ 
            switch (tipo.tipo) {
                case entero:
                    ent.insertar(id, new Simbolo(tipo, 0), linea, columna, "La variable");
                    break;
                case caracter:
                    ent.insertar(id, new Simbolo(tipo, '\0'), linea, columna, "La variable");
                    break;
                case booleano:
                    ent.insertar(id, new Simbolo(tipo, false), linea, columna, "La variable");
                    break;
                case doble:
                    ent.insertar(id, new Simbolo(tipo, 0.0), linea, columna, "La variable");
                    break;
                case cadena:
                    ent.insertar(id, new Simbolo(tipo, ""), linea, columna, "La variable");
                    break;
                case nulo:
                    ent.insertar(id, new Simbolo(new Tipo(Tipo.EnumTipo.nulo,tipo.tr,-1),null), linea, columna, "La variable");
                    break;
            }
        }
        return null;
    }
    

    @Override
    public String graficar(String nonode,String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;
    }
}
