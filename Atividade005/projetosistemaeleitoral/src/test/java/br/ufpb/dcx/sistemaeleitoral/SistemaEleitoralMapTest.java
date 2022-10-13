package br.ufpb.dcx.sistemaeleitoral;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class SistemaEleitoralMapTest {
    
    // Método nº1: Verifica se o cadastro de um candidato é realizado com sucesso
    @Test
    public void testeCadastraCandidato1() {
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();
        boolean candidato = sistema.cadastrarCandidato("João", 1345, Partido.PARTIDO2);
        assertTrue(candidato);
    }

    // Método nº2:  Verifica se o cadastro de um eleitor é realizado com sucesso
    @Test
    public void testeCadastraEleitor1() {
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();
        boolean eleitor = sistema.cadastrarEleitor("Silvio", "40028922");
        assertTrue(eleitor);
    }

    // Método nº3: Tentando cadastrar o mesmo candidato 2 vezes, verificando se o retorno será false
    @Test
    public void testeCadastraCandidato2() {
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();
        boolean candidato1 = sistema.cadastrarCandidato("João", 1345, Partido.PARTIDO2);
        assertTrue(candidato1);

        boolean candidato2 = sistema.cadastrarCandidato("João", 1345, Partido.PARTIDO2);
        assertFalse(candidato2);
    }

    // Método nº4: Testando cadastrar o mesmo eleitor 2 vezes, verificando se o retorno será false
    @Test
    public void testeCadastraEleitor2() {
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();
        boolean eleitor1 = sistema.cadastrarEleitor("Silvio", "40028922");
        assertTrue(eleitor1);

        boolean eleitor2 = sistema.cadastrarEleitor("Silvio", "40028922");
        assertFalse(eleitor2);
    }

    // 5) Método nº5: Verifica se, após realizar a busca de um candidato, retornará um valor diferente de nulo
    @Test
    public void testeObterDadosDoCandidato() {
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();
        sistema.cadastrarCandidato("João", 1345, Partido.PARTIDO2);

        try {
            Candidato buscarCandidato = sistema.obterDadosDoCandidato("João");
            assert(buscarCandidato != null);
        } catch (CandidatoInexistenteException e) {
            fail("Não deveria apresentar uma falha!");
        }

    }

    // Método nº6: Testa se a função votar está funcionando sem apresentar uma exceção
    @Test
    public void testeVotar() {
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();

        sistema.cadastrarCandidato("João", 1345, Partido.PARTIDO2);
        sistema.cadastrarEleitor("Silvio", "40028922");

        try {
            sistema.votar("40028922", 1345);
            System.out.println("Voto realizado com sucesso!");
        } catch (TituloInexistenteException e) {
            fail("Não deveria apresentar uma falha!");
        }
    }

    // Método nº7: Contando a quantidade de votos existentes em um candidato
    @Test
    public void testeQtdDeVotos() {
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();

        // Cadastro de candidatos
        sistema.cadastrarCandidato("João", 1345, Partido.PARTIDO2);
        sistema.cadastrarCandidato("Clodomir", 1306, Partido.PARTIDO2);
        sistema.cadastrarCandidato("Roberto", 9013, Partido.PARTIDO1);
        sistema.cadastrarCandidato("Kleber", 9023, Partido.PARTIDO1);
        sistema.cadastrarCandidato("Eduardo Filho", 4007, Partido.PARTIDO3);

        // Cadastro de eleitores
        boolean eleitor1 = sistema.cadastrarEleitor("Silvio", "12349876");
        assertTrue(eleitor1);

        sistema.cadastrarEleitor("Maria", "29018573");
        sistema.cadastrarEleitor("Mario", "10947529");
        sistema.cadastrarEleitor("Antonia", "22019384");
        sistema.cadastrarEleitor("Cicera", "91284032");
        sistema.cadastrarEleitor("Manoel", "38399876");
        sistema.cadastrarEleitor("Michael", "29001973");
        sistema.cadastrarEleitor("Luigi", "79130000");
        sistema.cadastrarEleitor("Yoshi", "55314384");
        sistema.cadastrarEleitor("Koora", "98127634");

        // Eleitores votando e verificando a quantidade de votos de um candidato
        try {
            sistema.votar("12349876", 4007);
            sistema.votar("29018573", 4007);
            sistema.votar("10947529", 4007);
            sistema.votar("22019384", 1306);
            sistema.votar("91284032", 1306);
            sistema.votar("38399876", 1345);
            sistema.votar("29001973", 9023);
            sistema.votar("79130000", 1345);
            sistema.votar("55314384", 9013);
            sistema.votar("98127634", 4007);

            // Verificando se existe 4 votos em um candidato. Se sim, retorna true. Caso
            // contrário, retorna false
            assertEquals(4, sistema.contarVotosParaCandidato(4007));

        } catch (TituloInexistenteException e) {
            fail("Não deveria apresentar uma falha!");
        }
    }
}
