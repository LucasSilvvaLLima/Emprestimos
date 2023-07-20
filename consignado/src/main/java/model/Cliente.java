/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lucka
 */
public class Cliente {

    private Integer idCliente;
    private String nome;
    private String whatsApp;
    private String dataEmprestimo;
    private Double valorTotalDivida;
    private String dataPagamentoMensal;
    private Double valorPagamentoMensal = 0.0;
    private String imagem;
    public Integer porcentagem;
    private Double valorTotalEmprestimo;
    private Double rendimento;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String numero) {
        this.whatsApp = numero;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Double getValorTotalDivida() {
        return valorTotalDivida;
    }

    public void setValorTotalDivida(Double valorTotalDivida) {
        this.valorTotalDivida = valorTotalDivida;
    }

    public String getDataPagamentoMensal() {
        if(dataPagamentoMensal == null){
            String dataEmprestimoString = getDataEmprestimo();

            String padraoFormatacao = "dd/MM/yyyy";
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padraoFormatacao);

            LocalDate dataEmprestimoLocalDate = LocalDate.parse(dataEmprestimoString, formatador);
            LocalDate dataPagamentoLocalDate = dataEmprestimoLocalDate.plus(Period.ofMonths(1));
            String dataPagamentoString = dataPagamentoLocalDate.format(formatador);

            return dataPagamentoString;
        }else{
            return dataPagamentoMensal;
        }
    }

    public void setDataPagamentoMensal(String dataPagamentoMensal) {
        this.dataPagamentoMensal = dataPagamentoMensal;
    }

    public Double getValorPagamentoMensal() {
        return Double.valueOf(String.format("%.0f", valorPagamentoMensal));
    }

    public void setValorPagamentoMensal(Double valorPagamentoMensal) {
        this.valorPagamentoMensal = valorPagamentoMensal;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Integer porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Double getValorTotalEmprestimo() {
        return valorTotalEmprestimo;
    }

    public void setValorTotalEmprestimo(Double valorTotalEmprestimo) {
        this.valorTotalEmprestimo = valorTotalEmprestimo;
    }

    public Double getRendimento() {
        return rendimento;
    }

    public void setRendimento(Double rendimento) {
        this.rendimento = rendimento;
    }
    
    

    @Override
    public String toString() {
        return String.format("""
                             ID: %d 
                             Nome: %s
                             WhatsApp: %s
                             DaTa Emprestimo: %s 
                             Valor Total Divida: %f
                             Data Pagamento: %s
                             Valor Pagamento: %f
                             Porcentagem: %d
                             Imagem: %s
                             Valor Total Emprestimo: %f
                             Rendimento: %f
                             """, idCliente, nome, whatsApp, dataEmprestimo, valorTotalDivida, dataPagamentoMensal, valorPagamentoMensal, porcentagem, imagem, valorTotalEmprestimo, rendimento);
    }

    

}
