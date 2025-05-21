package model.produtos;

import java.time.LocalDate;

import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;

public class Serie extends Produto {
    private int temporada;

    public Serie(int codigo, String nome, LocalDate dataLancamento, LocalDate dataCadastro, int quantidadeEstoque,
            Categoria categoria, Classe classe, Classificacao classificacaoIndicativa,
            int temporada) {
        super(codigo, nome, dataLancamento, dataCadastro, quantidadeEstoque, categoria, classe,
                classificacaoIndicativa);
        this.temporada = temporada;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }



    

    
    
}
