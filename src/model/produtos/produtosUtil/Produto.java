package model.produtos.produtosUtil;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class Produto implements Serializable {
    protected String nome ;
    protected LocalDate dataCadastro;
    protected int quantidadeEstoque, vezesEmprestado, codigo;
    protected Categoria categoria;
    protected Classe classe;
    protected Classificacao classificacaoIndicativa;

    public Produto(int codigo , String nome, LocalDate dataCadastro, int quantidadeEstoque,
         Categoria categoria, Classe classe, Classificacao classificacaoIndicativa) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
        this.classe = classe;
        this.classificacaoIndicativa = classificacaoIndicativa;
    }
    public abstract void diminuirEstoque();
    public abstract void vezesEmprestado();
    public abstract String exibirInformacoesCruciais();
    public abstract void aumentarEstoque();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getVezesEmprestado() {
        return vezesEmprestado;
    }

    public void setVezesEmprestado(int vezesEmprestado) {
        this.vezesEmprestado = vezesEmprestado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Classificacao getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(Classificacao classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", dataLancamento=" + ", dataCadastro=" + dataCadastro
                + ", quantidadeEstoque=" + quantidadeEstoque + ", vezesEmprestado=" + vezesEmprestado + ", codigo="
                + codigo + ", categoria=" + categoria + ", classe=" + classe + ", classificacaoIndicativa="
                + classificacaoIndicativa + "]";
    }
    

    

    

    


}
