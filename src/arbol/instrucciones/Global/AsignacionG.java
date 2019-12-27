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
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class AsignacionG extends Instruccion {
    
    String id;
    Expresion valor;

    public AsignacionG(String id, int linea, int columna, Expresion valor) {
        this.id = id;
        this.valor = valor;
        this.columna = columna;
        this.linea = linea;
        this.Instruccion="Asignacion";
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Simbolo sim = ent.Global.buscarG(id, linea, columna, "La variable"); 
        boolean error=true;
        if (sim != null) { 

            Expresion resultado = valor.getValor(ent);

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
                cError errora=new cError("Semantico","'"+id+"' asignacion de tipos incompatibles "+sim.tipo.tipo+" no se puede convertir a "+resultado.tipo.tipo,linea,columna);
                ProyectCompi1.errores.add(errora);
            }
        } 
        return null;
    }

    @Override
    public String graficar(String nonode,String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;
    }
    
    
    
    
}
