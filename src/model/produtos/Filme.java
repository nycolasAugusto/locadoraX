package model.produtos;

import java.time.LocalDate;

import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;

import model.produtos.produtosUtil.Produto;

public class Filme extends Produto {
    private int duracaoMinutos;

    private Filme(int codigo, String nome, LocalDate dataCadastro, int quantidadeEstoque,
            Categoria categoria, Classe classe, Classificacao classificacaoIndicativa,
            int duracaoMinutos) {
        super(codigo, nome, dataCadastro, quantidadeEstoque, categoria, classe,
                classificacaoIndicativa);
        this.duracaoMinutos = duracaoMinutos;
    }

    public static Filme criarFilme(int codigo, String nome, LocalDate dataCadastro, int quantidadeEstoque,
            Categoria categoria, Classe classe, Classificacao classificacaoIndicativa, int duracaoMinutos) {

        return new Filme(codigo, nome, dataCadastro, quantidadeEstoque, categoria, classe, classificacaoIndicativa,
                duracaoMinutos);

    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public void diminuirEstoque() {
        this.quantidadeEstoque -= 1;
    }

    @Override
    public void aumentarEstoque() {
        this.quantidadeEstoque += 1;
    }

    @Override
    public void vezesEmprestado() {
        vezesEmprestado += 1;

    }

    /*
     * na area do funcionario , mostra apenas CODIGO - NOME - CLASSIFICACAO -CLASSE
     * - ESTOQUE
     * na area do cliente : mostrar CODIGO - NOME - CATEGORIA - CLASSIFICACAO -
     * VEZESEMPRESTADO
     */
    public String exibirInformacoesCruciais() {
        return codigo + " - " + nome + ", classificação: " + classificacaoIndicativa + ", cadastro " + dataCadastro + 
                ", classe: " + classe + ", estoque: " + quantidadeEstoque;
    }

    @Override
    public String toString() {
        return codigo + "- " + nome + ", " +
                ", categoria: " + categoria + ", classe: " + classe +
                ", classificação: " + classificacaoIndicativa + ", duração: " + duracaoMinutos + " min";
    }

}
