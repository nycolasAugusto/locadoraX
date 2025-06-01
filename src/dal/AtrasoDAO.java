package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.transacoes.Atraso;


public abstract class AtrasoDAO {
    private static final String CAMINHO = "src/dados/atraso";

    public static void salvar(List<Atraso> atrasos) throws IOException{

        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/atrasos.ser"))){
            oos.writeObject(atrasos);
        }
    }

     @SuppressWarnings("unchecked")
    public static List<Atraso> carregar() throws IOException, ClassNotFoundException{
        File arquivo = new File(CAMINHO + "/atrasos.ser");
        if(!arquivo.exists()) return new ArrayList<Atraso>();
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))){
            return (List<Atraso>) ois.readObject();
        }
    }
}
