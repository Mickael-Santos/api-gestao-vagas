package br.com.mickaelsantos.gestaovagasapi.exceptions;

public class UserFoundException extends RuntimeException 
{
    public UserFoundException()
    {
        super("Usuário já existe");
    }
}
