package br.com.mickaelsantos.gestaovagasapi.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.gestaovagasapi.modules.company.dto.CreateJobDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Job;
import br.com.mickaelsantos.gestaovagasapi.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")

public class JobController 
{
    @Autowired
    private CreateJobUseCase createJob;

    @PostMapping("/create")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request)
    {

        var companyId = request.getAttribute("company_id");

        var job = Job.builder()
        .benefits(createJobDTO.getBenefits())
        .description(createJobDTO.getDescription())
        .level(createJobDTO.getLevel())
        .companyId(UUID.fromString(companyId.toString()))
        .build();
        
        try
        {
            var result = this.createJob.create(job);
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
