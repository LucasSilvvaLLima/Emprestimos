# Sistema de Empréstimos Lucas Silva
<h2>Esse foi um projeto de férias no qual decide praticar meus conhecimentos em java</h2>
<h3>Ele foi criado inteiramente para estudos.</h3>
As tecnologias utilizadas foram JAVA e Mysql. Nesse sistema o usuário vai poder cadastrar os clientes que ele fez o empréstimo com foto, nome, WhatsApp, valor do empréstimo, porcentagem cobrada e a data que o empréstimo foi feito(também é possível definir a data de pagamento que se a diferença de dias entre o empréstimo e o pagamento for menor que 1 mês o cálculo da primeira parcela é calculando somente essa diferença de dias, após o primeiro pagamento o valor voltara a porcentagem padrão cobrada). O sistema disponibiliza um CRUD completo dos clientes cadastrados, ao registrar os pagamentos o sistema gera a nova data de pagamento automaticamente, e será possível ver o histórico de pagamento dos usuários de forma individual, o quanto o emprestimo esta rendendo e quanto que o cliente já rendeu de no total.

## TELA DE CADASTRO
![Tela Cadastro](https://github.com/LucasSilvaLLima/Emprestimos/blob/main/consignado/src/main/resources/assets/TelaCadastro.png)

## Para ver os dados do cliente basta clicar na sua linha ou clicar na seta para cima ou para baixo para ir passando pelos clientes.
![Tela Cadastro](https://github.com/LucasSilvaLLima/Emprestimos/blob/main/consignado/src/main/resources/assets/ClienteSelecionado.png)

## Ou Pesquisar pelo cliente por seu ID, Nome ou WhatsApp.
<table>
  <tr>
    <td><img src="https://github.com/LucasSilvaLLima/Emprestimos/blob/main/consignado/src/main/resources/assets/ClientePesquisaId.png" alt="Imagem 1" width="400"></td>
    <td><img src="https://github.com/LucasSilvaLLima/Emprestimos/blob/main/consignado/src/main/resources/assets/ClientePesquisaNome.png" alt="Imagem 2" width="400"></td>
    <td><img src="https://github.com/LucasSilvaLLima/Emprestimos/blob/main/consignado/src/main/resources/assets/ClientePesquisaWhats.png" alt="Imagem 3" width="400"></td>
  </tr>
</table>

## Ao clicar em registrar o pagamento uma nova janela com os dados do cliente selecionado será aberta, onde o registro do pagamento é feito.
![Tela Cadastro](https://github.com/LucasSilvaLLima/Emprestimos/blob/main/consignado/src/main/resources/assets/ClienteDados.png)
