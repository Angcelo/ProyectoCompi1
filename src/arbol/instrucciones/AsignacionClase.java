/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Simbolo;
import arbol.entornos.SimboloClase;
import arbol.entornos.SimboloMF;
import arbol.entornos.Tipo;
import arbol.expresiones.Id;
import arbol.expresiones.IdG;
import arbol.expresiones.Literal;
import java.util.LinkedList;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class AsignacionClase extends Instruccion{

    LinkedList<Expresion> ids;
    String clase;
    LinkedList<Expresion> parametros;
    
    public AsignacionClase(LinkedList<Expresion> ids, String clase, LinkedList<Expresion> parametros,int linea,int columna) {
        this.ids = ids;
        this.clase = clase;
        this.parametros = parametros;
        this.linea=linea;
        this.columna=columna;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno temp=ent;
        for (int i=(ids.size()-1); i>0;i--) {
            if (ids.get(i).getClass()==Id.class) {
                Simbolo sim = temp.buscar(((Id)ids.get(i)).getid(), linea, columna, "La variable");
                if(sim.getClass()==SimboloClase.class){
                    if (sim.tipo.tipo!=Tipo.EnumTipo.nulo) {
                        temp=((SimboloClase)sim).entClase;
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
            }else if(ids.get(i).getClass()==IdG.class){
                Simbolo sim = temp.Global.buscar(((IdG)ids.get(i)).getid(), linea, columna, "La variable");
                if(sim.getClass()==SimboloClase.class){
                    if (sim.tipo.tipo!=Tipo.EnumTipo.nulo) {
                        temp=((SimboloClase)sim).entClase;
                    }else{
                        cError errora=new cError("Semantico",((IdG)ids.get(i)).getid()+"La clase no esta inicializada",linea,columna);
                        ProyectCompi1.errores.add(errora);
                        return null;
                    }
                }else{
                    cError errora=new cError("Semantico",((IdG)ids.get(i)).getid()+" solo se puede referenciar una clase",linea,columna);
                    ProyectCompi1.errores.add(errora);
                    return new Literal(new Tipo(Tipo.EnumTipo.error),"Error");
                }
            }
        }
        SimboloClase sim=null;
        if (ids.get(0).getClass()==Id.class) {
           sim=(SimboloClase)temp.Global.buscar(((Id)ids.get(0)).getid(), linea, columna, "La clase"); 
        }else if(ids.get(0).getClass()==IdG.class){
           sim=(SimboloClase)temp.Global.buscar(((IdG)ids.get(0)).getid(), linea, columna, "La clase"); 
        }
        if (sim!=null && (sim.tipo.tipo==Tipo.EnumTipo.nulo || sim.tipo.tipo==Tipo.EnumTipo.clase) && sim.valor.equals(this.clase)) {
            sim.entClase=new Entorno(((Id)ids.get(0)).getid(),ent,null);
            sim.entClase.Global=sim.entClase;
            if (parametros!=null) {
                LinkedList<Expresion> parametros_=new LinkedList();
                for (Expresion parametro : parametros) {
                    parametros_.add(parametro.getValor(ent));
                }
                sim.Bloque.param=parametros_;
            }
            sim.Bloque.ejecutar(sim.entClase);
            if (parametros==null) {
                sim.entClase.Constructor(linea,columna);
            }else{
                sim.entClase.Constructor(parametros,linea,columna);
            }
            sim.tipo=new Tipo(Tipo.EnumTipo.clase,clase);
        }else{
            cError error=new cError("Semantico",((Id)ids.get(0)).getid()+": no es posible asignar la clase",linea,columna);
            proyectcompi1.ProyectCompi1.errores.add(error);
        }
        return null;
                
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
