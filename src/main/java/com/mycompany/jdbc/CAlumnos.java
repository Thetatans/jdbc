/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc;

import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author SENA
 */
public class CAlumnos {

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }
      int codigo;
    String nombreAlumno;
    String apellidoAlumno;
    
    
       public void InsertarAlumno(JTextField paramNombres, JTextField paramApellidos){
    
        setNombreAlumno(paramNombres.getText());
        setApellidoAlumno(paramApellidos.getText());
        // Establecemos conexión//
        CConexion objetoConexion = new CConexion();
//incorporamos la consulta ya la habíamos hecho en la base de datos Myqsl//  La copiamos y la llevamos.
 String consulta = "insert into Alumnos (nombres, apellidos) values (?,?);";
try {
           
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            
        cs.execute();
    JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno");
        
    
    } catch (Exception e){
        JOptionPane.showConfirmDialog(null,"No se insertó correctamente el alumno, error: "+e.toString());
}


       }}
