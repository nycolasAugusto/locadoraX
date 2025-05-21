package model.pessoa;

import java.time.LocalDate;
import java.util.List;

import model.produtos.Produto;
import model.transacoes.Emprestimo;

public final class Cliente extends Pessoa {

    private List<Emprestimo> produtosAlugados;
    private double multaApagar;

    public Cliente(String nome, String email, String endereco, long cpf, long numero, LocalDate dataNascimento,
            List<Produto> listaDeCompras) {
        super(nome, email, endereco, cpf, numero, dataNascimento);
       
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
