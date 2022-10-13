package br.ufpb.dcx.sistemaeleitoral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaEleitoralMap {
    
    private List<Voto> votos;
    private Map<String, Candidato> candidatos;
    private Map<String, Eleitor> eleitores;
    
    public SistemaEleitoralMap() {
        this.votos = new ArrayList<>();
        this.candidatos = new HashMap<>();
        this.eleitores = new HashMap<>();
    }

    public void votar(String numTitulo, int numeroVotado) throws TituloInexistenteException {
        Voto voto = new Voto(numeroVotado);
        if(this.eleitores.containsKey(numTitulo)) {
            this.votos.add(voto);
        } else {
            throw new TituloInexistenteException("O número do título " + numTitulo + " não foi cadastrado no sistema");
        }
    }

    public Candidato obterDadosDoCandidato(String nome) throws CandidatoInexistenteException {
        if(!this.candidatos.containsKey(nome)) {
            throw new CandidatoInexistenteException("O candidato " + nome + " não foi cadastrado no sistema");
        } else {
            return this.candidatos.get(nome);
        }
    }

    public int contarVotosParaCandidato(int numero) {
        int cont = 0;
        for(Voto voto : this.votos) {
            if(voto.getNumeroVotado() == numero) {
                cont++;
            }
        }

        return cont;
    }

    public boolean cadastrarCandidato(String nome, int numero, Partido partido) {
        Candidato candidato = new Candidato(nome, numero, partido);
        if(this.candidatos.containsKey(nome)) {
            return false;
        } else {
            this.candidatos.put(nome, candidato);
            return true;
        }
    }

    public boolean cadastrarEleitor(String nome, String titulo) {
        Eleitor eleitor = new Eleitor(nome, titulo);
        if(this.eleitores.containsKey(titulo)) {
            return false;
        } else {
            this.eleitores.put(titulo, eleitor);
            return true;
        }
    }
}
