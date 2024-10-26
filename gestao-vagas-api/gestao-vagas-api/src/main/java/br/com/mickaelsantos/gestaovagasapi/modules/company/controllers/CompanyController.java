package br.com.mickaelsantos.gestaovagasapi.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.gestaovagasapi.exceptions.CompanyFoundException;
import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Company;
import br.com.mickaelsantos.gestaovagasapi.modules.company.useCases.CreateCompany;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
@Tag(name = "Empresa", description = "Informações sobre a empresa")

public class CompanyController 
{
    @Autowired
    private CreateCompany createCompany;

    @PostMapping("/register")
    @Operation(summary = "Criação de usuário",
    description = "Endpoint para criação de novos usuários")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {

            @Content(
                schema = @Schema(implementation = Company.class)
            )
        }),
        @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody Company company)
    {
        try
        {
            var result = createCompany.create(company);
            return ResponseEntity.ok().body(result);
        }
        catch(CompanyFoundException ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
