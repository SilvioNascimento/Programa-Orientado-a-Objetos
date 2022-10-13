package br.ufpb.dcx.sistemaeleitoral;

public class Candidato {
    
    private String nome;
    private int numero;
    private Partido partido;

    public Candidato(String nome, int numero, Partido partido) {
        this.nome = nome;
        this.numero = numero;
        this.partido = partido;
    }
    
    public Candidato() {
        this("", 0, null);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Partido getPartido() {
        return this.partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nNúmero: " + this.numero + "\nPartido: " + this.partido;
    } 
}
