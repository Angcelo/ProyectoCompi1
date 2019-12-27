/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.aritmeticas;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;

/**
 *
 * @author angel
 */
public class Decremento extends Expresion{

    Instruccion ins;
    Expresion id;
    
    public Decremento(Instruccion ins,Expresion id, int linea , int columna) {
        this.ins=ins;
        this.id=id;
        this.linea = linea;
        this.columna= columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion literal=id.getValor(ent);
        ins.ejecutar(ent);
        return literal;
    }
    
}
