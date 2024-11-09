package br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.gestaovagasapi.exceptions.CandidateNotFoundException;
import br.com.mickaelsantos.gestaovagasapi.exceptions.JobNotFoundException;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories.CandidateRepository;
import br.com.mickaelsantos.gestaovagasapi.modules.company.repositories.JobRepository;

@Service
public class ApplyJobUseCase 
{
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public void execute(UUID jobUuId, UUID candidateUuId)
    {

        var candidate = candidateRepository.findByUuid(candidateUuId)
        .orElseThrow(() -> {
            throw new CandidateNotFoundException();
        });

        var job = jobRepository.findById(jobUuId)
        .orElseThrow( () -> {
            throw new JobNotFoundException();
        } );
    }
}
