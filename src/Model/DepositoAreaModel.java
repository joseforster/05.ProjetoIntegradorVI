/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author forster
 */
public class DepositoAreaModel {
    private int id;
    private String descricao;
    private DepositoModel deposito;
    private double limiteKg;
    private double atualKg;

    public DepositoAreaModel(int id, String descricao, DepositoModel deposito, double limiteKg, double atualKg) {
        this.id = id;
        this.descricao = descricao;
        this.deposito = deposito;
        this.limiteKg = limiteKg;
        this.atualKg = atualKg;
    }

    public DepositoAreaModel(String descricao, DepositoModel deposito, double limiteKg, double atualKg) {
        this.descricao = descricao;
        this.deposito = deposito;
        this.limiteKg = limiteKg;
        this.atualKg = atualKg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DepositoModel getDeposito() {
        return deposito;
    }

    public void setDeposito(DepositoModel deposito) {
        this.deposito = deposito;
    }

    public double getLimiteKg() {
        return limiteKg;
    }

    public void setLimiteKg(double limiteKg) {
        this.limiteKg = limiteKg;
    }

    public double getAtualKg() {
        return atualKg;
    }

    public void setAtualKg(double atualKg) {
        this.atualKg = atualKg;
    }
    
    
    
}
