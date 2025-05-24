package view.funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.LocadoraController;
import model.produtos.Produto;

public abstract class GerenciarTransacoes {
    public static void gerenciarTransacoes(LocadoraController controller, Scanner scanner) {

        int opcaoTransacoes = 0;
        System.out.println("1 - Realizar Emprestimo ");
        System.out.println("2 - Registrar Devolucao ");
        System.out.println("3 - Registrar Compra ");
        opcaoTransacoes = scanner.nextInt();

        switch (opcaoTransacoes) {
            case 1:
                int codigoProduto = 0;
                List<Produto> carrinhoUsuario = new ArrayList<>();

                System.out.println("Digite seu cpf !!");
                long cpf = scanner.nextLong();

                do {
                    System.out
                            .println("Digite o código do produto que deseja adicionar ao carrinho (0 para finalizar):");
                    codigoProduto = scanner.nextInt();

                    if (codigoProduto == 0) {
                        if (carrinhoUsuario.isEmpty()) {
                            System.out.println("Carrinho Vazio !!!");
                        }
                        System.out.println("Finalizando carrinho:");
                        carrinhoUsuario.forEach(System.out::println);
                        break;
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
                        System.out.println("Código inválido ou produto já no carrinho.");
                    }

                } while (codigoProduto != 0);

                break;

            default:
                break;
        }

    }
}
