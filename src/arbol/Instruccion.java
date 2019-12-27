/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import arbol.entornos.Entorno;

/**
 *
 * @author angel
 */
public abstract class Instruccion extends Nodo {
    public abstract Object ejecutar(Entorno ent);
    public abstract String graficar(String nonode,String siguiente);
}
