package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.transacoes.Compra;

public abstract class CompraDAO {
    private static final String CAMINHO = "src/dados/compra";

    public static void salvar(List<Compra> compras) throws IOException{

        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/compra.ser"))){
            oos.writeObject(compras);
        }
    }

     @SuppressWarnings("unchecked")
    public static List<Compra> carregar() throws IOException, ClassNotFoundException{
        File arquivo = new File(CAMINHO + "/compra.ser");
        if(!arquivo.exists()) return new ArrayList<Compra>();
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))){
            return (List<Compra>) ois.readObject();
        }
    }
}
