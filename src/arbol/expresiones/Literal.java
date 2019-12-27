/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.entornos.Entorno;
import arbol.entornos.Tipo;

/**
 *
 * @author angel
 */

public class Literal extends Expresion {
    
    public Literal (Tipo tipo, Object valor) {
        this.tipo = tipo;
        this.valor  = valor;
    }

    @Override
    public Expresion getValor(Entorno ent) {
    
        return new Literal (tipo, valor);
        
    }
}
