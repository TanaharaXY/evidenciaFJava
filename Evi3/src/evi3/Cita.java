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
public class Cita implements Serializable{
    
    private String id;
    private String fecha;
    private String hora;
    private String motivo;
    
    public Cita(){}
    
    public Cita(String id, String fecha,String hora, String motivo){
        this.id= id;
        this.fecha =fecha;
        this.hora =hora;
        this.motivo =motivo;
    
    }
    
    public String getId(){
    return id;
    }
    
    public void setId(String id){
        this.id=id;
    
    }
    
    public String getFecha(){
    return fecha;
    }
    
    public void setFecha(String fecha){
        this.fecha=fecha;
    
    }
    
    public String getHora(){
    return hora;
    }
    
    public void setHora(String hora){
        this.hora=hora;
    
    }
    
    public String getMotivo(){
    return motivo;
    }
    
    public void setMotivo(String motivo){
        this.motivo=motivo;
    
    }
    
    @Override
    public String toString(){
    return "Cita{"
            + "id=" +id
            + ", fecha =" +fecha +
            ", hora =" +hora +
            ", motivo =" +motivo + "}";
    
    }
    
    
}
