package model.transacoes;
import java.io.Serializable;
import java.util.List;

import model.pessoa.Cliente;
import model.produtos.produtosUtil.Produto;

public class Compra implements Serializable{
    
    private List<Produto> produtosComprados;
    private Cliente cliente;
    private double precoCompra;

    private Compra(List<Produto> produtosComprados, Cliente cliente, double precoCompra) {
        this.produtosComprados = produtosComprados;
        this.cliente = cliente;
        this.precoCompra = precoCompra;
    }

    public static Compra criarCompra(List<Produto> produtosComprados, Cliente cliente, double precoCompra){
        return new Compra(produtosComprados, cliente, precoCompra);
    }
    
    public List<Produto> getProdutosComprados() {
        return produtosComprados;
    }
    public void setProdutosComprados(List<Produto> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public double getPrecoCompra() {
        return precoCompra;
    }
    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }
    private String mostraApenasNomeProduto(List<Produto> lista){
        String resultado = "";
        for (Produto produto : lista) {
            resultado += produto.getNome();
        } 
        return resultado;
    }

    @Override
    public String toString() {
        return "#" + cliente.getNome() + " [produtosComprados=" + mostraApenasNomeProduto(produtosComprados) ;
    }

    

    



    
}
