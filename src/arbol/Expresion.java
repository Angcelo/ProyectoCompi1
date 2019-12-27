/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import arbol.entornos.Entorno;
import arbol.entornos.Tipo;

/**
 *
 * @author angel
 */
public abstract class Expresion extends Nodo {
    public Tipo tipo;
    public Object valor; 
    
    public abstract Expresion getValor(Entorno ent);
}
