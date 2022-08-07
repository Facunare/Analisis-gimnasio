
package lospibescorp;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.JRadioButton;

public class LospibesCorp {

    public static void main(String[] args) throws InterruptedException {
        
        principal();
    }
    
    
    
    public static void principal() throws InterruptedException{
        int seguir = 0;
        int contDias = 0;
        Conexion cx = new Conexion();
        cx.conectar();
        
        int seleccion = JOptionPane.showOptionDialog(
        null,
        "Seleccione opcion", 
        "Selector de opciones",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        new Object[] { "INICIAR SESION", "REGISTRARSE", "DAR DE BAJA" }, 
        "opcion 1");
        
        
        switch(seleccion){
            case 0->{
                System.out.println("INICIAR SESION");
                LogIn log = new LogIn("a", "b");
                boolean iniciar = log.inicioSesion(cx);
                if(iniciar){
                    log.login1(cx);
                }
            }
            case 1->{
                System.out.println("REGISTRARSE");
                registro(cx, seguir);
            }
            case 2->{
                System.out.println("DAR DE BAJA");
                LogIn log = new LogIn("c", "d");
                log.eliminarCuenta(cx);
            }
        }     
    }
    
    
    public static void registro(Conexion cx, int seguir) throws InterruptedException{
        try {
              
              String usuario = JOptionPane.showInputDialog("Ingrese su nuevo nombre de usuario: ");
              String contraseña = JOptionPane.showInputDialog("Ingrese su nueva contraseña: ");
              String query ="INSERT INTO usuarios(usuario_cli, contraseña_cli) values('"+usuario+"','"+contraseña+"')";
              LogIn log = new LogIn(usuario, contraseña);
              Statement st=cx.conectar().createStatement();
              st.executeUpdate(query);
              boolean iniciar = log.inicioSesion(cx);
              inicio(cx, seguir, iniciar);
     
        } catch (SQLException ex) {
            Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void inicio(Conexion cx, int seguir, boolean iniciar) throws InterruptedException{
        CheckBox cb = null;
        if(iniciar){
            String nombre_cli=JOptionPane.showInputDialog("Ingrese su nombre: ");
            String DNI_cli = JOptionPane.showInputDialog("Ingrese su DNI: ");

            Cliente c = new Cliente(nombre_cli, DNI_cli);

            Object[] niveles = {"LITE", "FULL", "EXCLUSIVO"};
            String nivel = (String)JOptionPane.showInputDialog(null,
                    "QUE NIVEL DE SUSCRIPCION DESEA ADQUIRIR?",
                    "", JOptionPane.QUESTION_MESSAGE, null, niveles, niveles[0]);

                switch(nivel.toUpperCase()){
                case "LITE"->{
                    String level = "LITE";
                    Niveles lite = new Lite(500);
                    JOptionPane.showMessageDialog(null, "El precio a pagar es: "+lite.getPrecio());
                    JOptionPane.showMessageDialog(null, "Solo puede elegir 1 ejercicio");
                    cb = new CheckBox("1");
                    JButton j = new JButton();
                    cb.setBounds(0, 0, 350, 200);
                    cb.setVisible(true);
                    cb.setResizable(false);
                    cb.setLocationRelativeTo(null);
                    j.setBounds(0, 0, 400, 200);
                    try{
                        String query3 = "INSERT INTO gimnasio(DNI_cli, nombre_cli, nivel_cli) values('"+DNI_cli+"','"+nombre_cli+"','"+level+"')";
                        Statement st2=cx.conectar().createStatement();
                        st2.executeUpdate(query3);
                    }catch (SQLException ex) {
                        Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }
                case "FULL"->{
                    Niveles full = new Full(1500);
                    JOptionPane.showMessageDialog(null, "El precio a pagar es: "+full.getPrecio());
                    String level = "FULL";
                    JOptionPane.showMessageDialog(null, "Puede elegir los ejercicios que quiera");
                    cb = new CheckBox();
                    JButton j = new JButton();
                    cb.setBounds(0, 0, 350, 200);
                    cb.setVisible(true);
                    cb.setResizable(false);
                    cb.setLocationRelativeTo(null);
                    j.setBounds(0, 0, 400, 200);

                }
                case "EXCLUSIVO"->{
                    Niveles exclusivo = new Exclusivo(3000);
                    JOptionPane.showMessageDialog(null, "El precio a pagar es: "+exclusivo.getPrecio());
                    String level = "EXCLUSIVO";
                    JOptionPane.showMessageDialog(null, "Puede elegir los ejercicios que quiera y servicios exclusivos!");
                    cb = new CheckBox(1);
                    JButton j = new JButton();
                    cb.setBounds(0, 0, 390, 250);
                    cb.setVisible(true);
                    cb.setResizable(false);
                    cb.setLocationRelativeTo(null);
                    j.setBounds(0, 0, 400, 200);

                }
            }
            System.out.println("Espere");    
            while(cb.isVisible()){
                
                System.out.print(".");
                Thread.sleep(1000);
            }
            try{
                int mayor = 0;
                String nom_mayor = "";
                String query2 = "select max(cant) as mayor from actividades";
                Statement st1=cx.conectar().createStatement();
                ResultSet rs1=st1.executeQuery(query2);

                if(rs1.next()){
                    mayor = rs1.getInt("mayor");
                }
                try{
                    String query3 = "select nombre_act as nombre_mayor from actividades where cant = '"+mayor+"'";
                    Statement st2=cx.conectar().createStatement();
                    ResultSet rs2=st2.executeQuery(query3);
                    if(rs2.next()){
                        nom_mayor = rs2.getString("nombre_mayor");
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "El ejercicio mas realizado es "+nom_mayor+" con un total de "+mayor);


            } catch (SQLException ex) {
                Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int cerrarSesion = JOptionPane.showOptionDialog(
            null,
            "Desea cerrar la sesion?", 
            "Selector de opciones",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[] {"CERRAR SESION y CONTINUAR", "CERRAR SESION y SALIR"}, 
            "opcion 1");
            
            switch(cerrarSesion){
                case 0->{
                    principal();
                }
                case 1->{
                    System.exit(0);
                }
            }
            
        }
                
                
            
    }
            

        
        
}

