/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entidades.Employee;
import com.entidades.Departments;
import com.entidades.EmployeeDepartment;
import com.manejador.ConstantesSQL;
import com.manejador.SqldbConnectionFactory;
import com.entidades.ClvRespuesta;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author luisf
 */
public class DirectorioDAO {
    
    CallableStatement procStmt = null;
    ResultSet rsResult = null;
    ConstantesSQL con_sql = new ConstantesSQL();
    
    public List<Employee> obtenerEmpleados(int num_opcion) 
    throws IOException, ClassNotFoundException, Exception{
        
       Connection connSqlDb = null;
        procStmt = null;
        rsResult = null; 
        List<Employee> listEmpleados = null;
        try {
            
            String sentencia = null;
            sentencia = con_sql.CONSULTA_EMPLEADOS;
            connSqlDb = SqldbConnectionFactory.getInstance().getConnection();
            procStmt = connSqlDb.prepareCall(sentencia);
            procStmt.setInt(1,num_opcion);
            procStmt.setInt(2,1);
            procStmt.setString(3, "b");
            procStmt.setString(4, "c");
            procStmt.setString(5, "d");
            procStmt.setString(6, "e");
            procStmt.setInt(7, 1);
            procStmt.setString(8, "0");
            rsResult = procStmt.executeQuery();
            listEmpleados = this.llenarResultSetEmpleado(rsResult);
            connSqlDb.close();
            
        }catch(SQLException | IOException  ex){
           System.out.println(ex.getMessage());
       }
       this.liberarRecursos();
       return listEmpleados;
    }
    
    public List<Departments> obtenerDepartamentos(int num_opcion) 
    throws IOException, ClassNotFoundException, Exception{
        
       Connection connSqlDb = null;
        procStmt = null;
        rsResult = null; 
        List<Departments> listaDepartamentos = null;
        try {
            
            String sentencia = null;
            sentencia = con_sql.CONSULTA_EMPLEADOS;
            connSqlDb = SqldbConnectionFactory.getInstance().getConnection();
            procStmt = connSqlDb.prepareCall(sentencia);
            procStmt.setInt(1,num_opcion);
            procStmt.setInt(2, 2);
            procStmt.setString(3, "b");
            procStmt.setString(4, "c");
            procStmt.setString(5, "d");
            procStmt.setString(6, "e");
            procStmt.setInt(7, 2);
            procStmt.setString(8, "0");
            rsResult = procStmt.executeQuery();
            listaDepartamentos = this.llenarResultSetDepartamentos(rsResult);
            connSqlDb.close();
            
        }catch(SQLException | IOException  ex){
           System.out.println(ex.getMessage());
       }
       this.liberarRecursos();
       return listaDepartamentos;   
    }
    
    public int insOModEmpleadoDepartamento( 
        int num_opcion,
        int Clave_Emp,
        String nombre,
        String ApPaterno,
        String ApMaterno,
        String FecNac,
        int Departamento,
        String Sueldo) 
    throws IOException, ClassNotFoundException, Exception{
        
        Connection connSqlDb = null;
        procStmt = null;
        rsResult = null; 
        ClvRespuesta clv_respuestaRS = null;
        int clv_respuesta = 0;
        //List<EmployeeDepartment> listaEmpleadoDepartamento = null;
        try {
            
            String sentencia = null;
            sentencia = con_sql.CONSULTA_EMPLEADOS;
            connSqlDb = SqldbConnectionFactory.getInstance().getConnection();
            procStmt = connSqlDb.prepareCall(sentencia);
            procStmt.setInt(1,num_opcion);
            procStmt.setInt(2, Clave_Emp);
            procStmt.setString(3, nombre);
            procStmt.setString(4, ApPaterno);
            procStmt.setString(5, ApMaterno);
            procStmt.setString(6, FecNac);
            procStmt.setInt(7, Departamento);
            procStmt.setString(8, Sueldo);
            rsResult = procStmt.executeQuery();
            clv_respuestaRS = this.llenaResultSetClvRespuesta(rsResult);
            //listaEmpleadoDepartamento = 
                    //this.llenarResultSetEmpleadoDepartamento(rsResult);
            connSqlDb.close();
            
            clv_respuesta = clv_respuestaRS.getClv_respuesta();
        }catch(SQLException | IOException | ClassNotFoundException  ex){
           System.out.println(ex.getMessage());
       }
       this.liberarRecursos();
       return clv_respuesta;   
    }
    
    public int eliminaEmpleado( 
        int num_opcion,
        int Clave_Emp) 
    throws IOException, ClassNotFoundException, Exception{
        Connection connSqlDb = null;
        procStmt = null;
        rsResult = null; 
        ClvRespuesta clv_respuestaRSE = null;
        int clv_respuesta = 0;
        try {
            
            String sentencia = null;
            sentencia = con_sql.CONSULTA_EMPLEADOS;
            connSqlDb = SqldbConnectionFactory.getInstance().getConnection();
            procStmt = connSqlDb.prepareCall(sentencia);
            procStmt.setInt(1,num_opcion);
            procStmt.setInt(2, Clave_Emp);
            procStmt.setString(3, "a");
            procStmt.setString(4, "b");
            procStmt.setString(5, "c");
            procStmt.setString(6, "1900-01-01");
            procStmt.setInt(7, 1);
            procStmt.setString(8, "0");
            rsResult = procStmt.executeQuery();
            clv_respuestaRSE = this.llenaResultSetClvRespuesta(rsResult);
            
            connSqlDb.close();            
        }catch(SQLException | IOException | ClassNotFoundException  ex){
           System.out.println(ex.getMessage());
       }
       this.liberarRecursos();
       clv_respuesta = clv_respuestaRSE.getClv_respuesta();
       return clv_respuesta;   
    }
    
    private List<Employee> llenarResultSetEmpleado(ResultSet rsResult) 
    throws SQLException {
        List<Employee> listaEmp = new LinkedList<>();
        while (rsResult.next()) {
            Employee entidad = new Employee(rsResult);
            listaEmp.add(entidad);
        }
        return listaEmp;
    }
    
    private List<Departments> llenarResultSetDepartamentos(ResultSet rsResult) 
    throws SQLException {
        List<Departments> listaDep = new LinkedList<>();
        while (rsResult.next()) {
            Departments entidad = new Departments(rsResult);
            listaDep.add(entidad);
        }
        return listaDep;
    }
    
    private ClvRespuesta llenaResultSetClvRespuesta(ResultSet rsResult) 
    throws SQLException {
        ClvRespuesta clvResp = null;
        while (rsResult.next()) {
            ClvRespuesta entidad = new ClvRespuesta(rsResult);
            clvResp = entidad;
        }
        return clvResp;
    }
    
    public void liberarRecursos(){
        this.procStmt = null;
        this.rsResult = null;
    }
    
}
