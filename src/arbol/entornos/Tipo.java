/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entornos;

/**
 *
 * @author angel
 */
public class Tipo {
    
    public EnumTipo tipo;
    public String tr;
    
    
    public Tipo (EnumTipo tipo) {
        this.tipo = tipo;
        
        this.tr = "";
    }
    
    public Tipo (EnumTipo tipo , String tr) {
        this.tipo = tipo;
        this.tr = tr;
    }
    
    public enum EnumTipo {
        entero, caracter, booleano, doble,cadena,vacio,objeto,clase,nulo,contructor,error
    }
}

