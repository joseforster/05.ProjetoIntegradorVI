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
    public boolean update (T objeto);
    public boolean delete (T objeto);
    public boolean getAll ();
    public boolean getAllComboBox ();
}
