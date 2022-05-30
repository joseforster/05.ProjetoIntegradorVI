/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ClienteModel;
import Util.ConexaoBD;
import Util.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author forster
 */
public class ClienteDAO implements IDAO<ClienteModel>{

    @Override
    public boolean create(ClienteModel objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[][] read(String filtro) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sqlCount = "select count(id) as qtde FROM projeto_integrador_vi.cliente where ativo = 'S';";
            }else{
                sqlCount = "select count(id) as qtde FROM projeto_integrador_vi.cliente where ativo = 'S'"
                        + " and nome ilike '%" + filtro + "%';";
            }
            
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sql = "select * FROM projeto_integrador_vi.cliente where ativo = 'S' order by id;";
            }else{
                sql = "select * FROM projeto_integrador_vi.cliente where ativo = 'S'"
                        + " and nome ilike '%" + filtro + "%';";
            }
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Id","Nome","CNPJ", "Fone", "Email","Endereço"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                int id = rsSelect.getInt("id");
                String nome = rsSelect.getString("nome");
                String cnpj = rsSelect.getString("cnpj");
                String fone = rsSelect.getString("fone");
                String email = rsSelect.getString("email");
                String endereco = rsSelect.getString("endereco");
          
                data[i][0] = id+"";
                data[i][1] = nome;
                data[i][2] = cnpj;
                data[i][3] = fone;
                data[i][4] = email;
                data[i][5] = endereco;
        
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    }

    @Override
    public boolean update(ClienteModel objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[] readComboBox() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
