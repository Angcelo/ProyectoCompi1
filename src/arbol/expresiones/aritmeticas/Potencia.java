/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.aritmeticas;

import arbol.Expresion;
import arbol.entornos.Entorno;
import arbol.entornos.Tipo;
import arbol.expresiones.Literal;

/**
 *
 * @author angel
 */
public class Potencia extends Expresion {

    Expresion izquierda ;
    Expresion derecha;

    public Potencia(Expresion izquierda, Expresion derecho, int linea , int columna) {
        this.izquierda = izquierda;
        this.derecha=derecho;
        this.linea = linea;
        this.columna = columna;
    }
    @Override
    public Expresion getValor(Entorno ent) {
    
        Expresion exp1 = this.izquierda.getValor(ent);
        Expresion exp2 = this.derecha.getValor(ent);
        
        String str1 = exp1.valor.toString();
        String str2  = exp2.valor.toString();
        Literal literal=new Literal (new Tipo (Tipo.EnumTipo.error) , "@Error@") ;

        switch (exp1.tipo.tipo) {
            case entero:
                switch (exp2.tipo.tipo) {
                    case entero:
                        double powint =  (double) Math.pow(Double.parseDouble(str1), Double.parseDouble(str2));
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , powint) ;
                        break;
                    case doble :
                        double powdoble =  (double) Math.pow(Double.parseDouble(str1), Double.parseDouble(str2));
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , powdoble ) ;
                        break;
                    case caracter:
                        double powchar =  (double) Math.pow(Double.parseDouble(str1), (double) str2.charAt(0) );
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , powchar ) ;
                        break;
                }
                break;
            case doble:
                switch (exp2.tipo.tipo) {
                    case entero:
                        double powint =  (double) Math.pow(Double.parseDouble(str1), Double.parseDouble(str2));
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , powint) ;
                        break;
                    case doble :
                        double powdoble =  (double) Math.pow(Double.parseDouble(str1), Double.parseDouble(str2));
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , powdoble ) ;
                        break;
                    case caracter:
                        double powchar =  (double) Math.pow(Double.parseDouble(str1), (double) str2.charAt(0) );
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , powchar ) ;
                        break;
                }
                break;
            case caracter:
                switch (exp2.tipo.tipo) {
                    case entero:
                        double powint = (double) Math.pow((double) str1.charAt(0), Double.parseDouble(str2));
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , powint ) ;
                        break;
                    case doble :
                        double powdoble = (double) Math.pow((double) str1.charAt(0), Double.parseDouble(str2));
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , powdoble ) ;
                        break;
                    case caracter:
                        double powchar = (double) Math.pow((double) str1.charAt(0), Double.parseDouble(str2));
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , powchar ) ;
                        break;
                }
                break;
        }
        return literal;
    }
    
}
