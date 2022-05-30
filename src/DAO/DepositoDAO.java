/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.DepositoModel;
import Util.ConexaoBD;
import Util.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author forster
 */
public class DepositoDAO implements IDAO<DepositoModel> {

    @Override
    public boolean create(DepositoModel objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[][] read(String filtro) {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sqlCount;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sqlCount = "select count(id) as qtde FROM projeto_integrador_vi.deposito where ativo = 'S';";
            }else{
                sqlCount = "select count(id) as qtde FROM projeto_integrador_vi.deposito where ativo = 'S'"
                        + " and descricao ilike '%" + filtro + "%';";
            }
            
            
            ResultSet rsCount = st.executeQuery(sqlCount);
            
            int quantidadeRegistros = 0;
            
            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }
            
            String sql;
            
            if(filtro.isBlank() || filtro.isEmpty()){
                sql = "select * FROM projeto_integrador_vi.deposito where ativo = 'S' order by id;";
            }else{
                sql = "select * FROM projeto_integrador_vi.deposito where ativo = 'S'"
                        + " and descricao ilike '%" + filtro + "%';";
            }
            
            ResultSet rsSelect = st.executeQuery(sql);
            
            String[] colunas = new String[]{"Id","Descrição","Endereço"};
            
            String [][] data = new String[quantidadeRegistros][colunas.length];
            
            int i = 0;
            
            while(rsSelect.next()){
                int id = rsSelect.getInt("id");
                String descricao = rsSelect.getString("descricao");
                String endereco = rsSelect.getString("endereco");
          
                data[i][0] = id+"";
                data[i][1] = descricao;
                data[i][2] = endereco;
        
                
                i++;
                
            }
            
            return data;
            
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os registros: "+e);
            return new String[0][0];
        }
    }

    @Override
    public boolean update(DepositoModel objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[] readComboBox() {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sqlCount = "select count(id) as qtde from projeto_integrador_vi.deposito where ativo = 'S';";

            ResultSet rsCount = st.executeQuery(sqlCount);

            int quantidadeRegistros = 0;

            while(rsCount.next()){
                quantidadeRegistros = rsCount.getInt("qtde");
            }

            String sql = "select id||' - '||descricao as info from projeto_integrador_vi.deposito where ativo = 'S' order by id;";

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
