package br.com.mickaelsantos.gestaovagasapi.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.gestaovagasapi.modules.company.dto.AuthCompanyDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.company.dto.AuthCompanyResponseDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.company.useCases.AuthCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/authCompany")
@Tag(name = "Empresa")

public class AuthCompanyController 
{
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação da empresa", 
    description = "Endpoint para autenticação de um usuário tipo empresa")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {

            @Content(
                schema = @Schema(implementation = AuthCompanyResponseDTO.class)
            )
        }),
        @ApiResponse(responseCode = "400", description = "Usuário ou senha incorreto!")
    })
    public ResponseEntity<Object> getToken(@RequestBody AuthCompanyDTO authCompanyDTO) 
    {
        try
        {
            var result = authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
        
    }
}
