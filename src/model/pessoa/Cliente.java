package model.pessoa;

import java.time.LocalDate;
import java.util.List;

import model.produtos.Produto;
import model.transacoes.Emprestimo;

public final class Cliente extends Pessoa {
    private List<Produto> produtosComprados;
    private List<Emprestimo> produtosAlugados;
    private double multaApagar;

    private Cliente(String nome, String email, String endereco, long cpf, long numero, LocalDate dataNascimento) {
        super(nome, email, endereco, cpf, numero, dataNascimento);
       
    }   
    
    public static Cliente criarCliente(String nome, String email, String endereco, long cpf, long numero, LocalDate dataNascimento){
        return new Cliente(nome, email, endereco, cpf, numero, dataNascimento);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo){
        produtosAlugados.add(emprestimo);
    }
    public void adicionarCompra(Produto produto){
        produtosComprados.add(produto);
    }

    public List<Emprestimo> getListaDeCompras() {
        return produtosAlugados;
    }

    public void setListaDeCompras(List<Emprestimo> produtosAlugados) {
        this.produtosAlugados = produtosAlugados;
    }
    
    public double getMultaApagar() {
        return multaApagar;
    }

    public void setMultaApagar(double multaApagar) {
        this.multaApagar = multaApagar;
    }
    @Override
    public String toString() {
        return "Cliente [nome=" + nome + ", cpf=" + cpf + ", listaDeCompras=" + produtosAlugados + "]";
    }





    


    
    

    
    

    
}
