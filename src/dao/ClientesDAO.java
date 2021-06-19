/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Clientes;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Guilh
 */
public class ClientesDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ClientesDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    public void inserir(Clientes clientes){
        String sql = "INSERT INTO clientes(cpf, nome) VALUES (?, ?)";
        try {
           PreparedStatement stmt = this.conn.prepareStatement(sql);
           stmt.setLong(1, clientes.getCpf());
           stmt.setString(2, clientes.getNome());
           stmt.execute();
           JOptionPane.showMessageDialog(null,"CLIENTE CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("Erro ao inserir cliente: "+ e.getMessage());
        }
    }
    
    public void editar(Clientes clientes){
        String sql = "UPDATE clientes SET cpf=?, nome=? WHERE cpf=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setLong(1, clientes.getCpf());
            stmt.setString(2, clientes.getNome());
            stmt.setLong(3, clientes.getCpf());
            stmt.execute();
            JOptionPane.showMessageDialog(null,"CLIENTE EDITADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("Erro ou editar cliente: " +e.getMessage());
        }
    }
    
    public void excluir(long cpf){
        String sql = "DELETE FROM clientes WHERE cpf = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setLong(1, cpf);
            stmt.execute();
            JOptionPane.showMessageDialog(null,"CLIENTE EXCLUIDO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente: "+e.getMessage());
        }
    }
    
    public Clientes getClientes(long cpf){
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setLong(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Clientes clientes = new Clientes();
            rs.first();
            clientes.setCpf(cpf);
            clientes.setNome(rs.getString("nome"));
            return clientes;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Clientes> getClientes(String nome){
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            List<Clientes> listaClientes = new ArrayList<>();
            while(rs.next()){
                Clientes clientes = new Clientes();
                clientes.setCpf(rs.getLong("cpf"));
                clientes.setNome(rs.getString("nome"));
                listaClientes.add(clientes);
            }
            return listaClientes;
        } catch (Exception e) {
            return null;
        }
    }
}
