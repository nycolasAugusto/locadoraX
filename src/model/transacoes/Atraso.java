package model.transacoes;

public class Atraso {
    
    private Emprestimo emprestimoAtrasado;
    private int diasAtrasado;


    public Atraso(Emprestimo emprestimoAtrasado, int diasAtrasado) {
        this.emprestimoAtrasado = emprestimoAtrasado;
        this.diasAtrasado = diasAtrasado;
    }

    public Emprestimo getEmprestimoAtrasado() {
        return emprestimoAtrasado;
    }
    public void setEmprestimoAtrasado(Emprestimo emprestimoAtrasado) {
        this.emprestimoAtrasado = emprestimoAtrasado;
    }
    public int getDiasAtrasado() {
        return diasAtrasado;
    }
    public void setDiasAtrasado(int diasAtrasado) {
        this.diasAtrasado = diasAtrasado;
    }


    



}
