package model.pessoa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.produtos.produtosUtil.Produto;
import model.transacoes.Compra;
import model.transacoes.Emprestimo;

public final class Cliente extends Pessoa implements Serializable {
    private List<Compra> compras;
    private List<Emprestimo> produtosAlugados;
    private double multaApagar;

    private Cliente(String nome, String email, String endereco, long cpf, long numero, LocalDate dataNascimento) {
        super(nome, email, endereco, cpf, numero, dataNascimento);
        produtosAlugados = new ArrayList<>();
        compras = new ArrayList<>();

       
    }   
    
    public static Cliente criarCliente(String nome, String email, String endereco, long cpf, long numero, LocalDate dataNascimento){
        return new Cliente(nome, email, endereco, cpf, numero, dataNascimento);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo){
        produtosAlugados.add(emprestimo);
    }
    public void removerEmprestimo(Emprestimo emprestimo){
        produtosAlugados.remove(emprestimo);
    }
    public void adicionarCompra(Compra compra){
            compras.add(compra);
    }

    public List<Emprestimo> getListaDeCompras() {
        return produtosAlugados;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Emprestimo> getProdutosAlugados() {
        return produtosAlugados;
    }

    public void setProdutosAlugados(List<Emprestimo> produtosAlugados) {
        this.produtosAlugados = produtosAlugados;
    }

    public void setListaDeCompras(List<Emprestimo> produtosAlugados) {
        this.produtosAlugados = produtosAlugados;
    }
    
    public double getMultaApagar() {
        return multaApagar;
    }

    public void setMultaApagar(double multaApagar) {
        this.multaApagar = multaApagar;
    }

  @Override
    public String toString() {
        boolean temEmprestimoAtivo = false;
        for (Emprestimo emprestimo : produtosAlugados) {
            // Considere ativo se dataDevolucao for null ou maior que hoje
            if (emprestimo.getDataDevolucao() == null || emprestimo.getDataDevolucao().isAfter(java.time.LocalDate.now())) {
                temEmprestimoAtivo = true;
                break;
            }
        }

        // Monta lista de compras
        String nomesCompras = "[";
        boolean primeiroProd = true;
        for (Compra compra : compras) {
            for (Produto produto : compra.getProdutosComprados()) {
                if (!primeiroProd) {
                    nomesCompras += ", ";
                }
                nomesCompras += produto.getNome();
                primeiroProd = false;
            }
        }
        nomesCompras += "]";

        // Monta lista de produtos alugados (empréstimos ativos)
        String nomesEmprestimos = "[";
        boolean primeiroEmp = true;
        for (Emprestimo emprestimo : produtosAlugados) {
            // Só mostra produtos de empréstimos ativos
            if (emprestimo.getDataDevolucao() == null || emprestimo.getDataDevolucao().isAfter(java.time.LocalDate.now())) {
                for (Produto produto : emprestimo.getProdutosEmprestados()) {
                    if (!primeiroEmp) {
                        nomesEmprestimos += ", ";
                    }
                    nomesEmprestimos += produto.getNome();
                    primeiroEmp = false;
                }
            }
        }
        nomesEmprestimos += "]";

        String emprestimoInfo = "NÃO";
        if (temEmprestimoAtivo) {
            emprestimoInfo = "SIM - PRODUTOS: " + nomesEmprestimos;
        }

        return "NOME: " + nome +
                " - CPF: " + cpf +
                " - EMPRESTIMO ATIVO: " + emprestimoInfo +
                " - LISTA DE COMPRAS: " + nomesCompras;
    }
}