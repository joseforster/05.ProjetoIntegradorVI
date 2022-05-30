/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author forster
 */
public class FormatField {
    
    public static void FormatCPF(JFormattedTextField field){
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("###.###.###-##");
            field.setFormatterFactory(null);
            field.setFormatterFactory(new DefaultFormatterFactory(m));
            field.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void FormatTelefone(JFormattedTextField field){
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("(##) #####-####");
            field.setFormatterFactory(null);
            field.setFormatterFactory(new DefaultFormatterFactory(m));
            field.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void FormatCNPJ(JFormattedTextField field) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##.###.###/####-##");
            field.setFormatterFactory(null);
            field.setFormatterFactory(new DefaultFormatterFactory(m));
            field.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void FormatCEP(JFormattedTextField field) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("#####-###");
            field.setFormatterFactory(null);
            field.setFormatterFactory(new DefaultFormatterFactory(m));
            field.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    
    public static String removeFormat(String dado) {
        String retorno = "";
        for (int i = 0; i < dado.length(); i++) {
            if (
                    dado.charAt(i) != '.' && 
                    dado.charAt(i) != '/' && 
                    dado.charAt(i) != '-' &&
                    dado.charAt(i) != '(' &&
                    dado.charAt(i) != ')' &&
                    dado.charAt(i) != ' ' &&
                    dado.charAt(i) != 'K' &&
                    dado.charAt(i) != 'G' &&
                    dado.charAt(i) != 'R' &&
                    dado.charAt(i) != '$' 
                    
            ) {
                retorno = retorno + dado.charAt(i);
            }
        }
        return (retorno);
        }
    
}
