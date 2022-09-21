package br.ufpb.dcx.silvio;

public class MensagemParaTodos extends Mensagem {

    public MensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        super(texto, emailRemetente, anonima);
    }

    @Override
    public String getTextoCompletoAExibir() {
        if (this.ehAnonima() == true) {
            return "Mensagem para todos. Texto: " + super.getTexto();
        } else {
            return "Mensagem de " + super.getEmailRemetente() + " para todos. Texto: " + super.getTexto();
        }
    }
}
