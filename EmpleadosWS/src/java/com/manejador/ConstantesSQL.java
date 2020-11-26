/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manejador;

/**
 *
 * @author luisf
 */
public class ConstantesSQL {
    public static final String CONEXION_SIF = "ConexionEMP";//DATOS
    public static final String RUTA_CONEXION = "C://sys//conexion//ConexionE.xml";
    public static final String con_sql = "conexionsql";
    public static final String CONSULTA_EMPLEADOS = 
            "{ call SP_OPERACIONES_EMPLEADOS(?,?,?,?,?,?,?,?) }";
}
