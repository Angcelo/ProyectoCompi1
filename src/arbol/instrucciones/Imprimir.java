/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Tipo;
import proyectcompi1.ProyectCompi1;
/**
 *
 * @author angel
 */
public class Imprimir extends Instruccion{

    Expresion  valor;
    boolean salto;

    public Imprimir(Expresion valor, boolean salto) {
        this.valor = valor;
        this.salto = salto;
        this.Instruccion="Imprimir";
    }
    
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        Expresion resultado = valor.getValor(ent);
        String auxiliar = "";
        
        if (this.salto) {
            auxiliar = "\n";
        }
        
        if (resultado.tipo.tipo != Tipo.EnumTipo.error) {
            System.out.println(resultado.valor + auxiliar);
            ProyectCompi1.Salida += resultado.valor + auxiliar;
        }
        
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        return "node"+nonode+" -> "+"node"+siguiente;    
    }
    
    
    
}
