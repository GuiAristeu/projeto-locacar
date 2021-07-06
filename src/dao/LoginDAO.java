/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Login;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilh
 */
public class LoginDAO {
    private Conexao conexao;
    private Connection conn;
    
    public LoginDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
     
    public boolean checkLogin(String usuario, String senha){
        String sql = "SELECT * FROM login WHERE usuario = ? and senha = ?";
        ResultSet rs = null;
        boolean check = false;
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if(rs.next()){
                check = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro: "+e.getMessage());
        }
        return check;
    }
    
    public Login getLogin(){
        String sql = "SELECT * FROM login";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Login login = new Login();
            rs.first();
            login.setUsuario(rs.getString("usuario"));
            login.setSenha(rs.getString("senha"));
            return login;
        } catch (Exception e) {
            return null;
        }
    }
    
    public void editar(Login login) {
        String sql = "UPDATE login SET usuario=?, senha=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, login.getUsuario());
            stmt.setString(2, login.getSenha());
            stmt.execute();
            JOptionPane.showMessageDialog(null,"Dados alterados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao alterar dados: " +e.getMessage());
            JOptionPane.showMessageDialog(null,"Erro ao alterar dados: "+e.getMessage());
        }
    }
}
