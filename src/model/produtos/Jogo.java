package model.produtos;

import java.time.LocalDate;

import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;

public class Jogo extends Produto{
    private String plataforma;

    public Jogo(int codigo, String nome, LocalDate dataLancamento, LocalDate dataCadastro, int quantidadeEstoque,
        Categoria categoria, Classe classe, Classificacao classificacaoIndicativa,
            String plataforma) {
        super(codigo, nome, dataLancamento, dataCadastro, quantidadeEstoque, categoria, classe,
                classificacaoIndicativa);
        this.plataforma = plataforma;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    

}
