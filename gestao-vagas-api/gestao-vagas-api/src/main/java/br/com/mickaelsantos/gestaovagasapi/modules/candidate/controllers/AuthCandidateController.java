package br.com.mickaelsantos.gestaovagasapi.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.gestaovagasapi.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.AuthCandidateUseCase;

@RestController
@RequestMapping("/authCandidate")
public class AuthCandidateController
{
    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
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
