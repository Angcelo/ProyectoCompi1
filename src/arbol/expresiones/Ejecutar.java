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
public class Ejecutar extends Expresion{

    LinkedList<Expresion> ids;
    
    public Ejecutar(LinkedList<Expresion> ids,int columna,int linea){
        this.ids=ids;
        this.linea=linea;
        this.columna=columna;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        Entorno temp=ent;
        for (int i=(ids.size()-1); i>0;i--) {
            Simbolo sim = temp.buscar(((Id)ids.get(i)).getid(), linea, columna, "La variable");
            if(sim.tipo.tipo==Tipo.EnumTipo.clase){
                if (sim.tipo.tipo!=Tipo.EnumTipo.nulo) {
                    temp=((Instancia)sim.valor).cent;
                    temp.anterior=ent;
                }else{
                    cError errora=new cError("Semantico",((Id)ids.get(i)).getid()+"La clase no esta inicializada",linea,columna);
                    ProyectCompi1.errores.add(errora);
                    return null;
                }
            }else{
                cError errora=new cError("Semantico",((Id)ids.get(i)).getid()+" solo se puede referenciar una clase",linea,columna);
                ProyectCompi1.errores.add(errora);
                return new Literal(new Tipo(Tipo.EnumTipo.error),"Error");
            }
        } 
        return ids.get(0).getValor(ent);
    }
}
