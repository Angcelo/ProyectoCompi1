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
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class CondicionSwitch extends Instruccion {

    LinkedList<Instruccion> bloque;
    Expresion valorCaso;
    boolean ejecutado;

    public CondicionSwitch( Expresion valor, LinkedList<Instruccion> bloque) {
        this.bloque = bloque;
        this.valorCaso = valor;
        this.ejecutado = false;
        this.Instruccion="case";
    }
    
    public CondicionSwitch( LinkedList<Instruccion> bloque) {
        this.bloque = bloque;
        this.valorCaso = null;
        this.ejecutado = false;
        this.Instruccion="default";
    }
    
    public CondicionSwitch(Expresion valor){
        this.bloque=new LinkedList();
        this.valorCaso=valor;
        this.ejecutado=false;
        this.Instruccion="case";
    }
    
    public CondicionSwitch() {
        this.bloque = new LinkedList();
        this.valorCaso = null;
        this.ejecutado = false;
        this.Instruccion="default";
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        for (Instruccion instruccion : bloque) {
            Object retorno = instruccion.ejecutar(ent);
            
            if (retorno != null ) {
                
                return retorno;
            }
        }
        return null;
    }

    public String graficar(String nonode,String siguiente,boolean ultimo) {
        String cadena="";
        char[] a=nonode.toCharArray();
            String nuevo=(Integer.parseInt(a[nonode.length()-1]+"")+1)+"";
            a[nonode.length()-1]=nuevo.charAt(0);
            String nonodes2=String.valueOf(a);
        boolean haybreak=false;
        for (int i = 0; i < bloque.size(); i++) {
            if (i==0) {
                cadena+="node"+nonode+i+"\n";
            }
            cadena +="node"+nonode+i+" [label=\""+bloque.get(i).Instruccion+"\",height=0.5];\n";
            if (bloque.get(i).Instruccion.equals("break")) {
                haybreak=true;
            }
            if ((i+1)<bloque.size()) {
                cadena += bloque.get(i).graficar(nonode+i,nonode+(i+1))+"\n";
            }else if(haybreak||ultimo){
                switch(bloque.get(i).Instruccion){
                    case "IfElse":
                    case "for":
                    case "switch":
                    case "while":
                    case "do":
                        cadena +=bloque.get(i).graficar(nonode+i,siguiente)+"\n";
                        break;
                    default:  
                        cadena+="node"+nonode+i+" -> node"+siguiente+"\n";
                        break;
                }
            }else{
                cadena+="node"+nonode+i+" -> node"+nonodes2 +"\n";
            }
        }
        if (bloque.isEmpty()) {
            cadena+="node"+nonodes2 +"\n";
        }
        return cadena;
    }

    @Override
    public String graficar(String nonode,String siguiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
