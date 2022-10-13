package br.ufpb.dcx.sistemaeleitoral;

public interface SistemaEleitoralInterface {
    
    void votar(String numTitulo, int numeroVotado) throws TituloInexistenteException;

    Candidato obterDadosDoCandidato(String nome) throws CandidatoInexistenteException;

    int contarVotosParaCandidato(int numero);

    boolean cadastrarCandidato(String nome, int Numero, Partido partido);
}
