/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import dao.ClienteDAO;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.CampoNumeros;
import model.Cliente;

/**
 *
 * @author lucka
 */
public class ClienteGUI extends javax.swing.JFrame {

    RegistrarPagamento enviar;
    ClienteDAO clienteDAO = new ClienteDAO();
    Cliente cliente = new Cliente();
    String caminhoFoto = "";

    /**
     * Creates new form ClienteGUI
     */
    public ClienteGUI() {

        setIcon();
        initComponents();
        setLocationRelativeTo(null);
        botaoRegistrarPagamento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        campoImagem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        campoDataEmprestimo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        campoPesquisar.requestFocus();
        campoWhats.setDocument(new CampoNumeros(11));
        campoValorEmprestimo.setDocument(new CampoNumeros(11));
        campoPorcentagem.setDocument(new CampoNumeros(3));
        lerTabela();

    }

    public void lerTabela() {
        configurarTabela();
        DefaultTableModel model = (DefaultTableModel) tabelaClientes.getModel();
        for (Cliente cliente : clienteDAO.getClientesForTable()) {

            /*MASCARA DINHEIRO*/
            NumberFormat formatoMoeda = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));
            if (cliente.getValorTotalDivida() < 0 || cliente.getValorPagamentoMensal() < 0) {
                cliente.setValorTotalDivida(0.0);
                cliente.setValorPagamentoMensal(0.0);
            }

            String valorDividaFormatado = formatoMoeda.format(cliente.getValorTotalDivida());
            String valorPagamentoMensalFormatado = formatoMoeda.format(clienteDAO.obterValorPagamento(cliente));

            /*MASCARA WHATSAPP*/
            String whatsAppFormatado = "";
            try {
                MaskFormatter formatador = new MaskFormatter("(##) ##### - ####");
                formatador.setValueContainsLiteralCharacters(false);
                whatsAppFormatado = formatador.valueToString(cliente.getWhatsApp());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Boolean validarDivida = valorDividaFormatado.equals("R$ 0,00") && valorPagamentoMensalFormatado.equals("R$ 0,00");
            if (validarDivida) {
                valorDividaFormatado = "FINALIZADO";
                valorPagamentoMensalFormatado = "FINALIZADO";
            }

            model.addRow(new Object[]{
                cliente.getIdCliente(),
                cliente.getNome(),
                whatsAppFormatado,
                cliente.getDataEmprestimo(),
                valorDividaFormatado,
                cliente.getDataPagamentoMensal(),
                valorPagamentoMensalFormatado
            });
        }
    }

    public void lerTabela(String busca) {

        configurarTabela();
        DefaultTableModel model = (DefaultTableModel) tabelaClientes.getModel();
        for (Cliente cliente : clienteDAO.getClientesForTable(busca)) {
            /*MASCARA DINHEIRO*/
            NumberFormat formatoMoeda = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String valorDividaFormatado = formatoMoeda.format(cliente.getValorTotalDivida());
            String valorPagamentoMensalFormatado = formatoMoeda.format(cliente.getValorPagamentoMensal());

            /*MASCARA WHATSAPP*/
            String whatsAppFormatado = "";
            try {
                MaskFormatter formatador = new MaskFormatter("(##) ##### - ####");
                formatador.setValueContainsLiteralCharacters(false);
                whatsAppFormatado = formatador.valueToString(cliente.getWhatsApp());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            model.addRow(new Object[]{
                cliente.getIdCliente(),
                cliente.getNome(),
                whatsAppFormatado,
                cliente.getDataEmprestimo(),
                valorDividaFormatado,
                cliente.getDataPagamentoMensal(),
                valorPagamentoMensalFormatado
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        botaoSalvar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        campoPesquisar = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JLabel();
        botaoLimpar = new javax.swing.JButton();
        botaoRegistrarPagamento = new javax.swing.JButton();
        campoImagem = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        campoDataEmprestimo = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        campoDataPagamento = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        campoPorcentagem = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        campoValorEmprestimo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoWhats = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Cliente");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 700));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Clientes");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        botaoSalvar.setBackground(new java.awt.Color(204, 204, 204));
        botaoSalvar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botaoSalvar.setForeground(new java.awt.Color(0, 0, 0));
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoExcluir.setBackground(new java.awt.Color(204, 204, 204));
        botaoExcluir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botaoExcluir.setForeground(new java.awt.Color(0, 0, 0));
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        campoPesquisar.setBackground(new java.awt.Color(255, 255, 255));
        campoPesquisar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        campoPesquisar.setForeground(new java.awt.Color(0, 0, 0));
        campoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPesquisarActionPerformed(evt);
            }
        });
        campoPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoPesquisarKeyPressed(evt);
            }
        });

        botaoPesquisar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botaoPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons8-search-36.png"))); // NOI18N
        botaoPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoPesquisarMouseClicked(evt);
            }
        });

        botaoLimpar.setBackground(new java.awt.Color(204, 204, 204));
        botaoLimpar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botaoLimpar.setForeground(new java.awt.Color(0, 0, 0));
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoRegistrarPagamento.setBackground(new java.awt.Color(204, 204, 204));
        botaoRegistrarPagamento.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        botaoRegistrarPagamento.setForeground(new java.awt.Color(0, 0, 0));
        botaoRegistrarPagamento.setText("<html>Registrar<br/>Pagamento</html>");
        botaoRegistrarPagamento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoRegistrarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRegistrarPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoPesquisar)
                .addGap(0, 0, 0)
                .addComponent(campoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoRegistrarPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoRegistrarPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(campoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(botaoPesquisar)
                        .addGap(7, 7, 7))))
        );

        campoImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        campoImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/perspective_matte-59-128x128.png"))); // NOI18N
        campoImagem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        campoImagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoImagemMouseClicked(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        tabelaClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabelaClientes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "WhatsApp", "Data da Divida", "Valor Total", "Data do Pagamento", "Valor R$"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClientesMouseClicked(evt);
            }
        });
        tabelaClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaClientesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaClientes);
        if (tabelaClientes.getColumnModel().getColumnCount() > 0) {
            tabelaClientes.getColumnModel().getColumn(0).setMinWidth(40);
            tabelaClientes.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        campoDataEmprestimo.setBackground(new java.awt.Color(255, 255, 255));
        campoDataEmprestimo.setForeground(new java.awt.Color(255, 255, 255));
        campoDataEmprestimo.setToolTipText("");
        campoDataEmprestimo.setDateFormatString("dd/MM/yyyy");
        campoDataEmprestimo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Data do Emprestimo");

        campoDataPagamento.setBackground(new java.awt.Color(255, 255, 255));
        campoDataPagamento.setForeground(new java.awt.Color(255, 255, 255));
        campoDataPagamento.setDateFormatString("dd/MM/yyyy");
        campoDataPagamento.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Data de Pagameto");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("(Opicioal)");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/perspective_matte-373-128x128.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(campoDataEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addComponent(campoDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("%");

        campoPorcentagem.setBackground(new java.awt.Color(255, 255, 255));
        campoPorcentagem.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        campoPorcentagem.setForeground(new java.awt.Color(0, 0, 0));
        campoPorcentagem.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Porcentagem");

        campoValorEmprestimo.setBackground(new java.awt.Color(255, 255, 255));
        campoValorEmprestimo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        campoValorEmprestimo.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Valor do Emprestimo");

        campoWhats.setBackground(new java.awt.Color(255, 255, 255));
        campoWhats.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        campoWhats.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("WhatsApp");

        campoNome.setBackground(new java.awt.Color(255, 255, 255));
        campoNome.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        campoNome.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nome");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/perspective_matte-388-128x128.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoWhats, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(campoPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoValorEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(campoWhats, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, 0)
                        .addComponent(campoValorEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campoImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoRegistrarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRegistrarPagamentoActionPerformed

        if (validarCampoVazio()) {
            JOptionPane.showMessageDialog(null, "Escolha um cliente\n Antes de Registrar o Pagamento", "ATENÇÃO", 2);
        } else {

            if (enviar == null) {
                enviar = new RegistrarPagamento();
                enviar.setVisible(true);
                System.out.println("Cliente enviado:");
                System.out.println(cliente);
                enviar.recebendo(String.valueOf(cliente.getIdCliente()));

            } else {
                enviar.setVisible(true);
                enviar.setState(RegistrarPagamento.NORMAL);
                System.out.println("Cliente enviado:");
                System.out.println(cliente);
                enviar.recebendo(String.valueOf(cliente.getIdCliente()));

            }
        }
    }//GEN-LAST:event_botaoRegistrarPagamentoActionPerformed

    private void tabelaClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaClientesKeyReleased
        // TODO add your handling code here:
        Integer numeroDeLinhas = tabelaClientes.getRowCount();
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (numeroDeLinhas <= 1) {
                vincularCamposComBusca();
            } else {
                vincularCampos();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (numeroDeLinhas <= 1) {
                vincularCamposComBusca();
            } else {
                vincularCampos();
            }
        }
    }//GEN-LAST:event_tabelaClientesKeyReleased

    private void tabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClientesMouseClicked
        Integer numeroDeLinhas = tabelaClientes.getRowCount();
        if (numeroDeLinhas <= 1) {
            vincularCamposComBusca();
        } else {
            vincularCampos();
        }
    }//GEN-LAST:event_tabelaClientesMouseClicked

    private void campoImagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoImagemMouseClicked
        if (evt.getClickCount() == 2) {
            JFileChooser arquivo = new JFileChooser();
            arquivo.setDialogTitle("ESCOLHA UMA IMAGEM");
            arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

            Integer op = arquivo.showOpenDialog(this);

            if (op == JFileChooser.APPROVE_OPTION) {
                File file = new File("");
                file = arquivo.getSelectedFile();
                caminhoFoto = file.getAbsolutePath();
                ImageIcon imagem = new ImageIcon(file.getAbsolutePath());

                campoImagem.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(campoImagem.getWidth(), campoImagem.getHeight(), Image.SCALE_DEFAULT)));

            }
        }
    }//GEN-LAST:event_campoImagemMouseClicked

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        limpar();
    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoPesquisarMouseClicked
        obterCliente();
        vincularCamposComBusca();
    }//GEN-LAST:event_botaoPesquisarMouseClicked

    private void campoPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPesquisarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            obterCliente();
            vincularCamposComBusca();
        }
    }//GEN-LAST:event_campoPesquisarKeyPressed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        if (validarCampoVazio()) {
            JOptionPane.showMessageDialog(null, "Selecione o cliente\nclicando na lupa\npara poder excluir", "ATENÇÃO", 2);
        } else {
            Cliente cliente = new Cliente();
            cliente.setNome(campoNome.getText());
            cliente.setWhatsApp(campoWhats.getText());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataEmprestimo = dateFormat.format(campoDataEmprestimo.getDate());

            cliente.setDataEmprestimo(dataEmprestimo);
            cliente.setValorTotalDivida(Double.parseDouble(campoValorEmprestimo.getText()));

            String mensagem = "Tem certeza que quer excluir o cliente:\n" + cliente.getNome();
            Integer confirmar = JOptionPane.showConfirmDialog(null, mensagem, "EXCLUIR", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                clienteDAO.deletarCliente(cliente);
            }
        }
        botaoLimparActionPerformed(evt);
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed

        if (validarCampoVazio()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos\npara poder realizar o cadastro", "ATENÇÃO", 2);
        } else {
            Cliente clientePreCadastro = new Cliente();
            clientePreCadastro.setNome(campoNome.getText());
            clientePreCadastro.setWhatsApp(campoWhats.getText());
            clientePreCadastro.setValorTotalDivida(Double.valueOf(campoValorEmprestimo.getText()));
            clientePreCadastro.setImagem(caminhoFoto.replace('\\', '-'));
            clientePreCadastro.setPorcentagem(Integer.valueOf(campoPorcentagem.getText()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataEmprestimo = dateFormat.format(campoDataEmprestimo.getDate());
            clientePreCadastro.setDataEmprestimo(dataEmprestimo);

            if (campoDataPagamento.getDate() != null) {
                String dataPagamento = dateFormat.format(campoDataPagamento.getDate());
                clientePreCadastro.setDataPagamentoMensal(dataPagamento);
            }

            clienteDAO.validarCadastro(clientePreCadastro);
            botaoLimparActionPerformed(evt);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void campoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoPesquisarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteGUI().setVisible(true);
            }
        });

    }

    public void configurarTabela() {
        DefaultTableModel model = (DefaultTableModel) tabelaClientes.getModel();
        tabelaClientes.getTableHeader().setDefaultRenderer(new CenterRenderer());
        tabelaClientes.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderer());
        tabelaClientes.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderer());
        tabelaClientes.getColumnModel().getColumn(2).setCellRenderer(new CenterRenderer());
        tabelaClientes.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderer());
        tabelaClientes.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderer());
        tabelaClientes.getColumnModel().getColumn(5).setCellRenderer(new CenterRenderer());
        tabelaClientes.getColumnModel().getColumn(6).setCellRenderer(new CenterRenderer());
        model.setNumRows(0);
    }

    public Boolean validarCampoVazio() {
        Boolean validarCampos = campoNome.getText().equals("")
                || campoWhats.getText().equals("")
                || campoDataEmprestimo.getDate() == null
                || campoValorEmprestimo.getText().equals("")
                || campoPorcentagem.getText().equals("");
        return validarCampos;
    }

    public void obterCliente() {
        String busca = campoPesquisar.getText();

        Cliente cliente = clienteDAO.getPesquisarCliente(busca);
        ImageIcon imagem = new ImageIcon(cliente.getImagem().replace('-', '\\'));
        campoImagem.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(campoImagem.getWidth(), campoImagem.getHeight(), Image.SCALE_DEFAULT)));
        campoNome.setText(cliente.getNome());
        campoWhats.setText(cliente.getWhatsApp());
        campoValorEmprestimo.setText(String.valueOf(cliente.getValorTotalDivida()));
        String dateEmprestimoString = cliente.getDataEmprestimo();
        String dateStringPagamentoString = cliente.getDataPagamentoMensal();
        try {
            campoDataEmprestimo.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateEmprestimoString));
            campoDataPagamento.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateStringPagamentoString));
        } catch (ParseException ex) {
            Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        campoPorcentagem.setText(String.valueOf(cliente.getPorcentagem()));
        lerTabela(busca);
    }

    public void vincularCampos() {
        cliente = clienteDAO.getClientesForTable().get(tabelaClientes.getSelectedRow());
        clienteDAO.obterId(cliente.getIdCliente());

        campoNome.setText(cliente.getNome());
        campoWhats.setText(cliente.getWhatsApp());
        campoValorEmprestimo.setText(String.valueOf(String.format("%.0f", cliente.getValorTotalDivida())));
        campoPorcentagem.setText(String.valueOf(cliente.getPorcentagem()));
        String dateEmprestimoString = cliente.getDataEmprestimo();
        String dateStringPagamentoString = cliente.getDataPagamentoMensal();
        try {
            campoDataEmprestimo.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateEmprestimoString));
            campoDataPagamento.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateStringPagamentoString));
        } catch (ParseException ex) {
            Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        caminhoFoto = clienteDAO.getPesquisarCliente(String.valueOf(cliente.getIdCliente())).getImagem();
        ImageIcon imagem = new ImageIcon(caminhoFoto.replace('-', '\\'));
        if (!caminhoFoto.equals("")) {
            campoImagem.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(247, 249, Image.SCALE_SMOOTH)));
//            campoImagem.setIcon(new ImageIcon(getClass().getResource(caminhoFoto)));
        } else {
            campoImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/perspective_matte-59-128x128.png")));
        }
    }

    public void vincularCamposComBusca() {
        String busca = campoPesquisar.getText();
//        Cliente cliente = new Cliente();
        if (!busca.equals("")) {
            cliente = clienteDAO.getClientesForTable(busca).get(0);
        } else {
            cliente = clienteDAO.getClientesForTable().get(tabelaClientes.getSelectedRow());
        }
        clienteDAO.obterId(cliente.getIdCliente());
        campoNome.setText(cliente.getNome());
        campoWhats.setText(cliente.getWhatsApp());
        campoValorEmprestimo.setText(String.valueOf(String.format("%.0f", cliente.getValorTotalDivida())));
        campoPorcentagem.setText(String.valueOf(cliente.getPorcentagem()));
        String dateEmprestimoString = cliente.getDataEmprestimo();
        String dateStringPagamentoString = cliente.getDataPagamentoMensal();
        try {
            campoDataEmprestimo.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateEmprestimoString));
            campoDataPagamento.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateStringPagamentoString));
        } catch (ParseException ex) {
            Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        caminhoFoto = clienteDAO.getPesquisarCliente(String.valueOf(cliente.getIdCliente())).getImagem();
        ImageIcon imagem = new ImageIcon(caminhoFoto.replace('-', '\\'));
        if (!caminhoFoto.equals("")) {
            campoImagem.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(247, 249, Image.SCALE_DEFAULT)));
        } else {
            campoImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/perspective_matte-59-128x128.png")));
        }
    }

    public void limpar() {
        campoNome.setText("");
        campoWhats.setText("");
        campoValorEmprestimo.setText("");
        campoDataEmprestimo.setDate(null);
        campoDataPagamento.setDate(null);
        campoPorcentagem.setText("");
        campoImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/perspective_matte-59-128x128.png")));
        caminhoFoto = "";
        clienteDAO.obterId(null);
        lerTabela();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoExcluir;
    public javax.swing.JButton botaoLimpar;
    public static javax.swing.JLabel botaoPesquisar;
    private javax.swing.JButton botaoRegistrarPagamento;
    private javax.swing.JButton botaoSalvar;
    private com.toedter.calendar.JDateChooser campoDataEmprestimo;
    private com.toedter.calendar.JDateChooser campoDataPagamento;
    private javax.swing.JLabel campoImagem;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoPesquisar;
    private javax.swing.JTextField campoPorcentagem;
    private javax.swing.JTextField campoValorEmprestimo;
    private javax.swing.JTextField campoWhats;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaClientes;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/perspective_matte-388-128x128.png")));
    }

}
