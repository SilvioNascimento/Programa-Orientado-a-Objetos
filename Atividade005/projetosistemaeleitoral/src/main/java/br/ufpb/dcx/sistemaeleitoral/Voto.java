package br.ufpb.dcx.sistemaeleitoral;

public class Voto {
    
    private int numeroVotado;

    public Voto(int numeroVotado) {
        this.numeroVotado = numeroVotado;
    }

    public Voto(){
        this(0);
    }

    public int getNumeroVotado() {
        return this.numeroVotado;
    }

    public void setNumeroVotado(int numeroVotado) {
        this.numeroVotado = numeroVotado;
    }

    
}
