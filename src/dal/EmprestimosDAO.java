package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.transacoes.Emprestimo;

public abstract class EmprestimosDAO {
     private static final String CAMINHO = "src/dados/emprestimos";

    public static void salvar(List<Emprestimo> emprestimos) throws IOException{

        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/emprestimos.ser"))){
            oos.writeObject(emprestimos);
        }
    }

     @SuppressWarnings("unchecked")
    public static List<Emprestimo> carregar() throws IOException, ClassNotFoundException{
        File arquivo = new File(CAMINHO + "/emprestimos.ser");
        if(!arquivo.exists()) return new ArrayList<Emprestimo>();
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))){
            return (List<Emprestimo>) ois.readObject();
        }
    }
}
