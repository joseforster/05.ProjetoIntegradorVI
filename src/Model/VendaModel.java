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
public class VendaModel {
    private int id;
    private Date dt;
    private ClienteModel cliente;
    private double valorTotal;
    private ArrayList<VendaProdutoModel> vendaProdutos;

    public VendaModel(ClienteModel cliente) {
        this.cliente = cliente;
        this.dt = new Date();
        this.vendaProdutos = new ArrayList<VendaProdutoModel>();
    }

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

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal() {
        this.valorTotal = 0;
        for(var produto : this.vendaProdutos){
            this.valorTotal += produto.getValorTotal();
        };
    }

    public ArrayList<VendaProdutoModel> getVendaProdutos() {
        return vendaProdutos;
    }

    public void setVendaProdutos(ArrayList<VendaProdutoModel> vendaProdutos) {
        this.vendaProdutos = vendaProdutos;
    }
    
}
