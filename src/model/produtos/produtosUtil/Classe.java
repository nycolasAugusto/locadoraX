package model.produtos.produtosUtil;

public enum Classe {
    PREMIUM(10.0 , 25.00,1 ),
    INTERMEDIARIO(7.50 , 16.00, 2),
    CLASSICO(5.0 , 10, 3);

    private double precoAluguel, precoCompra;
    private int codigoClasse;
    private Classe(double precoAluguel, double precoCompra, int codigoClasse) {
        this.precoAluguel = precoAluguel;
        this.precoCompra = precoCompra;
        this.codigoClasse = codigoClasse;
    }

    public static Classe getClasse(int numeroClasse){
        for (Classe classe : Classe.values()) {
            if (numeroClasse  == classe.codigoClasse) {
                return classe;
            }
        }
        return null;
	}
    
    

}
  