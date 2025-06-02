import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.LocadoraController;
import model.pessoa.Cliente;
import model.produtos.produtosUtil.Produto;
import model.transacoes.Atraso;
import model.transacoes.Compra;
import model.transacoes.Emprestimo;
import util.Log;
import view.cliente.ClienteView;
import view.funcionario.ConsultarListagens;
import view.funcionario.GerenciarCliente;
import view.funcionario.GerenciarProdutos;
import view.funcionario.GerenciarTransacoes;

public class App {
    public static void main(String[] args) throws Exception {
        Log.setError();

        List<Produto> listaDeProdutos = new ArrayList<>();
        List<Cliente> listaDeClientes = new ArrayList<>();
        List<Emprestimo> listaDeEmprestimos = new ArrayList<>();
        List<Atraso> listaDeAtrasos = new ArrayList<>();
        List<Compra> listaDeCompras = new ArrayList<>();
        try {
            listaDeClientes = LocadoraController.carregarClientes();
            listaDeProdutos = LocadoraController.carregarProdutos();
            listaDeEmprestimos = LocadoraController.carregarEmprestimos();
            listaDeAtrasos = LocadoraController.carregarAtrasos();
            listaDeCompras = LocadoraController.carregarCompras();
            System.out.println("Dados baixados com sucesso");
        } catch (IOException e) {
            System.err.println("Arquivo não encontrado " + e.getMessage() + "Data :" + LocalDate.now() + " Hora :"
                    + LocalTime.now());
        } catch (ClassNotFoundException e) {
            System.err.println("Arquivo corrompido");
        }
        LocadoraController controller = new LocadoraController(
                listaDeProdutos,
                listaDeClientes,
                listaDeEmprestimos,
                listaDeAtrasos,
                listaDeCompras);

        Scanner scanner = new Scanner(System.in);
        int opcaoPrincipal;

        do {
            System.out.println("\n--- Bem-vindo à Locadora Central! ---");
            System.out.println("1. Área do Cliente");
            System.out.println("2. Área do Funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcaoPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoPrincipal) {
                case 1:
                    ClienteView.viewCliente(controller, scanner);
                    break;
                case 2:
                    int opcaoFuncionario;
                    do {
                        System.out.println("\n--- Área do Funcionário ---");
                        System.out.println("1. Gerenciar Produtos (Filmes, Séries, Jogos)");
                        System.out.println("2. Gerenciar Clientes");
                        System.out.println("3. Gerenciar Trasacoes");
                        System.out.println("4. Consultar Listagens");
                        System.out.println("0. Voltar ao Menu Principal");
                        System.out.print("Escolha uma opção: ");

                        opcaoFuncionario = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoFuncionario) {
                            case 1:
                                GerenciarProdutos.gerenciarProdutos(controller, scanner);
                                break;
                            case 2:
                                GerenciarCliente.gerenciarCliente(controller, scanner);
                                break;
                            case 3:
                                GerenciarTransacoes.gerenciarTransacoes(controller, scanner);
                                break;
                            case 4:
                                ConsultarListagens.consultarListagens(controller, scanner);
                                break;
                            case 5:
                                System.out.println("Registrar Compras (Vendas)");
                                break;
                            case 0:
                                System.out.println("Voltando ao Menu Principal...");
                                break;
                            default:
                                System.out.println("Opção inválida. Por favor, tente novamente.");
                        }
                    } while (opcaoFuncionario != 0);
                    break;
                case 0:
                    System.out.println("Obrigado por utilizar a Locadora Central! Até mais.");
                    try {
                        LocadoraController.salvar(listaDeClientes, listaDeAtrasos, listaDeCompras, listaDeEmprestimos,
                                listaDeProdutos);
                        System.out.println("Dados salvos com sucesso!");
                        System.out.println("Encerrando o sistema");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar Arquivo");
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcaoPrincipal != 0);

        scanner.close();
    }
}
