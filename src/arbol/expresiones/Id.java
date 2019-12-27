/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.Tipo;

/**
 *
 * @author angel
 */
public class Id extends Expresion {
    
    String id; 

    public Id(String id ,int linea, int columna) {
        this.id = id;
        this.linea = linea;
        this.columna = columna;
    }
    
    public String getid(){
        return this.id;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        Simbolo sim = ent.buscar(id, linea, columna, "La variable");
        Literal literal;
        if (sim != null){
            Literal retorno = new Literal (sim.tipo, sim.valor );
            literal= retorno;
        }else{
            literal=new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@");
        }
        return literal;
    }
    
}
