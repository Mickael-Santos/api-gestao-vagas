package br.com.mickaelsantos.gestaovagasapi.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Job;
import br.com.mickaelsantos.gestaovagasapi.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase 
{
    @Autowired
    private JobRepository jobRepository;

    public Job create(Job job)
    {
        return jobRepository.save(job);
    }

}
