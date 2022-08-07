
package lospibescorp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class CheckBox extends JFrame implements  ActionListener{
    
    private JCheckBox check1, check2, check3, check4, check5, check6, check7, check8, check9;
    private JButton boton, boton1, boton40;
    private JRadioButton radio1, radio2, radio3;
    private ButtonGroup grupo;
    private int cont1, cont2, cont3;
    
    public CheckBox(){
        setLayout(null);
        check1= new JCheckBox("Ejercicio 1");
        check1.setBounds(10,10,150,30);

        add(check1);
        
        check2= new JCheckBox("Ejercicio 2");
        check2.setBounds(10,50,150,30);
       
        add(check2);
        
        check3= new JCheckBox("Ejercicio 3");
        check3.setBounds(10,90,150,30);
     
        add(check3);
        
        boton=new JButton();
        boton.setText("ACEPTAR");
        boton.setBounds(170, 50, 120, 40);
        boton.addActionListener(this);
        add(boton);
    }
    
    public CheckBox(String a){
        setLayout(null);
        grupo = new ButtonGroup();
        radio1=new JRadioButton();
        radio1.setText("Ejercicio 1");
        radio1.setBounds(10,10,150,30);
        
        
        radio2=new JRadioButton();
        radio2.setText("Ejercicio 2");
        radio2.setBounds(10,50,150,30);
        
        
        radio3=new JRadioButton();
        radio3.setText("Ejercicio 3");
        radio3.setBounds(10,90,150,30);
        
        
        boton1=new JButton();
        boton1.setText("ACEPTAR");
        boton1.setBounds(170, 50, 120, 40);
        boton1.addActionListener(this);
        
        
        grupo.add(radio1);
        grupo.add(radio2);
        grupo.add(radio3);
        add(radio1);
        add(radio2);
        add(radio3);
        add(boton1);
    }
    
    public CheckBox(int b){
        setLayout(null);
        check4= new JCheckBox("Ejercicio 1");
        check4.setBounds(10,10,150,30);
   
        
        check5= new JCheckBox("Ejercicio 2");
        check5.setBounds(10,50,150,30);
       
   
        
        check6= new JCheckBox("Ejercicio 3");
        check6.setBounds(10,90,150,30);
     
    
        
        check7= new JCheckBox("Servicio 1");
        check7.setBounds(180,10,150,30);
   
        
        
        check8= new JCheckBox("Servicio 2");
        check8.setBounds(180,50,150,30);
       
        
        
        check9= new JCheckBox("Servicio 3");
        check9.setBounds(180,90,150,30);
     
        
        
        boton40=new JButton();
        boton40.setText("ACEPTAR");
        boton40.setBounds(10, 150, 120, 40);
        boton40.addActionListener(this);
        
        
        
        add(check4);
        add(check5);
        add(check6);
        add(check7);
        add(check8);
        add(check9);
        add(boton40);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
        Conexion cx = new Conexion();
        cx.conectar();
        
        if(e.getSource()==boton){
            String cad1="";
            String cad2="";
            String cad3="";
            if(check1.isSelected() == true){
                cad1 = cad1 + "Ejercicio 1".toUpperCase();
                listar(cx, cad1);
            }
            if(check2.isSelected() == true){
                cad2 = cad2 + "Ejercicio 2".toUpperCase();
                listar(cx, cad2);
            }
            if(check3.isSelected() == true){
                cad3 = cad3 + "Ejercicio 3".toUpperCase();
                listar(cx, cad3);
            }
            
            setVisible(false);
        }
        if(e.getSource()==boton1){
            String cad="";
            if(radio1.isSelected() == true){
                cad = cad + "Ejercicio 1".toUpperCase();
                listar(cx, cad);
            }
            if(radio2.isSelected() == true){
                cad = cad + "Ejercicio 2".toUpperCase();
                listar(cx, cad);
            }
            if(radio3.isSelected() == true){
                cad = cad + "Ejercicio 3".toUpperCase();
                listar(cx, cad);
            }
            setVisible(false);
            
                        
        }
        if(e.getSource()==boton40){
            String cad="";
            if(check4.isSelected() == true){
                cad = cad + "Ejercicio 1".toUpperCase();
                listar(cx, cad);
            }
            if(check5.isSelected() == true){
                cad = cad + "Ejercicio 2".toUpperCase();
                listar(cx, cad);
            }
            if(check6.isSelected() == true){
                cad = cad + "Ejercicio 3".toUpperCase();
                listar(cx, cad);
            }
            setVisible(false);
            

        }
    }
    
    
    public void ingreso(String cad){
        Conexion cx = new Conexion();
        cx.conectar();
        try{
            String query="INSERT INTO actividades values('"+cad+"', 1)"; 

            Statement st=cx.conectar().createStatement();
            int rs=st.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listar(Conexion cx, String cad){
        System.out.println("aca empieza");
        try{
            String query = "SELECT nombre_act FROM actividades WHERE nombre_act = '"+cad+"'";
            Statement st=cx.conectar().createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                System.out.println("aca sigue");
                String nombre = rs.getString("nombre_act");
                System.out.println(nombre);
                if(nombre.equals(cad)){
                    try{
                        String query2 = "UPDATE actividades SET cant = cant + 1 WHERE nombre_act = '"+cad+"'";
                        Statement st2=cx.conectar().createStatement();
                        int rs2=st2.executeUpdate(query2);
                    }catch(SQLException ex) {
                        Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else{
                System.out.println("aca va");
                ingreso(cad);
            }
        }catch(SQLException ex) {
            Logger.getLogger(LospibesCorp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
