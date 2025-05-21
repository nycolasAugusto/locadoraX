import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import controller.LocadoraController;
import model.pessoa.Cliente;
import model.produtos.Produto;
import model.transacoes.Atraso;
import model.transacoes.Compra;
import model.transacoes.Emprestimo;
public class App {
    public static void main(String[] args) throws Exception {
        
         
        List<Produto> listaDeProdutos = new ArrayList<>();
        List<Cliente> listaDeClientes = new ArrayList<>();
        List<Emprestimo> listaDeEmprestimos = new ArrayList<>();
        List<Atraso> listaDeAtrasos = new ArrayList<>();
        List<Compra> listaDeCompras = new ArrayList<>();

        
        LocadoraController controller = new LocadoraController(
            listaDeProdutos,
            listaDeClientes,
            listaDeEmprestimos,
            listaDeAtrasos,
            listaDeCompras
        );


        controller.cadastrarFilme("Pii", LocalDate.now(), LocalDate.now(), 
        10, "Romance", 1, 1, 100);

        System.out.println(controller.getProdutos());






    }
}
