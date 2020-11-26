/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.controllador.DirectorioController;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author luisf
 */
@Path("directorio")
public class DirectorioResource {

    @Context
    private UriInfo context;

    DirectorioController directorio = new DirectorioController();
    public DirectorioResource() {
    }

    
    @Path("/{num_opcion}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getInfoDirectorio(
    @PathParam("num_opcion") int num_opcion
    /*@QueryParam("Clave_Emp") int Clave_Emp,       
    @QueryParam("Nombre") String nombre,
    @QueryParam("ApPaterno") String ApPaterno,
    @QueryParam("ApMaterno") String ApMaterno,
    @QueryParam("FecNac") String FecNac,
    @QueryParam("Departamento") int Departamento,
    @QueryParam("Sueldo") String Sueldo*/
    ) 
    throws IOException, ClassNotFoundException, Exception{
        String respuesta = null;
        switch(num_opcion){
            case 1:
                respuesta = directorio
                                .obtenerDepartamentos(num_opcion);
            break;    
            case 2: 
                respuesta = directorio
                                .obtenerEmpleados(num_opcion);
            break;
            /*case 3:
                respuesta = directorio
                                .insOModEmpleadoDepartamento(num_opcion,
                                                            Clave_Emp,
                                                            nombre,
                                                            ApPaterno,
                                                            ApMaterno,
                                                            FecNac,
                                                            Departamento,
                                                            Sueldo);
            break;
            case 4:
                respuesta = directorio
                                .insOModEmpleadoDepartamento(num_opcion,
                                                            Clave_Emp,
                                                            nombre,
                                                            ApPaterno,
                                                            ApMaterno,
                                                            FecNac,
                                                            Departamento,
                                                            Sueldo);
            break;
            case 5:
                respuesta = directorio
                                .eliminaEmpleado(num_opcion,Clave_Emp);
            break;*/
            default: break;
           
        }
        return respuesta;
    }
}
