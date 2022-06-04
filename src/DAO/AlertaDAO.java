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
public class AlertaDAO {
    
    public String[][] read(){
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount = "select count(*) as qtde from ( " +
            "select 'Validade Próx. 90 dias' as tipo, produto.descricao, quantidade_kg, 'Validade: '||to_char(validade, 'DD/MM/YYYY') as obs " +
            "from projeto_integrador_vi.produto_detalhes " +
            "inner join projeto_integrador_vi.produto on produto.id = produto_detalhes.produto_id " +
            "where validade <= current_date + interval'90 days' " +
            "union " +
            "select 'Quantidade abaixo Estoque Mínimo', produto.descricao, sum(quantidade_kg), 'Estoque Mínimo (Kg): '||estoque_minimo_kg " +
            "from projeto_integrador_vi.produto_detalhes " +
            "inner join projeto_integrador_vi.produto on produto.id = produto_detalhes.produto_id " +
            "group by 1,produto.descricao,estoque_minimo_kg " +
            "having sum(quantidade_kg) < estoque_minimo_kg " +
            ") as tb";
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql = "select 'Validade Próx. 90 dias' as tipo, produto.descricao, quantidade_kg, 'Validade: '||to_char(validade, 'DD/MM/YYYY') as obs " +
            "from projeto_integrador_vi.produto_detalhes " +
            "inner join projeto_integrador_vi.produto on produto.id = produto_detalhes.produto_id " +
            "where validade <= current_date + interval'90 days' " +
            "union " +
            "select 'Quantidade abaixo Estoque Mínimo', produto.descricao, sum(quantidade_kg), 'Estoque Mínimo (Kg): '||estoque_minimo_kg " +
            "from projeto_integrador_vi.produto_detalhes " +
            "inner join projeto_integrador_vi.produto on produto.id = produto_detalhes.produto_id " +
            "group by 1,produto.descricao,estoque_minimo_kg " +
            "having sum(quantidade_kg) < estoque_minimo_kg " +
            "order by 1 asc,2 asc,3 desc";
            
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Tipo Alerta","Produto","Quantidade", "Observação"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                String tipoAlerta = rsSelect.getString("tipo");
                String produto = rsSelect.getString("descricao");
                String quantidadeKg = rsSelect.getString("quantidade_kg");
                String obs = rsSelect.getString("obs");
          
                data[i][0] = tipoAlerta;
                data[i][1] = produto;
                data[i][2] = quantidadeKg;
                data[i][3] = obs;
        
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro buscar alertas: "+e);
            return new String[0][0];
        }
    }
    
}
