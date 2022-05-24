/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author forster
 */
public class CompraModel {
    private int id;
    private Date dt;
    private FornecedorModel fornecedor;
    private double valorTotal;
    private ArrayList<CompraProdutoModel> compraProdutos;

    public CompraModel(FornecedorModel fornecedor) {
        this.fornecedor = fornecedor;
        this.dt = new Date();
        this.compraProdutos = new ArrayList<CompraProdutoModel>();
    }
    
    
    
    
}
