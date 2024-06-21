package br.com.mickaelsantos.gestaovagasapi.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.Candidate;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.CreateCandidate;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")

public class CandidateController 
{
    @Autowired
    private CreateCandidate createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping("/register")
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
}
