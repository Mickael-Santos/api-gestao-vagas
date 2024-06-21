package br.com.mickaelsantos.gestaovagasapi.modules.company.controllers;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.gestaovagasapi.modules.company.dto.AuthCompanyDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.company.useCases.AuthCompanyUseCase;

@RestController
@RequestMapping("/authCompany")

public class AuthCompanyController 
{
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
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
