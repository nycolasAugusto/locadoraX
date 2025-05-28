package view.funcionario;

import java.util.Scanner;

import controller.LocadoraController;

public abstract class ConsultarListagens {

    public static void consultarListagens(LocadoraController controller, Scanner scanner) {
    int opcao;

    do {
        System.out.println("\n======= CONSULTAR LISTAGENS =======");
        System.out.println("1 - Listar todos os empréstimos");
        System.out.println("2 - Listar todos os usuários");
        System.out.println("3 - Listar todos os produtos");
        System.out.println("4 - Listar produtos por tipo");
        System.out.println("5 - Listar atrasos");
        System.out.println("6 - Listar Emprestimos");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Digite um número válido.");
            scanner.next();
        }
        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("-> Listando todos os empréstimos...");
                controller.listarEmprestimo().forEach(System.out::println);
                break;

            case 2:
                System.out.println("-> Listando todos os usuários...");
                controller.listarUsuarios().forEach(System.out::println);
                break;

            case 3:
                System.out.println("-> Listando todos os produtos...");
                controller.listarTodosProdutos().forEach(System.out::println);
                break;

            case 4:
                int subOpcao;
                do {
                    System.out.println("\n--- LISTAR PRODUTOS POR TIPO ---");
                    System.out.println("1 - Listar Jogos");
                    System.out.println("2 - Listar Filmes");
                    System.out.println("3 - Listar Séries");
                    System.out.println("0 - Voltar");
                    System.out.print("Escolha uma opção: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Digite um número válido.");
                        scanner.next();
                    }
                    subOpcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (subOpcao) {
                        case 1:
                            System.out.println("-> Listando todos os Jogos...");
                            controller.listarJogos().forEach(System.out::println);
                            break;
                        case 2:
                            System.out.println("-> Listando todos os Filmes...");
                            controller.listarFilmes().forEach(System.out::println);
                            break;
                        case 3:
                            System.out.println("-> Listando todas as Séries...");
                            controller.listarSeries().forEach(System.out::println);
                            break;
                        case 0:
                            System.out.println("Voltando ao menu anterior...");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }

                } while (subOpcao != 0);
                break;

            case 5:
                System.out.println("-> Listando produtos em atraso...");
                controller.listarAtrasos().forEach(System.out::println);
                break;

            case 6:
                System.out.println("-> Listando todos os empréstimos...");
                controller.listarEmprestimo().forEach(System.out::println);
                break;

            case 0:
                System.out.println("Retornando ao menu principal...");
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
        }

    } while (opcao != 0);
}

}
