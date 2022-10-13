package br.ufpb.dcx.sistemaeleitoral;

public class TituloInexistenteException extends Exception {

    private static final long serialVersionUID = 1L;

    public TituloInexistenteException(String msg) {
        super(msg);
    }
}
