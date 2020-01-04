/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.logicas;

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
public class Or extends Expresion {

    Expresion izquierdo, derecho;

    public Or(Expresion izquierda, Expresion derecho, int linea, int columna) {
        this.izquierdo = izquierda;
        this.derecho=derecho;
        this.linea  = linea;
        this.columna =columna;
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
        Boolean resultado;
        
        switch (exp1.tipo.tipo) {
            case booleano:
                if (Boolean.parseBoolean(str1)) {
                    return new Literal (new Tipo (Tipo.EnumTipo.booleano), true);
                }
                switch (exp2.tipo.tipo) {
                    case booleano:
                        resultado = Boolean.parseBoolean(str1) || Boolean.parseBoolean(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), resultado.toString());
                }
        }
        cError errora=new cError("Semantico","No se puede comparar "+exp1.tipo.tipo+" entre "+exp2.tipo.tipo,linea,columna);
        ProyectCompi1.errores.add(errora);
        return new Literal(new Tipo(Tipo.EnumTipo.error) , "@Error@");
    }
    
}
