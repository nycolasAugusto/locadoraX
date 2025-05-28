package model.produtos;

import java.time.LocalDate;

import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;

import model.produtos.produtosUtil.Produto;

public class Jogo extends Produto {
    private String plataforma;

    private Jogo(int codigo, String nome, LocalDate dataCadastro, int quantidadeEstoque,
        Categoria categoria, Classe classe, Classificacao classificacaoIndicativa,
            String plataforma) {
        super(codigo, nome, dataCadastro, quantidadeEstoque, categoria, classe,
                classificacaoIndicativa);
        this.plataforma = plataforma;
    }
    
    public static Jogo criarJogo(int codigo, String nome, LocalDate dataCadastro, int quantidadeEstoque,
        Categoria categoria, Classe classe, Classificacao classificacaoIndicativa,
            String plataforma) {
        return new Jogo(codigo, nome, dataCadastro, quantidadeEstoque,
                        categoria, classe, classificacaoIndicativa, plataforma);

    }

    

    @Override
    public String exibirInformacoesCruciais() {
        return "Nome: " + nome + ", Categoria: " + categoria + ", Classe: " + classe + ", Plataforma : " + plataforma;
    
    }

     @Override
    public void vezesEmprestado() {
        vezesEmprestado += 1;
        
    }
    @Override
    public void diminuirEstoque() {
        this.quantidadeEstoque -= 1;
        
    }
    @Override
    public void aumentarEstoque() {
        this.quantidadeEstoque += 1; 
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + ", " + dataCadastro + ", estoque: " + quantidadeEstoque +
            ", categoria: " + categoria + ", classe: " + classe +
            ", classificação: " + classificacaoIndicativa + ", plataforma: " + plataforma;
    }
    

    

}
