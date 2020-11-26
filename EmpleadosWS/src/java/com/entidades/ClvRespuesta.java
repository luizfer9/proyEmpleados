/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author luisf
 */
public class ClvRespuesta {
    
    int clv_respuesta;
    
    public ClvRespuesta(){
    }
    
    public ClvRespuesta(ResultSet rs)throws SQLException{
        try{
            this.clv_respuesta  = rs.getInt("clv_respuesta");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public int getClv_respuesta(){
        return this.clv_respuesta;
    }
    
    public void setClv_respuesta(){
        this.clv_respuesta = clv_respuesta;
    }    
}
