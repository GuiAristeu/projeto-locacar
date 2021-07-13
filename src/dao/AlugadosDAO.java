/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Alugados;
import beans.Carros;
import beans.Clientes;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Guilh
 */
public class AlugadosDAO {
    private Conexao conexao;
    private Connection conn;
    
    public AlugadosDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void alugar(Alugados alugados){
        String sql = "INSERT INTO alugados(clientescpf, carrosid, data) VALUES"
                + "(?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setLong(1, alugados.getClientescpf());
            stmt.setInt(2, alugados.getCarrosid().getId());
            stmt.setDate(3, new java.sql.Date(alugados.getData().getTime()));
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao alugar carro: "+e.getMessage());
        }
    }
    
    public List<Alugados> getAlugados(){
        String sql = "SELECT clientescpf, data, carrosid, modelo FROM alugados"
                + " INNER JOIN carros ON alugados.carrosid = carros.id";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Alugados> lista = new ArrayList<>();
            while(rs.next()){
                Alugados alugados = new Alugados();
                Carros carros = new Carros();
                //Clientes clientes = new Clientes();
                
                //alugados.setId(rs.getInt("id"));
                alugados.setClientescpf(rs.getLong("clientescpf"));
                carros.setId(rs.getInt("carrosid"));
                carros.setModelo(rs.getString("modelo"));
                alugados.setCarrosid(carros);
                alugados.setData(rs.getDate("data"));
                
                lista.add(alugados);  
            }
            return lista;
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
            return null;
        }
    }
}
