package br.ufpb.dcx.sistemaeleitoral;

public class CandidatoInexistenteException extends Exception {

    private static final long serialVersionUID = 1L;

    public CandidatoInexistenteException(String msg) {
        super(msg);
    }
}
