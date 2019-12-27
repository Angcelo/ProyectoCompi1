/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.AST;
import arbol.Instruccion;
import arbol.entornos.Entorno;
import java.io.File;
import java.io.FileInputStream;
import proyectcompi1.cError;
import proyectcompi1.ProyectCompi1;

/**
 *
 * @author angel
 */
public class Importar extends Instruccion{

    String path;

    public Importar(String path,int linea,int columna) {
        this.path = path;
        this.linea=linea;
        this.columna=columna;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        analizadores.parser pars;
        File ftemp=new File(path);
        if (ftemp.exists()) {
            AST arbol;
            try {
                pars = new analizadores.parser(new analizadores.Lexico(new FileInputStream(path)));
                pars.parse();
                arbol = pars.AST;
                if (arbol != null) { 
                    arbol.Ejecutar();
                    ProyectCompi1.L_clases.put(((Clase)arbol.Clase).nombre, (Clase)arbol.Clase);
                    Entorno temp=ent;
                    while(temp.anterior!=null){
                        temp=temp.anterior;
                    }
                    temp.anterior=arbol.getEntorno();
                } else {
                    cError errora=new cError("Ejecucion","<----------> Existió un error en el análisis, no se pudo construir el árbol <---------->",linea,columna);
                    ProyectCompi1.errores.add(errora); 
                }
            } catch (Exception ex) {
                cError errora=new cError("Ejecucion","Error fatal en compilación de entrada: "+path,linea,columna);
                ProyectCompi1.errores.add(errora); 
            }   
        }else{
            cError errora=new cError("Ejecucion","No se encontro el archivo: "+path,linea,columna);
            ProyectCompi1.errores.add(errora); 
        }
        
        return null;
    }

    @Override
    public String graficar(String nonode, String siguiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
