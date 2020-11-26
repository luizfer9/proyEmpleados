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
public class Employee {
    
    private int Clave_Emp;
    private String Nombre;
    private String ApPaterno;
    private String ApMaterno;
    private String FecNac;
    private int Departamento;
    private String Sueldo;

    
    
    public Employee(int clv_emp, 
                    String desc_apPaterno,
                    String desc_apMaterno,
                    String fec_nac,
                    int clv_dep,
                    String num_sueldo){
        this.Clave_Emp = clv_emp;
        this.Nombre = desc_apPaterno;
        this.ApPaterno = desc_apMaterno;
        this.FecNac = fec_nac;
        this.Departamento = clv_dep;
        this.Sueldo = num_sueldo;
    }
    
    public Employee(ResultSet rs) throws SQLException{
        try{
            this.Clave_Emp = rs.getInt("Clave_Emp");
            this.Nombre = rs.getString("Nombre");
            this.ApPaterno = rs.getString("ApPaterno");
            this.ApMaterno = rs.getString("ApMaterno");
            this.FecNac = rs.getString("FecNac");
            this.Departamento = rs.getInt("Departamento");
            this.Sueldo = rs.getString("Sueldo");
        }catch(SQLException ex){
            System.out.println("Error:"+ex);
        }
    }
}
