package br.com.mickaelsantos.gestaovagasapi.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.ApplyJob;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.Candidate;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.ApplyJobUseCase;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.CreateCandidate;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")

@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController 
{
    @Autowired
    private CreateCandidate createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobUseCase applyJobUseCase;

    @PostMapping("/register")
    @Operation(summary = "Cadastro de candidatos", 
    description = "Endpoint para cadastro de candidatos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {

            @Content(
                schema = @Schema(implementation = Candidate.class)
            )
        }),
        @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate)
    {
        try
        {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }   
        
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem do candidato",
    description = "Listagem as informações do candidato")
    @ApiResponses
    (
        {
            @ApiResponse(responseCode = "200", content = {
                @Content
                (
                    array = @ArraySchema( schema = @Schema(implementation = Candidate.class))
                )
            }),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
        }
    )
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request)
    {
        try
        {
            var candidateUuId = request.getAttribute("candidate_id");

            var result = profileCandidateUseCase.execute(UUID.fromString(candidateUuId.toString()));

            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
        summary = "Listagem das vagas conforme o filtro informado", 
        description = "Esse endpoint é responsável por listar todas vagas disponíveis conforme o filtro informado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content
            (
                array = @ArraySchema(schema = @Schema(implementation = Job.class))
            )
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<Job> findJobsByFilter(@RequestParam("filter") String filter)
    {
        return listAllJobsByFilterUseCase.execute(filter);
    }

    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
        summary = "Aplicação de um candidato a uma vaga disponível",
        description = "Esse endpoint permite fazer a aplicação de um candidato a uma nova vaga"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content
            (
                schema = @Schema(implementation = ApplyJob.class)
            )
        })
    })
    @SecurityRequirement(name = "jwt_auth")

    public ResponseEntity<Object> applyJob(@RequestBody UUID job, HttpServletRequest request)
    {
        try
        {
            var result = applyJobUseCase.execute(job, 
                UUID.fromString(
                    request.getAttribute("candidate_id").toString()
                )
            );
            return ResponseEntity.ok().body(result);

        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }
}
