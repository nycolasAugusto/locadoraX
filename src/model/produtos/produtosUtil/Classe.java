package model.produtos.produtosUtil;

public enum Classe {
    PREMIUM("Premium",10.0 , 25.00,1 ),
    INTERMEDIARIO("Intermediario",7.50 , 16.00, 2),
    CLASSICO("Classico",5.0 , 10, 3);

    private double precoAluguel, precoCompra;
    private int codigoClasse;
    private String nomeClasse;
    private Classe(String nomeClasse ,double precoAluguel, double precoCompra, int codigoClasse) {
        this.precoAluguel = precoAluguel;
        this.precoCompra = precoCompra;
        this.codigoClasse = codigoClasse;
        this.nomeClasse = nomeClasse;
    }

    public static Classe getClasse(int numeroClasse){
        for (Classe classe : Classe.values()) {
            if (numeroClasse  == classe.codigoClasse) {
                return classe;
            }
        }
        return null;
	}

    public double getPrecoAluguel() {
        return precoAluguel;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public int getCodigoClasse() {
        return codigoClasse;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return nomeClasse;
    }
    
    
    

}
  