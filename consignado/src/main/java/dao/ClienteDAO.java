/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.awt.HeadlessException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Conexao;

/**
 *
 * @author lucka
 */
public class ClienteDAO {

    JdbcTemplate con;

    public ClienteDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }
    private Integer id = 0;

    public void obterId(Integer id) {
        this.id = id;
    }

    public void cadastrarCliente(Cliente cliente) {
        try {
            cliente.setValorTotalEmprestimo(cliente.getValorTotalDivida());
            List<Cliente> clientes = con.query(String.format("""
                        SELECT * FROM cliente 
                        WHERE whatsApp = '%s';""", cliente.getWhatsApp()),
                    new BeanPropertyRowMapper<>(Cliente.class));
            if (clientes.isEmpty()) {
                con.execute(String.format("""
                    INSERT INTO cliente 
                        (nome, whatsApp, dataEmprestimo, valorTotalDivida, dataPagamentoMensal, porcentagem, imagem, valorTotalEmprestimo)
                    VALUES('%s', '%s', '%s', '%s', '%s', %d, '%s', '%s');
                    """,
                        cliente.getNome(),
                        cliente.getWhatsApp(),
                        cliente.getDataEmprestimo(),
                        cliente.getValorTotalDivida(),
                        cliente.getDataPagamentoMensal(),
                        cliente.getPorcentagem(),
                        cliente.getImagem(),
                        cliente.getValorTotalEmprestimo()));
                List<Cliente> ultimoCLiente = con.query("SELECT * FROM cliente", new BeanPropertyRowMapper<>(Cliente.class));
                Integer ultimoID = ultimoCLiente.get(ultimoCLiente.size() - 1).getIdCliente();
                String padraoFormatacao = "dd/MM/yyyy";
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padraoFormatacao);

                LocalDate dataEmprestimoLocalDate = LocalDate.parse(cliente.getDataEmprestimo(), formatador);
                LocalDate dataPagamentoLocalDate2 = LocalDate.parse(cliente.getDataPagamentoMensal(), formatador);

                long diferenca = ChronoUnit.DAYS.between(dataEmprestimoLocalDate, dataPagamentoLocalDate2);

                if (diferenca < 30) {
                    con.execute(String.format("""
                    UPDATE cliente SET valorPagamentoMensal = ( (valorTotalDivida * (porcentagem / 100) ) / 30) * %d WHERE idCliente = %d;
                                          """, diferenca, ultimoID));
                    JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso", "Sucesso", 1);
                } else {
                    con.execute(String.format("""
                    UPDATE cliente SET valorPagamentoMensal = (valorTotalDivida * (porcentagem / 100)) WHERE idCliente = %d;
                                          """, ultimoID));
                    JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso", "Sucesso", 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Já existe cliente cadastrado com esse número: \n"
                        + "Cliente: " + clientes.get(0).getNome()
                        + "\nWhatsApp: " + clientes.get(0).getWhatsApp(), "ATENÇÃO", 2);
            }

        } catch (Exception ex) {
            System.out.println("ERRO no cadastro de clientes!! \nERRO: " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarCliente(Cliente cliente) {
        try {
            List<Cliente> validarAtualizacao = con.query(String.format("SELECT * FROM cliente WHERE idCliente = %d;", id), new BeanPropertyRowMapper<>(Cliente.class));
            Cliente clienteValidacao = validarAtualizacao.get(0);
            if (!Objects.equals(cliente.getValorTotalDivida(), clienteValidacao.getValorTotalDivida())) {
                con.execute(String.format("""
                UPDATE cliente SET rendimento = %d
                WHERE idCliente = %d;
                """,
                        0, id));
            }
            if (cliente.getValorTotalDivida() != 0) {
                cliente.setValorTotalEmprestimo(cliente.getValorTotalDivida());
                con.execute(String.format("""
                               UPDATE cliente SET 
                                    valorTotalEmprestimo = '%s'
                                WHERE idCliente = %d;
                                """,
                        cliente.getValorTotalEmprestimo(),
                        id
                ));
            }
            System.out.println(String.format("cliente.setValorTotalEmprestimo(%f);", cliente.getValorTotalDivida()));
            con.execute(String.format("""
                               UPDATE cliente SET 
                                    nome = '%s', 
                                    whatsApp = '%s', 
                                    dataEmprestimo = '%s', 
                                    valorTotalDivida = '%s', 
                                    dataPagamentoMensal = '%s', 
                                    porcentagem = %d ,
                                    imagem = '%s'
                                WHERE idCliente = %d;
                                """,
                    cliente.getNome(),
                    cliente.getWhatsApp(),
                    cliente.getDataEmprestimo(),
                    cliente.getValorTotalDivida(),
                    cliente.getDataPagamentoMensal(),
                    cliente.getPorcentagem(),
                    cliente.getImagem(),
                    id
            ));

            String padraoFormatacao = "dd/MM/yyyy";
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padraoFormatacao);

            LocalDate dataEmprestimoLocalDate = LocalDate.parse(cliente.getDataEmprestimo(), formatador);
            LocalDate dataPagamentoLocalDate = LocalDate.parse(cliente.getDataPagamentoMensal(), formatador);

            long diferenca = ChronoUnit.DAYS.between(dataEmprestimoLocalDate, dataPagamentoLocalDate);

            if (diferenca < 30) {
                con.execute(String.format("""
                    UPDATE cliente SET valorPagamentoMensal = ( (valorTotalDivida * (porcentagem / 100) ) / 30) * %d WHERE idCliente = %d;
                                          """, diferenca, id));
            } else {
                con.execute(String.format("""
                    UPDATE cliente SET valorPagamentoMensal = (valorTotalDivida * (porcentagem / 100)) WHERE idCliente = %d;
                                          """, id));
            }

            JOptionPane.showMessageDialog(null, "Cliente Atualizado com Sucesso", "Sucesso", 1);
        } catch (HeadlessException | DataAccessException ex) {
            System.out.println("ERRO ao atualizar cliente!! \nERRO: " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("CLIENTE ATUALIZADO");
        System.out.println(cliente);
    }

    public void registrarPagamento(Cliente cliente) {
        try {

            List<Cliente> clientes = con.query(String.format("""
                    SELECT * FROM cliente WHERE idCliente = %d
                    """,
                    cliente.getIdCliente()),
                    new BeanPropertyRowMapper<>(Cliente.class));

            Cliente clienteBD = clientes.get(0);

            con.execute(String.format("""
                               UPDATE cliente SET 
                                    dataPagamentoMensal = '%s',
                                    valorTotalDivida = '%s',
                                    valorPagamentoMensal = '%s'
                                WHERE idCliente = %d;
                                """,
                    cliente.getDataPagamentoMensal(),
                    cliente.getValorTotalDivida(),
                    cliente.getValorPagamentoMensal(),
                    cliente.getIdCliente()
            ));

            if (cliente.getValorPagamentoMensal() < clienteBD.getValorPagamentoMensal()) {
                con.execute(String.format("""
                    UPDATE cliente SET valorTotalDivida = ('%s' - '%s') + valorTotalDivida
                    WHERE idCliente = %d;
                        """, clienteBD.getValorPagamentoMensal(), cliente.getValorPagamentoMensal(), cliente.getIdCliente()
                ));
            }
            con.execute(String.format("""
                    UPDATE cliente SET valorPagamentoMensal = (valorTotalDivida * (porcentagem / 100))
                    WHERE idCliente = %d;
                        """, cliente.getIdCliente()
            ));

            con.execute(String.format("""
                    INSERT INTO historicoPagamento (fkCliente, dataPagamentoMensal, valorPagamentoMensal)
                    VALUES(%d, '%s', '%s');
                        """,
                    cliente.getIdCliente(),
                    getDataPagamentoHistorico(cliente),
                    cliente.getValorPagamentoMensal()));

            con.execute(String.format("""
                    UPDATE cliente SET rendimento = COALESCE(rendimento, 0) + %.0f
                    WHERE idCliente = %d;
                        """,
                    cliente.getValorPagamentoMensal(), cliente.getIdCliente()));

            JOptionPane.showMessageDialog(null, "Pagamento registrado com Sucesso", "Sucesso", 1);
        } catch (HeadlessException | DataAccessException ex) {
            System.out.println("ERRO ao registrar o pagademto!! \nERRO: " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validarCadastro(Cliente cliente) {
        List<Cliente> clientes = con.query(String.format("""
                        SELECT * FROM cliente WHERE idCliente = '%d';""",
                id), new BeanPropertyRowMapper<>(Cliente.class));
        if (clientes.isEmpty()) {
            cadastrarCliente(cliente);
        } else {
            String mensagem = "Tem certeza que deseja atualizar o cliente:\n" + cliente.getNome() + "?";
            Integer confirmar = JOptionPane.showConfirmDialog(null, mensagem, "EXCLUIR", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                atualizarCliente(cliente);
            }
        }
    }

    public void deletarCliente(Cliente cliente) {
        try {
            List<Cliente> clientes = con.query(String.format("""
                                SELECT * FROM cliente WHERE idCliente = %d; 
                                """,
                    id
            ), new BeanPropertyRowMapper<>(Cliente.class));

            con.execute(String.format("""
                    DELETE FROM historicoPagamento 
                        WHERE fkCliente = %d;
                        """,
                    id
            ));
            if (!clientes.isEmpty()) {
                con.execute(String.format("""
                               DELETE FROM cliente WHERE idCliente = %d;
                                """,
                        id
                ));
                JOptionPane.showMessageDialog(null, "Cliente Deletado com Sucesso", "Sucesso", 1);
            }

        } catch (Exception ex) {
            System.out.println("ERRO ao deletear cliente!! \nERRO: " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente getPesquisarCliente(String busca) {
        Cliente cliente = null;
        try {
            List<Cliente> clientes = con.query(String.format("""
                                        SELECT * FROM cliente 
                                               WHERE 
                                                   nome = '%s' OR 
                                                   whatsApp = '%s' OR
                                                   idCliente = '%s';
                                        """, busca, busca, busca), new BeanPropertyRowMapper<>(Cliente.class));
            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cliente não existe", "ALERTA", 2);
            } else {
                cliente = clientes.get(0);
                return cliente;
            }
        } catch (Exception ex) {
            System.out.println("ERRO na busca de clientes!! \nERRO: " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return cliente;
        }
        return cliente;
    }

    public List<Cliente> getClientesForTable() {
        try {
            List<Cliente> clientes = con.query("SELECT * FROM cliente;", new BeanPropertyRowMapper<>(Cliente.class));
            return clientes;
        } catch (Exception ex) {
            System.out.println("ERRO no metodo getClientes!! \nERRO: " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Cliente> getClientesForTable(String busca) {
        try {
            List<Cliente> clientes = con.query(String.format(
                    "SELECT * FROM cliente "
                    + "WHERE "
                    + "nome = '%s' OR "
                    + "whatsApp = '%s';", busca, busca), new BeanPropertyRowMapper<>(Cliente.class));
            if (clientes.isEmpty()) {
                clientes = con.query(String.format(
                        "SELECT * FROM cliente "
                        + "WHERE "
                        + "idCliente = %d;", Integer.valueOf(busca)), new BeanPropertyRowMapper<>(Cliente.class));
            }
            return clientes;
        } catch (NumberFormatException | DataAccessException ex) {
            System.out.println("ERRO no metodo getClientes(String busca)!! \nERRO: " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Double obterValorPagamento(Cliente cliente) {
        List<Cliente> clientes = con.query(String.format("SELECT valorPagamentoMensal FROM cliente WHERE whatsApp = '%s';", cliente.getWhatsApp()), new BeanPropertyRowMapper(Cliente.class));
        Double valorPagamento = clientes.get(0).getValorPagamentoMensal();
        return valorPagamento;
    }

    public String getDataPagamentoHistorico(Cliente cliente) {
        String dataEmprestimoString = cliente.getDataPagamentoMensal();

        String padraoFormatacao = "dd/MM/yyyy";
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padraoFormatacao);

        LocalDate dataEmprestimoLocalDate = LocalDate.parse(dataEmprestimoString, formatador);
        LocalDate dataPagamentoLocalDate = dataEmprestimoLocalDate.plus(Period.ofMonths(-1));
        String dataPagamentoString = dataPagamentoLocalDate.format(formatador);
        return dataPagamentoString;
    }

    public List<Cliente> getClientesHistorico(Integer id) {
        try {
            List<Cliente> clientes = con.query(String.format(
                    "select * from historicoPagamento where fkCliente = %d;",
                    id),
                    new BeanPropertyRowMapper<>(Cliente.class));
            return clientes;
        } catch (Exception ex) {
            System.out.println("ERRO no metodo getClientes!! \nERRO: " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Double getRendimentoTotalCliente(Integer id) {
        List<Cliente> clientes = con.query(String.format(
                "select * from historicoPagamento where fkCliente = %d;",
                id),
                new BeanPropertyRowMapper<>(Cliente.class));
        Double rendimento = 0.0;
        for (Cliente cliente : clientes) {
            rendimento += cliente.getValorPagamentoMensal();
        }
        return rendimento;
    }
}
