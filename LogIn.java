
package lospibescorp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LogIn{
    private String nom_usuario;
    private String passw_usuario;

    public LogIn(String nom_usuario, String passw_usuario) {
        this.nom_usuario = nom_usuario;
        this.passw_usuario = passw_usuario;
    }
    
    public boolean inicioSesion(Conexion cx){
        
        try {
            String usuarioInicio = JOptionPane.showInputDialog("Ingrese su nombre de usuario: ");            
            String query="SELECT usuario_cli FROM usuarios where usuario_cli = '"+usuarioInicio+"'"; 

            Statement st=cx.conectar().createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                String compararU = rs.getString("usuario_cli");
                if(compararU.equals(usuarioInicio)){
                    System.out.println("ok");
                    try{
                        String contraseñaInicio = JOptionPane.showInputDialog("Ingrese su contraseña: ");
                        String query2 = "SELECT contraseña_cli FROM usuarios WHERE usuario_cli = '"+usuarioInicio+"'";
                        Statement st1=cx.conectar().createStatement();
                        ResultSet rs1=st1.executeQuery(query2);
                        if(rs1.next()){
                            String compararC = rs1.getString("contraseña_cli");
                            if(compararC.equals(contraseñaInicio)){
                                System.out.println("ok");
                                return true;
                            }else{
                                System.out.println("mal");
                                inicioSesion(cx);
                            }
                        
                        }
                            
                    }
                    catch (SQLException ex){
                        Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    System.out.println("mal");
                    inicioSesion(cx);
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public void eliminarCuenta(Conexion cx){
        try {
            String usuarioInicio = JOptionPane.showInputDialog("Ingrese su nombre de usuario: ");            
            String query="SELECT usuario_cli FROM usuarios where usuario_cli = '"+usuarioInicio+"'"; 

            Statement st=cx.conectar().createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                String compararU = rs.getString("usuario_cli");
                if(compararU.equals(usuarioInicio)){
                    System.out.println("ok");
                    try{
                        String contraseñaInicio = JOptionPane.showInputDialog("Ingrese su contraseña: ");
                        String query2 = "SELECT contraseña_cli FROM usuarios WHERE usuario_cli = '"+usuarioInicio+"'";
                        Statement st1=cx.conectar().createStatement();
                        ResultSet rs1=st1.executeQuery(query2);
                        if(rs1.next()){
                            String compararC = rs1.getString("contraseña_cli");
                            if(compararC.equals(contraseñaInicio)){
                                System.out.println("ok");
                                String query3 = "DELETE FROM usuarios WHERE usuario_cli = '"+usuarioInicio+"' and '"+contraseñaInicio+"'";
                                Statement st2=cx.conectar().createStatement();
                                int rs2=st2.executeUpdate(query3);
                                System.out.println("LA CUENTA HA SIDO ELIMINADA");
                            }else{
                                System.out.println("mal");
                                eliminarCuenta(cx);
                            }
                        
                        }
                            
                    }
                    catch (SQLException ex){
                        Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    System.out.println("mal");
                    inicioSesion(cx);
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void login1(Conexion cx){
        int cuota = 0;
        String DNI_cli = JOptionPane.showInputDialog("Ingrese su DNI: ");
        try{
            String query="SELECT * FROM gimnasio where DNI_cli = '"+DNI_cli+"'"; 
            
            Statement st=cx.conectar().createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                String dni = rs.getString("DNI_cli");
                String nombre= rs.getString("nombre_cli");
                String level = rs.getString("nivel_cli");
                
                JOptionPane.showMessageDialog(null, "DNI: "+dni+
                        "\nNombre: "+nombre+
                        "\nNivel de suscripcion: "+level);
                        
            }
        }catch (SQLException ex) {
            Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
