package model.produtos.produtosUtil;

public enum Classificacao {
    LIVRE("Livre",1),
    MAIORES("Maiores",2);

    private int codigoClassificacao;
    private String nomeClassificacao;

    private Classificacao(String nomeClassificacao , int codigoClassificacao) {
        this.codigoClassificacao = codigoClassificacao;
        this.nomeClassificacao = nomeClassificacao;
    }

    public static Classificacao getClasse(int numeroClassificacao){
        for (Classificacao classificacao : Classificacao.values()) {
            if (numeroClassificacao  == classificacao.codigoClassificacao) {
                return classificacao;
            }
        }
        return null;
	}

    @Override
    public String toString() {

        return nomeClassificacao;
    }
    

    
    
}
