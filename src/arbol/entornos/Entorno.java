/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entornos;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.instrucciones.Declaracion;
import arbol.instrucciones.control.Instrucciones;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public class Entorno {
    public Entorno anterior;
    public Entorno Global;
    public String nombre;
    public HashMap <String , Simbolo> tabla;
    public String archivo;
    
    public Entorno (String nombre,Entorno anterior,Entorno Global) {
        this.anterior = anterior;
        this.tabla = new HashMap<>();
        this.Global=Global;
    }
    
    public void insertar (String nombre, Simbolo sim, int linea, int columna, String cadenaError) {
        if (tabla.containsKey(nombre)) {
            cError error=new cError("Semantico",cadenaError + " '" + nombre + "' ya existe.",linea,columna);
            ProyectCompi1.errores.add(error);
        }else{
            tabla.put(nombre, sim);
        }
    }
    
    public void Constructor(){
        boolean constructor=false;
        for (Simbolo var : this.Global.tabla.values()) {
            if(var.tipo.tipo==Tipo.EnumTipo.contructor){
                constructor=true;
            }
        }
        if (!constructor) {
            SimboloMF nuevoc=new SimboloMF(new Tipo(Tipo.EnumTipo.contructor),nombre);
            nuevoc.bloque=new Instrucciones(new LinkedList());
            this.Global.insertar(this.Global.nombre+"$", nuevoc, 0, 0, "El constructor");
        }
    }
    
    public void Constructor(int linea,int columna){
        if (this.Global.tabla.containsKey(this.Global.nombre+"$")) {
            Simbolo sim=this.Global.tabla.get(this.Global.nombre+"$");
            Entorno cent=new Entorno("constructor",this,this.Global);
            ((SimboloMF)sim).getBloque().ejecutar(cent);    
        }else{
            cError error=new cError("Semantico","Parametros neceesarios para inicializar clase",linea,columna);
            ProyectCompi1.errores.add(error);
        }
    }
    
    public void Constructor(LinkedList<Expresion> param,int linea,int columna){
        String strcons=this.Global.nombre+"$";
        for(Expresion pa:param){
            strcons+=pa.tipo.tipo.toString();
        }
        if (this.Global.tabla.containsKey(strcons)) {
            Simbolo sim=this.Global.tabla.get(strcons);
            Entorno cent=new Entorno("constructor",this,this.Global);
            int iterador=0;
            for (Instruccion id:((SimboloMF)sim).getParametros()) {
                ((Declaracion)id).valor=param.get(iterador);
                id.ejecutar(cent);
                iterador++;
            }
            ((SimboloMF)sim).getBloque().ejecutar(cent);  
        }else{
            cError error=new cError("Semantico","Parametros incorrectos para inicializar clase",linea,columna);
            ProyectCompi1.errores.add(error);
        }
    }
    
    public void Constructor(LinkedList<Expresion> param){
        
    }
    
    public Simbolo buscar (String nombre, int linea, int columna, String cadenaError) {
        Simbolo sim=null;
        boolean NoVar=true;
        for (Entorno e = this; e != null; e = e.anterior) {
            if (e.tabla.containsKey(nombre)) {
                sim = e.tabla.get(nombre);
                NoVar=false;
                break;
            }
        }
        if (NoVar) {
            if (this.Global.tabla.containsKey(nombre)) {
                sim=this.Global.tabla.get(nombre);
            }else{
                cError error=new cError("Semantico",cadenaError + " '" + "' no existe",linea,columna);
                proyectcompi1.ProyectCompi1.errores.add(error);
            }
        }
        return sim;
    }
    
    public Simbolo buscar (String nombre, int linea, int columna) {
        Simbolo sim=null;
        if (this.tabla.containsKey(nombre)) {
            sim=this.tabla.get(nombre);
        }
        return sim;
    }
    
    public Simbolo buscarG(String nombre,int linea,int columna,String cadenaError){
        Simbolo sim=null;
        if (this.tabla.containsKey(nombre)) {
            sim=this.Global.tabla.get(nombre);
        }else{
            cError error=new cError("Semantico",cadenaError + " '" + "' no existe",linea,columna);
            proyectcompi1.ProyectCompi1.errores.add(error);
        }
        return sim;
    }
    
    public void GraficarEntorno(int linea,int columna) throws IOException, InterruptedException{
        File dot=new File("Entorno"+linea+columna+".dot");
        BufferedWriter bw = new BufferedWriter(new FileWriter(dot));  
        this.archivo="digraph matriz{ \n "
                + "rankdir=LR; \n"
                +"nodesep=.05;\n"
                + "node [shape=record,width=.1,height=.1];\n";
        Entorno temp=this;
        int iterador=0;
        while(temp!=null){
            this.archivo+="node"+iterador+" [label = \" ";
            temp.tabla.forEach((k,v) -> archivo+="Variable: " + k + ", tipo: " + v.tipo.tipo+ ", valor: "+v.valor+"|");
            this.archivo+="\",height=2.0];\n";
            if (temp.anterior!=null) {
                this.archivo+="node"+iterador+" -> "+"node"+(iterador+1)+"\n";
            }
            temp=temp.anterior;
            iterador++;
        }
        archivo+="}";
        bw.write(archivo);
        bw.close();
        String cmd = "dot -Tjpg Entorno"+linea+columna+".dot -o Entorno"+linea+columna+".jpg"; //Comando de apagado en linux
        Runtime.getRuntime().exec(cmd); 
        Thread.sleep(1000);
        File objetofile = new File ("Entorno"+linea+columna+".jpg");
        Desktop.getDesktop().open(objetofile);
    }  
}
