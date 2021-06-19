/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Carros;
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
public class CarrosDAO {
    private Conexao conexao;
    private Connection conn;
    
    //Construtor da classe
    public CarrosDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
        
    }
    
    public void inserir(Carros carros){
        String sql = "INSERT INTO carros(marca, modelo, cor, quilometragem, valordia, valorkm, status) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)";
        try{
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1, carros.getMarca());
        stmt.setString(2, carros.getModelo());
        stmt.setString(3, carros.getCor());
        stmt.setInt(4, carros.getQuilometragem());
        stmt.setInt(5, carros.getValordia());
        stmt.setInt(6, carros.getValorkm());
        stmt.setBoolean(7, carros.isStatus());
        stmt.execute();
        JOptionPane.showMessageDialog(null,"CARRO CADASTRADO COM SUCESSO");
        }
        catch(Exception e){
            System.out.println("Erro ao inserir carro: " + e.getMessage());
        }
    }
    
    public void editar(Carros carros){
        String sql = "UPDATE carros SET marca=?, modelo=?, cor=?, quilometragem=?, valordia=?, valorkm=?, status=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, carros.getMarca());
            stmt.setString(2, carros.getModelo());
            stmt.setString(3, carros.getCor());
            stmt.setInt(4, carros.getQuilometragem());
            stmt.setInt(5, carros.getValordia());
            stmt.setInt(6, carros.getValorkm());
            stmt.setBoolean(7, carros.isStatus());
            stmt.setInt(8, carros.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null,"CARRO EDITADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("Erro ou editar carro: " +e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM carros WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            JOptionPane.showMessageDialog(null,"CARRO EXCLUIDO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("Erro ao excluir carro: "+e.getMessage());
        }
    }
    
    public Carros getCarros(int id){
        String sql = "SELECT * FROM carros WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Carros carros = new Carros();
            rs.first();
            carros.setId(id);
            carros.setMarca(rs.getString("marca"));
            carros.setModelo(rs.getString("modelo"));
            carros.setCor(rs.getString("cor"));
            carros.setQuilometragem(rs.getInt("quilometragem"));
            carros.setValordia(rs.getInt("valordia"));
            carros.setValorkm(rs.getInt("valorkm"));
            return carros;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Carros> getCarros(String modelo){
        String sql = "SELECT * FROM carros WHERE modelo LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + modelo + "%");
            ResultSet rs = stmt.executeQuery();
            List<Carros> listaCarros = new ArrayList<>();
            while(rs.next()){
                Carros carros = new Carros();
                carros.setId(rs.getInt("id"));
                carros.setMarca(rs.getString("marca"));
                carros.setModelo(rs.getString("modelo"));
                carros.setCor(rs.getString("cor"));
                carros.setQuilometragem(rs.getInt("quilometragem"));
                carros.setValordia(rs.getInt("valordia"));
                carros.setValorkm(rs.getInt("valorkm"));
                listaCarros.add(carros);
            }
            return listaCarros;
        } catch (Exception e) {
            return null;
        }
    }
}
