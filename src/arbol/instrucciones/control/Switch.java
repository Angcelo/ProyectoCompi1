/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.control;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import arbol.entornos.Tipo;
import arbol.expresiones.comparacion.Igual;
import arbol.instrucciones.Break;
import arbol.instrucciones.Continue;
import proyectcompi1.ProyectCompi1;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class Switch extends Instruccion {

    Expresion valor;
    LinkedList <CondicionSwitch> condiciones;
    boolean ejecutado,salir;
    public Switch(Expresion valor, LinkedList<CondicionSwitch> condiciones) {
        
        this.valor = valor;
        this.condiciones = condiciones;
        this.ejecutado = false;
        this.salir=true;
        this.Instruccion="switch";
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        ProyectCompi1.pilaCiclos.push(ProyectCompi1.SwitchCiclo.Switch);
        Object retorno=null;
        for (CondicionSwitch condicion : this.condiciones) {
            if (condicion.valorCaso != null) {
                Igual igual = new Igual (this.valor, condicion.valorCaso);
                Expresion igual_ = igual.getValor(new Entorno ("swtich",ent,ent.Global));
                if (igual_.tipo.tipo == Tipo.EnumTipo.booleano) {
                    boolean blnIgual = Boolean.parseBoolean(igual_.valor.toString());
                    if (blnIgual || this.ejecutado) {
                        retorno = condicion.ejecutar(new Entorno("switch",ent,ent.Global));
                        if (retorno != null) {
                            if(retorno.getClass() == Break.class) {
                                this.ejecutado=false;
                                ProyectCompi1.pilaCiclos.pop();
                                return null;
                            }else if (retorno.getClass() == Continue.class) {
                                this.ejecutado=false;
                                ProyectCompi1.pilaCiclos.pop();
                                return retorno;
                            }else{
                                this.ejecutado=false;
                                ProyectCompi1.pilaCiclos.pop();
                                return retorno;
                            }
                        }
                        this.ejecutado  = true;
                    }
                }
            }else if (this.ejecutado)  {
                retorno = condicion.ejecutar(new Entorno("switch",ent,ent.Global));
                if (retorno != null) {
                    if (retorno.getClass() == Break.class) {
                        ProyectCompi1.pilaCiclos.pop();
                        return null;
                    }else if (retorno.getClass() == Continue.class) {
                        this.ejecutado=false;
                        ProyectCompi1.pilaCiclos.pop();
                        return retorno;
                    }else{
                        this.ejecutado=false;
                        ProyectCompi1.pilaCiclos.pop();
                        return retorno;
                    }   
                }
            }
        }
        if (!this.ejecutado) {
            for (CondicionSwitch condicion : this.condiciones) {
                if (condicion.valorCaso == null) {
                    this.ejecutado = true;
                    retorno = condicion.ejecutar(new Entorno("switch",ent,ent.Global));
                    if (retorno != null) {
                        if (retorno.getClass() == Break.class) {
                            this.ejecutado=false;
                            ProyectCompi1.pilaCiclos.pop();
                            return null;
                        }else if (retorno.getClass() == Continue.class) {
                            this.ejecutado=false;
                            ProyectCompi1.pilaCiclos.pop();
                            return retorno;
                        }else{
                            this.ejecutado=false;
                            ProyectCompi1.pilaCiclos.pop();
                            return retorno;
                        }    
                    } 
                }else if (this.ejecutado) {
                    retorno = condicion.ejecutar(new Entorno("switch",ent,ent.Global));
                    if (retorno != null) {
                        if (retorno.getClass() == Break.class) {
                            this.ejecutado=false;
                            ProyectCompi1.pilaCiclos.pop();
                            return null;
                        }else if (retorno.getClass() == Continue.class) {
                            this.ejecutado=false;
                            ProyectCompi1.pilaCiclos.pop();
                            return retorno;
                        }else{
                            this.ejecutado=false;
                            ProyectCompi1.pilaCiclos.pop();
                            return retorno;
                        }  
                    }
                }   
            }
        }
        this.ejecutado=false;
        ProyectCompi1.pilaCiclos.pop();
        return null;
    }

    @Override
    public String graficar(String nonode,String siguiente) {
        String bloque="";
        int i=0;
        String rank="{rank=same; ";
        char[] a=nonode.toCharArray();
            String nuevo=(Integer.parseInt(a[nonode.length()-1]+"")+1)+"";
            a[nonode.length()-1]=nuevo.charAt(0);
            String nonodes=String.valueOf(a);
        for (i = 0; i < condiciones.size(); i++) {
            bloque +="node"+nonode+" -> node"+nonode+i+"\n";
            bloque +="node"+nonode+i+" [label=\""+condiciones.get(i).Instruccion+"\",height=0.5];\n";
            rank+="node"+nonode+i+"; ";
            if ((i+1)<condiciones.size()) {    
                bloque +="node"+nonode+i+"->"+condiciones.get(i).graficar(nonode+i,siguiente,false);
            }else{
                bloque +="node"+nonode+i+"->"+condiciones.get(i).graficar(nonode+i,siguiente,true);
            }
        }
        if (condiciones.isEmpty()) {
            bloque += "node"+nonodes+"\n";
        }
        rank+=" }\n";
        bloque+=rank;
        return bloque;
    }
}
