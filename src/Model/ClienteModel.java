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
    private int cnpj;
    private int fone;
    private String email;
    private String endereço;

    public ClienteModel(int id, String nome, int cnpj, int telefone, String email, String endereço) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.fone = telefone;
        this.email = email;
        this.endereço = endereço;
    }

    public ClienteModel(String nome, int cnpj, int telefone, String email, String endereço) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.fone = telefone;
        this.email = email;
        this.endereço = endereço;
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

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public int getTelefone() {
        return fone;
    }

    public void setTelefone(int telefone) {
        this.fone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }
    
    
}
