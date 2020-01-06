/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.entornos.Entorno;
import arbol.entornos.Tipo;
import java.util.LinkedList;
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;

/**
 *
 * @author angel
 */
public final class Arreglos extends Expresion{
    public LinkedList<Expresion> n;
    public int dimension=0;
    public int tamaño;
    
    public Arreglos(LinkedList<Expresion> e){
        this.n=e;
        this.dimension=1;
        this.tamaño=e.size();
        this.tipo=null;
    }
    
    public Arreglos(Tipo tipo,LinkedList<Integer> dimensiones){
        this.tipo=tipo;
        this.tipo.Dimension=dimensiones.size();
        this.valor=Crear(tipo,dimensiones.size(),dimensiones);
    }
    
    public Arreglos(){
        this.n=new LinkedList();
        this.tipo=null;
    }
    
    public  void agregar(){
        this.tamaño++;
    }
    
    public void Dimension(){
        if (!n.isEmpty()) {
            this.dimension=((Arreglos)this.n.get(0)).dimension+1;
        }
        this.agregar();
    }
    
    public Arreglos Crear(Tipo tipo,int dimension,LinkedList<Integer> dimensiones){
        int numero=dimensiones.size()-dimension;
        if (dimension!=1) {
            for (int i = 0; i < dimensiones.get(numero); i++) {
                this.n.add(Crear2(tipo,dimension-1,dimensiones));
            }
            this.dimension=((Arreglos)this.n.get(0)).dimension+1;
            this.tamaño=dimensiones.get(numero);
        }else{
            this.n=new LinkedList();
            this.dimension=1;
            Literal l=null;
            switch (tipo.tipo) {
                case entero:
                    l=new Literal(tipo,0);
                    break;
                case caracter:
                    l=new Literal(tipo,'\0');
                    break;
                case booleano:
                    l=new Literal(tipo,false);
                    break;
                case doble:
                    l=new Literal(tipo,0.0);
                    break;
                case cadena:
                    l=new Literal(tipo,"");
                    break;
                case objeto:
                    l=new Literal(tipo,null);
                    break;
            }
            for (int i = 0; i < dimensiones.get(numero); i++){
                this.n.add(i,l);   
            }
        }
        return this;
    }
    
    public Arreglos Crear2(Tipo tipo,int dimension,LinkedList<Integer> dimensiones){
        Arreglos n=new Arreglos();
        int numero=dimensiones.size()-dimension;
        if (dimension!=1) {
            for (int i = 0; i < dimensiones.get(numero); i++) {
                n.n.add(Crear2(tipo,dimension-1,dimensiones));
            }
            n.dimension=((Arreglos)this.n.get(0)).dimension+1;
            n.tamaño=dimensiones.get(numero);
        }else{
            n.n=new LinkedList();
            n.dimension=1;
            Literal l=null;
            switch (tipo.tipo) {
                case entero:
                    l=new Literal(tipo,0);
                    break;
                case caracter:
                    l=new Literal(tipo,'\0');
                    break;
                case booleano:
                    l=new Literal(tipo,false);
                    break;
                case doble:
                    l=new Literal(tipo,0.0);
                    break;
                case cadena:
                    l=new Literal(tipo,"");
                    break;
                case objeto:
                    l=new Literal(tipo,null);
                    break;
            }
            for (int i = 0; i < dimensiones.get(numero); i++){
                this.n.add(i,l);   
            }
        }
        return n;
    }
    
    public void Ejecutar(Expresion e,Entorno ent){
        if (((Arreglos)e).dimension!=1 && ((Arreglos)e).n!=null) {
            int Dimension=0;
            for (Expresion e_:((Arreglos)e).n) {
                Ejecutar(e_,ent);
                if (e.tipo==null) {
                    e.tipo=new Tipo(e_.tipo.tipo,"Arreglo",((Arreglos)e).dimension);
                }else if(e.tipo.tipo!=e_.tipo.tipo){
                    e.tipo.tipo=Tipo.EnumTipo.error;
                    cError errora=new cError("Semantico","Error de tipos entre"+e.tipo.tipo+" y "+e_.tipo.tipo,linea,columna);
                    ProyectCompi1.errores.add(errora); 
                }
                if (Dimension==0) {
                    Dimension=((Arreglos)e_).dimension;
                }else if(Dimension!=((Arreglos)e_).dimension){
                    e.tipo.tipo=Tipo.EnumTipo.error;
                    cError errora=new cError("Semantico","Error en las dimensiones del arreglo"+tipo.tipo,linea,columna);
                    ProyectCompi1.errores.add(errora); 
                }
            }
        }else if(((Arreglos)e).n!=null){
            LinkedList<Expresion> temp=new LinkedList<>();
            for (Expresion e_:((Arreglos)e).n) {
                Expresion temp2=(e_.getValor(ent));
                if (e.tipo==null) {
                    String arreglo="Arreglo";
                    if (!temp2.tipo.tr.equals("")){
                        arreglo=temp2.tipo.tr;
                    }
                    e.tipo=new Tipo(temp2.tipo.tipo,arreglo,((Arreglos)e).dimension);
                }else if(e.tipo.tipo!=temp2.tipo.tipo){
                    e.tipo.tipo=Tipo.EnumTipo.error;
                    cError errora=new cError("Semantico","Error de tipos entre"+e.tipo.tipo+" y "+temp2.tipo.tipo,linea,columna);
                    ProyectCompi1.errores.add(errora); 
                }
                temp.add(temp2);
            }
            ((Arreglos)e).n=temp;
        }
    }
    
    public Expresion buscar(Arreglos e,LinkedList<Integer> numeros,int numero,int linea,int columna){
        if (numero<numeros.size()-1) {
            try{
                return buscar((Arreglos)e.n.get(numeros.get(numero)),numeros,numero+1,linea,columna);
            }catch(Exception ex){
                cError errora=new cError("Semantico","Error en al busqueda del arreglo"+tipo.tipo,linea,columna);
                ProyectCompi1.errores.add(errora); 
                return (new Literal(new Tipo(Tipo.EnumTipo.error),null));
            }
        }else{
            try{
                return e.n.get(numeros.get(numero)); 
            }catch(Exception ex){
                cError errora=new cError("Semantico","Error en al busqueda del arreglo"+tipo.tipo,linea,columna);
                ProyectCompi1.errores.add(errora); 
                return (new Literal(new Tipo(Tipo.EnumTipo.error),null));
            }
        }
    }
    
    public void Reemplazar(Arreglos e,LinkedList<Integer> numeros,Expresion valor,int numero,int linea,int columna){
        if (numero<numeros.size()-1) {
            try{
                buscar((Arreglos)e.n.get(numeros.get(numero)),numeros,numero+1,linea,columna);
            }catch(Exception ex){
                cError errora=new cError("Semantico","Error en al busqueda del arreglo"+tipo.tipo,linea,columna);
                ProyectCompi1.errores.add(errora); 
            }
        }else{
            if (e.n.get(numero).getClass()!=Arreglos.class) {
                Expresion e_=e.n.get(numeros.get(numero));
                if (e_.tipo.tipo!=valor.tipo.tipo) {
                    cError errora=new cError("Semantico","asignacion de tipos incompatibles "+e.n.get(numeros.get(numero)).tipo.tipo+" no se puede convertir a "+valor.tipo.tipo,linea,columna);
                    ProyectCompi1.errores.add(errora);
                }else{
                    e.n.get(numeros.get(numero)).valor=valor.valor; 
                } 
            }else{
               cError errora=new cError("Semantico","Error en al busqueda del arreglo"+tipo.tipo,linea,columna);
               ProyectCompi1.errores.add(errora); 
            }
        }
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        Ejecutar(this,ent);
        Literal l=new Literal(this.tipo,this);
        return l;
    }
}
