package br.ufpb.dcx.silvio;

public class MensagemParaAlguem extends Mensagem {

    private String emailDestinatário;

    public MensagemParaAlguem(String texto, String emailRemetente, String emailDestinatário, boolean anonima) {
        super(texto, emailRemetente, anonima);
        this.emailDestinatário = emailDestinatário;
    }

    public String getEmailDestinatario() {
        return this.emailDestinatário;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatário = emailDestinatario;
    }

    @Override
    public String getTextoCompletoAExibir() {
        if(this.ehAnonima()) {
            return "Mensagem para " + this.getEmailDestinatario() + ". Texto: "
            + super.getTexto();
        } else {
            return "Mensagem de " + super.getEmailRemetente() + " para " +
            this.getEmailDestinatario() + ". Texto: " + super.getTexto();
        }
    }
}
