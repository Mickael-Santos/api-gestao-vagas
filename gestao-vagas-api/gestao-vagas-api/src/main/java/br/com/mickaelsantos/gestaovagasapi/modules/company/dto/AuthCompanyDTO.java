package br.com.mickaelsantos.gestaovagasapi.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AuthCompanyDTO 
{
    private String username;
    private String password;
}