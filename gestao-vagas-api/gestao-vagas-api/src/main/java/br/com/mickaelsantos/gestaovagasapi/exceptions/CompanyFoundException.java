package br.com.mickaelsantos.gestaovagasapi.exceptions;

public class CompanyFoundException extends RuntimeException
{
    public CompanyFoundException()
    {
        super("A companhia já existe");
    }
}
