package model.transacoes;

import java.io.Serializable;
import java.util.List;

import model.Ilocadora;
import model.pessoa.Cliente;
import model.produtos.produtosUtil.Produto;

public class Atraso  implements Serializable, Ilocadora{
    
    private Cliente cliente ;
    private List<Produto> produtosEntreguesComAtraso;
    private int diasAtrasado;
    private double multa;


     
    public Atraso(Cliente cliente, List<Produto> produtosEntreguesComAtraso, int diasAtrasado, double multa) {
        this.cliente = cliente;
        this.produtosEntreguesComAtraso = produtosEntreguesComAtraso;
        this.diasAtrasado = diasAtrasado;
        this.multa = multa;
    }
    

    public static Atraso criarAtraso(Cliente cliente, List<Produto> produtosEntreguesComAtraso, int diasAtrasado, double multa){
        return new Atraso( cliente, produtosEntreguesComAtraso,  diasAtrasado,  multa);
    }

    
    public Cliente getCliente() {
        return cliente;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public List<Produto> getProdutosEntreguesComAtraso() {
        return produtosEntreguesComAtraso;
    }


    public void setProdutosEntreguesComAtraso(List<Produto> produtosEntreguesComAtraso) {
        this.produtosEntreguesComAtraso = produtosEntreguesComAtraso;
    }


    public double getMulta() {
        return multa;
    }


    public void setMulta(double multa) {
        this.multa = multa;
    }


    public int getDiasAtrasado() {
        return diasAtrasado;
    }
    public void setDiasAtrasado(int diasAtrasado) {
        this.diasAtrasado = diasAtrasado;
    }

    
    @Override
    public String listagemFormal() {

        return cliente.getNome()  + " atrasou " + diasAtrasado + " dias " + "Multado em : " + multa + " reais";
    }


    @Override
	public String toString() {
    String nomesProdutos = "";
    boolean primeiro = true;
    for (Produto produto : produtosEntreguesComAtraso) {
        if (!primeiro) {
            nomesProdutos += ", ";
        }
        nomesProdutos += produto.getNome();
        primeiro = false;
    }
     return cliente.getNome() + " - " + nomesProdutos + " - " + diasAtrasado + " dias de atraso.";
	}

}
