package br.ufpb.dcx.sistemaeleitoral;

import javax.swing.*;

public class SistemaEleitoralJOptionPane {
    public static void main(String [] args) {

        SistemaEleitoralMap sistema = new SistemaEleitoralMap();
        String opcao = "";
        boolean sair = false;

        while (!sair) {
            opcao = JOptionPane.showInputDialog("Opções do programa:\n" +
                                                "1- Votar\n" +
                                                "2- Obter dados do candidato\n" +
                                                "3- Contar votos para candidato\n" +
                                                "4- Cadastrar candidato\n" +
                                                "5- Cadastrar eleitor\n" +
                                                "6- Sair");
            if(opcao.equals("1")) {
                String titulo = JOptionPane.showInputDialog("Digite o número do título da pessoa");
                int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número que a pessoa votou"));
                try {
                    sistema.votar(titulo, numero);
                    JOptionPane.showMessageDialog(null ,"Voto realizado com sucesso!");
                } catch (Exception e) {
                    e.getMessage();
                }

            } else if(opcao.equals("2")) {
                String nomeDoCandidato = JOptionPane.showInputDialog("Digite o nome do candidato");
                try {
                    Candidato candidato = sistema.obterDadosDoCandidato(nomeDoCandidato);
                    JOptionPane.showMessageDialog(null, "Dados do candidato\n" + candidato.toString());
                } catch(CandidatoInexistenteException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            } else if(opcao.equals("3")) {
                int numeroDoCandidato = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do candidato"));
                int qtDeVotosParaCandidato = sistema.contarVotosParaCandidato(numeroDoCandidato);
                JOptionPane.showMessageDialog(null, "A quantidade de votos que o candidato cujo número " + numeroDoCandidato + " obteve foi " + qtDeVotosParaCandidato + " votos");

            } else if(opcao.equals("4")) {
                Partido partido = null;
                String nomeDoCandidato = JOptionPane.showInputDialog("Digite o nome do candidato");
                int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do candidato"));
                String decidirPartido = JOptionPane.showInputDialog("Partidos:\n[1]" + Partido.PARTIDO1 + "\n[2]" + Partido.PARTIDO2 + "\n[3]" + Partido.PARTIDO3 + "\nDigite o código referente ao partido do candidato cadastrado");
                if(decidirPartido.equals("1")) {
                    partido = Partido.PARTIDO1;
                } else if(decidirPartido.equals("2")) {
                    partido = Partido.PARTIDO2;
                } else if(decidirPartido.equals("3")) {
                    partido = Partido.PARTIDO3;
                } else {
                    JOptionPane.showMessageDialog(null, "Código inexistente. Por favor, tente novamente mais tarde!");
                    continue;
                }

                boolean cadastroC = sistema.cadastrarCandidato(nomeDoCandidato, numero, partido);
                if(cadastroC) {
                    JOptionPane.showMessageDialog(null, "O candidato foi cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "O candidato já foi cadastrado!");
                }

            } else if(opcao.equals("5")){
                String nomeDoEleitor = JOptionPane.showInputDialog("Digite o nome do eleitor");
                String numTituloDoEleitor = JOptionPane.showInputDialog("Digite o número do título do eleitor");
                boolean cadastroE = sistema.cadastrarEleitor(nomeDoEleitor, numTituloDoEleitor);
                if(cadastroE) {
                    JOptionPane.showMessageDialog(null, "O eleitor foi cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "O eleitor já foi cadastrado!");
                }

            } else if(opcao.equals("6")) {
                JOptionPane.showMessageDialog(null, "Programa finalizado com sucesso!");
                sair = true;
            } else {
                JOptionPane.showMessageDialog(null, "Opção inexistente. Por favor, digite uma das opções já existentes.");
            }
        }
    }
}
