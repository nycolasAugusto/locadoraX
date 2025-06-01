package model.produtos.produtosUtil;

public enum Categoria  {
    ROMANCE("Romance"),
    ACAO("Acao"),
    COMEDIA("Comedia"),
    DRAMA("Drama"),
    FANTASIA("Fantasia"),
    FICCAO_CIENTIFICA("Ficcao Cientifica"),
    MISTERIO("Misterio"),
    TERROR("Terror"),
    BIOGRAFIA("Biografia"),
    INFANTIL("Infantil");


    private String nomeCategoria;

    private Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public static Categoria getCategoria(String categoriaUser){
            for (Categoria categoria : Categoria.values()) {
                if (categoriaUser.trim().equalsIgnoreCase(categoria.nomeCategoria)) {
                    return categoria;
                }
            }
            return null;
	}

    @Override
    public String toString() {
        
        return nomeCategoria;
    }

    
}
