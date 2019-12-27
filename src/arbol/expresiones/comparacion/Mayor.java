/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.comparacion;

import arbol.Expresion;
import arbol.entornos.Entorno;
import arbol.entornos.Tipo;
import arbol.expresiones.Literal;

/**
 *
 * @author angel
 */
public class Mayor extends Expresion {
    
    Expresion izquierdo;
    Expresion derecho ;
    

    public Mayor(  Expresion izquierdo, Expresion derecho, int linea , int columna) {
        this.izquierdo = izquierdo;
        this.derecho=derecho;
        this.columna= columna;
        this.linea = linea;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion exp1 = this.izquierdo.getValor(ent);
        Expresion exp2 = this.derecho.getValor(ent);
        
        String str1 = exp1.valor.toString();
        String str2 = exp2.valor.toString();
        Literal literal= new Literal(new Tipo(Tipo.EnumTipo.error) , "@Error@");
        Boolean comp;
        
        switch (exp1.tipo.tipo) {
            case entero:
                switch (exp2.tipo.tipo) {
                    case entero:
                        comp = Integer.parseInt(str1) > Integer.parseInt(str2);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.booleano), comp.toString()) ;
                        break;
                    case doble:
                        comp = Double.parseDouble(str1) > Double.parseDouble(str2);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.booleano), comp.toString()) ;
                        break;
                    case caracter:
                        comp = Integer.parseInt(str1) > (int) str2.charAt(0);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.booleano), comp.toString()) ;
                        break;
                }
                break;
            case doble:
                switch (exp2.tipo.tipo) {
                    case entero:
                        comp = Integer.parseInt(str1) > Integer.parseInt(str2);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.booleano), comp.toString()) ;
                        break;
                    case doble:
                        comp = Double.parseDouble(str1) > Double.parseDouble(str2);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.booleano), comp.toString()) ;
                        break;
                    case caracter:
                        comp = Integer.parseInt(str1) > (int) str2.charAt(0);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.booleano), comp.toString()) ;
                        break;
                }
                
                break;
            case caracter:
                switch (exp2.tipo.tipo) {
                    case entero:
                        comp = (int) str1.charAt(0) > Integer.parseInt(str2);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.booleano), comp.toString()) ;
                        break;
                    case doble:
                        comp = (int) str1.charAt(0) > Double.parseDouble(str2);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.booleano), comp.toString()) ;
                        break;
                    case caracter:
                        comp = (int) str1.charAt(0) > (int) str2.charAt(0);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.booleano), comp.toString()) ;
                        break;
                }
                break;
        }
        return literal;        
    }
    
}