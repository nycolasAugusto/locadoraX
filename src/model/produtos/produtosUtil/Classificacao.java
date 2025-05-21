package model.produtos.produtosUtil;

public enum Classificacao {
    LIVRE(1),
    MAIORES(2);

    private int codigoClassificacao;

    private Classificacao(int codigoClassificacao) {
        this.codigoClassificacao = codigoClassificacao;
    }

    public static Classificacao getClasse(int numeroClassificacao){
        for (Classificacao classificacao : Classificacao.values()) {
            if (numeroClassificacao  == classificacao.codigoClassificacao) {
                return classificacao;
            }
        }
        return null;
	}

    
    
}
