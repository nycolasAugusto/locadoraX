package view.funcionario;

import java.time.LocalDate;
import java.util.Scanner;

import controller.LocadoraController;

public abstract class GerenciarProdutos {

    public static void gerenciarProdutos(LocadoraController controller, Scanner scanner) {

        System.out.println("Gerenciar Produtos (Filmes, Séries, Jogos)");
        int opcaoCadastroProduto;
        do {
            System.out.println("\n--- Gerenciar Produtos ---");
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Cadastrar Série");
            System.out.println("3. Cadastrar Jogo");
            System.out.println("4. Listar Todos Produtos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcaoCadastroProduto = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoCadastroProduto) {
                case 1:
                    System.out.println("Cadastrar Filme");
                    System.out.print("Nome: ");
                    String nomeFilme = scanner.nextLine();
                    System.out.print("Quantidade em Estoque: ");
                    int estoqueFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Categoria (Ex: ACAO, DRAMA): ");
                    String categoriaFilme = scanner.nextLine();
                    System.out.print("Classe de Aluguel (1-LANCAMENTO, 2-PREMIUM, 3-NORMAL, 4-ANTIGO): ");
                    int classeFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Classificação Indicativa (Ex: LIVRE, 10 ANOS): ");
                    int classificacaoFilme = scanner.nextInt();
                    System.out.print("Duração em Minutos: ");
                    int duracaoFilme = scanner.nextInt();
                    scanner.nextLine();

                    boolean filmeCadastrado = controller.cadastrarFilme(nomeFilme, LocalDate.now(),
                            estoqueFilme, categoriaFilme, classeFilme, classificacaoFilme, duracaoFilme);
                    System.out.println(filmeCadastrado ? "Filme cadastrado com sucesso!" : "Falha ao cadastrar filme.");
                    break;
                case 2:
                    System.out.println("Cadastrar Série");
                    System.out.print("Nome: ");
                    String nomeSerie = scanner.nextLine();
                    System.out.print("Quantidade em Estoque: ");
                    int estoqueSerie = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Categoria (Ex: ACAO, DRAMA): ");
                    String categoriaSerie = scanner.nextLine();
                    System.out.print("Classe de Aluguel (1-LANCAMENTO, 2-PREMIUM, 3-NORMAL, 4-ANTIGO): ");
                    int classeSerie = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Classificação Indicativa (Ex: LIVRE, 10 ANOS): ");
                    int classificacaoSerie = scanner.nextInt();
                    System.out.print("Número da Temporada: ");
                    int temporadaSerie = scanner.nextInt();
                    scanner.nextLine();

                    boolean serieCadastrada = controller.cadastrarSerie(nomeSerie, LocalDate.now(),
                            estoqueSerie, categoriaSerie, classeSerie,
                            classificacaoSerie, temporadaSerie);
                    System.out.println(serieCadastrada ? "Série cadastrada com sucesso!" : "Falha ao cadastrar série.");
                    break;
                case 3:
                    System.out.println("Cadastrar Jogo");
                    System.out.print("Nome: ");
                    String nomeJogo = scanner.nextLine();
                    System.out.print("Quantidade em Estoque: ");
                    int estoqueJogo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Categoria (Ex: ACAO, RPG): ");
                    String categoriaJogo = scanner.nextLine();
                    System.out.print("Classe de Aluguel (1-LANCAMENTO, 2-PREMIUM, 3-NORMAL, 4-ANTIGO): ");
                    int classeJogo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Classificação Indicativa (Ex: LIVRE, 18 ANOS): ");
                    int classificacaoJogo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Plataforma: ");
                    String plataformaJogo = scanner.nextLine();

                    boolean jogoCadastrado = controller.cadastrarJogo(nomeJogo, LocalDate.now(), // dataCadastro
                            estoqueJogo, categoriaJogo, classeJogo,
                            classificacaoJogo, plataformaJogo);
                    System.out.println(jogoCadastrado ? "Jogo cadastrado com sucesso!" : "Falha ao cadastrar jogo.");
                    break;
                case 4:
                     System.out.println(controller.listarTodosProdutos());
                
                case 0:
                    System.out.println("Voltando ao Menu de Funcionário...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcaoCadastroProduto != 0);

    }

}
