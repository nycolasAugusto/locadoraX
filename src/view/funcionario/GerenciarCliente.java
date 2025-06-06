package view.funcionario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import controller.LocadoraController;

public abstract class GerenciarCliente {

    public static void gerenciarCliente(LocadoraController controller, Scanner scanner) {
        System.out.println("Gerenciar Clientes");
        int opcaoGerenciarCliente = 0;
        do {
            System.out.println("Digite uma opção");
            System.out.println("1 - Cadastra novo cliente");
            System.out.println("2 - Remover Cliente inativo");
            System.out.println("0 - Volta ao menu do funcionario");
            opcaoGerenciarCliente = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoGerenciarCliente) {
                case 1:
                    System.out.println("Digite o nome :");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o email");
                    String email = scanner.nextLine();
                    System.out.println("Endereço");
                    String endereco = scanner.nextLine();
                    Long cpf;
                    do {
                        try {
                            System.out.print("Digite o CPF (ou 0 para voltar): ");
                            cpf = scanner.nextLong();
                            scanner.nextLine();

                            if (cpf == 0) {
                                System.out.println("Voltando ao menu...");
                                return;
                            }

                            if (controller.cpfValido(cpf)) {
                                System.out.println("CPF já registrado! Digite outro CPF ou 0 para voltar:");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Digite apenas números.");
                            System.err.println(
                                    "Cpf digitado Como String" + e.getMessage() + LocalDate.now() + LocalTime.now() +",");
                            scanner.nextLine();
                            cpf = -1L;
                        }
                    } while (controller.cpfValido(cpf) || cpf <= 0);

                    Long numero = null;

                    while (true) {
                        try {
                            System.out.print("Número de Contato: ");
                            numero = scanner.nextLong();
                            scanner.nextLine();
                            break; 
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Digite apenas números.");
                            System.err.println("Numero Como String " + e.getMessage()+ LocalDate.now() + LocalTime.now() +",");
                            scanner.nextLine();
                        }
                    }

                    System.out.println("Data De Nascimento : ");
                    String dataNascimento = scanner.nextLine();
                    LocalDate localDateNasicimento;
                    try {
                        localDateNasicimento = controller.dataStringParaLocaLDate(dataNascimento);
                    } catch (Exception e) {
                        System.out.println("Data Invalida, retornando...");
                        System.err.println("Data fora de formato " + e.getMessage() + "Data :" + LocalDate.now()
                                + " Hora :" + LocalTime.now() +",");
                        break;
                    }

                    boolean cadastrarUsuario = controller.cadastrarCliente(nome, email, endereco, cpf, numero,
                            dataNascimento);
                    System.out.println(
                            cadastrarUsuario ? "Cliente cadastrado com Sucesso !!" : "Erro ao cadastrar Usuario !");
                    break;
                case 2:
                    // falta fazer
                    break;
                case 0:
                    System.out.println("Voltando ao menu");
                    break;

                default:
                    break;
            }

        } while (opcaoGerenciarCliente != 0);

    }
}
