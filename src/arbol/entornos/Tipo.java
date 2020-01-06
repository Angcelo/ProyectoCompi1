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
    public int Dimension;
    
    
    public Tipo (EnumTipo tipo) {
        this.tipo = tipo;
        this.Dimension=-1;
        this.tr = "";
    }
    
    public Tipo (EnumTipo tipo , String tr,int Dimension) {
        this.tipo = tipo;
        this.Dimension=Dimension;
        this.tr = tr;
    }
    
    public enum EnumTipo {
        entero, caracter, booleano,doble,cadena,vacio,objeto,nulo,contructor,error
    }
}

