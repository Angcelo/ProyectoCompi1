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
public class Modulo extends Expresion {

    Expresion izquierda;
    Expresion derecha;

    public Modulo(Expresion izquierda, Expresion derecho, int linea , int columna) {
        this.izquierda = izquierda;
        this.derecha=derecho;
        this.linea = linea;
        this.columna = columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion e1 = this.izquierda.getValor(ent);
        Expresion e2 = this.derecha.getValor(ent);
        
        String str1=null,str2=null;
        if (e1.valor!=null && e2.valor!=null) {
            str1 = e1.valor.toString();
            str2 = e2.valor.toString();
        }
        Literal literal=new Literal (new Tipo (Tipo.EnumTipo.error) , "@@Error");

        switch (e1.tipo.tipo) {
            case entero:
                switch (e2.tipo.tipo) {
                    case entero:
                        int modint  = (int)Double.parseDouble(str1) % (int)Double.parseDouble(str2);
                        literal=new Literal (new Tipo (Tipo.EnumTipo.entero), modint) ;
                        break;
                    case doble:
                        Double moddoble  = Integer.parseInt(str1) % Double.parseDouble(str2);
                        literal= new Literal (new Tipo (Tipo.EnumTipo.doble), moddoble) ;
                        break;
                    case caracter:
                        double modchar  = (double ) Double.parseDouble(str1) % (double) str2.charAt(0);
                        literal= new Literal (new Tipo (Tipo.EnumTipo.doble), modchar) ;
                        break;
                }
                break;
            case doble:
                switch (e2.tipo.tipo) {
                    case entero:
                        Double modint  = Double.parseDouble(str1) % Double.parseDouble(str2);
                        literal= new Literal (new Tipo (Tipo.EnumTipo.doble), modint) ;
                        break;
                    case doble:
                        double moddoble  = (double ) Double.parseDouble(str1) % (double) Double.parseDouble(str2);
                        literal= new Literal (new Tipo (Tipo.EnumTipo.doble), moddoble) ;
                        break;
                    case caracter:
                        double modchar  = (double ) Double.parseDouble(str1) % (double) str2.charAt(0);
                        literal= new Literal (new Tipo (Tipo.EnumTipo.doble), modchar) ;
                        break;
                }
                break;
            case caracter:
                switch (e2.tipo.tipo) {
                    case entero:
                        double modint = (double) str1.charAt(0) % (double) Double.parseDouble(str2);
                        literal= new Literal (new Tipo (Tipo.EnumTipo.doble), modint);
                        break;
                    case doble:
                        double moddoble = (double) str1.charAt(0) % (double) Double.parseDouble(str2);
                        literal= new Literal (new Tipo (Tipo.EnumTipo.doble), moddoble);
                        break;
                    case caracter:
                        double modchar = (double) str1.charAt(0) % (double) str2.charAt(0) ;
                        literal= new Literal (new Tipo (Tipo.EnumTipo.doble), modchar);
                        break;
                }
                break;
        }
        if (literal.tipo.tipo==Tipo.EnumTipo.error) {
            cError errora=new cError("Semantico","No se hacer modulo "+e1.tipo.tipo+" entre "+e2.tipo.tipo,linea,columna);
            ProyectCompi1.errores.add(errora);
        }
        return literal;
    }
    
}
