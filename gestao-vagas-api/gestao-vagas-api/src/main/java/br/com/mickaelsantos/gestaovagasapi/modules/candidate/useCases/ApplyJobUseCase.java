package br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.gestaovagasapi.exceptions.CandidateNotFoundException;
import br.com.mickaelsantos.gestaovagasapi.exceptions.JobNotFoundException;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories.ApplyJobRepository;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories.CandidateRepository;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.ApplyJob;
import br.com.mickaelsantos.gestaovagasapi.modules.company.repositories.JobRepository;

@Service
public class ApplyJobUseCase 
{
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJob execute(UUID jobUuId, UUID candidateUuId)
    {
        
        this.candidateRepository.findById(candidateUuId)
        .orElseThrow(() -> {
            throw new CandidateNotFoundException();
        });

        this.jobRepository.findById(jobUuId)
        .orElseThrow( () -> {
            throw new JobNotFoundException();
        } );

        var applyJob = ApplyJob.builder()
        .candidateUuId(candidateUuId)
        .jobUuId(jobUuId).build();

        var savedApplyJob = applyJobRepository.save(applyJob);

        return savedApplyJob;
    }
}
