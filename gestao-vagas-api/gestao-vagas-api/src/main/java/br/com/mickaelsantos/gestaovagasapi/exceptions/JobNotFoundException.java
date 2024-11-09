package br.com.mickaelsantos.gestaovagasapi.exceptions;

public class JobNotFoundException extends RuntimeException
{
    public JobNotFoundException()
    {
        super("Vaga não encontrada!");
    }
}