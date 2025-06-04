package view.funcionario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import controller.LocadoraController;
import model.produtos.produtosUtil.Produto;

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
            System.out.println("5. Alterar Produto :");
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
                     System.out.println("Classe:\n1 - Premium\n2 - Intermediario\n3 - Classico");
                    int classeFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Classificação Indicativa (1 - LIVRE, 2 - 18+ ): ");
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
                    System.out.println("Classe:\n1 - Premium\n2 - Intermediario\n3 - Classico");
                    int classeSerie = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Classificação Indicativa (1 - LIVRE, 2 - 18+ ): ");
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
                    System.out.println("Classe:\n1 - Premium\n2 - Intermediario\n3 - Classico");
                    int classeJogo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Classificação Indicativa (1 - LIVRE, 2 - 18+ ): ");
                    int classificacaoJogo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Plataforma: ");
                    String plataformaJogo = scanner.nextLine();

                    boolean jogoCadastrado = controller.cadastrarJogo(nomeJogo, LocalDate.now(),
                            estoqueJogo, categoriaJogo, classeJogo,
                            classificacaoJogo, plataformaJogo);
                    System.out.println(jogoCadastrado ? "Jogo cadastrado com sucesso!" : "Falha ao cadastrar jogo.");
											break;
						case 4:
					System.out.println("\n--- Lista de Todos os Produtos (Funcionário) ---");
					if (controller.getProdutos().isEmpty()) {
							System.out.println("Não há nenhum produto cadastrado.");
					} else {
							for (Produto produto : controller.getProdutos()) {
									System.out.println(produto.exibirInfoFuncionario());
							}
					}
					break;	
                case 5:
                    System.out.println("Digite o código do produto:");
                    int codigoProduto = scanner.nextInt();
                    scanner.nextLine(); 
                    Produto produto = controller.procurarProdutoPorCodigo(codigoProduto);

                    if (produto == null) {
                        System.out.println("Produto não encontrado!");
                        break;
                    }

                    System.out.println("Qual atributo deseja alterar:\n1 - Classe\n2 - Categoria\n3 - Nome");
                    int opcaoAlterarProduto = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoAlterarProduto) {
                        case 1:
                            System.out.println("Classe atual: " + produto.getClasse());
                            System.out.println("Para qual classe deseja alterar:");
                            System.out.println("1 - Premium\n2 - Intermediario\n3 - Classico");

                            int novaClasse = scanner.nextInt();
                            scanner.nextLine(); 
                            try {
                                controller.alterarClasse(produto, novaClasse);
                                
                            } catch (IllegalArgumentException e) {
                                System.err.println ("Classe nao encontrada"+ e.getMessage() + LocalDate.now() + LocalTime.now());
                                System.out.println("Classe nao encontrada");
                                break;
                            }
                            System.out.println("Classe alterada com sucesso!");
                            break;

                        case 2:
                            System.out.println("Categoria atual: " + produto.getCategoria());
                            System.out.println("Digite a nova categoria (EX: Romance, Terror, Comédia, etc):");
                            String novaCategoria = scanner.nextLine();
                            try {
                                controller.alterarCategoria(produto, novaCategoria);
                            } catch (Exception e) {
                                System.err.println("Categoria nao localizada "+ e.getMessage() + LocalDate.now() + LocalTime.now());
                                System.out.println("Categoria nao localizada!");
                                break;
                            }
                            System.out.println("Categoria alterada com sucesso!");
                            break;

                        case 3:
                            System.out.println("Nome atual: " + produto.getNome());
                            System.out.println("Digite o novo nome:");
                            String novoNome = scanner.nextLine();
                            try {
                                controller.alterarNome(produto, novoNome);
                                
                            } catch (Exception e) {
                                System.err.println(" Texto vazio " + e.getMessage() + LocalDate.now() + LocalTime.now());
                                System.out.println("Nao pode salvar um nome vazio!");
                                break;
                            }
                            System.out.println("Nome alterado com sucesso!");
                            break;

                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao Menu de Funcionário...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcaoCadastroProduto != 0);

    }

}
