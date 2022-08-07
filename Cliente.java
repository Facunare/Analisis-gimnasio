
package lospibescorp;


import javax.swing.JOptionPane;

public class Cliente {
    
    private String nombre;
    private String DNI;


    public Cliente(String nombre, String DNI) {
        this.nombre = nombre;
        this.DNI = DNI;
    
        
    }
    
   
    
    
    public void mostrarDatos(){
        JOptionPane.showMessageDialog(null, "Nombre: "+this.nombre+
                                        "\nDNI: "+this.DNI);
    }
    
    
}
