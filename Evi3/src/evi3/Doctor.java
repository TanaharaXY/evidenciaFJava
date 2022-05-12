/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evi3;

import java.io.Serializable;

/**
 *
 * @author Fredo
 */
public class Doctor implements Serializable{
   private String id;
    private String nombre;
    private String especialidad;
    
    public Doctor(){
    
    }
    
    public Doctor(String id, String nombre, String especialidad){
        this.id= id;
        this.nombre =nombre;
    this.especialidad =especialidad;
    }
    public String getId(){
    return id;
    }
    
    public void setId(String id){
        this.id=id;
    
    }
    public String getNombre(){
    return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getEspecialidad(){
    return especialidad;
    }
    
    public void setEspecialidad(String especialidad){
        this.especialidad=especialidad;
    
    }
    @Override
    public String toString(){
    return "Paciente{"
            + "id=" +id+
            "nombre=" + nombre
            + ", especialidad =" +especialidad +"}";
    
    }
    
}