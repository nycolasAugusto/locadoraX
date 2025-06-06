package model.transacoes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import model.Ilocadora;
import model.pessoa.Cliente;
import model.produtos.produtosUtil.Produto;

public class Emprestimo implements Serializable, Ilocadora{

    private List<Produto> produtosEmprestados;
    private Cliente cliente;
    private LocalDate dataEmprestimo , dataDevolucao;
    private double precoEmprestimo;
    
    private Emprestimo(List<Produto> produtosEmprestados, Cliente cliente, LocalDate dataEmprestimo,
            LocalDate dataDevolucao, double precoEmprestimo) {
        this.produtosEmprestados = produtosEmprestados;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.precoEmprestimo = precoEmprestimo;
        
    }
    public static Emprestimo criarEmprestimo(List<Produto> produtosEmprestados, Cliente cliente, LocalDate dataEmprestimo,
            LocalDate dataDevolucao, double precoEmprestimo ){
                return new Emprestimo(produtosEmprestados, cliente, dataEmprestimo, dataDevolucao, precoEmprestimo);
            }


    
    public List<Produto> getProdutosEmprestados() {
        return produtosEmprestados;
    }
    public void removerProdutosEmprestimo(Produto p){
        produtosEmprestados.remove(p);
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
    public String mostraApenasNomeProduto(List<Produto> lista){
        String resultado = "";
        for (Produto produto : lista) {
            resultado += produto.getNome();
        } 
        return resultado;
    }
    
    
		@Override
    public String listagemFormal() {
        
        return cliente.getNome() + " [" + mostraApenasNomeProduto(produtosEmprestados) + "]";
    }
    
        @Override
			public String toString() {
					return cliente.getNome() + " - " +
								mostraApenasNomeProduto(produtosEmprestados) + " - " +
								dataEmprestimo + " - " +
								dataDevolucao;
			}
    
    
    




}
