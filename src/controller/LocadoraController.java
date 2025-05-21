package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.pessoa.Cliente;
import model.produtos.Filme;
import model.produtos.Produto;
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

    public boolean cadastrarFilme(String nome, LocalDate dataLancamento, LocalDate dataCadastro, int quantidadeEstoque, String categoria, int classe, int classificacaoIndicativa, int duracaoMinutos){

        Filme filme = new Filme(classificacaoIndicativa, nome, dataLancamento, dataCadastro, quantidadeEstoque, Categoria.getCategoria(categoria), Classe.getClasse(classe), 
        Classificacao.getClasse(classificacaoIndicativa), duracaoMinutos);    

        adicionarProduto(filme);

        return true;


    }
    public List<Produto> getProdutos() {
        return produtos;
    }

    




    









}
