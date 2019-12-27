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
public class Negativo extends Expresion{

    Expresion e;
 
    public Negativo(Expresion e, int linea, int columna){
        this.linea=linea;
        this.columna=columna;
        this.e=e;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion exp = this.e.getValor(ent);
        
        String str = exp.valor.toString();
        Literal literal= new Literal (new Tipo(Tipo.EnumTipo.error ) , "@Error@");        
        
        switch (exp.tipo.tipo) {
            case entero:
                int restaint = 0 - Integer.parseInt(str);
                literal = new Literal (new Tipo(Tipo.EnumTipo.entero) , restaint);
                break;
                
            case doble:
                double restadoble = 0.0 - Double.parseDouble(str);
                literal = new Literal (new Tipo (Tipo.EnumTipo.doble), restadoble);
                break;
                
            case caracter:
                int restachar = (int)0 - (int) str.charAt(0);
                literal = new Literal (new Tipo (Tipo.EnumTipo.entero), restachar) ;
                break;
                
        }
        return literal;  
    }
}
