/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.aritmeticas;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.Tipo;
import arbol.expresiones.Literal;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;
/**
 *
 * @author angel
 */
public class Aumento extends Expresion{
    
    String id;
    
    public Aumento(String id, int linea , int columna) {
        this.id=id;
        this.linea = linea;
        this.columna= columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        Simbolo sim = ent.buscar(id, linea, columna, "La variable"); 
        Expresion literal=new Literal(new Tipo(Tipo.EnumTipo.error),null);
        boolean error=true;
        if (sim != null) { 
            literal=new Literal(sim.tipo,sim.valor);
            switch (sim.tipo.tipo) { 
                case entero:
                    error=false;
                    sim.valor=Integer.parseInt(sim.valor.toString())+1;
                    break;
                case doble:
                    error=false;
                    sim.valor=Double.parseDouble(sim.valor.toString())+1;
                    break;
                case caracter:
                    error=false;
                    sim.valor=(int) sim.valor.toString().charAt(0)+1;
                    break;
            }
            if (error) {
                cError errora=new cError("Semantico","'"+id+"' tipo de dato incorrecto",linea,columna);
                ProyectCompi1.errores.add(errora);
            }
        } 
        return literal;
    }
    
}
