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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")

public class CompanyController 
{
    @Autowired
    private CreateCompany createCompany;

    @PostMapping("/register")
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
