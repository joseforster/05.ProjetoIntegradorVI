/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author forster
 */
public class DepositoModel {
    private int id;
    private String descricao;
    private String endereco;

    public DepositoModel(int id, String descricao, String endereco) {
        this.id = id;
        this.descricao = descricao;
        this.endereco = endereco;
    }

    public DepositoModel(String descricao, String endereco) {
        this.descricao = descricao;
        this.endereco = endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}
