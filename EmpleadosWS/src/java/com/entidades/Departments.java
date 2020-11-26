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
public class Departments {
    
    private int clv_puesto;
    private String desc_descripcion;

    public Departments(int clv_puesto, 
                    String desc_descripcion){
        this.clv_puesto = clv_puesto;
        this.desc_descripcion = desc_descripcion;
    }
    
    public Departments(ResultSet rs) throws SQLException{
        try{
            this.clv_puesto = rs.getInt("Puesto");
            this.desc_descripcion= rs.getString("Descripcion");
        }catch(SQLException ex){
            System.out.println("Error:"+ex);
        }
    }
}
