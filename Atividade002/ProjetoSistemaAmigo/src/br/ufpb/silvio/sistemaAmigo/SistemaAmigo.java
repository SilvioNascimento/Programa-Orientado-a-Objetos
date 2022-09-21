package br.ufpb.silvio.sistemaAmigo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private List<Amigo> amigos;
    private int qtMaxMsg;
    private int qtTotalAmigos;

    public SistemaAmigo() {
        this.mensagens = new LinkedList<>();
        this.amigos = new LinkedList<>();
    }

    public SistemaAmigo(List<Mensagem> mensagens, List<Amigo> amigos) {
        this.mensagens = mensagens;
        this.amigos = amigos;
        this.qtMaxMsg = 0;
        this.qtTotalAmigos = 0;
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
        for(Amigo amigo: this.amigos) {
            if(amigo.getNome().equals(nomeAmigo) && amigo.getEmail().equals(emailAmigo)){
                throw new AmigoJaExisteException("Já existe pessoa com o email" + emailAmigo);

            }
        }
        Amigo cadastro = new Amigo(nomeAmigo, emailAmigo);
        amigos.add(cadastro);
    } 

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException{
        for(Amigo amigo: this.amigos) {
            if(amigo.getEmail().equals(emailAmigo)) {
                return amigo;
            }
        }
        throw new AmigoInexistenteException("O amigo cujo email " + emailAmigo + " é inexistente.");
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
        mensagens.add(new MensagemParaTodos(texto, emailRemetente, ehAnonima));
    }

    public void enviarMensagemParaAlguem(String texto, String emailRementente, String emailDestinatario, boolean ehAnonima) {
        mensagens.add(new MensagemParaAlguem(texto, emailRementente, emailDestinatario, ehAnonima));
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> listaMensagensAnonimas = new LinkedList<>();
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
        boolean situacao = false;
        for(Amigo amigoA : this.amigos) {
            if(amigoA.getEmail().equals(emailDaPessoa)) {
                situacao = true;
                amigoA.setEmailAmigoSorteado(emailAmigoSorteado);
                return;
            }
        }
        if(!situacao){
            throw new AmigoInexistenteException("Amigo não cadastrado.");
        }
    }

    public String sortear() {
        int pos = 0;
        String relatorio = "";
        List<Amigo> nSorteados = new ArrayList<>();
        nSorteados.addAll(this.amigos);
        for(Amigo amigo : this.amigos){
            while(true){
                pos = (int) (Math.random() * nSorteados.size());
                if(!amigo.getEmail().equals(nSorteados.get(pos).getEmail())) {
                    amigo.setEmailAmigoSorteado(nSorteados.get(pos).getEmail());
                    nSorteados.remove(nSorteados.get(pos));
                    relatorio += "A pessoa, cujo email é " + amigo.getEmail() + ", sorteou um amigo com email " +
                    amigo.getEmailAmigoSorteado() + " como amigo secreto\n";
                    break;
                }
            }
        }
        
        return relatorio;
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {
        for(Amigo amigo: this.amigos) {
            if(amigo.getEmail().equals(emailDaPessoa)) {
                if(amigo.getEmailAmigoSorteado() != null) {
                    return amigo.getEmailAmigoSorteado();
                } else {  
                    throw new AmigoNaoSorteadoException("Amigo secreto não sorteado.");
                }
            }
        }
        throw new AmigoInexistenteException("Amigo não cadastrado.");
    }

    

    public long getQtMaxMsg() {
        return this.qtMaxMsg;
    }

    public void setQtMaxMsg(int qtMaxMsg) {
        this.qtMaxMsg = qtMaxMsg;
    }

    public long getQtTotalAmigos() {
        return this.qtTotalAmigos;
    }

    public void setQtTotalAmigos(int qtTotalAmigos) {
        this.qtTotalAmigos = qtTotalAmigos;
    }

    
}
