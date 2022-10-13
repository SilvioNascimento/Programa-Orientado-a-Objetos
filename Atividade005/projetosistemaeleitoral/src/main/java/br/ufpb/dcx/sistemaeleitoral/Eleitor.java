package br.ufpb.dcx.sistemaeleitoral;

public class Eleitor {
    
    private String nome;
    private String titulo;

    public Eleitor(String nome, String titulo) {
        this.nome = nome;
        this.titulo = titulo;
    }

    public Eleitor(){
        this("", "");
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
}
