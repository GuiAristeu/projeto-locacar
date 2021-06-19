/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Guilh
 */
public class Conexao {
    public Connection getConexao()
    {
        try {
            //Tentar estabelecer a conexao
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/locacar?serverTimezone=UTC", //Linha de conexao
                    "root", //Usuário do MySQL
                    "" //Senha do MySQL
            );
            return conn;
        } catch (Exception e) {
            //Se der derro na hora de conectar
            System.out.println("Erro ao conectar "+e.getMessage());
            return null;
        }
    }
}
