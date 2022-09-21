package br.ufpb.dcx.silvio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaAmigoMap {

    private List<Mensagem> mensagens;
    private Map<String, Amigo> amigos;

    public SistemaAmigoMap() {
        this.mensagens = new ArrayList<>();
        this.amigos = new HashMap<>();
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {

        if(!this.amigos.containsKey(emailAmigo)) {
            Amigo amigo = new Amigo(nomeAmigo, emailAmigo);
            this.amigos.put(emailAmigo, amigo);
        } else {
            throw new AmigoJaExisteException("Já existe essa pessoa no sistema");
        }
    }

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException{
        Amigo a = this.amigos.get(emailAmigo);
        if(a == null) {
            throw new AmigoInexistenteException("Não existe este usuário no sistema");
        } else {
            return a;
        }
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
        mensagens.add(new MensagemParaTodos(texto, emailRemetente, ehAnonima));
    }

    public void enviarMensagemParaAlguem(String texto, String emailRementente, String emailDestinatario, boolean ehAnonima) {
        mensagens.add(new MensagemParaAlguem(texto, emailRementente, emailDestinatario, ehAnonima));
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> listaMensagensAnonimas = new ArrayList<>();
        for(Mensagem mensagem : this.mensagens) {
            if(mensagem.ehAnonima()) {
                listaMensagensAnonimas.add(mensagem);
            }
        }
        return listaMensagensAnonimas;
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        return this.mensagens;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException {
        Amigo amigoA = this.amigos.get(emailDaPessoa);
        if(amigoA == null) {
            throw new AmigoInexistenteException("Não existe este usuário neste sistema");
        } else {
            amigoA.setEmailAmigoSorteado(emailAmigoSorteado);
            return;
        }
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {
        Amigo amigoA = this.amigos.get(emailDaPessoa);
        if(amigoA == null) {
            throw new AmigoInexistenteException("Não existe esse usuário no sistema");
        } else {
            if(amigoA.getEmailAmigoSorteado() == null) {
                throw new AmigoNaoSorteadoException("Amigo secreto não sorteado para este usuário");
            } else {
                return amigoA.getEmailAmigoSorteado();
            }
        }
    }

    public String sortear() {
        int pos = 0;
        String relatorio = ""; 
        List<String> amgS = new ArrayList<>();
        
        for(String email : this.amigos.keySet()) {
            amgS.add(email);
        }

        for(Amigo amigoA : this.amigos.values()){
            while(true) {
            pos = (int) (Math.random() * amgS.size());
            if(!amigoA.getEmail().equals(amgS.get(pos))){
                amigoA.setEmailAmigoSorteado(amgS.get(pos));
                amgS.remove(amgS.get(pos));
                relatorio += "A pessoa, cujo email é " + amigoA.getEmail() + ", sorteou um amigo com email " +
                amigoA.getEmailAmigoSorteado() + " como amigo secreto\n";
                break;
            }
        }
    }
        return relatorio;
    }
}
