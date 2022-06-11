/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CompraModel;
import Util.ConexaoBD;
import Util.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author forster
 */
public class CompraDAO implements IDAO<CompraModel>{

    @Override
    public boolean create(CompraModel objeto) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "insert into projeto_integrador_vi.compra values (default, default, null,"
                    + objeto.getFornecedor().getId()+ ", default, default) returning compra.id";
            
            System.out.println(sql);
            
            ResultSet rs = st.executeQuery(sql);
            
            int idCompra = 0;
            
            while(rs.next()){
                idCompra = Integer.parseInt(rs.getString(1));
            }
            
            for(var produto : objeto.getCompraProdutos()){
                
                sql = "insert into projeto_integrador_vi.compra_produto values (default, "+
                      idCompra+", "+
                      produto.getProduto().getId()+","+
                      produto.getQuantidade()+","+
                      produto.getValorUnitario()+","+
                      produto.getValorTotal()
                      +");";
                
                System.out.println(sql);
                        
                st.executeUpdate(sql);
                
                sql = "insert into projeto_integrador_vi.produto_detalhes values (default, "
                        + produto.getProduto().getId() + ", "
                        + produto.getDepositoArea().getId() +", "
                        + produto.getQuantidade() + ", '"
                        + produto.getValidade().toString()
                        + "' )";
                
                System.out.println(sql);
                        
                st.executeUpdate(sql);
            }
            
            return true;
                    
                    
        }catch(Exception e){
            System.out.println("Erro ao criar registro: " + e);
            
            return false;
        }
    }

    @Override
    public String[][] read(String filtro) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount = "select count(id) as qtde from  projeto_integrador_vi.compra where cancelado = 'N'";
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql = "select compra.id, to_char(dt_criacao, 'DD/MM/YYYY') as dt_criacao, to_char(dt_recebimento, 'DD/MM/YYYY') as dt_recebimento, " +
            "sum(valor_total) as valor_total, " +
            "fornecedor.nome, recebido from  projeto_integrador_vi.compra " +
            "inner join projeto_integrador_vi.fornecedor ON fornecedor.id = compra.fornecedor_id " +
            "inner join projeto_integrador_vi.compra_produto ON compra_produto.compra_id = compra.id " +
            "where cancelado = 'N' " +
            "group by 1,2,3,5,6";
            
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Id","Data Pedido","Data Recebimento","Valor Total","Fornecedor", "Recebido"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                int id = rsSelect.getInt("id");
                String dtPedido = rsSelect.getString("dt_criacao");
                String dtRecebimento = rsSelect.getString("dt_recebimento");
                String valorTotal = rsSelect.getString("valor_total");
                String fornecedor = rsSelect.getString("nome");
                String recebido = rsSelect.getString("recebido");

          
                data[i][0] = id+"";
                data[i][1] = dtPedido;
                data[i][2] = dtRecebimento;
                data[i][3] = valorTotal;
                data[i][4] = fornecedor;
                data[i][5] = recebido;
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    }

    @Override
    public boolean update(CompraModel objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update projeto_integrador_vi.compra set cancelado = 'S' where id = "+id;
            
            System.out.println(sql);
            
            st.executeUpdate(sql);
            
            return true;
            
        }catch(Exception e){
            
            System.out.println("Erro ao inativar registro: " + e);
            
            return false;
        }    
    }

    @Override
    public String[] readComboBox() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String[][] detalhesCompra(int idCompra){
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount = "select count(compra_produto.id) as qtde from projeto_integrador_vi.compra_produto " +
            "inner join projeto_integrador_vi.produto on produto_id = produto.id " +
            "where compra_id = "+ idCompra;
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql = "select produto.descricao, quantidade_kg, valor_kg, valor_total from projeto_integrador_vi.compra_produto " +
            "inner join projeto_integrador_vi.produto on produto_id = produto.id " +
            "where compra_id = "+ idCompra;
            
            
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
    
    
    public boolean finalizarCompra (int id){
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update projeto_integrador_vi.compra " +
            "set dt_recebimento = cast((select now()) as date), recebido = 'S' " +
            "where id = "+id;
            
            st.executeUpdate(sql);
            
            return true;
            
        }catch(Exception e){
            System.out.println("Erro ao finalizar registro: " + e);
            
            return false;
        }
    }
    
}
