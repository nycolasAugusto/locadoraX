package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.produtos.produtosUtil.Produto;

public abstract class ProdutosDAO  {

    private static final String CAMINHO = "src/dados/produtos";

    public static void salvar(List<Produto> produtos) throws IOException{

        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/produtos.ser"))){
            oos.writeObject(produtos);
        }
    }

     @SuppressWarnings("unchecked")
    public static List<Produto> carregar() throws IOException, ClassNotFoundException{
        File arquivo = new File(CAMINHO + "/produtos.ser");
        if(!arquivo.exists()) return new ArrayList<Produto>();
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))){
            return (List<Produto>) ois.readObject();
        }
    }

  
 





}


    

