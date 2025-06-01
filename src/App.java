import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.LocadoraController;
import model.pessoa.Cliente;
import model.produtos.produtosUtil.Produto;
import model.transacoes.Atraso;
import model.transacoes.Compra;
import model.transacoes.Emprestimo;
import view.funcionario.ConsultarListagens;
import view.funcionario.GerenciarCliente;
import view.funcionario.GerenciarProdutos;
import view.funcionario.GerenciarTransacoes;
public class App {
    public static void main(String[] args) throws Exception {
        
        List<Produto> listaDeProdutos = new ArrayList<>();
        List<Cliente> listaDeClientes = new ArrayList<>();
        List<Emprestimo> listaDeEmprestimos = new ArrayList<>();
        List<Atraso> listaDeAtrasos = new ArrayList<>();
        List<Compra> listaDeCompras = new ArrayList<>();
        try {
            listaDeClientes = LocadoraController.carregar();    
        } catch (IOException e) {
            System.err.println("Arquivo não encontrado " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Arquivo corrompido");
        }
        
        LocadoraController controller = new LocadoraController(
            listaDeProdutos,
            listaDeClientes,
            listaDeEmprestimos,
            listaDeAtrasos,
            listaDeCompras
            );
            
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
                    int opcaoCliente;
                    do {
                        System.out.println("\n--- Área do Cliente ---");
                        System.out.println("1. Ver Catálogo de Filmes");
                        System.out.println("2. Ver Catálogo de Séries");
                        System.out.println("3. Ver Catálogo de Jogos");
                        System.out.println("4. Meus Aluguéis Ativos");
                        System.out.println("5. Histórico de Compras");
                        System.out.println("0. Voltar ao Menu Principal");
                        System.out.print("Escolha uma opção: ");

                        opcaoCliente = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoCliente) {
                            case 1:
                                System.out.println("Ver Catálogo de Filmes");
                                break;
                            case 2:
                                System.out.println("Ver Catálogo de Séries");
                                break;
                            case 3:
                                System.out.println("Ver Catálogo de Jogos");
                                break;
                            case 4:
                                System.out.println("Meus Aluguéis Ativos");
                                break;
                            case 5:
                                System.out.println("Histórico de Compras");
                                break;
                            case 0:
                                System.out.println("Voltando ao Menu Principal...");
                                break;
                            default:
                                System.out.println("Opção inválida. Por favor, tente novamente.");
                        }
                    } while (opcaoCliente != 0);
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
                    try{
                        controller.salvar();
                        System.out.println("Dados salvos com sucesso!");
                        System.out.println("Encerrando o sistema");
                    }catch(IOException e){
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






    