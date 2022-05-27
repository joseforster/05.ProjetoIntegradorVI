/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author forster
 */
public class VendaProdutoModel {
    private VendaModel venda;
    private ProdutoModel produto;
    private double quantidade;
    private double valorUnitario;
    private double valorTotal;

    public VendaProdutoModel(VendaModel venda, ProdutoModel produto, double quantidade, double valorUnitario) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorUnitario * quantidade;
    }

    public VendaModel getVenda() {
        return venda;
    }

    public void setVenda(VendaModel venda) {
        this.venda = venda;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
    
    
}
