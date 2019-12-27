/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.SimboloMF;
import arbol.entornos.Tipo;
import arbol.instrucciones.Declaracion;
import java.util.LinkedList;
import proyectcompi1.ProyectCompi1;

/**
 *
 * @author angel
 */
public class ExpresionMF extends Expresion{

    public String id;
    public LinkedList<Expresion> argumentos;
    
    public ExpresionMF(String id,LinkedList<Expresion> argumentos,int linea,int columna){
        this.linea=linea;
        this.columna=columna;
        this.id=id;
        this.argumentos=argumentos;
    }
    
    public ExpresionMF(String id,int linea,int columna){
        this.linea=linea;
        this.columna=columna;
        this.id=id;
        this.argumentos=null;
    }
    
    
    @Override
    public Expresion getValor(Entorno ent) {
        Object retorno=null;
        LinkedList<Expresion> argumentos_=new LinkedList();
        Entorno entMF=new Entorno(id,ent,ent.Global);
        String id_=id+"#";
        if (argumentos!=null) {
            argumentos.forEach((argumento) -> {
                argumentos_.add(argumento.getValor(ent));
            });
            id_ = argumentos_.stream().map((argumento) -> argumento.tipo.tipo.toString()).reduce(id_, String::concat);
        }
        Simbolo sim=ent.buscar(id_, linea, columna, "El metodo o funcion");
        if (sim!=null) {
            if(sim.tipo==null){
                ProyectCompi1.pilaMF.push(ProyectCompi1.MF.metodo);
            }else if(sim.tipo.tipo==Tipo.EnumTipo.vacio){
                ProyectCompi1.pilaMF.push(ProyectCompi1.MF.metodo);
            }else{
                ProyectCompi1.pilaMF.push(ProyectCompi1.MF.funcion);
            }
            if (argumentos!=null && ((SimboloMF)sim).getParametros()!=null) {
                int iterador=0;
                for (Instruccion parametro:((SimboloMF)sim).getParametros()) {
                    ((Declaracion)parametro).valor=argumentos_.get(iterador);
                    ((Declaracion)parametro).ejecutar(entMF);
                    iterador++;
                }
            }
            retorno=((SimboloMF)sim).getBloque().ejecutar(entMF);
            if (retorno!=null) {
                return (Literal)retorno;
            }
        }
        return null;
    }
    
}
