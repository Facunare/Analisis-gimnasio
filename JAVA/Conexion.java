
package lospibescorp;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    String bd = "lospibescorporation";
    String url="jdbc:mysql://localhost/";
    String user="root";
    String passw = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;

    public Conexion() {
        
    }
    
    public Connection conectar(){
        try {
            Class.forName(driver);
            cx=DriverManager.getConnection(url+bd, user, passw);
        } catch (ClassNotFoundException | SQLException ex ) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return cx;
    }
    
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
