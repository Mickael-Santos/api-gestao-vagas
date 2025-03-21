package br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Job;
import br.com.mickaelsantos.gestaovagasapi.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase 
{
    @Autowired
    private JobRepository jobRepository;
    

    public List<Job> execute(String filter)
    {
        return jobRepository.findByDescriptionContaining(filter);
    }
}
