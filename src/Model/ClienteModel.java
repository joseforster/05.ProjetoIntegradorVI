/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author forster
 */
public class ClienteModel {
    private int id;
    private String nome;
    private String cnpj;
    private String fone;
    private String email;
    private String endereco;

    public ClienteModel(int id, String nome, String cnpj, String telefone, String email, String endereço) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.fone = telefone;
        this.email = email;
        this.endereco = endereço;
    }

    public ClienteModel(String nome, String cnpj, String telefone, String email, String endereço) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.fone = telefone;
        this.email = email;
        this.endereco = endereço;
    }

    public ClienteModel(int id, String nome) {
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

    public String getTelefone() {
        return fone;
    }

    public void setTelefone(String telefone) {
        this.fone = telefone;
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

    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }
    
    
}
