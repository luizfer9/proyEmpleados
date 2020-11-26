/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manejador;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author luisf
 */
public class SqldbConnectionFactory {
    private static SqldbConnectionFactory sqldbConnectionFactory;
    private  String sUrlCnx = "jdbc:sqlserver://";
    private  String portNumber = "";
    private  final String selectMethod = "Direct";
    private  String databaseName = "";
    private  String serverName = "";
    private  String userName = "";
    private  String password = "";
    private String sDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private SqldbConnectionFactory() throws SQLException, IOException, 
            ClassNotFoundException, Exception {
        try{
            //Archivo xml que almacena los datos de las conexiones
            /*File fXmlFile = new File(ConstantesSQL.RUTA_CONEXION);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            
            //Seleccionando el nodo que contiene los elementos de la conexion
            NodeList nList = doc.getElementsByTagName(ConstantesSQL.con_sql);
            Node nNode = nList.item(0);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String text = eElement.getAttribute("NombreConexion");
                       //Leyendo los elementos del nodo y guardandolo en variables
                       this.userName = eElement.getElementsByTagName("user").item(0).getTextContent();
                       this.databaseName = eElement.getElementsByTagName("database").item(0).getTextContent();
                       this.serverName = eElement.getElementsByTagName("server").item(0).getTextContent();
                       this.password = eElement.getElementsByTagName("password").item(0).getTextContent();
                       this.portNumber = eElement.getElementsByTagName("port").item(0).getTextContent();
            
            }*/
            this.userName="naibi199_SQLLogin_1";
            this.databaseName="directorio1";
            this.serverName="directorio1.mssql.somee.com";
            this.password="soa9wqlfeh";
            this.portNumber="1433";
        } catch(DOMException ex){
            throw ex;
        }
        this.sUrlCnx+=this.serverName+":"+this.portNumber+"; Database="+this.databaseName+";";
        Class.forName(sDriver);
    }
    
    public static SqldbConnectionFactory getInstance() throws SQLException, IOException, ClassNotFoundException, Exception
    {
        if(sqldbConnectionFactory==null)
        {
          synchronized(SqldbConnectionFactory.class)
          {
              if(sqldbConnectionFactory==null)
              {
                 sqldbConnectionFactory= new SqldbConnectionFactory(); 
              }
              
          }
        }
        return sqldbConnectionFactory;
    }
    
    public final Connection getConnection() throws SQLException, IOException, ClassNotFoundException
    {
        return DriverManager.getConnection(sUrlCnx, userName, password);
    }
}
