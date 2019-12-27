/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entornos;

import arbol.instrucciones.Clase;

/**
 *
 * @author angel
 */
public class SimboloClase extends Simbolo{
    public Clase Bloque;
    public Entorno entClase;
    
    public SimboloClase(Tipo tipo, Object valor) {
        super(tipo, valor);
    }
}
