package projetointegradorvi;

import View.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;

public class ProjetoIntegradorVI {

    public static void main(String[] args) {
        
        
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        new FrmMenuPrincipal().setVisible(true);
  
    }
    
}
