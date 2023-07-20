/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import dao.ClienteDAO;
import java.util.List;
import model.Cliente;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {

    private final JdbcTemplate connection;

    public Conexao() {

        BasicDataSource dataSource = new BasicDataSource();
        // setDriverClassName recebe o drive para conexão do banco // Mysql = com.mysql.cj.jdbc.Driver
        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // setUrl cria um arquivo de com o nome do banco que vc especificar no argumento // Mysql = jdbc:mysql://localhost:3306/nomeBanco
        dataSource​.setUrl("jdbc:mysql://localhost:3306/consignado");

        // setUsername recebe o nome do usuario como argumento
        dataSource​.setUsername("root");

        // setPassword recebe a senha do usuario como argumento
        dataSource​.setPassword("40028922");

        this.connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        conexao.getConnection();


    }
}
