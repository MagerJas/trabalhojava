package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import model.UsuarioModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class UsuarioController {

    Connection conn;

    public ResultSet autenticaçao(UsuarioModel objusuariomodel) {
        conn = new Conexao().conectaBD();

        try {
            String sql = "select * from usuario where Nome_Usuario=? and Senha_Usuario=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objusuariomodel.getNome_usuario());
            pstm.setString(2, objusuariomodel.getSenha_usuario());
            
            ResultSet rs= pstm.executeQuery();
            return rs;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioController::" + erro);
            return null;
        }

    }
}
