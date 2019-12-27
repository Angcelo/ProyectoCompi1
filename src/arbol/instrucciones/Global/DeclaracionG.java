/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.Global;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.Tipo;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class DeclaracionG extends Instruccion {

    public Tipo tipo;
    public String id;
    public String id2;
    public Expresion valor;

    public DeclaracionG(Tipo tipo, String id, Expresion valor, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.id2="";
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
        this.Instruccion="declaracion";
    }
    
    public DeclaracionG(Tipo tipo, String id, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.id2="";
        this.valor = null;
        this.linea = linea;
        this.columna = columna;
        this.Instruccion="declaracion";
    }
    
    public DeclaracionG(Tipo tipo,String id1,String id2,int linea,int columna){
        this.tipo = tipo;
        this.id = id1;
        this.id2=id2;
        this.valor = null;
        this.linea = linea;
        this.columna = columna;
        this.Instruccion="declaracion";
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        if (valor != null) { 
            Expresion resultado = valor.getValor(ent);
            Simbolo simbolo;
            boolean error=true;
            switch (tipo.tipo) { 
                case entero:
                    switch (resultado.tipo.tipo) {
                        case entero:
                            simbolo = new Simbolo(tipo, (int)Double.parseDouble(resultado.valor.toString()));
                            ent.Global.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            simbolo = new Simbolo(tipo, ascii);
                            ent.Global.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
                case doble:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            simbolo = new Simbolo(tipo, ascii);
                            ent.Global.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                        case entero:
                        case doble:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.Global.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
                case caracter:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.Global.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
                case booleano:
                    switch (resultado.tipo.tipo) {
                        case booleano:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.Global.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
                case cadena:
                    switch (resultado.tipo.tipo) {
                        case cadena:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.Global.insertar(id, simbolo, linea, columna, "La variable");
                            error=false;
                            break;
                    }
                    break;
            }
            if (error) {
                cError errora=new cError("Semantico","'"+id+"' asignacion de tipos incompatibles "+tipo.tipo+" no se puede convertir a "+resultado.tipo.tipo,linea,columna);
                ProyectCompi1.errores.add(errora); 
            }
        } else if(this.id2.equals("")){
            
        }else{ 
            switch (tipo.tipo) {
                case entero:
                    ent.Global.insertar(id, new Simbolo(tipo, 0), linea, columna, "La variable");
                    break;
                case caracter:
                    ent.Global.insertar(id, new Simbolo(tipo, '\0'), linea, columna, "La variable");
                    break;
                case booleano:
                    ent.Global.insertar(id, new Simbolo(tipo, false), linea, columna, "La variable");
                    break;
                case doble:
                    ent.Global.insertar(id, new Simbolo(tipo, 0.0), linea, columna, "La variable");
                    break;
                case cadena:
                    ent.Global.insertar(id, new Simbolo(tipo, ""), linea, columna, "La variable");
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
