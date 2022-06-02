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
    private ArrayList<CompraProdutoModel> compraProdutos;

    public CompraModel(FornecedorModel fornecedor) 
    {
        this.fornecedor = fornecedor;
        this.dt = new Date();
        this.compraProdutos = new ArrayList<CompraProdutoModel>();
    }
    
    public CompraModel(){
        this.dt = new Date();
        this.compraProdutos = new ArrayList<CompraProdutoModel>();
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public FornecedorModel getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorModel fornecedor) {
        this.fornecedor = fornecedor;
    }

    public ArrayList<CompraProdutoModel> getCompraProdutos() {
        return compraProdutos;
    }

    public void setCompraProdutos(ArrayList<CompraProdutoModel> compraProdutos) {
        this.compraProdutos = compraProdutos;
    }
    
}
