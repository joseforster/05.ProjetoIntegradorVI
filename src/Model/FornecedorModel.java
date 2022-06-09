/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author forster
 */
public class FornecedorModel {
    private int id;
    private String nome;
    private String cnpj;
    private String fone;
    private String email;
    private String endereco;

    public FornecedorModel(String nome, String cnpj, String fone, String email, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.fone = fone;
        this.email = email;
        this.endereco = endereco;
    }

    public FornecedorModel(int id, String nome, String cnpj, String fone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.fone = fone;
        this.email = email;
        this.endereco = endereco;
    }

    public FornecedorModel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
}
