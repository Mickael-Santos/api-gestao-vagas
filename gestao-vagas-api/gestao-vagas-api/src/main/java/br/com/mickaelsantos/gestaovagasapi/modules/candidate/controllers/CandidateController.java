package br.com.mickaelsantos.gestaovagasapi.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.Candidate;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.CreateCandidate;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Job;
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

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

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

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    public List<Job> findJobsByFilter(@RequestParam("filter") String filter)
    {
        return listAllJobsByFilterUseCase.execute(filter);
    }
}
