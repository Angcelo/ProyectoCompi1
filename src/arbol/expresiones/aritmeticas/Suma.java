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
public class Suma extends Expresion {

    Expresion izquierdo;
    Expresion derecho ;
    

    public Suma(Expresion izquierdo, Expresion derecho, int linea , int columna) {
        this.izquierdo = izquierdo;
        this.derecho=derecho;
        this.columna= columna;
        this.linea = linea;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion exp1 = this.izquierdo.getValor(ent);
        Expresion exp2 = this.derecho.getValor(ent);
        
        String strS1 = exp1.valor.toString();
        String strS2 = exp2.valor.toString();
        Literal literal= new Literal ( new Tipo (Tipo.EnumTipo.error) , "@Error@") ;
        
        switch (exp1.tipo.tipo) {
            
            case entero :
                switch (exp2.tipo.tipo) {
                    case entero:
                        int sumaint= Integer.parseInt(strS1) + Integer.parseInt(strS2);
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.entero) , sumaint);
                        break;
                    case doble:
                        Double sumadoble = Double.parseDouble(strS1) + Double.parseDouble(strS2);
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.doble) , sumadoble);
                        break;
                    case caracter:
                        int sumachar = Integer.parseInt(strS1) + (int) strS2.charAt(0);
                        literal = new Literal (new Tipo(Tipo.EnumTipo.entero), sumachar) ;
                        break;
                    case cadena:
                        String sumastr  = strS1 + strS2;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.cadena), sumastr);   
                        break;                 
                }
                break;
            case doble :                
                switch (exp2.tipo.tipo) {
                    case entero:    
                        Double sumaint= Double.parseDouble(strS1) + Double.parseDouble(strS2);
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.doble) , sumaint);
                        break;
                    case doble:
                        Double sumadoble = Double.parseDouble(strS1) + Double.parseDouble(strS2);
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.doble) , sumadoble);
                        break;
                    case caracter :
                        Double sumachar = Double.parseDouble(strS1) + (int) strS2.charAt(0);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , sumachar);
                        break;
                    case cadena:
                        String sumastr  = strS1 + strS2;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.cadena), sumastr);
                        break;
                }                
                break;
            case caracter:
                switch (exp2.tipo.tipo){
                    case entero :
                        int sumaint = (int) strS1.charAt(0) + Integer.parseInt(strS2) ;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.entero), sumaint) ;  
                        break;                  
                    case doble:
                        Double sumadoble =  (int) strS1.charAt(0) + Double.parseDouble(strS2) ;
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , sumadoble);   
                        break;                 
                    case caracter:
                        int sumachar  = (int) strS1.charAt(0) + (int) strS2.charAt(0) ;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.entero) , sumachar);  
                        break;                  
                    case cadena:
                        String sumastr = strS1 + strS2;
                        literal = new Literal (new Tipo (Tipo.EnumTipo.cadena) , sumastr);  
                        break;                      
                } 
                break;
            case cadena :                
                String sumastr = strS1 + strS2;                
                switch (exp2.tipo.tipo) {
                    case entero:                        
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.cadena) , sumastr );  
                        break;                  
                    case doble:                        
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.cadena) , sumastr); 
                        break;                   
                    case cadena:                        
                        literal = new Literal (new Tipo(Tipo.EnumTipo.cadena), sumastr);  
                        break;                  
                    case caracter:
                        literal = new Literal (new Tipo (Tipo.EnumTipo.cadena) , sumastr); 
                        break;                    
                    case booleano:
                        literal = new Literal (new Tipo(Tipo.EnumTipo.cadena) , sumastr);
                        break;
                }                
                break;            
            case booleano :
                switch (exp2.tipo.tipo) {
                    case cadena:
                        String sumabool = strS1 + strS2;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.cadena) , sumabool) ;
                        break;
                }
            
        }
        return literal;
    }

    
    
}
