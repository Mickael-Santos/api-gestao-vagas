package br.com.mickaelsantos.gestaovagasapi.exceptions;

public class CandidateNotFoundException extends RuntimeException
{
    public CandidateNotFoundException()
    {
        super("Candidato não encontrado!");
    }
}
