/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectcompi1;

import arbol.AST;
import arbol.instrucciones.Clase;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author angel
 */
public class ProyectCompi1 {
    
    public static ArrayList<cError> errores;
    public static String Salida="";
    public static fmrPrincipal fmrP ;
    public static Stack<SwitchCiclo> pilaCiclos;
    public static Stack<MF> pilaMF;
    public static HashMap<String,Clase> L_clases;
    public static ArrayList<File> imagenes=new ArrayList();
    
    public static void main(String[] args) {
        L_clases=new HashMap();
        errores=new ArrayList();
        pilaCiclos = new Stack();
        pilaMF=new Stack();
        fmrP=new fmrPrincipal();
        fmrP.setVisible(true);  
    }
    
    public static enum SwitchCiclo{
        Switch, Ciclo
    }
    
    public static enum MF{
        funcion,metodo
    }
    
    public static boolean esVacia() {
        return !pilaCiclos.isEmpty();
    }
    
    public static boolean Nociclo(){
        return pilaCiclos.contains(SwitchCiclo.Ciclo);
    }
    
}
