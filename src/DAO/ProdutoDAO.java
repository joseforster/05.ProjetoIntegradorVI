/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ProdutoModel;
import Util.ConexaoBD;
import Util.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author forster
 */
public class ProdutoDAO implements IDAO<ProdutoModel> {

    @Override
    public boolean create(ProdutoModel objeto) {
    try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "insert into projeto_integrador_vi.produto values (default, "
                    + "'"+objeto.getDescricao()+"', "
                    +objeto.getValorVenda()+" , "
                    +objeto.getEstoqueMinimo()+", default);";
            
            System.out.println(sql);
            
            st.executeUpdate(sql);
            
            return true;
            
            
        }
        catch(Exception e){
            System.out.println("Erro ao inserir registro: "+e);
            return false;
        }    
    }

    @Override
    public String[][] read(String filtro) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sqlCount = "select count(id) as qtde FROM projeto_integrador_vi.produto where ativo = 'S';";
            }else{
                sqlCount = "select count(id) as qtde FROM projeto_integrador_vi.produto where ativo = 'S'"
                        + " and descricao ilike '%" + filtro + "%';";
            }
            
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sql = "select * FROM projeto_integrador_vi.produto where ativo = 'S' order by id;";
            }else{
                sql = "select * FROM projeto_integrador_vi.produto where ativo = 'S'"
                        + " and descricao ilike '%" + filtro + "%';";
            }
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Id","Descrição","Valor Venda", "Estoque Mínimo"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                int id = rsSelect.getInt("id");
                String descricao = rsSelect.getString("descricao");
                String valorVenda = rsSelect.getString("valor_venda");
                String estoqueMinimo = rsSelect.getString("estoque_minimo_kg");
                
          
                data[i][0] = id+"";
                data[i][1] = descricao;
                data[i][2] = valorVenda;
                data[i][3] = estoqueMinimo;
                
        
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    }

    @Override
    public boolean update(ProdutoModel objeto) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update projeto_integrador_vi.produto set "
                    + "descricao = '" + objeto.getDescricao()+"'"
                    + ", valor_venda = " + objeto.getValorVenda()
                    + ", estoque_minimo_kg = " + objeto.getEstoqueMinimo()
                    + " where id = " + objeto.getId();
            
            System.out.println(sql);
            
            st.executeUpdate(sql);
            
            return true;
            
        }catch(Exception e){
            System.out.println("Erro ao editar produto:"+e);
            
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update projeto_integrador_vi.produto set ativo = 'N' where id = "+id;
            
            System.out.println(sql);
            
            st.executeUpdate(sql);
            
            return true;
            
        }catch(Exception e){
            
            System.out.println("Erro ao inativar produto: " + e);
            
            return false;
        }
    }

    @Override
    public String[] readComboBox() {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sqlCount = "select count(id) as qtde from projeto_integrador_vi.produto where ativo = 'S';";

            ResultSet rsCount = st.executeQuery(sqlCount);

            int quantidadeRegistros = 0;

            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }

            String sql = "select id||' - '||descricao||' - Valor Venda: R$ '||produto.valor_venda as info from projeto_integrador_vi.produto where ativo = 'S' order by id;";

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
