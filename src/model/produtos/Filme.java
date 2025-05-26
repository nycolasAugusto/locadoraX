package model.produtos;

import java.time.LocalDate;

import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;

public class Filme extends Produto {
    private int duracaoMinutos;

    private Filme(int codigo, String nome, LocalDate dataCadastro, int quantidadeEstoque,
        Categoria categoria, Classe classe, Classificacao classificacaoIndicativa,
        int duracaoMinutos){
        super(codigo , nome, dataCadastro, quantidadeEstoque, categoria, classe,
                classificacaoIndicativa);
        this.duracaoMinutos = duracaoMinutos;
    }
    public static Filme criarFilme(int codigo, String nome, LocalDate dataCadastro, int quantidadeEstoque,Categoria categoria, Classe classe, Classificacao classificacaoIndicativa,int duracaoMinutos){
        
        return new Filme(codigo, nome, dataCadastro, quantidadeEstoque, categoria, classe, classificacaoIndicativa, duracaoMinutos);

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
    public String toString() {
        return "Filme [nome=" + nome + ", duracaoMinutos=" + duracaoMinutos 
                + ", dataCadastro=" + dataCadastro + ", quantidadeEstoque=" + quantidadeEstoque + ", codigo=" + codigo
                + ", categoria=" + categoria + ", classe=" + classe + ", classificacaoIndicativa="
                + classificacaoIndicativa + "]";
    }


    
    

    

    
}
