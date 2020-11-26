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
public class EmployeeDepartment {
    
    private int clv_emp;
    private String desc_nombre;
    private String desc_apPaterno;
    private String desc_apMaterno;
    private String fec_nac;
    private String desc_departametno;
    private float cant_sueldo;

    public int getClv_emp() {
        return clv_emp;
    }

    public void setClv_emp(int clv_emp) {
        this.clv_emp = clv_emp;
    }

    public String getDesc_nombre() {
        return desc_nombre;
    }

    public void setDesc_nombre(String desc_nombre) {
        this.desc_nombre = desc_nombre;
    }

    public String getDesc_apPaterno() {
        return desc_apPaterno;
    }

    public void setDesc_apPaterno(String desc_apPaterno) {
        this.desc_apPaterno = desc_apPaterno;
    }

    public String getDesc_apMaterno() {
        return desc_apMaterno;
    }

    public void setDesc_apMaterno(String desc_apMaterno) {
        this.desc_apMaterno = desc_apMaterno;
    }

    public String getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(String fec_nac) {
        this.fec_nac = fec_nac;
    }

    public String getDesc_departametno() {
        return desc_departametno;
    }

    public void setDesc_departametno(String desc_departametno) {
        this.desc_departametno = desc_departametno;
    }

    public float getCant_sueldo() {
        return cant_sueldo;
    }

    public void setCant_sueldo(float cant_sueldo) {
        this.cant_sueldo = cant_sueldo;
    }
    
    public EmployeeDepartment (int clv_emp,
        String desc_nombre,
        String desc_apPaterno,
        String desc_apMaterno,
        String fec_nac,
        String desc_departametno,
        float cant_sueldo) {
        this.clv_emp = clv_emp;
        this.desc_nombre = desc_nombre;
        this.desc_apPaterno = desc_apPaterno;
        this.desc_apMaterno = desc_apMaterno;
        this.fec_nac = fec_nac;
        this.desc_departametno = desc_departametno;
        this.cant_sueldo = cant_sueldo;
    }
    
    public EmployeeDepartment(ResultSet rs) throws SQLException{
        try{
            this.clv_emp = rs.getInt("clv_emp");
            this.desc_nombre = rs.getString("desc_nombre");
            this.desc_apPaterno = rs.getString("desc_apPaterno");
            this.desc_apMaterno = rs.getString("desc_apMaterno");
            this.fec_nac = rs.getString("fec_nac");
            this.desc_departametno = rs.getString("desc_departametno");
            this.cant_sueldo = rs.getFloat("cant_sueldo");
        }catch(SQLException ex){
            System.out.println("Error:"+ex);
        }
    }
}
