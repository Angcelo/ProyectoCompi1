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
        if (valor != null) {
            Expresion resultado = valor.getValor(ent);
            if (tipo.Dimension!=resultado.tipo.Dimension) {
                cError errora=new cError("Semantico","'"+id+"' asignacion de tipos incompatibles "+tipo.tipo+" D: "+tipo.Dimension+" no se puede convertir a "+resultado.tipo.tipo+" D: "+resultado.tipo.Dimension,linea,columna);
                ProyectCompi1.errores.add(errora); 
                return null;
            }
            Simbolo simbolo;
            boolean error=true;
            if(tipo.Dimension>-1){
                switch (tipo.tipo) { 
                    case entero:
                        switch (resultado.tipo.tipo) {
                            case entero:
                            case caracter:
                                simbolo = new Simbolo(tipo, resultado.valor);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
                                error=false;
                                break;
                            case nulo:
                                simbolo=new Simbolo(tipo,null);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
                                break;
                        }
                        break;
                    case doble:
                        switch (resultado.tipo.tipo) {
                            case caracter:
                            case entero:
                            case doble:
                                simbolo = new Simbolo(tipo, resultado.valor);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
                                error=false;
                                break;
                            case nulo:
                                simbolo=new Simbolo(tipo,null);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
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
                            case nulo:
                                simbolo=new Simbolo(tipo,null);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
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
                            case nulo:
                                simbolo=new Simbolo(tipo,null);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
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
                            case nulo:
                                simbolo=new Simbolo(tipo,null);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
                                break;
                        }
                        break;
                    case objeto:
                        switch(resultado.tipo.tipo){
                            case objeto:
                                simbolo = new Simbolo(tipo, resultado.valor);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
                                error=false;
                                break;
                            case nulo:
                                simbolo=new Simbolo(tipo,null);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
                                break;
                        }
                        break;
                }
            }else{
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
                            case nulo:

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
                    case objeto:
                        switch(resultado.tipo.tipo){
                            case objeto:
                                if (tipo.tr == null ? resultado.tipo.tr == null : tipo.tr.equals(resultado.tipo.tr)) {
                                    simbolo=new Simbolo(tipo,resultado.valor);
                                    ent.insertar(id, simbolo, linea, columna, "La variable");
                                }else{
                                    cError errora=new cError("Semantico","asignacion de tipos incompatibles "+tipo.tr+" no se puede convertir a "+valor.tipo.tr,linea,columna);
                                    ProyectCompi1.errores.add(errora);
                                }
                                error=false;
                                break;
                            case nulo:
                                simbolo=new Simbolo(tipo,null);
                                ent.insertar(id, simbolo, linea, columna, "La variable");
                                error=false;
                                break;
                        }
                        break;
                }
            }
            if (error) {
                cError errora=new cError("Semantico","'"+id+"' asignacion de tipos incompatibles "+tipo.tipo+" no se puede convertir a "+resultado.tipo.tipo,linea,columna);
                ProyectCompi1.errores.add(errora); 
            }
        }else{ 
            if (tipo.Dimension>-1) {
                ent.insertar(id, new Simbolo(tipo,null), linea, columna, "El arreglo");
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
                    case objeto:
                        ent.insertar(id, new Simbolo(tipo, null), linea, columna, "La variable");
                        break;
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
