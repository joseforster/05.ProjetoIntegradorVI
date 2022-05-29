/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author forster
 */
public interface IDAO <T> {
    public boolean create (T objeto);
    public String [][] read (String filtro);
    public boolean update (T objeto);
    public boolean delete (int id);
    public String[] readComboBox ();
}
