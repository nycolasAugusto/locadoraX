package model.produtos;

import java.time.LocalDate;

import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;
import model.produtos.produtosUtil.Produto;

public class Serie extends Produto {
    private int temporada;

    private Serie(int codigo, String nome, LocalDate dataCadastro, int quantidadeEstoque,
            Categoria categoria, Classe classe, Classificacao classificacaoIndicativa,
            int temporada) {
        super(codigo, nome, dataCadastro, quantidadeEstoque, categoria, classe,
                classificacaoIndicativa);
        this.temporada = temporada;
    }

    public static Serie criarSerie(int codigo, String nome, LocalDate dataCadastro, int quantidadeEstoque,
            Categoria categoria, Classe classe, Classificacao classificacaoIndicativa, int temporada) {
        return new Serie(codigo, nome, dataCadastro, quantidadeEstoque, categoria, classe, classificacaoIndicativa,
                temporada);
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

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String exibirInformacoesCruciais() {
        return codigo + " - " + nome + ", classificação: " + classificacaoIndicativa + ", cadastro " + dataCadastro +
                ", classe: " + classe + ", estoque: " + quantidadeEstoque;
    }

    @Override
    public String toString() {
        return codigo + "- " + nome + ", " +
                ", categoria: " + categoria + ", classe: " + classe +
                ", classificação: " + classificacaoIndicativa + ", temporada: " + temporada ;
    }
}
