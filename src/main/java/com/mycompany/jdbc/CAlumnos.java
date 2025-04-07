/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
}

  public void MostrarAlumno(JTable paramTablaTotalAlumnos){
        
        CConexion objetoConexion = new CConexion(); 
        
        /*El modelo de nuestra tabla*/
        
        DefaultTableModel  modelo = new DefaultTableModel();
        
        /*Ordenamos la tabla como si fuera un excel si lo deseamos alfabeticamente para mostar los datos*/
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        /*Hacemos la consulta*/
        
        String sql="";
        
        /*Ahora ordenamos las columnas (las cuales ya habíamos retirado antes)*/
        
        modelo.addColumn("ID");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        paramTablaTotalAlumnos.setModel(modelo);
        
        sql = "select * from Alumnos";
        
        /*De acuerdo a la consulta hacemos un vector matriz donde se van a mostrar esos valores*/
        
        String[] datos = new String[3]; /*Preparamos la consulta con Statement*/
        Statement st;
        
        /*Ahora si lo que vamos a ejecutar*/
        
        try {
            
            st = objetoConexion.establecerConexion().createStatement(); /*ejecutamos la conexion*/
            
            ResultSet rs = st.executeQuery(sql);  /*ejecutamos la consulta*/
            
            /*Como es una tabla la recorro con un while*/
            while(rs.next()){ /*Ahora que los muestre el vectos empieza en cero pero los parametros en uno*/
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            
            /*Ahora que nos incorpore las filas que ya estan recorridas (datos)*/
            
            modelo.addRow(datos);
            
            } 
            
            /*fuera de while incorporamos el modelo que hicimos*/
            paramTablaTotalAlumnos.setModel(modelo);
            
        } catch (Exception e) {
            
            /*Mostarmos el mensaje*/
            JOptionPane.showMessageDialog(null,"Nose pudo mostrar los registros error: "+ e.toString());
                    } 
                   
    }   

 
       
public void SeleccionarAlumno(JTable paramTableAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos){
  
        try {
            
            int fila  = paramTableAlumnos.getSelectedRow();
            
            if (fila>=0) {
                
                paramId.setText((String) (paramTableAlumnos.getValueAt(fila,0)));
                paramNombres.setText((String) (paramTableAlumnos.getValueAt(fila,1)));
                paramApellidos.setText((String) (paramTableAlumnos.getValueAt(fila,2)));
                        }
            else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
                                } catch (Exception e) {
                              JOptionPane.showMessageDialog(null, "Error :  " + e.toString());
                      
        }  
    
   

}
public void ModificarAlumnos (JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos){
    
    setCodigo(Integer.parseInt(paramCodigo.getText()));
    setNombreAlumno(paramNombres.getText());
    setApellidoAlumno(paramApellidos.getText());
    
    CConexion objetoConexion = new CConexion(); /*Preparamos la conexion*/
  
    String consulta = "UPDATE Alumnos SET alumnos.nombres = ?, alumnos.apellidos =? WHERE alumnos.id=?;";
        
  try {
    
        CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, getNombreAlumno());
        cs.setString(2, getApellidoAlumno());
        cs.setInt(3,getCodigo());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Modificación Exitosa");
        
} catch (Exception e){

    JOptionPane.showMessageDialog(null,"Error al modificar Alumno error :" + e.toString());
     
}
}// Define un método público que no retorna valor (void), llamado EliminarAlumnos, que recibe un JTextField como parámetro
public void EliminarAlumnos(JTextField paramCodigo){
    
    // Obtiene el texto del JTextField paramCodigo, lo convierte a entero con parseInt y lo asigna usando el método setCodigo
    setCodigo(Integer.parseInt(paramCodigo.getText()));
    
    // Crea un nuevo objeto de la clase CConexion para establecer la conexión con la base de datos
    CConexion objetoConexion = new CConexion();
    
    // Define una consulta SQL para eliminar un registro de la tabla Alumnos donde el ID coincida con el valor proporcionado
    String consulta = " DELETE FROM Alumnos WHERE alumnos.id =?;";
    
    // Inicia un bloque try-catch para manejar posibles excepciones durante la ejecución de la consulta SQL
    try {
         // Establece la conexión a la base de datos y prepara la consulta SQL para su ejecución
         CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
         
         // Asigna el valor del código (ID) del alumno al marcador de posición (?) en la consulta SQL
         cs.setInt(1, getCodigo());
         
         // Ejecuta la consulta SQL preparada con el valor asignado
         cs.execute();
         
         // Muestra un cuadro de diálogo informando que la eliminación se realizó con éxito
         JOptionPane.showMessageDialog(null, "Se elimino correctamente el Alumno");
         
    // Captura específicamente excepciones de tipo SQLException (errores relacionados con la base de datos)
    } catch (SQLException e) {
         
         // Muestra un cuadro de diálogo con un mensaje de error
         // NOTA: Hay un error aquí, debería ser e.toString() en lugar de toString()
         JOptionPane.showMessageDialog(null, "No se pudo eliminar, error : " + toString());
    }

}


}





