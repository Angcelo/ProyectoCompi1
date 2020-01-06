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
import java.util.LinkedList;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class Id extends Expresion {
    
    String id; 
    public LinkedList<Integer> busqueda;
    public Entorno anterior;

    public Id(String id ,int linea, int columna) {
        this.id = id;
        this.linea = linea;
        this.columna = columna;
        this.busqueda=null;
    }
    
    public Id(String id,LinkedList<Integer> busqueda,int linea,int columna){
        this.id = id;
        this.linea = linea;
        this.columna = columna;
        this.busqueda=busqueda;
    }
    
    public String getid(){
        return this.id;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        Simbolo sim = ent.buscar(id, linea, columna, "La variable");
        Literal literal;
        if (busqueda!=null) {
            if (sim.tipo.Dimension>-1) {
                Expresion e= ((Arreglos)sim.valor).buscar((Arreglos)sim.valor, busqueda, 0, linea, columna);
                e.tipo.Dimension=busqueda.size();
                return e;
            }else{
                cError errora=new cError("Semantico",id+": no es un arreglo"+tipo.tipo,linea,columna);
                ProyectCompi1.errores.add(errora); 
            }
        }
        if (sim != null){
            literal = new Literal (sim.tipo, sim.valor );
        }else{
            literal=new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@");
        }
        return literal;
    }
    
}
