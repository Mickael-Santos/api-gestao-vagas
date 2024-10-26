package br.com.mickaelsantos.gestaovagasapi.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.gestaovagasapi.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.AuthCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/authCandidate")
@Tag(name = "Candidato", description = "Permite autenticação dos candidatos cadastrados")
public class AuthCandidateController
{
    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação",
    description = "Endpoint para autenticação" )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AuthCandidateResponseDTO.class))
        }),
        @ApiResponse(responseCode = "400", description = "Usuário ou senha incorreta!")
    })
    public ResponseEntity<Object> getToken(@RequestBody AuthCandidateRequestDTO authRequest)
    {
        try
        {
            var result = authCandidateUseCase.execute(authRequest);
            return ResponseEntity.ok().body(result);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    
}
