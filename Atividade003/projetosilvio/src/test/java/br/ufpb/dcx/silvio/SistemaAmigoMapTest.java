package br.ufpb.dcx.silvio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SistemaAmigoMapTest {

    SistemaAmigoMap sistema;

    @BeforeEach
    void setUp()  {
        this.sistema = new SistemaAmigoMap();
    }

    @Test
    void testSistemaAmigoMap() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        assertThrows(AmigoInexistenteException.class,
                ()-> sistema.pesquisaAmigo("silvio@teste.com"));
    }

    @Test
    void testPesquisaECadastraAmigoMap() {
        try {
            sistema.pesquisaAmigo("silvio@teste.com");
            fail("Deveria falhar pois não existe ainda");
        } catch (AmigoInexistenteException e) {
            //Ok
        }
        try {
            sistema.cadastraAmigo("silvio", "silvio@teste.com");
            Amigo a = sistema.pesquisaAmigo("silvio@teste.com");
            assertEquals("silvio", a.getNome());
            assertEquals("silvio@teste.com", a.getEmail());
        } catch (AmigoJaExisteException | AmigoInexistenteException  e) {
            fail("Não deveria lançar exceção");
        }


    }

    @Test
    void testEnviarMensagemParaTodosMap() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaTodos("texto", "silvio.nascimento@dcx.ufpb.br", true);
        List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
        assertTrue(mensagensAchadas.size()==1);
        assertTrue(mensagensAchadas.get(0).getEmailRemetente().equals("silvio.nascimento@dcx.ufpb.br"));
    }

    @Test
    void testEnviarMensagemParaAlguemMap() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto", "silvio.nascimento@dcx.ufpb.br", "maria.cecilia@dcx.ufpb.br", true);
        List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
        assertEquals(1, mensagensAchadas.size());
        assertTrue(mensagensAchadas.get(0) instanceof MensagemParaAlguem);
        assertTrue(mensagensAchadas.get(0).getTexto().equals("texto"));
    }

    @Test
    void testPesquisaMensagensAnonimasMap() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 1", "silvio.nascimento@dcx.ufpb.br", "maria.cecilia@dcx.ufpb.br", false);
        assertTrue(sistema.pesquisaMensagensAnonimas().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 2", "silvio.nascimento@dcx.ufpb.br", "maria.cecilia@dcx.ufpb.br", true);
        assertTrue(sistema.pesquisaMensagensAnonimas().size()==1);
    }

    @Test
    void testPesquisaTodasAsMensagensMap() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 1", "silvio.nascimento@dcx.ufpb.br", "maria.cecilia@dcx.ufpb.br", false);
        assertTrue(sistema.pesquisaTodasAsMensagens().size()==1);
        sistema.enviarMensagemParaAlguem("texto 2", "silvio.nascimento@dcx.ufpb.br", "maria.cecilia@dcx.ufpb.br", true);
        assertTrue(sistema.pesquisaTodasAsMensagens().size()==2);
    }

    @Test
    void testPesquisaAmigoEConfiguraAmigoSecretoDeMap() {
        assertThrows(AmigoInexistenteException.class,
                ()-> sistema.pesquisaAmigoSecretoDe("silvio.nascimento@dcx.ufpb.br"));
        try {
            sistema.cadastraAmigo("Silvio", "silvio.nascimento@dcx.ufpb.br");
            sistema.cadastraAmigo("Maria Cecilia", "maria.cecilia@dcx.ufpb.br");
            sistema.configuraAmigoSecretoDe("silvio.nascimento@dcx.ufpb.br", "maria.cecilia@dcx.ufpb.br");
            sistema.configuraAmigoSecretoDe("maria.cecilia@dcx.ufpb.br", "silvio.nascimento@dcx.ufpb.br");
            assertEquals("maria.cecilia@dcx.ufpb.br", sistema.pesquisaAmigoSecretoDe("silvio.nascimento@dcx.ufpb.br"));
            assertEquals("silvio.nascimento@dcx.ufpb.br", sistema.pesquisaAmigoSecretoDe("maria.cecilia@dcx.ufpb.br"));
        } catch (AmigoInexistenteException | AmigoJaExisteException | AmigoNaoSorteadoException e) {
            fail("Não deveria lançar exceção");
        }
    }

    @Test
    void testSorteioMap() {
        try {
            sistema.cadastraAmigo("Silvio", "silvio.nascimento@dcx.ufpb.br");
            sistema.cadastraAmigo("Maria Cecilia", "maria.cecilia@dcx.ufpb.br");
            sistema.cadastraAmigo("Antonia Beatriz", "antonia.beatriz@dcx.ufpb.br");
            sistema.sortear();
            assert(sistema.pesquisaAmigoSecretoDe("silvio.nascimento@dcx.ufpb.br") != null);
            assert(sistema.pesquisaAmigoSecretoDe("maria.cecilia@dcx.ufpb.br") != null);
            assert(sistema.pesquisaAmigoSecretoDe("antonia.beatriz@dcx.ufpb.br") != null);
        } catch (AmigoInexistenteException | AmigoJaExisteException | AmigoNaoSorteadoException e) {
            fail("Não deveria lançar exceção");
        }
        
    }

}
