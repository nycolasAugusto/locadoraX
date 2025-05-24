package view.funcionario;
import java.util.Scanner;
import controller.LocadoraController;

public abstract class GerenciarCliente {
    

    public static void gerenciarCliente(LocadoraController controller , Scanner scanner){
        System.out.println("Gerenciar Clientes");
        int opcaoGerenciarCliente = 0;
        do{ 
            System.out.println("Digite uma opção");
            System.out.println("1 - Cadastra novo cliente");
            System.out.println("2 - Remover Cliente inativo");
            System.out.println("3 - Listar Clientes");
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
                    System.out.println("Cpf :");
                    Long cpf = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Numero de Contato");
                    Long numero = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Data De Nascimento : ");
                    String dataNascimento = scanner.nextLine();

                    boolean cadastrarUsuario = controller.cadastrarCliente( nome, email,  endereco,  cpf,  numero,  dataNascimento);
                    System.out.println(cadastrarUsuario ? "Cliente cadastrado com Sucesso !!" : "Erro ao cadastrar Usuario !");
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 0:
                    System.out.println("Voltando ao menu");
                    break;
            
                default:
                    break;
            }
        

        }while(opcaoGerenciarCliente != 0);






    }



}
