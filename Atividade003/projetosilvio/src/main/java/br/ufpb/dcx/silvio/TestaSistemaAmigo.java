package br.ufpb.dcx.silvio;

import java.util.LinkedList;
import java.util.List;


public class TestaSistemaAmigo extends SistemaAmigo{

    public static void main(String[] args) {
        List<Mensagem> mensagens = new LinkedList<>();
        List<Amigo> amigos = new LinkedList<>();
        SistemaAmigo sistema = new SistemaAmigo(mensagens, amigos);

        // a) Cadastre dois amigos José e Maria com seus e-mails.
        try {
            sistema.cadastraAmigo("José", "jose@email.com");
            sistema.cadastraAmigo("Maria", "maria@email.com");
            sistema.cadastraAmigo("Manuel", "manuel@email.com");
            sistema.cadastraAmigo("Eduardo", "eduardo@email.com");
            sistema.cadastraAmigo("Silvio", "silvio@email.com");
            System.out.println("José, Maria, Manuel, Eduardo e Silvio foram cadastrados com sucesso\n");
        } catch (AmigoJaExisteException e1) {
            System.out.println(e1.getMessage());
        }

        for(Amigo a : amigos) {
            System.out.println("Nome: " + a.getNome() +
                    ".  Email: " + a.getEmail());
        }

        // b) Configure o amigo secreto de José como sendo Maria e o amigo secreto de Maria como sendo José. Obs: Lembre de tratar a exceção que pode ser lançada.
        try {
            sistema.configuraAmigoSecretoDe("jose@email.com", "maria@email.com");
            sistema.configuraAmigoSecretoDe("maria@email.com", "jose@email.com");
            sistema.configuraAmigoSecretoDe("manuel@email.com", "eduardo@email.com");
            sistema.configuraAmigoSecretoDe("eduardo@email.com", "silvio@email.com");
            sistema.configuraAmigoSecretoDe("silvio@email.com", "manuel@email.com");
            System.out.println("\nConfiguração de amigos secretos realizado com sucesso\n");
        } catch (AmigoInexistenteException e) {
            System.out.println(e.getMessage());
        } finally {
            if(amigos.size() > 0) {
                for(Amigo a : amigos) {
                    System.out.println("O amigo secreto da pessoa cujo email é " + a.getEmail() + " é " + a.getEmailAmigoSorteado());
            }
        }
        }

        // c) Envie uma mensagem anônima de Maria para José.
        sistema.enviarMensagemParaAlguem("Olá, José. Tudo bem?", "maria@email.com", "jose@email.com", true);

        // d) Envie uma mensagem anônima de Maria para todos.
        sistema.enviarMensagemParaTodos("Qual é a boa, pessoal?", "maria@email.com", true);

        // e) Pesquise as mensagens anônimas e imprima o texto completo dessas mensagens através do método getTextoCompletoAExibir.
        List<Mensagem> mensagensAnonimas = sistema.pesquisaMensagensAnonimas();
        for(Mensagem msg: mensagensAnonimas) {
            System.out.println(msg.getTexto());
        }

        // f) Pesquise o e-mail do amigo secreto de José e veja se é o e-mail de Maria, imprimindo “Ok” caso seja.
        try {
            Amigo amigo = sistema.pesquisaAmigo("jose@email.com");
            if(amigo.getEmailAmigoSorteado().equals("maria@email.com")) {
                System.out.println("Ok");
            } else {
                System.out.println("Falha");
            }

        } catch (AmigoInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }
}
