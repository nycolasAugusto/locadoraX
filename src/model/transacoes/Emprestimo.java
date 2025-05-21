package model.transacoes;

import java.time.LocalDate;
import java.util.List;

import model.pessoa.Cliente;
import model.produtos.Produto;

public class Emprestimo {

    private List<Produto> produtosEmprestados;
    private Cliente cliente;
    private LocalDate dataEmprestimo , dataDevolucao;
    private double precoEmprestimo;
    
    public Emprestimo(List<Produto> produtosEmprestados, Cliente cliente, LocalDate dataEmprestimo,
            LocalDate dataDevolucao, double precoEmprestimo) {
        this.produtosEmprestados = produtosEmprestados;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.precoEmprestimo = precoEmprestimo;
    }


    public List<Produto> getProdutosEmprestados() {
        return produtosEmprestados;
    }
    public void setProdutosEmprestados(List<Produto> produtosEmprestados) {
        this.produtosEmprestados = produtosEmprestados;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public double getPrecoEmprestimo() {
        return precoEmprestimo;
    }
    public void setPrecoEmprestimo(double precoEmprestimo) {
        this.precoEmprestimo = precoEmprestimo;
    }
    

    




}
