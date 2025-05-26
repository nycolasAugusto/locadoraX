package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.pessoa.Cliente;
import model.produtos.Filme;
import model.produtos.Jogo;
import model.produtos.Produto;
import model.produtos.Serie;
import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;
import model.transacoes.Atraso;
import model.transacoes.Compra;
import model.transacoes.Emprestimo;

public class LocadoraController {
    
    private List<Produto> produtos;
    private List<Cliente> clientes;
    private List<Emprestimo> emprestimos;
    private List<Atraso> atrasos;
    private List<Compra> compras;
    
    public LocadoraController(List<Produto> produtos, List<Cliente> clientes, List<Emprestimo> emprestimos,
            List<Atraso> atrasos, List<Compra> compras) {
        this.produtos = produtos;
        this.clientes = clientes;
        this.emprestimos = emprestimos;
        this.atrasos = atrasos;
        this.compras = compras;
    }
    public void adicionarProduto(Produto produto){produtos.add(produto);}
    public void adicionarCliente(Cliente cliente){clientes.add(cliente);}
    public void adicionarEmprestimo(Emprestimo emprestimo){emprestimos.add(emprestimo);}
    public void adicionarAtraso(Atraso atraso){atrasos.add(atraso);}
    public void adicionarCompra(Compra compra){compras.add(compra);}
    public void removerProduto(Produto produto){produtos.remove(produto);}
    public void removerCliente(Cliente cliente){clientes.remove(cliente);}
    public void removerEmprestimo(Emprestimo emprestimo){emprestimos.remove(emprestimo);}
    public void removerAtraso(Atraso atraso){atrasos.remove(atraso);}
    public void removerCompra(Compra compra){compras.remove(compra);}


    public int gerarNovoCodigo(){
        return produtos.stream()
                .mapToInt(p -> p.getCodigo())
                .max()
                .orElse(0) + 1;
    }

    public boolean cadastrarFilme(String nome, LocalDate dataCadastro, int quantidadeEstoque, String categoria, int classe, int classificacaoIndicativa, int duracaoMinutos){

        Filme filme = Filme.criarFilme(gerarNovoCodigo(), nome, dataCadastro, quantidadeEstoque, Categoria.getCategoria(categoria), Classe.getClasse(classe), 
        Classificacao.getClasse(classificacaoIndicativa), duracaoMinutos);    

        adicionarProduto(filme);

        return true;
    }
    public boolean cadastrarSerie(String nome,  LocalDate dataCadastro, int quantidadeEstoque, String categoria, int classe, int classificacaoIndicativa,int temporada){
        
        Serie serie = Serie.criarSerie(gerarNovoCodigo(), nome,  dataCadastro, quantidadeEstoque, Categoria.getCategoria(categoria), Classe.getClasse(classe), 
        Classificacao.getClasse(classe), temporada);
        adicionarProduto(serie);
        return true;
    }
    public boolean cadastrarJogo(String nome, LocalDate dataCadastro, int quantidadeEstoque, String categoria, int classe, int classificacaoIndicativa,String plataforma){

        Jogo jogo = Jogo.criarJogo(gerarNovoCodigo(), nome, dataCadastro, quantidadeEstoque, Categoria.getCategoria(categoria), Classe.getClasse(classe), 
        Classificacao.getClasse(classe), plataforma);

        adicionarProduto(jogo);
        
        return true;

    }
    //Melhorar Esta Listagem !!

    public List<String> listarTodosProdutos(){
        return produtos.stream()
            .map(p -> p.toString())
            .toList();
    }

    public List<String> listarEmprestimo(){
        return emprestimos.stream()
            .map(p -> p.toString())
            .toList();
    }


    public boolean emprestarProdutos(List<Produto> carrinhoCliente, int cpf , LocalDate dataDevolucao , LocalDate dataEmprestimo){
        
        
        Cliente cliente = acharCliente(cpf);

        for (Produto produto : carrinhoCliente) {
            produto.diminuirEstoque();
        }

        Emprestimo emprestimo  = Emprestimo.criarEmprestimo(carrinhoCliente, cliente, dataEmprestimo, dataDevolucao, cpf);
        
        cliente.adicionarEmprestimo(emprestimo);
        emprestimos.add(emprestimo);
        
        return true;


    }
    public LocalDate dataStringParaLocaLDate(String dataUser){
        if (dataUser.contains("-")) {
                dataUser = dataUser.replaceAll("-", "/");
            }
            DateTimeFormatter frmtReceber = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataConvertida = LocalDate.parse(dataUser, frmtReceber);
            return dataConvertida;
    }



    public boolean cadastrarCliente(String nome, String email, String endereco, long cpf, long numero, String dataNascimento){
        
        Cliente cliente = Cliente.criarCliente(nome, email, endereco, cpf, numero, dataStringParaLocaLDate(dataNascimento));
        adicionarCliente(cliente);
        return true;
    
    }


    public Cliente acharCliente(int cpf){
        return clientes.stream()
            .filter(c -> c.getCpf() == cpf)
            .findFirst()
            .orElse(null);
    }

    public Produto procurarProdutoPorCodigo(int codigo){
        return produtos.stream()
            .filter(p -> p.getCodigo() == codigo)
            .findFirst()
            .orElse(null);
    }

    public boolean verificarCodigo(int codigo, List<Produto> carrinho ){
        for (Produto produtoCarrinho : carrinho) {
            if (produtoCarrinho.getCodigo() == codigo) {
                return false; 
            }
        }

        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return true; 
            }
        }
        return false; 
    }
    }
    





    




    










