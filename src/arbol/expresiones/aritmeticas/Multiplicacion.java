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
public class Multiplicacion extends Expresion {

    Expresion izquierda;
    Expresion derecha;

    public Multiplicacion(Expresion izquierda, Expresion derecho, int linea, int columna) {
        this.izquierda = izquierda;
        this.derecha=derecho;
        this.linea = linea;
        this.columna  = columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion exp1 = this.izquierda.getValor(ent);
        Expresion exp2 = this.derecha.getValor(ent);
        
        String str1=null,str2=null;
        if (exp1.valor!=null && exp2.valor!=null) {
            str1 = exp1.valor.toString();
            str2 = exp2.valor.toString();
        }
        Literal literal=new Literal (new Tipo (Tipo.EnumTipo.error) , "@@Error");

        switch (exp1.tipo.tipo) {
            case entero :
                switch (exp2.tipo.tipo) {
                    case entero :
                        int porint = (int)Double.parseDouble(str1) * (int)Double.parseDouble(str2);
                        literal= new Literal(new Tipo (Tipo.EnumTipo.entero) , porint);
                        break;
                    case doble :
                        Double pordoble = Integer.parseInt(str1) * Double.parseDouble(str2);
                        literal= new Literal(new Tipo (Tipo.EnumTipo.doble) , pordoble);
                        break;
                    case caracter :
                        int porchar = Integer.parseInt(str1) * (int) str2.charAt(0);
                        literal= new Literal(new Tipo (Tipo.EnumTipo.entero) , porchar);
                        break;
                }
                break;
            case doble :
                switch (exp2.tipo.tipo) {
                    case entero :
                        Double porint = Double.parseDouble(str1) * Double.parseDouble(str2);
                        literal= new Literal(new Tipo (Tipo.EnumTipo.doble) , porint);
                        break;
                    case doble :
                        Double pordoble = Double.parseDouble(str1) * Double.parseDouble(str2);
                        literal= new Literal(new Tipo (Tipo.EnumTipo.doble) , pordoble);
                        break;
                    case caracter :
                        Double porchar = Double.parseDouble(str1) * (int)str2.charAt(0);
                        literal= new Literal(new Tipo (Tipo.EnumTipo.doble) , porchar);
                        break;
                }
                break;
            case caracter :
                switch (exp2.tipo.tipo) {
                    case entero :
                        int porint = (int) str1.charAt(0) * Integer.parseInt(str2);
                        literal= new Literal (new Tipo (Tipo.EnumTipo.entero), porint);
                        break;
                    case doble :
                        Double pordoble = (int) str1.charAt(0) * Double.parseDouble(str2);
                        literal= new Literal (new Tipo (Tipo.EnumTipo.doble) , pordoble);
                        break;
                    case caracter :
                        int porchar = (int) str1.charAt(0) * (int) str2.charAt(0) ;
                        literal= new Literal (new Tipo (Tipo.EnumTipo.entero), porchar);
                        break;
                }
                break;
            
        }
        if (literal.tipo.tipo==Tipo.EnumTipo.error) {
            cError errora=new cError("Semantico","No se puede multiplicar "+exp1.tipo.tipo+" entre "+exp2.tipo.tipo,linea,columna);
            ProyectCompi1.errores.add(errora);
        }
        return literal; 
    }
    
}
