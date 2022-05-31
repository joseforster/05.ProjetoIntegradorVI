/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Util.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author forster
 */
public class EstoqueDAO {
    
    public String[][] readEstoqueByProduto(String filtro){
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sqlCount = "select count(produto.id) as qtde from projeto_integrador_vi.produto_detalhes " +
                            "inner join projeto_integrador_vi.produto ON produto.id = produto_detalhes.produto_id";
            }else{
                sqlCount = "select count(produto.id) as qtde from projeto_integrador_vi.produto_detalhes " +
                            "inner join projeto_integrador_vi.produto ON produto.id = produto_detalhes.produto_id "+
                            "where produto.descricao ilike '%"+filtro+"%';";
            }
            
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sql = "select produto.descricao, sum(quantidade_kg) as qtde from projeto_integrador_vi.produto_detalhes " +
                        "inner join projeto_integrador_vi.produto ON produto.id = produto_detalhes.produto_id " +
                        "group by produto.descricao order by 2 desc";
            }else{
                sql = "select produto.descricao, sum(quantidade_kg) as qtde from projeto_integrador_vi.produto_detalhes " +
                        "inner join projeto_integrador_vi.produto ON produto.id = produto_detalhes.produto_id " +
                        " where produto.descricao ilike '%" + filtro + "%' " +
                        "group by produto.descricao order by 2 desc;";
                        
            }
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Descrição","Quantidade Kg"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                
                String descricao = rsSelect.getString("descricao");
                String quantidade = rsSelect.getString("qtde");
                
                data[i][0] = descricao;
                data[i][1] = quantidade;
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    }
    
    public String [][] readEstoqueByAreaDeposito(String filtro){
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sqlCount = "select count(distinct deposito_area.id) as qtde " +
                            "from projeto_integrador_vi.produto_detalhes " +
                            "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                            "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id " +
                            "group by deposito.descricao,deposito_area.descricao, limite_kg";
            }else{
                sqlCount = "select count(distinct deposito_area.id) as qtde " +
                            "from projeto_integrador_vi.produto_detalhes " +
                            "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                            "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id " +
                            "where deposito_area.descricao ilike '%"+filtro+"%' " +
                            "group by deposito.descricao,deposito_area.descricao, limite_kg";
            }
            
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sql = "select deposito.descricao as deposito,  deposito_area.descricao as area, sum(quantidade_kg) as atual, limite_kg, " +
                        "cast((sum(quantidade_kg)/limite_kg) * 100 as numeric(3,1)) as utilizado " +
                        "from projeto_integrador_vi.produto_detalhes " +
                        "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                        "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id " +
                        "group by deposito.descricao,deposito_area.descricao, limite_kg order by 4 desc";
            }else{
                sql = "select deposito.descricao as deposito,  deposito_area.descricao as area, sum(quantidade_kg) as atual, limite_kg, " +
                        "cast((sum(quantidade_kg)/limite_kg) * 100 as numeric(3,1)) as utilizado " +
                        "from projeto_integrador_vi.produto_detalhes " +
                        "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                        "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id " +
                        "where deposito_area.descricao ilike '%"+filtro+"%' " +
                        "group by deposito.descricao,deposito_area.descricao, limite_kg order by 4 desc";
                        
            }
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Depósito","Área", "Atual Kg", "Limite Kg", "Utilizado"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                
                String deposito = rsSelect.getString("deposito");
                String area = rsSelect.getString("area");
                String atual = rsSelect.getString("atual");
                String limite = rsSelect.getString("limite_kg");
                String utilizado = rsSelect.getString("utilizado");
                
                
                data[i][0] = deposito;
                data[i][1] = area;
                data[i][2] = atual;
                data[i][3] = limite;
                data[i][4] = utilizado;
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    }
    
    public String[][] readEstoqueDescritivo(String filtro){
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sqlCount = "select count(produto.id) as qtde " +
                "from projeto_integrador_vi.produto_detalhes " +
                "inner join projeto_integrador_vi.produto ON produto.id = produto_detalhes.produto_id " +
                "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id";
            }else{
                sqlCount = "select count(produto.id) as qtde " +
                "from projeto_integrador_vi.produto_detalhes " +
                "inner join projeto_integrador_vi.produto ON produto.id = produto_detalhes.produto_id " +
                "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id " +
                "where produto.descricao ilike '%"+filtro+"%';";
            }
            
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sql = "select produto.descricao as produto, deposito_area.descricao as area, deposito.descricao as deposito, sum(quantidade_kg) as quantidade_kg, " +
                "to_char(validade, 'DD/MM/YYYY') as validade " +
                "from projeto_integrador_vi.produto_detalhes " +
                "inner join projeto_integrador_vi.produto ON produto.id = produto_detalhes.produto_id " +
                "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id " +
                "group by 1,2,3,5 " +
                "order by produto.descricao";
            }else{
                sql = "select produto.descricao as produto, deposito_area.descricao as area, deposito.descricao as deposito, sum(quantidade_kg) as quantidade_kg, " +
                "to_char(validade, 'DD/MM/YYYY') as validade " +
                "from projeto_integrador_vi.produto_detalhes " +
                "inner join projeto_integrador_vi.produto ON produto.id = produto_detalhes.produto_id " +
                "inner join projeto_integrador_vi.deposito_area ON deposito_area.id = produto_detalhes.area_id " +
                "inner join projeto_integrador_vi.deposito ON deposito.id = deposito_area.deposito_id " +
                "where produto.descricao ilike '%"+filtro+"%' " +
                "group by 1,2,3,5 " +
                "order by produto.descricao";
                        
            }
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Produto","Área", "Depósito", "Quantidade Kg", "Validade"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                
                String produto = rsSelect.getString("produto");
                String area = rsSelect.getString("area");
                String deposito = rsSelect.getString("deposito");
                String quantidadeKg = rsSelect.getString("quantidade_kg");
                String validade = rsSelect.getString("validade");
                
                
                data[i][0] = produto;
                data[i][1] = area;
                data[i][2] = deposito;
                data[i][3] = quantidadeKg;
                data[i][4] = validade;
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    }
}
