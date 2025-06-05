package view.cliente;

import java.util.Scanner;
import controller.LocadoraController;
import model.produtos.produtosUtil.Produto;

public class ClienteView {

    public static void menuCliente(LocadoraController controller, Scanner scanner) {
        int opcaoCliente;
        do {
            System.out.println("\n--- Área do Cliente ---");
            System.out.println("1. Explorar Catálogo de Produtos");
            System.out.println("2. Meus Aluguéis Ativos"); 
            System.out.println("3. Minhas Compras");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoCliente = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcaoCliente) {
                case 1:
                    int opcaoExplorar;
                    do {
                        System.out.println("\n--- Explorar Catálogo ---");
                        System.out.println("1. Ver Catálogo Completo");
                        System.out.println("2. Listar por Categoria (Filme, Série, Jogo)");
                        System.out.println("3. Listar os 10 Mais Populares");
                        System.out.println("0. Voltar à Área do Cliente");
                        System.out.print("Escolha uma opção: ");

                        opcaoExplorar = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (opcaoExplorar) {
                            case 1:
                                System.out.println("\n--- Catálogo Completo ---");
                                if (controller.getProdutos().isEmpty()) {
                                    System.out.println("Não há nenhum produto cadastrado.");
                                } else {
                                    for (Produto produto : controller.getProdutos()) {
                                        System.out.println(produto.exibirInfoCliente());
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("\n--- Listar por Categoria ---");
                                System.out.println("Lógica para obter categoria e chamar controller.listarProdutosPorCategoria() comentada.");
                                break;
                            case 3:
                                System.out.println("\n--- Os 10 Produtos Mais Populares ---");
                                System.out.println("Chamada para controller.listarMaisPopulares(10) comentada.");
                                break;
                            case 0:
                                System.out.println("Voltando à Área do Cliente...");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        } // Fim switch opcaoExplorar

                    } while (opcaoExplorar != 0);

                    break; // Quebra do case 1 do switch opcaoCliente

                case 2: // Anteriormente case 3
                    System.out.println("\n--- Meus Aluguéis Ativos ---");
                    System.out.println("Chamada para controller.listarAlugueisAtivos() comentada.");
                    break;

                case 3: // Anteriormente case 4
                    System.out.println("\n--- Meu Histórico de Aluguéis ---");
                    System.out.println("Chamada para controller.listarHistoricoAlugueis() comentada.");
                    break;

                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            } 

        } while (opcaoCliente != 0);

    } 

} 
