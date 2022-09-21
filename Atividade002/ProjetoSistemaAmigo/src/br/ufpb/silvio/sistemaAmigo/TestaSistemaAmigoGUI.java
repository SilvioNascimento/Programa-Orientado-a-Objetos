package br.ufpb.silvio.sistemaAmigo;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

public class TestaSistemaAmigoGUI {
    
    public static void main(String[] args) {
        List<Mensagem> mensagens = new LinkedList<>();
        List<Amigo> amigos = new LinkedList<>();
        SistemaAmigo sistema = new SistemaAmigo(mensagens, amigos);

        // a) Leia a quantidade máxima de mensagens que o sistema suporta (pode usar JOptionPane), inicializando com este valor a variável do tipo SistemaAmigo.
        int qtMaxMsg = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade máxima de mensagens que seu sistema possa suportar"));
        sistema.setQtMaxMsg(qtMaxMsg);

        // b) Leia a quantidade total de amigos a participar da brincadeira
        int qtTotalAmigos = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de amigos que irão participar da brincadeira"));
        sistema.setQtTotalAmigos(qtTotalAmigos);

        // c) Leia os dados (nome e e-mail) de cada um dos amigos e os cadastre.
        for(int n = 0; n < qtTotalAmigos; n++) {
            String nome = JOptionPane.showInputDialog("Informe o nome da pessoa");
            String email = JOptionPane.showInputDialog("Informe o email da pessoa");
            try {
                sistema.cadastraAmigo(nome, email);
            } catch (AmigoJaExisteException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());;
            }
        }

        for(Amigo amigo : amigos) {
            System.out.println(amigo.getEmail());
        }

        // d) Cadastre os resultados do sorteio dos amigos secretos (diga quem pegou quem).sistema.sortear();
        String relatorio = sistema.sortear();
        JOptionPane.showMessageDialog(null, relatorio);

        // e) Envie uma mensagem de algum dos amigos para todos, coletando para isso os dados necessários (ex: remetente, texto, se a mensagem é anônima ou não).
        String mensagensEnviadas = "";
        int pos = (int) (Math.random() * amigos.size());
        sistema.enviarMensagemParaTodos("E aí, galerinha? Tudo bem com vocês?\nEstou mandando esta mensagem para agradecer todos vocês que me ajudaram. Valeu mesmo!", amigos.get(pos).getEmail(), false);
        List<Mensagem> todasAsMensagens = sistema.pesquisaTodasAsMensagens();
        for(Mensagem msg : todasAsMensagens) {
            mensagensEnviadas += msg.getTextoCompletoAExibir() + "\n";
        }
        JOptionPane.showMessageDialog(null, mensagensEnviadas, "Todas as mensagens enviadas", JOptionPane.INFORMATION_MESSAGE);
    }
}
