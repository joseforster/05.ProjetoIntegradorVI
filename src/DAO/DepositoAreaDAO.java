/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.DepositoAreaModel;
import Util.ConexaoBD;
import Util.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author forster
 */
public class DepositoAreaDAO implements IDAO<DepositoAreaModel>{

    @Override
    public boolean create(DepositoAreaModel objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[][] read(String filtro) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sqlCount = "select count(deposito_area.id) as qtde from  projeto_integrador_vi.deposito_area " +
                            "inner join projeto_integrador_vi.deposito as d on d.id = deposito_area.deposito_id " +
                            "where deposito_area.ativo = 'S'";
            }else{
                sqlCount = "select count(deposito_area.id) as qtde from  projeto_integrador_vi.deposito_area " +
                            "inner join projeto_integrador_vi.deposito as d on d.id = deposito_area.deposito_id " +
                            "where deposito_area.ativo = 'S' and deposito_area.descricao ilike '%"+filtro+"%';";
            }
            
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sql = "select deposito_area.id, deposito_area.descricao, limite_kg, d.descricao as deposito from  projeto_integrador_vi.deposito_area " +
                        "inner join projeto_integrador_vi.deposito as d on d.id = deposito_area.deposito_id " +
                        "where deposito_area.ativo = 'S'";
            }else{
                sql = "select deposito_area.id, deposito_area.descricao, limite_kg, d.descricao as deposito from  projeto_integrador_vi.deposito_area " +
                            "inner join projeto_integrador_vi.deposito as d on d.id = deposito_area.deposito_id " +
                            "where deposito_area.ativo = 'S' and deposito_area.descricao ilike '%"+filtro+"%';";
            }
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Id","Descrição","Limite Kg", "Depósito"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                int id = rsSelect.getInt("id");
                String descricao = rsSelect.getString("descricao");
                String limiteKg = rsSelect.getString("limite_kg");
                String deposito = rsSelect.getString("deposito");
          
                data[i][0] = id+"";
                data[i][1] = descricao;
                data[i][2] = limiteKg;
                data[i][3] = deposito;
        
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    }

    @Override
    public boolean update(DepositoAreaModel objeto) {
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
    
    public String [] readComboBoxByProduto(int idProduto){
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sqlCount = "select count(produto_detalhes.id) as qtde from projeto_integrador_vi.produto_detalhes " +
            "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
            "where produto_id = "+idProduto;

            ResultSet rsCount = st.executeQuery(sqlCount);

            int quantidadeRegistros = 0;

            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }

            String sql = "select deposito_area.id||' - '||deposito_area.descricao||' - '||quantidade_kg||' Kg - Validade: '||to_char(validade, 'DD/MM/YYYY') as info from projeto_integrador_vi.produto_detalhes " +
            "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
            "where produto_id = " + idProduto;

            ResultSet rsSelect = st.executeQuery(sql);

            String [] data = new String[quantidadeRegistros];

            int i = 0;

            while(rsSelect.next()){

                String info = rsSelect.getString("info");

                data[i] = info;

                i++;

            }

            return data;

        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros para combo box: "+e);
            return new String[0];
        }
    }
    
    public String[] readComboBoxByQuantidadeDisponivel(double quantidade){
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sqlCount = "select count(deposito_area.id) as qtde from projeto_integrador_vi.deposito_area " +
                            "inner join ( " +
                            "    select deposito_area.id, limite_kg - sum(quantidade_kg) as disponivel from projeto_integrador_vi.produto_detalhes " +
                            "    inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                            "    group by deposito_area.id, limite_kg " +
                            ") as tb on tb.id = deposito_area.id " +
                            "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id " +
                            "where disponivel > " + quantidade;

            ResultSet rsCount = st.executeQuery(sqlCount);

            int quantidadeRegistros = 0;

            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }

            String sql = "select deposito_area.id||' - '||deposito_area.descricao||' - '||deposito.descricao||' - Disponível (Kg): '||tb.disponivel as info from projeto_integrador_vi.deposito_area " +
                        "inner join ( " +
                        "    select deposito_area.id, limite_kg - sum(quantidade_kg) as disponivel from projeto_integrador_vi.produto_detalhes " +
                        "    inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                        "    group by deposito_area.id, limite_kg " +
                        ") as tb on tb.id = deposito_area.id " +
                        "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id " +
                        "where disponivel > " + quantidade;

            ResultSet rsSelect = st.executeQuery(sql);

            String [] data = new String[quantidadeRegistros];

            int i = 0;

            while(rsSelect.next()){

                String info = rsSelect.getString("info");

                data[i] = info;

                i++;

            }

            return data;

        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros para combo box: "+e);
            return new String[0];
        }
    }
    
    
}
