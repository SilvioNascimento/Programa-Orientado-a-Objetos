package br.ufpb.silvio.sistemaAmigo;

public class Amigo {

    private String nome;
    private String email;
    private String emailAmigoSorteado;

    public Amigo(String nome, String emailAmigo) {
        this.nome = nome;
        this.email = emailAmigo;
        this.emailAmigoSorteado = null;     // Atributo emailAmigoSorteado: 
                                            // Ainda não sabemos qual é o amigo secreto de um amigo
                                            // Então ignoramos o parâmetro voltado a ela e, dentro do
                                            // corpo do construtor, denominamos o email do amigo sorteado 
                                            // como null
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAmigoSorteado() {
        return this.emailAmigoSorteado;
    }

    public void setEmailAmigoSorteado(String emailAmigoSorteado) {
        this.emailAmigoSorteado = emailAmigoSorteado;
    }
}
