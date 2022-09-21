package br.ufpb.silvio.sistemaAmigo;

public class AmigoInexistenteException extends Exception {
    
    public AmigoInexistenteException() {
        super();
    }

    public AmigoInexistenteException(String msg) {
        super(msg);
    }
}
