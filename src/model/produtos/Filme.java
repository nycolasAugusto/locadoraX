package model.produtos;

import java.time.LocalDate;

import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;

public class Filme extends Produto {
    private int duracaoMinutos;

    public Filme(int codigo, String nome, LocalDate dataLancamento, LocalDate dataCadastro, int quantidadeEstoque,
        Categoria categoria, Classe classe, Classificacao classificacaoIndicativa,
        int duracaoMinutos) {
        super(codigo , nome, dataLancamento, dataCadastro, quantidadeEstoque, categoria, classe,
                classificacaoIndicativa);
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String toString() {
        return "Filme [nome=" + nome + ", duracaoMinutos=" + duracaoMinutos + ", dataLancamento=" + dataLancamento
                + ", dataCadastro=" + dataCadastro + ", quantidadeEstoque=" + quantidadeEstoque + ", codigo=" + codigo
                + ", categoria=" + categoria + ", classe=" + classe + ", classificacaoIndicativa="
                + classificacaoIndicativa + "]";
    }


    
    

    

    
}
