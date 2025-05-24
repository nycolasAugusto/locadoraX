package model.produtos;

import java.time.LocalDate;

import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;

public class Jogo extends Produto{
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

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    

}
