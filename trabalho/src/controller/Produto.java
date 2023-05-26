package controller;

import model.ProdutoModel;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;

public class Produto{

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ProdutoModel> lista = new ArrayList<>();

    public void cadastrarProduto(ProdutoModel objprodutomodel) {
        String sql = "insert into produto(nome_produto, quantidade_produto) values (?,?)";

        conn = new Conexao().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objprodutomodel.getNome_produto());
            pstm.setInt(2, objprodutomodel.getQuantidade_produto());

            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Produto Cadastrar:" + erro);
        }
    }

    public ArrayList<ProdutoModel> PesquisarProduto() {
        String sql = "select * from produto";
        
        conn = new Conexao().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                ProdutoModel objprodutoModel = new ProdutoModel();
                objprodutoModel.setId_produto(rs.getInt("Id_produto"));
                objprodutoModel.setNome_produto(rs.getString("nome_produto"));
                objprodutoModel.setQuantidade_produto(rs.getInt("quantidade_produto"));
            
                lista.add(objprodutoModel);
            } 
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Produto Pesquisar:" + erro);
        }
        return lista;
    }
    
    public void alterarProduto(ProdutoModel objprodutomodel){
        String sql="update produto set nome_produto = ?, quantidade_produto = ? where id_produto = ?";
        conn = new Conexao().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objprodutomodel.getNome_produto());
            pstm.setInt(2, objprodutomodel.getQuantidade_produto());
            pstm.setInt(3, objprodutomodel.getId_produto());

            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Produto Alterar:" + erro);
        }
        
    }
    public void excluirProduto(ProdutoModel objprodutomodel){
        String sql="delete from produto where id_produto=?";
        
        conn = new Conexao().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objprodutomodel.getId_produto());

            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Produto Excluir:" + erro);
        }
    }
}