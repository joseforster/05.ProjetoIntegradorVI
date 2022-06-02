/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.VendaModel;
import Util.ConexaoBD;
import Util.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author forster
 */
public class VendaDAO implements IDAO<VendaModel>{

    @Override
    public boolean create(VendaModel objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[][] read(String filtro) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount = "select count(id) as qtde from  projeto_integrador_vi.venda where cancelado = 'N'";
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql = "select venda.id, to_char(dt_venda, 'DD/MM/YYYY') as dt_venda, " +
            "sum(valor_total) as valor_total, " +
            "cliente.nome from  projeto_integrador_vi.venda " +
            "inner join projeto_integrador_vi.cliente ON cliente.id = venda.cliente_id " +
            "inner join projeto_integrador_vi.venda_produto ON venda_produto.venda_id = venda.id " +
            "where cancelado = 'N' " +
            "group by 1,2,4";
            
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Id","Data Venda","Valor Total","Cliente"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                int id = rsSelect.getInt("id");
                String dtVenda = rsSelect.getString("dt_venda");
                String valorTotal = rsSelect.getString("valor_total");
                String cliente = rsSelect.getString("nome");
       

          
                data[i][0] = id+"";
                data[i][1] = dtVenda;
                data[i][2] = valorTotal;
                data[i][3] = cliente;
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    }

    @Override
    public boolean update(VendaModel objeto) {
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
    
    public String[][] detalhesVenda(int idVenda){
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount = "select count(venda_produto.id) as qtde from projeto_integrador_vi.venda_produto " +
            "inner join projeto_integrador_vi.produto on produto_id = produto.id " +
            "where venda_id = "+ idVenda;
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql = "select produto.descricao, quantidade_kg, valor_kg, valor_total from projeto_integrador_vi.venda_produto " +
            "inner join projeto_integrador_vi.produto on produto_id = produto.id " +
            "where venda_id = "+ idVenda;
            
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Produto","Quantidade Kg","Valor Kg","Valor Total"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                String produto = rsSelect.getString("descricao");
                String quantidadeKg = rsSelect.getString("quantidade_kg");
                String valorKg = rsSelect.getString("valor_kg");
                String valorTotal = rsSelect.getString("valor_total");
                
                data[i][0] = produto;
                data[i][1] = quantidadeKg;
                data[i][2] = valorKg;
                data[i][3] = valorTotal;
                
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    } 
    
}
