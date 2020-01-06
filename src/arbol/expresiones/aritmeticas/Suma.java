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
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

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
        
        String str1=null,str2=null;
        if (exp1.valor!=null && exp2.valor!=null) {
            str1 = exp1.valor.toString();
            str2 = exp2.valor.toString();
        }
        Literal literal= new Literal ( new Tipo (Tipo.EnumTipo.error) , "@Error@") ;
        
        switch (exp1.tipo.tipo) {
            
            case entero :
                switch (exp2.tipo.tipo) {
                    case entero:
                        int sumaint= (int)Double.parseDouble(str1) + (int)Double.parseDouble(str2);
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.entero) , sumaint);
                        break;
                    case doble:
                        Double sumadoble = Double.parseDouble(str1) + Double.parseDouble(str2);
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.doble) , sumadoble);
                        break;
                    case caracter:
                        int sumachar = Integer.parseInt(str1) + (int) str2.charAt(0);
                        literal = new Literal (new Tipo(Tipo.EnumTipo.entero), sumachar) ;
                        break;
                    case cadena:
                        String sumastr  = str1 + str2;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.cadena), sumastr);   
                        break;                 
                }
                break;
            case doble :                
                switch (exp2.tipo.tipo) {
                    case entero:    
                        Double sumaint= Double.parseDouble(str1) + Double.parseDouble(str2);
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.doble) , sumaint);
                        break;
                    case doble:
                        Double sumadoble = Double.parseDouble(str1) + Double.parseDouble(str2);
                        literal = new Literal ( new Tipo (Tipo.EnumTipo.doble) , sumadoble);
                        break;
                    case caracter :
                        Double sumachar = Double.parseDouble(str1) + (int) str2.charAt(0);
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , sumachar);
                        break;
                    case cadena:
                        String sumastr  = str1 + str2;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.cadena), sumastr);
                        break;
                }                
                break;
            case caracter:
                switch (exp2.tipo.tipo){
                    case entero :
                        int sumaint = (int) str1.charAt(0) + Integer.parseInt(str2) ;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.entero), sumaint) ;  
                        break;                  
                    case doble:
                        Double sumadoble =  (int) str1.charAt(0) + Double.parseDouble(str2) ;
                        literal = new Literal (new Tipo (Tipo.EnumTipo.doble) , sumadoble);   
                        break;                 
                    case caracter:
                        int sumachar  = (int) str1.charAt(0) + (int) str2.charAt(0) ;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.entero) , sumachar);  
                        break;                  
                    case cadena:
                        String sumastr = str1 + str2;
                        literal = new Literal (new Tipo (Tipo.EnumTipo.cadena) , sumastr);  
                        break;                      
                } 
                break;
            case cadena :                
                String sumastr = str1 + str2;                
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
                    case objeto:
                        literal = new Literal (new Tipo(Tipo.EnumTipo.cadena) , sumastr);
                        break;
                }                
                break;            
            case booleano :
                switch (exp2.tipo.tipo) {
                    case cadena:
                        String sumabool = str1 + str2;
                        literal = new Literal (new Tipo(Tipo.EnumTipo.cadena) , sumabool) ;
                        break;
                }
            
        }
        if (literal.tipo.tipo==Tipo.EnumTipo.error) {
            cError errora=new cError("Semantico","No se puede sumar "+exp1.tipo.tipo+" entre "+exp2.tipo.tipo,linea,columna);
            ProyectCompi1.errores.add(errora);
        }
        return literal;
    }

    
    
}
