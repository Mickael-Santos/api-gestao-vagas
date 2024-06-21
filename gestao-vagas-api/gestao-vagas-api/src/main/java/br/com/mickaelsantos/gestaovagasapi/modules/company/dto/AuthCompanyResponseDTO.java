package br.com.mickaelsantos.gestaovagasapi.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthCompanyResponseDTO 
{
    private String acessToken;

    private long expiresIn;
}
