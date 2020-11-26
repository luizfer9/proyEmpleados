/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllador;

import com.dao.DirectorioDAO;
import com.entidades.Employee;
import com.entidades.Departments;
import com.entidades.EmployeeDepartment;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author luisf
 */
public class DirectorioController {

    public String obtenerEmpleados(int num_opcion)
    throws SQLException,IOException,ClassNotFoundException, Exception{
        String respuesta = null;
        List<Employee> listEmp = null;
        Gson gson = new Gson();
        try {
            DirectorioDAO directorioDAO = new DirectorioDAO();
            listEmp = directorioDAO.obtenerEmpleados(num_opcion);
            respuesta = gson.toJson(listEmp);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
    
    public String obtenerDepartamentos(int num_opcion)
    throws SQLException,IOException,ClassNotFoundException, Exception{
        String respuesta = null;
        List<Departments> listDep = null;
        Gson gson = new Gson();
        try {
            DirectorioDAO directorioDAO = new DirectorioDAO();
            listDep = directorioDAO.obtenerDepartamentos(num_opcion);
            respuesta = gson.toJson(listDep);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
    
    public String insOModEmpleadoDepartamento(
        int num_opcion,
        int Clave_Emp,
        String nombre,
        String ApPaterno,
        String ApMaterno,
        String FecNac,
        int Departamento,
        String Sueldo)
    throws SQLException,IOException,ClassNotFoundException, Exception{
        int clv_respuesta = 0;
        String respuesta = "{\"clv_respuesta\":\""+clv_respuesta+"\"}";
        Gson gson = new Gson();
        try {
            DirectorioDAO directorioDAO = new DirectorioDAO();
            clv_respuesta = directorioDAO.insOModEmpleadoDepartamento(
            num_opcion,
            Clave_Emp,
            nombre,
            ApPaterno,
            ApMaterno,
            FecNac,
            Departamento,
            Sueldo);
            respuesta = "{\"clv_respuesta\":\""+clv_respuesta+"\"}";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
    
    public String eliminaEmpleado(int num_opcion,int Clave_Emp)
    throws SQLException,IOException,ClassNotFoundException, Exception{
        int clv_respuesta = 0;
        String respuesta = "{\"clv_respuesta\":\""+clv_respuesta+"\"}";
        Gson gson = new Gson();
        try {
            DirectorioDAO directorioDAO = new DirectorioDAO();
            clv_respuesta = directorioDAO.eliminaEmpleado(num_opcion,Clave_Emp);
            respuesta = "{\"clv_respuesta\":\""+clv_respuesta+"\"}";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
}
