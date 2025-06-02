package view.funcionario;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import controller.LocadoraController;
import model.produtos.produtosUtil.Produto;
import model.transacoes.Emprestimo;

public abstract class GerenciarTransacoes {
    public static void gerenciarTransacoes(LocadoraController controller, Scanner scanner) {

        List<Produto> carrinhoUsuario = new ArrayList<>();
        Long cpf;
        LocalDate dataDevolucao = null;
        int opcaoTransacoes = 0;
        int codigoProduto = 0;
        System.out.println("1 - Realizar Emprestimo ");
        System.out.println("2 - Registrar Devolucao ");
        System.out.println("3 - Prorrogar Devolucao ");
        System.out.println("4 - Registrar Compra ");
        opcaoTransacoes = scanner.nextInt();

        switch (opcaoTransacoes) {
            case 1:
                switch (opcaoTransacoes) {
                    case 1:
                        System.out.println("Digite seu cpf !!");
                        cpf = scanner.nextLong();
                        scanner.nextLine();
                        while (!controller.cpfValido(cpf) && cpf != 0) {
                            System.out.println("Usuário não cadastrado ou usuário com empréstimo ativo.");
                            System.out
                                    .println("Digite o CPF correto, ou digite 0 para retornar ao menu de TRANSAÇÕES.");
                            cpf = scanner.nextLong();
                            scanner.nextLine();

                        }

                        if (cpf == 0) {
                            System.out.println("Retornando ao menu de Transações...");
                            break;
                        }
                        System.out.println("Digite a data de emprestimo :");
                        String dataE = scanner.nextLine();
                        try {
                            dataDevolucao = controller.dataStringParaLocaLDate(dataE).plusDays(7);
                        } catch (Exception e) {
                            System.err.println("Data fora de formato " + e.getMessage() + "Data :" + LocalDate.now()
                                    + " Hora :" + LocalTime.now());
                            break;
                        }
                        do {
                            System.out
                                    .println(
                                            "Digite o código do produto que deseja adicionar ao carrinho ( 0 para finalizar):");
                            codigoProduto = scanner.nextInt();

                            if (codigoProduto == 0) {
                                if (carrinhoUsuario.isEmpty()) {
                                    System.out.println("Carrinho Vazio !!!");
                                    break;
                                }
                                System.out.println("\n========== CARRINHO FINALIZADO ==========\n");
                                carrinhoUsuario.forEach(p -> System.out.println(" •" + p));
                                System.out.println("\n=========================================\n");
                                System.out
                                        .println("Valor Total da Compra : "
                                                + controller.calcularValorCompre(carrinhoUsuario));

                                System.out.println("Data De emprestimo : " + dataE + " até "
                                        + dataDevolucao);
                                System.out.println(
                                        "Caso nao devolva ate a data determinada sera cobrado 5% de multa por dia de atraso !!");
                                System.out.println("Deseja confirmar o emprestimo ? ");
                                System.out.println("1 - SIM\n2 - Nao");
                                int confirmar = scanner.nextInt();

                                if (confirmar == 1) {

                                    try {
                                        LocalDate dataEmprestimo = controller.dataStringParaLocaLDate(dataE);
                                        controller.emprestarProdutos(carrinhoUsuario, cpf, dataEmprestimo);
                                    } catch (DataFormatException e) {
                                        System.err.println("Emprestimo nao Efetuado " + e.getMessage() + "Data :"
                                                + LocalDate.now() + " Hora :" + LocalTime.now());
                                        break;
                                    }catch(IOException e){
                                        System.err.println("Emprestimo criado erroneamente "+ e.getMessage() + "Data :"
                                        + LocalDate.now() + " Hora :" + LocalTime.now());
                                    }
                                    System.out.println("Empréstimo realizado!");

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
                }
                break;

            case 2:
                System.out.println("Digite seu cpf !!");
                cpf = scanner.nextLong();
                scanner.nextLine();
                if (!controller.clientePossuiEmprestimo(cpf)) {
                    System.out.println("Cliente nao encontrado ou nao possui emprestimo ativo ");
                    break;
                }
                System.out.println("Digite a data de devolucao :");
                String dataDevolvida = scanner.nextLine();

                List<Integer> codigosParaDevolver = new ArrayList<>();

                do {
                    System.out.println("Digite o código do produto para devolver (0 para finalizar):");
                    codigoProduto = scanner.nextInt();

                    if (codigoProduto == 0) {
                        if (codigosParaDevolver.isEmpty()) {
                            System.out.println("Nenhum produto para devolver.");
                            break;
                        }

                        System.out.println("\n========== PRODUTOS PARA DEVOLUÇÃO ==========\n");
                        codigosParaDevolver.forEach(
                                c -> System.out.println(" • Produto: " + controller.procurarProdutoPorCodigo(c)));
                        System.out.println("\n=============================================\n");

                        System.out.println("Deseja confirmar a devolução? (1 - Sim / 2 - Não)");
                        int confirmarDevolucao = scanner.nextInt();
                        if (confirmarDevolucao == 1) {
                            boolean sucesso = controller.devolverProdutos(cpf, dataDevolvida, codigosParaDevolver);
                            System.out.println(
                                    sucesso ? "Devolução registrada com sucesso!" : "Erro ao registrar devolução.");
                        } else {
                            System.out.println("Devolução cancelada.");
                            codigosParaDevolver.clear();
                        }
                        break;
                    }

                    if (!codigosParaDevolver.contains(codigoProduto)) {
                        codigosParaDevolver.add(codigoProduto);
                        System.out.println("Código adicionado!");
                    } else {
                        System.out.println("Código já informado anteriormente.");
                    }

                } while (codigoProduto != 0);

                break;
            case 3:
                System.out.println("Digite seu CPF para localizarmos o empréstimo:");
                long cpfProrrogar = scanner.nextLong();
                scanner.nextLine();

                Emprestimo emprestimo = controller.localizarEmprestimo(cpfProrrogar);
                if (emprestimo != null) {
                    System.out.println("Digite a nova data de devolução:");
                    String dataNovaString = scanner.nextLine();

                    try {
                        LocalDate dataNova = controller.dataStringParaLocaLDate(dataNovaString);
                        LocalDate dataEmprestimo = emprestimo.getDataEmprestimo();

                        if (!dataNova.isAfter(dataEmprestimo)) {
                            System.out.println("A nova data de devolução deve ser posterior à data do empréstimo ("
                                    + dataEmprestimo + ").");
                            break;
                        }

                        controller.alterarDataDevolucao(cpfProrrogar, dataNovaString);
                    } catch (Exception e) {
                        System.err.println("Data inválida: " + e.getMessage());
                    }

                } else {
                    System.out.println("Empréstimo não localizado.");
                }
                break;
            case 4:

                System.out.println("Digite seu CPF:");
                long cpfCompra = scanner.nextLong();
                if (!controller.cpfValido(cpfCompra)) {
                    System.out.println("Este cpf nao esta cadastrado ou possui um emprestimo em aberto");
                    break;
                }

                do {
                    System.out.println("Digite o código do produto para comprar (0 para finalizar):");
                    codigoProduto = scanner.nextInt();

                    if (codigoProduto == 0)
                        break;

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
                    carrinhoUsuario.forEach(p -> System.out.println(" •" + p));
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
