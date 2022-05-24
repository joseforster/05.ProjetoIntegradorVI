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
    private int documento;
    private int fone;
    private String email;
    private String endereco;

    public FornecedorModel(String nome, int documento, int fone, String email, String endereco) {
        this.nome = nome;
        this.documento = documento;
        this.fone = fone;
        this.email = email;
        this.endereco = endereco;
    }

    public FornecedorModel(int id, String nome, int documento, int fone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.fone = fone;
        this.email = email;
        this.endereco = endereco;
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

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getFone() {
        return fone;
    }

    public void setFone(int fone) {
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
