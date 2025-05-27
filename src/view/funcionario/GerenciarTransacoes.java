package view.funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.LocadoraController;
import model.produtos.produtosUtil.Produto;

public abstract class GerenciarTransacoes {
    public static void gerenciarTransacoes(LocadoraController controller, Scanner scanner) {
        
        List<Produto> carrinhoUsuario = new ArrayList<>();
        Long cpf;
        int opcaoTransacoes = 0;
        int codigoProduto = 0;
        System.out.println("1 - Realizar Emprestimo ");
        System.out.println("2 - Registrar Devolucao ");
        System.out.println("3 - Registrar Compra ");
        opcaoTransacoes = scanner.nextInt();
        
        switch (opcaoTransacoes) {
            case 1:
                System.out.println("Digite seu cpf !!");
                cpf = scanner.nextLong();
                scanner.nextLine();
                 while (!controller.cpfValido(cpf) && cpf != 0) {
                    System.out.println("Usuário não cadastrado ou usuário com empréstimo ativo.");
                    System.out.println("Digite o CPF correto, ou digite 0 para retornar ao menu de TRANSAÇÕES.");
                    cpf = scanner.nextLong();
                }

                if (cpf == 0) {
                    System.out.println("Retornando ao menu de Transações...");
                    break; 
                }
                System.out.println("Digite a data de emprestimo :");
                String dataE = scanner.nextLine();
               
                do {
                    System.out
                            .println("Digite o código do produto que deseja adicionar ao carrinho ( 0 para finalizar):");
                    codigoProduto = scanner.nextInt();

                    if (codigoProduto == 0) {
                        if (carrinhoUsuario.isEmpty()) {
                            System.out.println("Carrinho Vazio !!!");
                            break;
                        }
                        System.out.println("Finalizando carrinho:");
                        carrinhoUsuario.forEach(System.out::println);
                        System.out.println("Preco total do Emprestimo : " + controller.calcularValorEmprestimo(carrinhoUsuario) + " R$");
                        System.out.println("Data De emprestimo : " + dataE + " até " + controller.dataStringParaLocaLDate(dataE).plusDays(7));
                        System.out.println("Caso nao devolva ate a data determinada sera cobrado 5% de multa por dia de atraso !!");
                        System.out.println("Deseja confirmar o emprestimo ? ");
                        System.out.println("1 - SIM\n2 - Nao");
                        int confirmar = scanner.nextInt();
                        if (confirmar == 1) {
                            controller.emprestarProdutos(carrinhoUsuario, cpf, controller.dataStringParaLocaLDate(dataE)  );
                            System.out.println("Emprestimo realizado !");
                            break;
                        } else if (confirmar == 2) {
                            System.out.println("Carrinho excluido, o");
                            carrinhoUsuario.removeAll(carrinhoUsuario);
                            break;
                        }

                    }

                    if (controller.verificarCodigo(codigoProduto, carrinhoUsuario)) {
                        Produto produto = controller.procurarProdutoPorCodigo(codigoProduto);
                                                                                            
                        if (produto != null) {
                            carrinhoUsuario.add(produto);
                            System.out.println("Produto adicionado ao carrinho!");
                        } else {
                            System.out.println("Produto não encontrado!");
                        }
                    } else {
                        System.out.println("Código inválido ou produto já esta no carrinho.");
                    } 

                } while (codigoProduto != 0);

                break;

            case 2 :
                System.out.println("Digite seu cpf !!");
                cpf = scanner.nextLong();
                //System.out.println("Digite seu cpf !!");
                //cpf = scanner.nextLong();
                controller.listarEmprestimo().forEach(System.out::println);
                
                break;
           case 3 :

                        System.out.println("Digite seu CPF:");
                        long cpfCompra = scanner.nextLong();

                        do {
                                System.out.println("Digite o código do produto para comprar (0 para finalizar):");
                                codigoProduto = scanner.nextInt();

                                if (codigoProduto == 0) break;

                                if (controller.verificarCodigo(codigoProduto, carrinhoUsuario)) {
                                        Produto prod = controller.procurarProdutoPorCodigo(codigoProduto);
                                        if (prod != null) {
                                                carrinhoUsuario.add(prod);
                                                System.out.println("Produto adicionado ao carrinho!");
                                        } else {
                                                System.out.println("Produto não encontrado!");
                                        }
                                } else {
                                        System.out.println("Código inválido ou produto já no carrinho.");
                                }
                        } while (true);

                        if (carrinhoUsuario.isEmpty()) {
                                System.out.println("Carrinho vazio! Nenhuma compra realizada.");
                        } else {
                            System.out.println("\n========== CARRINHO FINALIZADO ==========\n");
                            carrinhoUsuario.forEach(p -> System.out.println(" •"));
                            System.out.println("\n=========================================\n");
                            System.out.println("Valor Total da Compra : " + controller.calcularValorCompre(carrinhoUsuario));

                                System.out.println("Deseja confirmar compra? (1 - Sim / 2 - Não)");

                                int opcao = scanner.nextInt();
                                if (opcao == 1) {
                            
                                    List<Integer> codigos = new ArrayList<>();
                                    for (Produto p : carrinhoUsuario) {
                                            codigos.add(p.getCodigo());
                                    }

                                    boolean sucesso = controller.comprarProdutos(codigos, cpfCompra);
                                    System.out.println(sucesso 
                                            ? "Compra confirmada e estoque atualizado!" 
                                            : "Erro: cliente não encontrado.");
                            } else {
                                    System.out.println("Compra cancelada.");
                            }
                        }

                break;

            default:
                break;
        }
    }
}
